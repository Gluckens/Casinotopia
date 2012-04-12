package ca.uqam.casinotopia;

import java.io.Serializable;

public class Utilisateur implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8754627405172607798L;
	private String nomUtilisateur = null;
	private char[] motDePasse = null;
	
	public Utilisateur() {
		
	}
	
	public Utilisateur(String nomUtilisateur, char[] motDePasse) {
		this.nomUtilisateur = nomUtilisateur;
		this.motDePasse = motDePasse;
	}

	/**
	 * @return the nomUtilisateur
	 */
	public String getNomUtilisateur() {
		return nomUtilisateur;
	}
	
	/**
	 * @param nomUtilisateur the nomUtilisateur to set
	 */
	public void setNomUtilisateur(String nomUtilisateur) {
		this.nomUtilisateur = nomUtilisateur;
	}

	/**
	 * @return the motDePasse
	 */
	public char[] getMotDePasse() {
		return motDePasse;
	}

	/**
	 * @param motDePasse the motDePasse to set
	 */
	public void setMotDePasse(char[] motDePasse) {
		this.motDePasse = motDePasse;
	}
	
	
	
	
}