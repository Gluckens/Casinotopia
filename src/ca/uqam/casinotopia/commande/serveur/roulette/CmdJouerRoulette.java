package ca.uqam.casinotopia.commande.serveur.roulette;

import ca.uqam.casinotopia.commande.CommandeServeurControleurThread;
import ca.uqam.casinotopia.controleur.Controleur;
import ca.uqam.casinotopia.controleur.serveur.ControleurServeurThread;
import ca.uqam.casinotopia.type.TypeJeuArgent;
import ca.uqam.casinotopia.type.TypeJeuMultijoueurs;

public class CmdJouerRoulette implements CommandeServeurControleurThread {

	private static final long serialVersionUID = -3726034859881126673L;

	private int idJeu;
	private TypeJeuMultijoueurs typeMultijoueur;
	private TypeJeuArgent typeArgent;

	public CmdJouerRoulette(int idJeu, TypeJeuMultijoueurs typeMultijoueur, TypeJeuArgent typeArgent) {
		this.idJeu = idJeu;
		this.typeMultijoueur = typeMultijoueur;
		this.typeArgent = typeArgent;
	}

	@Override
	public void action(Controleur controleur) {
		((ControleurServeurThread) controleur).actionAjouterJoueurDansRoulette(this.idJeu, this.typeMultijoueur, this.typeArgent);
	}
}
