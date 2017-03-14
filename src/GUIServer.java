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

public class GUIServer {
	/**
	 * barrowd code from a Youtube tutorial to get started
	 */
	
	ArrayList<PrintWriter> clientOutputStreams;
	ArrayList<String> onlineUsers;

	JFrame frame;
	JPanel mainPanel;
	JLabel headLabel;
	JTextArea connectTextArea;
	JButton startButton, stopButton;

	public GUIServer() {

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

		connectTextArea = new JTextArea(300, 200);
		mainPanel.add(connectTextArea);
		mainPanel.setVisible(true);

		frame.add(mainPanel);
		frame.setLayout(new GridLayout(0, 1));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Connect with IP");
		frame.setSize(new Dimension(300, 300));
		frame.setVisible(true);
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
				connectTextArea.append("Error beginning StreamReader. \n");
			} 

		}

		public void run() {
			String message, connect = "Connect", disconnect = "Disconnect", chat = "Chat";
			String[] data;

			try {
				while ((message = reader.readLine()) != null) {

					connectTextArea.append("Received: " + message + "\n");
					data = message.split(":");
					for (String token : data) {

						connectTextArea.append(token + "\n");

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
						connectTextArea.append("No Conditions were met. \n");
					}

				} 
			} 
			catch (Exception ex) {
				connectTextArea.append("Lost a connection. \n");
				ex.printStackTrace();
				clientOutputStreams.remove(client);
			} 
		} 
	} 

	private void startButtonActionPerformed() {
		Thread starter = new Thread(new ServerStart());
		starter.start();

		connectTextArea.append("Server started. \n");
	}

	@SuppressWarnings("unused")
	private void stopButtonActionPerformed() {

		tellEveryone("Server:is stopping and all users will be disconnected.\n:Chat");
		connectTextArea.append("Server stopping... \n");

	}

	public class ServerStart implements Runnable {
		public void run() {
			clientOutputStreams = new ArrayList<PrintWriter>();
			onlineUsers = new ArrayList<String>();

			try {
				@SuppressWarnings("resource")
				ServerSocket serverSock = new ServerSocket(8090);

				while (true) {
					Socket clientSock = serverSock.accept();
					PrintWriter writer = new PrintWriter(clientSock.getOutputStream());
					clientOutputStreams.add(writer);

					Thread listener = new Thread(new ClientHandler(clientSock, writer));
					listener.start();
					connectTextArea.append("Got a connection. \n");
				}
			} 
			catch (Exception ex) {
				connectTextArea.append("Error making a connection. \n");
			} 

		} 
	}

	public void userAdd(String data) {
		String message, add = ": :Connect", done = "Server: :Done", name = data;
		connectTextArea.append("Before " + name + " added. \n");
		onlineUsers.add(name);
		connectTextArea.append("After " + name + " added. \n");
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

	public void tellEveryone(String message) {
		Iterator<PrintWriter> it = clientOutputStreams.iterator();

		while (it.hasNext()) {
			try {
				PrintWriter writer = (PrintWriter) it.next();
				writer.println(message);
				connectTextArea.append("Sending: " + message + "\n");
				writer.flush();
				connectTextArea.setCaretPosition(connectTextArea.getDocument().getLength());

			} 
			catch (Exception ex) {
				connectTextArea.append("Error telling everyone. \n");
			} 
		} 
	} 
}
