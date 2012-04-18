package ca.uqam.casinotopia.controleur.serveur;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

import javax.swing.JFrame;

import ca.uqam.casinotopia.commande.Commande;
import ca.uqam.casinotopia.commande.CommandeServeur;
import ca.uqam.casinotopia.commande.CommandeServeurControleurClient;
import ca.uqam.casinotopia.commande.CommandeServeurControleurPrincipal;
import ca.uqam.casinotopia.commande.CommandeServeurControleurRoulette;
import ca.uqam.casinotopia.commande.CommandeServeurControleurThread;
import ca.uqam.casinotopia.commande.client.CmdAfficherJeuRoulette;
import ca.uqam.casinotopia.commande.serveur.CmdMiserRoulette;
import ca.uqam.casinotopia.connexion.Connexion;
import ca.uqam.casinotopia.controleur.ControleurServeur;
import ca.uqam.casinotopia.modele.serveur.ModeleUtilisateurServeur;
import ca.uqam.casinotopia.serveur.MainServeur;

public class ControleurServeurThread extends ControleurServeur implements Runnable {
		
	
	private ControleurClientServeur ctrlClientServeur;
	private ControleurRouletteServeur ctrlRouletteServeur;
	
	private ModeleUtilisateurServeur modele = new ModeleUtilisateurServeur();
	
	public ControleurServeurThread(Socket clientSocket, int number) {
		this.setConnexion(new Connexion(clientSocket));
		this.modele.number = number;
		
		this.ctrlClientServeur = new ControleurClientServeur(this.getConnexion());
		this.ctrlRouletteServeur = new ControleurRouletteServeur(this.getConnexion());
	}
	
	@Override
	public void run() {
		try {
			System.out.println("client no "+this.modele.number+" connecté");
			while(getConnexion().isConnected()) {
				Commande cmd = null;
	            try {
					cmd = (Commande) this.getConnexion().getObjectInputStream().readObject();
		            if(cmd != null) {
		            	if(cmd instanceof CommandeServeur) {
		            		System.out.println("COMMANDE RECU DE TYPE COMMANDE_SERVEUR");
			            	if(cmd instanceof CommandeServeurControleurClient) {
			            		cmd.action(this.ctrlClientServeur);
			            	}
			            	else if(cmd instanceof CommandeServeurControleurRoulette) {
			            		cmd.action(this.ctrlRouletteServeur);
			            	}
			            	else if(cmd instanceof CommandeServeurControleurPrincipal) {
			            		cmd.action(this);
			            	}
			            	else if(cmd instanceof CommandeServeurControleurThread) {
			            		cmd.action(this);
			            	}
		            	}
		            	else {
		            		System.err.println("Seulement des commandes destinées au serveur sont recevables!");
		            	}
		            }
		            else{
		            	System.err.println("Un problème est survenu (commande nulle).");
		            }
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SocketException e) {
					// TODO Auto-generated catch block
					System.out.println("Déconnexion du client " + this.modele.number);
					getConnexion().close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Thread.sleep(1);//sauve du cpu
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void actionAjouterJoueurDansRoulette() {
		//TODO créer la partie dans la liste de partie sur le controleur principal et aussi dans le controleurServeurThread du client
		
		int idPartie = 3;
		
		this.cmdAfficherJeuRoulette(idPartie);
	}
	
	public void cmdAfficherJeuRoulette(int idPartie) {
		this.connexion.envoyerCommande(new CmdAfficherJeuRoulette(idPartie));
	}


	/**
	 * @return the modele
	 */
	public ModeleUtilisateurServeur getModel() {
		return modele;
	}

	/**
	 * @param modele the modele to set
	 */
	public void setModel(ModeleUtilisateurServeur model) {
		this.modele = model;
	}
	
	public ArrayList<String> getAllUtilisateurs(){
		ArrayList<String> liste = new ArrayList<String>();
		for (int i = 0; i < MainServeur.NUMCONNEXION; i++) {
			if(MainServeur.thread[i] != null && 
					MainServeur.thread[i].isAlive() && 
					MainServeur.serverThread[i].getModel().getUtilisateur().getNomUtilisateur() != null){
				liste.add( MainServeur.serverThread[i].getModel().getUtilisateur().getNomUtilisateur());
			}
		}
		return liste;
	}
	
	public void envoyerCommandeATous(Commande cmd){
		for (int i = 0; i < MainServeur.NUMCONNEXION; i++) {
			if(MainServeur.thread[i] != null && 
					MainServeur.thread[i].isAlive() && 
					MainServeur.serverThread[i].getModel().getUtilisateur().getNomUtilisateur() != null){
				MainServeur.serverThread[i].getConnexion().envoyerCommande(cmd);
			}
		}
	}
	
}
