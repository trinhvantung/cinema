package vn.trinhtung.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.42.1)",
    comments = "Source: payment.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class PaymentServiceGrpc {

  private PaymentServiceGrpc() {}

  public static final String SERVICE_NAME = "vn.trinhtung.grpc.PaymentService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<vn.trinhtung.grpc.GetVnPayPaymentUrlRequest,
      vn.trinhtung.grpc.GetVnPayPaymentUrlResponse> getGetVnPayPaymentUrlMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getVnPayPaymentUrl",
      requestType = vn.trinhtung.grpc.GetVnPayPaymentUrlRequest.class,
      responseType = vn.trinhtung.grpc.GetVnPayPaymentUrlResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<vn.trinhtung.grpc.GetVnPayPaymentUrlRequest,
      vn.trinhtung.grpc.GetVnPayPaymentUrlResponse> getGetVnPayPaymentUrlMethod() {
    io.grpc.MethodDescriptor<vn.trinhtung.grpc.GetVnPayPaymentUrlRequest, vn.trinhtung.grpc.GetVnPayPaymentUrlResponse> getGetVnPayPaymentUrlMethod;
    if ((getGetVnPayPaymentUrlMethod = PaymentServiceGrpc.getGetVnPayPaymentUrlMethod) == null) {
      synchronized (PaymentServiceGrpc.class) {
        if ((getGetVnPayPaymentUrlMethod = PaymentServiceGrpc.getGetVnPayPaymentUrlMethod) == null) {
          PaymentServiceGrpc.getGetVnPayPaymentUrlMethod = getGetVnPayPaymentUrlMethod =
              io.grpc.MethodDescriptor.<vn.trinhtung.grpc.GetVnPayPaymentUrlRequest, vn.trinhtung.grpc.GetVnPayPaymentUrlResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getVnPayPaymentUrl"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  vn.trinhtung.grpc.GetVnPayPaymentUrlRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  vn.trinhtung.grpc.GetVnPayPaymentUrlResponse.getDefaultInstance()))
              .setSchemaDescriptor(new PaymentServiceMethodDescriptorSupplier("getVnPayPaymentUrl"))
              .build();
        }
      }
    }
    return getGetVnPayPaymentUrlMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static PaymentServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<PaymentServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<PaymentServiceStub>() {
        @java.lang.Override
        public PaymentServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new PaymentServiceStub(channel, callOptions);
        }
      };
    return PaymentServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static PaymentServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<PaymentServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<PaymentServiceBlockingStub>() {
        @java.lang.Override
        public PaymentServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new PaymentServiceBlockingStub(channel, callOptions);
        }
      };
    return PaymentServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static PaymentServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<PaymentServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<PaymentServiceFutureStub>() {
        @java.lang.Override
        public PaymentServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new PaymentServiceFutureStub(channel, callOptions);
        }
      };
    return PaymentServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class PaymentServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void getVnPayPaymentUrl(vn.trinhtung.grpc.GetVnPayPaymentUrlRequest request,
        io.grpc.stub.StreamObserver<vn.trinhtung.grpc.GetVnPayPaymentUrlResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetVnPayPaymentUrlMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetVnPayPaymentUrlMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                vn.trinhtung.grpc.GetVnPayPaymentUrlRequest,
                vn.trinhtung.grpc.GetVnPayPaymentUrlResponse>(
                  this, METHODID_GET_VN_PAY_PAYMENT_URL)))
          .build();
    }
  }

  /**
   */
  public static final class PaymentServiceStub extends io.grpc.stub.AbstractAsyncStub<PaymentServiceStub> {
    private PaymentServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PaymentServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new PaymentServiceStub(channel, callOptions);
    }

    /**
     */
    public void getVnPayPaymentUrl(vn.trinhtung.grpc.GetVnPayPaymentUrlRequest request,
        io.grpc.stub.StreamObserver<vn.trinhtung.grpc.GetVnPayPaymentUrlResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetVnPayPaymentUrlMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class PaymentServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<PaymentServiceBlockingStub> {
    private PaymentServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PaymentServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new PaymentServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public vn.trinhtung.grpc.GetVnPayPaymentUrlResponse getVnPayPaymentUrl(vn.trinhtung.grpc.GetVnPayPaymentUrlRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetVnPayPaymentUrlMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class PaymentServiceFutureStub extends io.grpc.stub.AbstractFutureStub<PaymentServiceFutureStub> {
    private PaymentServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PaymentServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new PaymentServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<vn.trinhtung.grpc.GetVnPayPaymentUrlResponse> getVnPayPaymentUrl(
        vn.trinhtung.grpc.GetVnPayPaymentUrlRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetVnPayPaymentUrlMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_VN_PAY_PAYMENT_URL = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final PaymentServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(PaymentServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_VN_PAY_PAYMENT_URL:
          serviceImpl.getVnPayPaymentUrl((vn.trinhtung.grpc.GetVnPayPaymentUrlRequest) request,
              (io.grpc.stub.StreamObserver<vn.trinhtung.grpc.GetVnPayPaymentUrlResponse>) responseObserver);
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

  private static abstract class PaymentServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    PaymentServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return vn.trinhtung.grpc.Payment.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("PaymentService");
    }
  }

  private static final class PaymentServiceFileDescriptorSupplier
      extends PaymentServiceBaseDescriptorSupplier {
    PaymentServiceFileDescriptorSupplier() {}
  }

  private static final class PaymentServiceMethodDescriptorSupplier
      extends PaymentServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    PaymentServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (PaymentServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new PaymentServiceFileDescriptorSupplier())
              .addMethod(getGetVnPayPaymentUrlMethod())
              .build();
        }
      }
    }
    return result;
  }
}
