package grpc.examples.employeeRegistration;

//required java packages for the program. Depends on your logic.
import java.io.IOException;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JLabel;

//required grpc package for the server side
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;

//This is ImplBase class that was generated from the proto file.
//You need to change this location for your projects. NOTICE: The class is in StringsServiceGrpc.java -> StringsServiceImplBase class (this Base class is generated from proto file option java_outer_classname)
import grpc.examples.employeeRegistration.userGrpc.userImplBase;


//Extend the ImplBase imported class here. It is an Interface file with required rpc methods
public class RegistrationServer extends userImplBase {

//First we create a logger to show server side logs in the console. logger instance will be used to log different events at the server console.
	private static final Logger logger = Logger.getLogger(RegistrationServer.class.getName());

//Main method would contain the logic to start the server.	For throws keyword refer https://www.javatpoint.com/throw-keyword
//NOTE: THIS LOGIC WILL BE SAME FOR ALL THE TYPES OF SERVICES
	 public static void main(String[] args) throws IOException, InterruptedException {
		    
		 // The StringServer is the current file name/ class name. Using an instance of this class different methods could be invoked by the client.
		 RegistrationServer registrationserver = new RegistrationServer();

		 	// This is the port number where server will be listening to clients. Refer - https://en.wikipedia.org/wiki/Port_(computer_networking) 
		    int port = 50037;
		    
		    // Here, we create a server on the port defined in in variable "port" and attach a service "stringserver" (instance of the class) defined above.
		    Server server = ServerBuilder.forPort(port) // Port is defined in line 34
		        .addService(registrationserver) // Service is defined in line 31
		        .build() // Build the server
		        .start(); // Start the server and keep it running for clients to contact.
		    
		    // Giving a logging information on the server console that server has started
		    logger.info("Server started, listening on " + port);
		    		    
		    // Server will be running until externally terminated.
		    server.awaitTermination();
	 }
	
//These RPC methods have been defined in the proto files. The interface is already present in the ImplBase File.
public void logout(logoutResponse request, StreamObserver<empty> responseObserver) {
		System.out.println("Log-Out Processing!");
			
		//will generate an error CANCELLED: HTTP/2 error code: CANCEL
		empty reply = empty.newBuilder().build();
		
		// sending an empty response. No value is there.
		responseObserver.onNext(reply); 
		
//		StatusRuntimeException er = new StatusRuntimeException(Status.ABORTED);
//		responseObserver.onError(er);
	  
		responseObserver.onCompleted();
	}


	// This is the second RPC method defined in proto file. It accepts an argument and return one.
//	public void login(loginRequest request, StreamObserver<loginResponse> responseObserver) {
//		
//		System.out.print("receiving login message ");
//		
//		// Retrieve the value from the request of the client
//		StringBuilder stb = new StringBuilder("\n"+request.getEmpNo()+"\n"+request.getEmpName());
//		
//		// LOGIC of THE METHOD 
//		// NOTE: YOU MAY NEED TO MODIFY THIS LOGIC HERE.
//		String output = stb.toString();
//		
//		// Preparing the reply for the client. Here, response is build and with the value (output) computed by above logic.  
//		loginResponse reply = loginResponse.newBuilder().setResponseMessage(output).build();
//		
//		// Sending the reply for each request.
//		responseObserver.onNext(reply);
//		
//		responseObserver.onCompleted();
//	}
	
	// These RPC methods have been defined in the proto files. The interface is already present in the ImplBase File.
//	NOTE - YOU MAY NEED TO MODIFY THIS LOGIC FOR YOUR PROJECTS BASED ON TYPE OF THE RPC METHODS 
//For override Refer - https://docs.oracle.com/javase/8/docs/api/java/lang/Override.html	 


@Override
public StreamObserver<employee> register(StreamObserver<employeeList> responseObserver) {
	
	// Retrieve the value from the stream of requests of the client. 
	return new StreamObserver<employee>() {
	
      
		StringBuilder sb = new StringBuilder();

		
		// For each message in the stream, get one stream at a time.
		// NOTE: YOU MAY MODIFY THE LOGIC OF onNext, onError, onCompleted BASED ON YOUR PROJECT.
		@Override
		public void onNext(employee value) {	
			// Here, in this example we compute the value of string length for each message in the stream. 
			sb.append("\n============");
			sb.append("\n ID: "+value.getEmpNo()+"\n Name: "+value.getEmpName()+"\n Shift: "+value.getShift());
			
			System.out.println("receive -> " + value.getEmpNo());
		
		}

		@Override
		public void onError(Throwable t) {
			// TODO Auto-generated method stub
			
		}

		// Once the complete stream is received this logic will be executed.
		@Override
		public void onCompleted() {
			// Preparing and sending the reply for the client. Here, response is build and with the value (length) computed by above logic.
			 // Here, response is sent once the client is done with sending the stream.
			
			
			JFrame frame = new JFrame();
			
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setLayout(null);
			frame.setSize(500,500);
			frame.setVisible(true);
			frame.setResizable(false);
			
			  
			  employeeList res = employeeList.newBuilder().setVal(sb.toString()).build();		  
//
	          responseObserver.onNext(res);
	          
	          JLabel label = new JLabel();
	          label.setText(sb.toString());
	          frame.add(label);
	          
	          responseObserver.onCompleted();

	     

	        
		}


		
	};
}



}



