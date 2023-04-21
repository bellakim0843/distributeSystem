package client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

//required java packages for the program. Depends on your logic.

import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

//required grpc package for the client side
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import grpc.examples.employeeRegistration.RegistrationClient;
import grpc.examples.employeeRegistration.loginRequest;
import grpc.examples.employeeRegistration.loginResponse;
import grpc.examples.realtimeChat.chatClient;
import grpc.examples.securityDeviceCheck.checkClient;

//Client need not to extend any other class (GRPC related code) here 
public class guiClient {
	// First we create a logger to show client side logs in the console. logger
	// instance will be used to log different events at the client console.
	// This is optional. Could be used if needed.

	// Creating stubs for establishing the connection with server.
	// Blocking stub
//
//	static String host = "_grpc._tcp.local.";//
//	static String myhost = "localhost";
//	static int JMDNSport = 50050; // 여기 포트 그대로 쓸 것 local host + port 넘버 입력. 나머지는 놔둘
//	static String resolvedIP;

	// The main method will have the logic for client.
	public static void main(String[] args) throws Exception {
		// First a channel is being created to the server from client. Here, we provide
		// the server name (localhost) and port (50055).
		// As it is a local demo of GRPC, we can have non-secured channel
		// (usePlaintext).

		// Unary RPC call

		login();

		// passing an empty message - no server reply, error message

	}

	public static void login() {

		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50050).usePlaintext().build();

		RegistrationClient.login();

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
				loginBar.setSize(400, 30);

				JLabel loginLabel = new JLabel();
				loginLabel.setText("Welcome! ID: " + idText + " Name: " + nameText);
				loginBar.add(loginLabel);
				loginLabel.setFont(new Font("Verdana", Font.BOLD, 14));
				frame.add(loginBar, BorderLayout.NORTH);

				JPanel namePanel1 = new JPanel(null);
				namePanel1.setSize(400, 200);
				namePanel1.setBackground(Color.decode("#ACD1E9"));

				String workShiftText = "Work-Shift\nRegistration";
				JButton registrationButton = new JButton(
						"<html>" + workShiftText.replaceAll("\\n", "<br>") + "</html>");
				registrationButton.setBounds(10, 20, 120, 120);

				String deviceCheckText = "Security" + "\n" + "&nbsp;Device" + "\n" + "&nbsp;&nbsp;&nbsp;List";
				JButton deviceCheckButton = new JButton(
						"<html>" + deviceCheckText.replaceAll("\\n", "<br>") + "</html>");
				deviceCheckButton.setBounds(140, 20, 120, 120);

				String chatText = "Real-Time" + "\n" + "&nbsp;&nbsp;&nbsp;Chat";
				JButton chatButton = new JButton("<html>" + chatText.replaceAll("\\n", "<br>") + "</html>");
				chatButton.setBounds(270, 20, 120, 120);

				JButton logoutButton = new JButton("Close");
				logoutButton.setBounds(150, 150, 100, 40);

				namePanel1.add(registrationButton);
				namePanel1.add(deviceCheckButton);
				namePanel1.add(chatButton);
				namePanel1.add(logoutButton);

				frame.add(namePanel1);

				frame.setVisible(true);

				registrationButton.addActionListener(e -> {

					RegistrationClient.register();
				});

				deviceCheckButton.addActionListener(e -> {

					checkClient.securityDeviceCheck();
				});

				chatButton.addActionListener(e -> {

					chatClient.realtimeChat();

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

}
