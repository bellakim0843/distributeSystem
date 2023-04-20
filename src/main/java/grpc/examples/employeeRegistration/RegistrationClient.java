package grpc.examples.employeeRegistration;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
//required java packages for the program. Depends on your logic.
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

//required grpc package for the client side
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;
//This is to include rpc method enum message
import grpc.examples.employeeRegistration.empty;
import grpc.examples.employeeRegistration.userGrpc.userStub;




//Client need not to extend any other class (GRPC related code) here 
public class RegistrationClient {
	// First we create a logger to show client side logs in the console. logger instance will be used to log different events at the client console.
	// This is optional. Could be used if needed.
	private static  Logger logger = Logger.getLogger(RegistrationClient.class.getName());

	// Creating stubs for establishing the connection with server.
	// Blocking stub
	private static userGrpc.userBlockingStub blockingStub;
	// Asynch stub
	private static userStub asyncStub;
	
	// The main method will have the logic for client.
	public static void main(String[] args) throws Exception {
	// First a channel is being created to the server from client. Here, we provide the server name (localhost) and port (50055).
		// As it is a local demo of GRPC, we can have non-secured channel (usePlaintext).
		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50037).usePlaintext().build();

		//stubs -- generate from proto

		asyncStub = userGrpc.newStub(channel);
		blockingStub = userGrpc.newBlockingStub(channel);
		// Unary RPC call
		  register();


		 // logout(); 	//passing an empty message - no server reply, error message

