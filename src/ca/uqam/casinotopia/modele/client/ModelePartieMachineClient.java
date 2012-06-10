package ca.uqam.casinotopia.modele.client;

import ca.uqam.casinotopia.modele.Modele;
import ca.uqam.casinotopia.modif.TypeModif;
import ca.uqam.casinotopia.observateur.Observable;
import ca.uqam.casinotopia.observateur.Observateur;

public class ModelePartieMachineClient implements Modele, Observable  {

	private static final long serialVersionUID = -8592873550374651424L;

	public ModelePartieMachineClient() {
		// TODO Auto-generated constructor stub
	}

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
