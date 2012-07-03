package ca.uqam.casinotopia;

import java.awt.Point;

import ca.uqam.casinotopia.modele.Modele;
import ca.uqam.casinotopia.objet.AvatarClient;

/**
 * Regroupe les informations d'un avatar côté serveur
 */
@SuppressWarnings("serial")
public class Avatar implements Modele {
	
	/**
	 * Id de l'avatar
	 */
	private int id;
	
	/**
	 * Path vers l'image de l'avatar
	 */
	private String pathImage;
	
	/**
	 * Texte de l'avatar
	 */
	private String texte;
	
	/**
	 * Position actuelle de l'avatar
	 */
	private Point position;
	
	/**
	 * Largeur de l'avatar
	 */
	private int largeur;
	
	/**
	 * Hauteur de l'avatar
	 */
	private int hauteur;
	
	public Avatar() {
		
	}
	
	public Avatar(int id, String pathImage) {
		this(id, pathImage, "");
	}
	
	public Avatar(int id, String pathImage, String texte) {
		this(id, pathImage, texte, 40, 40);
	}
	
	public Avatar(int id, String pathImage, int largeur, int hauteur) {
		this(id, pathImage, "", largeur, hauteur);
	}
	
	public Avatar(int id, String pathImage, String texte, int largeur, int hauteur) {
		this(id, pathImage, "", largeur, hauteur, new Point(0, 0));
	}
	
	public Avatar(int id, String pathImage, String texte, int largeur, int hauteur, Point position) {
		this.id = id;
		this.pathImage = pathImage;
		this.texte = texte;
		this.largeur = largeur;
		this.hauteur = hauteur;
		this.setPosition(position);
	}
	
	/**
	 * Récupérer la version client du modele de l'avatar
	 * 
	 * @return La version client d'un avatar
	 */
	public AvatarClient creerModeleClient() {
		return new AvatarClient(this.id, this.pathImage, this.texte, this.largeur, this.hauteur, this.position);
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPathImage() {
		return this.pathImage;
	}

	public void setPathImage(String pathImage) {
		this.pathImage = pathImage;
	}

	public String getTexte() {
		return this.texte;
	}

	public void setTexte(String texte) {
		this.texte = texte;
	}

	public Point getPosition() {
		return this.position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}
	
	public int getX() {
		return this.position.x;
	}
	
	public void setX(int x) {
		this.position.x = x;
	}
	
	public int getY() {
		return this.position.y;
	}
	
	public void setY(int y) {
		this.position.y = y;
	}
	
	public int getLargeur() {
		return this.largeur;
	}
	
	public int getHauteur() {
		return this.hauteur;
	}
}