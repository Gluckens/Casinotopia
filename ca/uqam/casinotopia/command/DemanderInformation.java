package ca.uqam.casinotopia.command;

import java.io.Serializable;

import ca.uqam.casinotopia.client.MainClient;
import ca.uqam.casinotopia.console.CInput;
import ca.uqam.casinotopia.controleur.Controleur;
import ca.uqam.casinotopia.vue.ConnexionFrame;

public class DemanderInformation implements Command, Serializable{



	/**
	 * 
	 */
	private static final long serialVersionUID = -3244283363812622821L;

	@Override
	public void action(Controleur controleur) {
		
		ConnexionFrame connexionFrame = new ConnexionFrame(controleur);
		((MainClient) controleur).setConnexionFrame(connexionFrame);
		
		connexionFrame.setVisible(true);
		
		/*
		System.out.println("Quel est ton nom?");
		
		String message = CInput.readline();
		
		
		Command cmd = new EnvoyerUsername(message);
		controleur.getConnexion().envoyerCommand(cmd);
		*/
	}


	
	
}