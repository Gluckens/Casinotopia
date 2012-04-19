package ca.uqam.casinotopia.modele.client;

import ca.uqam.casinotopia.modele.Modele;
import ca.uqam.casinotopia.observateur.Observateur;

public class ModeleChatClient implements Modele{

	private String salle;
	
	
	
	
	
	
	
	public String getSalle() {
		return salle;
	}
	
	public void setSalle(String salle) {
		this.salle = salle;
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
	public boolean estObserveePar(Observateur obs) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void notifierObservateur() {
		// TODO Auto-generated method stub
		
	}

}
