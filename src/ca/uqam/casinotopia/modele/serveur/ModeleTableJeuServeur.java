package ca.uqam.casinotopia.modele.serveur;

import java.util.HashMap;
import java.util.Map;

import ca.uqam.casinotopia.JoueurServeur;
import ca.uqam.casinotopia.commande.client.compte.CmdModifierSolde;
import ca.uqam.casinotopia.modele.Modele;
import ca.uqam.casinotopia.objet.Case;
import ca.uqam.casinotopia.objet.ListeCases;
import ca.uqam.casinotopia.observateur.BaseObservable;
import ca.uqam.casinotopia.observateur.Observable;
import ca.uqam.casinotopia.observateur.Observateur;
import ca.uqam.casinotopia.type.TypeCouleurCase;
import ca.uqam.casinotopia.type.TypePariteCase;
import ca.uqam.casinotopia.type.modif.TypeModif;

@SuppressWarnings("serial")
public class ModeleTableJeuServeur implements Modele, Observable {

	// private Map<Case, ArrayList<Integer>> cases = new HashMap<Case,
	// ArrayList<Integer>>();
	/**
	 * Map<Case, Map<idJoueur, nbrJetonsMises>>
	 */
	private Map<Case, Map<Integer, Integer>> cases = new HashMap<Case, Map<Integer, Integer>>();
	
	private ModelePartieRouletteServeur partieRoulette;

	private BaseObservable sujet = new BaseObservable(this);

	public ModeleTableJeuServeur(ModelePartieRouletteServeur partieRoulette) {
		this.partieRoulette = partieRoulette;
		this.initialiserCases();
	}

	public void effectuerMises(Map<Integer, Map<Case, Integer>> mises) {

		System.out.println("avant foreach " + this.cases);

		//Pour chaque joueur ayant misé
		for (Map.Entry<Integer, Map<Case, Integer>> m : mises.entrySet()) {
			int nbrJetonsMises;
			int totalMises = 0;
			int idJoueur = m.getKey();
			JoueurServeur joueur = this.partieRoulette.getJoueur(idJoueur);
			Case caseMisee;
			Map<Integer, Integer> misesCourantesCase;
			//Pour chaque mises du joueur
			for (Map.Entry<Case, Integer> m2 : m.getValue().entrySet()) {
				caseMisee = m2.getKey();
				nbrJetonsMises = m2.getValue();

				misesCourantesCase = this.cases.get(caseMisee);
				if (misesCourantesCase != null) {
					totalMises += nbrJetonsMises;
					
					if (misesCourantesCase.containsKey(idJoueur)) {
						nbrJetonsMises += misesCourantesCase.get(idJoueur);
					}
					//Déplacement d'une mise existante
					if(nbrJetonsMises == 0) {
						misesCourantesCase.remove(idJoueur);
					}
					//Nouvelle mise
					else {
						misesCourantesCase.put(idJoueur, nbrJetonsMises);
					}
				}
				else {
					System.out.println(caseMisee.hashCode() + " n'est pas trouvable!");
					m.getValue().remove(caseMisee);
				}
			}
			
			if(totalMises != 0) {
				System.out.println("Nouveau solde du joueur 1 = " + joueur.getClient().getSolde() + " - " + totalMises + " = " + (joueur.getClient().getSolde() - totalMises));
				joueur.getClient().setSolde(joueur.getClient().getSolde() - totalMises);
				joueur.getClient().getConnexion().envoyerCommande(new CmdModifierSolde(joueur.getClient().getSolde()));
			}
		}

		System.out.println("apres foreach " + this.cases);
	}

	public void retirerMises(int idJoueur) {
		for(Map<Integer, Integer> map : this.cases.values()) {
			map.remove(idJoueur);
		}
	}

