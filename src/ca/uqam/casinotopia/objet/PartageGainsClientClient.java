package ca.uqam.casinotopia.objet;

import java.io.Serializable;

import ca.uqam.casinotopia.modele.client.ModeleClientClient;

public class PartageGainsClientClient implements Serializable {
	
	private static final long serialVersionUID = 6389582743473593802L;
	
	private int id;
	private ModeleClientClient client;
	private Fondation fondation;
	private int pourcentage;
	
	public PartageGainsClientClient(ModeleClientClient client, Fondation fondation, int pourcentage) {
		this(-1, client, fondation, pourcentage);
	}
	
	public PartageGainsClientClient(int id, ModeleClientClient client, Fondation fondation, int pourcentage) {
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

	public ModeleClientClient getClient() {
		return this.client;
	}

	public void setClient(ModeleClientClient client) {
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