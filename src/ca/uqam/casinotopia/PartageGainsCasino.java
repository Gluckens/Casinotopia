package ca.uqam.casinotopia;

import ca.uqam.casinotopia.objet.Fondation;

/**
 * Regroupe les informations d'un partage de gains du casino
 */
public class PartageGainsCasino {
	
	/**
	 * Id du partage
	 */
	private int id;
	
	/**
	 * Compte casino associé au partage
	 */
	private CompteCasino compte;
	
	/**
	 * Fondation associée au partage
	 */
	private Fondation fondation;
	
	/**
	 * Pourcentage de gains envoyés à la fondation
	 */
	private int pourcentage;
	
	public PartageGainsCasino(CompteCasino compte, Fondation fondation, int pourcentage) {
		this(-1, compte, fondation, pourcentage);
	}
	
	public PartageGainsCasino(int id, CompteCasino compte, Fondation fondation, int pourcentage) {
		this.id = id;
		this.compte = compte;
		this.fondation = fondation;
		this.pourcentage = pourcentage;
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public void setCompteCasino(CompteCasino compte) {
		this.compte = compte;
	}

	public CompteCasino getCompteCasino() {
		return this.compte;
	}

	public void setFondation(Fondation fondation) {
		this.fondation = fondation;
	}

	public Fondation getFondation() {
		return this.fondation;
	}

	public int getPourcentage() {
		return this.pourcentage;
	}

	public void setPourcentage(int pourcentage) {
		this.pourcentage = pourcentage;
	}
}