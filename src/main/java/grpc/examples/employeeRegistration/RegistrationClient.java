package grpc.examples.employeeRegistration;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.net.InetAddress;
//required java packages for the program. Depends on your logic.
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;

//required grpc package for the client side
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;

import grpc.examples.employeeRegistration.userGrpc.userBlockingStub;
//This is to include rpc method enum message
import grpc.examples.employeeRegistration.userGrpcClient.userStub;
import grpc.examples.employeeRegistration.userGrpc.userBlockingStub;

//Client need not to extend any other class (GRPC related code) here 
public class RegistrationClient {
	// First we create a logger to show client side logs in the console. logger
	// instance will be used to log different events at the client console.
	// This is optional. Could be used if needed.
	private static Logger logger = Logger.getLogger(RegistrationClient.class.getName());

	// Creating stubs for establishing the connection with server.
	// Blocking stub
	// private static userGrpcClient.userBlockingStub blockingStub;
	// Asynch stub
	private static userStub asyncStub;
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

		// testClientJMDNS();
		register();

		// passing an empty message - no server reply, error message

	}

	// unary rpc
	public static void register() {

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
				System.out.println("Workshift Registration is totally completed!");

			}

		};

		// Here, we are calling the Remote length method. Using onNext, client sends a
		// stream of messages.
		StreamObserver<employee> requestObserver = asyncStub.register(responseObserver);

		try {
			/* GUI */

			JTextField id = new JTextField();

			JTextField name = new JTextField();

			Object[] message = { "Employee ID:", id, "Employee Name:", name };

			int option = JOptionPane.showConfirmDialog(null, message, "Login", JOptionPane.OK_CANCEL_OPTION);

			if (option == JOptionPane.OK_OPTION) {
				String idText = (id.getText());
				System.out.println(idText);
				String nameText = name.getText();

				System.out.println(nameText);

				loginRequest loginrequeset = loginRequest.newBuilder().setEmpNo(Integer.parseInt(idText))
						.setEmpName(nameText).build();
				loginResponse reponse = loginResponse.newBuilder()
						.setConfirm("Login Completed! EmpNo: " + loginrequeset.getEmpNo())
						.setResponseMessage(" Welcome! " + loginrequeset.getEmpName()).build();
				JOptionPane.showConfirmDialog(null, reponse.getResponseMessage());

				JFrame frame = new JFrame();

				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setSize(500, 350);
				frame.setResizable(false);

				JPanel loginBar = new JPanel();
				loginBar.setBackground(Color.red);
				loginBar.setPreferredSize(new Dimension(500, 30));

				JLabel loginLabel = new JLabel();
				loginLabel.setText("Welcome! ID: " + idText + " Name: " + nameText);
				loginBar.add(loginLabel);
				loginLabel.setFont(new Font("Verdana", Font.BOLD, 14));
				frame.add(loginBar, BorderLayout.NORTH);

				JPanel namePanel1 = new JPanel();
				GridBagLayout layout = new GridBagLayout();
				namePanel1.setLayout(layout);
				namePanel1.setBackground(Color.CYAN);
				namePanel1.setPreferredSize(new Dimension(500, 80));
				GridBagConstraints gbc = new GridBagConstraints();
				gbc.insets = new Insets(5, 5, 5, 5);

				JPanel namePanel2 = new JPanel();
				namePanel2.setPreferredSize(new Dimension(500, 200));

				namePanel2.setBackground(Color.magenta);
				frame.add(namePanel2, BorderLayout.SOUTH);

				JButton registrationButton = new JButton("Check Work-Shift");
				JButton logoutButton = new JButton("Close");

				gbc.fill = GridBagConstraints.HORIZONTAL;
				gbc.gridx = 0;
				gbc.gridy = 0;
				namePanel1.add(registrationButton, gbc);

				gbc.gridx = 1;
				gbc.gridy = 0;
				namePanel1.add(logoutButton, gbc);

				JTextArea list = new JTextArea("====Today's Workshift List===\n");
				list.setPreferredSize(new Dimension(400, 500));
				list.setLineWrap(true);
				list.setWrapStyleWord(true);

				JScrollPane scrollPane = new JScrollPane(list);

				scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
				scrollPane.setEnabled(true);
				scrollPane.setPreferredSize(new Dimension(400, 150));

				frame.getContentPane().add(scrollPane);

				namePanel2.add(scrollPane);

				StringBuilder total = new StringBuilder();
				total.append("====Today's Workshift List===\n");

				int number = Integer.parseInt(JOptionPane.showInputDialog("How many Shift will you register?"));

				for (int i = 0; i < number; i++) {

					int a = Integer.parseInt(JOptionPane.showInputDialog("Input employee's ID"));
					String b = JOptionPane.showInputDialog("Input employee's Name");
					int c = Integer.parseInt(JOptionPane.showInputDialog("Input employee'shift "));

					total.append("\n============");
					total.append("\n ID: " + a + "\n Name: " + b + "\n Shift: " + c);

					requestObserver.onNext(employee.newBuilder().setEmpNo(a).setEmpName(b).setShift(c).build());

				}

				registrationButton.addActionListener(e -> {

					list.setText(total.toString());

				});

				logoutButton.addActionListener(e -> {

					frame.dispose();
				});

				frame.add(namePanel1);

				frame.setVisible(true);

			}

			System.out.println("Registration is finished successfully!");

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

	public static void login() {

		register();

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

	public static void clientJMDNS() {

		try {
			// Create a JmDNS instance
			JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
			System.out.println("Service Registration..");

			// Add a service listener
			jmdns.addServiceListener(host, new SampleListener());

			System.out.println("Service Registration..");

			// Wait a bit
			Thread.sleep(20000);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50050).usePlaintext().build();

		// stubs -- generate from proto

		asyncStub = userGrpcClient.newStub(channel);
		userBlockingStub blockingStubNew = userGrpc.newBlockingStub(channel);
		// Unary RPC call

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
		}

		// Closing the channel once message has been passed.
		channel.shutdown();

	}
}
