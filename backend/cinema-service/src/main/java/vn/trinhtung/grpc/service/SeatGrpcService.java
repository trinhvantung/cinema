package vn.trinhtung.grpc.service;

import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import vn.trinhtung.grpc.*;
import vn.trinhtung.service.SeatService;

import java.util.List;

@RequiredArgsConstructor
@GrpcService
public class SeatGrpcService extends SeatServiceGrpc.SeatServiceImplBase {
    private final SeatService seatService;

    @Override
    public void getSeatsByHall(GetSeatsByHallRequest request, StreamObserver<GetSeatsByHallResponse> responseObserver) {
        List<SeatResponse> seatResponses = seatService.getSeatsByHall(request.getHallId());
        GetSeatsByHallResponse response = GetSeatsByHallResponse.newBuilder().addAllSeats(seatResponses).build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getSeatsByIds(GetSeatsByIdsRequest request, StreamObserver<GetSeatsByIdsResponse> responseObserver) {
        List<SeatResponse> seatResponses = seatService.getSeatsByIds(request.getIdsList());
        GetSeatsByIdsResponse response = GetSeatsByIdsResponse.newBuilder().addAllSeats(seatResponses).build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
