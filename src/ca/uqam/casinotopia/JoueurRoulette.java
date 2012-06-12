package ca.uqam.casinotopia;

import ca.uqam.casinotopia.modele.serveur.ModeleClientServeur;
import ca.uqam.casinotopia.objet.JoueurClient;
import ca.uqam.casinotopia.objet.JoueurRouletteClient;
import ca.uqam.casinotopia.objet.PartieClient;
import ca.uqam.casinotopia.type.TypeCouleurJoueurRoulette;

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
	
	public JoueurClient creerModeleClient(PartieClient partieClient) {
		return new JoueurRouletteClient(
				this.client.creerModeleClient(),
				partieClient,
				this.getCouleur()
		);
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