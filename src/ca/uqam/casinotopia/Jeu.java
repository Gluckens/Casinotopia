package ca.uqam.casinotopia;

import java.awt.Rectangle;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.Map.Entry;

import ca.uqam.casinotopia.controleur.serveur.ControleurPrincipalServeur;
import ca.uqam.casinotopia.objet.JeuClient;
import ca.uqam.casinotopia.type.TypeEtatPartie;
import ca.uqam.casinotopia.type.TypeJeu;
import ca.uqam.casinotopia.type.TypeJeuArgent;

/**
 * Regroupe les informations d'un jeu côté serveur
 */
public class Jeu {
	
	/**
	 * Id du jeu
	 */
	private int id;
	
	/**
	 * Id de la salle contenant le jeu
	 */
	private int idSalle;
	
	/**
	 * Nom du jeu
	 */
	private String nom;
	
	/**
	 * Description du jeu
	 */
	private String description;
	
	/**
	 * Règles du jeu en texte
	 */
	private String reglesJeu;
	
	/**
	 * Emplacement du jeu dans la salle
	 */
	private Rectangle emplacement;
	
	/**
	 * Nombde de joueurs minimale dans une partie
	 */
	private int nbrJoueursMin;
	
	/**
	 * Nombde de joueurs maximale dans une partie
	 */
	private int nbrJoueursMax;

	/**
	 * Liste des parties actives du jeu
	 */
	private Map<TypeEtatPartie, Map<Integer, Partie>> lstParties;

	/**
	 * Type de jeu
	 */
	private TypeJeu type;

	public Jeu(int id, int idSalle, String nom, String description, String reglesJeu, Rectangle emplacement, int nbrJoueursMin, int nbrJoueursMax, TypeJeu type) {
		this.id = id;
		this.idSalle = idSalle;
		this.nom = nom;
		this.description = description;
		this.reglesJeu = reglesJeu;
		this.emplacement = emplacement;
		this.nbrJoueursMin = nbrJoueursMin;
		this.nbrJoueursMax = nbrJoueursMax;

		this.type = type;

		this.initLstParties();
	}

	/**
	 * Initialiser les listes de parties
	 */
	private void initLstParties() {
		this.lstParties = new HashMap<TypeEtatPartie, Map<Integer, Partie>>();
		this.lstParties.put(TypeEtatPartie.EN_ATTENTE, new HashMap<Integer, Partie>());
		this.lstParties.put(TypeEtatPartie.EN_COURS, new HashMap<Integer, Partie>());
	}

	/**
	 * Récupérer une partie par son id.
	 * Recherche parmi les parties en attente et en cours
	 * 
	 * @param idPartie L'id de la partie recherchée
	 * @return La partie demandée
	 */
	public Partie getPartie(int idPartie) {
		Partie partie = this.lstParties.get(TypeEtatPartie.EN_ATTENTE).get(idPartie);
		if (partie == null) {
			partie = this.lstParties.get(TypeEtatPartie.EN_COURS).get(idPartie);
		}
		return partie;
	}

	/**
	 * Récupérer une partie par son instance.
	 * Recherche parmi les parties en attente et en cours.
	 * Retourne l'instance globale de la partie
	 * 
	 * @param partie L'instance de la partie recherchée
	 * @return La partie demandée
	 */
	public Partie getPartie(Partie partie) {
		return this.getPartie(partie.getId());
	}

	/**
	 * Récupérer le mapping d'une partie par son id
	 * 
	 * @param idPartie L'id de la partie demandée
	 * @return Le mapping de la partie
	 */
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

	/**
	 * Ajouter une partie à la liste appropriée (en attent ou en cours)
	 * 
	 * @param partie La partie à ajouter
	 * @param etat L'état de la partie (en attente ou en cours)
	 */
	public void ajouterPartie(Partie partie, TypeEtatPartie etat) {
		this.lstParties.get(etat).put(partie.getId(), partie);
	}

	/**
	 * Rechercher une partie en attente selon les paramètres défini
	 * Recherche la partie étant le plus proche de son masimum de joueurs, de sorte à comblé
	 * les parties au maximum et d'éviter d'avoir plusieurs parties avec peu de joueurs
	 * 
	 * @param typeArgent Le type de jeu d'argent
	 * @return La partie trouvé selon les paramètres, null si aucune 
	 */
	public Partie rechercherPartieEnAttente(TypeJeuArgent typeArgent) {
		Partie partieEnAttente = null;

		if (!this.lstParties.get(TypeEtatPartie.EN_ATTENTE).isEmpty()) {
			SortedSet<Entry<Integer, Partie>> lstPartiesSorted = ControleurPrincipalServeur.entriesSortedByValues(this.lstParties.get(TypeEtatPartie.EN_ATTENTE));
			
			for(Map.Entry<Integer, Partie> entry : lstPartiesSorted) {
				if(entry.getValue().getTypeArgent() == typeArgent) {
					partieEnAttente = entry.getValue();
					break;
				}
			}
		}

		return partieEnAttente;
	}
	
	/**
	 * Créer la version client du moèdle de jeu
	 * 
	 * @return La version client du modèle
	 */
	public JeuClient creerModeleClient() {
		return new JeuClient(
			this.id,
			this.idSalle,
			this.nom,
			this.description,
			this.reglesJeu,
			this.emplacement,
			this.nbrJoueursMin,
			this.nbrJoueursMax,
			this.type
		);
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getIdSalle() {
		return this.idSalle;
	}

	public void setIdSalle(int idSalle) {
		this.idSalle = idSalle;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getReglesJeu() {
		return this.reglesJeu;
	}

	public void setReglesJeu(String reglesJeu) {
		this.reglesJeu = reglesJeu;
	}

	public Rectangle getEmplacement() {
		return emplacement;
	}

	public void setEmplacement(Rectangle emplacement) {
		this.emplacement = emplacement;
	}

	public int getNbrJoueursMin() {
		return this.nbrJoueursMin;
	}

	public void setNbrJoueursMin(int nbrJoueursMin) {
		this.nbrJoueursMin = nbrJoueursMin;
	}

	public int getNbrJoueursMax() {
		return this.nbrJoueursMax;
	}

	public void setNbrJoueursMax(int nbrJoueursMax) {
		this.nbrJoueursMax = nbrJoueursMax;
	}

	public Map<TypeEtatPartie, Map<Integer, Partie>> getLstParties() {
		return this.lstParties;
	}

	public void setLstParties(Map<TypeEtatPartie, Map<Integer, Partie>> lstParties) {
		this.lstParties = lstParties;
	}

	public TypeJeu getType() {
		return this.type;
	}

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