package vn.trinhtung.grpc.service;

import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import vn.trinhtung.grpc.CinemaResponse;
import vn.trinhtung.grpc.CinemaServiceGrpc;
import vn.trinhtung.grpc.GetCinemasByIdsRequest;
import vn.trinhtung.grpc.GetCinemasByIdsResponse;
import vn.trinhtung.service.CinemaService;

import java.util.List;

@RequiredArgsConstructor
@GrpcService
public class CinemaGrpcService extends CinemaServiceGrpc.CinemaServiceImplBase {
    private final CinemaService cinemaService;

    @Override
    public void getCinemasByIds(GetCinemasByIdsRequest request, StreamObserver<GetCinemasByIdsResponse> responseObserver) {
        List<CinemaResponse> cinemaResponses = cinemaService.getCinemasByIds(request.getIdsList());

        GetCinemasByIdsResponse response = GetCinemasByIdsResponse.newBuilder().addAllCinemas(cinemaResponses).build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
