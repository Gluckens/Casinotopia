package ca.uqam.casinotopia.modele.client;

import java.util.Map;

import ca.uqam.casinotopia.Case;
import ca.uqam.casinotopia.Jeu;
import ca.uqam.casinotopia.PartieClient;
import ca.uqam.casinotopia.modele.Modele;
import ca.uqam.casinotopia.modif.TypeModif;
import ca.uqam.casinotopia.observateur.BaseObservable;
import ca.uqam.casinotopia.observateur.Observable;
import ca.uqam.casinotopia.observateur.Observateur;

public class ModelePartieRouletteClient extends PartieClient implements Modele, Observable {
	
	private static final long serialVersionUID = -1713451671266579670L;
	
	private Case caseResultat;
	private ModeleTableJeuClient tableJeu;
	private BaseObservable sujet = new BaseObservable(this);

	public ModelePartieRouletteClient(int id, boolean optionArgent, boolean optionMultijoueur, Jeu infoJeu) {
		this(id, optionArgent, optionMultijoueur, infoJeu, new ModeleTableJeuClient());
	}
	
	public ModelePartieRouletteClient(int id, boolean optionArgent, boolean optionMultijoueur, Jeu infoJeu, Map<Case, Map<Integer, Integer>> cases) {
		this(id, optionArgent, optionMultijoueur, infoJeu, new ModeleTableJeuClient(cases));
	}
	
	public ModelePartieRouletteClient(int id, boolean optionArgent, boolean optionMultijoueur, Jeu infoJeu, ModeleTableJeuClient tableJeu) {
		super(id, optionArgent, optionMultijoueur, infoJeu);

		this.tableJeu = tableJeu;
	}

	public void updateTableJeu(Map<Case, Map<Integer, Integer>> cases) {
		this.tableJeu.updateTableJeu(cases);

		// TODO enlever ce notifier et le traite dans tableJeu
		//this.notifierObservateur();
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
	public TypeModif getTypeModif() {
		// TODO Auto-generated method stub
		return null;
	}
}
