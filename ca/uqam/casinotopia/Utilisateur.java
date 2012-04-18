package ca.uqam.casinotopia;

import java.io.Serializable;

import ca.uqam.casinotopia.connexion.Connexion;

public class Utilisateur{
	
	private String nomUtilisateur = null;
	
	private Connexion connexion;
	
	public Utilisateur() {
		
	}
	
	public Utilisateur(String nomUtilisateur, Connexion connexion) {
		this.nomUtilisateur = nomUtilisateur;
		this.connexion = connexion;
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
	
	public Connexion getConnexion() {
		return connexion;
	}
	
}