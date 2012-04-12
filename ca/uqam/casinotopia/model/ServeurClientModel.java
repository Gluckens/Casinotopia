package ca.uqam.casinotopia.model;


import ca.uqam.casinotopia.Utilisateur;

public class ServeurClientModel implements Model {
	private Utilisateur utilisateur = new Utilisateur();
	
	
	public ServeurClientModel() {
		// TODO Auto-generated constructor stub
	}


	/**
	 * @return the utilisateur
	 */
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}


	/**
	 * @param utilisateur the utilisateur to set
	 */
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	
	
	
}
