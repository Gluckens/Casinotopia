package ca.uqam.casinotopia.model;


import ca.uqam.casinotopia.Utilisateur;
import ca.uqam.casinotopia.connexion.Connexion;
import ca.uqam.casinotopia.modele.Model;

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
	public void setUtilisateur(String nomUtilisateur, Connexion connexion) {
		this.utilisateur = new Utilisateur(nomUtilisateur, connexion);
	}

	
	
	
}
