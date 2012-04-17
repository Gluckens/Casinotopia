package ca.uqam.casinotopia.controleur.serveur;

import ca.uqam.casinotopia.controleur.ControleurServeur;
import ca.uqam.casinotopia.modele.serveur.ModeleServeurPrincipal;

public class ControleurServeurPrincipal extends ControleurServeur {

	ModeleServeurPrincipal modele;
	
	
	public ControleurServeurPrincipal() {
		initModele();
		
		
	}

	public void initModele() {
		modele = new ModeleServeurPrincipal();
		
	}
	
}
