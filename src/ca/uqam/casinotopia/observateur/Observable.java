package ca.uqam.casinotopia.observateur;

import java.io.Serializable;

import ca.uqam.casinotopia.modif.TypeModif;

public interface Observable extends Serializable {
	
	public void ajouterObservateur(Observateur obs);

	public void retirerObservateur(Observateur obs);

	public boolean estObservePar(Observateur obs);

	public void notifierObservateur();
	
	public TypeModif getTypeModif();
}
