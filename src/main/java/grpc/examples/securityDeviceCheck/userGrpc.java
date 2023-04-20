package grpc.examples.securityDeviceCheck;

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
 * <pre>
 * Interface exported by the server.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.15.0)",
    comments = "Source: securityDeviceCheck.proto")
public final class userGrpc {

  private userGrpc() {}

  public static final String SERVICE_NAME = "strings.user";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<grpc.examples.securityDeviceCheck.loginRequest,
      grpc.examples.securityDeviceCheck.loginResponse> getLoginMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "login",
      requestType = grpc.examples.securityDeviceCheck.loginRequest.class,
      responseType = grpc.examples.securityDeviceCheck.loginResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<grpc.examples.securityDeviceCheck.loginRequest,
      grpc.examples.securityDeviceCheck.loginResponse> getLoginMethod() {
    io.grpc.MethodDescriptor<grpc.examples.securityDeviceCheck.loginRequest, grpc.examples.securityDeviceCheck.loginResponse> getLoginMethod;
    if ((getLoginMethod = userGrpc.getLoginMethod) == null) {
      synchronized (userGrpc.class) {
        if ((getLoginMethod = userGrpc.getLoginMethod) == null) {
          userGrpc.getLoginMethod = getLoginMethod = 
              io.grpc.MethodDescriptor.<grpc.examples.securityDeviceCheck.loginRequest, grpc.examples.securityDeviceCheck.loginResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "strings.user", "login"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.examples.securityDeviceCheck.loginRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.examples.securityDeviceCheck.loginResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new userMethodDescriptorSupplier("login"))
                  .build();
          }
        }
     }
     return getLoginMethod;
  }

  private static volatile io.grpc.MethodDescriptor<grpc.examples.securityDeviceCheck.deviceRequest,
      grpc.examples.securityDeviceCheck.deviceList> getDeviceCheckMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "deviceCheck",
      requestType = grpc.examples.securityDeviceCheck.deviceRequest.class,
      responseType = grpc.examples.securityDeviceCheck.deviceList.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<grpc.examples.securityDeviceCheck.deviceRequest,
      grpc.examples.securityDeviceCheck.deviceList> getDeviceCheckMethod() {
    io.grpc.MethodDescriptor<grpc.examples.securityDeviceCheck.deviceRequest, grpc.examples.securityDeviceCheck.deviceList> getDeviceCheckMethod;
    if ((getDeviceCheckMethod = userGrpc.getDeviceCheckMethod) == null) {
      synchronized (userGrpc.class) {
        if ((getDeviceCheckMethod = userGrpc.getDeviceCheckMethod) == null) {
          userGrpc.getDeviceCheckMethod = getDeviceCheckMethod = 
              io.grpc.MethodDescriptor.<grpc.examples.securityDeviceCheck.deviceRequest, grpc.examples.securityDeviceCheck.deviceList>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "strings.user", "deviceCheck"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.examples.securityDeviceCheck.deviceRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.examples.securityDeviceCheck.deviceList.getDefaultInstance()))
                  .setSchemaDescriptor(new userMethodDescriptorSupplier("deviceCheck"))
                  .build();
          }
        }
     }
     return getDeviceCheckMethod;
  }

  private static volatile io.grpc.MethodDescriptor<grpc.examples.securityDeviceCheck.empty,
      grpc.examples.securityDeviceCheck.logoutResponse> getLogoutMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "logout",
      requestType = grpc.examples.securityDeviceCheck.empty.class,
      responseType = grpc.examples.securityDeviceCheck.logoutResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<grpc.examples.securityDeviceCheck.empty,
      grpc.examples.securityDeviceCheck.logoutResponse> getLogoutMethod() {
    io.grpc.MethodDescriptor<grpc.examples.securityDeviceCheck.empty, grpc.examples.securityDeviceCheck.logoutResponse> getLogoutMethod;
    if ((getLogoutMethod = userGrpc.getLogoutMethod) == null) {
      synchronized (userGrpc.class) {
        if ((getLogoutMethod = userGrpc.getLogoutMethod) == null) {
          userGrpc.getLogoutMethod = getLogoutMethod = 
              io.grpc.MethodDescriptor.<grpc.examples.securityDeviceCheck.empty, grpc.examples.securityDeviceCheck.logoutResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "strings.user", "logout"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.examples.securityDeviceCheck.empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.examples.securityDeviceCheck.logoutResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new userMethodDescriptorSupplier("logout"))
                  .build();
          }
        }
     }
     return getLogoutMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static userStub newStub(io.grpc.Channel channel) {
    return new userStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static userBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new userBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static userFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new userFutureStub(channel);
  }

  /**
   * <pre>
   * Interface exported by the server.
   * </pre>
   */
  public static abstract class userImplBase implements io.grpc.BindableService {

    /**
     */
    public void login(grpc.examples.securityDeviceCheck.loginRequest request,
        io.grpc.stub.StreamObserver<grpc.examples.securityDeviceCheck.loginResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getLoginMethod(), responseObserver);
    }

    /**
     */
    public void deviceCheck(grpc.examples.securityDeviceCheck.deviceRequest request,
        io.grpc.stub.StreamObserver<grpc.examples.securityDeviceCheck.deviceList> responseObserver) {
      asyncUnimplementedUnaryCall(getDeviceCheckMethod(), responseObserver);
    }

    /**
     */
    public void logout(grpc.examples.securityDeviceCheck.empty request,
        io.grpc.stub.StreamObserver<grpc.examples.securityDeviceCheck.logoutResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getLogoutMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getLoginMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                grpc.examples.securityDeviceCheck.loginRequest,
                grpc.examples.securityDeviceCheck.loginResponse>(
                  this, METHODID_LOGIN)))
          .addMethod(
            getDeviceCheckMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                grpc.examples.securityDeviceCheck.deviceRequest,
                grpc.examples.securityDeviceCheck.deviceList>(
                  this, METHODID_DEVICE_CHECK)))
          .addMethod(
            getLogoutMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                grpc.examples.securityDeviceCheck.empty,
                grpc.examples.securityDeviceCheck.logoutResponse>(
                  this, METHODID_LOGOUT)))
          .build();
    }
  }

  /**
   * <pre>
   * Interface exported by the server.
   * </pre>
   */
  public static final class userStub extends io.grpc.stub.AbstractStub<userStub> {
    private userStub(io.grpc.Channel channel) {
      super(channel);
    }

    private userStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected userStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new userStub(channel, callOptions);
    }

    /**
     */
    public void login(grpc.examples.securityDeviceCheck.loginRequest request,
        io.grpc.stub.StreamObserver<grpc.examples.securityDeviceCheck.loginResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getLoginMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void deviceCheck(grpc.examples.securityDeviceCheck.deviceRequest request,
        io.grpc.stub.StreamObserver<grpc.examples.securityDeviceCheck.deviceList> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getDeviceCheckMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void logout(grpc.examples.securityDeviceCheck.empty request,
        io.grpc.stub.StreamObserver<grpc.examples.securityDeviceCheck.logoutResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getLogoutMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Interface exported by the server.
   * </pre>
   */
  public static final class userBlockingStub extends io.grpc.stub.AbstractStub<userBlockingStub> {
    private userBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private userBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected userBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new userBlockingStub(channel, callOptions);
    }

    /**
     */
    public grpc.examples.securityDeviceCheck.loginResponse login(grpc.examples.securityDeviceCheck.loginRequest request) {
      return blockingUnaryCall(
          getChannel(), getLoginMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<grpc.examples.securityDeviceCheck.deviceList> deviceCheck(
        grpc.examples.securityDeviceCheck.deviceRequest request) {
      return blockingServerStreamingCall(
          getChannel(), getDeviceCheckMethod(), getCallOptions(), request);
    }

    /**
     */
    public grpc.examples.securityDeviceCheck.logoutResponse logout(grpc.examples.securityDeviceCheck.empty request) {
      return blockingUnaryCall(
          getChannel(), getLogoutMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Interface exported by the server.
   * </pre>
   */
  public static final class userFutureStub extends io.grpc.stub.AbstractStub<userFutureStub> {
    private userFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private userFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected userFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new userFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<grpc.examples.securityDeviceCheck.loginResponse> login(
        grpc.examples.securityDeviceCheck.loginRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getLoginMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<grpc.examples.securityDeviceCheck.logoutResponse> logout(
        grpc.examples.securityDeviceCheck.empty request) {
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
    private final userImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(userImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_LOGIN:
          serviceImpl.login((grpc.examples.securityDeviceCheck.loginRequest) request,
              (io.grpc.stub.StreamObserver<grpc.examples.securityDeviceCheck.loginResponse>) responseObserver);
          break;
        case METHODID_DEVICE_CHECK:
          serviceImpl.deviceCheck((grpc.examples.securityDeviceCheck.deviceRequest) request,
              (io.grpc.stub.StreamObserver<grpc.examples.securityDeviceCheck.deviceList>) responseObserver);
          break;
        case METHODID_LOGOUT:
          serviceImpl.logout((grpc.examples.securityDeviceCheck.empty) request,
              (io.grpc.stub.StreamObserver<grpc.examples.securityDeviceCheck.logoutResponse>) responseObserver);
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

  private static abstract class userBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    userBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return grpc.examples.securityDeviceCheck.SecurityDeviceCheckServiceImpl.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("user");
    }
  }

  private static final class userFileDescriptorSupplier
      extends userBaseDescriptorSupplier {
    userFileDescriptorSupplier() {}
  }

  private static final class userMethodDescriptorSupplier
      extends userBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    userMethodDescriptorSupplier(String methodName) {
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
      synchronized (userGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new userFileDescriptorSupplier())
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