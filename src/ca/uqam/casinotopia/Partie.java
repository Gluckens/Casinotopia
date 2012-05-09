package ca.uqam.casinotopia;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public abstract class Partie implements Comparable<Partie>, Serializable {
	
	private static final long serialVersionUID = 2847945782668066206L;
	
	protected int id;
	protected TypeJeuMultijoueurs typeMultijoueurs;
	protected TypeJeuArgent typeArgent;
	protected Clavardage clavardage;
	protected Set<JoueurServeur> lstJoueurs;
	protected Jeu infoJeu;

	//public Partie(int id, boolean optionArgent, boolean optionMultijoueur, Jeu infoJeu) {
	public Partie(int id, TypeJeuMultijoueurs typeMultijoueurs, TypeJeuArgent typeArgent, Jeu infoJeu) {
		this.id = id;
		this.typeMultijoueurs = typeMultijoueurs;
		this.typeArgent = typeArgent;
		this.clavardage = new Clavardage(infoJeu.getNom() + this.id);

		this.infoJeu = infoJeu;

		this.lstJoueurs = new HashSet<JoueurServeur>();
	}
	
	public boolean isPartieVide() {
		return (this.lstJoueurs.size() == 0);
	}
	
	public boolean isPartiePleine() {
		return this.lstJoueurs.size() >= this.infoJeu.getNbrJoueursMax();
	}
	
	public void ajouterJoueur(JoueurServeur joueurServeur) {
		if(!this.isPartiePleine()) {
			this.lstJoueurs.add(joueurServeur);
		}
	}

	public void retirerJoueur(JoueurServeur joueurServeur) {
		this.lstJoueurs.remove(joueurServeur);
	}
	
	public JoueurServeur getJoueur(int idJoueur) {
		JoueurServeur joueurTrouve = null;
		
		for(JoueurServeur joueur : this.lstJoueurs) {
			if(joueur.getId() == idJoueur) {
				joueurTrouve = joueur;
			}
		}
		
		return joueurTrouve;
	}
	
	public Set<JoueurServeur> getLstJoueurs() {
		return this.lstJoueurs;
	}

	public TypeJeu getTypeJeu() {
		return this.infoJeu.getType();
	}

	public int getId() {
		return this.id;
	}
	
	public TypeJeuMultijoueurs getTypeMultijoueurs() {
		return this.typeMultijoueurs;
	}
	
	public boolean isMultijoueurs() {
		return (this.isAvecInconnus() || this.isAvecAmis());
	}
	
	public boolean isAvecInconnus() {
		return this.typeMultijoueurs == TypeJeuMultijoueurs.INCONNUS;
	}
	
	public boolean isAvecAmis() {
		return this.typeMultijoueurs == TypeJeuMultijoueurs.AMIS;
	}
	
	public TypeJeuArgent getTypeArgent() {
		return this.typeArgent;
	}
	
	public boolean isAvecArgent() {
		return this.typeArgent == TypeJeuArgent.ARGENT;
	}

	/**
	 * @return the clavardage
	 */
	public Clavardage getClavardage() {
		return this.clavardage;
	}

	/**
	 * @param clavardage
	 *            the clavardage to set
	 */
	public void setClavardage(Clavardage clavardage) {
		this.clavardage = clavardage;
	}

	/**
	 * @return the infoJeu
	 */
	public Jeu getInfoJeu() {
		return this.infoJeu;
	}

	/**
	 * @param infoJeu
	 *            the infoJeu to set
	 */
	public void setInfoJeu(Jeu infoJeu) {
		this.infoJeu = infoJeu;
	}

	@Override
	public int compareTo(Partie p) {
		int placeRestante1 = this.infoJeu.getNbrJoueursMax() - this.lstJoueurs.size();
		int placeRestante2 = p.infoJeu.getNbrJoueursMax() - p.lstJoueurs.size();

		//System.out.println(this + " --> " + this.infoJeu.getNom() + "(" + String.valueOf(placeRestante1) + ") compareTo " + p + " --> " + p.infoJeu.getNom() + "(" + String.valueOf(placeRestante2) + ")");

		return Integer.valueOf(placeRestante1).compareTo(placeRestante2);
	}
}