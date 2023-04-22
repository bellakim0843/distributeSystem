package grpc.examples.securityDeviceCheck;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.net.InetAddress;
//required java packages for the program. Depends on your logic.
import java.util.Iterator;
import java.util.logging.Logger;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceInfo;
import javax.jmdns.ServiceListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
//required grpc package for the client side
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;


//Client need not to extend any other class (GRPC related code) here
public class checkClient {

	JFrame frameToPrint;
	static String host = "_check._tcp.local.";//
	static String myhost = "localhost";
	static int port = 50056; // 여기 포트 그대로 쓸 것 local host + port 넘버 입력. 나머지는 놔둘
	static String resolvedIP;
	// First we create a logger to show client side logs in the console. logger
	// instance will be used to log different events at the client console.
	// This is optional. Could be used if needed.
	private static Logger logger = Logger.getLogger(checkClient.class.getName());

	// Creating stubs for establishing the connection with server.
	// Blocking stub
	private static userGrpc.userBlockingStub blockingStubDevice;
	private static userGrpc.userBlockingStub blockingStubCheck;

	// The main method will have the logic for client.
	public static void main(String[] args) throws Exception {
		// First a channel is being created to the server from client. Here, we provide
		// the server name (localhost) and port (50057).
		// As it is a local demo of GRPC, we can have non-secured channel
		// (usePlaintext).

		// RPC call with Asynchronous stub

		// RPC call with Blocking stub
		securityDeviceCheck();

		// Closing the channel once message has been passed.

	}

	public static void securityDeviceCheck() {

		ManagedChannel channel = ManagedChannelBuilder.forAddress(myhost, port).usePlaintext().build();

		// stubs -- generate from proto
		blockingStubDevice = userGrpc.newBlockingStub(channel);

		serverJMDNS();

		int totalNumber = 0;
		// First creating a request message. Here, the message contains a string in
		// setVal
		deviceRequest request = deviceRequest.newBuilder().setMassData(dataSet()).build();

		// as this call is blocking. The client will not proceed until all the messages
		// in stream has been received.
		try {
			// Iterating each message in response when calling remote split RPC method.
			Iterator<deviceList> responses = blockingStubDevice.deviceCheck(request);

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

	
			// Client keeps a check on the next message in stream.
			while (responses.hasNext()) {
				deviceList temp = responses.next();
				System.out.println(temp.getVal());
				list.append(temp.getVal() + "\n=============================");
				totalNumber++;
			}

			list.append("\nTotal " + totalNumber + " devices are in the company.");
	
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

	private static class checkListener implements ServiceListener {
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

	public static void serverJMDNS() {

		try {
			// Create a JmDNS instance
			JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
			System.out.println("Security Devices List check program is being opened..");

			// Add a service listener
			jmdns.addServiceListener(host, new checkListener());

			System.out.println("Please wait the moment..");

			deviceRequest request = deviceRequest.newBuilder().setMassData("Data is transferring!").build();
			deviceList reply = deviceList.newBuilder().setVal(request.getMassData()).build();
			System.out.println(request.getMassData());

			// Wait a bit
			Thread.sleep(20000);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public static void printOut() {

		ManagedChannel channel = ManagedChannelBuilder.forAddress(myhost, port).usePlaintext().build();

		// stubs -- generate from proto
		// asyncStubChat = chatGrpc.newStub(channel);
		blockingStubCheck = userGrpc.newBlockingStub(channel);

		printRequest req = printRequest.newBuilder().setRequest("print requested").build();
		try {
			// Calling a remote RPC method using blocking stub defined in main method. req
			// is the message we want to pass.
			printResponse response = blockingStubCheck.printOut(req);
			// response contains the output from the server side. Here, we are printing the
			// value contained by response.
			System.out.println("Print Server " + response.toString());

		} catch (StatusRuntimeException ex) {
			// Print if any error/exception is generated.
			System.out.println(ex.getMessage());
		}

		channel.shutdown();

	}
	
	public static String dataSet() {
		
		String data = 	"Device No: 1\nDevice Type: Security Camera\nDevice Code: AAA2020\nDevice Location: Warehouse-Front,"
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
