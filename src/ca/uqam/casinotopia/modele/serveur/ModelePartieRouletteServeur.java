package ca.uqam.casinotopia.modele.serveur;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import ca.uqam.casinotopia.Jeu;
import ca.uqam.casinotopia.JoueurRoulette;
import ca.uqam.casinotopia.JoueurServeur;
import ca.uqam.casinotopia.Partie;
import ca.uqam.casinotopia.commande.client.roulette.CmdUpdateCasesRoulette;
import ca.uqam.casinotopia.commande.client.roulette.CmdUpdateListeJoueurs;
import ca.uqam.casinotopia.controleur.serveur.ControleurPrincipalServeur;
import ca.uqam.casinotopia.modele.Modele;
import ca.uqam.casinotopia.modele.client.ModelePartieRouletteClient;
import ca.uqam.casinotopia.objet.Case;
import ca.uqam.casinotopia.objet.JoueurClient;
import ca.uqam.casinotopia.objet.ListeCases;
import ca.uqam.casinotopia.objet.Utilisateur;
import ca.uqam.casinotopia.type.TypeCouleurJoueurRoulette;
import ca.uqam.casinotopia.type.TypeEtatPartie;
import ca.uqam.casinotopia.type.TypeJeuArgent;
import ca.uqam.casinotopia.type.TypeJeuMultijoueurs;

/**
 * Représente une instance de partie de roulette
 */
@SuppressWarnings("serial")
public class ModelePartieRouletteServeur extends Partie implements Modele {
	
	/**
	 * La case gagnante lors du dernier tour de roue.
	 */
	private Case caseResultat;
	
	/**
	 * La liste des gains : Map<IdJoueur, Gains>
	 */
	private Map <Integer, Integer> listeGains = new HashMap <Integer,Integer>();
	
	/**
	 * La table de jeu associée à la partie de roulette
	 */
	private ModeleTableJeuServeur tableJeu;

