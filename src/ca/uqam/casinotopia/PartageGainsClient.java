package ca.uqam.casinotopia;

import ca.uqam.casinotopia.modele.serveur.ModeleClientServeur;
import ca.uqam.casinotopia.objet.Fondation;

/**
 * Regroupe les informations d'un partage de gains d'un utilisateur côté serveur
 */
public class PartageGainsClient {
	
	/**
	 * Id du partage
	 */
	private int id;
	
	/**
	 * Client associé au partage de gains
	 */
	private ModeleClientServeur client;
	
	/**
	 * Fondation associée au partage de gains
	 */
	private Fondation fondation;
	
	/**
	 * Pourcentage des gains envoyé à la fondation
	 */
	private int pourcentage;
	
	public PartageGainsClient(ModeleClientServeur client, Fondation fondation, int pourcentage) {
		this(-1, client, fondation, pourcentage);
	}
	
	public PartageGainsClient(int id, ModeleClientServeur client, Fondation fondation, int pourcentage) {
		this.id = id;
		this.client = client;
		this.fondation = fondation;
		this.pourcentage = pourcentage;
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public ModeleClientServeur getClient() {
		return this.client;
	}

	public void setClient(ModeleClientServeur client) {
		this.client = client;
	}

	public Fondation getFondation() {
		return this.fondation;
	}

	public void setFondation(Fondation fondation) {
		this.fondation = fondation;
	}

	public int getPourcentage() {
		return this.pourcentage;
	}

	public void setPourcentage(int pourcentage) {
		this.pourcentage = pourcentage;
	}

	@Override
	public String toString() {
		return this.client.getPrenom() + " " + this.fondation.getNom() + " : " + this.getPourcentage();
	}
}