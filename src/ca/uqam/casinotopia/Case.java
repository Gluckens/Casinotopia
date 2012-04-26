package ca.uqam.casinotopia;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Case implements Serializable {

	private static final long serialVersionUID = -1687051630860379950L;
	
	private int numero;
	private TypeCouleurCase couleur;
	private TypePariteCase parite;
	private TypeCase type;
	private List<Case> lstCases;
	private double multiplicateurGain;
	
	/**
	 * Constructeur de numero
	 * @param numero
	 * @param type
	 */
	public Case(int numero, TypeCase type, double multiplicateurGain) {
		this.numero = numero;
		this.type = type;
		this.multiplicateurGain = multiplicateurGain;
	}
	
	/**
	 * Constructeur de couleur
	 * @param couleur
	 * @param type
	 * @param lstCases
	 */
	public Case(TypeCouleurCase couleur, TypeCase type, double multiplicateurGain) {
		this.couleur = couleur;
		this.type = type;
		this.multiplicateurGain = multiplicateurGain;
	}
	
	/**
	 * Constructeur de parité
	 * @param type
	 * @param lstCases
	 */
	public Case(TypePariteCase parite, TypeCase type, double multiplicateurGain) {
		this.type = type;
		this.parite = parite;
		this.multiplicateurGain = multiplicateurGain;
	}
	
	/**
	 * Constructeur de couleur
	 * @param couleur
	 * @param type
	 * @param lstCases
	 */
	public Case(TypeCouleurCase couleur, TypeCase type, List<Case> lstCases, double multiplicateurGain) {
		this.couleur = couleur;
		this.type = type;
		this.lstCases = lstCases;
		this.multiplicateurGain = multiplicateurGain;
	}
	
	/**
	 * Constructeur de parité
	 * @param type
	 * @param lstCases
	 */
	public Case(TypeCase type, List<Case> lstCases, double multiplicateurGain) {
		this.type = type;
		this.lstCases = lstCases;
		this.multiplicateurGain = multiplicateurGain;
	}

	// TODO quand une case représente une couleur, je fait koi?
	// Serait mieux de faire une liste de numero toujours, et si le type est
	// numero il n'y en aurait qu'un seul
	// Ou plutot une liste de case?
	public Case(int numero, TypeCouleurCase couleur, TypeCase type, double multiplicateurGain) {
		this(numero, couleur, type, new ArrayList<Case>(), multiplicateurGain);
	}

	public Case(int numero, TypeCouleurCase couleur, TypeCase type, List<Case> lstCases, double multiplicateurGain) {
		this.numero = numero;
		this.couleur = couleur;

		this.type = type;

		this.lstCases = lstCases;
		
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.couleur == null) ? 0 : this.couleur.hashCode());
		result = prime * result + ((this.lstCases == null) ? 0 : this.lstCases.hashCode());
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
		if (this.lstCases == null) {
			if (other.lstCases != null) {
				return false;
			}
		}
		else if (!this.lstCases.equals(other.lstCases)) {
			return false;
		}
		if (this.numero != other.numero) {
			return false;
		}
		if (this.type != other.type) {
			return false;
		}
		return true;
	}

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