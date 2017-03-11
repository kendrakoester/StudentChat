import java.net.InetAddress;

public class StudentChat
{

	public static void main(String[] args) 
	{
		int port = 8090;
		InetAddress address = null;
		
		Server guiServer = new Server(port);
		guiServer.guiServer();
		
		//GUI gui = new GUI(address, port);
		//gui.showGUI();
		
		
				   
	}	

}
