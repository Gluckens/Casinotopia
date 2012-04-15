package ca.uqam.casinotopia.serveur;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

import javax.swing.JFrame;

import ca.uqam.casinotopia.commande.CmdUpdateMisesRoulette;
import ca.uqam.casinotopia.commande.Commande;
import ca.uqam.casinotopia.connexion.Connexion;
import ca.uqam.casinotopia.controleur.Controleur;
import ca.uqam.casinotopia.modele.ServeurClientModel;

public class ServerThread extends Controleur implements Runnable {
	
	//private Connexion connexion;
	
	private int number = 0;
	
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
			this.envoyerCommande(new CmdUpdateMisesRoulette());
			while(getConnexion().isConnected()) {
				Commande cmd = null;
	            try {
					cmd = (Commande) this.getConnexion().getObjectInputStream().readObject();
		            if(cmd != null) {
						cmd.action(this);//, new JFrame());
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
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			System.out.println("déconnexion du client "+number);
			getConnexion().close();
		} catch (IOException e1) {
			System.err.println("IOException e1");
			e1.printStackTrace();
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
