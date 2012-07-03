package ca.uqam.casinotopia.objet;

import java.awt.Rectangle;
import java.io.Serializable;

import ca.uqam.casinotopia.type.TypeJeu;

/**
 * Regroupe les informations d'un jeu côté client
 */
public class JeuClient implements Serializable {
	
	private static final long serialVersionUID = -873683798526516175L;
	
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
	 * Nombre de joueurs minimale dans une partie
	 */
	private int nbrJoueursMin;
	
	/**
	 * Nombde de joueurs maximale dans une partie
	 */
	private int nbrJoueursMax;

	/**
	 * Type de jeu
	 */
	private TypeJeu type;

	public JeuClient(int id, int idSalle, String nom, String description, String reglesJeu, Rectangle emplacement, int nbrJoueursMin, int nbrJoueursMax, TypeJeu type) {
		this.id = id;
		this.idSalle = idSalle;
		this.nom = nom;
		this.description = description;
		this.reglesJeu = reglesJeu;
		this.emplacement = emplacement;
		this.nbrJoueursMin = nbrJoueursMin;
		this.nbrJoueursMax = nbrJoueursMax;

		this.type = type;
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
	
	/**
	 * Récupérer le rectangle contenant le jeu dans la salle, en y ajoutant un radius autour
	 * 
	 * @param radius Le radius à ajouter
	 * @return Le rectangle contenant le jeu en tenant compte du radius
	 */
	public Rectangle getEmplacement(int radius) {
		return new Rectangle(this.emplacement.x - radius, this.emplacement.y - radius, this.emplacement.width + (2*radius), this.emplacement.height + (2*radius));
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

	public TypeJeu getType() {
		return this.type;
	}

	public void setType(TypeJeu type) {
		this.type = type;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof JeuClient) {
			JeuClient other = (JeuClient) o;
			return (other.id == this.id);
		}

		return false;
	}

	@Override
	public int hashCode() {
		return this.id;
	}
}