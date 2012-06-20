package ca.uqam.casinotopia.commande;

import ca.uqam.casinotopia.controleur.Controleur;

/**
 * commande lu par le client
 *
 */
public interface CommandeClient extends Commande {

	@Override
	public void action(Controleur controleur);
}
