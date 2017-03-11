import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Server
{
	JFrame frame;
	JPanel mainPanel;
	JLabel ipLabel, headLabel;
	JTextField ipTextField;
	JButton sendButton, connectButton;
	
	public Server(int port)
	{
		
		
	}
	
	public void guiServer()
	{
		frame = new JFrame();
		
		//main panel
		mainPanel = new JPanel();
		frame.add(mainPanel);
		mainPanel.setVisible(true);
		mainPanel.setBorder(new EmptyBorder(10,10,10,10));
		
		headLabel = new JLabel("ServerGUI");
		mainPanel.add(headLabel);
		mainPanel.setVisible(true);
		
		ipLabel = new JLabel("Enter your IP address to connect");
		mainPanel.add(ipLabel);
		mainPanel.setVisible(true);
		ipTextField = new JTextField(20);
		mainPanel.add(ipTextField);
		mainPanel.setVisible(true);
		connectButton = new JButton("Connect");
		mainPanel.add(connectButton);
		mainPanel.setVisible(true);
		
		frame.setLayout(new GridLayout());		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Connect with IP");
		frame.setSize(new Dimension(500,200));
		frame.setVisible(true);
	}
}
