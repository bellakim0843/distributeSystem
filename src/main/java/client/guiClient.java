package client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
//required java packages for the program. Depends on your logic.
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceInfo;
import javax.jmdns.ServiceListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

//required grpc package for the client side
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;
import grpc.examples.employeeRegistration.employee;
import grpc.examples.employeeRegistration.employeeList;
import grpc.examples.employeeRegistration.loginRequest;
import grpc.examples.employeeRegistration.loginResponse;
import grpc.examples.employeeRegistration.userGrpc;
import grpc.examples.employeeRegistration.userGrpcClient;
import grpc.examples.employeeRegistration.userGrpc.userBlockingStub;
//This is to include rpc method enum message
import grpc.examples.employeeRegistration.userGrpcClient.userStub;
import grpc.examples.realtimeChat.ClientSideChat;
import grpc.examples.realtimeChat.ServerSideChat;
import grpc.examples.realtimeChat.chatGrpc;
import grpc.examples.securityDeviceCheck.deviceList;
import grpc.examples.securityDeviceCheck.deviceRequest;
import grpc.examples.securityDeviceCheck.userGrpcServer;
import grpc.examples.securityDeviceCheck.checkServer;

//Client need not to extend any other class (GRPC related code) here 
public class guiClient {
	// First we create a logger to show client side logs in the console. logger
	// instance will be used to log different events at the client console.
	// This is optional. Could be used if needed.
	private static Logger logger = Logger.getLogger(guiClient.class.getName());

	// Creating stubs for establishing the connection with server.
	// Blocking stub
	// private static userGrpc.userBlockingStub blockingStub;
	// Asynch stub
	private static userStub asyncStubClient;
	private static userGrpcClient.userBlockingStub blockingStubClient;
	private static userGrpcServer.userBlockingStub blockingStubServer;
	private static chatGrpc.chatStub asyncStubChat;
	static String host = "_grpc._tcp.local.";// 
	static String myhost= "localhost";
	static int port = 50050;   //여기 포트 그대로 쓸 것 local host + port 넘버 입력. 나머지는 놔둘 
	static String resolvedIP;

	// The main method will have the logic for client.
	public static void main(String[] args) throws Exception {
		// First a channel is being created to the server from client. Here, we provide
		// the server name (localhost) and port (50055).
		// As it is a local demo of GRPC, we can have non-secured channel
		// (usePlaintext).

		// Unary RPC call

		 login();

		// register();

		// Closing the channel once message has been passed.

		// checkBlocking();

		//realtimeChat();

		// passing an empty message - no server reply, error message

	}
	
	
	public static void login() {

		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50050).usePlaintext().build();

		// stubs -- generate from proto

		// blockingStub = userGrpc.newBlockingStub(channel);

