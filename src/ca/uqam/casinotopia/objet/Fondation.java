package ca.uqam.casinotopia.objet;

import java.io.Serializable;

/**
 * Regroupe les informations sur les fondations
 */
public class Fondation implements Serializable {
	
	private static final long serialVersionUID = 5092416676324724960L;
	
	/**
	 * Id de la fondation
	 */
	private int id;
	
	/**
	 * Nom de la fondation
	 */
	private String nom;
	
	/**
	 * Description de la fondation
	 */
	private String description;
	
	public Fondation(String nom, String description) {
		this(-1, nom, description);
	}
	
	public Fondation(int id, String nom, String description) {
		this.id = id;
		this.nom = nom;
		this.description = description;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public String getNom() {
		return this.nom;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	@Override
	public String toString() {
		return "Fondation : nom " + this.getNom() + ", description " + this.getDescription();
	}
}