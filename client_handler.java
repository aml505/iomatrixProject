import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;



public class client_handler implements Runnable{
	
	private Socket client;
	private BufferedReader in;
	private PrintWriter out;
	private ArrayList<client_handler> clients;
	
	
	public client_handler( Socket clientSocket,ArrayList<client_handler> clients) throws IOException {
		this.client = clientSocket;
		this.clients = clients;
		in = new BufferedReader( new InputStreamReader(client.getInputStream()));
		out = new PrintWriter(client.getOutputStream(),true); 
		
	}

	@Override
	public void run() {
		try {
			while(true){
				
				String request = in.readLine();
				if (request.contains("matrix")){
					out.println(server.getRandomName());
					}
				
				if(request.contains("cmd")) {
					int firstspace = request.indexOf(" ");
					if(firstspace != -1) {
						outtoAll(request.substring(firstspace+1));
					}
				}
				
				
			}
		} catch (IOException e) {
			System.err.println("IOExeption in client handler");
			System.err.println(e.getStackTrace());
		}
		finally{
			System.out.println(" [SERVER]  Closing...*");
			out.close();
			try {
				
				in.close();
				//Listener.close();
				//client.close();
			}catch (IOException e) {
				e.printStackTrace();
			}
			
		}
			
		}

	private void outtoAll(String msg) {
		for(client_handler aClient : clients) {
			if(!(aClient==this))aClient.out.println(msg);
			
		}
		
	}
		
	}
	


