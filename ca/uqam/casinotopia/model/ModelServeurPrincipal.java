package ca.uqam.casinotopia.model;

import java.util.HashMap;
import java.util.Map;

import ca.uqam.casinotopia.Clavardage;
import ca.uqam.casinotopia.modele.Model;


public class ModelServeurPrincipal implements Model {

	Map<String, Clavardage> chats = new HashMap<String, Clavardage>();

	public Clavardage getChat(String salle) {
		//si le chat n'existe pas on le crée
		if(!chats.containsKey(salle)){
			chats.put(salle, new Clavardage(salle));
		}
		return chats.get(salle);
	}
	
	
	
}
