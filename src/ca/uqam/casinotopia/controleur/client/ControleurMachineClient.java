package ca.uqam.casinotopia.controleur.client;

import ca.uqam.casinotopia.commande.serveur.machine.CmdMachineMiser;
import ca.uqam.casinotopia.commande.serveur.machine.CmdQuitterMachine;
import ca.uqam.casinotopia.connexion.Connexion;
import ca.uqam.casinotopia.controleur.ControleurClient;
import ca.uqam.casinotopia.modele.client.ModeleClientClient;
import ca.uqam.casinotopia.modele.client.ModelePartieMachineClient;
import ca.uqam.casinotopia.modele.client.ModelePrincipalClient;
import ca.uqam.casinotopia.vue.machine.VueMachine;

@SuppressWarnings("serial")
public class ControleurMachineClient extends ControleurClient {

	/**
	 * vue de la machine à sous
	 */
	private VueMachine vue;
	
	/**
	 * modele de la manchine à sous
	 */
	private ModelePartieMachineClient modele;

	public ControleurMachineClient(Connexion connexion, ModeleClientClient client, ModelePrincipalClient modeleNav) {
		super(connexion, client, modeleNav);

		this.vue = new VueMachine(this);
		//this.modele = modele;
		modele = new ModelePartieMachineClient();
		this.modele.ajouterObservateur(this.vue);
		
	}
	
	/**
	 * affiche un message à l'écran
	 * @param message le message a afficher
	 */
	public void actionAfficherMessage(String message) {
		this.vue.setMessage(message);
	}
	
	public VueMachine getVue() {
		return this.vue;
	}


	/**
	 * fait une mise dans la machine à sous et envoie la mise au serveur
	 */
	public void cmdMiser() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		connexion.envoyerCommande(new CmdMachineMiser(Integer.parseInt(vue.getTxtMontant().getText())));
		
	}


	/**
	 * affiche 3 valeur de la machine à sous
	 */
	public void actionAfficherLeHasard(int int1, int int2, int int3) {
		vue.setVal(int1, int2, int3);
		
	}


	/**
	 * déconnecte l'utilisateur de la machine à sous
	 */
	public void cmdQuitterPartie() {
		this.connexion.envoyerCommande(new CmdQuitterMachine(this.client.getId()));
		
	}

}
