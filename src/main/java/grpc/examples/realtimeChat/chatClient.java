package grpc.examples.realtimeChat;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.File;
import java.net.InetAddress;
//required java packages for the program. Depends on your logic.
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Logger;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceInfo;
import javax.jmdns.ServiceListener;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import grpc.examples.realtimeChat.chatGrpc.chatBlockingStub;
//required grpc package for the client side
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;

//Client need not to extend any other class (GRPC related code) here
public class chatClient {

	private static Logger logger = Logger.getLogger(chatClient.class.getName());

	static String host = "_chat._tcp.local.";//
	static String myhost = "localhost";
	static int port = 60011; // 여기 포트 그대로 쓸 것 local host + port 넘버 입력. 나머지는 놔둘
	static String resolvedIP;

	// First we create a logger to show client side logs in the console. logger
	// instance will be used to log different events at the client console.
	// This is optional. Could be used if needed.

	// Creating stubs for establishing the connection with server.
	// Blocking stub
	private static chatGrpc.chatBlockingStub blockingStubChat;
	// Asynch stub
	private static chatGrpc.chatStub asyncStubChat;

	// The main method will have the logic for client.
	public static void main(String[] args) throws Exception {
		// First a channel is being created to the server from client. Here, we provide
		// the server name (localhost) and port (50058).
		// As it is a local demo of GRPC, we can have non-secured channel
		// (usePlaintext).

		// bidirectional streaming

		//realtimeChat();

		// Closing the channel once message has been passed.

	}

	public static void realtimeChat(String str) {

		clientJMDNS();

		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 60011).usePlaintext().build();

		// stubs -- generate from proto
		asyncStubChat = chatGrpc.newStub(channel);

		String name = str;
				//JOptionPane.showInputDialog("Please input your name.");

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

		JButton uploadButton = new JButton("Upload the file");

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
		namePanel2.add(uploadButton, gbc2);

		gbc2.gridx = 2;
		gbc2.gridy = 0;
		namePanel2.add(quitButton, gbc2);

		// Handling the server stream for client using onNext (logic for handling each
		// message in stream), onError, onCompleted (logic will be executed after the
		// completion of stream)
		StreamObserver<ClientSideChat> responseObserver = new StreamObserver<ClientSideChat>() {

			@Override
			public void onNext(ClientSideChat value) {
				StringBuilder sb = new StringBuilder();
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
				System.out.println(name + ": " + myText);
				clientChatBox.append(name + ": " + myText + "\n");
				clientChatInput.setText("");
			});

			uploadButton.addActionListener(e -> {

				uploadFile();

			});

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
		channel.shutdown();

	}

	public static void uploadFile() {

		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 60011).usePlaintext().build();

		// stubs -- generate from proto

		chatBlockingStub blockingStubChat = chatGrpc.newBlockingStub(channel);

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		int result = fileChooser.showOpenDialog(fileChooser);

		if (result == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();

			String fileName = selectedFile.getName();

			upload req = upload.newBuilder().setUploadRequest(fileName).build();
			System.out.println(req.toString());

			try {

				upload request = upload.newBuilder().setUploadRequest(fileName).build();

				uploadSuccess reply = blockingStubChat.fileUpload(request);
				System.out.println(reply.toString());
				System.out.println(fileName + " File uploaded completely!");
			} catch (StatusRuntimeException ex) {
				System.out.print(ex.getMessage());
			}

			channel.shutdown();

		}

	}

	private static class ChatListener implements ServiceListener {
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
			System.out.println("Real-time chat application is being opened..");

			// Add a service listener
			jmdns.addServiceListener(host, new ChatListener());

			System.out.println("Please wait the moment..");

			// Wait a bit
			Thread.sleep(20000);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
