package grpc.examples.securityDeviceCheck;

	//required java packages for the program. Depends on your logic.
	import java.util.ArrayList;
	import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
	import java.util.Random;
	import java.util.logging.Level;
	import java.util.logging.Logger;

	//required grpc package for the client side
	import io.grpc.ManagedChannel;
	import io.grpc.ManagedChannelBuilder;
	import io.grpc.Status;
	import io.grpc.StatusRuntimeException;
	import io.grpc.stub.StreamObserver;

	//Client need not to extend any other class (GRPC related code) here
	public class checkClient {
		// First we create a logger to show client side logs in the console. logger instance will be used to log different events at the client console.
			// This is optional. Could be used if needed.
		private static  Logger logger = Logger.getLogger(checkClient.class.getName());

		// Creating stubs for establishing the connection with server.
			// Blocking stub
		private static userGrpcServer.userBlockingStub blockingStub;
		// Asynch stub
		private static userGrpcServer.userStub asyncStub;
		
		// The main method will have the logic for client.
		public static void main(String[] args) throws Exception {
			// First a channel is being created to the server from client. Here, we provide the server name (localhost) and port (50057).
			// As it is a local demo of GRPC, we can have non-secured channel (usePlaintext).

			ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50071).usePlaintext().build();

			//stubs -- generate from proto
			blockingStub = userGrpcServer.newBlockingStub(channel);
			asyncStub = userGrpcServer.newStub(channel);
			
			// RPC call with Asynchronous stub
			//checkAsync();
			
			// RPC call with Blocking stub
			checkBlocking();
			
			// Closing the channel once message has been passed.		
			channel.shutdown();

		}


	// Server streaming in Async stub
		public static void checkAsync() {
			// First creating a request message. Here, the message contains a string in setVal
			deviceRequest request = deviceRequest.newBuilder().setMassData("Device No: 1\nDevice Type: Security Camera\nDevice Code: AAA2020\nDevice Location: Warehouse-Front,"
					+ "\nDevice No: 2\nDevice Type: Security Camera\nDevice Code: ACC2022\nDevice Location: Warehouse-Rear,"
					+ "\nDevice No: 3\nDevice Type: Security Camera\nDevice Code: ABB2017\nDevice Location: Main entrance,"
					+ "\nDevice No: 4\nDevice Type: Thermal Imaging Camera\nDevice Code: UPC2021\nDevice Location: Main entrance,"
					+ "\nDevice No: 5\nDevice Type: Finger-Print Scanner \nDevice Code: FII2020\nDevice Location: Sub entrance,"
					+ "\nDevice No: 6\nDevice Type: Security Camera\nDevice Code: RTT2018\nDevice Location: Lab, \nDevice No: 7\nDevice Type: Security Camera\nDevice Code: ABB2017\nDevice Location: Control tower").build();

			// Handling the stream from server using onNext (logic for handling each message in stream), onError, onCompleted (logic will be executed after the completion of stream)
			StreamObserver<deviceList> responseObserver = new StreamObserver<deviceList>() {

				int count =0 ;

				@Override
				public void onNext(deviceList value) {
					System.out.println("receiving messages " + value);
					count += 1;
				}

				@Override
				public void onError(Throwable t) {
					t.printStackTrace();

				}

				@Override
				public void onCompleted() {
					System.out.println("stream is completed ... received "+ count+ " messages");
				}

			};

			// Here, we are calling the Remote split method. On receiving the message from server the onNext, onError, onCompleted will be called (as defined above). 
			asyncStub.deviceCheck(request, responseObserver);


			try {
				Thread.sleep(30000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		}

		//blocking server-streaming
		public static void checkBlocking() {
			// First creating a request message. Here, the message contains a string in setVal
			deviceRequest request = deviceRequest.newBuilder().setMassData("Device No: 1\nDevice Type: Security Camera\nDevice Code: AAA2020\nDevice Location: Warehouse-Front,"
					+ "\nDevice No: 2\nDevice Type: Security Camera\nDevice Code: ACC2022\nDevice Location: Warehouse-Rear,"
					+ "\nDevice No: 3\nDevice Type: Security Camera\nDevice Code: ABB2017\nDevice Location: Main entrance,"
					+ "\nDevice No: 4\nDevice Type: Thermal Imaging Camera\nDevice Code: UPC2021\nDevice Location: Main entrance,"
					+ "\nDevice No: 5\nDevice Type: Finger-Print Scanner \nDevice Code: FII2020\nDevice Location: Sub entrance,"
					+ "\nDevice No: 6\nDevice Type: Security Camera\nDevice Code: RTT2018\nDevice Location: Lab, \nDevice No: 7\nDevice Type: Security Camera\nDevice Code: ABB2017\nDevice Location: Control tower").build();

			int count = 0;
			// as this call is blocking. The client will not proceed until all the messages in stream has been received. 
			try {
				// Iterating each message in response when calling remote split RPC method.
				Iterator<deviceList> responses = blockingStub.deviceCheck(request);
				
				// Client keeps a check on the next message in stream.
				while(responses.hasNext()) {
					deviceList temp = responses.next();
					System.out.println(temp.getVal());	
					count++;
				}
				System.out.println(count);

			} catch (StatusRuntimeException e) {
				e.printStackTrace();
			}
			
		}

	}

