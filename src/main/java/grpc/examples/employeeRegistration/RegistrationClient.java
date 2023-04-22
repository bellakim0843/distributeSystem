package grpc.examples.employeeRegistration;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.net.InetAddress;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceInfo;
import javax.jmdns.ServiceListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;
import grpc.examples.employeeRegistration.userGrpc.userBlockingStub;
import grpc.examples.employeeRegistration.userGrpcClient.userStub;

public class RegistrationClient {

	private static  Logger logger = Logger.getLogger(RegistrationClient.class.getName());

	//Due to error of JmDNS in mac, I set all argument in static.
	private static userStub asyncStubClient;
	private static userBlockingStub blockingStubClient;
	static String host = "_grpc._tcp.local.";//
	static String myhost = "localhost";
	static int port = 50050;
	static String resolvedIP;

	public static void main(String[] args) throws Exception {

		//Using one main GUI, I decided not to call any method in main method.
		//login();
		//register();
	}
	
	public static void login() {

		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50050).usePlaintext().build();

		//Create blockingStub to react server side's message.
		blockingStubClient = userGrpc.newBlockingStub(channel);

		try {
			/* We're using Protocol Buffers
			to define the loginRequest and loginResponse messages 
			that will be sent between the client and server.*/
			loginRequest request = loginRequest.newBuilder().setEmpName("Worker").setEmpNo(19921019).build();
			
			/* We're calling the login() method on the blockingStubClient object, 
			 * which is a gRPC stub generated from the Protocol Buffers. 
			 * The blockingStub provides a blocking RPC (remote procedure call) style 
			 * where the client waits for the response to be returned by the server 
			 * before continuing execution. */
			
			loginResponse reply = blockingStubClient.login(request);
			
			/* The login() method sends the loginRequest message
			 *  to the server and returns a loginResponse message */
			
			System.out.println("Message sent by the server " + reply.getConfirm());
			
			/* printing the confirmation message returned by the server
			 *  to the console using the getConfirm() method */
			
		} catch (StatusRuntimeException e) {
			e.getStatus();
		} finally {
			try {
				channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//After that, Call the register() method
			 register();

			//channel will be shut down
			channel.shutdown();
		}

	}

	public static void register() {

		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50050).usePlaintext().build();

		//Create asyncStub to react server side's message.
		asyncStubClient = userGrpcClient.newStub(channel);
		//Call JmDNS method() firstly.
		clientJMDNS();

		/* Handling the stream for client using onNext, onError, 
		 * onCompleted (logic will be executed after the completion of stream)*/
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
				System.out.println("Work-Shift registration is totally completed!");

			}

		};

		//Using onNext, client sends a stream of messages.
		StreamObserver<employee> requestObserver = asyncStubClient.register(responseObserver);

		try {
			/* GUI using java swing */

			JFrame registerFrame = new JFrame();

			registerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			registerFrame.setSize(500, 520);
			registerFrame.setResizable(true);

			JPanel namePanel1 = new JPanel();
			namePanel1.setPreferredSize(new Dimension(500, 250));
			namePanel1.setBackground(Color.decode("#C1DAD6"));

			JPanel namePanel2 = new JPanel();

			namePanel2.setPreferredSize(new Dimension(500, 40));
			namePanel2.setBackground(Color.decode("#C1DAD6"));
			registerFrame.add(namePanel2, BorderLayout.SOUTH);
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.insets = new Insets(5, 5, 5, 5);

			JLabel workShift = new JLabel("Today's Work-shift List");
			workShift.setFont(new Font("Verdana", Font.BOLD, 17));
			namePanel1.add(workShift);

			JButton closeButton = new JButton("Close");

			StringBuilder total = new StringBuilder();

			/* Input the employee's number using JOptionPane*/
			int number = Integer.parseInt(JOptionPane.showInputDialog("How many Employee will you register?"));
			
			/* Create the 2D Array to store employee's information*/
			String[][] data = new String[number][3];

			/* Using for-loop to set the value of work shift list */
			for (int i = 0; i < number; i++) {

				data[i][0] = (JOptionPane.showInputDialog("Input employee's ID"));
				data[i][1] = (JOptionPane.showInputDialog("Input employee's Name"));
				data[i][2] = (JOptionPane.showInputDialog("Input employee'shift "));

				int a = Integer.parseInt(data[i][0]);
				String b = data[i][1];
				int c = Integer.parseInt(data[i][2]);

				/* To print out the total list in the console,
				 * I use stringbuilder to store the message */
				total.append("\n ID: " + a + "\n Name: " + b + "\n Shift: " + c);
				total.append("\n============");
				
				/*The code is building a new Employee message using the newBuilder() method 
				 * of the Employee class. build() method is called to create the Employee message and
				 *  pass it to the onNext() method of requestObserver.
				 * */
				
				requestObserver.onNext(employee.newBuilder().setEmpNo(a).setEmpName(b).setShift(c).build());

			}

			String column[] = { "Employee ID", "Employee Name", "Shift" };
			JTable jt = new JTable(data, column);

			JScrollPane sp = new JScrollPane(jt);
			namePanel1.add(sp);

			gbc.gridx = 1;
			gbc.gridy = 0;
			namePanel2.add(closeButton, gbc);

			closeButton.addActionListener(e -> {

				registerFrame.dispose();
			});

			registerFrame.add(namePanel1);

			registerFrame.setVisible(true);

			System.out.println("Registration Process completed!");

			// End of requests
			requestObserver.onCompleted();

			Thread.sleep(new Random().nextInt(1000) + 500);

		} catch (RuntimeException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		channel.shutdown();

	}
	
	/**/
	private static class RegistrationListener implements ServiceListener {
		
		/*a message is printed to the console indicating that 
		 * a service has been added and providing information about the service.*/
		public void serviceAdded(ServiceEvent event) {
			System.out.println("Service added: " + event.getInfo());
		}
		
		/*a message is printed to the console indicating that
		 *  a service has been removed and providing information about the service.*/
		public void serviceRemoved(ServiceEvent event) {
			System.out.println("Service removed: " + event.getInfo());
		}
		/*a message is printed to the console indicating that 
		 * a service has been resolved and providing information about the service*/
		
		@SuppressWarnings("deprecation")
		public void serviceResolved(ServiceEvent event) {
			System.out.println("Service resolved: " + event.getInfo());
			
			/*the port and IP address of the service are extracted from the ServiceInfo object, 
			 * and the port and resolvedIP static variables are set to these values*/
			
			ServiceInfo info = event.getInfo();
			port = info.getPort();
			resolvedIP = info.getHostAddress();
			System.out.println("IP Resolved - " + resolvedIP + ":" + port);
		}
	}

	public static void clientJMDNS() {

		try {
			// Create a JmDNS instance
			JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
			System.out.println("Employee Registration program is being opened..");

			// Add a service listener RegistrationListener()
			jmdns.addServiceListener(host, new RegistrationListener());

			System.out.println("Please wait the moment..");

			Thread.sleep(20000);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
}
