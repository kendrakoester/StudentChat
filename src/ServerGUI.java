import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class ServerGUI {
	JFrame frame;
	JPanel mainPanel;
	JLabel ipLabel, headLabel;
	JTextField ipTextField;
	JButton submitIPButton;

	private int port = 8090;
	private InetAddress address;

	public ServerGUI() 
	{

	}

	public void guiServer() {
		frame = new JFrame();

		// main panel
		mainPanel = new JPanel();
		frame.add(mainPanel);
		mainPanel.setVisible(true);
		mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

		headLabel = new JLabel("ServerGUI");
		mainPanel.add(headLabel);
		mainPanel.setVisible(true);

		ipLabel = new JLabel("Enter your IP address to connect");
		mainPanel.add(ipLabel);
		mainPanel.setVisible(true);
		ipTextField = new JTextField(20);
		mainPanel.add(ipTextField);
		mainPanel.setVisible(true);
		submitIPButton = new JButton("Submit IP");
		submitIPButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tryIP(ipTextField.getText());
				
			}

		});
		mainPanel.add(submitIPButton);
		mainPanel.setVisible(true);

		frame.setLayout(new GridLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Connect with IP");
		frame.setSize(new Dimension(500, 200));
		frame.setVisible(true);
	}

	private void tryIP(String iP) {
		try {
			address = InetAddress.getByName(iP);
			if (serverListening(address, port)) {
				System.out.println("Server is Listening\nStarting Client");
				startClient();
				
			} else {
				System.out.println("Server NOT Listening\nStarting Server\nStarting Client");
				startServer();
				startClient();
			}

		} catch (UnknownHostException e) {
			JOptionPane.showMessageDialog(null, "Couldn't recognize that IP\nTry again");
		}
	}

	private void startServer() {
		new StartServer();
	}

	private void startClient() {
		new Client();
	}

	private boolean serverListening(InetAddress address, int port) {
		Socket s = null;
		try {
			s = new Socket(address, port);
			System.out.println("Try is True");
			return true;
		} catch (Exception e) {
			System.out.println("Try is False");
			return false;
		} finally {
			if (s != null) {
				try {
					s.close();
				} catch (Exception e) {
					System.out.println("Couldn't close test Socket");
				}
			}
		}
	}

}
