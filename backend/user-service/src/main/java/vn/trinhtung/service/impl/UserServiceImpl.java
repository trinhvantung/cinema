package vn.trinhtung.service.impl;

import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import vn.trinhtung.config.KeycloakPropertiesConfig;
import vn.trinhtung.dto.ProfileRequestDTO;
import vn.trinhtung.dto.ProfileResponseDTO;
import vn.trinhtung.exception.ApplicationException;
import vn.trinhtung.grpc.UserResponse;
import vn.trinhtung.mapper.UserMapper;
import vn.trinhtung.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final Keycloak keycloak;
    private final KeycloakPropertiesConfig keycloakProperties;


    @Override
    public ProfileResponseDTO getProfile(String userId) {
        UserRepresentation userRepresentation = keycloak.realm(keycloakProperties.getRealm()).users()
                .get(userId).toRepresentation();
        if (Objects.isNull(userRepresentation)) {
            return null;
        }
        return userMapper.userRepresentationToProfileResponseDTO(userRepresentation);
    }

    @Override
    public void updateProfile(String id, ProfileRequestDTO request) {
        String idTemp = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(idTemp);

        UserRepresentation userRepresentation = keycloak.realm(keycloakProperties.getRealm())
                .users().get(id).toRepresentation();
        if (Objects.isNull(userRepresentation)) {
            throw new ApplicationException("user_not_found", "User not found");
        }

        UserResource userResource = keycloak.realm(keycloakProperties.getRealm())
                .users().get(id);

        userRepresentation.setFirstName(request.getFirstName());
        userRepresentation.setLastName(request.getLastName());

        userResource.update(userRepresentation);
    }

    @Override
    public List<UserResponse> getUsersByIds(List<String> ids) {
        List<UserRepresentation> users = new ArrayList<>();

        for(String id:ids) {
            UserRepresentation user = keycloak.realm(keycloakProperties.getRealm())
                    .users().get(id).toRepresentation();
            users.add(user);
        }

        return users.stream().map(userMapper::userRepresentationToUserResponse).collect(Collectors.toList());

    }
}
