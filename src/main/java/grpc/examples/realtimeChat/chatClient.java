package grpc.examples.realtimeChat;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
//required java packages for the program. Depends on your logic.
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import grpc.examples.realtimeChat.*;
//required grpc package for the client side
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;

//Client need not to extend any other class (GRPC related code) here
public class chatClient {
	// First we create a logger to show client side logs in the console. logger instance will be used to log different events at the client console.
		// This is optional. Could be used if needed.
	private static  Logger logger = Logger.getLogger(chatClient.class.getName());

	// Creating stubs for establishing the connection with server.
				// Blocking stub
	private static chatGrpc.chatBlockingStub blockingStub;
	// Asynch stub
	private static chatGrpc.chatStub asyncStub;
	
	// The main method will have the logic for client.
	public static void main(String[] args) throws Exception {
		// First a channel is being created to the server from client. Here, we provide the server name (localhost) and port (50058).
		// As it is a local demo of GRPC, we can have non-secured channel (usePlaintext).
		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 60012).usePlaintext().build();

		//stubs -- generate from proto
		blockingStub = chatGrpc.newBlockingStub(channel);
		asyncStub = chatGrpc.newStub(channel);

		//bidirectional streaming
		realtimeChat();

		// Closing the channel once message has been passed.		
		channel.shutdown();

	}


//	public static void realtimeChat() {
//		
//		Scanner sc = new Scanner(System.in);
//		System.out.println("Username");
//		String name = sc.nextLine();
//
//		// Handling the server stream for client using onNext (logic for handling each message in stream), onError, onCompleted (logic will be executed after the completion of stream)
//		StreamObserver<ClientSideChat> responseObserver = new StreamObserver<ClientSideChat>() {
//
//			@Override
//			public void onNext(ClientSideChat value) {
//
//				System.out.println(name+": " + value.getMessage());
//
//			}
//
//			@Override
//			public void onError(Throwable t) {
//				// TODO Auto-generated method stub
//
//			}
//
//			@Override
//			public void onCompleted() {
//				// TODO Auto-generated method stub
//				System.out.println("server completed");
//			}
//
//
//
//		};
//
//		// Here, we are calling the Remote reverseStream method. Using onNext, client sends a stream of messages.
//		StreamObserver<ServerSideChat> requestObserver = asyncStub.realtimeChat(responseObserver);
//
//
//		
//try {
//			
//			for(int i =0; i<4; i++) {
//				
//			System.out.println("Input");
//			String myText = sc.nextLine();
//		    requestObserver.onNext(ServerSideChat.newBuilder().setMessage(myText).build());
//		
//			
//			}
//
//
//			System.out.println("SENDING EMSSAGES");
//
//			// Mark the end of requests
//			requestObserver.onCompleted();
//
//
//			// Sleep for a bit before sending the next one.
//			Thread.sleep(new Random().nextInt(1000) + 500);
//
//
//		} catch (RuntimeException e) {
//			e.printStackTrace();
//		} catch (InterruptedException e) {			
//			e.printStackTrace();
//		}
//
//
//	}
	
	public static void realtimeChat() {

		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 60012).usePlaintext().build();

		// stubs -- generate from proto
		asyncStub = chatGrpc.newStub(channel);
		
		

//		Scanner sc = new Scanner(System.in);
		// System.out.println("Username");
//		String name = JOptionPane.showInputDialog("Please input your name.");
		
		String name = "Kim";

		JFrame chatFrame = new JFrame();

		chatFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		chatFrame.setSize(500, 490);
		chatFrame.setResizable(true);

		JLabel chatLabel = new JLabel("Real-Time Chat - User: "+name);
		chatLabel.setFont(new Font("Verdana", Font.BOLD, 17));

		JPanel subjectBar = new JPanel();
		subjectBar.setSize(new Dimension(500, 40));
		subjectBar.setBackground(Color.pink);

		subjectBar.add(chatLabel);
		chatFrame.add(subjectBar);

		JPanel namePanel1 = new JPanel();
		GridBagLayout layout = new GridBagLayout();
		namePanel1.setLayout(layout);
		namePanel1.setPreferredSize(new Dimension(500, 350));
		namePanel1.setBackground(Color.CYAN);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(15, 5, 0, 5);



		JTextArea clientChatBox = new JTextArea();
		clientChatBox.setPreferredSize(new Dimension(425, 300));
		clientChatBox.setLineWrap(true);
		clientChatBox.setWrapStyleWord(true);
		clientChatBox.setEnabled(false);


		JTextField clientChatInput = new JTextField(null);
		clientChatInput.setPreferredSize(new Dimension(425, 30));
		clientChatInput.setBackground(Color.ORANGE);



		JButton clientChatButton = new JButton("Confirm");
		
		JButton quitButton = new JButton("Quit");


		gbc.gridx = 0;
		gbc.gridy = 0;
		namePanel1.add(clientChatBox, gbc);



		gbc.gridx = 0;
		gbc.gridy = 1;
		namePanel1.add(clientChatInput, gbc);

		chatFrame.add(namePanel1);
		chatFrame.setVisible(true);

		JPanel namePanel2 = new JPanel();
		namePanel2.setPreferredSize(new Dimension(500, 40));
		namePanel2.setBackground(Color.yellow);
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
		
		StringBuilder sb = new StringBuilder();

		// Handling the server stream for client using onNext (logic for handling each
		// message in stream), onError, onCompleted (logic will be executed after the
		// completion of stream)
		StreamObserver<ClientSideChat> responseObserver = new StreamObserver<ClientSideChat>() {
			
			//StringBuilder chatFinal = new StringBuilder();
			@Override
			public void onNext(ClientSideChat value) {
				System.out.println(value.getMessage());
				sb.append(value.getMessage());
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
		StreamObserver<ServerSideChat> requestObserver = asyncStub.realtimeChat(responseObserver);


			try {

				clientChatButton.addActionListener(e -> {
					
					
					
				//	System.out.println("Input");
				String myText = clientChatInput.getText();

					requestObserver.onNext(ServerSideChat.newBuilder().setMessage(name+": "+myText).build());

					System.out.println("SENDING EMSSAGES");
				});
				

	

				
				//종료 버튼 만들어서 버튼누르면 completed 되게 만들
				// Mark the end of requests

				
				quitButton.addActionListener(e -> {
				
						requestObserver.onCompleted();
						chatFrame.dispose();
					

					});
				
				

		

				// Sleep for a bit before sending the next one.
				//Thread.sleep(new Random().nextInt(1000) + 500);
			
			}

			catch (RuntimeException d) {
				d.printStackTrace();
//			} catch (InterruptedException d) {
//				d.printStackTrace();
//			}
			}


	}


}
