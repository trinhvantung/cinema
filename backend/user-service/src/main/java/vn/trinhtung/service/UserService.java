package vn.trinhtung.service;

import java.util.List;

import org.springframework.data.domain.Page;

import vn.trinhtung.dto.*;
import vn.trinhtung.grpc.UserResponse;

public interface UserService {
    ProfileResponseDTO getProfile(String userId);

    List<UserResponse> getUsersByIds(List<String> ids);

    void updateProfile(String id, ProfileRequestDTO request);


}