	private void initialiserCases() {
		this.cases.put(ListeCases.INSTANCE.getCaseCouleur(TypeCouleurCase.ROUGE), new HashMap<Integer, Integer>());
		this.cases.put(ListeCases.INSTANCE.getCaseCouleur(TypeCouleurCase.NOIRE), new HashMap<Integer, Integer>());
		
		this.cases.put(ListeCases.INSTANCE.getCaseParite(TypePariteCase.PAIRE), new HashMap<Integer, Integer>());
		this.cases.put(ListeCases.INSTANCE.getCaseParite(TypePariteCase.IMPAIRE), new HashMap<Integer, Integer>());
		
		this.cases.put(ListeCases.INSTANCE.getCaseNumero(0), new HashMap<Integer, Integer>());
		
		this.cases.put(ListeCases.INSTANCE.getCaseNumero(1), new HashMap<Integer, Integer>());
		this.cases.put(ListeCases.INSTANCE.getCaseNumero(2), new HashMap<Integer, Integer>());
		this.cases.put(ListeCases.INSTANCE.getCaseNumero(3), new HashMap<Integer, Integer>());
		
		this.cases.put(ListeCases.INSTANCE.getCaseNumero(4), new HashMap<Integer, Integer>());
		this.cases.put(ListeCases.INSTANCE.getCaseNumero(5), new HashMap<Integer, Integer>());
		this.cases.put(ListeCases.INSTANCE.getCaseNumero(6), new HashMap<Integer, Integer>());
		
		this.cases.put(ListeCases.INSTANCE.getCaseNumero(7), new HashMap<Integer, Integer>());
		this.cases.put(ListeCases.INSTANCE.getCaseNumero(8), new HashMap<Integer, Integer>());
		this.cases.put(ListeCases.INSTANCE.getCaseNumero(9), new HashMap<Integer, Integer>());
		
		this.cases.put(ListeCases.INSTANCE.getCaseNumero(10), new HashMap<Integer, Integer>());
		this.cases.put(ListeCases.INSTANCE.getCaseNumero(11), new HashMap<Integer, Integer>());
		this.cases.put(ListeCases.INSTANCE.getCaseNumero(12), new HashMap<Integer, Integer>());
		
		this.cases.put(ListeCases.INSTANCE.getCaseNumero(13), new HashMap<Integer, Integer>());
		this.cases.put(ListeCases.INSTANCE.getCaseNumero(14), new HashMap<Integer, Integer>());
		this.cases.put(ListeCases.INSTANCE.getCaseNumero(15), new HashMap<Integer, Integer>());
		
		this.cases.put(ListeCases.INSTANCE.getCaseNumero(16), new HashMap<Integer, Integer>());
		this.cases.put(ListeCases.INSTANCE.getCaseNumero(17), new HashMap<Integer, Integer>());
		this.cases.put(ListeCases.INSTANCE.getCaseNumero(18), new HashMap<Integer, Integer>());
		
		this.cases.put(ListeCases.INSTANCE.getCaseNumero(19), new HashMap<Integer, Integer>());
		this.cases.put(ListeCases.INSTANCE.getCaseNumero(20), new HashMap<Integer, Integer>());
		this.cases.put(ListeCases.INSTANCE.getCaseNumero(21), new HashMap<Integer, Integer>());
		
		this.cases.put(ListeCases.INSTANCE.getCaseNumero(22), new HashMap<Integer, Integer>());
		this.cases.put(ListeCases.INSTANCE.getCaseNumero(23), new HashMap<Integer, Integer>());
		this.cases.put(ListeCases.INSTANCE.getCaseNumero(24), new HashMap<Integer, Integer>());
		
		this.cases.put(ListeCases.INSTANCE.getCaseNumero(25), new HashMap<Integer, Integer>());
		this.cases.put(ListeCases.INSTANCE.getCaseNumero(26), new HashMap<Integer, Integer>());
		this.cases.put(ListeCases.INSTANCE.getCaseNumero(27), new HashMap<Integer, Integer>());
		
		this.cases.put(ListeCases.INSTANCE.getCaseNumero(28), new HashMap<Integer, Integer>());
		this.cases.put(ListeCases.INSTANCE.getCaseNumero(29), new HashMap<Integer, Integer>());
		this.cases.put(ListeCases.INSTANCE.getCaseNumero(30), new HashMap<Integer, Integer>());
		
		this.cases.put(ListeCases.INSTANCE.getCaseNumero(31), new HashMap<Integer, Integer>());
		this.cases.put(ListeCases.INSTANCE.getCaseNumero(32), new HashMap<Integer, Integer>());
		this.cases.put(ListeCases.INSTANCE.getCaseNumero(33), new HashMap<Integer, Integer>());
		
		this.cases.put(ListeCases.INSTANCE.getCaseNumero(34), new HashMap<Integer, Integer>());
		this.cases.put(ListeCases.INSTANCE.getCaseNumero(35), new HashMap<Integer, Integer>());
		this.cases.put(ListeCases.INSTANCE.getCaseNumero(36), new HashMap<Integer, Integer>());
	}

	public Map<Case, Map<Integer, Integer>> getCases() {
		return this.cases;
	}
	
	public void resetMises() {
		for(Map<Integer, Integer> mises : this.cases.values()) {
			mises.clear();
		}
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
