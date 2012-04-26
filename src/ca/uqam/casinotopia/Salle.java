package ca.uqam.casinotopia;

import java.io.Serializable;
import java.util.Vector;

public class Salle implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6466675203663195872L;
	private String nom;
	private Vector<Client> listeClients = new Vector<Client>();
	private Clavardage clavardage;
	private Vector<Jeu> listeJeu = new Vector<Jeu>();
}