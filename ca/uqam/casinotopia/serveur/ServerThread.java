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
import ca.uqam.casinotopia.controleur.Controleur;
import ca.uqam.casinotopia.model.ServeurClientModel;

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
			premiereAction(new DemanderUsername());
			while(getConnexion().isConnected()){
				Command cmd = null;
	            try {
					cmd = (Command) getConnexion().getObjectInputStream().readObject();
		            if(cmd != null){
						cmd.action(this);
		            }else{
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


	public void premiereAction(Command cmd) throws IOException{

		getConnexion().getObjectOutputStream().writeObject(cmd);
		getConnexion().getObjectOutputStream().reset();
		
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
