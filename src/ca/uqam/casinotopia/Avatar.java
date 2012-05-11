package ca.uqam.casinotopia;

import java.awt.Point;

import ca.uqam.casinotopia.modele.Modele;

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
		this(id, pathImage, 40, 40);
	}
	
	public Avatar(int id, String pathImage, int largeur, int hauteur) {
		this(id, pathImage, largeur, hauteur, "", new Point(0, 0));
	}
	
	public Avatar(int id, String pathImage, int largeur, int hauteur, String texte, Point position) {
		this.id = id;
		this.pathImage = pathImage;
		this.largeur = largeur;
		this.hauteur = hauteur;
		this.texte = texte;
		this.setPosition(position);
	}

	public int getId() {
		return this.id;
	}

	public String getPathImage() {
		return this.pathImage;
	}

	public String getTexte() {
		return this.texte;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setPathImage(String pathImage) {
		this.pathImage = pathImage;
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
