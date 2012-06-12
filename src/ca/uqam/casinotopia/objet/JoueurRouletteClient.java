package ca.uqam.casinotopia.objet;

import ca.uqam.casinotopia.modele.client.ModeleClientClient;
import ca.uqam.casinotopia.type.TypeCouleurJoueurRoulette;

public class JoueurRouletteClient extends JoueurClient {
	
	private static final long serialVersionUID = 4069831466240710967L;
	
	private TypeCouleurJoueurRoulette couleur;
	private String pathImgJeton;
	private boolean misesTerminees;
	
	//TODO Mise dans joueur???
	//private Vector<Case> mises = new Vector<Case>();

	public JoueurRouletteClient(ModeleClientClient client, PartieClient partie, TypeCouleurJoueurRoulette couleur) {
		super(client, partie);
		this.couleur = couleur;
		//TODO lowercase?
		this.pathImgJeton = "jeton_" + this.couleur.toString() + ".png";
		this.misesTerminees = false;
	}
	
	public TypeCouleurJoueurRoulette getCouleur() {
		return this.couleur;
	}
	
	public String getPathImgJeton() {
		return this.pathImgJeton;
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