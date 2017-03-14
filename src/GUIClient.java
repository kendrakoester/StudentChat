import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import com.sun.glass.events.KeyEvent;

public class GUIClient extends JFrame{
	/**
	 * barrowd code from a Youtube tutorial to get started
	 */
	private static final long serialVersionUID = 1L;
	String username, serverIP;
	int port = 8090;
	Socket sock;
	BufferedReader reader;
	PrintWriter writer;
	ArrayList<String> userList = new ArrayList<String>();
	Boolean isConnected = false;

	JFrame frame;
	JPanel mainPanel, headPanel, chatPanel, textPanel, sendPanel;
	JLabel headLabel, ipLabel, userNameLabel;
	JScrollPane chatScrollPane, textScrollPane;
	static JTextArea chatTextArea;
	JTextArea textTextArea;
	JButton sendButton, connectButton;
	JTextField ipTextField, userNameTextField;;

	Conversation conversation = new Conversation();

	public GUIClient() {
	}

	public void showGUI() {
		frame = new JFrame("Student Chat GUI");

		// main panel
		mainPanel = new JPanel();
		frame.add(mainPanel);
		mainPanel.setVisible(true);

		// header Panel
		headPanel = new JPanel();
		headPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		ipLabel = new JLabel("IP address: ");
		headPanel.add(ipLabel);
		headPanel.setVisible(true);
		ipTextField = new JTextField(10);
		headPanel.add(ipTextField);
		headPanel.setVisible(true);

		userNameLabel = new JLabel("Username: ");
		headPanel.add(userNameLabel);
		headPanel.setVisible(true);
		userNameTextField = new JTextField(10);
		headPanel.add(userNameTextField);
		headPanel.setVisible(true);

		connectButton = new JButton("Connect");
		headPanel.add(connectButton);
		headPanel.setVisible(true);
		connectButton.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				connectButtonActionPerformed();
			}

		});
		// chat Panel
		chatPanel = new JPanel();

		chatTextArea = new JTextArea(12, 40);
		chatTextArea.setLineWrap(true);
		chatTextArea.setEditable(false);
		chatTextArea.append(conversation.haveConversation());
		chatScrollPane = new JScrollPane(chatTextArea);
		chatScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		chatPanel.add(chatScrollPane);
		chatPanel.setVisible(true);

		// send text Panel
		textPanel = new JPanel();

		textTextArea = new JTextArea(3, 40);
		textTextArea.setLineWrap(true);
		textTextArea.setEditable(true);
		textScrollPane = new JScrollPane(textTextArea);
		textScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		textPanel.add(textScrollPane);
		textPanel.setVisible(true);

		textTextArea.addKeyListener(new KeyListener() {
			public void keyPressed(java.awt.event.KeyEvent p) {
				if ((p.getKeyCode() == KeyEvent.VK_ENTER) && (p.isControlDown())) {
					chatTextArea.setText(chatTextArea.getText() + "\n" + textTextArea.getText());
				}

			}

			public void keyReleased(java.awt.event.KeyEvent r) {
				if ((r.getKeyCode() == KeyEvent.VK_ENTER) && (r.isControlDown())) {
					textTextArea.setText("");
				}

			}

			public void keyTyped(java.awt.event.KeyEvent t) {

			}
		});

		// send button Panel
		sendPanel = new JPanel();

		sendButton = new JButton("Send");
		sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// chatTextArea.setText(chatTextArea.getText() + "\n" +
				// textTextArea.getText());
				// textTextArea.setText("");
				sendButtonActionPerformed();
			}

		});
		sendPanel.add(sendButton);
		textPanel.setVisible(true);

		// adding all Panels to the main Panel
		mainPanel.add(headPanel);
		mainPanel.add(chatPanel);
		mainPanel.add(textPanel);
		mainPanel.add(sendPanel);

		frame.setLayout(new GridLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Student Chat GUI");
		frame.setSize(new Dimension(500, 420));
		frame.setVisible(true);

	}

	public class IncomingReader implements Runnable {

		public void run() {
			String[] data;
			String stream, done = "Done", connect = "Connect", disconnect = "Disconnect", chat = "Chat";

			try {
				while ((stream = reader.readLine()) != null) {

					data = stream.split(":");

					if (data[2].equals(chat)) {

						chatTextArea.append(data[0] + ":" + data[1] + "\n");
						chatTextArea.setCaretPosition(chatTextArea.getDocument().getLength());

					} else if (data[2].equals(connect)) {

						chatTextArea.removeAll();
						userAdd(data[0]);

					} else if (data[2].equals(disconnect)) {

						userRemove(data[0]);

					} else if (data[2].equals(done)) {
						writeUsers();
						userList.clear();

					}

				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	public void ListenThread() {
		Thread IncomingReader = new Thread(new IncomingReader());
		IncomingReader.start();
	}

	public void userAdd(String data) {
		userList.add(data);

	}

	public void userRemove(String data) {
		chatTextArea.append(data + " has disconnected.\n");

	}

	public void writeUsers() {
		String[] tempList = new String[(userList.size())];
		userList.toArray(tempList);
		for (String token : tempList) {

			System.out.println(token + "\n");

		}

	}

	private void connectButtonActionPerformed() {
		if (isConnected == false) {
			username = userNameTextField.getText();
			userNameTextField.setEditable(false);
			serverIP = ipTextField.getText();

			try {
				sock = new Socket(serverIP, port);
				InputStreamReader streamreader = new InputStreamReader(sock.getInputStream());
				reader = new BufferedReader(streamreader);
				writer = new PrintWriter(sock.getOutputStream());
				writer.println(username + ":has connected.:Connect"); 
				writer.flush(); 
				isConnected = true; 
			} catch (Exception ex) {
				chatTextArea.append("Cannot Connect! Try Again. \n");
				userNameTextField.setEditable(true);
				ServerGUI gui = new ServerGUI();
				gui.guiServer();
			}
			ListenThread();
		} else if (isConnected == true) {
			chatTextArea.append("You are already connected. \n");
		}
	}

	private void sendButtonActionPerformed() {
		String nothing = "";
		if ((textTextArea.getText()).equals(nothing)) {

		} else {
			try {
				writer.println(username + ":" + textTextArea.getText() + ":" + "Chat");
				writer.flush(); 
			} catch (Exception ex) {
				chatTextArea.append("Message was not sent. \n");
			}
		}

		textTextArea.setText("");
		textTextArea.requestFocus();
	}
}
