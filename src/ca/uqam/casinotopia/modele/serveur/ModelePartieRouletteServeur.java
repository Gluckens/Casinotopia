package ca.uqam.casinotopia.modele.serveur;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import ca.uqam.casinotopia.Case;
import ca.uqam.casinotopia.Jeu;
import ca.uqam.casinotopia.JoueurClient;
import ca.uqam.casinotopia.JoueurRoulette;
import ca.uqam.casinotopia.JoueurServeur;
import ca.uqam.casinotopia.ListeCases;
import ca.uqam.casinotopia.Partie;
import ca.uqam.casinotopia.TypeCouleurJoueurRoulette;
import ca.uqam.casinotopia.TypeJeuArgent;
import ca.uqam.casinotopia.TypeJeuMultijoueurs;
import ca.uqam.casinotopia.commande.client.CmdUpdateCasesRoulette;
import ca.uqam.casinotopia.modele.Modele;
import ca.uqam.casinotopia.modele.client.ModeleClientClient;
import ca.uqam.casinotopia.modele.client.ModelePartieRouletteClient;

@SuppressWarnings("serial")
public class ModelePartieRouletteServeur extends Partie implements Modele {
	
	private Case caseResultat;
	private Map <Integer, Integer> listeGains = new HashMap <Integer,Integer>();
	private ModeleTableJeuServeur tableJeu;
	private ModeleRoueRouletteServeur roueRoulette;

	public ModelePartieRouletteServeur(int id, TypeJeuMultijoueurs typeMultijoueurs, TypeJeuArgent typeArgent, Jeu infoJeu) {
		super(id, typeMultijoueurs, typeArgent, infoJeu);

		this.tableJeu = new ModeleTableJeuServeur(this);
		this.roueRoulette = new ModeleRoueRouletteServeur();
	}
	
	public Case getCaseResultat() {
		return caseResultat;
	}
	
	public void setCaseResultat(Case caseResultat) {
		this.caseResultat = caseResultat;
	}
	
	public Map<Integer, Integer> getListeGains() {
		return listeGains;
	}
	
	private TypeCouleurJoueurRoulette getCouleurLibre() {
		//TODO coder sa
		
		for(TypeCouleurJoueurRoulette typeCouleur : TypeCouleurJoueurRoulette.values()) {
			if(this.isCouleurJoueurLibre(typeCouleur)) {
				return typeCouleur;
			}
		}
		
		return null;
		
		//return TypeCouleurJoueurRoulette.BLEU;
	}
	
	private boolean isCouleurJoueurLibre(TypeCouleurJoueurRoulette typeCouleur) {
		for(JoueurServeur joueur : this.lstJoueurs) {
			if(((JoueurRoulette) joueur).getCouleur() == typeCouleur ) {
				return false;
			}
		}
		
		return true;
	}
	
	public void ajouterJoueur(ModeleClientServeur client) {
		if(!this.isPartiePleine()) {
			this.ajouterJoueur(new JoueurRoulette(client, this, this.getCouleurLibre()));
		}
	}
	
	/*public void ajouterJoueur(ModeleClientServeur client, TypeCouleurJoueurRoulette couleur) {
		if(!this.isPartiePleine()) {
			this.ajouterJoueur(new JoueurRoulette(client, this, couleur));
		}
	}*/

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
	
	public void resetMisesTerminees() {
		for(JoueurServeur joueur : this.lstJoueurs) {
			((JoueurRoulette) joueur).setMisesTerminees(false);
		}
	}
	
	public void resetMises() {
		this.tableJeu.resetMises();
		
		for(JoueurServeur joueur : this.lstJoueurs) {
			joueur.getClient().getConnexion().envoyerCommande(new CmdUpdateCasesRoulette(this.tableJeu.getCases()));
		}
	}
	
	public ModelePartieRouletteClient creerModeleClient() {
		ModelePartieRouletteClient modeleClient = new ModelePartieRouletteClient(
				this.id,
				this.typeMultijoueurs,
				this.typeArgent,
				this.infoJeu.creerModeleClient()
		);
		
		modeleClient.setLstJoueurs(this.creerSetJoueurClient(modeleClient));
		
		return modeleClient;
	}
	
	private Set<JoueurClient> creerSetJoueurClient(ModelePartieRouletteClient modelePartieClient) {
		Set<JoueurClient> lstJoueursClients = new HashSet<JoueurClient>();
		for(JoueurServeur joueur : this.lstJoueurs) {
			lstJoueursClients.add(joueur.creerModeleClient(modelePartieClient));
		}
		
		return lstJoueursClients;
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
	
	//aaa
	public void tournerRoulette() {
		int res;
        res = (int)(Math.random()*36);
        this.caseResultat = ListeCases.INSTANCE.getCaseNumero(res);
        
        System.out.println("le resultat est : " + caseResultat.toString());
	}

	public int calculerGainRoulette(JoueurServeur joueur) {
		int gainTotal = 0;
		System.out.println("gain cases : " + this.tableJeu.getCases().toString());
		for (Entry<Case, Map<Integer, Integer>> mCase : this.tableJeu.getCases().entrySet()) {
			for(Entry<Integer, Integer> mMise :  mCase.getValue().entrySet()) {
				if(mMise.getKey() == joueur.getId()) {
					System.out.println("joueur a misé sur : " + mCase.getKey() + " le montant : " + mMise.getValue());
					gainTotal += calculerGainCase(mCase.getKey(), mMise.getValue());
				}
			}
		}
		
		return gainTotal;
	}

	private int calculerGainCase(Case caseMise, Integer montantMise) {
		int gain = 0;
		
		switch(caseMise.getType()) {
			case CHIFFRE :
				if(caseMise.getNumero() == caseResultat.getNumero()) {
					gain = caseMise.getMultiplicateurGain() * montantMise;
				}
				break;
			case COULEUR :
				if(caseMise.getCouleur() == caseResultat.getCouleur()) {
					gain = caseMise.getMultiplicateurGain() * montantMise;
				}
				break;
			case PARITE :
				if(caseMise.estPaire() == caseResultat.estPaire()) {
					gain = caseMise.getMultiplicateurGain() * montantMise;
				}
				break;
		}
		
		//TODO On update son solde seulement apres le tour? (donc ses mises sont prise en compte seulement lorsque la roue est tournée
		return gain - montantMise;
		
		/*if (caseResultat.equals(caseMise)) {
			if (caseMise.getType()==TypeCase.COULEUR || caseMise.getType()==TypeCase.PARITE ){
				//System.out.println("Le joueur gagne avec la case : " + caseMise + " le montant de " + (10*montantMise));
				gain = 2 * montantMise;
			}
			else
			{
				gain = 36 * montantMise;
			}
		}
		return gain;*/
	}

	public void quitterPartie(int idJoueur) {
		JoueurServeur joueur = this.getJoueur(idJoueur);
		this.retirerJoueur(joueur);
	}

}
