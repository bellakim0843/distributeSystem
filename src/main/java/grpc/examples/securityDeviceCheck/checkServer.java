package grpc.examples.securityDeviceCheck;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.logging.Logger;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;

//import the grpc package for the server
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import grpc.examples.securityDeviceCheck.userGrpc.userImplBase;

public class checkServer extends userImplBase {
	
	static int port = 50056; //Create the static int port for JmDNS. My Laptop, JmDNs is not working.

	private static final Logger logger = Logger.getLogger(checkServer.class.getName());

	public static void main(String[] args) throws IOException, InterruptedException {
		//Using the instance of checkServer , Server is created.
		checkServer checkserver = new checkServer();
		
		try {
			// Create a JmDNS instance firstly.
			
			Server server = ServerBuilder.forPort(port) // using static port
					.addService(checkserver)
					.build() // Start to build the registration server
					.start(); // Start the server and running to connect with clients.
			
			System.out.println("Security devices checking Server is being started!");
			 // Register service through JmDNS
	        JmDNS jmdns = JmDNS.create();
	        //Declare serviceInfo with information.
	        ServiceInfo serviceInfo = ServiceInfo.create("_check._tcp.local.", "checkDevice", port,
					"checkserver");
	        jmdns.registerService(serviceInfo);
			System.out.println("Security devices checking Server a is being loaded!");

			logger.info("Employee Registration Server started, listening on " + port);
			Thread.sleep(20000);
			server.awaitTermination();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			
	
		}
	}
	
	//The first Rpc in the protofile. I implemeted Server side streaming method in device Check function.
	@Override
	public void deviceCheck(deviceRequest request, StreamObserver<deviceList> responseObserver) {
		System.out.println("Device check request is receivied!");

        //This String Array will be saved data.
		String[] deviceArray = request.getMassData().split(",");

		//Using for loop, we will send the server side message repeatedly.
		for (int i = 0; i < deviceArray.length; i++) {

			// This is a stream of deviceList(response) is sent out using the for loop.	
			responseObserver.onNext(deviceList.newBuilder().setVal(deviceArray[i]).build());
		}
		//After finish the loop, onCompleted message will be sent out.
		responseObserver.onCompleted();
	}
	
	
	//Second rpc in the protofile. I implemeted unary service as device list print out function.
	@Override
	public void printOut(printRequest request, StreamObserver<printResponse> responseObserver) {

		System.out.print("print request is well received..\n");

        //This String will be sent to the client side.
		String confirm = "The process is successfully Completed!";

		//Using printResponse streamObserver and set Confirm message as string .
		//It will be delieved to the client side one by one
		printResponse reply = printResponse.newBuilder().setComfirm(confirm).build();
		
		//Test the deadine of the server's delay
//	      try {
//			Thread.sleep(5000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		// Sending the reply for each request.
		responseObserver.onNext(reply);
		responseObserver.onCompleted();
	}

}
