package ca.uqam.casinotopia.command;

import java.io.Serializable;

import ca.uqam.casinotopia.console.CInput;
import ca.uqam.casinotopia.controleur.Controleur;

public class DemanderUsername implements Command, Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = -7977107941947866560L;

	@Override
	public void action(Controleur controleur) {

		System.out.println("Quel est ton nom?");
		
		String message = CInput.readline();
		
		
		Command cmd = new EnvoyerUsername(message);
		controleur.getConnexion().envoyerCommand(cmd);
	}


	
	
}