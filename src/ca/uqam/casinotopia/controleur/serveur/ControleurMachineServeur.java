package ca.uqam.casinotopia.controleur.serveur;

import ca.uqam.casinotopia.commande.client.machine.CmdMachineEnvoyerLeHasard;
import ca.uqam.casinotopia.connexion.Connexion;
import ca.uqam.casinotopia.controleur.ControleurServeur;
import ca.uqam.casinotopia.modele.serveur.ModeleMachineServeur;

public class ControleurMachineServeur extends ControleurServeur {
	
	private ModeleMachineServeur modele;
	
	public ControleurMachineServeur(Connexion connexion, ControleurServeurThread ctrlThread, ModeleMachineServeur modele) {
		super(connexion, ctrlThread);
		this.modele = modele;
	}
	
	public void actionMiserMachine(int mise) {
		//this.ajouterSoldeClient(-mise);
		this.modele.actionnerMachine();
		this.connexion.envoyerCommande(new CmdMachineEnvoyerLeHasard(this.modele.getNo1(), this.modele.getNo2(), this.modele.getNo3()));
		this.ajouterSoldeClient(this.modele.getGain(mise));
	}
}