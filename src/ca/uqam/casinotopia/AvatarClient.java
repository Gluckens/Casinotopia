package ca.uqam.casinotopia;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.Serializable;

import ca.uqam.casinotopia.modele.Modele;
import ca.uqam.casinotopia.modele.client.ModeleClientClient;
import ca.uqam.casinotopia.modif.TypeModif;
import ca.uqam.casinotopia.modif.TypeModifAvatar;
import ca.uqam.casinotopia.observateur.BaseObservable;
import ca.uqam.casinotopia.observateur.Observable;
import ca.uqam.casinotopia.observateur.Observateur;

public class AvatarClient implements Modele, Observable, Serializable {
	
	private static final long serialVersionUID = 6908585380935996326L;
	
	//TODO Référence au client nécessaire?
	private ModeleClientClient client;
	private int id;
	private String pathImage;
	private String texte;
	
	private Point position;
	
	private int largeur;
	private int hauteur;
	
	private TypeModifAvatar typeModif;

	private BaseObservable sujet = new BaseObservable(this);
	
	
	public AvatarClient() {
		
	}
	
	public AvatarClient(int id, String pathImage) {
		this(id, pathImage, "");
	}
	
	public AvatarClient(int id, String pathImage, String texte) {
		this(id, pathImage, texte, 40, 40);
	}
	
	public AvatarClient(int id, String pathImage, int largeur, int hauteur) {
		this(id, pathImage, "", largeur, hauteur);
	}
	
	public AvatarClient(int id, String pathImage, String texte, int largeur, int hauteur) {
		this(id, pathImage, "", largeur, hauteur, new Point(0, 0));
	}
	
	public AvatarClient(int id, String pathImage, String texte, int largeur, int hauteur, Point position) {
		this.id = id;
		this.pathImage = pathImage;
		this.texte = texte;
		this.largeur = largeur;
		this.hauteur = hauteur;
		this.setPosition(position);
	}
	
	/*public AvatarClient() {
		
	}
	
	public AvatarClient(ModeleClientClient client, int id, String pathImage) {
		this(client, id, pathImage, 40, 40);
	}
	
	public AvatarClient(ModeleClientClient client, int id, String pathImage, Point position) {
		this(client, id, pathImage, 40, 40, "", position);
	}
	
	public AvatarClient(ModeleClientClient client, int id, String pathImage, int largeur, int hauteur) {
		this(client, id, pathImage, largeur, hauteur, "", new Point(0, 0));
	}
	
	public AvatarClient(ModeleClientClient client, int id, String pathImage, int largeur, int hauteur, String texte, Point position) {
		this.setClient(client);
		this.id = id;
		this.pathImage = pathImage;
		this.largeur = largeur;
		this.hauteur = hauteur;
		this.texte = texte;
		this.position = position;
		//this.setPosition(position);
	}*/

	/**
	 * @return the client
	 */
	public ModeleClientClient getClient() {
		return this.client;
	}

	/**
	 * @param client the client to set
	 */
	public void setClient(ModeleClientClient client) {
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
		this.typeModif = TypeModifAvatar.DEPLACEMENT;
		this.notifierObservateur();
	}
	
	public int getX() {
		return this.position.x;
	}
	
	public void setX(int x) {
		this.position.x = x;
		this.typeModif = TypeModifAvatar.DEPLACEMENT;
		this.notifierObservateur();
	}
	
	public int getY() {
		return this.position.y;
	}
	
	public void setY(int y) {
		this.position.y = y;
		this.typeModif = TypeModifAvatar.DEPLACEMENT;
		this.notifierObservateur();
	}
	
	public int getLargeur() {
		return this.largeur;
	}
	
	public int getHauteur() {
		return this.hauteur;
	}
	
	public Rectangle getBounds() {
		//return new Rectangle(this.position, new Dimension(this.largeur, this.hauteur));
		return this.getBounds(this.position);
	}
	
	public Rectangle getBounds(Point p) {
		return new Rectangle(p, new Dimension(this.largeur, this.hauteur));
	}
	
	@Override
	public void ajouterObservateur(Observateur obs) {
		this.sujet.ajouterObservateur(obs);
	}

	@Override
	public void retirerObservateur(Observateur obs) {
		this.sujet.retirerObservateur(obs);
	}

	@Override
	public boolean estObservePar(Observateur obs) {
		return this.sujet.estObservePar(obs);
	}

	@Override
	public void notifierObservateur() {
		this.sujet.notifierObservateur();
	}

	@Override
	public TypeModifAvatar getTypeModif() {
		return this.typeModif;
	}
}
