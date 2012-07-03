package ca.uqam.casinotopia;

import ca.uqam.casinotopia.objet.Utilisateur;

/**
 * Regroupe les informations d'un utilisateur de type administrateur
 */
@SuppressWarnings("serial")
public class Administrateur extends Utilisateur {

	public Administrateur(int idUtilisateur, String nomUtilisateur) {
		super(idUtilisateur, nomUtilisateur);
	}
}