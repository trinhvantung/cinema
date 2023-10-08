package vn.trinhtung.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.42.1)",
    comments = "Source: hall.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class HallServiceGrpc {

  private HallServiceGrpc() {}

  public static final String SERVICE_NAME = "vn.trinhtung.grpc.HallService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<vn.trinhtung.grpc.GetHallByIdRequest,
      vn.trinhtung.grpc.GetHallByIdResponse> getGetHallByIdMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getHallById",
      requestType = vn.trinhtung.grpc.GetHallByIdRequest.class,
      responseType = vn.trinhtung.grpc.GetHallByIdResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<vn.trinhtung.grpc.GetHallByIdRequest,
      vn.trinhtung.grpc.GetHallByIdResponse> getGetHallByIdMethod() {
    io.grpc.MethodDescriptor<vn.trinhtung.grpc.GetHallByIdRequest, vn.trinhtung.grpc.GetHallByIdResponse> getGetHallByIdMethod;
    if ((getGetHallByIdMethod = HallServiceGrpc.getGetHallByIdMethod) == null) {
      synchronized (HallServiceGrpc.class) {
        if ((getGetHallByIdMethod = HallServiceGrpc.getGetHallByIdMethod) == null) {
          HallServiceGrpc.getGetHallByIdMethod = getGetHallByIdMethod =
              io.grpc.MethodDescriptor.<vn.trinhtung.grpc.GetHallByIdRequest, vn.trinhtung.grpc.GetHallByIdResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getHallById"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  vn.trinhtung.grpc.GetHallByIdRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  vn.trinhtung.grpc.GetHallByIdResponse.getDefaultInstance()))
              .setSchemaDescriptor(new HallServiceMethodDescriptorSupplier("getHallById"))
              .build();
        }
      }
    }
    return getGetHallByIdMethod;
  }

  private static volatile io.grpc.MethodDescriptor<vn.trinhtung.grpc.GetHallsByIdsRequest,
      vn.trinhtung.grpc.GetHallsByIdsResponse> getGetHallsByIdsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getHallsByIds",
      requestType = vn.trinhtung.grpc.GetHallsByIdsRequest.class,
      responseType = vn.trinhtung.grpc.GetHallsByIdsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<vn.trinhtung.grpc.GetHallsByIdsRequest,
      vn.trinhtung.grpc.GetHallsByIdsResponse> getGetHallsByIdsMethod() {
    io.grpc.MethodDescriptor<vn.trinhtung.grpc.GetHallsByIdsRequest, vn.trinhtung.grpc.GetHallsByIdsResponse> getGetHallsByIdsMethod;
    if ((getGetHallsByIdsMethod = HallServiceGrpc.getGetHallsByIdsMethod) == null) {
      synchronized (HallServiceGrpc.class) {
        if ((getGetHallsByIdsMethod = HallServiceGrpc.getGetHallsByIdsMethod) == null) {
          HallServiceGrpc.getGetHallsByIdsMethod = getGetHallsByIdsMethod =
              io.grpc.MethodDescriptor.<vn.trinhtung.grpc.GetHallsByIdsRequest, vn.trinhtung.grpc.GetHallsByIdsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getHallsByIds"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  vn.trinhtung.grpc.GetHallsByIdsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  vn.trinhtung.grpc.GetHallsByIdsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new HallServiceMethodDescriptorSupplier("getHallsByIds"))
              .build();
        }
      }
    }
    return getGetHallsByIdsMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static HallServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<HallServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<HallServiceStub>() {
        @java.lang.Override
        public HallServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new HallServiceStub(channel, callOptions);
        }
      };
    return HallServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static HallServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<HallServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<HallServiceBlockingStub>() {
        @java.lang.Override
        public HallServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new HallServiceBlockingStub(channel, callOptions);
        }
      };
    return HallServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static HallServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<HallServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<HallServiceFutureStub>() {
        @java.lang.Override
        public HallServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new HallServiceFutureStub(channel, callOptions);
        }
      };
    return HallServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class HallServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void getHallById(vn.trinhtung.grpc.GetHallByIdRequest request,
        io.grpc.stub.StreamObserver<vn.trinhtung.grpc.GetHallByIdResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetHallByIdMethod(), responseObserver);
    }

    /**
     */
    public void getHallsByIds(vn.trinhtung.grpc.GetHallsByIdsRequest request,
        io.grpc.stub.StreamObserver<vn.trinhtung.grpc.GetHallsByIdsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetHallsByIdsMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetHallByIdMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                vn.trinhtung.grpc.GetHallByIdRequest,
                vn.trinhtung.grpc.GetHallByIdResponse>(
                  this, METHODID_GET_HALL_BY_ID)))
          .addMethod(
            getGetHallsByIdsMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                vn.trinhtung.grpc.GetHallsByIdsRequest,
                vn.trinhtung.grpc.GetHallsByIdsResponse>(
                  this, METHODID_GET_HALLS_BY_IDS)))
          .build();
    }
  }

  /**
   */
  public static final class HallServiceStub extends io.grpc.stub.AbstractAsyncStub<HallServiceStub> {
    private HallServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected HallServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new HallServiceStub(channel, callOptions);
    }

    /**
     */
    public void getHallById(vn.trinhtung.grpc.GetHallByIdRequest request,
        io.grpc.stub.StreamObserver<vn.trinhtung.grpc.GetHallByIdResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetHallByIdMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getHallsByIds(vn.trinhtung.grpc.GetHallsByIdsRequest request,
        io.grpc.stub.StreamObserver<vn.trinhtung.grpc.GetHallsByIdsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetHallsByIdsMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class HallServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<HallServiceBlockingStub> {
    private HallServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected HallServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new HallServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public vn.trinhtung.grpc.GetHallByIdResponse getHallById(vn.trinhtung.grpc.GetHallByIdRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetHallByIdMethod(), getCallOptions(), request);
    }

    /**
     */
    public vn.trinhtung.grpc.GetHallsByIdsResponse getHallsByIds(vn.trinhtung.grpc.GetHallsByIdsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetHallsByIdsMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class HallServiceFutureStub extends io.grpc.stub.AbstractFutureStub<HallServiceFutureStub> {
    private HallServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected HallServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new HallServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<vn.trinhtung.grpc.GetHallByIdResponse> getHallById(
        vn.trinhtung.grpc.GetHallByIdRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetHallByIdMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<vn.trinhtung.grpc.GetHallsByIdsResponse> getHallsByIds(
        vn.trinhtung.grpc.GetHallsByIdsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetHallsByIdsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_HALL_BY_ID = 0;
  private static final int METHODID_GET_HALLS_BY_IDS = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final HallServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(HallServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_HALL_BY_ID:
          serviceImpl.getHallById((vn.trinhtung.grpc.GetHallByIdRequest) request,
              (io.grpc.stub.StreamObserver<vn.trinhtung.grpc.GetHallByIdResponse>) responseObserver);
          break;
        case METHODID_GET_HALLS_BY_IDS:
          serviceImpl.getHallsByIds((vn.trinhtung.grpc.GetHallsByIdsRequest) request,
              (io.grpc.stub.StreamObserver<vn.trinhtung.grpc.GetHallsByIdsResponse>) responseObserver);
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

  private static abstract class HallServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    HallServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return vn.trinhtung.grpc.Hall.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("HallService");
    }
  }

  private static final class HallServiceFileDescriptorSupplier
      extends HallServiceBaseDescriptorSupplier {
    HallServiceFileDescriptorSupplier() {}
  }

  private static final class HallServiceMethodDescriptorSupplier
      extends HallServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    HallServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (HallServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new HallServiceFileDescriptorSupplier())
              .addMethod(getGetHallByIdMethod())
              .addMethod(getGetHallsByIdsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
