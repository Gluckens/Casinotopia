package ca.uqam.casinotopia.controleur.serveur;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

import ca.uqam.casinotopia.commande.Commande;
import ca.uqam.casinotopia.connexion.Connexion;
import ca.uqam.casinotopia.controleur.ControleurServeur;
import ca.uqam.casinotopia.model.ModelServeurClient;
import ca.uqam.casinotopia.serveur.MainServeur;

public class ControleurServeurThread extends ControleurServeur implements Runnable {
		
	private ModelServeurClient model = new ModelServeurClient();
	
	public ControleurServeurThread(Socket clientSocket, int number) {
		super(null);
		setConnexion(new Connexion(clientSocket));
		this.model.number = number;
	}
	
	@Override
	public void run() {
		try {
			System.out.println("client no "+this.model.number+" connecté");
			while(getConnexion().isConnected()){
				Commande cmd = null;
	            try {
					cmd = (Commande) getConnexion().getObjectInputStream().readObject();
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
					System.out.println("déconnexion du client "+this.model.number);
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
	
	public void envoyerCommandATous(Commande cmd){
		for (int i = 0; i < MainServeur.NUMCONNEXION; i++) {
			if(MainServeur.thread[i] != null && 
					MainServeur.thread[i].isAlive() && 
					MainServeur.serverThread[i].getModel().getUtilisateur().getNomUtilisateur() != null){
				MainServeur.serverThread[i].getConnexion().envoyerCommand(cmd);
			}
		}
	}
	
}
