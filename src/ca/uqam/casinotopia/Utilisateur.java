package ca.uqam.casinotopia;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ca.uqam.casinotopia.connexion.Connectable;
import ca.uqam.casinotopia.connexion.Connexion;

public /*abstract*/ class Utilisateur implements Serializable {
	
	private static final long serialVersionUID = 1967013417447817664L;
	
	protected int idUtilisateur;
	protected String nomUtilisateur;
	protected String motDePasse;
	//public transient int number = 0;

	List<Connectable> connectables = new ArrayList<Connectable>();

	//Les sockets ne sont pas serializable
	private transient Connexion connexion;

	public Utilisateur() {
		this("", "", null);
	}
	
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

	/**
	 * @return the nomUtilisateur
	 */
	public String getNomUtilisateur() {
		return this.nomUtilisateur;
	}

	/**
	 * @param nomUtilisateur
	 *            the nomUtilisateur to set
	 */
	public void setNomUtilisateur(String nomUtilisateur) {
		this.nomUtilisateur = nomUtilisateur;
	}

	/**
	 * @return the motDePasse
	 */
	public String getMotDePasse() {
		return this.motDePasse;
	}

	/**
	 * @param motDePasse
	 *            the motDePasse to set
	 */
	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	/**
	 * @return the idUtilisateur
	 */
	public int getIdUtilisateur() {
		return this.idUtilisateur;
	}

	/**
	 * @param idUtilisateur
	 *            the idUtilisateur to set
	 */
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
		for (int i = 0; i < this.connectables.size(); i++) {
			if (this.connectables.get(i) instanceof Clavardage) {
				this.connectables.get(i).deconnecter(this);
			}
		}
	}

}
