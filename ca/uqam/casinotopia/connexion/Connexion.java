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


public class Connexion {
	
	private Socket socket;
	private boolean connected;
	
	private InputStream input;
	private OutputStream output;

	private ObjectOutputStream oOutputStream;
	private ObjectInputStream oInputStream;
	
	private DataOutputStream dOutputStream;
	private DataInputStream dInputStream;

	public Connexion(String ip, int port) {
		try {
			socket = new Socket(ip, port);
		} catch (UnknownHostException e) {
            System.out.println("Unable to connect to server, UnknownHostException");
            connected = false;
		} catch (IOException e) {
            System.out.println("Unable to connect to server, IOException");
            connected = false;
		}
		init();
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
			// TODO Auto-generated catch block
			e.printStackTrace();
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

		try {
			oOutputStream.close();
			oInputStream.close();
			dOutputStream.close();
			dInputStream.close();
			socket.close();
			connected = false;
		} catch (IOException e) {
			System.err.println("On ne peut pas fermer la connexion");
			e.printStackTrace();
		}
	}
	
	
}
