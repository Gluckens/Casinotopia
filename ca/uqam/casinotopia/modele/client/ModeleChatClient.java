package ca.uqam.casinotopia.modele.client;

import ca.uqam.casinotopia.modele.Modele;
import ca.uqam.casinotopia.observateur.BaseObservable;
import ca.uqam.casinotopia.observateur.Observable;
import ca.uqam.casinotopia.observateur.Observateur;

public class ModeleChatClient implements Modele, Observable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1197460785333129913L;

	private String salle;

	private BaseObservable sujet = new BaseObservable(this);

	public String getSalle() {
		return this.salle;
	}

	public void setSalle(String salle) {
		this.salle = salle;
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
