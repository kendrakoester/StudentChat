import java.net.InetAddress;

public class StudentChat
{

	public static void main(String[] args) 
	{
		int port = 8090;
		InetAddress address = null;
		
		ServerGUI server = new ServerGUI();
		server.guiServer();
		
		//GUI gui = new GUI(address, port);
		//gui.showGUI();
		
		
				   
	}	

}
