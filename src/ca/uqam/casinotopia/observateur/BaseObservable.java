package ca.uqam.casinotopia.observateur;

import java.util.HashSet;
import java.util.Set;

import ca.uqam.casinotopia.modif.TypeModif;

public class BaseObservable implements Observable {

	private static final long serialVersionUID = -2611781316325159310L;

	Set<Observateur> observateurs = new HashSet<Observateur>();

	Observable sujetConcret;

	public BaseObservable(Observable sujetConcret) {
		this.sujetConcret = sujetConcret;
	}

	@Override
	public void ajouterObservateur(Observateur obs) {
		this.observateurs.add(obs);
	}

	@Override
	public void retirerObservateur(Observateur obs) {
		this.observateurs.remove(obs);
	}

	@Override
	public boolean estObservePar(Observateur obs) {
		return this.observateurs.contains(obs);
	}

	@Override
	public void notifierObservateur() {
		for (Observateur obs : this.observateurs) {
			obs.update(this.sujetConcret);
		}
	}

	@Override
	public TypeModif getTypeModif() {
		// TODO Auto-generated method stub
		return null;
	}

}
