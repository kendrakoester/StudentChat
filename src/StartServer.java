import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class StartServer implements Runnable {
	public void run() {
		try {
			ServerSocket serverSock = new ServerSocket(8090);

			while (true) {
				// set up the server writer function and then begin at the same
				// the listener using the Runnable and Thread
				Socket clientSock = serverSock.accept();
				PrintWriter writer = new PrintWriter(clientSock.getOutputStream());

				// use a Runnable to start a 'second main method that will run
				// the listener
				Thread listener = new Thread(new Server(clientSock, writer));
				listener.start();
				System.out.println("Got a connection. \n");
			} // end while
		} // end try
		catch (Exception ex) {
			System.out.println("Error making a connection. \n");
		} // end catch

	}
}