	public ModelePartieRouletteServeur(int id, TypeJeuMultijoueurs typeMultijoueurs, TypeJeuArgent typeArgent, TypeEtatPartie typeEtat, Jeu infoJeu) {
		super(id, typeMultijoueurs, typeArgent, typeEtat, infoJeu);

		this.tableJeu = new ModeleTableJeuServeur(this);
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
	
	/**
	 * Récupérer une couleur de jetons libre
	 * 
	 * @return Une couleur de jetons
	 */
	private TypeCouleurJoueurRoulette getCouleurLibre() {
		for(TypeCouleurJoueurRoulette typeCouleur : TypeCouleurJoueurRoulette.values()) {
			if(this.isCouleurJoueurLibre(typeCouleur)) {
				return typeCouleur;
			}
		}
		
		return null;
	}
	
	/**
	 * Vérifier si la couleur est libree
	 * 
	 * @param typeCouleur La couleur à vérifier
	 * @return True si la couleur n'est pas déjà utilisée, false sinon
	 */
	private boolean isCouleurJoueurLibre(TypeCouleurJoueurRoulette typeCouleur) {
		for(JoueurServeur joueur : this.lstJoueurs) {
			if(((JoueurRoulette) joueur).getCouleur() == typeCouleur ) {
				return false;
			}
		}
		
		return true;
	}
	
	public int getNbrJoueurs() {
		return this.lstJoueurs.size();
	}
	
	/**
	 * Déterminer si le nombre de joueur minimal de la partie est atteint
	 * 
	 * @return True s'il est atteint, false sinon
	 */
	public boolean isNbrMinimalJoueursAtteint() {
		return this.getNbrJoueurs() >= this.infoJeu.getNbrJoueursMin();
	}
	
	/**
	 * Déterminer si le nombre de joueur maximal de la partie est atteint
	 * 
	 * @return True s'il est atteint, false sinon
	 */
	public boolean isNbrMaximalJoueursAtteint() {
		return this.getNbrJoueurs() == this.infoJeu.getNbrJoueursMax();
	}
	
	/**
	 * Ajouter un joueur à la partie, si elle n'est pas pleine.
	 * 
	 * @param client Le client à ajouter
	 */
	public void ajouterJoueur(ModeleClientServeur client) {
		if(!this.isPartiePleine()) {
			this.ajouterJoueur(new JoueurRoulette(client, this, this.getCouleurLibre()));
		}
	}

	/**
	 * Ajouter des mises sur la table de jeu
	 * 
	 * @param mises Les mises à ajouter : Map<IdJoueur, Map<CaseMisee, NbrJetons>>
	 */
	public void effectuerMises(Map<Integer, Map<Case, Integer>> mises) {
		this.tableJeu.effectuerMises(mises);
	}
	
	/**
	 * Déterminer si tous les joueurs de la partie ont fini de miser
	 * 
	 * @return True si tous les joueurs ont terminés, false sinon.
	 */
	public boolean isToutesMisesTerminees() {
		for(JoueurServeur joueur : this.lstJoueurs) {
			if(!((JoueurRoulette) joueur).isMisesTerminees()) {
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Réinitialiser les flags de mises terminées
	 */
	public void resetMisesTerminees() {
		for(JoueurServeur joueur : this.lstJoueurs) {
			((JoueurRoulette) joueur).setMisesTerminees(false);
		}
	}
	
	/**
	 * Réinitialiser les mises côté serveur.
	 * Envoyer une commande à tous les joueurs de la partie pour réinitiliaser l'environnement client
	 */
	public void resetMises() {
		this.tableJeu.resetMises();
		
		for(JoueurServeur joueur : this.lstJoueurs) {
			joueur.getClient().getConnexion().envoyerCommande(new CmdUpdateCasesRoulette(this.tableJeu.getCases()));
		}
	}
	
	/**
	 * Créer la version client du modèle de roulette
	 * 
	 * @return La version client du modèle de roulette
	 */
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
	
	/**
	 * Créer la version client du Set de joueurs
	 * 
	 * @return La version client du Set de joueurs
	 */
	private Set<JoueurClient> creerSetJoueurClient(ModelePartieRouletteClient modelePartieClient) {
		Set<JoueurClient> lstJoueursClients = new HashSet<JoueurClient>();
		for(JoueurServeur joueur : this.lstJoueurs) {
			lstJoueursClients.add(joueur.creerModeleClient(modelePartieClient));
		}
		
		return lstJoueursClients;
	}

	public ModeleTableJeuServeur getTableJeu() {
		return this.tableJeu;
	}

	public void setTableJeu(ModeleTableJeuServeur tableJeu) {
		this.tableJeu = tableJeu;
	}
	
	/**
	 * Calculer le hasard de la roulette
	 */
	public void tournerRoulette() {
		int res;
        res = (int)(Math.random()*36);
        this.caseResultat = ListeCases.INSTANCE.getCaseNumero(res);
        
        System.out.println("le resultat est : " + caseResultat.toString());
	}

	/**
	 * Calculer les gains d'un joueur
	 * 
	 * @param joueur Le joueur sur le quel on veut calculer les gains.
	 * @return Les gains du joueur
	 */
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

	/**
	 * Pour chaque case misée par le joueur, vérifier si elle est gagnante et calculer le gain.
	 * 
	 * @param caseMise La case misée
	 * @param montantMise Le montant misé
	 * @return Le gain réalisé
	 */
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
		
		return gain;
	}

	/**
	 * Quitter la partie de roulette
	 * 
	 * @param idJoueur L'id du joueur qui quitte
	 */
	//TODO L'id du joueur n'est plus nécessaire
	public void quitterPartie(int idJoueur) {
		JoueurServeur joueur = this.getJoueur(idJoueur);
		this.deconnecter(joueur.getClient());
		this.retirerJoueur(joueur);
	}

	@Override
	public void connecter(Utilisateur utilisateur) {
		ModeleClientServeur client = (ModeleClientServeur) utilisateur;
		if(!this.isPartiePleine()) {
			this.ajouterJoueur(new JoueurRoulette(client, this, this.getCouleurLibre()));
			utilisateur.ajouterConnectable(this);
		}
	}

	@Override
	public void deconnecter(Utilisateur utilisateur) {
		ModeleClientServeur client = (ModeleClientServeur) utilisateur;
		JoueurServeur joueur = this.getJoueur(client);
		
		this.lstJoueurs.remove(joueur);
		
		if(this.isPartieVide()) {
			ControleurPrincipalServeur.getInstance().retirerPartie(this);
		}
		else {
			this.tableJeu.retirerMises(joueur.getId());
			
			
			
			CmdUpdateListeJoueurs cmdUpdateListeJoueurs = new CmdUpdateListeJoueurs(this.creerSetJoueurClient(this.creerModeleClient()));
			CmdUpdateCasesRoulette cmdUpdateCasesRoulette = new CmdUpdateCasesRoulette(this.tableJeu.getCases());
			
			//TODO Faudrait rafraichir la vue des jetons, ie: retirer les mises du joueurs qui quitte
			for(JoueurServeur autreJoueur : this.lstJoueurs) {
				autreJoueur.getClient().getConnexion().envoyerCommande(cmdUpdateListeJoueurs);
				autreJoueur.getClient().getConnexion().envoyerCommande(cmdUpdateCasesRoulette);
			}
		}
	}
}