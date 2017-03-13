import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
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
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class ServerGUI {
	JFrame frame;
	JPanel mainPanel;
	JLabel ipLabel, headLabel, userNameLabel;
	JTextField ipTextField, userNameTextField;
	JButton connectButton, startButton;

	Socket sock;
	String username, serverIP;
	int port = 8090;
	PrintWriter writer;
	BufferedReader reader;
	Boolean isConnected = false;

	ArrayList clientOutputStreams;
	ArrayList<String> onlineUsers;

	public ServerGUI() {

	}

	public void guiServer() {
		frame = new JFrame();

		mainPanel = new JPanel(new GridLayout(0,1));

		mainPanel.setVisible(true);
		mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		ipLabel = new JLabel("Enter your IP address to connect");
		mainPanel.add(ipLabel);
		mainPanel.setVisible(true);
		ipTextField = new JTextField(20);
		mainPanel.add(ipTextField);
		mainPanel.setVisible(true);
		

		userNameLabel = new JLabel("Username: ");
		mainPanel.add(userNameLabel);
		mainPanel.setVisible(true);
		userNameTextField = new JTextField(20);
		mainPanel.add(userNameTextField);
		mainPanel.setVisible(true);
		
		connectButton = new JButton("Connect");
		mainPanel.add(connectButton);
		mainPanel.setVisible(true);
		connectButton.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				startButtonActionPerformed(evt);
			}

		});
		
		startButton = new JButton("Start Chatting");
		mainPanel.add(startButton);
		mainPanel.setVisible(true);
		connectButton.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				connectButtonActionPerformed(evt);
			}

		});


		frame.add(mainPanel);
		frame.setLayout(new GridLayout(0, 1));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Connect with IP");
		frame.setSize(new Dimension(300, 200));
		frame.setVisible(true);
	}

	private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {
		Thread starter = new Thread(new ServerStart());
		starter.start();

		System.out.println("Server started. \n");
	}

	public class ServerStart implements Runnable {
		public void run() {
			clientOutputStreams = new ArrayList();
			onlineUsers = new ArrayList();

			try {
				ServerSocket serverSock = new ServerSocket(8090);

				while (true) {
					// set up the server writer function and then begin at the
					// same
					// the listener using the Runnable and Thread
					Socket clientSock = serverSock.accept();
					PrintWriter writer = new PrintWriter(clientSock.getOutputStream());
					clientOutputStreams.add(writer);

					// use a Runnable to start a 'second main method that will
					// run
					// the listener
					Thread listener = new Thread(new ClientHandler(clientSock, writer));
					listener.start();
					System.out.println("Got a connection. \n");
				} // end while
			} // end try
			catch (Exception ex) {
				System.out.println("Error making a connection. \n");
			} // end catch

		} // end go()
	}
	
	private void connectButtonActionPerformed(java.awt.event.ActionEvent evt) {                                              
        // TODO add your handling code here:
            if (isConnected == false) {
            username = userNameTextField.getText();
            serverIP = ipTextField.getText();
            userNameTextField.setEditable(false);

            try {
                sock = new Socket(serverIP, port);
                InputStreamReader streamreader = new InputStreamReader(sock.getInputStream());
                reader = new BufferedReader(streamreader);
                writer = new PrintWriter(sock.getOutputStream());
                writer.println(username + ":has connected.:Connect"); // Displays to everyone that user connected.
                writer.flush(); // flushes the buffer
                isConnected = true; // Used to see if the client is connected.
            } catch (Exception ex) {
                System.out.println("Cannot Connect! Try Again. \n");
                userNameTextField.setEditable(true);
            }
            //ListenThread();
            //Thread IncomingReader = new Thread(new IncomingReader());
            Thread IncomingReader = new Thread();
	         IncomingReader.start();
        } else if (isConnected == true) {
            System.out.println("You are already connected. \n");
        }
    } 

	public class ClientHandler implements Runnable {
		BufferedReader reader;
		Socket sock;
		PrintWriter client;

		public ClientHandler(Socket clientSocket, PrintWriter user) {
			// new inputStreamReader and then add it to a BufferedReader
			client = user;
			try {
				sock = clientSocket;
				InputStreamReader isReader = new InputStreamReader(sock.getInputStream());
				reader = new BufferedReader(isReader);
			} // end try
			catch (Exception ex) {
				System.out.println("Error beginning StreamReader. \n");
			} // end catch

		} // end ClientHandler()

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

				} // end while
			} // end try
			catch (Exception ex) {
				System.out.println("Lost a connection. \n");
				ex.printStackTrace();
				clientOutputStreams.remove(client);
			} // end catch
		} // end run()
	} // end class ClientHandler

	public void tellEveryone(String message) {
		// sends message to everyone connected to server
		Iterator it = clientOutputStreams.iterator();

		while (it.hasNext()) {
			try {
				PrintWriter writer = (PrintWriter) it.next();
				writer.println(message);
				System.out.println("Sending: " + message + "\n");
				writer.flush();
			} // end try
			catch (Exception ex) {
				System.out.println("Error telling everyone. \n");
			} // end catch
		} // end while
	}

	public void userAdd(String data) {
		String message, add = ": :Connect", done = "Server: :Done", name = data;
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
