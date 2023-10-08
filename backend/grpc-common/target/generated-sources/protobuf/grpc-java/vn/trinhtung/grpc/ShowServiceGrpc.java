package vn.trinhtung.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.42.1)",
    comments = "Source: show.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class ShowServiceGrpc {

  private ShowServiceGrpc() {}

  public static final String SERVICE_NAME = "vn.trinhtung.grpc.ShowService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<vn.trinhtung.grpc.GetShowByIdRequest,
      vn.trinhtung.grpc.GetShowByIdResponse> getGetShowByIdMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getShowById",
      requestType = vn.trinhtung.grpc.GetShowByIdRequest.class,
      responseType = vn.trinhtung.grpc.GetShowByIdResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<vn.trinhtung.grpc.GetShowByIdRequest,
      vn.trinhtung.grpc.GetShowByIdResponse> getGetShowByIdMethod() {
    io.grpc.MethodDescriptor<vn.trinhtung.grpc.GetShowByIdRequest, vn.trinhtung.grpc.GetShowByIdResponse> getGetShowByIdMethod;
    if ((getGetShowByIdMethod = ShowServiceGrpc.getGetShowByIdMethod) == null) {
      synchronized (ShowServiceGrpc.class) {
        if ((getGetShowByIdMethod = ShowServiceGrpc.getGetShowByIdMethod) == null) {
          ShowServiceGrpc.getGetShowByIdMethod = getGetShowByIdMethod =
              io.grpc.MethodDescriptor.<vn.trinhtung.grpc.GetShowByIdRequest, vn.trinhtung.grpc.GetShowByIdResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getShowById"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  vn.trinhtung.grpc.GetShowByIdRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  vn.trinhtung.grpc.GetShowByIdResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ShowServiceMethodDescriptorSupplier("getShowById"))
              .build();
        }
      }
    }
    return getGetShowByIdMethod;
  }

  private static volatile io.grpc.MethodDescriptor<vn.trinhtung.grpc.GetShowsByIdsRequest,
      vn.trinhtung.grpc.GetShowsByIdsResponse> getGetShowsByIdsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getShowsByIds",
      requestType = vn.trinhtung.grpc.GetShowsByIdsRequest.class,
      responseType = vn.trinhtung.grpc.GetShowsByIdsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<vn.trinhtung.grpc.GetShowsByIdsRequest,
      vn.trinhtung.grpc.GetShowsByIdsResponse> getGetShowsByIdsMethod() {
    io.grpc.MethodDescriptor<vn.trinhtung.grpc.GetShowsByIdsRequest, vn.trinhtung.grpc.GetShowsByIdsResponse> getGetShowsByIdsMethod;
    if ((getGetShowsByIdsMethod = ShowServiceGrpc.getGetShowsByIdsMethod) == null) {
      synchronized (ShowServiceGrpc.class) {
        if ((getGetShowsByIdsMethod = ShowServiceGrpc.getGetShowsByIdsMethod) == null) {
          ShowServiceGrpc.getGetShowsByIdsMethod = getGetShowsByIdsMethod =
              io.grpc.MethodDescriptor.<vn.trinhtung.grpc.GetShowsByIdsRequest, vn.trinhtung.grpc.GetShowsByIdsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getShowsByIds"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  vn.trinhtung.grpc.GetShowsByIdsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  vn.trinhtung.grpc.GetShowsByIdsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ShowServiceMethodDescriptorSupplier("getShowsByIds"))
              .build();
        }
      }
    }
    return getGetShowsByIdsMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ShowServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ShowServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ShowServiceStub>() {
        @java.lang.Override
        public ShowServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ShowServiceStub(channel, callOptions);
        }
      };
    return ShowServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ShowServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ShowServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ShowServiceBlockingStub>() {
        @java.lang.Override
        public ShowServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ShowServiceBlockingStub(channel, callOptions);
        }
      };
    return ShowServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ShowServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ShowServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ShowServiceFutureStub>() {
        @java.lang.Override
        public ShowServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ShowServiceFutureStub(channel, callOptions);
        }
      };
    return ShowServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class ShowServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void getShowById(vn.trinhtung.grpc.GetShowByIdRequest request,
        io.grpc.stub.StreamObserver<vn.trinhtung.grpc.GetShowByIdResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetShowByIdMethod(), responseObserver);
    }

    /**
     */
    public void getShowsByIds(vn.trinhtung.grpc.GetShowsByIdsRequest request,
        io.grpc.stub.StreamObserver<vn.trinhtung.grpc.GetShowsByIdsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetShowsByIdsMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetShowByIdMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                vn.trinhtung.grpc.GetShowByIdRequest,
                vn.trinhtung.grpc.GetShowByIdResponse>(
                  this, METHODID_GET_SHOW_BY_ID)))
          .addMethod(
            getGetShowsByIdsMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                vn.trinhtung.grpc.GetShowsByIdsRequest,
                vn.trinhtung.grpc.GetShowsByIdsResponse>(
                  this, METHODID_GET_SHOWS_BY_IDS)))
          .build();
    }
  }

  /**
   */
  public static final class ShowServiceStub extends io.grpc.stub.AbstractAsyncStub<ShowServiceStub> {
    private ShowServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ShowServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ShowServiceStub(channel, callOptions);
    }

    /**
     */
    public void getShowById(vn.trinhtung.grpc.GetShowByIdRequest request,
        io.grpc.stub.StreamObserver<vn.trinhtung.grpc.GetShowByIdResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetShowByIdMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getShowsByIds(vn.trinhtung.grpc.GetShowsByIdsRequest request,
        io.grpc.stub.StreamObserver<vn.trinhtung.grpc.GetShowsByIdsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetShowsByIdsMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class ShowServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<ShowServiceBlockingStub> {
    private ShowServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ShowServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ShowServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public vn.trinhtung.grpc.GetShowByIdResponse getShowById(vn.trinhtung.grpc.GetShowByIdRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetShowByIdMethod(), getCallOptions(), request);
    }

    /**
     */
    public vn.trinhtung.grpc.GetShowsByIdsResponse getShowsByIds(vn.trinhtung.grpc.GetShowsByIdsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetShowsByIdsMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class ShowServiceFutureStub extends io.grpc.stub.AbstractFutureStub<ShowServiceFutureStub> {
    private ShowServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ShowServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ShowServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<vn.trinhtung.grpc.GetShowByIdResponse> getShowById(
        vn.trinhtung.grpc.GetShowByIdRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetShowByIdMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<vn.trinhtung.grpc.GetShowsByIdsResponse> getShowsByIds(
        vn.trinhtung.grpc.GetShowsByIdsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetShowsByIdsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_SHOW_BY_ID = 0;
  private static final int METHODID_GET_SHOWS_BY_IDS = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final ShowServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(ShowServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_SHOW_BY_ID:
          serviceImpl.getShowById((vn.trinhtung.grpc.GetShowByIdRequest) request,
              (io.grpc.stub.StreamObserver<vn.trinhtung.grpc.GetShowByIdResponse>) responseObserver);
          break;
        case METHODID_GET_SHOWS_BY_IDS:
          serviceImpl.getShowsByIds((vn.trinhtung.grpc.GetShowsByIdsRequest) request,
              (io.grpc.stub.StreamObserver<vn.trinhtung.grpc.GetShowsByIdsResponse>) responseObserver);
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

  private static abstract class ShowServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ShowServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return vn.trinhtung.grpc.Show.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ShowService");
    }
  }

  private static final class ShowServiceFileDescriptorSupplier
      extends ShowServiceBaseDescriptorSupplier {
    ShowServiceFileDescriptorSupplier() {}
  }

  private static final class ShowServiceMethodDescriptorSupplier
      extends ShowServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    ShowServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (ShowServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ShowServiceFileDescriptorSupplier())
              .addMethod(getGetShowByIdMethod())
              .addMethod(getGetShowsByIdsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
