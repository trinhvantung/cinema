package vn.trinhtung.grpc.service;

import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import vn.trinhtung.grpc.*;
import vn.trinhtung.service.SeatTypeService;

import java.util.List;

@RequiredArgsConstructor
@GrpcService
public class SeatTypeGrpcService extends SeatTypeServiceGrpc.SeatTypeServiceImplBase {
    private final SeatTypeService seatTypeService;

    @Override
    public void getSeatTypesByHall(GetSeaTypesByHallRequest request, StreamObserver<GetSeaTypesByHallResponse> responseObserver) {
        List<SeatTypeResponse> seatTypeResponses = seatTypeService.getSeatTypesByHall(request.getHallId());

        GetSeaTypesByHallResponse response = GetSeaTypesByHallResponse.newBuilder().addAllSeatTypes(seatTypeResponses).build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
