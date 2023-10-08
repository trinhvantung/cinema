package vn.trinhtung.grpc.service;

import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import vn.trinhtung.grpc.*;
import vn.trinhtung.service.ShowService;

import java.util.List;

@RequiredArgsConstructor
@GrpcService
public class ShowGrpcService extends ShowServiceGrpc.ShowServiceImplBase {
    private final ShowService showService;

    @Override
    public void getShowById(GetShowByIdRequest request, StreamObserver<GetShowByIdResponse> responseObserver) {
        ShowResponse showResponse = showService.getShowById(request.getId());

        GetShowByIdResponse response = GetShowByIdResponse.newBuilder().setShow(showResponse).build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getShowsByIds(GetShowsByIdsRequest request, StreamObserver<GetShowsByIdsResponse> responseObserver) {

        List<ShowResponse> showResponses = showService.getShowsByIds(request.getIdsList());

        GetShowsByIdsResponse response = GetShowsByIdsResponse.newBuilder().addAllShows(showResponses).build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