		try {
			/* GUI */

			JTextField id = new JTextField();

			JTextField name = new JTextField();

			Object[] message = { "Employee ID:", id, "Employee Name:", name };

			int option = JOptionPane.showConfirmDialog(null, message, "Login", JOptionPane.OK_CANCEL_OPTION);

			if (option == JOptionPane.OK_OPTION) {
				String idText = (id.getText());
				String nameText = name.getText();

				loginRequest loginrequeset = loginRequest.newBuilder().setEmpNo(Integer.parseInt(idText))
						.setEmpName(nameText).build();

				loginResponse reponse = loginResponse.newBuilder()
						.setResponseMessage("Welcome! " + loginrequeset.getEmpName())
						.setConfirm("EmpNo: " + loginrequeset.getEmpNo() + "\nLog-in Completed!").build();

				JOptionPane.showConfirmDialog(null, reponse.getResponseMessage() + "\n" + reponse.getConfirm());
				System.out.println(reponse.getResponseMessage() + "\n" + reponse.getConfirm());
				
				
				JFrame frame = new JFrame();

				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setSize(400, 350);
				frame.setResizable(false);

				JPanel loginBar = new JPanel();
				loginBar.setBackground(Color.decode("#ACD1E9"));
				loginBar.setSize(400,30);

				JLabel loginLabel = new JLabel();
				loginLabel.setText("Welcome! ID: " + idText + " Name: " + nameText);
				loginBar.add(loginLabel);
				loginLabel.setFont(new Font("Verdana", Font.BOLD, 14));
				frame.add(loginBar, BorderLayout.NORTH);

				JPanel namePanel1 = new JPanel(null); // set the layout manager to null
				namePanel1.setSize(400,200);
				namePanel1.setBackground(Color.decode("#ACD1E9"));
				
				String workShiftText = "Work-Shift\nRegistration";
				JButton registrationButton = 
				    new JButton("<html>" + workShiftText.replaceAll("\\n", "<br>") + "</html>");
				registrationButton.setBounds(10,20,120,120);
				//registrationButton.setBackground(Color.decode("#C1DAD6"));

				
				String deviceCheckText = "Security"+"\n"+"&nbsp;Device"+"\n"+"&nbsp;&nbsp;&nbsp;List";
				JButton deviceCheckButton = 
				    new JButton("<html>" + deviceCheckText.replaceAll("\\n", "<br>") + "</html>");
				deviceCheckButton.setBounds(140,20,120,120);
				//deviceCheckButton.setBackground(Color.decode("#ACD1E9"));

				String chatText = "Real-Time"+"\n"+"&nbsp;&nbsp;&nbsp;Chat";
				JButton chatButton = 
				    new JButton("<html>" + chatText.replaceAll("\\n", "<br>") + "</html>");
				chatButton.setBounds(270,20,120,120);
				//chatButton.setBackground(Color.decode("#6D929B"));


					
				JButton logoutButton = new JButton("Close");
				logoutButton.setBounds(150,150,100,40);
				
				namePanel1.add(registrationButton);
				namePanel1.add(deviceCheckButton);
				namePanel1.add(chatButton);
				namePanel1.add(logoutButton );

				frame.add(namePanel1);

				frame.setVisible(true);

				registrationButton.addActionListener(e -> {

					register();
				});

				deviceCheckButton.addActionListener(e -> {

					checkBlocking();
				});

				chatButton.addActionListener(e -> {

					realtimeChat();
				});

				logoutButton.addActionListener(e -> {

					frame.dispose();
				});

			}

			// Mark the end of requests

			// Sleep for a bit before sending the next one.
			Thread.sleep(new Random().nextInt(1000) + 500);

		} catch (RuntimeException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		channel.shutdown();

	}

	public static void realtimeChat() {

		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 60011).usePlaintext().build();

		// stubs -- generate from proto
		asyncStubChat = chatGrpc.newStub(channel);

		Scanner sc = new Scanner(System.in);
		// System.out.println("Username");
		String name = JOptionPane.showInputDialog("Please input your name.");

		JFrame chatFrame = new JFrame();

		chatFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		chatFrame.setSize(500, 490);
		chatFrame.setResizable(true);

		JLabel chatLabel = new JLabel("Real-Time Chat - User: " + name);
		chatLabel.setFont(new Font("Verdana", Font.BOLD, 17));

		JPanel subjectBar = new JPanel();
		subjectBar.setSize(new Dimension(500, 40));
		subjectBar.setBackground(Color.decode("#C0C0C0"));

		subjectBar.add(chatLabel);
		chatFrame.add(subjectBar);

		JPanel namePanel1 = new JPanel();
		GridBagLayout layout = new GridBagLayout();
		namePanel1.setLayout(layout);
		namePanel1.setPreferredSize(new Dimension(500, 200));
		namePanel1.setBackground(Color.decode("#003366"));
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(15, 5, 0, 5);

		JTextArea clientChatBox = new JTextArea();
		clientChatBox.setPreferredSize(new Dimension(425, 1500));
		clientChatBox.setLineWrap(true);
		clientChatBox.setWrapStyleWord(true);
		clientChatBox.setEnabled(false);

