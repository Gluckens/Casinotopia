package ca.uqam.casinotopia;

import java.io.Serializable;

import ca.uqam.casinotopia.modele.client.ModeleClientClient;

public class JoueurClient implements Serializable {
	
	private static final long serialVersionUID = -7961445341270338010L;
	
	private ModeleClientClient client;
	private PartieClient partie;
	
	public JoueurClient(ModeleClientClient client, PartieClient partie) {
		this.client = client;
		this.partie = partie;
	}
	
	/**
	 * @return the partie
	 */
	public ModeleClientClient getClient() {
		return this.client;
	}
	
	/**
	 * @return the partie
	 */
	public PartieClient getPartie() {
		return this.partie;
	}
	
	/**
	 * @return the id
	 */
	public int getId() {
		return this.client.getId();
	}
}