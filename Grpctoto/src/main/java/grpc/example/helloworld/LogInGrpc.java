package grpc.example.helloworld;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.15.0)",
    comments = "Source: HelloWorld.proto")
public final class LogInGrpc {

  private LogInGrpc() {}

  public static final String SERVICE_NAME = "extrahelloworld.LogIn";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<grpc.example.helloworld.LoginRequest,
      grpc.example.helloworld.LoginReply> getInputInfoMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "InputInfo",
      requestType = grpc.example.helloworld.LoginRequest.class,
      responseType = grpc.example.helloworld.LoginReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<grpc.example.helloworld.LoginRequest,
      grpc.example.helloworld.LoginReply> getInputInfoMethod() {
    io.grpc.MethodDescriptor<grpc.example.helloworld.LoginRequest, grpc.example.helloworld.LoginReply> getInputInfoMethod;
    if ((getInputInfoMethod = LogInGrpc.getInputInfoMethod) == null) {
      synchronized (LogInGrpc.class) {
        if ((getInputInfoMethod = LogInGrpc.getInputInfoMethod) == null) {
          LogInGrpc.getInputInfoMethod = getInputInfoMethod = 
              io.grpc.MethodDescriptor.<grpc.example.helloworld.LoginRequest, grpc.example.helloworld.LoginReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "extrahelloworld.LogIn", "InputInfo"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.example.helloworld.LoginRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.example.helloworld.LoginReply.getDefaultInstance()))
                  .setSchemaDescriptor(new LogInMethodDescriptorSupplier("InputInfo"))
                  .build();
          }
        }
     }
     return getInputInfoMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static LogInStub newStub(io.grpc.Channel channel) {
    return new LogInStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static LogInBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new LogInBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static LogInFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new LogInFutureStub(channel);
  }

  /**
   */
  public static abstract class LogInImplBase implements io.grpc.BindableService {

    /**
     */
    public void inputInfo(grpc.example.helloworld.LoginRequest request,
        io.grpc.stub.StreamObserver<grpc.example.helloworld.LoginReply> responseObserver) {
      asyncUnimplementedUnaryCall(getInputInfoMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getInputInfoMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                grpc.example.helloworld.LoginRequest,
                grpc.example.helloworld.LoginReply>(
                  this, METHODID_INPUT_INFO)))
          .build();
    }
  }

  /**
   */
  public static final class LogInStub extends io.grpc.stub.AbstractStub<LogInStub> {
    private LogInStub(io.grpc.Channel channel) {
      super(channel);
    }

    private LogInStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected LogInStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new LogInStub(channel, callOptions);
    }

    /**
     */
    public void inputInfo(grpc.example.helloworld.LoginRequest request,
        io.grpc.stub.StreamObserver<grpc.example.helloworld.LoginReply> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getInputInfoMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class LogInBlockingStub extends io.grpc.stub.AbstractStub<LogInBlockingStub> {
    private LogInBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private LogInBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected LogInBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new LogInBlockingStub(channel, callOptions);
    }

    /**
     */
    public grpc.example.helloworld.LoginReply inputInfo(grpc.example.helloworld.LoginRequest request) {
      return blockingUnaryCall(
          getChannel(), getInputInfoMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class LogInFutureStub extends io.grpc.stub.AbstractStub<LogInFutureStub> {
    private LogInFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private LogInFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected LogInFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new LogInFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<grpc.example.helloworld.LoginReply> inputInfo(
        grpc.example.helloworld.LoginRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getInputInfoMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_INPUT_INFO = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final LogInImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(LogInImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_INPUT_INFO:
          serviceImpl.inputInfo((grpc.example.helloworld.LoginRequest) request,
              (io.grpc.stub.StreamObserver<grpc.example.helloworld.LoginReply>) responseObserver);
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

  private static abstract class LogInBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    LogInBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return grpc.example.helloworld.DemoHelloWorldServiceImpl.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("LogIn");
    }
  }

  private static final class LogInFileDescriptorSupplier
      extends LogInBaseDescriptorSupplier {
    LogInFileDescriptorSupplier() {}
  }

  private static final class LogInMethodDescriptorSupplier
      extends LogInBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    LogInMethodDescriptorSupplier(String methodName) {
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
      synchronized (LogInGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new LogInFileDescriptorSupplier())
              .addMethod(getInputInfoMethod())
              .build();
        }
      }
    }
    return result;
  }
}
