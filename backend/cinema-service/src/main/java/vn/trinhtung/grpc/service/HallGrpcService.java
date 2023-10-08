package vn.trinhtung.grpc.service;

import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import vn.trinhtung.grpc.*;
import vn.trinhtung.service.HallService;

import java.util.List;

@RequiredArgsConstructor
@GrpcService
public class HallGrpcService extends HallServiceGrpc.HallServiceImplBase {
    private final HallService hallService;

    @Override
    public void getHallById(GetHallByIdRequest request, StreamObserver<GetHallByIdResponse> responseObserver) {
        HallResponse hallResponse = hallService.getHallById(request.getId());

        GetHallByIdResponse response = GetHallByIdResponse.newBuilder().setHall(hallResponse).build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getHallsByIds(GetHallsByIdsRequest request, StreamObserver<GetHallsByIdsResponse> responseObserver) {
        List<HallResponse> hallResponses = hallService.getHallsByIds(request.getIdsList());

        GetHallsByIdsResponse response = GetHallsByIdsResponse.newBuilder().addAllHalls(hallResponses).build();


        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
