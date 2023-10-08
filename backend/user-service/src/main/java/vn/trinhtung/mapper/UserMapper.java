package vn.trinhtung.mapper;

import lombok.RequiredArgsConstructor;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Component;
import vn.trinhtung.dto.ProfileResponseDTO;
import vn.trinhtung.grpc.UserResponse;

@RequiredArgsConstructor
@Component
public class UserMapper {

	public ProfileResponseDTO userRepresentationToProfileResponseDTO(UserRepresentation user) {
		return ProfileResponseDTO.builder().email(user.getEmail())
				.firstName(user.getFirstName())
				.lastName(user.getLastName())
				.build();
	}

	public UserResponse userRepresentationToUserResponse(UserRepresentation user) {
		return UserResponse.newBuilder()
				.setId(user.getId())
				.setFirstName(user.getFirstName())
				.setLastName(user.getLastName())
				.setEmail(user.getEmail())
				.build();
	}
}
