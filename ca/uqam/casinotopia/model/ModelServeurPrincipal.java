package ca.uqam.casinotopia.model;

import ca.uqam.casinotopia.Clavardage;
import ca.uqam.casinotopia.modele.Model;


public class ModelServeurPrincipal implements Model {

	Clavardage chat = new Clavardage();

	public Clavardage getChat() {
		return chat;
	}
	
	
	
}
