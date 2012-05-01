package ca.uqam.casinotopia;

import java.io.Serializable;

/**
 * 
 * @author Alexei
 */
public class Avatar implements Serializable {
	
	private static final long serialVersionUID = 6908585380935996326L;
	
	private int id;
	private String nomImage;
	private String texte;

	public int getId() {
		return this.id;
	}

	public String getNomImage() {
		return this.nomImage;
	}

	public String getTexte() {
		return this.texte;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNomImage(String nomImage) {
		this.nomImage = nomImage;
	}

	public void setTexte(String texte) {
		this.texte = texte;
	}

}
