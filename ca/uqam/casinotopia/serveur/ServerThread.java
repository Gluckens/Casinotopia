package ca.uqam.casinotopia.serveur;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

import javax.swing.JFrame;

import ca.uqam.casinotopia.command.Command;
import ca.uqam.casinotopia.command.DemanderInformation;
import ca.uqam.casinotopia.commande.CmdUpdateCasesRoulette;
import ca.uqam.casinotopia.commande.Commande;
import ca.uqam.casinotopia.commande.CommandeClient;
import ca.uqam.casinotopia.commande.CommandeClientControleurClient;
import ca.uqam.casinotopia.commande.CommandeClientControleurRoulette;
import ca.uqam.casinotopia.commande.CommandeServeur;
import ca.uqam.casinotopia.commande.CommandeServeurControleurClient;
import ca.uqam.casinotopia.commande.CommandeServeurControleurRoulette;
import ca.uqam.casinotopia.connexion.Connexion;
import ca.uqam.casinotopia.controleur.Controleur;
import ca.uqam.casinotopia.controleur.ControleurClientClient;
import ca.uqam.casinotopia.controleur.ControleurClientServeur;
import ca.uqam.casinotopia.controleur.ControleurRouletteClient;
import ca.uqam.casinotopia.controleur.ControleurRouletteServeur;
import ca.uqam.casinotopia.modele.ServeurClientModel;

public class ServerThread extends Controleur implements Runnable {
	
	//private Connexion connexion;
	
	private int number = 0;
	
	private ControleurClientServeur ctrlClientServeur = new ControleurClientServeur(this.getConnexion());
	private ControleurRouletteServeur ctrlRouletteServeur = new ControleurRouletteServeur(this.getConnexion());
	
	private ServeurClientModel model = new ServeurClientModel();
	
	public ServerThread(Socket clientSocket, int number) {
		setConnexion(new Connexion(clientSocket));
		this.number = number;
	}
	
	@Override
	public void run() {
		try {
			System.out.println("client no "+number+" connecté");
			//premiereAction(new DemanderInformation());
			//this.envoyerCommande(new CmdUpdateCasesRoulette());
			while(getConnexion().isConnected()) {
				Commande cmd = null;
	            try {
	            	System.out.println("Avant de bloquer en attente SERVEUR");
					cmd = (Commande) this.getConnexion().getObjectInputStream().readObject();
					System.out.println("J'ai recu une commande sul SERVEUR");
		            if(cmd != null) {
		            	if(cmd instanceof CommandeServeur) {
			            	if(cmd instanceof CommandeServeurControleurClient) {
			            		//cmd.action(new ControleurClientServeur(this.getConnexion()), new JFrame());
			            		cmd.action(this.ctrlClientServeur, new JFrame());
			            	}
			            	else if(cmd instanceof CommandeServeurControleurRoulette) {
			            		//cmd.action(new ControleurRouletteServeur(this.getConnexion()), new JFrame());
			            		cmd.action(this.ctrlRouletteServeur, new JFrame());
			            	}
		            	}
		            	else {
		            		System.err.println("Seulement des commandes destinées aux clients sont recevables!");
		            	}
		            }
		            else{
		            	System.err.println("la commande envoyé n'est pas valide");
		            }
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SocketException e) {
					// TODO Auto-generated catch block
					System.out.println("déconnexion du client "+number);
					getConnexion().close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Thread.sleep(1);//sauve du cpu
			}
		/*} catch (SocketException e) {
			// TODO Auto-generated catch block
			System.out.println("déconnexion du client "+number);
			getConnexion().close();
		} catch (IOException e1) {
			System.err.println("IOException e1");
			e1.printStackTrace();*/
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @return the model
	 */
	public ServeurClientModel getModel() {
		return model;
	}

	/**
	 * @param model the model to set
	 */
	public void setModel(ServeurClientModel model) {
		this.model = model;
	}
	

}
