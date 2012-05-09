package ca.uqam.casinotopia;

import ca.uqam.casinotopia.modele.serveur.ModeleClientServeur;

public class JoueurRoulette extends JoueurServeur {

	private static final long serialVersionUID = 6359607753125692254L;
	
	private TypeCouleurJoueurRoulette couleur;
	private boolean misesTerminees;
	
	//TODO Mise dans joueur???
	//private Vector<Case> mises = new Vector<Case>();

	public JoueurRoulette(ModeleClientServeur client, Partie partie, TypeCouleurJoueurRoulette couleur) {
		super(client, partie);
		this.couleur = couleur;
		this.misesTerminees = false;
	}
	
	public TypeCouleurJoueurRoulette getCouleur() {
		return this.couleur;
	}

	/**
	 * @return the misesTerminees
	 */
	public boolean isMisesTerminees() {
		return this.misesTerminees;
	}

	/**
	 * @param misesTerminees the misesTerminees to set
	 */
	public void setMisesTerminees(boolean misesTerminees) {
		this.misesTerminees = misesTerminees;
	}
}