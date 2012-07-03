package ca.uqam.casinotopia;

import ca.uqam.casinotopia.modele.serveur.ModeleClientServeur;
import ca.uqam.casinotopia.objet.JoueurClient;
import ca.uqam.casinotopia.objet.JoueurRouletteClient;
import ca.uqam.casinotopia.objet.PartieClient;
import ca.uqam.casinotopia.type.TypeCouleurJoueurRoulette;

/**
 * Repr�sente un joueur de roulette c�t� serveur
 */
public class JoueurRoulette extends JoueurServeur {
	
	/**
	 * La couleur du joueur
	 */
	private TypeCouleurJoueurRoulette couleur;
	
	/**
	 * D�termine si le joueur a termin� de miser
	 */
	private boolean misesTerminees;

	public JoueurRoulette(ModeleClientServeur client, Partie partie, TypeCouleurJoueurRoulette couleur) {
		super(client, partie);
		this.couleur = couleur;
		this.misesTerminees = false;
	}
	
	/**
	 * Cr�er la version client du mod�le de joueur de roulette
	 * 
	 * @param partieClient La partie associ�e au joueur
	 * @return La version client du mod�le
	 */
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

	public boolean isMisesTerminees() {
		return this.misesTerminees;
	}

	public void setMisesTerminees(boolean misesTerminees) {
		this.misesTerminees = misesTerminees;
	}
}