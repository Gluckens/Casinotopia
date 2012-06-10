package ca.uqam.casinotopia.commande.client.machine;

import ca.uqam.casinotopia.commande.CommandeClientControleurMachine;
import ca.uqam.casinotopia.controleur.Controleur;
import ca.uqam.casinotopia.controleur.client.ControleurMachineClient;

public class CmdMachineEnvoyerLeHasard implements CommandeClientControleurMachine {

	private static final long serialVersionUID = -5641712153365281012L;
	
	
	int int1,int2,int3;
	
	public CmdMachineEnvoyerLeHasard(int int1, int int2, int int3) {
		this.int1 = int1;
		this.int2 = int2;
		this.int3 = int3;
	}
	
	@Override
	public void action(Controleur controleur) {
		if(int1 == int2 && int3 == int2){
			((ControleurMachineClient)controleur).getVue().setMessage("Vous avez gagné 2 fois votre mise");
		}else if(int1 == int2 || int2  == int3 || int3 == int1){
			((ControleurMachineClient)controleur).getVue().setMessage("Vous avez gagné votre mise");
		}else{
			((ControleurMachineClient)controleur).getVue().setMessage("Vous avez perdu votre mise");
		}
		((ControleurMachineClient)controleur).afficherLeHasard(int1, int2, int3);

	}

}
