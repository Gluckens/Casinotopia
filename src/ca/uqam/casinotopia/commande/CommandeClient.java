package ca.uqam.casinotopia.commande;

import ca.uqam.casinotopia.controleur.Controleur;

/**
 * Commande envoyée par le serveur et reçue par le client
 */
public interface CommandeClient extends Commande {

	@Override
	public void action(Controleur controleur);
}
