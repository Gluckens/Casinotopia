package ca.uqam.casinotopia.commande.client.compte;

import ca.uqam.casinotopia.commande.CommandeClientControleurPrincipal;
import ca.uqam.casinotopia.controleur.Controleur;
import ca.uqam.casinotopia.controleur.client.ControleurPrincipalClient;
import ca.uqam.casinotopia.modele.serveur.ModeleClientServeur;


public class CmdModificationCompte implements CommandeClientControleurPrincipal {

	private static final long serialVersionUID = 8911438224598458768L;

	boolean modifReussi;
	ModeleClientServeur modele;


	public CmdModificationCompte(ModeleClientServeur modele,
			boolean b) {
		this.modifReussi = b;
		this.modele = modele;
	}

	@Override
	public void action(Controleur controleur) {
		
		((ControleurPrincipalClient) controleur).modifierCompteClient(modele, modifReussi);
	}
}