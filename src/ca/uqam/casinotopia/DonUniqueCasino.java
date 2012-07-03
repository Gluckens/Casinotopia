package ca.uqam.casinotopia;

import ca.uqam.casinotopia.objet.Fondation;

/**
 * Regroupe les informations d'un don du casino
 */
@SuppressWarnings("unused")
public class DonUniqueCasino {
	
	/**
	 * Montant du don
	 */
	private int montant;
	
	/**
	 * Compte casino associé au don
	 */
	public CompteCasino compteCasino;
	
	/**
	 * Fondation ayant reçu le don
	 */
	public Fondation fondation;

	public void setUnnamed_CompteCasino_(CompteCasino compteCasino) {
		this.compteCasino = compteCasino;
	}

	public CompteCasino getCompteCasino() {
		return this.compteCasino;
	}

	public void setFondation_(Fondation fondation) {
		this.fondation = fondation;
	}

	public Fondation getFondation() {
		return this.fondation;
	}
}