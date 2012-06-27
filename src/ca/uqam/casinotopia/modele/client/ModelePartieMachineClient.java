package ca.uqam.casinotopia.modele.client;

import ca.uqam.casinotopia.modele.Modele;
import ca.uqam.casinotopia.objet.JeuClient;
import ca.uqam.casinotopia.objet.PartieClient;
import ca.uqam.casinotopia.observateur.BaseObservable;
import ca.uqam.casinotopia.observateur.Observable;
import ca.uqam.casinotopia.observateur.Observateur;
import ca.uqam.casinotopia.type.modif.TypeModif;

/**
 * Représente une instance de partie de machine à sous
 */
public class ModelePartieMachineClient extends PartieClient implements Modele, Observable  {
	
	private static final long serialVersionUID = -8334003732509397091L;
	
	private BaseObservable sujet = new BaseObservable(this);

	public ModelePartieMachineClient(int id, JeuClient infoJeu) {
		super(id, infoJeu);
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