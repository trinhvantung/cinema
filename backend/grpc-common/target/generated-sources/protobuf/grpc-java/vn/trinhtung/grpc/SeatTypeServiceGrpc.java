package vn.trinhtung.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.42.1)",
    comments = "Source: seat_type.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class SeatTypeServiceGrpc {

  private SeatTypeServiceGrpc() {}

  public static final String SERVICE_NAME = "vn.trinhtung.grpc.SeatTypeService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<vn.trinhtung.grpc.GetSeaTypesByHallRequest,
      vn.trinhtung.grpc.GetSeaTypesByHallResponse> getGetSeatTypesByHallMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getSeatTypesByHall",
      requestType = vn.trinhtung.grpc.GetSeaTypesByHallRequest.class,
      responseType = vn.trinhtung.grpc.GetSeaTypesByHallResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<vn.trinhtung.grpc.GetSeaTypesByHallRequest,
      vn.trinhtung.grpc.GetSeaTypesByHallResponse> getGetSeatTypesByHallMethod() {
    io.grpc.MethodDescriptor<vn.trinhtung.grpc.GetSeaTypesByHallRequest, vn.trinhtung.grpc.GetSeaTypesByHallResponse> getGetSeatTypesByHallMethod;
    if ((getGetSeatTypesByHallMethod = SeatTypeServiceGrpc.getGetSeatTypesByHallMethod) == null) {
      synchronized (SeatTypeServiceGrpc.class) {
        if ((getGetSeatTypesByHallMethod = SeatTypeServiceGrpc.getGetSeatTypesByHallMethod) == null) {
          SeatTypeServiceGrpc.getGetSeatTypesByHallMethod = getGetSeatTypesByHallMethod =
              io.grpc.MethodDescriptor.<vn.trinhtung.grpc.GetSeaTypesByHallRequest, vn.trinhtung.grpc.GetSeaTypesByHallResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getSeatTypesByHall"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  vn.trinhtung.grpc.GetSeaTypesByHallRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  vn.trinhtung.grpc.GetSeaTypesByHallResponse.getDefaultInstance()))
              .setSchemaDescriptor(new SeatTypeServiceMethodDescriptorSupplier("getSeatTypesByHall"))
              .build();
        }
      }
    }
    return getGetSeatTypesByHallMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static SeatTypeServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SeatTypeServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SeatTypeServiceStub>() {
        @java.lang.Override
        public SeatTypeServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SeatTypeServiceStub(channel, callOptions);
        }
      };
    return SeatTypeServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static SeatTypeServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SeatTypeServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SeatTypeServiceBlockingStub>() {
        @java.lang.Override
        public SeatTypeServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SeatTypeServiceBlockingStub(channel, callOptions);
        }
      };
    return SeatTypeServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static SeatTypeServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SeatTypeServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SeatTypeServiceFutureStub>() {
        @java.lang.Override
        public SeatTypeServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SeatTypeServiceFutureStub(channel, callOptions);
        }
      };
    return SeatTypeServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class SeatTypeServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void getSeatTypesByHall(vn.trinhtung.grpc.GetSeaTypesByHallRequest request,
        io.grpc.stub.StreamObserver<vn.trinhtung.grpc.GetSeaTypesByHallResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetSeatTypesByHallMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetSeatTypesByHallMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                vn.trinhtung.grpc.GetSeaTypesByHallRequest,
                vn.trinhtung.grpc.GetSeaTypesByHallResponse>(
                  this, METHODID_GET_SEAT_TYPES_BY_HALL)))
          .build();
    }
  }

  /**
   */
  public static final class SeatTypeServiceStub extends io.grpc.stub.AbstractAsyncStub<SeatTypeServiceStub> {
    private SeatTypeServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SeatTypeServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SeatTypeServiceStub(channel, callOptions);
    }

    /**
     */
    public void getSeatTypesByHall(vn.trinhtung.grpc.GetSeaTypesByHallRequest request,
        io.grpc.stub.StreamObserver<vn.trinhtung.grpc.GetSeaTypesByHallResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetSeatTypesByHallMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class SeatTypeServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<SeatTypeServiceBlockingStub> {
    private SeatTypeServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SeatTypeServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SeatTypeServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public vn.trinhtung.grpc.GetSeaTypesByHallResponse getSeatTypesByHall(vn.trinhtung.grpc.GetSeaTypesByHallRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetSeatTypesByHallMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class SeatTypeServiceFutureStub extends io.grpc.stub.AbstractFutureStub<SeatTypeServiceFutureStub> {
    private SeatTypeServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SeatTypeServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SeatTypeServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<vn.trinhtung.grpc.GetSeaTypesByHallResponse> getSeatTypesByHall(
        vn.trinhtung.grpc.GetSeaTypesByHallRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetSeatTypesByHallMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_SEAT_TYPES_BY_HALL = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final SeatTypeServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(SeatTypeServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_SEAT_TYPES_BY_HALL:
          serviceImpl.getSeatTypesByHall((vn.trinhtung.grpc.GetSeaTypesByHallRequest) request,
              (io.grpc.stub.StreamObserver<vn.trinhtung.grpc.GetSeaTypesByHallResponse>) responseObserver);
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

  private static abstract class SeatTypeServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    SeatTypeServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return vn.trinhtung.grpc.SeatType.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("SeatTypeService");
    }
  }

  private static final class SeatTypeServiceFileDescriptorSupplier
      extends SeatTypeServiceBaseDescriptorSupplier {
    SeatTypeServiceFileDescriptorSupplier() {}
  }

  private static final class SeatTypeServiceMethodDescriptorSupplier
      extends SeatTypeServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    SeatTypeServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (SeatTypeServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new SeatTypeServiceFileDescriptorSupplier())
              .addMethod(getGetSeatTypesByHallMethod())
              .build();
        }
      }
    }
    return result;
  }
}
