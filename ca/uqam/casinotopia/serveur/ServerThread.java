package ca.uqam.casinotopia.serveur;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import ca.uqam.casinotopia.command.AfficherMenu;
import ca.uqam.casinotopia.command.Command;
import ca.uqam.casinotopia.command.EnvoyerListeUser;
import ca.uqam.casinotopia.command.EnvoyerMessage;
import ca.uqam.casinotopia.command.DemanderUsername;
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
				premiereAction(new DemanderUsername());
				Command cmd = null;
	            try {
					cmd = (Command) connexion.getObjectInputStream().readObject();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            if(cmd != null){
					cmd.action();
					cmd.repondre(connexion.getObjectOutputStream());
	            }else{
	            	System.err.println("la commande envoyé n'est pas valide");
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

	public void premiereAction(Command cmd) throws IOException{

		connexion.getObjectOutputStream().writeObject(cmd);
		connexion.getObjectOutputStream().reset();
		
	}
	

}
