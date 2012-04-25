package ca.uqam.casinotopia.observateur;

import java.io.Serializable;

public interface Observable extends Serializable {
	public void ajouterObservateur(Observateur obs);

	public void retirerObservateur(Observateur obs);

	public boolean estObservePar(Observateur obs);

	public void notifierObservateur();
}
