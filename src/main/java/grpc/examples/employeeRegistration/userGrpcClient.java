package grpc.examples.employeeRegistration;

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

import java.util.Iterator;

import grpc.examples.securityDeviceCheck.deviceList;
import grpc.examples.securityDeviceCheck.deviceRequest;
import io.grpc.stub.StreamObserver;

/**
 * <pre>
 * Interface exported by the server.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.15.0)",
    comments = "Source: employeeRegistration.proto")
public final class userGrpcClient {

  private userGrpcClient() {}

  public static final String SERVICE_NAME = "strings.user";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<grpc.examples.employeeRegistration.loginRequest,
      grpc.examples.employeeRegistration.loginResponse> getLoginMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "login",
      requestType = grpc.examples.employeeRegistration.loginRequest.class,
      responseType = grpc.examples.employeeRegistration.loginResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<grpc.examples.employeeRegistration.loginRequest,
      grpc.examples.employeeRegistration.loginResponse> getLoginMethod() {
    io.grpc.MethodDescriptor<grpc.examples.employeeRegistration.loginRequest, grpc.examples.employeeRegistration.loginResponse> getLoginMethod;
    if ((getLoginMethod = userGrpcClient.getLoginMethod) == null) {
      synchronized (userGrpcClient.class) {
        if ((getLoginMethod = userGrpcClient.getLoginMethod) == null) {
          userGrpcClient.getLoginMethod = getLoginMethod = 
              io.grpc.MethodDescriptor.<grpc.examples.employeeRegistration.loginRequest, grpc.examples.employeeRegistration.loginResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "strings.user", "login"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.examples.employeeRegistration.loginRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.examples.employeeRegistration.loginResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new userMethodDescriptorSupplier("login"))
                  .build();
          }
        }
     }
     return getLoginMethod;
  }

  private static volatile io.grpc.MethodDescriptor<grpc.examples.employeeRegistration.employee,
      grpc.examples.employeeRegistration.employeeList> getRegisterMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "register",
      requestType = grpc.examples.employeeRegistration.employee.class,
      responseType = grpc.examples.employeeRegistration.employeeList.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<grpc.examples.employeeRegistration.employee,
      grpc.examples.employeeRegistration.employeeList> getRegisterMethod() {
    io.grpc.MethodDescriptor<grpc.examples.employeeRegistration.employee, grpc.examples.employeeRegistration.employeeList> getRegisterMethod;
    if ((getRegisterMethod = userGrpcClient.getRegisterMethod) == null) {
      synchronized (userGrpcClient.class) {
        if ((getRegisterMethod = userGrpcClient.getRegisterMethod) == null) {
          userGrpcClient.getRegisterMethod = getRegisterMethod = 
              io.grpc.MethodDescriptor.<grpc.examples.employeeRegistration.employee, grpc.examples.employeeRegistration.employeeList>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "strings.user", "register"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.examples.employeeRegistration.employee.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.examples.employeeRegistration.employeeList.getDefaultInstance()))
                  .setSchemaDescriptor(new userMethodDescriptorSupplier("register"))
                  .build();
          }
        }
     }
     return getRegisterMethod;
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
    public void login(grpc.examples.employeeRegistration.loginRequest request,
        io.grpc.stub.StreamObserver<grpc.examples.employeeRegistration.loginResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getLoginMethod(), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<grpc.examples.employeeRegistration.employee> register(
        io.grpc.stub.StreamObserver<grpc.examples.employeeRegistration.employeeList> responseObserver) {
      return asyncUnimplementedStreamingCall(getRegisterMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getLoginMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                grpc.examples.employeeRegistration.loginRequest,
                grpc.examples.employeeRegistration.loginResponse>(
                  this, METHODID_LOGIN)))
          .addMethod(
            getRegisterMethod(),
            asyncClientStreamingCall(
              new MethodHandlers<
                grpc.examples.employeeRegistration.employee,
                grpc.examples.employeeRegistration.employeeList>(
                  this, METHODID_REGISTER)))
          .build();
    }

	public void register(grpc.examples.employeeRegistration.employee request, 
			io.grpc.stub.StreamObserver<grpc.examples.employeeRegistration.employeeList> responseObserver) {
		 asyncUnimplementedUnaryCall(getRegisterMethod(), responseObserver);
		// TODO Auto-generated method stub
		
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
    public void login(grpc.examples.employeeRegistration.loginRequest request,
        io.grpc.stub.StreamObserver<grpc.examples.employeeRegistration.loginResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getLoginMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<grpc.examples.employeeRegistration.employee> register(
        io.grpc.stub.StreamObserver<grpc.examples.employeeRegistration.employeeList> responseObserver) {
      return asyncClientStreamingCall(
          getChannel().newCall(getRegisterMethod(), getCallOptions()), responseObserver);
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
    public grpc.examples.employeeRegistration.loginResponse login(grpc.examples.employeeRegistration.loginRequest request) {
      return blockingUnaryCall(
          getChannel(), getLoginMethod(), getCallOptions(), request);
    }

	 public void deviceCheck(deviceRequest request, StreamObserver<deviceList> responseObserver) {
		 System.out.println("receiving split");
		 
		// Retrieve the value from the request of the client and convert it to array
		 String[] deviceArray = request.getMassData().split(",");
		 
		// LOGIC of THE METHOD 
			// NOTE: YOU MAY NEED TO MODIFY THIS LOGIC HERE.
		 System.out.println(deviceArray.length);
		 
			
			// Preparing and sending the reply for the client. Here, response is build and with the value (c) computed by above logic.
			 // Here, a stream of response is sent using the for loop. 
			 //고쳐야
		 for(int i =0; i<deviceArray.length; i++) {			
				// Preparing and sending the reply for the client. Here, response is build and with the value (c) computed by above logic.
				 // Here, a stream of response is sent using the for loop.
				 responseObserver.onNext(deviceList.newBuilder().setVal(deviceArray[i]).build());
			 }
		 
		 
		 responseObserver.onCompleted();
	}

	public Iterator<deviceList> deviceCheck(deviceRequest request, Iterator<deviceList> responses) {
		// TODO Auto-generated method stub
		return null;
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
    public com.google.common.util.concurrent.ListenableFuture<grpc.examples.employeeRegistration.loginResponse> login(
        grpc.examples.employeeRegistration.loginRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getLoginMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_LOGIN = 0;
  private static final int METHODID_REGISTER = 1;

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
          serviceImpl.login((grpc.examples.employeeRegistration.loginRequest) request,
              (io.grpc.stub.StreamObserver<grpc.examples.employeeRegistration.loginResponse>) responseObserver);
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
              (io.grpc.stub.StreamObserver<grpc.examples.employeeRegistration.employeeList>) responseObserver);
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
      return grpc.examples.employeeRegistration.EmployeeRegistrationServiceImpl.getDescriptor();
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
      synchronized (userGrpcClient.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new userFileDescriptorSupplier())
              .addMethod(getLoginMethod())
              .addMethod(getRegisterMethod())
              .build();
        }
      }
    }
    return result;
  }
}
