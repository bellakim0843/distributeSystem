package grpc.examples.realtimeChat;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

//required grpc package for the server side
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;

//This is ImplBase class that was generated from the proto file.
//You need to change this location for your projects. NOTICE: The class is in StringsServiceGrpc.java -> StringsServiceImplBase class (this Base class is generated from proto file option java_outer_classname)
import grpc.examples.realtimeChat.chatGrpc.chatImplBase;

//Extend the ImplBase imported class here. It is an Interface file with required rpc methods
public class chatServer extends chatImplBase {

	// First we create a logger to show server side logs in the console. logger
	// instance will be used to log different events at the server console.
	private static final Logger logger = Logger.getLogger(chatServer.class.getName());

	// Main method would contain the logic to start the server. For throws keyword
	// refer https://www.javatpoint.com/throw-keyword
	// NOTE: THIS LOGIC WILL BE SAME FOR ALL THE TYPES OF SERVICES
	public static void main(String[] args) throws IOException, InterruptedException {

		// The StringServer is the current file name/ class name. Using an instance of
		// this class different methods could be invoked by the client.
		chatServer stringserver = new chatServer();

		// This is the port number where server will be listening to clients. Refer -
		// https://en.wikipedia.org/wiki/Port_(computer_networking)
		int port = 60011;

		// Here, we create a server on the port defined in in variable "port" and attach
		// a service "stringserver" (instance of the class) defined above.
		Server server = ServerBuilder.forPort(port) // Port is defined in line 34
				.addService(stringserver) // Service is defined in line 31
				.build() // Build the server
				.start(); // Start the server and keep it running for clients to contact.

		// Giving a logging information on the server console that server has started
		logger.info("Server started, listening on " + port);

		// Server will be running until externally terminated.
		server.awaitTermination();
	}

	// These RPC methods have been defined in the proto files. The interface is
	// already present in the ImplBase File.
//		NOTE - YOU MAY NEED TO MODIFY THIS LOGIC FOR YOUR PROJECTS BASED ON TYPE OF THE RPC METHODS 
	// For override Refer -
	// https://docs.oracle.com/javase/8/docs/api/java/lang/Override.html

	@Override
	public StreamObserver<ServerSideChat> realtimeChat(StreamObserver<ClientSideChat> responseObserver) {

//		JFrame chatFrame = new JFrame();
//
//		chatFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		chatFrame.setSize(500, 550);
//		chatFrame.setResizable(true);
//		
//		JLabel chatLabel = new JLabel("Real-Time Chat Application");
//		chatLabel.setFont(new Font("Verdana", Font.BOLD, 17));
//		
//		JPanel subjectBar = new JPanel();
//		subjectBar.setSize(new Dimension(500, 40));
//		subjectBar.setBackground(Color.pink);
//		
//
//		subjectBar.add(chatLabel);
//		chatFrame.add(subjectBar);
//
//		
//		JPanel namePanel1 = new JPanel();
//		GridBagLayout layout = new GridBagLayout();
//		namePanel1.setLayout(layout);
//		namePanel1.setPreferredSize(new Dimension(500, 550));
//		namePanel1.setBackground(Color.gray);
//		GridBagConstraints gbc = new GridBagConstraints();
//		gbc.insets = new Insets(5, 5, 5, 5);
//		
//		JTextArea serverChatBox = new JTextArea();
//		serverChatBox.setPreferredSize(new Dimension(425, 300));
//		serverChatBox.setLineWrap(true);
//		serverChatBox.setWrapStyleWord(true);
//		serverChatBox.setEnabled(false);
////		namePanel1.add(serverChat);
//
//		JTextArea clientChatBox = new JTextArea();
//		clientChatBox.setPreferredSize(new Dimension(425, 300));
//		clientChatBox.setLineWrap(true);
//		clientChatBox.setWrapStyleWord(true);
//		clientChatBox.setEnabled(false);
////		namePanel1.add(clientChat);
//		
////		JTextField serverChatInput = new JTextField("Input the message");
////		serverChatInput.setPreferredSize(new Dimension(425, 30));
////		serverChatInput.setBackground(Color.ORANGE);
//		
//		JTextField clientChatInput = new JTextField(null);
//		clientChatInput.setPreferredSize(new Dimension(425, 30));
//		clientChatInput.setBackground(Color.ORANGE);
//		
////		JButton serverChatButton = new JButton("Confirm");
//
//		
//		JButton clientChatButton = new JButton("Confirm");
//		
//		JButton quitButton = new JButton("Quit");
//		
//
//		gbc.fill = GridBagConstraints.HORIZONTAL;
////		gbc.gridx = 0;
////		gbc.gridy = 0;
////		namePanel1.add(serverChatBox, gbc);
//		
//		gbc.gridx = 0;
//		gbc.gridy = 0;
//		namePanel1.add(clientChatBox, gbc);
//		
////		gbc.gridx = 0;
////		gbc.gridy = 1;
////		namePanel1.add(serverChatInput, gbc);
//		
//		gbc.gridx = 0;
//		gbc.gridy = 1;
//		namePanel1.add(clientChatInput, gbc);
//		
////		gbc.gridx = 0;
////		gbc.gridy = 2;
////		namePanel1.add(serverChatButton, gbc);
//		
////		gbc.gridx =0;
////		gbc.gridy = 2;
////		namePanel1.add(clientChatButton, gbc);
//
//
//		JPanel namePanel2 = new JPanel();
//		namePanel2.setPreferredSize(new Dimension(500, 40));
//		namePanel2.setBackground(Color.yellow);
//		chatFrame.add(namePanel2, BorderLayout.SOUTH);
//		GridBagLayout layout2 = new GridBagLayout();
//		namePanel2.setLayout(layout2);
//		GridBagConstraints gbc2 = new GridBagConstraints();
//		gbc2.insets = new Insets(5, 5, 5, 5);
//		
//		gbc2.gridx = 0;
//		gbc2.gridy = 0;
//		namePanel2.add(clientChatButton, gbc2);
//
//		gbc2.gridx = 1;
//		gbc2.gridy = 0;
//		namePanel2.add(quitButton, gbc2);
//		
//		
//
//		chatFrame.add(namePanel1);
//		chatFrame.setVisible(true);

		return new StreamObserver<ServerSideChat>() {

			// For each message in the stream, get one stream at a time.
			// NOTE: YOU MAY MODIFY THE LOGIC OF onNext, onError, onCompleted BASED ON YOUR
			// PROJECT.
			@Override
			public void onNext(ServerSideChat request) {
				// In bidirectional stream, both server and client would be sending the stream
				// of messages.
				// Here, for each message in stream from client, server is sending back one
				// response.

				//Scanner sc = new Scanner(System.in);
				//System.out.println("CHAT HERE");
				//String serverChat = sc.nextLine();
				String serverChat = JOptionPane.showInputDialog(request.getMessage());


				ClientSideChat reply = ClientSideChat.newBuilder().setMessage("Server: " + serverChat + "\n").build();

				responseObserver.onNext(reply);


				// Preparing and sending the reply for the client. Here, response is build and
				// with the value (input1.toString()) computed by above logic.

			}

			@Override
			public void onError(Throwable t) {
				// TODO Auto-generated method stubal

			}

			@Override
			public void onCompleted() {

				responseObserver.onCompleted();

			}

		};
	}

}
