package ca.uqam.casinotopia.model;


import ca.uqam.casinotopia.Utilisateur;

public class ModelServeurClient implements Model {

	public int number = 0;
	private Utilisateur utilisateur = new Utilisateur();
	
	public ModelServeurClient() {
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
