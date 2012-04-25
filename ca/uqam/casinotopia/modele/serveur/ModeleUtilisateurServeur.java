package ca.uqam.casinotopia.modele.serveur;

import ca.uqam.casinotopia.Utilisateur;

public class ModeleUtilisateurServeur {

	public int number = 0;
	private Utilisateur utilisateur = new Utilisateur();

	public ModeleUtilisateurServeur() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the utilisateur
	 */
	public Utilisateur getUtilisateur() {
		return this.utilisateur;
	}

	/**
	 * @param utilisateur
	 *            the utilisateur to set
	 */
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

}
