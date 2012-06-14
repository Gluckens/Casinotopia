package ca.uqam.casinotopia;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import ca.uqam.casinotopia.connexion.Connectable;
import ca.uqam.casinotopia.objet.Clavardage;
import ca.uqam.casinotopia.objet.PartieClient;
import ca.uqam.casinotopia.type.TypeEtatPartie;
import ca.uqam.casinotopia.type.TypeJeu;
import ca.uqam.casinotopia.type.TypeJeuArgent;
import ca.uqam.casinotopia.type.TypeJeuMultijoueurs;

public abstract class Partie implements Comparable<Partie>, Serializable, Connectable {
	
	private static final long serialVersionUID = 2847945782668066206L;
	
	protected int id;
	protected TypeJeuMultijoueurs typeMultijoueurs;
	protected TypeJeuArgent typeArgent;
	protected TypeEtatPartie typeEtat;
	protected Clavardage clavardage;
	protected Set<JoueurServeur> lstJoueurs;
	protected Jeu infoJeu;
	
	public Partie(int id, Jeu infoJeu) {
		this(id, TypeJeuMultijoueurs.NO_VALUE, TypeJeuArgent.NO_VALUE, TypeEtatPartie.NO_VALUE, infoJeu, null);
	}

	//public Partie(int id, boolean optionArgent, boolean optionMultijoueur, Jeu infoJeu) {
	public Partie(int id, TypeJeuMultijoueurs typeMultijoueurs, TypeJeuArgent typeArgent, TypeEtatPartie typeEtat, Jeu infoJeu) {
		this(id, typeMultijoueurs, typeArgent, typeEtat, infoJeu, new Clavardage(infoJeu.getNom() + id));
		
		this.id = id;
		this.typeMultijoueurs = typeMultijoueurs;
		this.typeArgent = typeArgent;
		this.typeEtat = typeEtat;
		this.clavardage = new Clavardage(infoJeu.getNom() + this.id);

		this.infoJeu = infoJeu;

		this.lstJoueurs = new HashSet<JoueurServeur>();
	}
	
	private Partie(int id, TypeJeuMultijoueurs typeMultijoueurs, TypeJeuArgent typeArgent, TypeEtatPartie typeEtat, Jeu infoJeu, Clavardage clavardage) {
		this.id = id;
		this.typeMultijoueurs = typeMultijoueurs;
		this.typeArgent = typeArgent;
		this.typeEtat = typeEtat;
		this.clavardage = clavardage;

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
	
	public abstract PartieClient creerModeleClient();
	
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
	
	public TypeEtatPartie getTypeEtat() {
		return this.typeEtat;
	}
	
	public boolean isEnAttente() {
		return this.typeEtat == TypeEtatPartie.EN_ATTENTE;
	}
	
	public void demarrerPartie() {
		this.typeEtat = TypeEtatPartie.EN_COURS;
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