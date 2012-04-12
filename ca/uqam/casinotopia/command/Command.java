package ca.uqam.casinotopia.command;

import ca.uqam.casinotopia.controleur.Controleur;


public interface Command {

	public void action(Controleur controleur);

}
