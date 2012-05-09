package ca.uqam.casinotopia;

import java.io.Serializable;

import ca.uqam.casinotopia.modele.serveur.ModeleClientServeur;

/**
 * 
 * @author Alexei
 */
public class PartageGainsClient implements Serializable {
	
	private static final long serialVersionUID = -5080255358704810523L;
	
	private int pourcentage;
	public ModeleClientServeur client;
	public Fondation fondation;

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