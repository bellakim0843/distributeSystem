package grpc.examples.securityDeviceCheck;


//required java packages for the program. Depends on your logic.
import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Logger;

//required grpc package for the server side
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;

//This is ImplBase class that was generated from the proto file.
//You need to change this location for your projects. NOTICE: The class is in StringsServiceGrpc.java -> StringsServiceImplBase class (this Base class is generated from proto file option java_outer_classname)
import grpc.examples.securityDeviceCheck.userGrpcServer.userImplBase;

//Extend the ImplBase imported class here. It is an Interface file with required rpc methods
public class checkServer extends userImplBase {

	// First we create a logger to show server side logs in the console. logger instance will be used to log different events at the server console.
	private static final Logger logger = Logger.getLogger(checkServer.class.getName());

	// Main method would contain the logic to start the server.	For throws keyword refer https://www.javatpoint.com/throw-keyword
	// NOTE: THIS LOGIC WILL BE SAME FOR ALL THE TYPES OF SERVICES
	 public static void main(String[] args) throws IOException, InterruptedException {
		
		 // The StringServer is the current file name/ class name. Using an instance of this class different methods could be invoked by the client.
		 	checkServer stringserver = new checkServer();
		   
		 // This is the port number where server will be listening to clients. Refer - https://en.wikipedia.org/wiki/Port_(computer_networking)	
		    int port = 50073;
		    
		 // Here, we create a server on the port defined in in variable "port" and attach a service "stringserver" (instance of the class) defined above.
		    Server server = ServerBuilder.forPort(port) // Port is defined in line 34
		        .addService(stringserver) // Service is defined in line 31
		        .build() // Build the server
		        .start(); // Start the server and keep it running for clients to contact.
		    
		    // Giving a logging information on the server console that server has started
		    logger.info("Server-streaming Server is started, listening on " + port);
		    		    
		    // Server will be running until externally terminated.
		    server.awaitTermination();
	 }

	// These RPC methods have been defined in the proto files. The interface is already present in the ImplBase File.
//		NOTE - YOU MAY NEED TO MODIFY THIS LOGIC FOR YOUR PROJECTS BASED ON TYPE OF THE RPC METHODS 
	// For override Refer - https://docs.oracle.com/javase/8/docs/api/java/lang/Override.html	 

	 @Override
	 public void deviceCheck(deviceRequest request, StreamObserver<deviceList> responseObserver) {
		 System.out.println("receiving split");
		 
		// Retrieve the value from the request of the client and convert it to array
		 String[] deviceArray = request.getMassData().split(",");
		 
		// LOGIC of THE METHOD 
			// NOTE: YOU MAY NEED TO MODIFY THIS LOGIC HERE.
		 
		 
		 for(int i =0; i<deviceArray.length; i++) {	
			// Preparing and sending the reply for the client. Here, response is build and with the value (c) computed by above logic.
			 // Here, a stream of response is sent using the for loop. 
			 //고쳐야
			 responseObserver.onNext(deviceList.newBuilder().setVal(deviceArray[i]).build());
		 }
		 
		 responseObserver.onCompleted();
	}
		
}

