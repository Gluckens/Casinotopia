package ca.uqam.casinotopia.commande.client;

import java.io.Serializable;

import ca.uqam.casinotopia.commande.Commande;
import ca.uqam.casinotopia.controleur.Controleur;

public class EnvoyerMessage implements Commande, Serializable {


	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7009862297908574682L;
	private String message;
	public EnvoyerMessage(String message) {
		this.message = message;
	}
	

	@Override
	public void action(Controleur controleur) {
		System.out.println(message);
	}

}
