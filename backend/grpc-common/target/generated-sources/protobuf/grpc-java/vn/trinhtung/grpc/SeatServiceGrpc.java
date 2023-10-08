package vn.trinhtung.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.42.1)",
    comments = "Source: seat.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class SeatServiceGrpc {

  private SeatServiceGrpc() {}

  public static final String SERVICE_NAME = "vn.trinhtung.grpc.SeatService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<vn.trinhtung.grpc.GetSeatsByHallRequest,
      vn.trinhtung.grpc.GetSeatsByHallResponse> getGetSeatsByHallMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getSeatsByHall",
      requestType = vn.trinhtung.grpc.GetSeatsByHallRequest.class,
      responseType = vn.trinhtung.grpc.GetSeatsByHallResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<vn.trinhtung.grpc.GetSeatsByHallRequest,
      vn.trinhtung.grpc.GetSeatsByHallResponse> getGetSeatsByHallMethod() {
    io.grpc.MethodDescriptor<vn.trinhtung.grpc.GetSeatsByHallRequest, vn.trinhtung.grpc.GetSeatsByHallResponse> getGetSeatsByHallMethod;
    if ((getGetSeatsByHallMethod = SeatServiceGrpc.getGetSeatsByHallMethod) == null) {
      synchronized (SeatServiceGrpc.class) {
        if ((getGetSeatsByHallMethod = SeatServiceGrpc.getGetSeatsByHallMethod) == null) {
          SeatServiceGrpc.getGetSeatsByHallMethod = getGetSeatsByHallMethod =
              io.grpc.MethodDescriptor.<vn.trinhtung.grpc.GetSeatsByHallRequest, vn.trinhtung.grpc.GetSeatsByHallResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getSeatsByHall"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  vn.trinhtung.grpc.GetSeatsByHallRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  vn.trinhtung.grpc.GetSeatsByHallResponse.getDefaultInstance()))
              .setSchemaDescriptor(new SeatServiceMethodDescriptorSupplier("getSeatsByHall"))
              .build();
        }
      }
    }
    return getGetSeatsByHallMethod;
  }

  private static volatile io.grpc.MethodDescriptor<vn.trinhtung.grpc.GetSeatsByIdsRequest,
      vn.trinhtung.grpc.GetSeatsByIdsResponse> getGetSeatsByIdsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getSeatsByIds",
      requestType = vn.trinhtung.grpc.GetSeatsByIdsRequest.class,
      responseType = vn.trinhtung.grpc.GetSeatsByIdsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<vn.trinhtung.grpc.GetSeatsByIdsRequest,
      vn.trinhtung.grpc.GetSeatsByIdsResponse> getGetSeatsByIdsMethod() {
    io.grpc.MethodDescriptor<vn.trinhtung.grpc.GetSeatsByIdsRequest, vn.trinhtung.grpc.GetSeatsByIdsResponse> getGetSeatsByIdsMethod;
    if ((getGetSeatsByIdsMethod = SeatServiceGrpc.getGetSeatsByIdsMethod) == null) {
      synchronized (SeatServiceGrpc.class) {
        if ((getGetSeatsByIdsMethod = SeatServiceGrpc.getGetSeatsByIdsMethod) == null) {
          SeatServiceGrpc.getGetSeatsByIdsMethod = getGetSeatsByIdsMethod =
              io.grpc.MethodDescriptor.<vn.trinhtung.grpc.GetSeatsByIdsRequest, vn.trinhtung.grpc.GetSeatsByIdsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getSeatsByIds"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  vn.trinhtung.grpc.GetSeatsByIdsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  vn.trinhtung.grpc.GetSeatsByIdsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new SeatServiceMethodDescriptorSupplier("getSeatsByIds"))
              .build();
        }
      }
    }
    return getGetSeatsByIdsMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static SeatServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SeatServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SeatServiceStub>() {
        @java.lang.Override
        public SeatServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SeatServiceStub(channel, callOptions);
        }
      };
    return SeatServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static SeatServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SeatServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SeatServiceBlockingStub>() {
        @java.lang.Override
        public SeatServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SeatServiceBlockingStub(channel, callOptions);
        }
      };
    return SeatServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static SeatServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SeatServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SeatServiceFutureStub>() {
        @java.lang.Override
        public SeatServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SeatServiceFutureStub(channel, callOptions);
        }
      };
    return SeatServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class SeatServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void getSeatsByHall(vn.trinhtung.grpc.GetSeatsByHallRequest request,
        io.grpc.stub.StreamObserver<vn.trinhtung.grpc.GetSeatsByHallResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetSeatsByHallMethod(), responseObserver);
    }

    /**
     */
    public void getSeatsByIds(vn.trinhtung.grpc.GetSeatsByIdsRequest request,
        io.grpc.stub.StreamObserver<vn.trinhtung.grpc.GetSeatsByIdsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetSeatsByIdsMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetSeatsByHallMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                vn.trinhtung.grpc.GetSeatsByHallRequest,
                vn.trinhtung.grpc.GetSeatsByHallResponse>(
                  this, METHODID_GET_SEATS_BY_HALL)))
          .addMethod(
            getGetSeatsByIdsMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                vn.trinhtung.grpc.GetSeatsByIdsRequest,
                vn.trinhtung.grpc.GetSeatsByIdsResponse>(
                  this, METHODID_GET_SEATS_BY_IDS)))
          .build();
    }
  }

  /**
   */
  public static final class SeatServiceStub extends io.grpc.stub.AbstractAsyncStub<SeatServiceStub> {
    private SeatServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SeatServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SeatServiceStub(channel, callOptions);
    }

    /**
     */
    public void getSeatsByHall(vn.trinhtung.grpc.GetSeatsByHallRequest request,
        io.grpc.stub.StreamObserver<vn.trinhtung.grpc.GetSeatsByHallResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetSeatsByHallMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getSeatsByIds(vn.trinhtung.grpc.GetSeatsByIdsRequest request,
        io.grpc.stub.StreamObserver<vn.trinhtung.grpc.GetSeatsByIdsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetSeatsByIdsMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class SeatServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<SeatServiceBlockingStub> {
    private SeatServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SeatServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SeatServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public vn.trinhtung.grpc.GetSeatsByHallResponse getSeatsByHall(vn.trinhtung.grpc.GetSeatsByHallRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetSeatsByHallMethod(), getCallOptions(), request);
    }

    /**
     */
    public vn.trinhtung.grpc.GetSeatsByIdsResponse getSeatsByIds(vn.trinhtung.grpc.GetSeatsByIdsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetSeatsByIdsMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class SeatServiceFutureStub extends io.grpc.stub.AbstractFutureStub<SeatServiceFutureStub> {
    private SeatServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SeatServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SeatServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<vn.trinhtung.grpc.GetSeatsByHallResponse> getSeatsByHall(
        vn.trinhtung.grpc.GetSeatsByHallRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetSeatsByHallMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<vn.trinhtung.grpc.GetSeatsByIdsResponse> getSeatsByIds(
        vn.trinhtung.grpc.GetSeatsByIdsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetSeatsByIdsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_SEATS_BY_HALL = 0;
  private static final int METHODID_GET_SEATS_BY_IDS = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final SeatServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(SeatServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_SEATS_BY_HALL:
          serviceImpl.getSeatsByHall((vn.trinhtung.grpc.GetSeatsByHallRequest) request,
              (io.grpc.stub.StreamObserver<vn.trinhtung.grpc.GetSeatsByHallResponse>) responseObserver);
          break;
        case METHODID_GET_SEATS_BY_IDS:
          serviceImpl.getSeatsByIds((vn.trinhtung.grpc.GetSeatsByIdsRequest) request,
              (io.grpc.stub.StreamObserver<vn.trinhtung.grpc.GetSeatsByIdsResponse>) responseObserver);
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

  private static abstract class SeatServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    SeatServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return vn.trinhtung.grpc.Seat.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("SeatService");
    }
  }

  private static final class SeatServiceFileDescriptorSupplier
      extends SeatServiceBaseDescriptorSupplier {
    SeatServiceFileDescriptorSupplier() {}
  }

  private static final class SeatServiceMethodDescriptorSupplier
      extends SeatServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    SeatServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (SeatServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new SeatServiceFileDescriptorSupplier())
              .addMethod(getGetSeatsByHallMethod())
              .addMethod(getGetSeatsByIdsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
