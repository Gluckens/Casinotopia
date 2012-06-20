package ca.uqam.casinotopia.commande;

import java.io.Serializable;

import ca.uqam.casinotopia.controleur.Controleur;

/**
 * commande envoy� par le client et le serveur
 *
 */
public interface Commande extends Serializable {

	/**
	 * action lanc� lors de la lecture d'une commande
	 * @param controleur le controleur sur leque on fait l'action
	 */
	public void action(Controleur controleur);
}
