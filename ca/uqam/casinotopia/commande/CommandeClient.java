package ca.uqam.casinotopia.commande;

import ca.uqam.casinotopia.controleur.Controleur;

public interface CommandeClient extends Commande {

	public void action(Controleur controleur);
}
