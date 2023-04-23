package grpc.examples.employeeRegistration;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.logging.Logger;

/*Import JmDNS for implement. But In my MAC laptop, there were some errors. So I set Localhost and static port number*/
import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;

/*Grpc Package for serverside*/
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

/*Import userImplBase */
import grpc.examples.employeeRegistration.userGrpcClient.userImplBase;

public class RegistrationServer extends userImplBase {

//Due to JmDNS error, set static port number.
	private static final int port = 50050;
//Logger will be shown registration server's class name.
	private static final Logger logger = Logger.getLogger(RegistrationServer.class.getName());

	
	public static void main(String[] args) throws IOException, InterruptedException {

		//Registration Server is created.
		RegistrationServer registrationserver = new RegistrationServer();

		try {
			// Create a JmDNS instance firstly.
			
			Server server = ServerBuilder.forPort(port) // using static port
					.addService(registrationserver)
					.build() // Start to build the registration server
					.start(); // Start the server and running to connect with clients.
			
			System.out.println("Service Registration Server is being started!");
			 // Register service through JmDNS
	        JmDNS jmdns = JmDNS.create();
	        //Declare serviceInfo with information.
	        ServiceInfo serviceInfo = ServiceInfo.create("_registration._tcp.local.", "employeeRegistration", port, "registrationServer");
	        jmdns.registerService(serviceInfo);
			System.out.println("This Service is being processed!");

			logger.info("Employee Registration Server started, listening on " + port);
			Thread.sleep(20000);
			server.awaitTermination();
		}catch (UnknownHostException e) {
	        System.err.println("Unknown host: " + e.getMessage());
	    } catch (IOException e) {
	        System.err.println("Error detected in the server: " + e.getMessage());
	    } catch (Exception e) {
	        System.err.println("Error detected: " + e.getMessage());
	        e.printStackTrace();
	    }


	}
	
	
	//The first Rpc in the protofile. I implemeted unary service as login function.
	@Override
	public void login(loginRequest request, StreamObserver<loginResponse> responseObserver) {
		// TODO Auto-generated method stub
		System.out.println("Work-shift registration program is opened Successfully!");

        //This String will be sent to the client side.
		String myTest = "Welcome! " + request.getEmpName() + ", Please Sign-in the Application!";

		//Using loginResponse streamObserver and set Confirm message as string myTest.
		//It will be delievered to the client side one by one
		loginResponse reply = loginResponse.newBuilder().setConfirm(myTest).build();

		responseObserver.onNext(reply);
		responseObserver.onCompleted();

	}


	//Second rpc in the protofile. I implemeted Client streaming service as employee registration function.
	@Override
	public StreamObserver<employee> register(StreamObserver<employeeList> responseObserver) {

		//browse the value from the stream of requests of the client. 
		return new StreamObserver<employee>() {
			//To merge the employee's information, I decided to use Stringbuilder.
			StringBuilder sb = new StringBuilder();
			// For each message in the stream, get one stream at a time.

			@Override
			public void onNext(employee value) {
				//When client send the value to the server side, StringBuilder will append the request.
				// During the stream.

				sb.append("\n============");
				sb.append("\n ID: " + value.getEmpNo() + "\n Name: " + value.getEmpName() + "\n Shift: "
						+ value.getShift());
				
				//Indicate the received value in the console of server side.
				System.out.println("receive -> " + value.getEmpNo());

			}

			@Override
			public void onError(Throwable t) {
				// TODO Auto-generated method stub
				//I didn't indicate anything in onError();
			}

			//After the stream finished, onCompleted() method will be called.
			@Override
			public void onCompleted() {
				// Preparing and sending the reply for the client. Here, response is build and
				// with the value (length) computed by above logic.
				// Here, response is sent once the client is done with sending the stream.
				
				//Sent out the response to the client side.
				//Using employeeList, we can build the message. This message will contain total work shift of employee.
				//Because it just sent once that the client is done with sending the stream.
				employeeList res = employeeList.newBuilder().setVal(sb.toString()).build();

				responseObserver.onNext(res);

				responseObserver.onCompleted();

			}

		};
	}


}
