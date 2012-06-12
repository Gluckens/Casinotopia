package ca.uqam.casinotopia.controleur.serveur;

import java.util.Random;

import ca.uqam.casinotopia.commande.client.machine.CmdMachineEnvoyerLeHasard;
import ca.uqam.casinotopia.connexion.Connexion;
import ca.uqam.casinotopia.controleur.ControleurServeur;
import ca.uqam.casinotopia.modele.serveur.ModeleMachineServeur;

@SuppressWarnings("serial")
public class ControleurMachineServeur extends ControleurServeur {

	private ModeleMachineServeur modele;
	
	public ControleurMachineServeur(Connexion connexion, ControleurServeurThread ctrlThread, ModeleMachineServeur modele) {
		super(connexion, ctrlThread);
		this.modele = modele;
	}
	
	public void cmdEnvoyerLeHasardEtCalculerGain(int mise){
		Random random = new Random();
		int int1 = random.nextInt(9);
		int int2 = random.nextInt(9);
		int int3 = random.nextInt(9);
		connexion.envoyerCommande(new CmdMachineEnvoyerLeHasard(int1, int2, int3));

		if(int1 == int2 && int3 == int2) {
			((ControleurClientServeur) this.ctrlThread.getControleur("ControleurClientServeur")).ajouterSolde(mise);
		}
		else if(int1 == int2 || int2  == int3 || int3 == int1) {
			
		}
		else {
			((ControleurClientServeur) this.ctrlThread.getControleur("ControleurClientServeur")).ajouterSolde(-mise);
		}
	}
}