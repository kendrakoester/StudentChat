import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

public class Server implements Runnable {
	BufferedReader reader;
	Socket sock;
	PrintWriter client;
	ArrayList clientOutputStreams;

	public Server(Socket clientSocket, PrintWriter user) {
		client = user;
		try {
			sock = clientSocket;
			InputStreamReader isReader = new InputStreamReader(sock.getInputStream());
			reader = new BufferedReader(isReader);
		} // end try
		catch (Exception ex) {
			System.out.println("Error beginning StreamReader. \n");
		} 
	}

	public void run() {
		String message, connect = "Connect", disconnect = "Disconnect", chat = "Chat";
		String[] data;

		try {
			while ((message = reader.readLine()) != null) {

				System.out.println("Received: " + message + "\n");
				data = message.split(":");
				for (String token : data) {

					System.out.println(token + "\n");

				}

				if (data[2].equals(connect)) {

					tellEveryone((data[0] + ":" + data[1] + ":" + chat));
					//userAdd(data[0]);

				} else if (data[2].equals(disconnect)) {

					tellEveryone((data[0] + ":has disconnected." + ":" + chat));
					//userRemove(data[0]);

				} else if (data[2].equals(chat)) {

					tellEveryone(message);

				} else {
					System.out.println("No Conditions were met. \n");
				}

			} // end while
		} // end try
		catch (Exception ex) {
			System.out.println("Lost a connection. \n");
			ex.printStackTrace();
		} // end catch
	} // end run()
	
	public void tellEveryone(String message) {
		// sends message to everyone connected to server
			Iterator it = clientOutputStreams.iterator();

			while (it.hasNext()) {
				try {
					PrintWriter writer = (PrintWriter) it.next();
					writer.println(message);
					System.out.println("Sending: " + message + "\n");
	                                writer.flush();
	                                //outputPane.setCaretPosition(outputPane.getDocument().getLength());

				} // end try
				catch (Exception ex) {
					System.out.println("Error telling everyone. \n");
				} // end catch
			} // end while
		}

}
