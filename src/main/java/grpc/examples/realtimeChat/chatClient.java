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
import java.util.logging.Logger;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceInfo;
import javax.jmdns.ServiceListener;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
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

public class chatClient {

	private static Logger logger = Logger.getLogger(chatClient.class.getName());

	private static chatGrpc.chatStub asyncStubChat;

	/*
	 * I implemented JMDNS in client side successfully and it worked. But the thing
	 * is, when i call JMDNS Method, I can't get message from server. And all the
	 * other services have same issue. So I decided to use server side JMDNS only in
	 * this code. And client side host and port value will be set in static value.
	 **/

	static String host = "localhost";
	static String myhost;
	static int port = 60011;
	static String resolvedIP;

	public static void main(String[] args) throws Exception {

	}

	/* This method will be received argument from guiClient class */
	public static void realtimeChat(String str) {

		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 60011).usePlaintext().build();

		asyncStubChat = chatGrpc.newStub(channel);

		/* Set the chat client's name as employee name */
		String name = str;

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

		/*
		 * The StreamObserver<ClientSideChat> object is used to handle the server's
		 * responses and the StreamObserver<ServerSideChat> object is used to send
		 * requests to the server.
		 */
		StreamObserver<ClientSideChat> responseObserver = new StreamObserver<ClientSideChat>() {

			@Override
			public void onNext(ClientSideChat value) {
				StringBuilder sb = new StringBuilder();
				sb.append(value.getMessage());
				clientChatBox.append(sb.toString());
				// printout the chat detail in the textarea and console.
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

		/*
		 * The requestObserver object is created by calling the realtimeChat() method on
		 * the asyncStubChat object. This method returns a
		 * StreamObserver<ServerSideChat> object that can be used to send messages to
		 * the server.
		 */
		StreamObserver<ServerSideChat> requestObserver = asyncStubChat.realtimeChat(responseObserver);

		try {
			/* If user clicks the button, it direct to the action. */
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
				// If user click the quit button, Observer will be finished.
				requestObserver.onCompleted();
				chatFrame.dispose();

			});

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

		chatBlockingStub blockingStubChat = chatGrpc.newBlockingStub(channel);

		/* Using JFileChooser to implement file upload */
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		int result = fileChooser.showOpenDialog(fileChooser);

		/* if user click choose the file, the name of file set as the message */
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

	public static void chatJMDNS() {

		try {

			// Create a JmDNS instance
			JmDNS jmdnsChat = JmDNS.create();
			// Put the service information in the serviceInfo[] array.
			ServiceInfo[] chatServices = jmdnsChat.list("_chat._tcp.local.");
			// If service is null, this message will be printed out.
			if (chatServices.length == 0) {
				System.out.println("There is no gRPC server here!");
				return;
			}

			// Initianlize static host and port
			ServiceInfo serviceInfo = chatServices[0];
			host = serviceInfo.getHostAddresses()[0];
			port = serviceInfo.getPort();

			System.out.println("JmDNS is started!..");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
