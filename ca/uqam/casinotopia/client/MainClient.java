package ca.uqam.casinotopia.client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import ca.uqam.casinotopia.command.Command;
import ca.uqam.casinotopia.command.EnvoyerUsername;

public class MainClient {

	private static Socket comms;
	private static Boolean connected;
	
	private static InputStream input;
	private static OutputStream output;

	private static ObjectOutputStream oos;
	private static ObjectInputStream ois;
	
	private static DataOutputStream dos;
	private static DataInputStream dis;

	
	public static void main(String[] args) {
		System.out.println("connection au serveur...");
        try {
            comms = new Socket("localhost", 7777);
            connected = true;
            System.out.println("Connecté");
            comms.setTcpNoDelay(true);

            output = comms.getOutputStream();
            input =  comms.getInputStream();

			oos = new ObjectOutputStream(output);
			ois = new ObjectInputStream(input);
			
            dos = new DataOutputStream(output);
            dis = new DataInputStream(input);

            while(true){
	            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
				System.out.println("serveur:\n"+ois.readUTF());
				String message = in.readLine();
				oos.writeUTF(message);
				oos.reset();
            }

        } catch (IOException e) {
            System.out.println("Unable to connect to server, please check and try again");
            connected = false;
        }
	}

}
