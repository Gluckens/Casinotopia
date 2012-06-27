package ca.uqam.casinotopia.objet;

import java.awt.Rectangle;
import java.io.Serializable;

import ca.uqam.casinotopia.type.TypeJeu;

public class JeuClient implements Serializable {
	
	private static final long serialVersionUID = -873683798526516175L;
	
	private int id;
	private int idSalle;
	private String nom;
	private String description;
	private String reglesJeu;
	private Rectangle emplacement;
	private int nbrJoueursMin;
	private int nbrJoueursMax;

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
	 * @return the idSalle
	 */
	public int getIdSalle() {
		return this.idSalle;
	}

	/**
	 * @param idSalle
	 *            the id to set
	 */
	public void setIdSalle(int idSalle) {
		this.idSalle = idSalle;
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
	
	public Rectangle getEmplacement(int radius) {
		return new Rectangle(this.emplacement.x - radius, this.emplacement.y - radius, this.emplacement.width + (2*radius), this.emplacement.height + (2*radius));
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