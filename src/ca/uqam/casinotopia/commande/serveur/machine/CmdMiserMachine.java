package ca.uqam.casinotopia.commande.serveur.machine;

import ca.uqam.casinotopia.commande.CommandeServeurControleurMachine;
import ca.uqam.casinotopia.controleur.Controleur;
import ca.uqam.casinotopia.controleur.serveur.ControleurMachineServeur;

public class CmdMiserMachine implements CommandeServeurControleurMachine {
	
	private static final long serialVersionUID = 273637580918256312L;
	
	/**
	 * la mise utilisée sur la machine 
	 */
	private int mise;


	 * @param mise mise en sous
	public CmdMiserMachine(int mise) {
		this.mise = mise;
	}
	
	@Override
	public void action(Controleur controleur) {
		((ControleurMachineServeur)controleur).actionMiserMachine(this.mise);
	}
}