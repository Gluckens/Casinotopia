package ca.uqam.casinotopia;

import java.io.Serializable;

import ca.uqam.casinotopia.modele.serveur.ModeleClientServeur;

public class JoueurServeur implements Serializable {
	
	private static final long serialVersionUID = 3156168097836093668L;
	
	private ModeleClientServeur client;
	private Partie partie;
	
	public JoueurServeur(ModeleClientServeur client, Partie partie) {
		this.client = client;
		this.partie = partie;
	}
	
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