package ca.grpc;

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
    comments = "Source: service.proto")
public final class deviceCheckGrpc {

  private deviceCheckGrpc() {}

  public static final String SERVICE_NAME = "deviceCheck";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<ca.grpc.Service.loginRequest,
      ca.grpc.Service.loginResponse> getLoginMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "login",
      requestType = ca.grpc.Service.loginRequest.class,
      responseType = ca.grpc.Service.loginResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ca.grpc.Service.loginRequest,
      ca.grpc.Service.loginResponse> getLoginMethod() {
    io.grpc.MethodDescriptor<ca.grpc.Service.loginRequest, ca.grpc.Service.loginResponse> getLoginMethod;
    if ((getLoginMethod = deviceCheckGrpc.getLoginMethod) == null) {
      synchronized (deviceCheckGrpc.class) {
        if ((getLoginMethod = deviceCheckGrpc.getLoginMethod) == null) {
          deviceCheckGrpc.getLoginMethod = getLoginMethod = 
              io.grpc.MethodDescriptor.<ca.grpc.Service.loginRequest, ca.grpc.Service.loginResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "deviceCheck", "login"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ca.grpc.Service.loginRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ca.grpc.Service.loginResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new deviceCheckMethodDescriptorSupplier("login"))
                  .build();
          }
        }
     }
     return getLoginMethod;
  }

  private static volatile io.grpc.MethodDescriptor<ca.grpc.Service.deviceRequest,
      ca.grpc.Service.deviceList> getDeviceCheckMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "deviceCheck",
      requestType = ca.grpc.Service.deviceRequest.class,
      responseType = ca.grpc.Service.deviceList.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<ca.grpc.Service.deviceRequest,
      ca.grpc.Service.deviceList> getDeviceCheckMethod() {
    io.grpc.MethodDescriptor<ca.grpc.Service.deviceRequest, ca.grpc.Service.deviceList> getDeviceCheckMethod;
    if ((getDeviceCheckMethod = deviceCheckGrpc.getDeviceCheckMethod) == null) {
      synchronized (deviceCheckGrpc.class) {
        if ((getDeviceCheckMethod = deviceCheckGrpc.getDeviceCheckMethod) == null) {
          deviceCheckGrpc.getDeviceCheckMethod = getDeviceCheckMethod = 
              io.grpc.MethodDescriptor.<ca.grpc.Service.deviceRequest, ca.grpc.Service.deviceList>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "deviceCheck", "deviceCheck"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ca.grpc.Service.deviceRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ca.grpc.Service.deviceList.getDefaultInstance()))
                  .setSchemaDescriptor(new deviceCheckMethodDescriptorSupplier("deviceCheck"))
                  .build();
          }
        }
     }
     return getDeviceCheckMethod;
  }

  private static volatile io.grpc.MethodDescriptor<ca.grpc.Service.empty,
      ca.grpc.Service.logoutResponse> getLogoutMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "logout",
      requestType = ca.grpc.Service.empty.class,
      responseType = ca.grpc.Service.logoutResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ca.grpc.Service.empty,
      ca.grpc.Service.logoutResponse> getLogoutMethod() {
    io.grpc.MethodDescriptor<ca.grpc.Service.empty, ca.grpc.Service.logoutResponse> getLogoutMethod;
    if ((getLogoutMethod = deviceCheckGrpc.getLogoutMethod) == null) {
      synchronized (deviceCheckGrpc.class) {
        if ((getLogoutMethod = deviceCheckGrpc.getLogoutMethod) == null) {
          deviceCheckGrpc.getLogoutMethod = getLogoutMethod = 
              io.grpc.MethodDescriptor.<ca.grpc.Service.empty, ca.grpc.Service.logoutResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "deviceCheck", "logout"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ca.grpc.Service.empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ca.grpc.Service.logoutResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new deviceCheckMethodDescriptorSupplier("logout"))
                  .build();
          }
        }
     }
     return getLogoutMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static deviceCheckStub newStub(io.grpc.Channel channel) {
    return new deviceCheckStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static deviceCheckBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new deviceCheckBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static deviceCheckFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new deviceCheckFutureStub(channel);
  }

  /**
   */
  public static abstract class deviceCheckImplBase implements io.grpc.BindableService {

    /**
     */
    public void login(ca.grpc.Service.loginRequest request,
        io.grpc.stub.StreamObserver<ca.grpc.Service.loginResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getLoginMethod(), responseObserver);
    }

    /**
     */
    public void deviceCheck(ca.grpc.Service.deviceRequest request,
        io.grpc.stub.StreamObserver<ca.grpc.Service.deviceList> responseObserver) {
      asyncUnimplementedUnaryCall(getDeviceCheckMethod(), responseObserver);
    }

    /**
     */
    public void logout(ca.grpc.Service.empty request,
        io.grpc.stub.StreamObserver<ca.grpc.Service.logoutResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getLogoutMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getLoginMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                ca.grpc.Service.loginRequest,
                ca.grpc.Service.loginResponse>(
                  this, METHODID_LOGIN)))
          .addMethod(
            getDeviceCheckMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                ca.grpc.Service.deviceRequest,
                ca.grpc.Service.deviceList>(
                  this, METHODID_DEVICE_CHECK)))
          .addMethod(
            getLogoutMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                ca.grpc.Service.empty,
                ca.grpc.Service.logoutResponse>(
                  this, METHODID_LOGOUT)))
          .build();
    }
  }

  /**
   */
  public static final class deviceCheckStub extends io.grpc.stub.AbstractStub<deviceCheckStub> {
    private deviceCheckStub(io.grpc.Channel channel) {
      super(channel);
    }

    private deviceCheckStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected deviceCheckStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new deviceCheckStub(channel, callOptions);
    }

    /**
     */
    public void login(ca.grpc.Service.loginRequest request,
        io.grpc.stub.StreamObserver<ca.grpc.Service.loginResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getLoginMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void deviceCheck(ca.grpc.Service.deviceRequest request,
        io.grpc.stub.StreamObserver<ca.grpc.Service.deviceList> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getDeviceCheckMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void logout(ca.grpc.Service.empty request,
        io.grpc.stub.StreamObserver<ca.grpc.Service.logoutResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getLogoutMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class deviceCheckBlockingStub extends io.grpc.stub.AbstractStub<deviceCheckBlockingStub> {
    private deviceCheckBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private deviceCheckBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected deviceCheckBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new deviceCheckBlockingStub(channel, callOptions);
    }

    /**
     */
    public ca.grpc.Service.loginResponse login(ca.grpc.Service.loginRequest request) {
      return blockingUnaryCall(
          getChannel(), getLoginMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<ca.grpc.Service.deviceList> deviceCheck(
        ca.grpc.Service.deviceRequest request) {
      return blockingServerStreamingCall(
          getChannel(), getDeviceCheckMethod(), getCallOptions(), request);
    }

    /**
     */
    public ca.grpc.Service.logoutResponse logout(ca.grpc.Service.empty request) {
      return blockingUnaryCall(
          getChannel(), getLogoutMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class deviceCheckFutureStub extends io.grpc.stub.AbstractStub<deviceCheckFutureStub> {
    private deviceCheckFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private deviceCheckFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected deviceCheckFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new deviceCheckFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<ca.grpc.Service.loginResponse> login(
        ca.grpc.Service.loginRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getLoginMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<ca.grpc.Service.logoutResponse> logout(
        ca.grpc.Service.empty request) {
      return futureUnaryCall(
          getChannel().newCall(getLogoutMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_LOGIN = 0;
  private static final int METHODID_DEVICE_CHECK = 1;
  private static final int METHODID_LOGOUT = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final deviceCheckImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(deviceCheckImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_LOGIN:
          serviceImpl.login((ca.grpc.Service.loginRequest) request,
              (io.grpc.stub.StreamObserver<ca.grpc.Service.loginResponse>) responseObserver);
          break;
        case METHODID_DEVICE_CHECK:
          serviceImpl.deviceCheck((ca.grpc.Service.deviceRequest) request,
              (io.grpc.stub.StreamObserver<ca.grpc.Service.deviceList>) responseObserver);
          break;
        case METHODID_LOGOUT:
          serviceImpl.logout((ca.grpc.Service.empty) request,
              (io.grpc.stub.StreamObserver<ca.grpc.Service.logoutResponse>) responseObserver);
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

  private static abstract class deviceCheckBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    deviceCheckBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return ca.grpc.Service.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("deviceCheck");
    }
  }

  private static final class deviceCheckFileDescriptorSupplier
      extends deviceCheckBaseDescriptorSupplier {
    deviceCheckFileDescriptorSupplier() {}
  }

  private static final class deviceCheckMethodDescriptorSupplier
      extends deviceCheckBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    deviceCheckMethodDescriptorSupplier(String methodName) {
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
      synchronized (deviceCheckGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new deviceCheckFileDescriptorSupplier())
              .addMethod(getLoginMethod())
              .addMethod(getDeviceCheckMethod())
              .addMethod(getLogoutMethod())
              .build();
        }
      }
    }
    return result;
  }
}
