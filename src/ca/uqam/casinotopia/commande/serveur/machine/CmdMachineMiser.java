package ca.uqam.casinotopia.commande.serveur.machine;

import ca.uqam.casinotopia.commande.CommandeServeurControleurMachine;
import ca.uqam.casinotopia.controleur.Controleur;
import ca.uqam.casinotopia.controleur.serveur.ControleurMachineServeur;

public class CmdMachineMiser implements CommandeServeurControleurMachine {


	private static final long serialVersionUID = 5330161293922351379L;
	
	private int mise;
	
	/**
	 * 
	 * @param mise mise en sous
	 */
	public CmdMachineMiser(int mise) {
		this.mise = mise;
	}
	
	@Override
	public void action(Controleur controleur) {
		((ControleurMachineServeur)controleur).cmdEnvoyerLeHasard();

	}

}
