package ca.uqam.casinotopia.commande;

import ca.uqam.casinotopia.controleur.Controleur;

/**
 * Commande envoy�e par le serveur et re�ue par le client
 */
public interface CommandeClient extends Commande {

	@Override
	public void action(Controleur controleur);
}
