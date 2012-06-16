package ca.uqam.casinotopia.commande;

import java.io.Serializable;

import ca.uqam.casinotopia.controleur.Controleur;

/**
 * commande envoyé par le client et le serveur
 *
 */
public interface Commande extends Serializable {

	/**
	 * action lancé lors de la lecture d'une commande
	 * @param controleur le controleur sur leque on fait l'action
	 */
	public void action(Controleur controleur);
}
