package ca.uqam.casinotopia.objet;

import java.io.Serializable;

import ca.uqam.casinotopia.modele.client.ModeleClientClient;

/**
 * Classe abstraite représentant un joueur version client
 */
public abstract class JoueurClient implements Serializable {
	
	private static final long serialVersionUID = 3922981401732700239L;
	
	/**
	 * Le client derrière le joueur
	 */
	private ModeleClientClient client;
	
	/**
	 * La partie dans lequel le joueur est
	 */
	private PartieClient partie;
	
	public JoueurClient(ModeleClientClient client, PartieClient partie) {
		this.client = client;
		this.partie = partie;
	}
	
	public ModeleClientClient getClient() {
		return this.client;
	}
	
	public PartieClient getPartie() {
		return this.partie;
	}
	
	public int getId() {
		return this.client.getId();
	}
}