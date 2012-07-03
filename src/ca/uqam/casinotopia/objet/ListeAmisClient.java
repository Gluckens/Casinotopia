package ca.uqam.casinotopia.objet;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ca.uqam.casinotopia.modele.client.ModeleClientClient;

/**
 * Regroupe les informations sur une liste d'amis d'un client côté client
 */
public class ListeAmisClient implements Serializable {
	
	private static final long serialVersionUID = -4240442991200830831L;
	
	/**
	 * Liste des clients amis
	 */
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