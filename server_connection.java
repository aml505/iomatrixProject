import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class server_connection implements Runnable{
	private Socket server;
	private BufferedReader in;
	private PrintWriter out;
	
	public server_connection(Socket s)throws IOException{
		server = s;
		in = new BufferedReader( new InputStreamReader(server.getInputStream()));
		out = new PrintWriter(server.getOutputStream(),true); 
	}

	@Override
	public void run() {
		JFrame frame = new JFrame("IoRMatrix");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 600, 600);				
		MyMatrix matrix = new MyMatrix();
		frame.add(matrix);
		frame.setVisible(true);
		System.out.println("IoRMatrix SERVEUR Start ...");	
		try {
			matrix.afficher();
			matrix.init();
			String s ;
			while(!(s = in.readLine()).equals("exit")) {
				if(s.equals("d")) { System.out.println("DROITE"); matrix.incX(); System.out.print("IoRMatrix_S > "); }
				if(s.equals("g")) { System.out.println("GAUCHE"); matrix.decX(); System.out.print("IoRMatrix_S > "); }
				if(s.equals("b")) { System.out.println("BAS"); matrix.incY(); System.out.print("IoRMatrix_S > "); }
				if(s.equals("h")) { System.out.println("HAUT"); matrix.decY(); System.out.print("IoRMatrix_S > "); }
				if(s.equals("init")) { System.out.println("INIT"); matrix.init(); System.out.print("IoRMatrix_S > "); }
				if(s.equals("afficher")) { System.out.println("AFFICHER") ;matrix.afficher();System.out.print("IoRMatrix_S > ");}
				if(s.equals("cacher")) { System.out.println("CACHER") ;matrix.cacher();System.out.print("IoRMatrix_S > ");}
				//System.out.println("server says: " + s);
			}
			matrix.cacher();
			System.out.println("Bye");
			frame.dispose();			
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			try {
				in.close();
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
	}

}
