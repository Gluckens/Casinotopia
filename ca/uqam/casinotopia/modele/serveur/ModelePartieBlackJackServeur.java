package ca.uqam.casinotopia.modele.serveur;

import ca.uqam.casinotopia.Jeu;
import ca.uqam.casinotopia.Partie;
import ca.uqam.casinotopia.TypeJeu;
import ca.uqam.casinotopia.modele.Modele;
import ca.uqam.casinotopia.observateur.Observateur;

public class ModelePartieBlackJackServeur extends Partie implements Modele {

	public ModelePartieBlackJackServeur(int id, boolean optionArgent, boolean optionMultijoueur, Jeu infoJeu) {
		super(id, optionArgent, optionMultijoueur, infoJeu);
	}

	@Override
	public void ajouterObservateur(Observateur obs) {
		// TODO Auto-generated method stub

	}

	@Override
	public void retirerObservateur(Observateur obs) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean estObserveePar(Observateur obs) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void notifierObservateur() {
		// TODO Auto-generated method stub

	}
}
