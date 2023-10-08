package vn.trinhtung.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.42.1)",
    comments = "Source: cinema.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class CinemaServiceGrpc {

  private CinemaServiceGrpc() {}

  public static final String SERVICE_NAME = "vn.trinhtung.grpc.CinemaService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<vn.trinhtung.grpc.GetCinemasByIdsRequest,
      vn.trinhtung.grpc.GetCinemasByIdsResponse> getGetCinemasByIdsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getCinemasByIds",
      requestType = vn.trinhtung.grpc.GetCinemasByIdsRequest.class,
      responseType = vn.trinhtung.grpc.GetCinemasByIdsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<vn.trinhtung.grpc.GetCinemasByIdsRequest,
      vn.trinhtung.grpc.GetCinemasByIdsResponse> getGetCinemasByIdsMethod() {
    io.grpc.MethodDescriptor<vn.trinhtung.grpc.GetCinemasByIdsRequest, vn.trinhtung.grpc.GetCinemasByIdsResponse> getGetCinemasByIdsMethod;
    if ((getGetCinemasByIdsMethod = CinemaServiceGrpc.getGetCinemasByIdsMethod) == null) {
      synchronized (CinemaServiceGrpc.class) {
        if ((getGetCinemasByIdsMethod = CinemaServiceGrpc.getGetCinemasByIdsMethod) == null) {
          CinemaServiceGrpc.getGetCinemasByIdsMethod = getGetCinemasByIdsMethod =
              io.grpc.MethodDescriptor.<vn.trinhtung.grpc.GetCinemasByIdsRequest, vn.trinhtung.grpc.GetCinemasByIdsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getCinemasByIds"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  vn.trinhtung.grpc.GetCinemasByIdsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  vn.trinhtung.grpc.GetCinemasByIdsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new CinemaServiceMethodDescriptorSupplier("getCinemasByIds"))
              .build();
        }
      }
    }
    return getGetCinemasByIdsMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static CinemaServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CinemaServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CinemaServiceStub>() {
        @java.lang.Override
        public CinemaServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CinemaServiceStub(channel, callOptions);
        }
      };
    return CinemaServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static CinemaServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CinemaServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CinemaServiceBlockingStub>() {
        @java.lang.Override
        public CinemaServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CinemaServiceBlockingStub(channel, callOptions);
        }
      };
    return CinemaServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static CinemaServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CinemaServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CinemaServiceFutureStub>() {
        @java.lang.Override
        public CinemaServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CinemaServiceFutureStub(channel, callOptions);
        }
      };
    return CinemaServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class CinemaServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void getCinemasByIds(vn.trinhtung.grpc.GetCinemasByIdsRequest request,
        io.grpc.stub.StreamObserver<vn.trinhtung.grpc.GetCinemasByIdsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetCinemasByIdsMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetCinemasByIdsMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                vn.trinhtung.grpc.GetCinemasByIdsRequest,
                vn.trinhtung.grpc.GetCinemasByIdsResponse>(
                  this, METHODID_GET_CINEMAS_BY_IDS)))
          .build();
    }
  }

  /**
   */
  public static final class CinemaServiceStub extends io.grpc.stub.AbstractAsyncStub<CinemaServiceStub> {
    private CinemaServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CinemaServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CinemaServiceStub(channel, callOptions);
    }

    /**
     */
    public void getCinemasByIds(vn.trinhtung.grpc.GetCinemasByIdsRequest request,
        io.grpc.stub.StreamObserver<vn.trinhtung.grpc.GetCinemasByIdsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetCinemasByIdsMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class CinemaServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<CinemaServiceBlockingStub> {
    private CinemaServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CinemaServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CinemaServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public vn.trinhtung.grpc.GetCinemasByIdsResponse getCinemasByIds(vn.trinhtung.grpc.GetCinemasByIdsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetCinemasByIdsMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class CinemaServiceFutureStub extends io.grpc.stub.AbstractFutureStub<CinemaServiceFutureStub> {
    private CinemaServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CinemaServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CinemaServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<vn.trinhtung.grpc.GetCinemasByIdsResponse> getCinemasByIds(
        vn.trinhtung.grpc.GetCinemasByIdsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetCinemasByIdsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_CINEMAS_BY_IDS = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final CinemaServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(CinemaServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_CINEMAS_BY_IDS:
          serviceImpl.getCinemasByIds((vn.trinhtung.grpc.GetCinemasByIdsRequest) request,
              (io.grpc.stub.StreamObserver<vn.trinhtung.grpc.GetCinemasByIdsResponse>) responseObserver);
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

  private static abstract class CinemaServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    CinemaServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return vn.trinhtung.grpc.Cinema.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("CinemaService");
    }
  }

  private static final class CinemaServiceFileDescriptorSupplier
      extends CinemaServiceBaseDescriptorSupplier {
    CinemaServiceFileDescriptorSupplier() {}
  }

  private static final class CinemaServiceMethodDescriptorSupplier
      extends CinemaServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    CinemaServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (CinemaServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new CinemaServiceFileDescriptorSupplier())
              .addMethod(getGetCinemasByIdsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
