package ca.uqam.casinotopia.modele.client;

import ca.uqam.casinotopia.modele.Modele;
import ca.uqam.casinotopia.observateur.BaseObservable;
import ca.uqam.casinotopia.observateur.Observable;
import ca.uqam.casinotopia.observateur.Observateur;

public class ModeleClientClient implements Modele, Observable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7675443359819747622L;

	private String username;
	private String prenom;
	private String nom;
	private int solde;

	private BaseObservable sujet = new BaseObservable(this);

	public void initDefault() {

	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return this.username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the prenom
	 */
	public String getPrenom() {
		return this.prenom;
	}

	/**
	 * @param prenom
	 *            the prenom to set
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	/**
	 * @return the nom
	 */
	public String getNom() {
		return this.nom;
	}

	/**
	 * @param nom
	 *            the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @return the solde
	 */
	public int getSolde() {
		return this.solde;
	}

	/**
	 * @param solde
	 *            the solde to set
	 */
	public void setSolde(int solde) {
		this.solde = solde;
	}

	@Override
	public void ajouterObservateur(Observateur obs) {
		this.sujet.ajouterObservateur(obs);
	}

	@Override
	public void retirerObservateur(Observateur obs) {
		this.sujet.retirerObservateur(obs);
	}

	@Override
	public boolean estObservePar(Observateur obs) {
		return this.sujet.estObservePar(obs);
	}

	@Override
	public void notifierObservateur() {
		this.sujet.notifierObservateur();
	}
}