		JScrollPane scrollPane = new JScrollPane(clientChatBox);

		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setEnabled(true);
		scrollPane.setPreferredSize(new Dimension(425, 280));

		chatFrame.getContentPane().add(scrollPane);

		namePanel1.add(scrollPane);

		JTextField clientChatInput = new JTextField(null);
		clientChatInput.setPreferredSize(new Dimension(425, 40));

		JButton clientChatButton = new JButton("Send");

		JButton quitButton = new JButton("Quit");

		gbc.gridx = 0;
		gbc.gridy = 1;
		namePanel1.add(clientChatInput, gbc);

		chatFrame.add(namePanel1);
		chatFrame.setVisible(true);

		JPanel namePanel2 = new JPanel();
		namePanel2.setPreferredSize(new Dimension(500, 40));
		namePanel2.setBackground(Color.decode("#003366"));
		chatFrame.add(namePanel2, BorderLayout.SOUTH);
		GridBagLayout layout2 = new GridBagLayout();
		namePanel2.setLayout(layout2);
		GridBagConstraints gbc2 = new GridBagConstraints();
		gbc2.insets = new Insets(5, 5, 5, 5);

		gbc2.gridx = 0;
		gbc2.gridy = 0;
		namePanel2.add(clientChatButton, gbc2);

		gbc2.gridx = 1;
		gbc2.gridy = 0;
		namePanel2.add(quitButton, gbc2);

		// Handling the server stream for client using onNext (logic for handling each
		// message in stream), onError, onCompleted (logic will be executed after the
		// completion of stream)
		StreamObserver<ClientSideChat> responseObserver = new StreamObserver<ClientSideChat>() {

			@Override
			public void onNext(ClientSideChat value) {
				StringBuilder sb = new StringBuilder();
				// clientChatBox.setText("");
				sb.append(value.getMessage());
				clientChatBox.append(sb.toString());

				System.out.println(value.getMessage());

			}

			@Override
			public void onError(Throwable t) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onCompleted() {
				// TODO Auto-generated method stub
				System.out.println("server completed");
			}

		};

		// Here, we are calling the Remote reverseStream method. Using onNext, client
		// sends a stream of messages.
		StreamObserver<ServerSideChat> requestObserver = asyncStubChat.realtimeChat(responseObserver);

		try {

			clientChatButton.addActionListener(e -> {

				String myText = clientChatInput.getText();

				requestObserver.onNext(ServerSideChat.newBuilder().setMessage(name + ": " + myText).build());
				clientChatBox.append(name + ": " + myText + "\n");
				clientChatInput.setText("");
			});

			System.out.println("SENDING EMSSAGES");

			quitButton.addActionListener(e -> {

				requestObserver.onCompleted();
				chatFrame.dispose();

			});

			// Sleep for a bit before sending the next one.
			Thread.sleep(new Random().nextInt(1000) + 500);

		}

