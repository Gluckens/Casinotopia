package ca.uqam.casinotopia.connexion;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

import ca.uqam.casinotopia.command.Command;


public class Connexion {
	
	private Socket socket;
	private boolean connected = false;
	
	private InputStream input;
	private OutputStream output;

	private ObjectOutputStream oOutputStream;
	private ObjectInputStream oInputStream;
	
	private DataOutputStream dOutputStream;
	private DataInputStream dInputStream;

	
	public Connexion() {
		connected = false;
	}
	
	public Connexion(String ip, int port) {
	    System.out.println("Connection à "+ip);
		int essaie = 5;
		while(essaie != 0){
			try {
				socket = new Socket(ip, port);
				init();
	            essaie = 0;
	    	    System.out.println("Connecté a "+ip);
			} catch (UnknownHostException e) {
	            System.out.println("Unable to connect to server, UnknownHostException");
	            connected = false;
	            essaie--;
			} catch (IOException e) {
	            System.out.println("Incapable de se connecter au serveur");
	            connected = false;
	            essaie--;
			}
		}
	}

	public Connexion(Socket socket) {
		this.socket = socket;
		init();
	}
	
	private void init() {
        connected = true;
        try {
			socket.setTcpNoDelay(true);
		} catch (SocketException e) {
			this.close();
		}
        
        try {
			output = socket.getOutputStream();
	        input =  socket.getInputStream();
	
	        oOutputStream = new ObjectOutputStream(output);
	        oInputStream = new ObjectInputStream(input);
			
	        dOutputStream = new DataOutputStream(output);
	        dInputStream = new DataInputStream(input);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public DataInputStream getDataInputStream() {
		return dInputStream;
	}
	
	public DataOutputStream getDataOutputStream() {
		return dOutputStream;
	}
	
	public ObjectInputStream getObjectInputStream() {
		return oInputStream;
	}
	
	public ObjectOutputStream getObjectOutputStream() {
		return oOutputStream;
	}
	
	public boolean isConnected() {
		return connected;
	}
	
	public void close(){

		connected = false;
		try {
			oOutputStream.close();
			oInputStream.close();
			dOutputStream.close();
			dInputStream.close();
			socket.close();
		} catch (IOException e) {
			System.err.println("erreur lors de la fermeture de la connexion");
		}
	}
	
	public void envoyerCommand(Command cmd){
		
		try {
			this.getObjectOutputStream().writeObject(cmd);
			this.getObjectOutputStream().reset();
		} catch (IOException e) {
			System.err.println("la commande n'a pas pu être envoyé car le serveur ne répond pas");
		}
	}
	
	
}
