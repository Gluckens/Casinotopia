package ca.uqam.casinotopia;

import java.util.HashSet;
import java.util.Set;

import ca.uqam.casinotopia.connexion.Connectable;
import ca.uqam.casinotopia.modele.serveur.ModeleClientServeur;
import ca.uqam.casinotopia.objet.Clavardage;
import ca.uqam.casinotopia.objet.PartieClient;
import ca.uqam.casinotopia.type.TypeEtatPartie;
import ca.uqam.casinotopia.type.TypeJeu;
import ca.uqam.casinotopia.type.TypeJeuArgent;
import ca.uqam.casinotopia.type.TypeJeuMultijoueurs;

/**
 * Classe abstraite regroupant les informations d'une partie côté serveur
 */
public abstract class Partie implements Comparable<Partie>, Connectable {
	
	/**
	 * Id de la partie
	 */
	protected int id;
	
	/**
	 * Type de jeu multijoueur
	 */
	protected TypeJeuMultijoueurs typeMultijoueurs;
	
	/**
	 * Type de jeu d'argent
	 */
	protected TypeJeuArgent typeArgent;
	
	/**
	 * État de la partie (en attente ou en cours)
	 */
	protected TypeEtatPartie typeEtat;
	
	/**
	 * Clavardage associé à la partie
	 */
	protected Clavardage clavardage;
	
	/**
	 * Liste des joueurs dans la partie
	 */
	protected Set<JoueurServeur> lstJoueurs;
	
	/**
	 * Jeu que représente la partie
	 */
	protected Jeu infoJeu;
	
	public Partie(int id, Jeu infoJeu) {
		this(id, TypeJeuMultijoueurs.NO_VALUE, TypeJeuArgent.NO_VALUE, TypeEtatPartie.NO_VALUE, infoJeu, null);
	}

	public Partie(int id, TypeJeuMultijoueurs typeMultijoueurs, TypeJeuArgent typeArgent, TypeEtatPartie typeEtat, Jeu infoJeu) {
		this(id, typeMultijoueurs, typeArgent, typeEtat, infoJeu, new Clavardage(infoJeu.getNom() + id));
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
	
	/**
	 * Récupérer un joueur par son instance de client
	 * 
	 * @param client L'instance de client du joueur demandé
	 * @return Le joueur
	 */
	
	public JoueurServeur getJoueur(ModeleClientServeur client) {
		return this.getJoueur(client.getId());
	}
	
	/**
	 * Récupérer un joueur par son id
	 * 
	 * @param idJoueur L'id du joueur demandé
	 * @return Le joueur, null si inexistant
	 */
	
	public JoueurServeur getJoueur(int idJoueur) {
		for(JoueurServeur joueur : this.lstJoueurs) {
			if(joueur.getId() == idJoueur) {
				return joueur;
			}
		}
		
		return null;
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

	public Clavardage getClavardage() {
		return this.clavardage;
	}

	public void setClavardage(Clavardage clavardage) {
		this.clavardage = clavardage;
	}

	public Jeu getInfoJeu() {
		return this.infoJeu;
	}

	public void setInfoJeu(Jeu infoJeu) {
		this.infoJeu = infoJeu;
	}

	@Override
	public int compareTo(Partie p) {
		int placeRestante1 = this.infoJeu.getNbrJoueursMax() - this.lstJoueurs.size();
		int placeRestante2 = p.infoJeu.getNbrJoueursMax() - p.lstJoueurs.size();

		return Integer.valueOf(placeRestante1).compareTo(placeRestante2);
	}
}