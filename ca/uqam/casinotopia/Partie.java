package ca.uqam.casinotopia;

import java.util.HashSet;
import java.util.Set;

public abstract class Partie implements Comparable<Partie> {
	private int id;
	private boolean optionArgent;
	private boolean optionMultijoueur;
	private Clavardage clavardage;
	private Set<Joueur> lstJoueurs;
	private Jeu infoJeu;
	
	public Partie(int id, boolean optionArgent, boolean optionMultijoueur, Jeu infoJeu) {
		this.id = id;
		this.optionArgent = optionArgent;
		this.optionMultijoueur = optionMultijoueur;
		//this.clavardage = new Clavardage(this.getTypeJeu() + String.valueOf(this.id));
		this.clavardage = new Clavardage(infoJeu.getNom() + String.valueOf(this.id));
		
		this.infoJeu = infoJeu;
		
		this.lstJoueurs = new HashSet<Joueur>();
	}
	
	public void ajouterJoueur(Joueur joueur) {
		this.lstJoueurs.add(joueur);
	}
	
	public void retirerJoueur(Joueur joueur) {
		this.lstJoueurs.remove(joueur);
	}
	
	public TypeJeu getTypeJeu() {
		return this.infoJeu.getType();
	}
	
	public int getId() {
		return this.id;
	}

	/**
	 * @return the infoJeu
	 */
	public Jeu getInfoJeu() {
		return infoJeu;
	}

	/**
	 * @param infoJeu the infoJeu to set
	 */
	public void setInfoJeu(Jeu infoJeu) {
		this.infoJeu = infoJeu;
	}
	
	
	public int compareTo(Partie p) {
		int placeRestante1 = this.infoJeu.getNbrJoueursMax() - this.lstJoueurs.size();
		int placeRestante2 = p.infoJeu.getNbrJoueursMax() - p.lstJoueurs.size();

		System.out.println(this + " --> " + this.infoJeu.getNom() + "(" + String.valueOf(placeRestante1) + ") compareTo " + p + " --> " + p.infoJeu.getNom() + "(" + String.valueOf(placeRestante2) + ")");
		
		return Integer.valueOf(placeRestante1).compareTo(placeRestante2);
	}
}