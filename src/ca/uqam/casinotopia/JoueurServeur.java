package ca.uqam.casinotopia;

import ca.uqam.casinotopia.modele.serveur.ModeleClientServeur;
import ca.uqam.casinotopia.objet.JoueurClient;
import ca.uqam.casinotopia.objet.PartieClient;

/**
 * Classe abstraite représentant un joueur version serveur
 */
public abstract class JoueurServeur {
	
	/**
	 * Le client derrière le joueur
	 */
	protected ModeleClientServeur client;
	
	/**
	 * La partie dans lequel le joueur est
	 */
	protected Partie partie;
	
	public JoueurServeur(ModeleClientServeur client, Partie partie) {
		this.client = client;
		this.partie = partie;
	}
	
	public abstract JoueurClient creerModeleClient(PartieClient partieClient);
	
	public ModeleClientServeur getClient() {
		return this.client;
	}
	
	public Partie getPartie() {
		return this.partie;
	}
	
	public int getId() {
		return this.client.getId();
	}
}