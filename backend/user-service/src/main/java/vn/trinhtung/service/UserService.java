package vn.trinhtung.service;

import java.util.List;

import org.springframework.data.domain.Page;

import vn.trinhtung.dto.*;
import vn.trinhtung.grpc.UserResponse;

public interface UserService {
	void register(UserRegistrationRequestDTO request);

	Page<UserResponseDTO> getAllUserByPage(Integer page);

	UserResponseDTO getById(Integer userId);

	ProfileResponseDTO getProfile(Integer userId);
	
	List<UserResponseDTO> getAllUserByIds(List<Integer> ids);

	void updateProfile(Integer id, ProfileRequestDTO request);

	void updateUser(Integer userId, UserRequestDTO request);

	void verifyRegister(String code);

	void updatePassword(Integer userId, UpdatePasswordRequestDTO request);

	void resetPassword(String email);

	void savePassword(String code);

	void updateUserStatus(Integer userId, Integer status);

	List<UserResponse> getUsersByIds(List<Integer> ids);

    void savePassword(ResetPasswordRequestDTO request);
}
