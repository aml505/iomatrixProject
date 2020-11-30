
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class client_matrix {
	private static final String SERVER_IP = "localhost";
	private static final int SERVER_PORT = 3030;
	private static final String clientType = "matrix";


	public static void main(String[] args) throws IOException {
		Socket socket = new Socket(SERVER_IP, SERVER_PORT);
		BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		server_connection serverConn = new server_connection(socket);
		BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		new Thread(serverConn).start();
		/*while(true){
			
			
			String serverresponse = input.readLine();
			System.out.println("server says: " + serverresponse);
			if (serverresponse.equals("quit")) break;
			out.println(clientType);
			
			
		}*/
		//socket.close();
		//System.exit(0);

	}

}
