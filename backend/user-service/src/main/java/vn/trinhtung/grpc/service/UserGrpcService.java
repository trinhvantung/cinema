package vn.trinhtung.grpc.service;

import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import vn.trinhtung.grpc.*;
import vn.trinhtung.service.UserService;

import java.util.List;

@RequiredArgsConstructor
@GrpcService
public class UserGrpcService extends UserServiceGrpc.UserServiceImplBase {
    private final UserService userService;

    @Override
    public void getUsersByIds(GetUsersByIdsRequest request, StreamObserver<GetUsersByIdsResponse> responseObserver) {
        List<UserResponse> userResponses = userService.getUsersByIds(request.getIdsList());

        GetUsersByIdsResponse response = GetUsersByIdsResponse.newBuilder().addAllUsers(userResponses).build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
