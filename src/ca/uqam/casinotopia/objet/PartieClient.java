package ca.uqam.casinotopia.objet;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import ca.uqam.casinotopia.type.TypeJeu;
import ca.uqam.casinotopia.type.TypeJeuArgent;
import ca.uqam.casinotopia.type.TypeJeuMultijoueurs;

/**
 * Classe abstraite regroupant les informations d'une partie côté client
 */
public abstract class PartieClient implements Comparable<PartieClient>, Serializable {
	
	private static final long serialVersionUID = -9165716931187429058L;
	
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
	 * Clavardage associé à la partie
	 */
	protected Clavardage clavardage;
	
	/**
	 * Liste des joueurs dans la partie
	 */
	protected Set<JoueurClient> lstJoueurs;
	
	/**
	 * Jeu que représente la partie
	 */
	protected JeuClient infoJeu;
	
	public PartieClient(int id, JeuClient infoJeu) {
		this(id, TypeJeuMultijoueurs.NO_VALUE, TypeJeuArgent.NO_VALUE, infoJeu, null);
	}

	public PartieClient(int id, TypeJeuMultijoueurs typeMultijoueurs, TypeJeuArgent typeArgent, JeuClient infoJeu) {
		this(id, typeMultijoueurs, typeArgent, infoJeu, new Clavardage(infoJeu.getNom() + id));
	}
	
	private PartieClient(int id, TypeJeuMultijoueurs typeMultijoueurs, TypeJeuArgent typeArgent, JeuClient infoJeu, Clavardage clavardage) {
		this.id = id;
		this.typeMultijoueurs = typeMultijoueurs;
		this.typeArgent = typeArgent;
		this.clavardage = clavardage;

		this.infoJeu = infoJeu;

		this.lstJoueurs = new HashSet<JoueurClient>();
	}
	
	public boolean isPartieVide() {
		return (this.lstJoueurs.size() == 0);
	}
	
	public boolean isPartiePleine() {
		return this.lstJoueurs.size() >= this.infoJeu.getNbrJoueursMax();
	}
	
	public void ajouterJoueur(JoueurClient joueur) {
		if(!this.isPartiePleine()) {
			this.lstJoueurs.add(joueur);
		}
	}

	public void retirerJoueur(JoueurClient joueur) {
		this.lstJoueurs.remove(joueur);
	}
	
	/**
	 * Récupérer un joueur par son id
	 * 
	 * @param idJoueur L'id du joueur demandé
	 * @return Le joueur, null si inexistant
	 */
	public JoueurClient getJoueur(int idJoueur) {
		JoueurClient joueurTrouve = null;
		
		for(JoueurClient joueur : this.lstJoueurs) {
			if(joueur.getId() == idJoueur) {
				joueurTrouve = joueur;
			}
		}
		
		return joueurTrouve;
	}
	
	public Set<JoueurClient> getLstJoueurs() {
		return this.lstJoueurs;
	}
	
	public void setLstJoueurs(Set<JoueurClient> lstJoueurs) {
		this.lstJoueurs = lstJoueurs;
	}

	public TypeJeu getTypeJeu() {
		return this.infoJeu.getType();
	}

	public int getId() {
		return this.id;
	}

	public boolean isAvecArgent() {
		return this.typeArgent == TypeJeuArgent.ARGENT;
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

	public Clavardage getClavardage() {
		return this.clavardage;
	}

	public void setClavardage(Clavardage clavardage) {
		this.clavardage = clavardage;
	}

	public JeuClient getInfoJeu() {
		return this.infoJeu;
	}

	public void setInfoJeu(JeuClient infoJeu) {
		this.infoJeu = infoJeu;
	}

	@Override
	public int compareTo(PartieClient p) {
		int placeRestante1 = this.infoJeu.getNbrJoueursMax() - this.lstJoueurs.size();
		int placeRestante2 = p.infoJeu.getNbrJoueursMax() - p.lstJoueurs.size();

		return Integer.valueOf(placeRestante1).compareTo(placeRestante2);
	}
}