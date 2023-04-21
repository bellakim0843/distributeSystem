package grpc.examples.employeeRegistration;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.net.InetAddress;
//required java packages for the program. Depends on your logic.
import java.util.Random;
import java.util.concurrent.TimeUnit;

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

//required grpc package for the client side
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;

import grpc.examples.employeeRegistration.userGrpc.userBlockingStub;
//This is to include rpc method enum message
import grpc.examples.employeeRegistration.userGrpcClient.userStub;

//Client need not to extend any other class (GRPC related code) here 
public class RegistrationClient {
	// First we create a logger to show client side logs in the console. logger
	// instance will be used to log different events at the client console.
	// This is optional. Could be used if needed.
	// private static Logger logger =
	// Logger.getLogger(RegistrationClient.class.getName());

	// Creating stubs for establishing the connection with server.
	// Blocking stub
	// private static userGrpcClient.userBlockingStub blockingStub;
	// Asynch stub
	private static userStub asyncStubClient;
	static String host = "_grpc._tcp.local.";//
	static String myhost = "localhost";
	static int port = 50050; // 여기 포트 그대로 쓸 것 local host + port 넘버 입력. 나머지는 놔둘
	static String resolvedIP;

	// The main method will have the logic for client.
	public static void main(String[] args) throws Exception {
		// First a channel is being created to the server from client. Here, we provide
		// the server name (localhost) and port (50055).
		// As it is a local demo of GRPC, we can have non-secured channel
		// (usePlaintext).

		// Unary RPC call

//		login();
//		register();

		// Closing the channel once message has been passed.

		// passing an empty message - no server reply, error message

	}

	public static void register() {

		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50050).usePlaintext().build();

		// stubs -- generate from proto
		// blockingStub = userGrpc.newBlockingStub(channel);
		asyncStubClient = userGrpcClient.newStub(channel);

		clientJMDNS();

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

	public static void login() {

		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50050).usePlaintext().build();

		// stubs -- generate from proto

		asyncStubClient = userGrpcClient.newStub(channel);
		userBlockingStub blockingStubNew = userGrpc.newBlockingStub(channel);

		try {

			loginRequest request = loginRequest.newBuilder().setEmpName("Nakyung Kim").setEmpNo(19921019).build();

			loginResponse reply = blockingStubNew.login(request);
			// employeeList.newBuilder().setVal(request.getEmpName()).build();
			System.out.println("Message sent by the server " + reply.getConfirm());
		} catch (StatusRuntimeException e) {
			e.getStatus();
		} finally {
			try {
				channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// register();

			channel.shutdown();
		}

	}

	private static class RegistrationListener implements ServiceListener {
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

	public static void clientJMDNS() {

		try {
			// Create a JmDNS instance
			JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
			System.out.println("Employee Registration program is being opened..");

			// Add a service listener
			jmdns.addServiceListener(host, new RegistrationListener());

			System.out.println("Please wait the moment..");

			// Wait a bit
			Thread.sleep(20000);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
}
