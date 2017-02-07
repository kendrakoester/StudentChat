import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame implements ActionListener
{
	JFrame frame;
	JPanel headPanel, chatPanel,textPanel, sendPanel;
	JLabel headLabel;
	JScrollPane chatPane;
	JTextArea chatTextArea;
	JButton sendButton;
	
	public GUI()
	{
		
	}
	
	public void showGUI()
	{
		frame = new JFrame();
		
		setLayout(new GridLayout(4,1));		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Student Chat GUI");
		setSize(new Dimension(300,400));
		setVisible(true);
		
		headPanel = new JPanel(new FlowLayout());
		
		headLabel = new JLabel("Student Chat GUI");
		headPanel.add(headLabel);
		
		chatPanel = new JPanel(new FlowLayout());
		
		chatTextArea = new JTextArea(16, 58);
		chatTextArea.setEditable(false); // set textArea non-editable
		chatPane = new JScrollPane(chatTextArea);
		chatPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		chatPanel.add(chatPane);
		
		textPanel = new JPanel(new FlowLayout());
		
		
		sendPanel = new JPanel(new FlowLayout());
		
		sendButton = new JButton("Send");
		sendButton.addActionListener(this);
		sendPanel.add(sendButton);
		
		frame.add(headPanel);
		frame.add(chatPanel);
		frame.add(textPanel);
		frame.add(sendPanel);
	}
	
	
	public void actionPerformed(ActionEvent arg0) 
	{
		
		
	}
	
	
	
	
}
