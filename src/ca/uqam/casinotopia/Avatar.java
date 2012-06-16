package ca.uqam.casinotopia;

import java.awt.Point;

import ca.uqam.casinotopia.modele.Modele;
import ca.uqam.casinotopia.objet.AvatarClient;

@SuppressWarnings("serial")
public class Avatar implements Modele {
	
	private int id;
	private String pathImage;
	private String texte;
	
	private Point position;
	
	private int largeur;
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

	/**
	 * @return the position
	 */
	public Point getPosition() {
		return this.position;
	}

	/**
	 * @param position the position to set
	 */
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