		catch (RuntimeException d) {
			d.printStackTrace();
		} catch (InterruptedException d) {
			d.printStackTrace();
		}

	}

	public static void checkBlocking() {

		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50073).usePlaintext().build();

		// stubs -- generate from proto
		blockingStubServer = userGrpcServer.newBlockingStub(channel);
		int totalNumber = 0;
		// First creating a request message. Here, the message contains a string in
		// setVal
		deviceRequest request = deviceRequest.newBuilder().setMassData(
				"Device No: 1\nDevice Type: Security Camera\nDevice Code: AAA2020\nDevice Location: Warehouse-Front,"
						+ "\nDevice No: 2\nDevice Type: Security Camera\nDevice Code: ACC2022\nDevice Location: Warehouse-Rear,"
						+ "\nDevice No: 3\nDevice Type: Security Camera\nDevice Code: ABB2017\nDevice Location: Main entrance,"
						+ "\nDevice No: 4\nDevice Type: Thermal Imaging Camera\nDevice Code: UPC2021\nDevice Location: Main entrance,"
						+ "\nDevice No: 5\nDevice Type: Finger-Print Scanner \nDevice Code: FII2020\nDevice Location: Sub entrance,"
						+ "\nDevice No: 6\nDevice Type: Security Camera\nDevice Code: RTT2018\nDevice Location: Lab, "
						+ "\nDevice No: 7\nDevice Type: Card Scanner\nDevice Code: MSN3000\nDevice Location: Security Room,"
						+ "\nDevice No: 8\nDevice Type: Thermal Imaging Camera\nDevice Code: SIP2023\nDevice Location: Security Device Cargo ,"
						+ "\nDevice No: 9\nDevice Type: Finger-Print Scanner \nDevice Code: FII2020\nDevice Location: Rear entrance,"
						+ "\nDevice No: 10\nDevice Type: Security Camera\nDevice Code: RTT2018\nDevice Location: Parking Lot, "
						+ "\nDevice No: 11\nDevice Type: Remote Controller\nDevice Code: QTT2023\nDevice Location: Control tower,"
						+ "\nDevice No: 12\nDevice Type: Security Camera\nDevice Code: SIP2022\nDevice Location: Cleaning room ,"
						+ "\nDevice No: 13\nDevice Type: Security Camera \nDevice Code: ACC2016\nDevice Location: Meeting room,"
						+ "\nDevice No: 14\nDevice Type: Security Camera\nDevice Code: OMM2019\nDevice Location: Lab, "
						+ "\nDevice No: 15\nDevice Type: Iris recognition Controller\nDevice Code: EYE2023\nDevice Location: Executive room,")
				.build();

		// as this call is blocking. The client will not proceed until all the messages
		// in stream has been received.
		try {
			// Iterating each message in response when calling remote split RPC method.
			Iterator<deviceList> responses = blockingStubServer.deviceCheck(request);

			JFrame deviceFrame = new JFrame();

			deviceFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			deviceFrame.setSize(500, 450);
			deviceFrame.setResizable(true);

			JPanel namePanel1 = new JPanel();
			namePanel1.setPreferredSize(new Dimension(500, 400));
			namePanel1.setBackground(Color.decode("#6D929B"));

			JPanel namePanel2 = new JPanel();
			GridBagLayout layout = new GridBagLayout();
			namePanel2.setLayout(layout);
			namePanel2.setPreferredSize(new Dimension(500, 50));
			namePanel2.setBackground(Color.decode("#6D929B"));
			deviceFrame.add(namePanel2, BorderLayout.SOUTH);
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.insets = new Insets(5, 5, 5, 5);

			JLabel workShift = new JLabel("Total Security Device List");
			workShift.setFont(new Font("Verdana", Font.BOLD, 17));
			namePanel1.add(workShift);

			JTextArea list = new JTextArea();
			list.setPreferredSize(new Dimension(400, 1500));
			list.setLineWrap(true);
			list.setWrapStyleWord(true);
			list.setEnabled(false);

			JScrollPane scrollPane = new JScrollPane(list);

			scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			scrollPane.setEnabled(true);
			scrollPane.setPreferredSize(new Dimension(400, 350));

			deviceFrame.getContentPane().add(scrollPane);

			namePanel1.add(scrollPane);

			JButton closeButton = new JButton("Close");

			namePanel2.add(closeButton);

			closeButton.addActionListener(e -> {

				deviceFrame.dispose();
			});

			deviceFrame.add(namePanel1);

			deviceFrame.setVisible(true);

			// Client keeps a check on the next message in stream.
			while (responses.hasNext()) {
				deviceList temp = responses.next();
				System.out.println(temp.getVal());
				list.append(temp.getVal() + "\n=============================");
				totalNumber++;
			}
			list.append("\nTotal " + totalNumber + "devices are in the company.");

		} catch (StatusRuntimeException e) {
			e.printStackTrace();
		}

	}

	

	public static void register() {

		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50050).usePlaintext().build();

		// stubs -- generate from proto
		// blockingStub = userGrpc.newBlockingStub(channel);
		asyncStubClient = userGrpcClient.newStub(channel);
		
		clientJMDNS();
