package ca.uqam.casinotopia;

import java.awt.Point;

import ca.uqam.casinotopia.modele.Modele;
import ca.uqam.casinotopia.modele.serveur.ModeleClientServeur;

public class Avatar implements Modele {
	
	//TODO Référence au client nécessaire?
	private ModeleClientServeur client;
	private int id;
	private String pathImage;
	private String texte;
	
	private Point position;
	
	private int largeur;
	private int hauteur;
	
	public Avatar() {
		
	}
	
	public Avatar(ModeleClientServeur client, int id, String pathImage) {
		this(client, id, pathImage, 40, 40);
	}
	
	public Avatar(ModeleClientServeur client, int id, String pathImage, int largeur, int hauteur) {
		this(client, id, pathImage, largeur, hauteur, "", new Point(0, 0));
	}
	
	public Avatar(ModeleClientServeur client, int id, String pathImage, int largeur, int hauteur, String texte, Point position) {
		this.setClient(client);
		this.id = id;
		this.pathImage = pathImage;
		this.largeur = largeur;
		this.hauteur = hauteur;
		this.texte = texte;
		this.setPosition(position);
	}

	/**
	 * @return the client
	 */
	public ModeleClientServeur getClient() {
		return this.client;
	}

	/**
	 * @param client the client to set
	 */
	public void setClient(ModeleClientServeur client) {
		this.client = client;
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
