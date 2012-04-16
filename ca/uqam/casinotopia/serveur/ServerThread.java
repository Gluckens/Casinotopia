package ca.uqam.casinotopia.serveur;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

import javax.swing.JFrame;

/*import ca.uqam.casinotopia.command.Command;
import ca.uqam.casinotopia.command.DemanderInformation;*/
import ca.uqam.casinotopia.commande.Commande;
import ca.uqam.casinotopia.commande.CommandeServeur;
import ca.uqam.casinotopia.commande.CommandeServeurControleurClient;
import ca.uqam.casinotopia.commande.CommandeServeurControleurRoulette;
import ca.uqam.casinotopia.connexion.Connexion;
import ca.uqam.casinotopia.controleur.Controleur;
import ca.uqam.casinotopia.controleur.ControleurClientServeur;
import ca.uqam.casinotopia.controleur.ControleurRouletteServeur;
import ca.uqam.casinotopia.modele.ServeurClientModel;

public class ServerThread extends Controleur implements Runnable {
	
	//private Connexion connexion;
	
	private int number = 0;
	
	private ControleurClientServeur ctrlClientServeur;
	private ControleurRouletteServeur ctrlRouletteServeur;
	
	private ServeurClientModel model = new ServeurClientModel();
	
	public ServerThread(Socket clientSocket, int number) {
		this.setConnexion(new Connexion(clientSocket));
		this.number = number;
		
		this.ctrlClientServeur = new ControleurClientServeur(this.getConnexion());
		this.ctrlRouletteServeur = new ControleurRouletteServeur(this.getConnexion());
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
	            	System.out.println("ATTENTE DE COMMANDE DU CLIENT");
					cmd = (Commande) this.getConnexion().getObjectInputStream().readObject();
					System.out.println("COMMANDE CLIENT OBTENUE");
		            if(cmd != null) {
		            	if(cmd instanceof CommandeServeur) {
			            	if(cmd instanceof CommandeServeurControleurClient) {
			            		//cmd.action(this.ctrlClientServeur, new JFrame());
			            	}
			            	else if(cmd instanceof CommandeServeurControleurRoulette) {
			            		//cmd.action(this.ctrlRouletteServeur, new JFrame());
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
