package ca.uqam.casinotopia;

import ca.uqam.casinotopia.objet.Utilisateur;

/**
 * Regroupe les informations d'un utilisateur au soutien technique
 */
@SuppressWarnings("serial")
public class SoutienTechnique extends Utilisateur {

	public SoutienTechnique(int idUtilisateur, String nomUtilisateur) {
		super(idUtilisateur, nomUtilisateur);
	}
}