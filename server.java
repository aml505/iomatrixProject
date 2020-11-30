

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class server {
	private static String[] names = {"Abderrahim", "Amalou", "Mohammed", "Amine", "Hssaini"};
	private static String[] adjs = {"l7mar","lkidar","psikopat","ttonsy"};
	private static final int PORT=3030;
	
	private static ArrayList<client_handler> clients = new ArrayList<>();
	private static ExecutorService pool = Executors.newFixedThreadPool(4);
	public static void main(String[] args) throws IOException {
		ServerSocket Listener = new ServerSocket(PORT);
		
		
		while(true) {
			System.out.println(" [SERVER] Waiting to client to connect...");
			Socket client = Listener.accept();
			System.out.println(" [SERVER] Client connected");
			client_handler clientThread = new client_handler(client, clients);
			clients.add(clientThread);
			
			pool.execute(clientThread);
			
		}
		

		

		
}
	public static String getRandomName(){
		String name = names[(int)(Math.random()*names.length)];
		String adj = adjs[(int)(Math.random()*adjs.length)];
		return name + " " + adj;
	}

}
