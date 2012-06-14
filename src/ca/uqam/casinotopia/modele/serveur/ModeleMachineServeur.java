package ca.uqam.casinotopia.modele.serveur;

import ca.uqam.casinotopia.Jeu;
import ca.uqam.casinotopia.Partie;
import ca.uqam.casinotopia.modele.Modele;
import ca.uqam.casinotopia.objet.PartieClient;
import ca.uqam.casinotopia.objet.Utilisateur;
import ca.uqam.casinotopia.observateur.Observable;
import ca.uqam.casinotopia.observateur.Observateur;
import ca.uqam.casinotopia.type.modif.TypeModif;


public class ModeleMachineServeur extends Partie implements Modele, Observable {

	public ModeleMachineServeur(int id, Jeu infoJeu) {
		super(id, infoJeu);
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = 8854771788955204109L;

	@Override
	public void ajouterObservateur(Observateur obs) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void retirerObservateur(Observateur obs) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean estObservePar(Observateur obs) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void notifierObservateur() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TypeModif getTypeModif() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void connecter(Utilisateur utilisateur) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deconnecter(Utilisateur utilisateur) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PartieClient creerModeleClient() {
		// TODO Auto-generated method stub
		return null;
	}

}
