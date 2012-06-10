package ca.uqam.casinotopia.controleur.client;

import ca.uqam.casinotopia.commande.serveur.CmdQuitterChat;
import ca.uqam.casinotopia.commande.serveur.CmdQuitterMachine;
import ca.uqam.casinotopia.commande.serveur.machine.CmdMachineMiser;
import ca.uqam.casinotopia.connexion.Connexion;
import ca.uqam.casinotopia.controleur.ControleurClient;
import ca.uqam.casinotopia.modele.client.ModeleClientClient;
import ca.uqam.casinotopia.modele.client.ModelePartieMachineClient;
import ca.uqam.casinotopia.modele.client.ModelePrincipalClient;
import ca.uqam.casinotopia.vue.machine.VueMachine;

@SuppressWarnings("serial")
public class ControleurMachineClient extends ControleurClient {

	private VueMachine vue;
	private ModelePartieMachineClient modele;

	public ControleurMachineClient(Connexion connexion, ModeleClientClient client, ModelePrincipalClient modeleNav) {
		super(connexion, client, modeleNav);

		this.vue = new VueMachine(this);
		//this.modele = modele;
		modele = new ModelePartieMachineClient();
		this.modele.ajouterObservateur(this.vue);
		
	}
	
	
	public VueMachine getVue() {
		return vue;
	}


	public void cmdMiser() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		connexion.envoyerCommande(new CmdMachineMiser(Integer.parseInt(vue.getTxtMontant().getText())));
		
	}


	public void afficherLeHasard(int int1, int int2, int int3) {
		vue.setVal(int1, int2, int3);
		
	}


	public void cmdQuitterPartie() {
		this.connexion.envoyerCommande(new CmdQuitterMachine(this.client.getId()));
		
	}

}
