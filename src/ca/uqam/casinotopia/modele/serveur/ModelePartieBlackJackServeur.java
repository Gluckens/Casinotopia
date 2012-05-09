package ca.uqam.casinotopia.modele.serveur;

import ca.uqam.casinotopia.Jeu;
import ca.uqam.casinotopia.Partie;
import ca.uqam.casinotopia.TypeJeuArgent;
import ca.uqam.casinotopia.TypeJeuMultijoueurs;
import ca.uqam.casinotopia.modele.Modele;

@SuppressWarnings("serial")
public class ModelePartieBlackJackServeur extends Partie implements Modele {

	public ModelePartieBlackJackServeur(int id, TypeJeuMultijoueurs typeMultijoueurs, TypeJeuArgent typeArgent, Jeu infoJeu) {
		super(id, typeMultijoueurs, typeArgent, infoJeu);
	}
}
