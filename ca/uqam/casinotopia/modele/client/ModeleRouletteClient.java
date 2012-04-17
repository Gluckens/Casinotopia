package ca.uqam.casinotopia.modele.client;

import java.util.Map;

import ca.uqam.casinotopia.Case;
import ca.uqam.casinotopia.Clavardage;
import ca.uqam.casinotopia.Partie;
import ca.uqam.casinotopia.modele.Modele;
import ca.uqam.casinotopia.observateur.BaseSujet;
import ca.uqam.casinotopia.observateur.Observateur;

public class ModeleRouletteClient extends Partie implements Modele {

	private Case resultat;
	private ModeleTableJeuClient tableJeu;
	private BaseSujet sujet = new BaseSujet(this);

	
	public ModeleRouletteClient(int id, boolean optionArgent, boolean optionMultijoueur, Clavardage clavardage) {
		super(id, optionArgent, optionMultijoueur, clavardage);
		
		this.tableJeu = new ModeleTableJeuClient();
	}
	
	public void updateTableJeu(Map<Case, Map<Integer, Integer>> cases) {
		this.tableJeu.updateTableJeu(cases);

		//TODO enlever ce notifier et le traite dans tableJeu
		this.notifierObservateur();
	}
	
	
	/**
	 * @return the tableJeu
	 */
	public ModeleTableJeuClient getTableJeu() {
		return tableJeu;
	}
	/**
	 * @param tableJeu the tableJeu to set
	 */
	public void setTableJeu(ModeleTableJeuClient tableJeu) {
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
