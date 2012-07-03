package ca.uqam.casinotopia.objet;

import ca.uqam.casinotopia.modele.client.ModeleClientClient;
import ca.uqam.casinotopia.type.TypeCouleurJoueurRoulette;

/**
 * Représente un joueur de roulette côté client
 */
public class JoueurRouletteClient extends JoueurClient {
	
	private static final long serialVersionUID = 6920435969922018408L;
	
	/**
	 * La couleur du joueur
	 */
	private TypeCouleurJoueurRoulette couleur;
	
	/**
	 * Le path vers l'image de son jeton
	 */
	private String pathImgJeton;
	
	/**
	 * Détermine si le joueur a terminé de miser
	 */
	private boolean misesTerminees;

	public JoueurRouletteClient(ModeleClientClient client, PartieClient partie, TypeCouleurJoueurRoulette couleur) {
		super(client, partie);
		this.couleur = couleur;
		this.pathImgJeton = "jeton_" + this.couleur.toString() + ".png";
		this.misesTerminees = false;
	}
	
	public TypeCouleurJoueurRoulette getCouleur() {
		return this.couleur;
	}
	
	public String getPathImgJeton() {
		return this.pathImgJeton;
	}

	public boolean isMisesTerminees() {
		return this.misesTerminees;
	}

	public void setMisesTerminees(boolean misesTerminees) {
		this.misesTerminees = misesTerminees;
	}
}