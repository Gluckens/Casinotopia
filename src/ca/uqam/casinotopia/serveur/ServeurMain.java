package ca.uqam.casinotopia.serveur;

import ca.uqam.casinotopia.controleur.serveur.ControleurPrincipalServeur;

public class ServeurMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ControleurPrincipalServeur.getInstance().ecouterConnexions();
	}

}
