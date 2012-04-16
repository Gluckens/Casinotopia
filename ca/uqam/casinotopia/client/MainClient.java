package ca.uqam.casinotopia.client;

import java.awt.EventQueue;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;

import ca.uqam.casinotopia.Case;
import ca.uqam.casinotopia.command.Command;
import ca.uqam.casinotopia.commande.CmdMiserRoulette;
import ca.uqam.casinotopia.commande.CmdUpdateCasesRoulette;
import ca.uqam.casinotopia.commande.Commande;
import ca.uqam.casinotopia.commande.CommandeClient;
import ca.uqam.casinotopia.commande.CommandeClientControleurClient;
import ca.uqam.casinotopia.commande.CommandeClientControleurRoulette;
import ca.uqam.casinotopia.connexion.Connexion;
import ca.uqam.casinotopia.controleur.Controleur;
import ca.uqam.casinotopia.controleur.ControleurClientClient;
import ca.uqam.casinotopia.controleur.ControleurClientServeur;
import ca.uqam.casinotopia.controleur.ControleurRouletteClient;
import ca.uqam.casinotopia.controleur.ControleurRouletteServeur;
import ca.uqam.casinotopia.vue.ConnexionFrame;
import ca.uqam.casinotopia.vue.FrameApplication;
import ca.uqam.casinotopia.vue.VueRoulette;

public class MainClient extends Controleur {

	private FrameApplication frameApplication;
	private ConnexionFrame connexionFrame;
	
	private ControleurClientClient ctrlClientClient;
	private ControleurRouletteClient ctrlRouletteClient;
	
	
	
	public static void main(String[] args) {
		new MainClient();
	}
	
	public MainClient() {
		
		System.out.println("recherche de serveur...");
        
		String[] serveurs = {"localhost","oli.dnsd.me","dan.dnsd.me"};
		int i = 0;
		while(this.getConnexion().isConnected() == false && i < 3){
			this.setConnexion(new Connexion(serveurs[i], 7777));
			i++;
		}
		
		//Utile de mettre sa dans un if? si on est ici = on est connecté?
		if(this.getConnexion().isConnected()) {
			this.frameApplication = new FrameApplication();
			this.frameApplication.changeContentPane(new VueRoulette());
			EventQueue.invokeLater(this.frameApplication);
			
			this.ctrlClientClient = new ControleurClientClient(this.getConnexion());
			this.ctrlRouletteClient = new ControleurRouletteClient(this.getConnexion());
		}
		
    
		int k = 0;
		
        while(this.getConnexion().isConnected()) {
        	k++;
        	if(k == 1) {
        		this.envoyerCommandeTest1();
        	}
        	else if(k == 2) {
        		this.envoyerCommandeTest2();
        	}
        	
            Commande cmd = null;
            try {
            	System.out.println("Avant de bloquer en attente CLIENT");
				cmd = (Commande) getConnexion().getObjectInputStream().readObject();
				System.out.println("J'ai recu une commande sul CLIENT");
	            if(cmd != null){
	            	if(cmd instanceof CommandeClient) {
		            	if(cmd instanceof CommandeClientControleurClient) {
		            		cmd.action(ctrlClientClient, this.frameApplication);
		            	}
		            	else if(cmd instanceof CommandeClientControleurRoulette) {
		            		cmd.action(ctrlRouletteClient, this.frameApplication);
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
				e.printStackTrace();
			}
			catch (IOException e) {
            	System.err.println("Le serveur ne répond plus");
            	this.getConnexion().close();
			}
        }
		
		this.getConnexion().close();
        
        System.out.println("Fermeture du programme...");
	}
	
	public void envoyerCommandeTest1() {
		Map<Integer, Map<Case, Integer>> mises = new HashMap<Integer, Map<Case, Integer>>();
		
		int joueurId = 4;
		
		Map<Case, Integer> misesCases = new HashMap<Case, Integer>();
		
		misesCases.put(new Case(1, "noire", false), 5);
		misesCases.put(new Case(2, "rouge", true), 2);
		misesCases.put(new Case(3, "rouge", false), 8);
		misesCases.put(new Case(4, "noire", true), 8);
		misesCases.put(new Case(5, "noire", false), 1);
		misesCases.put(new Case(6, "rouge", true), 3);
		
		mises.put(joueurId, misesCases);
		
		this.envoyerCommande(new CmdMiserRoulette(mises));
	}
	
	public void envoyerCommandeTest2() {
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Map<Integer, Map<Case, Integer>> mises = new HashMap<Integer, Map<Case, Integer>>();
		
		int joueurId2 = 9;
		
		Map<Case, Integer> misesCases = new HashMap<Case, Integer>();
		
		misesCases.put(new Case(1, "noire", false), 2);
		misesCases.put(new Case(2, "rouge", true), 7);
		misesCases.put(new Case(5, "noire", false), 6);
		
		mises.put(joueurId2, misesCases);
		
		this.envoyerCommande(new CmdMiserRoulette(mises));
	}

	
	public ConnexionFrame getConnexionFrame() {
		return this.connexionFrame;
	}

	public void setConnexionFrame(ConnexionFrame connexionFrame) {
		this.connexionFrame = connexionFrame;
	}
}
