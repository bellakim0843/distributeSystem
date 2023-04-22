package grpc.examples.securityDeviceCheck;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import io.grpc.Deadline;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

public class checkClient {

	private static Logger logger = Logger.getLogger(checkClient.class.getName());

	private static userGrpc.userBlockingStub blockingStubCheck;

	/*
	 * I implemented JMDNS in successfully and it worked. But the thing is, when i
	 * call JMDNS Method, I can't get message from server. And all the other
	 * services have same issue. So I decided to use server side JMDNS only in this
	 * code. And client side host and port value will be set in static value.
	 **/

	JFrame frameToPrint;
	static String host = "localhost";
	static int port = 50056;
	static String resolvedIP;

	public static void main(String[] args) throws Exception {

		// serverJMDNS();
//		System.out.println(host);
//		System.out.println(port);

		securityDeviceCheck();


	}

	public static void securityDeviceCheck() {

		ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();

		// Create blockingStub to react server side's message.
		blockingStubCheck = userGrpc.newBlockingStub(channel);

		int totalNumber = 0;

		/*
		 * We're using Protocol Buffers to sent the Massdata to the server.
		 * I implemented dataset() as method.
		 */
		deviceRequest request = deviceRequest.newBuilder().setMassData(dataSet()).build();

		try {
		
			/*The Iterator<deviceList> creates an iterator over the stream of deviceList messages 
			 * received from the server.*/
			Iterator<deviceList> responses = blockingStubCheck.deviceCheck(request);
			
			/*GUI*/
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

			JButton printButton = new JButton("Print");

			JButton closeButton = new JButton("Close");

			gbc.gridx = 0;
			gbc.gridy = 0;
			namePanel2.add(printButton, gbc);

			gbc.gridx = 1;
			gbc.gridy = 0;
			namePanel2.add(closeButton, gbc);

			deviceFrame.add(namePanel1);

			deviceFrame.setVisible(true);

			/*Append device list in the textarea in order */
			while (responses.hasNext()) {
				deviceList temp = responses.next();
				System.out.println(temp.getVal());
				list.append(temp.getVal() + "\n=============================");
				totalNumber++;
			}
			/*Indicate total number of the devices */
			list.append("\nTotal " + totalNumber + " devices are in the company.");

			/*If users click the button, they can print out the list of the devices
			 * I implemented with printer class in same package. */
			printButton.addActionListener(e -> {
				printOut();
				printer pp = new printer(deviceFrame);
				pp.print(list.getText());

			});

			closeButton.addActionListener(e -> {
				deviceFrame.dispose();
			});

		} catch (StatusRuntimeException e) {
			e.printStackTrace();
		}

		channel.shutdown();

	}

	// But when i call JMDNS method, all of the client side has error.
	// MAC OS has this kinds of error frequently.
	// I decided not to use JMDNS in server side streaming.
	public static void serverJMDNS() {

		try {

			// Create a JmDNS instance
			JmDNS jmdns = JmDNS.create();
			System.out.println("Security Devices List check program is being started!");
			// Put the service information in the serviceInfo[] array.
			ServiceInfo[] services = jmdns.list("_check._tcp.local.");
			// If service is null, this message will be printed out.
			if (services.length == 0) {
				System.out.println("There is no gRPC server here!");
				return;
			}
			// Initianlize static host and port, It is working.
			ServiceInfo serviceInfo = services[0];
			host = serviceInfo.getHostAddresses()[0];
			port = serviceInfo.getPort();
			// I succeded to get host value and port value from JMDNS
			System.out.println("JmDNS is started!..");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public static void printOut() {

		// Create a channel to communicate with the server.
		ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();

		// Create a blocking stub to communicate with the server.
		blockingStubCheck = userGrpc.newBlockingStub(channel);

		// Set the deadline for the request to 5 seconds.
		long deadlineMs = 5000L;

		// Create the request message.
		printRequest req = printRequest.newBuilder().setRequest("print requested").build();

		try {
			// Simulate a delay of 3 seconds before sending the response.
			Thread.sleep(3000);

			// Call the remote RPC method with the specified deadline.
			printResponse response = blockingStubCheck.withDeadline(Deadline.after(deadlineMs, TimeUnit.MILLISECONDS))
					.printOut(req);

			// Print the response from the server.
			System.out.println("Print Server " + response.toString());

		} catch (StatusRuntimeException ex) {
			// Print an error message if the RPC call fails.
			System.out.println(ex.getMessage());
		} catch (InterruptedException ex) {
			// Print an error message if the thread is interrupted while sleeping.
			System.out.println(ex.getMessage());
		}

		// Shutdown the channel when the RPC call is complete.
		channel.shutdown();
	}

	public static String dataSet() {

		String data = "Device No: 1\nDevice Type: Security Camera\nDevice Code: AAA2020\nDevice Location: Warehouse-Front,"
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
				+ "\nDevice No: 15\nDevice Type: Iris recognition Controller\nDevice Code: EYE2023\nDevice Location: Executive room,"
				+ "\nDevice No: 16\nDevice Type: Thermal Imaging Camera\nDevice Code: SIP2023\nDevice Location: Security Device Cargo ,"
				+ "\nDevice No: 17\nDevice Type: Finger-Print Scanner \nDevice Code: FII2020\nDevice Location: Rear entrance,"
				+ "\nDevice No: 18\nDevice Type: Security Camera\nDevice Code: RTT2018\nDevice Location: Parking Lot, "
				+ "\nDevice No: 19\nDevice Type: Remote Controller\nDevice Code: QTT2023\nDevice Location: Control tower,"
				+ "\nDevice No: 20\nDevice Type: Security Camera\nDevice Code: SIP2022\nDevice Location: Cleaning room ,"
				+ "\nDevice No: 21\nDevice Type: Security Camera \nDevice Code: ACC2016\nDevice Location: Meeting room,"
				+ "\nDevice No: 22\nDevice Type: Security Camera\nDevice Code: OMM2019\nDevice Location: Lab, "
				+ "\nDevice No: 23\nDevice Type: Iris recognition Controller\nDevice Code: EYE2023\nDevice Location: Executive room,";

		return data;

	}

}
