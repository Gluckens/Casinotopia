package ca.uqam.casinotopia.controleur.serveur;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

import javax.swing.JFrame;

import ca.uqam.casinotopia.commande.Commande;
import ca.uqam.casinotopia.commande.CommandeServeur;
import ca.uqam.casinotopia.commande.CommandeServeurControleurClient;
import ca.uqam.casinotopia.commande.CommandeServeurControleurRoulette;
import ca.uqam.casinotopia.connexion.Connexion;
import ca.uqam.casinotopia.controleur.ControleurClientServeur;
import ca.uqam.casinotopia.controleur.ControleurRouletteServeur;
import ca.uqam.casinotopia.controleur.ControleurServeur;
import ca.uqam.casinotopia.model.ModelServeurClient;
import ca.uqam.casinotopia.serveur.MainServeur;

public class ControleurServeurThread extends ControleurServeur implements Runnable {
		
	
	private ControleurClientServeur ctrlClientServeur;
	private ControleurRouletteServeur ctrlRouletteServeur;
	
	private ModelServeurClient model = new ModelServeurClient();
	
	public ControleurServeurThread(Socket clientSocket, int number) {
		super(null);
		setConnexion(new Connexion(clientSocket));
		this.model.number = number;
		
		this.ctrlClientServeur = new ControleurClientServeur(this.getConnexion());
		this.ctrlRouletteServeur = new ControleurRouletteServeur(this.getConnexion());
	}
	
	@Override
	public void run() {
		try {
			System.out.println("client no "+this.model.number+" connecté");
			while(getConnexion().isConnected()) {
				Commande cmd = null;
	            try {
	            	System.out.println("ATTENTE DE COMMANDE DU CLIENT");
					cmd = (Commande) this.getConnexion().getObjectInputStream().readObject();
					System.out.println("COMMANDE CLIENT OBTENUE");
		            if(cmd != null) {
		            	if(cmd instanceof CommandeServeur) {
			            	if(cmd instanceof CommandeServeurControleurClient) {
			            		cmd.action(this.ctrlClientServeur);
			            	}
			            	else if(cmd instanceof CommandeServeurControleurRoulette) {
			            		cmd.action(this.ctrlRouletteServeur);
			            	}
		            	}
		            	else {
		            		System.err.println("Seulement des commandes destinées aux clients sont recevables!");
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
					System.out.println("Déconnexion du client " + this.model.number);
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


	/**
	 * @return the model
	 */
	public ModelServeurClient getModel() {
		return model;
	}

	/**
	 * @param model the model to set
	 */
	public void setModel(ModelServeurClient model) {
		this.model = model;
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
