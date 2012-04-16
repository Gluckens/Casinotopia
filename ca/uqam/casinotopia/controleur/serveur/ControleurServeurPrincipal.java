package ca.uqam.casinotopia.controleur.serveur;

import ca.uqam.casinotopia.controleur.ControleurServeur;
import ca.uqam.casinotopia.model.ModelServeurPrincipal;

public class ControleurServeurPrincipal extends ControleurServeur {

	ModelServeurPrincipal model;
	
	
	public ControleurServeurPrincipal() {
		super(null);
		initModel();
		
		
	}

	public void initModel() {
		model = new ModelServeurPrincipal();
		
	}
	
}
