package ca.uqam.casinotopia.observateur;

import java.util.HashSet;
import java.util.Set;

public class BaseSujet implements Sujet {
	
	Set<Observateur> observateurs = new HashSet<Observateur>();

	@Override
	public void ajouterObservateur(Observateur obs) {
		this.observateurs.add(obs);
	}

	@Override
	public void retirerObservateur(Observateur obs) {
		this.observateurs.remove(obs);
	}

	@Override
	public void notifierObservateur() {
		for(Observateur obs : this.observateurs) {
			obs.update(this);
		}
	}

}
