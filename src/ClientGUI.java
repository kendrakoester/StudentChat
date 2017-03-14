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

public class ClientGUI extends JFrame 
{

	private static final long serialVersionUID = 1L;
	
	PrintWriter writer;
	BufferedReader reader;
    ArrayList<String> userList = new ArrayList();
    String username, serverIP;
    Socket sock;
	int port = 8090;
	Boolean isConnected = false;
	JFrame frame;
	JPanel mainPanel, headPanel, chatPanel,textPanel, sendPanel;
	JLabel headLabel, ipLabel, userNameLabel;
	JScrollPane chatScrollPane, textScrollPane;
	JTextArea chatTextArea, textTextArea;
	JButton sendButton, connectButton;
	JTextField ipTextField,userNameTextField;;
	
	Conversation conversation = new Conversation();
	
	public ClientGUI()
	{
		
	}
	
	public void showGUI()
	{
		frame = new JFrame("Student Chat GUI");
				
		//main panel
		mainPanel = new JPanel();
		frame.add(mainPanel);
		mainPanel.setVisible(true);
		
		//header Panel
		headPanel = new JPanel();
		headPanel.setBorder(new EmptyBorder(10,10,10,10));
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
		//chat Panel
		chatPanel = new JPanel();
		
		chatTextArea = new JTextArea(12,40);
		chatTextArea.setLineWrap(true);
		chatTextArea.setEditable(false);
		chatTextArea.append(conversation.haveConversation());
		chatScrollPane = new JScrollPane(chatTextArea);
		chatScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		chatPanel.add(chatScrollPane);
		chatPanel.setVisible(true);
		
		//send text Panel
		textPanel = new JPanel();
		
		textTextArea = new JTextArea(3, 40);
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
		          //chatTextArea.setText(chatTextArea.getText() + "\n" + textTextArea.getText());
		          //textTextArea.setText("");
		    	 sendButtonActionPerformed();
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
		frame.setSize(new Dimension(500,420));
		frame.setVisible(true);
			
	}
	
	public class IncomingReader implements Runnable{

        public void run() {
            String[] data;
            String stream, done = "Done", connect = "Connect", disconnect = "Disconnect", chat = "Chat";

            try {
                while ((stream = reader.readLine()) != null) {

                    data = stream.split(":");

                     if (data[2].equals(chat)) {

                        chatTextArea.append(data[0] + ": " + data[1] + "\n");
                        chatTextArea.setCaretPosition(chatTextArea.getDocument().getLength());

                    } else if (data[2].equals(connect)){

                        chatTextArea.removeAll();
                        //userAdd(data[0]);
                        userList.add(data[0]);

                    } else if (data[2].equals(disconnect)) {

                        //userRemove(data[0]);
                        chatTextArea.append(data[0] + " has disconnected.\n");

                    } else if (data[2].equals(done)) {

                        writeUsers();
                        userList.clear();

                    }
                 
                }
           }catch(Exception ex) {
           }
        }
    }
	
	public void writeUsers() {
        String[] tempList = new String[(userList.size())];
        userList.toArray(tempList);
        for (String token:tempList) {

            System.out.println("Current list of users\n" + token + "\n");

        }

    }
	
	private void sendButtonActionPerformed() {                                           
        username = userNameTextField.getText();
        if ((textTextArea.getText()).equals("")) {

        } else {
            try {
               writer.println(username + ":" + textTextArea.getText() + ":" + "Chat");
               writer.flush(); // flushes the buffer
            } catch (Exception ex) {
                chatTextArea.append("Enter IP and User to connect. \n");
            }
        }

        textTextArea.setText("");
        textTextArea.requestFocus();
    }
	
	private void connectButtonActionPerformed() {                                              

            if (isConnected == false) {
            username = userNameTextField.getText();
            serverIP = ipTextField.getText();
            userNameTextField.setEditable(false);

            try {
                sock = new Socket(serverIP, port);
                InputStreamReader streamreader = new InputStreamReader(sock.getInputStream());
                reader = new BufferedReader(streamreader);
                writer = new PrintWriter(sock.getOutputStream());
                writer.println(username + " : has connected.:Connect");
                
                writer.flush();
                isConnected = true; 
                
            } catch (Exception ex) {
                ServerGUI.connectTextArea.setText("DENY cannont connect, start server and try again. \n");
                ServerGUI gui = new ServerGUI();
				gui.guiServer();
            }
            Thread IncomingReader = new Thread(new IncomingReader());
	         IncomingReader.start();
        } else if (isConnected == true) {
        	ServerGUI.connectTextArea.setText("You are already connected. \n");
        }
    }
	
	
}
