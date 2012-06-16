package ca.uqam.casinotopia.commande.client.chat;

import ca.uqam.casinotopia.commande.CommandeClientControleurPrincipal;
import ca.uqam.casinotopia.controleur.Controleur;
import ca.uqam.casinotopia.controleur.client.ControleurPrincipalClient;

public class CmdQuitterChatClient implements CommandeClientControleurPrincipal {

	private static final long serialVersionUID = -5841989724869995773L;

	@Override
	public void action(Controleur controleur) {
		((ControleurPrincipalClient) controleur).actionQuitterChatClient();
	}
}