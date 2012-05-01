package ca.uqam.casinotopia;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public abstract class PartieClient implements Comparable<PartieClient>, Serializable {
	
	private static final long serialVersionUID = -2380888793385476200L;
	
	protected int id;
	protected boolean optionArgent;
	protected boolean optionMultijoueur;
	protected Clavardage clavardage;
	protected Set<JoueurClient> lstJoueurs;
	protected Jeu infoJeu;

	public PartieClient(int id, boolean optionArgent, boolean optionMultijoueur, Jeu infoJeu) {
		this.id = id;
		this.setOptionArgent(optionArgent);
		this.setOptionMultijoueur(optionMultijoueur);
		// this.clavardage = new Clavardage(this.getTypeJeu() +
		// String.valueOf(this.id));
		this.setClavardage(new Clavardage(infoJeu.getNom() + String.valueOf(this.id)));

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

	/**
	 * @return the optionArgent
	 */
	public boolean isOptionArgent() {
		return this.optionArgent;
	}

	/**
	 * @param optionArgent
	 *            the optionArgent to set
	 */
	public void setOptionArgent(boolean optionArgent) {
		this.optionArgent = optionArgent;
	}

	/**
	 * @return the optionMultijoueur
	 */
	public boolean isOptionMultijoueur() {
		return this.optionMultijoueur;
	}

	/**
	 * @param optionMultijoueur
	 *            the optionMultijoueur to set
	 */
	public void setOptionMultijoueur(boolean optionMultijoueur) {
		this.optionMultijoueur = optionMultijoueur;
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
	public int compareTo(PartieClient p) {
		int placeRestante1 = this.infoJeu.getNbrJoueursMax() - this.lstJoueurs.size();
		int placeRestante2 = p.infoJeu.getNbrJoueursMax() - p.lstJoueurs.size();

		//System.out.println(this + " --> " + this.infoJeu.getNom() + "(" + String.valueOf(placeRestante1) + ") compareTo " + p + " --> " + p.infoJeu.getNom() + "(" + String.valueOf(placeRestante2) + ")");

		return Integer.valueOf(placeRestante1).compareTo(placeRestante2);
	}
}