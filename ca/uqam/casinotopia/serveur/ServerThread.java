package ca.uqam.casinotopia.serveur;

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

import ca.uqam.casinotopia.command.AfficherMenu;
import ca.uqam.casinotopia.command.Command;
import ca.uqam.casinotopia.command.EnvoyerListeUser;
import ca.uqam.casinotopia.command.EnvoyerMessage;
import ca.uqam.casinotopia.command.EnvoyerUsername;
import ca.uqam.casinotopia.connexion.Connexion;

public class ServerThread implements Runnable {
	
	private Connexion connexion;
	
	private int number = 0;
	public String username = null;
	
	public ServerThread(Socket clientSocket, int number) {
		connexion = new Connexion(clientSocket);
		this.number = number;
	}
	
	@Override
	public void run() {
		try {
			System.out.println("client no "+number+" connecté");
			while(connexion.isConnected()){
				try{
					username = demanderUsername();
					int choix = 0;
					while(choix != -1){
						choix = Integer.parseInt(afficherMenu());
						switch (choix) {
						case 1:
							envoyerListeUser();
							break;
	
						default:
							envoyerMessage();
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
			connexion.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	
	private void envoyerMessage() throws IOException {

		connexion.getObjectOutputStream().writeObject(new EnvoyerMessage("Option invalide"));
		connexion.getObjectOutputStream().reset();

		System.out.println("client "+number+" s'est trompé");
		
	}

	private void envoyerListeUser() throws IOException {

		EnvoyerListeUser lst = new EnvoyerListeUser();
		String liste = "";
		for (int i = 0; i < MainServeur.NUMCONNEXION; i++) {
			if(MainServeur.thread[i] != null && MainServeur.thread[i].isAlive() && MainServeur.serverThread[i].username != null){
				liste += " "+i+": "+MainServeur.serverThread[i].username+"\n";
			}
		}
		lst.setListe(liste);
		connexion.getObjectOutputStream().writeObject(lst);
		connexion.getObjectOutputStream().reset();
		System.out.println("client "+number+" a eu une liste de user");
	}

	private String afficherMenu() throws IOException {

		connexion.getObjectOutputStream().writeObject(new AfficherMenu());
		connexion.getObjectOutputStream().reset();
		String reponse = connexion.getObjectInputStream().readUTF();
		System.out.println("client "+number+" choisit "+ reponse);
		return reponse;
		
	}

	public String demanderUsername() throws IOException{

		connexion.getObjectOutputStream().writeObject(new EnvoyerUsername());
		connexion.getObjectOutputStream().reset();
		String reponse = connexion.getObjectInputStream().readUTF();
		System.out.println("le client "+number+" s'appel "+ reponse);
		return reponse;
		
	}
	

}
