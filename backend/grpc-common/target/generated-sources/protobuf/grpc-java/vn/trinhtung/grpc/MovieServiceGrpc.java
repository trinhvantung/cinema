package vn.trinhtung.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.42.1)",
    comments = "Source: movie.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class MovieServiceGrpc {

  private MovieServiceGrpc() {}

  public static final String SERVICE_NAME = "vn.trinhtung.grpc.MovieService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<vn.trinhtung.grpc.GetMovieByIdRequest,
      vn.trinhtung.grpc.GetMovieByIdResponse> getGetMovieByIdMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getMovieById",
      requestType = vn.trinhtung.grpc.GetMovieByIdRequest.class,
      responseType = vn.trinhtung.grpc.GetMovieByIdResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<vn.trinhtung.grpc.GetMovieByIdRequest,
      vn.trinhtung.grpc.GetMovieByIdResponse> getGetMovieByIdMethod() {
    io.grpc.MethodDescriptor<vn.trinhtung.grpc.GetMovieByIdRequest, vn.trinhtung.grpc.GetMovieByIdResponse> getGetMovieByIdMethod;
    if ((getGetMovieByIdMethod = MovieServiceGrpc.getGetMovieByIdMethod) == null) {
      synchronized (MovieServiceGrpc.class) {
        if ((getGetMovieByIdMethod = MovieServiceGrpc.getGetMovieByIdMethod) == null) {
          MovieServiceGrpc.getGetMovieByIdMethod = getGetMovieByIdMethod =
              io.grpc.MethodDescriptor.<vn.trinhtung.grpc.GetMovieByIdRequest, vn.trinhtung.grpc.GetMovieByIdResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getMovieById"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  vn.trinhtung.grpc.GetMovieByIdRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  vn.trinhtung.grpc.GetMovieByIdResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MovieServiceMethodDescriptorSupplier("getMovieById"))
              .build();
        }
      }
    }
    return getGetMovieByIdMethod;
  }

  private static volatile io.grpc.MethodDescriptor<vn.trinhtung.grpc.GetMoviesByIdsRequest,
      vn.trinhtung.grpc.GetMoviesByIdsResponse> getGetMoviesByIdsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getMoviesByIds",
      requestType = vn.trinhtung.grpc.GetMoviesByIdsRequest.class,
      responseType = vn.trinhtung.grpc.GetMoviesByIdsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<vn.trinhtung.grpc.GetMoviesByIdsRequest,
      vn.trinhtung.grpc.GetMoviesByIdsResponse> getGetMoviesByIdsMethod() {
    io.grpc.MethodDescriptor<vn.trinhtung.grpc.GetMoviesByIdsRequest, vn.trinhtung.grpc.GetMoviesByIdsResponse> getGetMoviesByIdsMethod;
    if ((getGetMoviesByIdsMethod = MovieServiceGrpc.getGetMoviesByIdsMethod) == null) {
      synchronized (MovieServiceGrpc.class) {
        if ((getGetMoviesByIdsMethod = MovieServiceGrpc.getGetMoviesByIdsMethod) == null) {
          MovieServiceGrpc.getGetMoviesByIdsMethod = getGetMoviesByIdsMethod =
              io.grpc.MethodDescriptor.<vn.trinhtung.grpc.GetMoviesByIdsRequest, vn.trinhtung.grpc.GetMoviesByIdsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getMoviesByIds"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  vn.trinhtung.grpc.GetMoviesByIdsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  vn.trinhtung.grpc.GetMoviesByIdsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MovieServiceMethodDescriptorSupplier("getMoviesByIds"))
              .build();
        }
      }
    }
    return getGetMoviesByIdsMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static MovieServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MovieServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MovieServiceStub>() {
        @java.lang.Override
        public MovieServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MovieServiceStub(channel, callOptions);
        }
      };
    return MovieServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static MovieServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MovieServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MovieServiceBlockingStub>() {
        @java.lang.Override
        public MovieServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MovieServiceBlockingStub(channel, callOptions);
        }
      };
    return MovieServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static MovieServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MovieServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MovieServiceFutureStub>() {
        @java.lang.Override
        public MovieServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MovieServiceFutureStub(channel, callOptions);
        }
      };
    return MovieServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class MovieServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void getMovieById(vn.trinhtung.grpc.GetMovieByIdRequest request,
        io.grpc.stub.StreamObserver<vn.trinhtung.grpc.GetMovieByIdResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetMovieByIdMethod(), responseObserver);
    }

    /**
     */
    public void getMoviesByIds(vn.trinhtung.grpc.GetMoviesByIdsRequest request,
        io.grpc.stub.StreamObserver<vn.trinhtung.grpc.GetMoviesByIdsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetMoviesByIdsMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetMovieByIdMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                vn.trinhtung.grpc.GetMovieByIdRequest,
                vn.trinhtung.grpc.GetMovieByIdResponse>(
                  this, METHODID_GET_MOVIE_BY_ID)))
          .addMethod(
            getGetMoviesByIdsMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                vn.trinhtung.grpc.GetMoviesByIdsRequest,
                vn.trinhtung.grpc.GetMoviesByIdsResponse>(
                  this, METHODID_GET_MOVIES_BY_IDS)))
          .build();
    }
  }

  /**
   */
  public static final class MovieServiceStub extends io.grpc.stub.AbstractAsyncStub<MovieServiceStub> {
    private MovieServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MovieServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MovieServiceStub(channel, callOptions);
    }

    /**
     */
    public void getMovieById(vn.trinhtung.grpc.GetMovieByIdRequest request,
        io.grpc.stub.StreamObserver<vn.trinhtung.grpc.GetMovieByIdResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetMovieByIdMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getMoviesByIds(vn.trinhtung.grpc.GetMoviesByIdsRequest request,
        io.grpc.stub.StreamObserver<vn.trinhtung.grpc.GetMoviesByIdsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetMoviesByIdsMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class MovieServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<MovieServiceBlockingStub> {
    private MovieServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MovieServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MovieServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public vn.trinhtung.grpc.GetMovieByIdResponse getMovieById(vn.trinhtung.grpc.GetMovieByIdRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetMovieByIdMethod(), getCallOptions(), request);
    }

    /**
     */
    public vn.trinhtung.grpc.GetMoviesByIdsResponse getMoviesByIds(vn.trinhtung.grpc.GetMoviesByIdsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetMoviesByIdsMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class MovieServiceFutureStub extends io.grpc.stub.AbstractFutureStub<MovieServiceFutureStub> {
    private MovieServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MovieServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MovieServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<vn.trinhtung.grpc.GetMovieByIdResponse> getMovieById(
        vn.trinhtung.grpc.GetMovieByIdRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetMovieByIdMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<vn.trinhtung.grpc.GetMoviesByIdsResponse> getMoviesByIds(
        vn.trinhtung.grpc.GetMoviesByIdsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetMoviesByIdsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_MOVIE_BY_ID = 0;
  private static final int METHODID_GET_MOVIES_BY_IDS = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final MovieServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(MovieServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_MOVIE_BY_ID:
          serviceImpl.getMovieById((vn.trinhtung.grpc.GetMovieByIdRequest) request,
              (io.grpc.stub.StreamObserver<vn.trinhtung.grpc.GetMovieByIdResponse>) responseObserver);
          break;
        case METHODID_GET_MOVIES_BY_IDS:
          serviceImpl.getMoviesByIds((vn.trinhtung.grpc.GetMoviesByIdsRequest) request,
              (io.grpc.stub.StreamObserver<vn.trinhtung.grpc.GetMoviesByIdsResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class MovieServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    MovieServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return vn.trinhtung.grpc.Movie.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("MovieService");
    }
  }

  private static final class MovieServiceFileDescriptorSupplier
      extends MovieServiceBaseDescriptorSupplier {
    MovieServiceFileDescriptorSupplier() {}
  }

  private static final class MovieServiceMethodDescriptorSupplier
      extends MovieServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    MovieServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (MovieServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new MovieServiceFileDescriptorSupplier())
              .addMethod(getGetMovieByIdMethod())
              .addMethod(getGetMoviesByIdsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
