package ca.uqam.casinotopia.commande.client.machine;

import ca.uqam.casinotopia.commande.CommandeClientControleurMachine;
import ca.uqam.casinotopia.controleur.Controleur;
import ca.uqam.casinotopia.controleur.client.ControleurMachineClient;

public class CmdMachineEnvoyerLeHasard implements CommandeClientControleurMachine {
	
	private static final long serialVersionUID = -4011148245381781701L;
	
	private int int1, int2, int3;
	
	public CmdMachineEnvoyerLeHasard(int int1, int int2, int int3) {
		this.int1 = int1;
		this.int2 = int2;
		this.int3 = int3;
	}
	
	@Override
	public void action(Controleur controleur) {
		((ControleurMachineClient) controleur).actionAfficherLeHasard(this.int1, this.int2, this.int3);
	}
}