package ca.uqam.casinotopia;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public abstract class PartieClient implements Comparable<PartieClient>, Serializable {
	
	private static final long serialVersionUID = -2380888793385476200L;
	
	protected int id;
	protected TypeJeuMultijoueurs typeMultijoueurs;
	protected TypeJeuArgent typeArgent;
	protected Clavardage clavardage;
	protected Set<JoueurClient> lstJoueurs;
	protected JeuClient infoJeu;

	public PartieClient(int id, TypeJeuMultijoueurs typeMultijoueurs, TypeJeuArgent typeArgent, JeuClient infoJeu) {
		this.id = id;
		this.typeMultijoueurs = typeMultijoueurs;
		this.typeArgent = typeArgent;
		this.clavardage = new Clavardage(infoJeu.getNom() + this.id);

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
	public JeuClient getInfoJeu() {
		return this.infoJeu;
	}

	/**
	 * @param infoJeu
	 *            the infoJeu to set
	 */
	public void setInfoJeu(JeuClient infoJeu) {
		this.infoJeu = infoJeu;
	}

	@Override
	public int compareTo(PartieClient p) {
		int placeRestante1 = this.infoJeu.getNbrJoueursMax() - this.lstJoueurs.size();
		int placeRestante2 = p.infoJeu.getNbrJoueursMax() - p.lstJoueurs.size();

		//System.out.println(this + " --> " + this.infoJeu.getNom() + "(" + String.valueOf(placeRestante1) + ") compareTo " + p + " --> " + p.infoJeu.getNom() + "(" + String.valueOf(placeRestante2) + ")");

		return Integer.valueOf(placeRestante1).compareTo(placeRestante2);
	}
}