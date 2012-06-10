package ca.uqam.casinotopia;

import java.awt.Rectangle;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.Map.Entry;

import ca.uqam.casinotopia.controleur.serveur.ControleurPrincipalServeur;
import ca.uqam.casinotopia.modele.client.ModelePartieRouletteClient;
import ca.uqam.casinotopia.modele.serveur.ModeleSalleServeur;

public class Jeu implements Serializable {
	
	private static final long serialVersionUID = 7375777588643978481L;
	
	private int id;
	private String nom;
	private String description;
	private String reglesJeu;
	private Rectangle emplacement;
	private int nbrJoueursMin;
	private int nbrJoueursMax;

	private Map<TypeEtatPartie, Map<Integer, Partie>> lstParties;

	// private List<Partie> lstParties;

	private TypeJeu type;

	/*
	 * public Jeu() { this.lstParties = new HashMap<TypeEtatPartie, Map<Integer,
	 * Partie>>(); //this.lstParties = new ArrayList<Partie>(); }
	 */

	public Jeu(int id, String nom, String description, String reglesJeu, Rectangle emplacement, int nbrJoueursMin, int nbrJoueursMax, TypeJeu type) {
		this.setId(id);
		this.nom = nom;
		this.description = description;
		this.reglesJeu = reglesJeu;
		this.emplacement = emplacement;
		this.nbrJoueursMin = nbrJoueursMin;
		this.nbrJoueursMax = nbrJoueursMax;

		this.type = type;

		this.initLstParties();
	}

	private void initLstParties() {
		this.lstParties = new HashMap<TypeEtatPartie, Map<Integer, Partie>>();
		this.lstParties.put(TypeEtatPartie.EN_ATTENTE, new HashMap<Integer, Partie>());
		this.lstParties.put(TypeEtatPartie.EN_COURS, new HashMap<Integer, Partie>());
	}

	public Partie getPartie(int idPartie) {
		Partie partie = this.lstParties.get(TypeEtatPartie.EN_ATTENTE).get(idPartie);
		if (partie == null) {
			partie = this.lstParties.get(TypeEtatPartie.EN_COURS).get(idPartie);
		}
		return partie;
	}

	public Partie getPartie(Partie partie) {
		return this.getPartie(partie.getId());
	}

	public Map<Integer, Partie> getMapPartie(int idPartie) {
		Map<Integer, Partie> mapPartie = null;
		for (Map<Integer, Partie> mapPartieCourant : this.lstParties.values()) {
			if (mapPartieCourant.get(idPartie) != null) {
				mapPartie = mapPartieCourant;
				break;
			}
		}

		return mapPartie;
	}

	public void ajouterPartie(Partie partie, TypeEtatPartie etat) {
		this.lstParties.get(etat).put(partie.getId(), partie);
	}

	// Quelle est la politique de recherche de partie en cours? On cherche celle avec le moins de joueur manquant avant d'attendre le nombre maximal?
	// TODO Cette fonction sera appelé lorsqu'un joueur veut jouer à un jeu.
	// Elle devra regarder dans la liste de partie s'il y en a une en attente et dont le nombre maximale de joueur n'est pas atteint
	// (possible que le nbrMaxJoueur d'une partie en attente soit atteinte?
	// quand le dernier joueur entre dans une partie en attente, elle ne s'en va directement dans partie en cours?)
	public Partie rechercherPartieEnAttente() {
		Partie partieEnAttente = null;

		if (!this.lstParties.get(TypeEtatPartie.EN_ATTENTE).isEmpty()) {
			//System.out.println("PartiesEnAttente : " + this.lstParties.get(TypeEtatPartie.EN_ATTENTE));

			SortedSet<Entry<Integer, Partie>> lstPartiesSorted = ControleurPrincipalServeur.entriesSortedByValues(this.lstParties.get(TypeEtatPartie.EN_ATTENTE));

			//System.out.println("PartiesEnAttenteSORTED : " + lstPartiesSorted);

			partieEnAttente = lstPartiesSorted.first().getValue();
		}

		return partieEnAttente;
	}
	
	public JeuClient creerModeleClient() {
		return new JeuClient(
			this.id,
			this.nom,
			this.description,
			this.reglesJeu,
			this.emplacement,
			this.nbrJoueursMin,
			this.nbrJoueursMax,
			this.type
		);
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the nom
	 */
	public String getNom() {
		return this.nom;
	}

	/**
	 * @param nom
	 *            the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the reglesJeu
	 */
	public String getReglesJeu() {
		return this.reglesJeu;
	}

	/**
	 * @param reglesJeu
	 *            the reglesJeu to set
	 */
	public void setReglesJeu(String reglesJeu) {
		this.reglesJeu = reglesJeu;
	}

	/**
	 * @return the emplacement
	 */
	public Rectangle getEmplacement() {
		return emplacement;
	}

	/**
	 * @param emplacement the emplacement to set
	 */
	public void setEmplacement(Rectangle emplacement) {
		this.emplacement = emplacement;
	}

	/**
	 * @return the nbrJoueursMin
	 */
	public int getNbrJoueursMin() {
		return this.nbrJoueursMin;
	}

	/**
	 * @param nbrJoueursMin
	 *            the nbrJoueursMin to set
	 */
	public void setNbrJoueursMin(int nbrJoueursMin) {
		this.nbrJoueursMin = nbrJoueursMin;
	}

	/**
	 * @return the nbrJoueursMax
	 */
	public int getNbrJoueursMax() {
		return this.nbrJoueursMax;
	}

	/**
	 * @param nbrJoueursMax
	 *            the nbrJoueursMax to set
	 */
	public void setNbrJoueursMax(int nbrJoueursMax) {
		this.nbrJoueursMax = nbrJoueursMax;
	}

	/**
	 * @return the lstParties
	 */
	public Map<TypeEtatPartie, Map<Integer, Partie>> getLstParties() {
		return this.lstParties;
	}

	/**
	 * @param lstParties
	 *            the lstParties to set
	 */
	public void setLstParties(Map<TypeEtatPartie, Map<Integer, Partie>> lstParties) {
		this.lstParties = lstParties;
	}

	/**
	 * @return the type
	 */
	public TypeJeu getType() {
		return this.type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(TypeJeu type) {
		this.type = type;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Jeu) {
			Jeu other = (Jeu) o;
			return (other.id == this.id);
		}

		return false;
	}

	@Override
	public int hashCode() {
		return this.id;
	}

}