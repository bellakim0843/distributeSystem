package client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import grpc.examples.employeeRegistration.RegistrationClient;
import grpc.examples.employeeRegistration.loginRequest;
import grpc.examples.employeeRegistration.loginResponse;
import grpc.examples.realtimeChat.chatClient;
import grpc.examples.securityDeviceCheck.checkClient;

public class guiClient {

	public static void main(String[] args) throws Exception {

		login();

	}

	public static void login() {

		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50050).usePlaintext().build();

		try {
			/* GUI */

			/* When you test the code, id must match with password */
			JTextField id = new JTextField(); // Id accepts only numeric value.

			JTextField name = new JTextField();

			JPasswordField pw = new JPasswordField();// pw accepts only same value with id

			Object[] message = { "Employee ID:", id, "Employee Name:", name, "Password:", pw };

			int option = JOptionPane.showConfirmDialog(null, message, "Login", JOptionPane.OK_CANCEL_OPTION);

			String idText = (id.getText());
			System.out.println(idText);
			String nameText = (name.getText());
			System.out.println(nameText);
			String pwText = new String(pw.getPassword());
			System.out.println(pwText);

			// ID & Password Authentification. If it doesn't match, the system will be
			// quitted.
			if (option == JOptionPane.OK_OPTION) {
				if (!idText.equals(pwText)) {
					JOptionPane.showMessageDialog(null, "Username and Password mismatch ");
					System.exit(option);
				} else if (idText.matches("[^\\d]")) {
					JOptionPane.showMessageDialog(null, "Please input the numeric value in the employee ID!");
					System.exit(option);
				}

				loginRequest req = loginRequest.newBuilder().setEmpNo(Integer.parseInt(idText)).setEmpName(nameText)
						.build();

				loginResponse response = loginResponse.newBuilder().setResponseMessage("Welcome! " + req.getEmpNo())
						.setConfirm(req.getEmpName() + "\nLog-in Completed!").build();

				JOptionPane.showConfirmDialog(null, response.getResponseMessage() + "\n" + response.getConfirm());
				System.out.println(response.getResponseMessage() + "\n" + response.getConfirm());

				JFrame frame = new JFrame();

				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setSize(400, 350);
				frame.setResizable(false);

				JPanel loginBar = new JPanel();
				loginBar.setBackground(Color.decode("#ACD1E9"));
				loginBar.setSize(400, 30);

				JLabel loginLabel = new JLabel();
				loginLabel.setText("Welcome! empID: " + idText);
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

				/* If user clicks the button, each method will be called! */
				registrationButton.addActionListener(e -> {

					//RegistrationClient.clientJMDNS();
					RegistrationClient.login();
					RegistrationClient.register();
				});

				deviceCheckButton.addActionListener(e -> {

					try {
						checkClient.securityDeviceCheck();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				});

				chatButton.addActionListener(e -> {

					chatClient.realtimeChat(nameText);

				});

				logoutButton.addActionListener(e -> {

					frame.dispose();
					System.exit(0);
				});

			}

			Thread.sleep(new Random().nextInt(1000) + 500);

		} catch (RuntimeException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		channel.shutdown();

	}

}
