package ca.uqam.casinotopia;

import ca.uqam.casinotopia.modele.serveur.ModeleClientServeur;
import ca.uqam.casinotopia.objet.JoueurClient;
import ca.uqam.casinotopia.objet.PartieClient;

public abstract class JoueurServeur {
	
	protected ModeleClientServeur client;
	protected Partie partie;
	
	public JoueurServeur(ModeleClientServeur client, Partie partie) {
		this.client = client;
		this.partie = partie;
	}
	
	public abstract JoueurClient creerModeleClient(PartieClient partieClient);
	
	/**
	 * @return the partie
	 */
	public ModeleClientServeur getClient() {
		return this.client;
	}
	
	/**
	 * @return the partie
	 */
	public Partie getPartie() {
		return this.partie;
	}
	
	/**
	 * @return the id
	 */
	public int getId() {
		return this.client.getId();
	}
}