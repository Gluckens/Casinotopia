package ca.uqam.casinotopia.modele.client;

import java.util.HashMap;
import java.util.Map;

import ca.uqam.casinotopia.Case;
import ca.uqam.casinotopia.TypeCase;
import ca.uqam.casinotopia.TypeCouleurCase;
import ca.uqam.casinotopia.modele.Modele;
import ca.uqam.casinotopia.observateur.BaseObservable;
import ca.uqam.casinotopia.observateur.Observable;
import ca.uqam.casinotopia.observateur.Observateur;

public class ModeleTableJeuClient implements Modele, Observable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6303122505634608518L;

	private Map<Case, Map<Integer, Integer>> cases = new HashMap<Case, Map<Integer, Integer>>();

	private BaseObservable sujet = new BaseObservable(this);

	public ModeleTableJeuClient() {
		this.initialiserCases();
	}

	private void initialiserCases() {
		this.ajouterCase(1, TypeCouleurCase.ROUGE, TypeCase.CHIFFRE, 18);
		this.ajouterCase(2, TypeCouleurCase.NOIRE, TypeCase.CHIFFRE, 18);
		this.ajouterCase(3, TypeCouleurCase.ROUGE, TypeCase.CHIFFRE, 18);
		this.ajouterCase(4, TypeCouleurCase.NOIRE, TypeCase.CHIFFRE, 18);
		this.ajouterCase(5, TypeCouleurCase.ROUGE, TypeCase.CHIFFRE, 18);
		this.ajouterCase(6, TypeCouleurCase.NOIRE, TypeCase.CHIFFRE, 18);
	}

	public void ajouterCase(int numero, TypeCouleurCase couleur, TypeCase type, double multiplicateurGain) {
		this.cases.put(new Case(numero, couleur, type, multiplicateurGain), new HashMap<Integer, Integer>());
	}

	public void updateTableJeu(Map<Case, Map<Integer, Integer>> cases) {
		this.cases = cases;

		this.notifierObservateur();
	}

	public Map<Case, Map<Integer, Integer>> getCases() {
		return this.cases;
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
