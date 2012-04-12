package ca.uqam.casinotopia.command;

import java.io.Serializable;

import ca.uqam.casinotopia.controleur.Controleur;

public class EnvoyerMessage implements Command, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -768596385780977825L;
	private String message;
	public EnvoyerMessage(String message) {
		this.message = message;
	}
	

	@Override
	public void action(Controleur controleur) {
		System.out.println(message);
	}

}
