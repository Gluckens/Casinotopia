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

public class ModelePartieRouletteClient extends PartieClient implements Modele, Observable {
	
	private static final long serialVersionUID = -1713451671266579670L;
	
	private int gain;
	private Case caseResultat;
	private ModeleTableJeuClient tableJeu;
	private BaseObservable sujet = new BaseObservable(this);
	
	private TypeModifPartieRoulette typeModif;

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
	
	public void setGain(int gain) {
		this.gain = gain;
		this.notifierObservateur();
	}
	
	public void updateListeJoueurs(Set<JoueurClient> lstJoueurs) {
		this.lstJoueurs = lstJoueurs;
		this.typeModif = TypeModifPartieRoulette.MODIFICATION_JOUEURS;
		this.notifierObservateur();
	}

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
		System.out.println("Alexei --> ModelePartieRouletteClient.notifierObservateur()");
		this.sujet.notifierObservateur();
	}

	@Override
	public TypeModifPartieRoulette getTypeModif() {
		return this.typeModif;
	}
}
