package ca.uqam.casinotopia.modele.serveur;

import java.util.HashMap;
import java.util.Map;

import ca.uqam.casinotopia.objet.Clavardage;

/**
 * Regroupe les informations g�n�rales sur le serveur.
 */
//TODO Transf�rer les donn�es du controleur ici
public class ModeleServeurPrincipal {

	private Map<String, Clavardage> chats = new HashMap<String, Clavardage>();

	public Clavardage getChat(String salle) {
		// si le chat n'existe pas on le cr�e
		if (!this.chats.containsKey(salle)) {
			this.chats.put(salle, new Clavardage(salle));
		}
		return this.chats.get(salle);
	}
}