package ca.uqam.casinotopia.controleur.serveur;

import ca.uqam.casinotopia.commande.client.machine.CmdMachineEnvoyerLeHasard;
import ca.uqam.casinotopia.connexion.Connexion;
import ca.uqam.casinotopia.controleur.ControleurServeur;
import ca.uqam.casinotopia.modele.serveur.ModelePartieMachineServeur;

/**
 * Controleur gérant les actions du jeu de machine.
 */
public class ControleurMachineServeur extends ControleurServeur {
	
	/**
	 * Le modèle de la machine
	 */
	private ModelePartieMachineServeur modele;
	
	public ControleurMachineServeur(Connexion connexion, ControleurServeurThread ctrlThread, ModelePartieMachineServeur modele) {
		super(connexion, ctrlThread);
		this.modele = modele;
	}
	/**
	 * actionne la machine et calcul le gain du joueur en fonction de sa mise
	 * @param mise la mise du client
	 */
	public void actionMiserMachine(int mise) {
		//this.ajouterSoldeClient(-mise);
		this.modele.actionnerMachine();
		this.connexion.envoyerCommande(new CmdMachineEnvoyerLeHasard(this.modele.getNo1(), this.modele.getNo2(), this.modele.getNo3()));
		this.ajouterSoldeClient(this.modele.getGain(mise));
	}
}