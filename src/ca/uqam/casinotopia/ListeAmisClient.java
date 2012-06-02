package ca.uqam.casinotopia;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ca.uqam.casinotopia.modele.serveur.ModeleClientServeur;

public class ListeAmisClient implements Serializable {
	
	private static final long serialVersionUID = -5810268166115357333L;
	
	private List<ModeleClientServeur> lstClients;

	public ListeAmisClient() {
		this.lstClients = new ArrayList<ModeleClientServeur>();
	}
	
	public void ajouterAmi(ModeleClientServeur client) {
		this.lstClients.add(client);
	}
	
	public void retirerAmi(ModeleClientServeur client) {
		this.lstClients.remove(client);
	}

	public List<ModeleClientServeur> getLstClients() {
		return this.lstClients;
	}
}
