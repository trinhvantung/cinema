package vn.trinhtung.mapper;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import vn.trinhtung.dto.*;
import vn.trinhtung.entity.User;
import vn.trinhtung.grpc.UserResponse;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class UserMapper {
	private final RoleMapper roleMapper;

	public UserResponseDTO userToUserResponseDTO(User user) {
		List<RoleResponseDTO> roleResponseDTOs = null;
		if(user.getRoles() != null && user.getRoles().size() > 0) {
			roleResponseDTOs = user.getRoles().stream().map(roleMapper::roleToRoleResponseDTO).collect(Collectors.toList());
		}
		return UserResponseDTO.builder().id(user.getId()).email(user.getEmail())
				.fullName(user.getFullName())
				.roles(roleResponseDTOs).nonLock(user.isNonLock()).enable(user.isEnable())
				.build();
	}

	public ProfileResponseDTO userToProfileResponseDTO(User user) {
		return ProfileResponseDTO.builder().email(user.getEmail()).fullName(user.getFullName())
				.build();
	}

	public User userRequestDTOToUser(UserRequestDTO request) {
		return User.builder().email(request.getEmail()).fullName(request.getFullName())
				.nonLock(request.isNonLock())
				.roles(request.getRoles().stream().map(roleMapper::roleRequestDTOToRole).collect(Collectors.toList()))
				.build();
	}

	public User userRegistrationRequestDTOToUser(UserRegistrationRequestDTO request) {
		return User.builder().email(request.getEmail()).fullName(request.getFullName())
				.password(request.getPassword()).build();
	}

	public User profileRequestDTOToUser(ProfileRequestDTO request) {
		return User.builder().fullName(request.getFullName())
				.build();
	}

	public UserResponse userToUserResponse(User user) {
		return UserResponse.newBuilder()
				.setId(user.getId())
				.setFullName(user.getFullName())
				.setEmail("")
				.build();
	}
}
