package ca.uqam.casinotopia;

import java.io.Serializable;

public class Case implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8705295158111915319L;
	private int numero;
	private String couleur;
	private boolean boolPaires;
	private TypeCase type;
	
	public Case (int numero, String couleur, boolean boolPaires) {
		this.numero = numero;
		this.couleur = couleur;
		this.boolPaires = boolPaires;
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
		
		//System.out.println(noNumero+noCouleur+noPaire);
		
		return Integer.parseInt(noNumero+noCouleur+noPaire);
	}
}