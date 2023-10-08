package vn.trinhtung.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.trinhtung.dto.UserResponseDTO;
import vn.trinhtung.grpc.UserResponse;

@RequiredArgsConstructor
@Component
public class UserMapper {

	public UserResponseDTO userResponseDTOToUserResponse(UserResponse user) {
		return UserResponseDTO.builder()
				.id(user.getId())
				.firstName(user.getFirstName())
				.lastName(user.getLastName())
				.build();
	}
}
