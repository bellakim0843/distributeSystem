package grpc.examples.employeeRegistration;

//required java packages for the program. Depends on your logic.
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Logger;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;
import javax.swing.JFrame;
import javax.swing.JLabel;

//required grpc package for the server side
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;
import grpc.examples.employeeRegistration.userGrpc.userBlockingStub;

//This is ImplBase class that was generated from the proto file.
//You need to change this location for your projects. NOTICE: The class is in StringsServiceGrpc.java -> StringsServiceImplBase class (this Base class is generated from proto file option java_outer_classname)
import grpc.examples.employeeRegistration.userGrpcClient.userImplBase;
import grpc.examples.employeeRegistration.RegistrationClient;

//Extend the ImplBase imported class here. It is an Interface file with required rpc methods
public class RegistrationServer extends userImplBase {

//First we create a logger to show server side logs in the console. logger instance will be used to log different events at the server console.
	private static final Logger logger = Logger.getLogger(RegistrationServer.class.getName());
	private static final int port = 50050;

//Main method would contain the logic to start the server.	For throws keyword refer https://www.javatpoint.com/throw-keyword
//NOTE: THIS LOGIC WILL BE SAME FOR ALL THE TYPES OF SERVICES
	public static void main(String[] args) throws IOException, InterruptedException {

		// The StringServer is the current file name/ class name. Using an instance of
		// this class different methods could be invoked by the client.
		RegistrationServer registrationserver = new RegistrationServer();

		// This is the port number where server will be listening to clients. Refer -
		// https://en.wikipedia.org/wiki/Port_(computer_networking)
		testJMDNS();

		// Here, we create a server on the port defined in in variable "port" and attach
		// a service "stringserver" (instance of the class) defined above.
		Server server = ServerBuilder.forPort(port) // Port is defined in line 34
				.addService(registrationserver) // Service is defined in line 31
				.build() // Build the server
				.start(); // Start the server and keep it running for clients to contact.

		// Giving a logging information on the server console that server has started
		logger.info("Employee Registration Server started, listening on " + port);

		// Server will be running until externally terminated.
		server.awaitTermination();
	}

	// This is the second RPC method defined in proto file. It accepts an argument
	// and return one.
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

	// These RPC methods have been defined in the proto files. The interface is
	// already present in the ImplBase File.
//	NOTE - YOU MAY NEED TO MODIFY THIS LOGIC FOR YOUR PROJECTS BASED ON TYPE OF THE RPC METHODS 
//For override Refer - https://docs.oracle.com/javase/8/docs/api/java/lang/Override.html

	@Override
	public StreamObserver<employee> register(StreamObserver<employeeList> responseObserver) {

		// Retrieve the value from the stream of requests of the client.
		return new StreamObserver<employee>() {

			StringBuilder sb = new StringBuilder();

			// For each message in the stream, get one stream at a time.
			// NOTE: YOU MAY MODIFY THE LOGIC OF onNext, onError, onCompleted BASED ON YOUR
			// PROJECT.
			@Override
			public void onNext(employee value) {
				// Here, in this example we compute the value of string length for each message
				// in the stream.
				sb.append("\n============");
				sb.append("\n ID: " + value.getEmpNo() + "\n Name: " + value.getEmpName() + "\n Shift: "
						+ value.getShift());

				System.out.println("receive -> " + value.getEmpNo());

			}

			@Override
			public void onError(Throwable t) {
				// TODO Auto-generated method stub

			}

			// Once the complete stream is received this logic will be executed.
			@Override
			public void onCompleted() {
				// Preparing and sending the reply for the client. Here, response is build and
				// with the value (length) computed by above logic.
				// Here, response is sent once the client is done with sending the stream.

				employeeList res = employeeList.newBuilder().setVal(sb.toString()).build();

				responseObserver.onNext(res);

				responseObserver.onCompleted();

			}

		};
	}

	@Override
	public void login(loginRequest request, StreamObserver<loginResponse> responseObserver) {
		// TODO Auto-generated method stub
		System.out.println("Work-shift registration program is opened Successfully!");

		// Logic Code
		String myTest = "Welcome! " + request.getEmpName() + " Empno: " + request.getEmpNo();

		// Logic

		loginResponse reply = loginResponse.newBuilder().setConfirm(myTest).build();

		responseObserver.onNext(reply);
		responseObserver.onCompleted();

	}

	public static void testJMDNS() {
		try {
			// Create a JmDNS instance
			JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());

			// Register a service
			ServiceInfo serviceInfo = ServiceInfo.create("_grpc._tcp.local.", "employeeRegistration", port,
					"Employee registration service");
			System.out.println("Service Registration Server is being started!");
			jmdns.registerService(serviceInfo);
			System.out.println("This Service is being processed!");
			// Wait a bit
			Thread.sleep(20000);

			// Unregister all services
			// jmdns.unregisterAllServices();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
