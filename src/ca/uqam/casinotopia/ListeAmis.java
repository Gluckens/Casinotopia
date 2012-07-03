package ca.uqam.casinotopia;

import java.util.List;
import java.util.Vector;

import ca.uqam.casinotopia.modele.serveur.ModeleClientServeur;

/**
 * Regroupe les informations sur une liste d'amis d'un client côté serveur
 */
public class ListeAmis {
	
	/**
	 * Liste des clients amis
	 */
	private List<ModeleClientServeur> lstClients;
	
	public ListeAmis() {
		this.lstClients = new Vector<ModeleClientServeur>();
	}
	
	public void ajouterAmi(ModeleClientServeur ami) {
		this.lstClients.add(ami);
	}
	
	public void retirerAmi(ModeleClientServeur ami) {
		this.lstClients.remove(ami);
	}

	public List<ModeleClientServeur> getLstClients() {
		return this.lstClients;
	}
}