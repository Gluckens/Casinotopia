package ca.uqam.casinotopia;

import ca.uqam.casinotopia.objet.Utilisateur;

public class Administrateur extends Utilisateur {

	private static final long serialVersionUID = -4427151684919054539L;

	public Administrateur(int idUtilisateur, String nomUtilisateur) {
		super(idUtilisateur, nomUtilisateur);
	}
}