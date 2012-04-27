package ca.uqam.casinotopia;

import ca.uqam.casinotopia.modele.serveur.ModeleClientServeur;

public class JoueurRoulette extends JoueurServeur {
	
	private static final long serialVersionUID = -780970187450291078L;
	
	private boolean misesTerminees;
	
	//TODO Mise dans joueur???
	//private Vector<Case> mises = new Vector<Case>();

	public JoueurRoulette(ModeleClientServeur client, Partie partie) {
		super(client, partie);
		this.setMisesTerminees(false);
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