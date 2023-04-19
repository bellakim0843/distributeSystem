package grpc.example.helloworld;

import java.io.IOException;

import grpc.example.helloworld.LogInGrpc.LogInImplBase;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

public class LoginServer extends LogInImplBase{
	
	
	public static void main(String[] args) {
		
		LoginServer loginserver = new LoginServer();
		
		int port = 50097;
		
		Server server;
		try {
			server = ServerBuilder.forPort(port).addService(loginserver).build().start();
			System.out.println("Server is started!");
			server.awaitTermination();
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

	@Override
	public void inputInfo(LoginRequest request, StreamObserver<LoginReply> responseObserver) {
		// TODO Auto-generated method stub
		System.out.println("=====Receiving Log-in information from User ====");
		
		//Logic
		String myUser = "Welcome! "+ request.getId()+" :: "+request.getName()+". Have a nice day!";
		
		
		
		LoginReply reply = LoginReply.newBuilder().setMessage(myUser).build();
	
	
		responseObserver.onNext(reply);
		responseObserver.onCompleted();
	
	
	
	}
	
	

}
