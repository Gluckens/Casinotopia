package ca.uqam.casinotopia.serveur;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Random;

import ca.uqam.casinotopia.command.Command;
import ca.uqam.casinotopia.command.EnvoyerUsername;

public class ServerThread implements Runnable {

	private Socket clientSocket;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private DataInputStream dis;
	private DataOutputStream dos;
	private int number = 0;
	private Boolean clientConnected = true;
	
	public String username = null;
	
	public ServerThread(Socket clientSocket, int number) {
		this.clientSocket = clientSocket;
		this.number = number;
		try {
			this.clientSocket.setTcpNoDelay(true);
			
			InputStream is = clientSocket.getInputStream();
			OutputStream os = clientSocket.getOutputStream();
			
			ois = new ObjectInputStream(is);
			oos = new ObjectOutputStream(os);

			dis = new DataInputStream(is);
			dos = new DataOutputStream(os);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		try {
			System.out.println("client no "+number+" connecté");
			String message = "";
			Boolean authentifie = false;
			while(clientConnected){
				try{
					
					if(authentifie){
						message += "1. Se deconnecté\n2. Voir la liste des utilisateurs\n";
						oos.writeUTF(message);
						oos.reset();
						message = "";
						
						String reponse = ois.readUTF();
						int choix;
						try {
							choix = Integer.parseInt(reponse);	
						} catch (NumberFormatException e) {
							choix = -1;
						}
						
						switch (choix) {
						case 1:
							message += "Non tu te déconnecte pas!\n";
							break;

						case 2:
							message += "Voici la liste:\n";
							
							
							for (int i = 0; i < MainServeur.NUMCONNEXION; i++) {
								if(MainServeur.thread[i] != null && MainServeur.thread[i].isAlive() && MainServeur.serverThread[i].username != null){
									message += " "+i+": "+MainServeur.serverThread[i].username+"\n";
								}
							}
							break;
						default:
							message += "Ce choix n'est pas disponible\n";
							break;
						}
					}else{
						message += "1. Se connecté\n";
					
						oos.writeUTF(message);
						oos.reset();
						message = "";
						
						String reponse = ois.readUTF();
						System.out.println("le client a dit: "+reponse);
						int choix;
						try {
							choix = Integer.parseInt(reponse);	
						} catch (NumberFormatException e) {
							choix = -1;
						}
						
						
						switch (choix) {
						case 1:
							while(choix != 0){
								message += "C'est quoi ton nom?\n";
								oos.writeUTF(message);
								oos.reset();
								message = "";
								
								String reponse1 = ois.readUTF();
								System.out.println("le client s'appel: "+reponse1);
								
								message += reponse1+", C'est vraiment ton nom? (oui/non)";
								oos.writeUTF(message);
								oos.reset();
								message = "";
								
								String reponse11 = ois.readUTF();
								if(reponse11.equals("oui")){
									username = reponse1;
									authentifie = true;
									message += "Salut "+reponse1+"!\n";;
									choix = 0;
								}
								
							}
							break;
	
						default:
							message += "Ce choix n'est pas disponible\n";
							break;
						}
					}
					
				}catch (EOFException e) {
					System.err.println(e.getStackTrace());
				}
				Thread.sleep(1);//sauve du cpu
			}
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			System.out.println("déconnexion du client "+number);
			try {
				oos.close();
				ois.close();
				dos.close();
				dis.close();
				clientSocket.close();
				clientConnected = false;
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
