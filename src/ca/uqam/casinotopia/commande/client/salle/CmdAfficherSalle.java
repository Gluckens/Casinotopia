package ca.uqam.casinotopia.commande.client.salle;

import ca.uqam.casinotopia.commande.CommandeClientControleurPrincipal;
import ca.uqam.casinotopia.controleur.Controleur;
import ca.uqam.casinotopia.controleur.client.ControleurPrincipalClient;
import ca.uqam.casinotopia.modele.client.ModeleSalleClient;

public class CmdAfficherSalle implements CommandeClientControleurPrincipal {
	
	private static final long serialVersionUID = 843508698252411551L;
	
	private ModeleSalleClient modele;
	
	public CmdAfficherSalle(ModeleSalleClient modele) {
		this.modele = modele;
	}

	@Override
	public void action(Controleur controleur) {
		((ControleurPrincipalClient) controleur).actionAfficherSalle(this.modele);
	}
}
