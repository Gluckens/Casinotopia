package ca.uqam.casinotopia.commande;

import java.io.Serializable;

import ca.uqam.casinotopia.controleur.Controleur;

public interface Commande extends Serializable {

	public void action(Controleur controleur);
}
