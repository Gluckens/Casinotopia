package ca.uqam.casinotopia.modele.serveur;

import java.util.Map;

import ca.uqam.casinotopia.Case;
import ca.uqam.casinotopia.Jeu;
import ca.uqam.casinotopia.JoueurRoulette;
import ca.uqam.casinotopia.JoueurServeur;
import ca.uqam.casinotopia.Partie;
import ca.uqam.casinotopia.modele.Modele;

@SuppressWarnings("serial")
public class ModelePartieRouletteServeur extends Partie implements Modele {
	
	private Case resultat;
	private ModeleTableJeuServeur tableJeu;
	private ModeleRoueRouletteServeur roueRoulette;

	public ModelePartieRouletteServeur(int id, boolean optionArgent, boolean optionMultijoueur, Jeu infoJeu) {
		super(id, optionArgent, optionMultijoueur, infoJeu);

		this.tableJeu = new ModeleTableJeuServeur();
		this.roueRoulette = new ModeleRoueRouletteServeur();
	}

	public void effectuerMises(Map<Integer, Map<Case, Integer>> mises) {
		this.tableJeu.effectuerMises(mises);
	}
	
	public boolean isToutesMisesTerminees() {
		for(JoueurServeur joueur : this.lstJoueurs) {
			if(!((JoueurRoulette) joueur).isMisesTerminees()) {
				return false;
			}
		}
		
		return true;
	}

	/**
	 * @return the tableJeu
	 */
	public ModeleTableJeuServeur getTableJeu() {
		return this.tableJeu;
	}

	/**
	 * @param tableJeu
	 *            the tableJeu to set
	 */
	public void setTableJeu(ModeleTableJeuServeur tableJeu) {
		this.tableJeu = tableJeu;
	}
	
	public ModeleRoueRouletteServeur getRoueRoulette() {
		return this.roueRoulette;
	}
	
	public void setRoueRoulette(ModeleRoueRouletteServeur roueRoulette) {
		this.roueRoulette = roueRoulette;
	}
	

	public void tournerRoulette() {
		this.roueRoulette.tournerRoulette();
		
	}

	public void calculerGainRoulette() {
		// TODO Auto-generated method stub
	}

	public void quitterPartie(int idJoueur) {
		JoueurServeur joueur = this.getJoueur(idJoueur);
		this.retirerJoueur(joueur);
	}

}
