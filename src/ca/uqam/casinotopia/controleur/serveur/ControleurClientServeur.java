package ca.uqam.casinotopia.controleur.serveur;

import ca.uqam.casinotopia.commande.client.compte.CmdModifierSolde;
import ca.uqam.casinotopia.connexion.Connexion;
import ca.uqam.casinotopia.controleur.ControleurServeur;
import ca.uqam.casinotopia.modele.serveur.ModeleClientServeur;

public class ControleurClientServeur extends ControleurServeur {

	private static final long serialVersionUID = 204499404449988843L;
	
	private ModeleClientServeur modele;

	public ControleurClientServeur(Connexion connexion, ControleurServeurThread ctrlThread, ModeleClientServeur modele) {
		super(connexion, ctrlThread);
		this.modele = modele;
	}
	
	public void ajouterSolde(int montant) {
		this.modifierSolde(this.modele.getSolde() + montant);
	}
	
	public void modifierSolde(int nouveauSolde) {
		if(this.modele.modifierSolde(nouveauSolde)) {
			this.cmdModifierSolde();
		}
	}
	
	private void cmdModifierSolde() {
		this.connexion.envoyerCommande(new CmdModifierSolde(this.modele.getSolde()));
	}
	
	public ModeleClientServeur getModele() {
		return this.modele;
	}
}
