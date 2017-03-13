import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


public class ServerGUI {
	JFrame frame;
	JPanel mainPanel, ipPanel, userPanel;
	JLabel ipLabel, headLabel, userNameLabel;
	JTextField ipTextField, userNameTextField;
	JButton connectButton;

	Socket sock;
	String username, serverIP;
	int port = 8090;
	PrintWriter writer;
	BufferedReader reader;
	Boolean isConnected = false;
	//IncomingReader IncomingReader;

	public ServerGUI() {

	}

	public void guiServer() {
		frame = new JFrame();
		
		mainPanel = new JPanel(new GridLayout(2,2));

		// ipPanel
		ipPanel = new JPanel();
		frame.add(ipPanel, BorderLayout.NORTH);
		ipPanel.setVisible(true);
		ipPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		headLabel = new JLabel("ServerGUI");
		ipPanel.add(headLabel);
		ipPanel.setVisible(true);
		ipLabel = new JLabel("Enter your IP address to connect");
		ipPanel.add(ipLabel);
		ipPanel.setVisible(true);
		ipTextField = new JTextField(30);
		ipPanel.add(ipTextField);
		ipPanel.setVisible(true);
		
		//userPanel
		userPanel = new JPanel();
		frame.add(userPanel, BorderLayout.SOUTH);
		userNameLabel = new JLabel("Username: ");
		userPanel.add(userNameLabel);
		userPanel.setVisible(true);
		userNameTextField = new JTextField(30);
		userPanel.add(userNameTextField);
		userPanel.setVisible(true);
		connectButton = new JButton("Connect");
		userPanel.add(connectButton);
		userPanel.setVisible(true);
		connectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isConnected == false) {
					username = userNameTextField.getText();
					serverIP = ipTextField.getText();
					userNameTextField.setEditable(false);

					try {
						sock = new Socket(serverIP, port);
						InputStreamReader streamreader = new InputStreamReader(sock.getInputStream());
						reader = new BufferedReader(streamreader);
						writer = new PrintWriter(sock.getOutputStream());
						writer.println(username + ":has connected.:Connect"); // Displays
						writer.flush(); // flushes the buffer
						isConnected = true; // Used to see if the client is
											// connected.
					} catch (Exception ex) {
						System.out.println("Cannot Connect! Try Again. \n");
						userNameTextField.setEditable(true);
					}
					// listen thread
					//Thread IncomingReader = new Thread(new IncomingReader());
					Thread IncomingReader = new Thread();
					IncomingReader.start();
				} else if (isConnected == true) {
					System.out.println("You are already connected. \n");
				}

			}

		});

		frame.add(mainPanel);
		frame.setLayout(new GridLayout(0,2));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Connect with IP");
		frame.setSize(new Dimension(200, 400));
		frame.setVisible(true);
	}

	// private void tryIP(String iP) {
	// try {
	// address = InetAddress.getByName(iP);
	// if (serverListening(address, port)) {
	// System.out.println("Server is Listening\nStarting Client");
	// startClient();
	//
	// } else {
	// System.out.println("Server NOT Listening\nStarting Server\nStarting
	// Client");
	// new StartServer();
	// startClient();
	// }
	//
	// } catch (UnknownHostException e) {
	// JOptionPane.showMessageDialog(null, "Couldn't recognize that IP\nTry
	// again");
	// }
	// }
	//
	//// private void startServer() {
	//// new StartServer();
	//// }
	//
	// private void startClient() {
	// new Client();
	// }
	//
	// private boolean serverListening(InetAddress address, int port) {
	// Socket s = null;
	// try {
	// s = new Socket(address, port);
	// System.out.println("Try is True");
	// return true;
	// } catch (Exception e) {
	// System.out.println("Try is False");
	// return false;
	// } finally {
	// if (s != null) {
	// try {
	// s.close();
	// } catch (Exception e) {
	// System.out.println("Couldn't close test Socket");
	// }
	// }
	// }
	// }

}
