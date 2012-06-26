package ca.uqam.casinotopia.modele.client;

import java.util.Map;
import java.util.Set;

import ca.uqam.casinotopia.modele.Modele;
import ca.uqam.casinotopia.objet.Case;
import ca.uqam.casinotopia.objet.JeuClient;
import ca.uqam.casinotopia.objet.JoueurClient;
import ca.uqam.casinotopia.objet.PartieClient;
import ca.uqam.casinotopia.observateur.BaseObservable;
import ca.uqam.casinotopia.observateur.Observable;
import ca.uqam.casinotopia.observateur.Observateur;
import ca.uqam.casinotopia.type.TypeJeuArgent;
import ca.uqam.casinotopia.type.TypeJeuMultijoueurs;
import ca.uqam.casinotopia.type.modif.TypeModifPartieRoulette;

/**
 * Représente une instance de partie de roulette
 */
public class ModelePartieRouletteClient extends PartieClient implements Modele, Observable {
	
	private static final long serialVersionUID = 8914475130716960096L;
	
	/**
	 * Le gain du joueur lors du dernier tour de roue.
	 */
	private int gain;
	
	/**
	 * La case gagnante lors du dernier tour de roue.
	 */
	private Case caseResultat;
	
	/**
	 * La table de jeu associée à la partie de roulette
	 */
	private ModeleTableJeuClient tableJeu;
	
	/**
	 * Type de modification effectué sur le modèle.
	 * Ceci sera lu par l'observateur pour savoir quelle fonction appeler
	 */
	private TypeModifPartieRoulette typeModif;
	
	/**
	 * Délégation des fonctions de l'interface observable à l'objet BaseObservable
	 */
	private BaseObservable sujet = new BaseObservable(this);

	public ModelePartieRouletteClient(int id, TypeJeuMultijoueurs typeMultijoueurs, TypeJeuArgent typeArgent, JeuClient infoJeu) {
		this(id, typeMultijoueurs, typeArgent, infoJeu, new ModeleTableJeuClient());
	}
	
	public ModelePartieRouletteClient(int id, TypeJeuMultijoueurs typeMultijoueurs, TypeJeuArgent typeArgent, JeuClient infoJeu, Map<Case, Map<Integer, Integer>> cases) {
		this(id, typeMultijoueurs, typeArgent, infoJeu, new ModeleTableJeuClient(cases));
	}
	
	public ModelePartieRouletteClient(int id, TypeJeuMultijoueurs typeMultijoueurs, TypeJeuArgent typeArgent, JeuClient infoJeu, ModeleTableJeuClient tableJeu) {
		super(id, typeMultijoueurs, typeArgent, infoJeu);

		this.tableJeu = tableJeu;
	}
	
	public Case getCaseResultat() {
		return caseResultat;
	}
	
	public void setCaseResultat(Case caseResultat) {
		this.caseResultat = caseResultat;
	}
	
	public int getGain() {
		return gain;
	}
	
	/**
	 * Définir le gain du joueur pour le tour courant.
	 * Notifier les observateurs suite à la modification.
	 * 
	 * @param gain Le nouveau gain du joueur
	 */
	public void setGain(int gain) {
		this.gain = gain;
		this.typeModif = TypeModifPartieRoulette.SET_GAINS;
		this.notifierObservateur();
	}
	
	/**
	 * Mettre à jour la liste de joueurs
	 * Notifier les observateurs suite à la modification.
	 * 
	 * @param lstJoueurs La nouvelle liste de joueurs
	 */
	public void updateListeJoueurs(Set<JoueurClient> lstJoueurs) {
		this.lstJoueurs = lstJoueurs;
		this.typeModif = TypeModifPartieRoulette.MODIFICATION_JOUEURS;
		this.notifierObservateur();
	}

	/**
	 * Mettre à jour la table de jeu.
	 * 
	 * @param cases La nouvelle liste de case : Map<Case, Map<IdJoueur, NbrJetons>>
	 */
	public void updateTableJeu(Map<Case, Map<Integer, Integer>> cases) {
		this.tableJeu.updateTableJeu(cases);
	}

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

	@Override
	public TypeModifPartieRoulette getTypeModif() {
		return this.typeModif;
	}
}