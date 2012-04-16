package ca.uqam.casinotopia;

import java.util.HashSet;
import java.util.Set;

public abstract class Partie {
	private int id;
	private boolean optionArgent;
	private boolean optionMultijoueur;
	private Clavardage clavardage;
	private Set<Joueur> joueurs = new HashSet<Joueur>();
	
	public Partie(int id, boolean optionArgent, boolean optionMultijoueur, Clavardage clavardage) {
		this.id = id;
		this.optionArgent = optionArgent;
		this.optionMultijoueur = optionMultijoueur;
		this.clavardage = clavardage;
	}
	
	public void ajouterJoueur(Joueur joueur) {
		this.joueurs.add(joueur);
	}
	
	public void retirerJoueur(Joueur joueur) {
		this.joueurs.remove(joueur);
	}
	
	public int getId() {
		return this.id;
	}
}