package ca.uqam.casinotopia.modele.client;

import java.util.HashMap;

import ca.uqam.casinotopia.Case;
import ca.uqam.casinotopia.modele.Modele;
import ca.uqam.casinotopia.modif.TypeModif;
import ca.uqam.casinotopia.observateur.BaseObservable;
import ca.uqam.casinotopia.observateur.Observable;
import ca.uqam.casinotopia.observateur.Observateur;

public class ModeleRoueRouletteClient implements Modele, Observable {

	private static final long serialVersionUID = 3547778398611217008L;
	
	private Case caseResultat;
	private HashMap<Integer,Case> listeCases;
	private BaseObservable sujet = new BaseObservable(this);
	
	public ModeleRoueRouletteClient() {
		initialiserListeCases();
	}
	
	private void initialiserListeCases() {
		
		/*listeCases.put(0, new Case(0,"noire",true,TypeCase.CHIFFRE)); // à voir pour 0 qui n'a pas de notion paire impaire et de couleur
		
		listeCases.put(1, new Case(1,"rouge",false,TypeCase.CHIFFRE));
		listeCases.put(2, new Case(2,"noir",true,TypeCase.CHIFFRE));
		listeCases.put(3, new Case(3,"rouge",false,TypeCase.CHIFFRE));
		listeCases.put(4, new Case(4,"noir",true,TypeCase.CHIFFRE));
		listeCases.put(5, new Case(5,"rouge",false,TypeCase.CHIFFRE));
		listeCases.put(6, new Case(6,"noir",true,TypeCase.CHIFFRE));
		listeCases.put(7, new Case(7,"rouge",false,TypeCase.CHIFFRE));
		listeCases.put(8, new Case(8,"noir",true,TypeCase.CHIFFRE));
		listeCases.put(9, new Case(9,"rouge",false,TypeCase.CHIFFRE));
		listeCases.put(10, new Case(10,"noir",true,TypeCase.CHIFFRE));
		
		listeCases.put(11, new Case(11,"noir",false,TypeCase.CHIFFRE));
		listeCases.put(12, new Case(12,"rouge",true,TypeCase.CHIFFRE));
		listeCases.put(13, new Case(13,"noir",false,TypeCase.CHIFFRE));
		listeCases.put(14, new Case(14,"rouge",true,TypeCase.CHIFFRE));
		listeCases.put(15, new Case(15,"noir",false,TypeCase.CHIFFRE));
		listeCases.put(16, new Case(16,"rouge",true,TypeCase.CHIFFRE));
		listeCases.put(17, new Case(17,"noir",false,TypeCase.CHIFFRE));
		listeCases.put(18, new Case(18,"rouge",true,TypeCase.CHIFFRE));
		
		listeCases.put(19, new Case(19,"rouge",false,TypeCase.CHIFFRE));
		listeCases.put(20, new Case(20,"noir",true,TypeCase.CHIFFRE));
		listeCases.put(21, new Case(21,"rouge",false,TypeCase.CHIFFRE));
		listeCases.put(22, new Case(22,"noir",true,TypeCase.CHIFFRE));
		listeCases.put(23, new Case(23,"rouge",false,TypeCase.CHIFFRE));
		listeCases.put(24, new Case(24,"noir",true,TypeCase.CHIFFRE));
		listeCases.put(25, new Case(25,"rouge",false,TypeCase.CHIFFRE));
		listeCases.put(26, new Case(26,"noir",true,TypeCase.CHIFFRE));
		listeCases.put(27, new Case(27,"rouge",false,TypeCase.CHIFFRE));
		listeCases.put(28, new Case(28,"noir",true,TypeCase.CHIFFRE));
		
		listeCases.put(29, new Case(29,"noir",false,TypeCase.CHIFFRE));
		listeCases.put(30, new Case(30,"rouge",true,TypeCase.CHIFFRE));
		listeCases.put(31, new Case(31,"noir",false,TypeCase.CHIFFRE));
		listeCases.put(32, new Case(32,"rouge",true,TypeCase.CHIFFRE));
		listeCases.put(33, new Case(33,"noir",false,TypeCase.CHIFFRE));
		listeCases.put(34, new Case(34,"rouge",true,TypeCase.CHIFFRE));
		listeCases.put(35, new Case(35,"noir",false,TypeCase.CHIFFRE));
		listeCases.put(36, new Case(36,"rouge",true,TypeCase.CHIFFRE));*/
				
	}
	
	public void updateRoueRoulette(Case resultat) {
		this.caseResultat = resultat;
		
		this.notifierObservateur();
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

	@Override
	public TypeModif getTypeModif() {
		// TODO Auto-generated method stub
		return null;
	}

}
