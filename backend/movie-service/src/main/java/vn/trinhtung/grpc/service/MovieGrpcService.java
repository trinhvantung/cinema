package vn.trinhtung.grpc.service;

import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import vn.trinhtung.grpc.*;
import vn.trinhtung.service.MovieService;

import java.util.List;

@RequiredArgsConstructor
@GrpcService
public class MovieGrpcService extends MovieServiceGrpc.MovieServiceImplBase {
    private final MovieService movieService;

    @Override
    public void getMovieById(GetMovieByIdRequest request, StreamObserver<GetMovieByIdResponse> responseObserver) {
        MovieResponse movieResponse = movieService.getMovieById(request.getId());

        GetMovieByIdResponse response = GetMovieByIdResponse.newBuilder().setMovie(movieResponse).build();


        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getMoviesByIds(GetMoviesByIdsRequest request, StreamObserver<GetMoviesByIdsResponse> responseObserver) {

        List<MovieResponse> movieResponses = movieService.getMoviesByIds(request.getIdsList());

        GetMoviesByIdsResponse response = GetMoviesByIdsResponse.newBuilder().addAllMovies(movieResponses).build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
