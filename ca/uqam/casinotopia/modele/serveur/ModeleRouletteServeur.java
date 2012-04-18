package ca.uqam.casinotopia.modele.serveur;

import java.util.Map;

import ca.uqam.casinotopia.Case;
import ca.uqam.casinotopia.Clavardage;
import ca.uqam.casinotopia.Partie;
import ca.uqam.casinotopia.modele.Modele;
import ca.uqam.casinotopia.observateur.BaseSujet;
import ca.uqam.casinotopia.observateur.Observateur;

public class ModeleRouletteServeur extends Partie implements Modele {

	private Case resultat;
	private ModeleTableJeuServeur tableJeu;
	private BaseSujet sujet = new BaseSujet(this);

	
	public ModeleRouletteServeur(int id, boolean optionArgent, boolean optionMultijoueur, Clavardage clavardage) {
		super(id, optionArgent, optionMultijoueur, clavardage);
		
		this.tableJeu = new ModeleTableJeuServeur();
	}
	
	public void effectuerMises(Map<Integer, Map<Case, Integer>> mises) {
		this.tableJeu.effectuerMises(mises);
	}
	
	
	/**
	 * @return the tableJeu
	 */
	public ModeleTableJeuServeur getTableJeu() {
		return tableJeu;
	}
	/**
	 * @param tableJeu the tableJeu to set
	 */
	public void setTableJeu(ModeleTableJeuServeur tableJeu) {
		this.tableJeu = tableJeu;
	}

	@Override
	public void ajouterObservateur(Observateur obs) {
		this.sujet.ajouterObservateur(obs);
	}

	@Override
	public void retirerObservateur(Observateur obs) {
		this.sujet.retirerObservateur(obs);
	}

	@Override
	public boolean estObserveePar(Observateur obs) {
		return this.sujet.estObserveePar(obs);
	}

	@Override
	public void notifierObservateur() {
		this.sujet.notifierObservateur();
	}
}
