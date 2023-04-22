package grpc.examples.realtimeChat;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.logging.Logger;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;
import javax.swing.JOptionPane;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import grpc.examples.realtimeChat.chatGrpc.chatImplBase;

public class chatServer extends chatImplBase {

	private static final int port = 60011;
	private static final Logger logger = Logger.getLogger(chatServer.class.getName());

	public static void main(String[] args) throws IOException, InterruptedException {

		chatServer chatserver = new chatServer();

		try {

			Server server = ServerBuilder.forPort(port).addService(chatserver).build().start();

			System.out.println("Realtime chat application Server is being started!");
			// Create the JmDNS
			JmDNS jmdns = JmDNS.create();
			// Declare serviceInfo with information.
			ServiceInfo serviceInfo = ServiceInfo.create("_chat._tcp.local.", "realtimeChat", port, "chatServer");
			jmdns.registerService(serviceInfo);
			System.out.println("Realtime chat application Server is being processed!");

			logger.info("Realtime chat application Server started, listening on " + port);
			Thread.sleep(20000);
			server.awaitTermination();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {

		}

	}

	// The first Rpc in the protofile. I implemeted bidirectional service as
	// realtime chat function.
	/*
	 * realtimeChat method returns a StreamObserver object for handling
	 * bidirectional streaming communication between a server and a client in a gRPC
	 * service.
	 */

	@Override
	public StreamObserver<ServerSideChat> realtimeChat(StreamObserver<ClientSideChat> responseObserver) {
		/*
		 * a StreamObserver<ClientSideChat> object named responseObserver, which will be
		 * used to send responses back to the client.
		 */
		return new StreamObserver<ServerSideChat>() {

			/*
			 * this method sets up a bidirectional communication stream between a server and
			 * a client, where the server receives messages from the client, processes them,
			 * and sends responses back to the client.
			 */

			@Override
			public void onNext(ServerSideChat request) {
				/*
				 * n the onNext method, the server receives a ServerSideChat object containing a
				 * message from the client
				 */

				String serverChat = JOptionPane.showInputDialog(request.getMessage());

				ClientSideChat reply = ClientSideChat.newBuilder().setMessage("Server: " + serverChat + "\n").build();

				responseObserver.onNext(reply);

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

	// The second Rpc in the protofile. I implemeted unary service as file upload
	// function.
	@Override
	public void fileUpload(upload request, StreamObserver<uploadSuccess> responseObserver) {
		/**/
		System.out.println("File Upload Message: " + request.getUploadRequest());

		uploadSuccess reply = uploadSuccess.newBuilder().setUploadConfirm("File added successfully!").build();

		/*
		 * the server sends the message to the client using the responseObserver object
		 * by calling responseObserver.onNext(reply). Finally, the server signals that
		 * it has completed processing the request using responseObserver.onCompleted().
		 */

		responseObserver.onNext(reply);
		responseObserver.onCompleted();

	}

}
