package ca.uqam.casinotopia.controleur.client;

import javax.swing.JOptionPane;

import ca.uqam.casinotopia.commande.serveur.machine.CmdMiserMachine;
import ca.uqam.casinotopia.commande.serveur.machine.CmdQuitterMachine;
import ca.uqam.casinotopia.connexion.Connexion;
import ca.uqam.casinotopia.controleur.ControleurClient;
import ca.uqam.casinotopia.modele.client.ModeleClientClient;
import ca.uqam.casinotopia.modele.client.ModelePartieMachineClient;
import ca.uqam.casinotopia.modele.client.ModelePrincipalClient;
import ca.uqam.casinotopia.vue.machine.VueMachine;

/**
 * Controleur gérant les actions du jeu de machine.
 */
public class ControleurMachineClient extends ControleurClient {
	
	/**
	 * vue de la machine à sous
	 */
	private VueMachine vue;
	
	/**
	 * modele de la manchine à sous
	 */
	private ModelePartieMachineClient modele;

	public ControleurMachineClient(Connexion connexion, ModelePartieMachineClient modele, ModeleClientClient client, ModelePrincipalClient modeleNav) {
		super(connexion, client, modeleNav);

		this.vue = new VueMachine(this);
		this.modele = modele;
		this.modele.ajouterObservateur(this.vue);
	}
	
	/**
	 * affiche un message à l'écran
	 * @param message le message a afficher
	 */
	public void actionAfficherMessage(String message) {
    	JOptionPane.showMessageDialog(null, message);
	}
	
	public VueMachine getVue() {
		return this.vue;
	}
	
	public ModelePartieMachineClient getModele() {
		return this.modele;
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
		this.connexion.envoyerCommande(new CmdMiserMachine(Integer.parseInt(vue.getTxtMontant().getText())));
	}

	/**
	 * affiche les trois valeurs de la machine à sous
	 */
	public void actionAfficherLeHasard(int int1, int int2, int int3) {
		this.vue.setVal(int1, int2, int3);
		
		if(int1 == int2 && int3 == int2) {
			this.actionAfficherMessage("Vous avez gagné 2 fois votre mise");
		}
		else if(int1 == int2 || int2  == int3 || int3 == int1) {
			//TODO existe encore?
			this.actionAfficherMessage("Vous avez gagné votre mise");
		}
		else {
			this.actionAfficherMessage("Vous avez perdu votre mise");
		}
	}

	/**
	 * déconnecte l'utilisateur de la machine à sous
	 */
	public void cmdQuitterPartie() {
		this.connexion.envoyerCommande(new CmdQuitterMachine(this.client.getId()));
	}
}