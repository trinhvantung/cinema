package vn.trinhtung.service.impl;

import lombok.RequiredArgsConstructor;
import net.bytebuddy.utility.RandomString;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.trinhtung.dto.*;
import vn.trinhtung.entity.ResetPasswordToken;
import vn.trinhtung.entity.Role;
import vn.trinhtung.entity.User;
import vn.trinhtung.event.UserRegisteredEvent;
import vn.trinhtung.event.UserResetPasswordEvent;
import vn.trinhtung.exception.ApplicationException;
import vn.trinhtung.grpc.UserResponse;
import vn.trinhtung.mapper.RoleMapper;
import vn.trinhtung.mapper.UserMapper;
import vn.trinhtung.repository.ResetPasswordTokenRepository;
import vn.trinhtung.repository.RoleRepository;
import vn.trinhtung.repository.UserRepository;
import vn.trinhtung.service.UserService;

import javax.transaction.Transactional;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleMapper roleMapper;
    private final RoleRepository roleRepository;
    private final ResetPasswordTokenRepository resetPasswordTokenRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public void register(UserRegistrationRequestDTO request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new ApplicationException("duplicate_user_email", "Duplicate email");
        }

        String activationCode = UUID.randomUUID().toString();

        User user = userMapper.userRegistrationRequestDTOToUser(request);
        user.setNonLock(true);
        user.setActivationCode(activationCode);
        user.setEnable(false);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRoles(Collections.singletonList(roleRepository.findByName("USER")
                .orElseThrow(() -> new ApplicationException("role_not_found", "Role not found"))));

        final String urlVerify = "http://localhost:5173/verify/" + activationCode;

        UserRegisteredEvent event = new UserRegisteredEvent(request.getEmail(), user.getFullName(), urlVerify);
        userRepository.save(user);
        kafkaTemplate.send("user-registration", event);
    }

    @Override
    public Page<UserResponseDTO> getAllUserByPage(Integer page) {
        Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("id").descending());
        return userRepository.findAll(pageable).map(userMapper::userToUserResponseDTO);
    }

    @Override
    public UserResponseDTO getById(Integer userId) {
        return userMapper.userToUserResponseDTO(userRepository.findById(userId)
                .orElseThrow(() -> new ApplicationException("user_not_found", "User not found")));
    }

    @Override
    public ProfileResponseDTO getProfile(Integer userId) {
        return userMapper.userToProfileResponseDTO(userRepository.findById(userId)
                .orElseThrow(() -> new ApplicationException("user_not_found", "User not found")));
    }

    @Override
    public void updateProfile(Integer id, ProfileRequestDTO request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ApplicationException("user_not_found", "User not found"));

        user.setFullName(request.getFullName());

        userRepository.save(user);
    }

    @Override
    public void updateUser(Integer id, UserRequestDTO request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ApplicationException("user_not_found", "User not found"));

        Role adminRole = roleRepository.findByName("ADMIN").get();

        user.setEmail(request.getEmail());
        user.setNonLock(request.isNonLock());
        user.setFullName(request.getFullName());

        if (Objects.nonNull(request.getPassword()) && !request.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        if (!user.getRoles().contains(new Role(null, "ADMIN"))) {
            if (request.getRoles() == null || request.getRoles().isEmpty()
                    || request.getRoles().contains(new RoleRequestDTO(adminRole.getId()))) {
                Role userRole = roleRepository.findByName("USER").get();
                user.getRoles().clear();
                user.getRoles().add(userRole);
            } else {
                user.getRoles().clear();
                user.getRoles().addAll(request.getRoles().stream()
                        .filter(role -> !role.getId().equals(adminRole.getId()))
                        .map(roleMapper::roleRequestDTOToRole).collect(Collectors.toList()));
            }
        }


        userRepository.save(user);
    }

    @Override
    public void verifyRegister(String code) {
        User user = userRepository.findByActivationCode(code)
                .orElseThrow(() -> new ApplicationException("user_not_found", "User not found"));

        user.setEnable(true);
        user.setActivationCode("");

        userRepository.save(user);
    }

    @Override
    public void updatePassword(Integer userId, UpdatePasswordRequestDTO request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ApplicationException("user_not_found", "User not found"));

        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new ApplicationException("user_password_not_match", "User password not match");
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));

        userRepository.save(user);
    }

    @Override
    public void resetPassword(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ApplicationException("user_not_found", "User not found"));


        String token = RandomString.make(10);

        if (redisTemplate.opsForValue().setIfAbsent(String.format("user::reset_password::%s", email), token, Duration.ofMinutes(15))) {

            LocalDateTime expire = LocalDateTime.now().plusMinutes(15);

//            ResetPasswordToken resetPasswordToken = new ResetPasswordToken(token, expire, user);
//
//            resetPasswordTokenRepository.save(resetPasswordToken);

            UserResetPasswordEvent event = new UserResetPasswordEvent(email, token, expire);

            kafkaTemplate.send("user-reset-password", event);
        } else {
            throw new ApplicationException("reset_password_error", "Token sent");
        }
    }


    @Override
    public void savePassword(ResetPasswordRequestDTO request) {
//        ResetPasswordToken resetPasswordToken = resetPasswordTokenRepository.findByEmailAndToken(request.getEmail(),
//                request.getToken()).orElseThrow(() -> new ApplicationException("token_not_found", "Token not found"));

        String key = String.format("user::reset_password::%s", request.getEmail());

        String token = redisTemplate.opsForValue().get(key).toString();

        if (token.equals(request.getToken())) {

            User user = userRepository.findByEmail(request.getEmail())
                    .orElseThrow(() -> new ApplicationException("user_not_found", "User not found"));

            user.setPassword(passwordEncoder.encode(request.getNewPassword()));

            userRepository.save(user);
            redisTemplate.delete(key);
//            resetPasswordTokenRepository.deleteById(request.getToken());


        } else {
            throw new ApplicationException("token_not_found", "Token not found");
        }

//        if (resetPasswordToken.getResetPasswordExpire().isBefore(LocalDateTime.now())) {
//            throw new ApplicationException("token_has_expired", "Token has expired");
//        }

    }

    @Transactional
    @Override
    public void savePassword(String code) {
//        ResetPasswordToken resetPasswordToken = resetPasswordTokenRepository.findById(code)
//                .orElseThrow(() -> new ApplicationException("token_not_found", "Token not found"));
//
//        if (resetPasswordToken.getResetPasswordExpire().isBefore(LocalDateTime.now())) {
//            throw new ApplicationException("token_not_found", "Token not found");
//        }
//
//        User user = userRepository.findById(resetPasswordToken.getUser().getId())
//                .orElseThrow(() -> new ApplicationException("user_not_found", "User not found"));
//
//        userRepository.save(user);
//        resetPasswordTokenRepository.deleteById(code);
    }

    @Override
    public void updateUserStatus(Integer userId, Integer status) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ApplicationException("user_not_found", "User not found"));
        user.setNonLock(status != 0);
        userRepository.save(user);
    }

    @Override
    public List<UserResponse> getUsersByIds(List<Integer> ids) {
        List<User> users = userRepository.findAllById(ids);
        return users.stream().map(userMapper::userToUserResponse).collect(Collectors.toList());
    }


    @Override
    public List<UserResponseDTO> getAllUserByIds(List<Integer> ids) {
        return userRepository.findAllById(ids).stream().map(userMapper::userToUserResponseDTO)
                .collect(Collectors.toList());
    }

}
