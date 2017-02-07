import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import com.sun.glass.events.KeyEvent;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

public class GUI extends JFrame implements ActionListener
{
	JFrame frame;
	JPanel mainPanel, headPanel, chatPanel,textPanel, sendPanel;
	JLabel headLabel;
	JScrollPane chatScrollPane, textScrollPane;
	JTextArea chatTextArea, textTextArea;
	JButton sendButton;
	
	Conversation conversation = new Conversation();
	
	
	public GUI()
	{
		
	}
	
	public void showGUI()
	{
		frame = new JFrame();
				
		//main panel
		mainPanel = new JPanel(new GridLayout(4,1,0,0));
		frame.add(mainPanel);
		mainPanel.setVisible(true);
		
		//header Panel
		headPanel = new JPanel();
		
		headLabel = new JLabel("Student Chat GUI");
		headPanel.add(headLabel);
		headPanel.setVisible(true);
		
		//chat Panel
		chatPanel = new JPanel();
		
		chatTextArea = new JTextArea(10,40);
		chatTextArea.setLineWrap(true);
		chatTextArea.setEditable(false);
		chatTextArea.append(conversation.toString());
		chatScrollPane = new JScrollPane(chatTextArea);
		chatScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		chatPanel.add(chatScrollPane);
		chatPanel.setVisible(true);
		
		//send text Panel
		textPanel = new JPanel();
		
		textTextArea = new JTextArea(10, 40);
		textTextArea.setLineWrap(true);
		textTextArea.setEditable(true);
		textScrollPane = new JScrollPane(textTextArea);
		textScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		textPanel.add(textScrollPane);
		textPanel.setVisible(true);
		textTextArea.addKeyListener( new KeyListener()
				{
					public void keyPressed(java.awt.event.KeyEvent p) 
					{
						if((p.getKeyCode() == KeyEvent.VK_ENTER) && (p.isControlDown())) 
						{
							chatTextArea.setText(chatTextArea.getText() + "\n" + textTextArea.getText());
						}
						
						
					}

					public void keyReleased(java.awt.event.KeyEvent r) 
					{
						if((r.getKeyCode() == KeyEvent.VK_ENTER) && (r.isControlDown()))
						{
							textTextArea.setText("");
						}

					}

					public void keyTyped(java.awt.event.KeyEvent t) 
					{

					}
				});
		
		//send button Panel
		sendPanel = new JPanel();
		
		sendButton = new JButton("Send");
		sendButton.addActionListener(new ActionListener()
		{
		     public void actionPerformed(ActionEvent e)
		     {
		          chatTextArea.setText(chatTextArea.getText() + "\n" + textTextArea.getText());
		          textTextArea.setText("");
		     }
			
		});
		sendPanel.add(sendButton);
		textPanel.setVisible(true);
		
		//adding all Panels to the main Panel
		mainPanel.add(headPanel);
		mainPanel.add(chatPanel);
		mainPanel.add(textPanel);
		mainPanel.add(sendPanel);
		
		frame.setLayout(new GridLayout());		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Student Chat GUI");
		frame.setSize(new Dimension(500,800));
		frame.setVisible(true);
		
		
	}

	public void actionPerformed(ActionEvent e) 
	{
		
	}
	
}
