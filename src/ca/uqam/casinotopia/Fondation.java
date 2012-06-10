package ca.uqam.casinotopia;

import java.io.Serializable;

/**
 * 
 * @author Alexei
 */
public class Fondation implements Serializable {
	
	private static final long serialVersionUID = -816700186537050374L;
	
	private int id;
	private String nom;
	private String description;

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

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Fondation : nom " + this.getNom() + ", description " + this.getDescription();
	}

}