		// Closing the channel once message has been passed.		
		channel.shutdown();

	}


	//unary rpc
	public static void register() {
		

		
		  login();
		

		// First creating a request message. Here, the message contains a string in setVal
//		loginRequest req = loginRequest.newBuilder().setEmpNo(19921019).setEmpName("Nakyung Kim").build();
//		String information = "Welcome! "+req.getEmpName()+", EmpNo: "+req.getEmpNo();
		//  Calling a remote RPC method using blocking stub defined in main method. req is the message we want to pass.
		//loginResponse response = loginResponse.newBuilder().setConfirm("Login Completed! EmpNo: "+ req.getEmpNo()).setResponseMessage(" Welcome! "+req.getEmpName()).build();

		//response contains the output from the server side. Here, we are printing the value contained by response. 
	//	System.out.println(information);
		  
			// Handling the stream for client using onNext (logic for handling each message in stream), onError, onCompleted (logic will be executed after the completion of stream)
			StreamObserver<employeeList> responseObserver = new StreamObserver<employeeList>() {
				
				

				@Override
				public void onNext(employeeList value) {
					System.out.println("Today's Work Shift " + value.getVal());
				}

				@Override
				public void onError(Throwable t) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onCompleted() {
					System.out.println("completed ");

				}

			};

			// Here, we are calling the Remote length method. Using onNext, client sends a stream of messages.
			StreamObserver<employee> requestObserver = asyncStub.register(responseObserver);

						try {
							/*GUI*/
								System.out.println("Input the data!");
								
								
								
								requestObserver.onNext(employee.newBuilder().setEmpNo(19921019).setEmpName("Ann").setShift(1).build());
								requestObserver.onNext(employee.newBuilder().setEmpNo(19999999).setEmpName("Paul").setShift(2).build());
								requestObserver.onNext(employee.newBuilder().setEmpNo(20021010).setEmpName("Maria").setShift(3).build());


							System.out.println("SENDING MESSAGES");

							// Mark the end of requests
							requestObserver.onCompleted();


							// Sleep for a bit before sending the next one.
							Thread.sleep(new Random().nextInt(1000) + 500);


						} catch (RuntimeException e) {
							e.printStackTrace();
						} catch (InterruptedException e) {			
							e.printStackTrace();
						}

		}
	

	//unary rpc
	public static void logout() {
		// First creating a request message. Here, the message contains emply message as defined in proto enum
		logoutResponse req = logoutResponse.newBuilder().setConfirmMessage("Log-Out Completed!").build();
		
		try {
			// Calling a remote RPC method using blocking stub defined in main method. req is the message we want to pass.
			empty response = blockingStub.logout(req);
			//response contains the output from the server side. Here, we are printing the value contained by response.
			System.out.println("Log-Out Completed! "+response.toString());

		}catch(StatusRuntimeException ex) {
			// Print if any error/exception is generated.
			System.out.println( ex.getMessage());
			//ex.printStackTrace();
		}


	}
	
	

	public static void login() {
		
//		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50016).usePlaintext().build();
//
//		//stubs -- generate from proto
//
//		asyncStub = userGrpc.newStub(channel);
//		blockingStub = userGrpc.newBlockingStub(channel);
		
		
		
//    		
//		JTextField id = new JTextField();
//
//
//		JTextField name = new JPasswordField();
//
//		Object[] message = {
//		    "Employee ID:", id,
//		    "Name:", name
//		};
		

	//	int option = JOptionPane.showConfirmDialog(null, message, "Login", JOptionPane.OK_CANCEL_OPTION);

	//	if(option == JOptionPane.OK_OPTION)
		{
//			String idText = (id.getText());
//			System.out.println(idText);
//			String nameText = name.getText();
//			System.out.println(nameText);
			
			String idText = "19921019";
			String nameText = "Kim";
			
//			loginRequest loginrequeset = loginRequest.newBuilder().setEmpNo(Integer.parseInt(idText)).setEmpName(name.getText()).build();
			loginRequest loginrequeset = loginRequest.newBuilder().setEmpNo(Integer.parseInt(idText)).setEmpName(nameText).build();
			loginResponse reponse = loginResponse.newBuilder().setConfirm("Login Completed! EmpNo: "+ loginrequeset.getEmpNo()).setResponseMessage(" Welcome! "+loginrequeset.getEmpName()).build();
			//JOptionPane.showConfirmDialog(null, reponse.getResponseMessage());
			
			JFrame frame = new JFrame();
			
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(500,500);
			frame.setVisible(true);
			frame.setResizable(false);
			
			JPanel loginBar = new JPanel();
			frame.add(loginBar);
			loginBar.setBounds(0, 0, 500, 40);

			JPanel namePanel1 = new JPanel();
			namePanel1.setBounds(0, 40, 170, 150);
			namePanel1.setBackground(Color.CYAN);
			frame.add(namePanel1);
			
			JPanel namePanel2 = new JPanel();
			namePanel2.setBounds(170, 40, 170, 150);
			namePanel2.setBackground(Color.red);
			frame.add(namePanel2);
			
			JPanel namePanel3 = new JPanel();
			namePanel3.setBounds(340, 40, 170, 150);
			namePanel3.setBackground(Color.orange);
			frame.add(namePanel3);
			
			JPanel namePanel4 = new JPanel();
			namePanel4.setBounds(0, 190, 500, 50);
			namePanel4.setBackground(Color.magenta);
			frame.add(namePanel4);
			
			JPanel namePanel5 = new JPanel();
			namePanel5.setBounds(0, 240, 500, 150);
			namePanel5.setBackground(Color.green);
			frame.add(namePanel5);
			

			
			
			JLabel label = new JLabel();

			frame.add(label);
			label.setText("Welcome! EmpNo: "+idText+" Name: " + nameText);
			label.setOpaque(true);
			label.setBounds(10,10,500,20);
			label.setForeground(Color.black);
			label.setFont(new Font("Verdana", Font.BOLD,14));
			loginBar.add(label);
			
			
			JLabel labelEmpNo = new JLabel();
			
			labelEmpNo.setText("Employee Number");
			labelEmpNo.setBounds(0, 0, 0, 0);
			namePanel1.add(labelEmpNo);
			
			JLabel labelEmpName = new JLabel();
			
			labelEmpName.setText("Employee Name");
			labelEmpName.setBounds(0, 0, 0, 0);
			namePanel2.add(labelEmpName);
			
			JLabel labelShift  = new JLabel();
			
			labelShift.setText("Shift");
			labelShift.setBounds(0, 0, 0, 0);
			namePanel3.add(labelShift);
			
			JTextField empNoInput = new JTextField();
			namePanel1.add(empNoInput);
			empNoInput.setSize(100,50);
			empNoInput.setBounds(0, 35, 170, 50);

			
			JTextField empNameInput = new JTextField();
			empNameInput.setBounds(0, 35, 170, 50);
			namePanel2.add(empNameInput);
			
			JTextField shiftInput = new JTextField();
			shiftInput.setBounds(0, 35, 170, 50);
			namePanel3.add(shiftInput);
				
			JButton registrationButton = new JButton();
			registrationButton.setLocation(200, 0);
			registrationButton.setSize(100,50);
			registrationButton.setText("Registration");
			registrationButton.setFocusable(true);
			namePanel4.add(registrationButton);
			
			StringBuilder sb = new StringBuilder();
	
			JTextArea registrationArea = new JTextArea();

			namePanel5.add(registrationArea);
			registrationArea.setEnabled(false);

			
			registrationButton.addActionListener(e -> {
				
			
				sb.append("Employee Number: "+ empNoInput.getText()+" Employee Name: "+empNameInput.getText()+" Shift: "+shiftInput.getText());
				
				System.out.println(sb.toString());
				registrationArea.setText(sb.toString());
				
				empNoInput.setText("");
				empNameInput.setText("");
				shiftInput.setText("");
				
				
			});
			
			




			
			//BoxLayout bl = new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS);
			
			//frame.getContentPane().setLayout(bl);
			

			


			
//			
//			JTextField empInput = new JTextField();
//			empInput.setSize(200, 50);
//			namePanel1.add(empInput);
			
//			
//			JLabel empNo = new JLabel();
//			
//			
//			button.addActionListener(e -> {
//				
//				//frame.dispose();
//			
//				empNo.setText(empNoInput.getText());
//				namePanel4.add(empNo);
//
//				
//			});
		}



	/*Service*/
		
	
		
		
	
		

		

	
}
}




