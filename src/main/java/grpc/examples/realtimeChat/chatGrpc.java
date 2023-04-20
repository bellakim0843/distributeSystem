package grpc.examples.realtimeChat;

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
    comments = "Source: realtimeChat.proto")
public final class chatGrpc {

  private chatGrpc() {}

  public static final String SERVICE_NAME = "strings.chat";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<grpc.examples.realtimeChat.ServerSideChat,
      grpc.examples.realtimeChat.ClientSideChat> getRealtimeChatMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "realtimeChat",
      requestType = grpc.examples.realtimeChat.ServerSideChat.class,
      responseType = grpc.examples.realtimeChat.ClientSideChat.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<grpc.examples.realtimeChat.ServerSideChat,
      grpc.examples.realtimeChat.ClientSideChat> getRealtimeChatMethod() {
    io.grpc.MethodDescriptor<grpc.examples.realtimeChat.ServerSideChat, grpc.examples.realtimeChat.ClientSideChat> getRealtimeChatMethod;
    if ((getRealtimeChatMethod = chatGrpc.getRealtimeChatMethod) == null) {
      synchronized (chatGrpc.class) {
        if ((getRealtimeChatMethod = chatGrpc.getRealtimeChatMethod) == null) {
          chatGrpc.getRealtimeChatMethod = getRealtimeChatMethod = 
              io.grpc.MethodDescriptor.<grpc.examples.realtimeChat.ServerSideChat, grpc.examples.realtimeChat.ClientSideChat>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "strings.chat", "realtimeChat"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.examples.realtimeChat.ServerSideChat.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.examples.realtimeChat.ClientSideChat.getDefaultInstance()))
                  .setSchemaDescriptor(new chatMethodDescriptorSupplier("realtimeChat"))
                  .build();
          }
        }
     }
     return getRealtimeChatMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static chatStub newStub(io.grpc.Channel channel) {
    return new chatStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static chatBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new chatBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static chatFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new chatFutureStub(channel);
  }

  /**
   * <pre>
   * Interface exported by the server.
   * </pre>
   */
  public static abstract class chatImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * rpc method for bidirectional streaming calls
     * </pre>
     */
    public io.grpc.stub.StreamObserver<grpc.examples.realtimeChat.ServerSideChat> realtimeChat(
        io.grpc.stub.StreamObserver<grpc.examples.realtimeChat.ClientSideChat> responseObserver) {
      return asyncUnimplementedStreamingCall(getRealtimeChatMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getRealtimeChatMethod(),
            asyncBidiStreamingCall(
              new MethodHandlers<
                grpc.examples.realtimeChat.ServerSideChat,
                grpc.examples.realtimeChat.ClientSideChat>(
                  this, METHODID_REALTIME_CHAT)))
          .build();
    }
  }

  /**
   * <pre>
   * Interface exported by the server.
   * </pre>
   */
  public static final class chatStub extends io.grpc.stub.AbstractStub<chatStub> {
    private chatStub(io.grpc.Channel channel) {
      super(channel);
    }

    private chatStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected chatStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new chatStub(channel, callOptions);
    }

    /**
     * <pre>
     * rpc method for bidirectional streaming calls
     * </pre>
     */
    public io.grpc.stub.StreamObserver<grpc.examples.realtimeChat.ServerSideChat> realtimeChat(
        io.grpc.stub.StreamObserver<grpc.examples.realtimeChat.ClientSideChat> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(getRealtimeChatMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   * <pre>
   * Interface exported by the server.
   * </pre>
   */
  public static final class chatBlockingStub extends io.grpc.stub.AbstractStub<chatBlockingStub> {
    private chatBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private chatBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected chatBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new chatBlockingStub(channel, callOptions);
    }
  }

  /**
   * <pre>
   * Interface exported by the server.
   * </pre>
   */
  public static final class chatFutureStub extends io.grpc.stub.AbstractStub<chatFutureStub> {
    private chatFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private chatFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected chatFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new chatFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_REALTIME_CHAT = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final chatImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(chatImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_REALTIME_CHAT:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.realtimeChat(
              (io.grpc.stub.StreamObserver<grpc.examples.realtimeChat.ClientSideChat>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class chatBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    chatBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return grpc.examples.realtimeChat.realtimeChatServiceImpl.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("chat");
    }
  }

  private static final class chatFileDescriptorSupplier
      extends chatBaseDescriptorSupplier {
    chatFileDescriptorSupplier() {}
  }

  private static final class chatMethodDescriptorSupplier
      extends chatBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    chatMethodDescriptorSupplier(String methodName) {
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
      synchronized (chatGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new chatFileDescriptorSupplier())
              .addMethod(getRealtimeChatMethod())
              .build();
        }
      }
    }
    return result;
  }
}
