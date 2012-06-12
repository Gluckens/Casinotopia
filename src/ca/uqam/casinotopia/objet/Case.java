package ca.uqam.casinotopia.objet;

import java.io.Serializable;

import ca.uqam.casinotopia.type.TypeCase;
import ca.uqam.casinotopia.type.TypeCouleurCase;
import ca.uqam.casinotopia.type.TypePariteCase;

public class Case implements Serializable {
	
	private static final long serialVersionUID = -3295656245096943860L;
	
	private int numero;
	private TypeCouleurCase couleur;
	private TypePariteCase parite;
	private TypeCase type;
	private int multiplicateurGain;
	
	/**
	 * Constructeur de numero
	 * @param numero
	 * @param type
	 */
	public Case(int numero, TypeCase type, int multiplicateurGain) {
		this.numero = numero;
		this.type = type;
		this.multiplicateurGain = multiplicateurGain;
	}
	
	public Case(int numero, TypeCouleurCase couleur, TypeCase type, int multiplicateurGain) {
		this.numero = numero;
		this.couleur = couleur;
		this.type = type;
		this.multiplicateurGain = multiplicateurGain;
	}
	
	/**
	 * Constructeur de couleur
	 * @param couleur
	 * @param type
	 * @param lstCases
	 */
	public Case(TypeCouleurCase couleur, TypeCase type, int multiplicateurGain) {
		this.couleur = couleur;
		this.type = type;
		this.multiplicateurGain = multiplicateurGain;
	}
	
	/**
	 * Constructeur de parité
	 * @param type
	 * @param lstCases
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

/*	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((couleur == null) ? 0 : couleur.hashCode());
		long temp;
		temp = Double.doubleToLongBits(multiplicateurGain);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + numero;
		result = prime * result + ((parite == null) ? 0 : parite.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}*/

//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Case other = (Case) obj;
//		if (couleur != other.couleur)
//			return false;
//		if (Double.doubleToLongBits(multiplicateurGain) != Double.doubleToLongBits(other.multiplicateurGain))
//			return false;
//		if (numero != other.numero)
//			return false;
//		if (parite != other.parite)
//			return false;
//		if (type != other.type)
//			return false;
//		return true;
//	}
	
/*	@Override
	public boolean equals(Object obj) {
		boolean egale = false;
		if (obj != null && obj.getClass()==this.getClass())
		{
			Case cObj = (Case) obj;
			if (cObj.getType() == TypeCase.COULEUR)
			{
				if (cObj.getCouleur()==this.getCouleur()){
					egale = true;
				}
			}
			else if (cObj.getType() == TypeCase.PARITE){
				if (cObj.estPaire()==this.estPaire()){
					egale = true;
				}
			}
			else if (cObj.getType() == TypeCase.CHIFFRE){
				if (cObj.getNumero()==this.getNumero()){
					egale = true;
				}
			}
		}
		return egale;
	}*/

	/*@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.couleur == null) ? 0 : this.couleur.hashCode());
		result = prime * result + this.numero;
		result = prime * result + ((this.type == null) ? 0 : this.type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		Case other = (Case) obj;
		if (this.couleur != other.couleur) {
			return false;
		}
		if (this.numero != other.numero) {
			return false;
		}
		if (this.type != other.type) {
			return false;
		}
		return true;
	}*/

	// TODO Vérifier les égalités

	/*
	 * public boolean equals(Object o) { if(o instanceof Case) { Case other =
	 * (Case) o; return (other.numero == this.numero &&
	 * other.couleur.equals(this.couleur) && other.type == this.type); }
	 * 
	 * return false; }
	 * 
	 * public int hashCode() {
	 * 
	 * String noNumero = String.valueOf(this.numero); String noCouleur = "";
	 * String noPaire = ""; String noType = "";
	 * 
	 * if(this.couleur.equals(TypeCouleurCase.ROUGE)) { noCouleur = "0"; } else
	 * if(this.couleur.equals(TypeCouleurCase.NOIRE)) { noCouleur = "1"; }
	 * 
	 * if(!this.estPaire()) { noPaire = "0"; } else { noPaire = "1"; }
	 * switch(this.type){ case CHIFFRE : noType = "0"; break; case COULEUR :
	 * noType = "1"; break; case PARITE : noType = "2"; break; }
	 * 
	 * return Integer.parseInt(noNumero + noCouleur + noPaire + noType); }
	 */
}