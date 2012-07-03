package ca.uqam.casinotopia.objet;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.Serializable;

import ca.uqam.casinotopia.modele.Modele;
import ca.uqam.casinotopia.modele.client.ModeleClientClient;
import ca.uqam.casinotopia.observateur.BaseObservable;
import ca.uqam.casinotopia.observateur.Observable;
import ca.uqam.casinotopia.observateur.Observateur;
import ca.uqam.casinotopia.type.modif.TypeModifAvatar;

/**
 * Regroupe les informations d'un avatar côté client
 */
public class AvatarClient implements Modele, Observable, Serializable {
	
	private static final long serialVersionUID = -1566253627412224326L;
	
	/**
	 * Client associé à l'avatar
	 */
	private ModeleClientClient client;
	
	/**
	 * Id du client
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
	
	/**
	 * Type de modif effectué (pour les observateurs)
	 */
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

	public ModeleClientClient getClient() {
		return this.client;
	}

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

	public Point getPosition() {
		return this.position;
	}

	/**
	 * Définir la position de l'avatar et notifier les observateurs du déplacement.
	 * @param position La nouvelle position
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
	
	/**
	 * Récupérer le rectange de l'avatar à sa position actuelle
	 * 
	 * @return Le rectangle de l'avatar en fonction de sa position actuelle
	 */
	public Rectangle getBounds() {
		return this.getBounds(this.position);
	}
	
	/**
	 * Récupérer le rectange de l'avatar à une position donnée
	 * 
	 * @param p La position de l'avatar pour le calcul du rectangle
	 * @return Le rectangle de l'avatar en fonction de la position en paramètre
	 */
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