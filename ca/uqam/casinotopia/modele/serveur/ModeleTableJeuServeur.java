package ca.uqam.casinotopia.modele.serveur;

import java.util.HashMap;
import java.util.Map;

import ca.uqam.casinotopia.Case;
import ca.uqam.casinotopia.TypeCase;
import ca.uqam.casinotopia.modele.Modele;
import ca.uqam.casinotopia.observateur.BaseObservable;
import ca.uqam.casinotopia.observateur.Observable;
import ca.uqam.casinotopia.observateur.Observateur;

public class ModeleTableJeuServeur implements Modele, Observable {

	//private Map<Case, ArrayList<Integer>> cases = new HashMap<Case, ArrayList<Integer>>();
	/**
	 * Map<Case, Map<idJoueur, nbrJetonsMises>>
	 */
	private Map<Case, Map<Integer, Integer>> cases = new HashMap<Case, Map<Integer, Integer>>();
	
	private BaseObservable sujet = new BaseObservable(this);
	
	public ModeleTableJeuServeur() {
		this.initialiserCases();
	}
	
	public void effectuerMises(Map<Integer, Map<Case, Integer>> mises) {
		int idJoueur, nbrJetonsMises;
		Case caseMisee;
		Map<Integer, Integer> misesCourantesCase;
		
		System.out.println("avant foreach " + this.cases);
		
		for(Map.Entry<Integer, Map<Case, Integer>> m : mises.entrySet()) {
			idJoueur = m.getKey();
			for(Map.Entry<Case, Integer> m2 : m.getValue().entrySet()) {
				caseMisee = m2.getKey();
				nbrJetonsMises = m2.getValue();
				
				misesCourantesCase = this.cases.get(caseMisee);
				if(misesCourantesCase != null) {
					if(misesCourantesCase.containsKey(idJoueur)) {
						nbrJetonsMises += misesCourantesCase.get(idJoueur);
					}
					
					misesCourantesCase.put(idJoueur, nbrJetonsMises);
				}
				else {
					System.out.println(caseMisee.hashCode() + " est pas trouvable!");
				}
			}
		}
		
		System.out.println("apres foreach " + this.cases);
	}
	
	private void initialiserCases() {
		this.ajouterCase(1, "noire", false, TypeCase.CHIFFRE);
		this.ajouterCase(2, "rouge", true, TypeCase.CHIFFRE);
		this.ajouterCase(3, "rouge", false, TypeCase.CHIFFRE);
		this.ajouterCase(4, "noire", true, TypeCase.CHIFFRE);
		this.ajouterCase(5, "noire", false, TypeCase.CHIFFRE);
		this.ajouterCase(6, "rouge", true, TypeCase.CHIFFRE);
	}
	
	public void ajouterCase(int numero, String couleur, boolean boolPaires, TypeCase type) {
		this.cases.put(new Case(numero, couleur, boolPaires, type), new HashMap<Integer, Integer>());
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
