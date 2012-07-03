package ca.uqam.casinotopia.objet;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ca.uqam.casinotopia.connexion.Connectable;
import ca.uqam.casinotopia.connexion.Connexion;

/**
 * Classe abstraite regroupant les informations d'un utilisateur
 */
public abstract class Utilisateur implements Serializable {
	
	private static final long serialVersionUID = 2224574184399249175L;
	
	protected int idUtilisateur;
	protected String nomUtilisateur;
	protected String motDePasse;

	/**
	 * liste d'object avec lequel l'utilisateur est connecté
	 */
	protected List<Connectable> connectables = new ArrayList<Connectable>();

	/**
	 * connexion de l'utilisateur
	 */
	private transient Connexion connexion;
	
	public Utilisateur(int idUtilisateur, String nomUtilisateur) {
		this(idUtilisateur, nomUtilisateur, "");
	}
	
	public Utilisateur(int idUtilisateur, String nomUtilisateur, String motDePasse) {
		this(idUtilisateur, nomUtilisateur, motDePasse, null);
	}
	
	public Utilisateur(int idUtilisateur, String nomUtilisateur, Connexion connexion) {
		this(idUtilisateur, nomUtilisateur, "", connexion);
	}
	
	public Utilisateur(int idUtilisateur, String nomUtilisateur, String motDePasse, Connexion connexion) {
		this.idUtilisateur = idUtilisateur;
		this.nomUtilisateur = nomUtilisateur;
		this.motDePasse = motDePasse;
		this.connexion = connexion;
	}

	public Utilisateur(String nomUtilisateur, Connexion connexion) {
		this(nomUtilisateur, "", connexion);
	}

	public Utilisateur(String nomUtilisateur, String motDePasse, Connexion connexion) {
		this.nomUtilisateur = nomUtilisateur;
		this.motDePasse = motDePasse;
		this.connexion = connexion;
	}
	
	public void ajouterConnectable(Connectable connectable) {
		this.connectables.add(connectable);
	}

	public String getNomUtilisateur() {
		return this.nomUtilisateur;
	}

	public void setNomUtilisateur(String nomUtilisateur) {
		this.nomUtilisateur = nomUtilisateur;
	}

	public String getMotDePasse() {
		return this.motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public int getIdUtilisateur() {
		return this.idUtilisateur;
	}

	public void setIdUtilisateur(int idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

	public Connexion getConnexion() {
		return this.connexion;
	}

	public void setConnexion(Connexion connexion) {
		this.connexion = connexion;
	}

	public List<Connectable> getConnectables() {
		return this.connectables;
	}

	public void deconnecter() {
		for(Connectable conn : this.connectables) {
			conn.deconnecter(this);
		}
	}
}