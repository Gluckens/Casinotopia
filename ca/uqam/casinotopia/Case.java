package ca.uqam.casinotopia;

import java.io.Serializable;

public class Case implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1687051630860379950L;
	private int numero;
	private String couleur;
	private boolean boolPaires;
	private TypeCase type;
	
	public Case (int numero, String couleur, boolean boolPaires, TypeCase type) {
		this.numero = numero;
		this.couleur = couleur;
		this.boolPaires = boolPaires;
		
		this.type = type;
	}
	
	public String toString() {
		String retour = "";
		switch(this.type) {
			case CHIFFRE :
				retour = String.valueOf(this.numero);
				break;
			case COULEUR :
				retour = this.couleur;
				break;
			case PAIR_IMPAIR :
				retour = (this.boolPaires ? "paires" : "impaires");
				break;
		}
		
		return retour;
	}
	
	
	public boolean equals(Object o) {
		if(o instanceof Case) {
			Case other = (Case) o;
			return (other.numero == this.numero && other.couleur.equals(this.couleur) && other.boolPaires == this.boolPaires);
		}
		
		return false;
	}
	
	public int hashCode() {

		String noNumero = String.valueOf(this.numero);
		String noCouleur = ""; 
		String noPaire = "";
		
		if(this.couleur.equals("noire")) {
			noCouleur = "0";
		}
		else if(this.couleur.equals("rouge")) {
			noCouleur = "1";
		}
		
		if(!this.boolPaires) {
			noPaire = "0";
		}
		else {
			noPaire = "1";
		}
		
		return Integer.parseInt(noNumero+noCouleur+noPaire);
	}
}