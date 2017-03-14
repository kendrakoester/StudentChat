import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class ServerGUI {
	JFrame frame;
	JPanel mainPanel;
	JLabel headLabel;
	static JTextArea connectTextArea;
	JButton startButton, stopButton;

	
	PrintWriter writer;
	BufferedReader reader;
	Boolean isConnected = false;

	ArrayList<PrintWriter> clientOutputStreams;
	ArrayList<String> onlineUsers;

	public ServerGUI() {

	}

	public void guiServer() {
		frame = new JFrame();

		mainPanel = new JPanel();

		mainPanel.setVisible(true);
		mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		startButton = new JButton("Start Server");
		mainPanel.add(startButton);
		mainPanel.setVisible(true);
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				startButtonActionPerformed();
			}

		});
		
		connectTextArea = new JTextArea(300,200);
		mainPanel.add(connectTextArea);
		mainPanel.setVisible(true);
		
		frame.add(mainPanel);
		frame.setLayout(new GridLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Connect with IP");
		frame.setSize(new Dimension(300, 300));
		frame.setVisible(true);
	}

	private void startButtonActionPerformed() {
		Thread starter = new Thread(new ServerStart());
		starter.start();

		System.out.println("Server started. \n");
	}

	public class ServerStart implements Runnable {
		public void run() {
			clientOutputStreams = new ArrayList<PrintWriter>();
			onlineUsers = new ArrayList<String>();

			try {
				ServerSocket serverSock = new ServerSocket(8090);

				while (true) {

					Socket clientSock = serverSock.accept();
					PrintWriter writer = new PrintWriter(clientSock.getOutputStream());
					clientOutputStreams.add(writer);

					Thread listener = new Thread(new ClientHandler(clientSock, writer));
					listener.start();
					System.out.println("Got a connection. \n");
				} 
			} 
			catch (Exception ex) {
				System.out.println("Error making a connection. \n");
			} 

		} 
	}
	
	public class ClientHandler implements Runnable {
		BufferedReader reader;
		Socket sock;
		PrintWriter client;

		public ClientHandler(Socket clientSocket, PrintWriter user) {
			client = user;
			try {
				sock = clientSocket;
				InputStreamReader isReader = new InputStreamReader(sock.getInputStream());
				reader = new BufferedReader(isReader);
			} 
			catch (Exception ex) {
				System.out.println("Error beginning StreamReader. \n");
			} 

		} 

		public void run() {
			String message, connect = "Connect", disconnect = "Disconnect", chat = "Chat";
			String[] data;

			try {
				while ((message = reader.readLine()) != null) {

					System.out.println("Received: " + message + "\n");
					data = message.split(":");
					for (String token : data) {

						System.out.println(token + "\n");

					}

					if (data[2].equals(connect)) {

						tellEveryone((data[0] + ":" + data[1] + ":" + chat));
						userAdd(data[0]);

					} else if (data[2].equals(disconnect)) {

						tellEveryone((data[0] + ":has disconnected." + ":" + chat));
						userRemove(data[0]);

					} else if (data[2].equals(chat)) {

						tellEveryone(message);

					} else {
						System.out.println("No Conditions were met. \n");
					}

				} 
			}
			catch (Exception ex) {
				System.out.println("Lost a connection. \n");
				ex.printStackTrace();
				clientOutputStreams.remove(client);
			}
		}
	} 

	public void tellEveryone(String message) {
		Iterator<PrintWriter> it = clientOutputStreams.iterator();

		while (it.hasNext()) {
			try {
				PrintWriter writer = (PrintWriter) it.next();
				writer.println(message);
				//GUIClient.chatTextArea.repaint();
				connectTextArea.setText("Sending: " + message + "\n");
				writer.flush();
			} 
			catch (Exception ex) {
				System.out.println("Error telling everyone. \n");
			}
		}
	}

	public void userAdd(String data) {
		String message, add = ": :Connect", done = "User added, start chatting", name = data;
		System.out.println("Before " + name + " added. \n");
		onlineUsers.add(name);
		System.out.println("After " + name + " added. \n");
		String[] tempList = new String[(onlineUsers.size())];
		onlineUsers.toArray(tempList);

		for (String token : tempList) {

			message = (token + add);
			tellEveryone(message);
		}
		tellEveryone(done);
	}

	public void userRemove(String data) {
		String message, add = ": :Connect", done = "Server: :Done", name = data;
		onlineUsers.remove(name);
		String[] tempList = new String[(onlineUsers.size())];
		onlineUsers.toArray(tempList);

		for (String token : tempList) {

			message = (token + add);
			tellEveryone(message);
		}
		tellEveryone(done);
	}
	
}