//
//		// First creating a request message. Here, the message contains a string in
//		// setVal
////		loginRequest req = loginRequest.newBuilder().setEmpNo(19921019).setEmpName("Nakyung Kim").build();
////		String information = "Welcome! "+req.getEmpName()+", EmpNo: "+req.getEmpNo();
//		// Calling a remote RPC method using blocking stub defined in main method. req
//		// is the message we want to pass.
//		// loginResponse response = loginResponse.newBuilder().setConfirm("Login
//		// Completed! EmpNo: "+ req.getEmpNo()).setResponseMessage(" Welcome!
//		// "+req.getEmpName()).build();
//
//		// response contains the output from the server side. Here, we are printing the
//		// value contained by response.
//		// System.out.println(information);
//
//		// Handling the stream for client using onNext (logic for handling each message
//		// in stream), onError, onCompleted (logic will be executed after the completion
//		// of stream)
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

		// Here, we are calling the Remote length method. Using onNext, client sends a
		// stream of messages.
		StreamObserver<employee> requestObserver = asyncStubClient.register(responseObserver);

		try {
			/* GUI */

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

			int number = Integer.parseInt(JOptionPane.showInputDialog("How many Employee will you register?"));

			String[][] data = new String[number][3];

			for (int i = 0; i < number; i++) {

				data[i][0] = (JOptionPane.showInputDialog("Input employee's ID"));
				data[i][1] = (JOptionPane.showInputDialog("Input employee's Name"));
				data[i][2] = (JOptionPane.showInputDialog("Input employee'shift "));

				int a = Integer.parseInt(data[i][0]);
				String b = data[i][1];
				int c = Integer.parseInt(data[i][2]);

				total.append("\n ID: " + a + "\n Name: " + b + "\n Shift: " + c);
				total.append("\n============");

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

			// Mark the end of requests
			requestObserver.onCompleted();

			// Sleep for a bit before sending the next one.
			Thread.sleep(new Random().nextInt(1000) + 500);

		} catch (RuntimeException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		channel.shutdown();

	}
	
	public static void clientJMDNS() {
		
		try {
			// Create a JmDNS instance
			JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
	          System.out.println("Employee Registration program is being opened..");

			// Add a service listener
			jmdns.addServiceListener(host, new SampleListener());
			
	          System.out.println("Please wait the moment..");

			// Wait a bit
            Thread.sleep(20000);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50050).usePlaintext().build();

		// stubs -- generate from proto

		userStub asyncStub = userGrpcClient.newStub(channel);
		userBlockingStub blockingStubNew = userGrpc.newBlockingStub(channel);
		// Unary RPC call
		
		
		try {

			loginRequest request = loginRequest.newBuilder().setEmpName("Nakyung Kim").setEmpNo(19921019).build();
			
			loginResponse reply = blockingStubNew.login(request);
					//employeeList.newBuilder().setVal(request.getEmpName()).build();
			System.out.println("Confirm JmDNS message from the server " + reply.getConfirm());
		}catch (StatusRuntimeException e) {
			e.getStatus();
		} finally {
			try {
				channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// Closing the channel once message has been passed.
		channel.shutdown();
		
	}
	
	private static class SampleListener implements ServiceListener {
		public void serviceAdded(ServiceEvent event) {
			System.out.println("Service added: " + event.getInfo());
		}
		public void serviceRemoved(ServiceEvent event) {
			System.out.println("Service removed: " + event.getInfo());
		}
		@SuppressWarnings("deprecation")
		public void serviceResolved(ServiceEvent event) {
					System.out.println("Service resolved: " + event.getInfo());
			
                    ServiceInfo info = event.getInfo();
                    port = info.getPort();
                    resolvedIP = info.getHostAddress();                    
                    System.out.println("IP Resolved - " + resolvedIP + ":" + port);
		}
	}

}
