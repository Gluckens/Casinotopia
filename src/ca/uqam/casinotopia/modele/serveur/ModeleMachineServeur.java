package ca.uqam.casinotopia.modele.serveur;

import ca.uqam.casinotopia.modele.Modele;
import ca.uqam.casinotopia.observateur.Observable;
import ca.uqam.casinotopia.observateur.Observateur;
import ca.uqam.casinotopia.type.modif.TypeModif;

//TODO extends Partie
public class ModeleMachineServeur implements Modele, Observable {

	private static final long serialVersionUID = 8854771788955204109L;

	@Override
	public void ajouterObservateur(Observateur obs) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void retirerObservateur(Observateur obs) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean estObservePar(Observateur obs) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void notifierObservateur() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TypeModif getTypeModif() {
		// TODO Auto-generated method stub
		return null;
	}

}
