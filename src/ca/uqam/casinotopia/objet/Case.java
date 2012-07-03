package ca.uqam.casinotopia.objet;

import java.io.Serializable;

import ca.uqam.casinotopia.type.TypeCase;
import ca.uqam.casinotopia.type.TypeCouleurCase;
import ca.uqam.casinotopia.type.TypePariteCase;

/**
 * Regroupe les informations sur une case de la table de jeu de roulette
 */
public class Case implements Serializable {
	
	private static final long serialVersionUID = -266176186655432444L;
	
	/**
	 * Le numéro associé à la case
	 */
	private int numero;
	
	/**
	 * La couleur de la case
	 */
	private TypeCouleurCase couleur;
	
	/**
	 * La parité de la case
	 */
	private TypePariteCase parite;
	
	/**
	 * Le type de case : Chiffre, couleur, parité
	 */
	private TypeCase type;
	
	/**
	 * Le multiplicateur des gains lorsque gagnante
	 */
	private int multiplicateurGain;
	
	/**
	 * Constructeur de numéro
	 * 
	 * @param numero Le numéro de la case
	 * @param couleur La couleur de la case
	 * @param type Le type de la case
	 * @param multiplicateurGain Le multiplicateur de gains de la case
	 */
	public Case(int numero, TypeCouleurCase couleur, TypeCase type, int multiplicateurGain) {
		this.numero = numero;
		this.couleur = couleur;
		this.type = type;
		this.multiplicateurGain = multiplicateurGain;
	}
	
	/**
	 * Constructeur de couleur
	 * 
	 * @param couleur La couleur de la case
	 * @param type Le type de la case
	 * @param multiplicateurGain Le multiplicateur de gains de la case
	 */
	public Case(TypeCouleurCase couleur, TypeCase type, int multiplicateurGain) {
		this.couleur = couleur;
		this.type = type;
		this.multiplicateurGain = multiplicateurGain;
	}
	
	/**
	 * Constructeur de parité
	 * 
	 * @param parite La parité de la case
	 * @param type Le type de la case
	 * @param multiplicateurGain Le multiplicateur de gains de la case
	 */
	public Case(TypePariteCase parite, TypeCase type, int multiplicateurGain) {
		this.type = type;
		this.parite = parite;
		this.multiplicateurGain = multiplicateurGain;
	}

	@Override
	public String toString() {
		String retour = "";
		switch (this.type) {
			case CHIFFRE:
				retour = String.valueOf(this.numero);
				break;
			case COULEUR:
				retour = this.couleur.toString();
				break;
			case PARITE:
				retour = this.parite.toString();
				break;
		}

		return retour;
	}

	public boolean estPaire() {
		return (this.numero % 2 == 0);
	}

	public int getNumero() {
		return this.numero;
	}

	public TypeCouleurCase getCouleur() {
		return this.couleur;
	}

	public TypeCase getType() {
		return this.type;
	}
	
	public int getMultiplicateurGain() {
		return this.multiplicateurGain;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((couleur == null) ? 0 : couleur.hashCode());
		result = prime * result + multiplicateurGain;
		result = prime * result + numero;
		result = prime * result + ((parite == null) ? 0 : parite.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Case other = (Case) obj;
		if (couleur != other.couleur)
			return false;
		if (multiplicateurGain != other.multiplicateurGain)
			return false;
		if (numero != other.numero)
			return false;
		if (parite != other.parite)
			return false;
		if (type != other.type)
			return false;
		return true;
	}
}