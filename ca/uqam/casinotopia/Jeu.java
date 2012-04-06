package ca.uqam.casinotopia;
import java.util.Vector;

public class Jeu {
	private String nom;
	private String description;
	private String reglesJeu;
	private int posX;
	private int posY;
	private int nbJoueursMin;
	private int nbJoueursMax;
	private Salle salle;
	private Vector<Partie> listeParties = new Vector<Partie>();
}