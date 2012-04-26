package ca.uqam.casinotopia.modele.client;

import java.util.Map;

import ca.uqam.casinotopia.Case;
import ca.uqam.casinotopia.Jeu;
import ca.uqam.casinotopia.Partie;
import ca.uqam.casinotopia.modele.Modele;
import ca.uqam.casinotopia.observateur.BaseObservable;
import ca.uqam.casinotopia.observateur.Observable;
import ca.uqam.casinotopia.observateur.Observateur;

public class ModelePartieRouletteClient extends Partie implements Modele, Observable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1587373503556245991L;

	private Case resultat;
	private ModeleTableJeuClient tableJeu;
	private BaseObservable sujet = new BaseObservable(this);

	public ModelePartieRouletteClient(int id, boolean optionArgent, boolean optionMultijoueur, Jeu infoJeu) {
		super(id, optionArgent, optionMultijoueur, infoJeu);

		this.tableJeu = new ModeleTableJeuClient();
	}

	public void updateTableJeu(Map<Case, Map<Integer, Integer>> cases) {
		this.tableJeu.updateTableJeu(cases);

		// TODO enlever ce notifier et le traite dans tableJeu
		this.notifierObservateur();
	}

	/*
	 * @Override public TypeJeu getTypeJeu() { return TypeJeu.ROULETTE; }
	 */

	/**
	 * @return the tableJeu
	 */
	public ModeleTableJeuClient getTableJeu() {
		return this.tableJeu;
	}

	/**
	 * @param tableJeu
	 *            the tableJeu to set
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
	public boolean estObservePar(Observateur obs) {
		return this.sujet.estObservePar(obs);
	}

	@Override
	public void notifierObservateur() {
		this.sujet.notifierObservateur();
	}
}
