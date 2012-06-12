package ca.uqam.casinotopia;

import java.io.Serializable;

import ca.uqam.casinotopia.modele.serveur.ModeleClientServeur;
import ca.uqam.casinotopia.objet.Fondation;

public class PartageGainsClient implements Serializable {
	
	private static final long serialVersionUID = -5080255358704810523L;
	
	private int id;
	private ModeleClientServeur client;
	private Fondation fondation;
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