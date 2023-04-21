package grpc.examples.realtimeChat;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Logger;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;
import javax.swing.JOptionPane;

//required grpc package for the server side
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

//This is ImplBase class that was generated from the proto file.
//You need to change this location for your projects. NOTICE: The class is in StringsServiceGrpc.java -> StringsServiceImplBase class (this Base class is generated from proto file option java_outer_classname)
import grpc.examples.realtimeChat.chatGrpc.chatImplBase;

//Extend the ImplBase imported class here. It is an Interface file with required rpc methods
public class chatServer extends chatImplBase {

	// First we create a logger to show server side logs in the console. logger
	// instance will be used to log different events at the server console.
	private static final Logger logger = Logger.getLogger(chatServer.class.getName());
	private static final int port = 60011;

	// Main method would contain the logic to start the server. For throws keyword
	// refer https://www.javatpoint.com/throw-keyword
	// NOTE: THIS LOGIC WILL BE SAME FOR ALL THE TYPES OF SERVICES
	public static void main(String[] args) throws IOException, InterruptedException {

		// The StringServer is the current file name/ class name. Using an instance of
		// this class different methods could be invoked by the client.
		chatServer stringserver = new chatServer();

		chatJMDNS();

		// This is the port number where server will be listening to clients. Refer -
		// https://en.wikipedia.org/wiki/Port_(computer_networking)

		// Here, we create a server on the port defined in in variable "port" and attach
		// a service "stringserver" (instance of the class) defined above.
		Server server = ServerBuilder.forPort(port) // Port is defined in line 34
				.addService(stringserver) // Service is defined in line 31
				.build() // Build the server
				.start(); // Start the server and keep it running for clients to contact.

		// Giving a logging information on the server console that server has started
		logger.info("Server started, listening on " + port);

		// Server will be running until externally terminated.
		server.awaitTermination();
	}

	// These RPC methods have been defined in the proto files. The interface is
	// already present in the ImplBase File.
//		NOTE - YOU MAY NEED TO MODIFY THIS LOGIC FOR YOUR PROJECTS BASED ON TYPE OF THE RPC METHODS 
	// For override Refer -
	// https://docs.oracle.com/javase/8/docs/api/java/lang/Override.html

	@Override
	public StreamObserver<ServerSideChat> realtimeChat(StreamObserver<ClientSideChat> responseObserver) {

		return new StreamObserver<ServerSideChat>() {

			// For each message in the stream, get one stream at a time.
			// NOTE: YOU MAY MODIFY THE LOGIC OF onNext, onError, onCompleted BASED ON YOUR
			// PROJECT.
			@Override
			public void onNext(ServerSideChat request) {
				// In bidirectional stream, both server and client would be sending the stream
				// of messages.
				// Here, for each message in stream from client, server is sending back one
				// response.

				String serverChat = JOptionPane.showInputDialog(request.getMessage());

				ClientSideChat reply = ClientSideChat.newBuilder().setMessage("Server: " + serverChat + "\n").build();

				responseObserver.onNext(reply);

				// Preparing and sending the reply for the client. Here, response is build and
				// with the value (input1.toString()) computed by above logic.

			}

			@Override
			public void onError(Throwable t) {
				// TODO Auto-generated method stubal

			}

			@Override
			public void onCompleted() {

				responseObserver.onCompleted();

			}

		};
	}

	@Override
	public void fileUpload(upload request, StreamObserver<uploadSuccess> responseObserver) {
		System.out.println("File Upload Message: " + request.getUploadRequest());

		uploadSuccess reply = uploadSuccess.newBuilder().setUploadConfirm("File added successfully!").build();

		/* unary 소통 안 */

		responseObserver.onNext(reply);
		responseObserver.onCompleted();

	}

	public static void chatJMDNS() {
		try {
			// Create a JmDNS instance
			JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());

			// Register a service
			ServiceInfo serviceInfo = ServiceInfo.create("_chat._tcp.local.", "realtime-chat", port,
					"realtime chat service");
			System.out.println("Realtime-chat Server is being started!");
			jmdns.registerService(serviceInfo);
			System.out.println("Realtime-chat Server a is being loaded!");
			// Wait a bit
			Thread.sleep(20000);

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
