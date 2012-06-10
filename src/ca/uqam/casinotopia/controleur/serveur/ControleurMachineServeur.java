package ca.uqam.casinotopia.controleur.serveur;

import java.util.Random;

import ca.uqam.casinotopia.commande.client.machine.CmdMachineEnvoyerLeHasard;
import ca.uqam.casinotopia.connexion.Connexion;
import ca.uqam.casinotopia.controleur.ControleurServeur;
import ca.uqam.casinotopia.modele.serveur.ModeleClientServeur;
import ca.uqam.casinotopia.modele.serveur.ModeleMachineServeur;

public class ControleurMachineServeur extends ControleurServeur {

	private ModeleMachineServeur modele;
	
	public ControleurMachineServeur(Connexion connexion, ModeleClientServeur client, ModeleMachineServeur modele) {
		super(connexion, client);
		this.modele = modele;
	}
	
	
	public void cmdEnvoyerLeHasard(){
		Random random = new Random();
		int int1 = random.nextInt(9);
		int int2 = random.nextInt(9);
		int int3 = random.nextInt(9);
		connexion.envoyerCommande(new CmdMachineEnvoyerLeHasard(int1, int2, int3));
	}
	
	
}
