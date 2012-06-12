package ca.uqam.casinotopia.objet;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ca.uqam.casinotopia.modele.client.ModeleClientClient;

public class ListeAmisClient implements Serializable {
	
	private static final long serialVersionUID = -5810268166115357333L;
	
	private List<ModeleClientClient> lstClients;

	public ListeAmisClient() {
		this.lstClients = new ArrayList<ModeleClientClient>();
	}
	
	public void ajouterAmi(ModeleClientClient client) {
		this.lstClients.add(client);
	}
	
	public void retirerAmi(ModeleClientClient client) {
		this.lstClients.remove(client);
	}

	public List<ModeleClientClient> getLstClients() {
		return this.lstClients;
	}
}