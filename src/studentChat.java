import javax.swing.JFrame;

public class StudentChat
{

	public static void main(String[] args) 
	{
		Conversation conversation = new Conversation();
		conversation.haveConversation();
	
		GUI gui = new GUI();
		gui.showGUI();
		
				   
	}
	
	

}
