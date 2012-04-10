package ca.uqam.casinotopia.model;

import ca.uqam.casinotopia.Utilisateur;

public class ServeurClientModel implements Model {
	private Utilisateur utilisateur;
	
	
	public ServeurClientModel() {
		// TODO Auto-generated constructor stub
	}


	/**
	 * @return the user
	 */
	public Utilisateur getUser() {
		return utilisateur;
	}


	/**
	 * @param user the user to set
	 */
	public void setUser(Utilisateur user) {
		this.utilisateur = user;
	}
	
	
	
}
