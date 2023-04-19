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
public final class registrationGrpc {

  private registrationGrpc() {}

  public static final String SERVICE_NAME = "registration";

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
    if ((getLoginMethod = registrationGrpc.getLoginMethod) == null) {
      synchronized (registrationGrpc.class) {
        if ((getLoginMethod = registrationGrpc.getLoginMethod) == null) {
          registrationGrpc.getLoginMethod = getLoginMethod = 
              io.grpc.MethodDescriptor.<ca.grpc.Service.loginRequest, ca.grpc.Service.loginResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "registration", "login"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ca.grpc.Service.loginRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ca.grpc.Service.loginResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new registrationMethodDescriptorSupplier("login"))
                  .build();
          }
        }
     }
     return getLoginMethod;
  }

  private static volatile io.grpc.MethodDescriptor<ca.grpc.Service.employee,
      ca.grpc.Service.employeeList> getRegisterMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "register",
      requestType = ca.grpc.Service.employee.class,
      responseType = ca.grpc.Service.employeeList.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<ca.grpc.Service.employee,
      ca.grpc.Service.employeeList> getRegisterMethod() {
    io.grpc.MethodDescriptor<ca.grpc.Service.employee, ca.grpc.Service.employeeList> getRegisterMethod;
    if ((getRegisterMethod = registrationGrpc.getRegisterMethod) == null) {
      synchronized (registrationGrpc.class) {
        if ((getRegisterMethod = registrationGrpc.getRegisterMethod) == null) {
          registrationGrpc.getRegisterMethod = getRegisterMethod = 
              io.grpc.MethodDescriptor.<ca.grpc.Service.employee, ca.grpc.Service.employeeList>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "registration", "register"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ca.grpc.Service.employee.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ca.grpc.Service.employeeList.getDefaultInstance()))
                  .setSchemaDescriptor(new registrationMethodDescriptorSupplier("register"))
                  .build();
          }
        }
     }
     return getRegisterMethod;
  }

  private static volatile io.grpc.MethodDescriptor<ca.grpc.Service.logoutResponse,
      ca.grpc.Service.empty> getLogoutMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "logout",
      requestType = ca.grpc.Service.logoutResponse.class,
      responseType = ca.grpc.Service.empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ca.grpc.Service.logoutResponse,
      ca.grpc.Service.empty> getLogoutMethod() {
    io.grpc.MethodDescriptor<ca.grpc.Service.logoutResponse, ca.grpc.Service.empty> getLogoutMethod;
    if ((getLogoutMethod = registrationGrpc.getLogoutMethod) == null) {
      synchronized (registrationGrpc.class) {
        if ((getLogoutMethod = registrationGrpc.getLogoutMethod) == null) {
          registrationGrpc.getLogoutMethod = getLogoutMethod = 
              io.grpc.MethodDescriptor.<ca.grpc.Service.logoutResponse, ca.grpc.Service.empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "registration", "logout"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ca.grpc.Service.logoutResponse.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ca.grpc.Service.empty.getDefaultInstance()))
                  .setSchemaDescriptor(new registrationMethodDescriptorSupplier("logout"))
                  .build();
          }
        }
     }
     return getLogoutMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static registrationStub newStub(io.grpc.Channel channel) {
    return new registrationStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static registrationBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new registrationBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static registrationFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new registrationFutureStub(channel);
  }

  /**
   */
  public static abstract class registrationImplBase implements io.grpc.BindableService {

    /**
     */
    public void login(ca.grpc.Service.loginRequest request,
        io.grpc.stub.StreamObserver<ca.grpc.Service.loginResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getLoginMethod(), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<ca.grpc.Service.employee> register(
        io.grpc.stub.StreamObserver<ca.grpc.Service.employeeList> responseObserver) {
      return asyncUnimplementedStreamingCall(getRegisterMethod(), responseObserver);
    }

    /**
     */
    public void logout(ca.grpc.Service.logoutResponse request,
        io.grpc.stub.StreamObserver<ca.grpc.Service.empty> responseObserver) {
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
            getRegisterMethod(),
            asyncClientStreamingCall(
              new MethodHandlers<
                ca.grpc.Service.employee,
                ca.grpc.Service.employeeList>(
                  this, METHODID_REGISTER)))
          .addMethod(
            getLogoutMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                ca.grpc.Service.logoutResponse,
                ca.grpc.Service.empty>(
                  this, METHODID_LOGOUT)))
          .build();
    }
  }

  /**
   */
  public static final class registrationStub extends io.grpc.stub.AbstractStub<registrationStub> {
    private registrationStub(io.grpc.Channel channel) {
      super(channel);
    }

    private registrationStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected registrationStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new registrationStub(channel, callOptions);
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
    public io.grpc.stub.StreamObserver<ca.grpc.Service.employee> register(
        io.grpc.stub.StreamObserver<ca.grpc.Service.employeeList> responseObserver) {
      return asyncClientStreamingCall(
          getChannel().newCall(getRegisterMethod(), getCallOptions()), responseObserver);
    }

    /**
     */
    public void logout(ca.grpc.Service.logoutResponse request,
        io.grpc.stub.StreamObserver<ca.grpc.Service.empty> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getLogoutMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class registrationBlockingStub extends io.grpc.stub.AbstractStub<registrationBlockingStub> {
    private registrationBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private registrationBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected registrationBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new registrationBlockingStub(channel, callOptions);
    }

    /**
     */
    public ca.grpc.Service.loginResponse login(ca.grpc.Service.loginRequest request) {
      return blockingUnaryCall(
          getChannel(), getLoginMethod(), getCallOptions(), request);
    }

    /**
     */
    public ca.grpc.Service.empty logout(ca.grpc.Service.logoutResponse request) {
      return blockingUnaryCall(
          getChannel(), getLogoutMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class registrationFutureStub extends io.grpc.stub.AbstractStub<registrationFutureStub> {
    private registrationFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private registrationFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected registrationFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new registrationFutureStub(channel, callOptions);
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
    public com.google.common.util.concurrent.ListenableFuture<ca.grpc.Service.empty> logout(
        ca.grpc.Service.logoutResponse request) {
      return futureUnaryCall(
          getChannel().newCall(getLogoutMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_LOGIN = 0;
  private static final int METHODID_LOGOUT = 1;
  private static final int METHODID_REGISTER = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final registrationImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(registrationImplBase serviceImpl, int methodId) {
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
        case METHODID_LOGOUT:
          serviceImpl.logout((ca.grpc.Service.logoutResponse) request,
              (io.grpc.stub.StreamObserver<ca.grpc.Service.empty>) responseObserver);
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
        case METHODID_REGISTER:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.register(
              (io.grpc.stub.StreamObserver<ca.grpc.Service.employeeList>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class registrationBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    registrationBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return ca.grpc.Service.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("registration");
    }
  }

  private static final class registrationFileDescriptorSupplier
      extends registrationBaseDescriptorSupplier {
    registrationFileDescriptorSupplier() {}
  }

  private static final class registrationMethodDescriptorSupplier
      extends registrationBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    registrationMethodDescriptorSupplier(String methodName) {
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
      synchronized (registrationGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new registrationFileDescriptorSupplier())
              .addMethod(getLoginMethod())
              .addMethod(getRegisterMethod())
              .addMethod(getLogoutMethod())
              .build();
        }
      }
    }
    return result;
  }
}
