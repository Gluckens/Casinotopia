package ca.uqam.casinotopia.command;

import java.io.Serializable;

import ca.uqam.casinotopia.client.MainClient;
import ca.uqam.casinotopia.controleur.Controleur;

public class InformationNomValide implements Command,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8286996855123056751L;
	String message = "";
	
	public InformationNomValide(String message) {
		this.message = message;
	}
	
	@Override
	public void action(Controleur controleur) {
		
		((MainClient)controleur).getConnexionFrame().getLblInformations().setText(message);
		((MainClient)controleur).getConnexionFrame().getLblInformations().setVisible(true);
	}

}
