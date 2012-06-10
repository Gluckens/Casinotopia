package ca.uqam.casinotopia;

import java.io.Serializable;
import java.util.Vector;

import ca.uqam.casinotopia.modele.serveur.ModeleClientServeur;

public class ListeAmis implements Serializable {
	
	private static final long serialVersionUID = -5810268166115357333L;
	
	private Vector<ModeleClientServeur> clients = new Vector<ModeleClientServeur>();

	public Vector<ModeleClientServeur> getClients() {
		return this.clients;
	}

	public void setClients(Vector<ModeleClientServeur> clients) {
		this.clients = clients;
	}
}
