package ca.uqam.casinotopia;


import ca.uqam.casinotopia.connexion.Connexion;

public class Utilisateur{
	
	private int idUtilisateur;
	private String nomUtilisateur;
	private String motDePasse;
	
	private Connexion connexion;
	
	public Utilisateur() {
		this("", "", null);
	}
	
	public Utilisateur(String nomUtilisateur, Connexion connexion) {
		this(nomUtilisateur, "", connexion);
	}
	
	public Utilisateur(String nomUtilisateur, String motDePasse, Connexion connexion) {
		this.nomUtilisateur = nomUtilisateur;
		this.connexion = connexion;
	}

	/**
	 * @return the nomUtilisateur
	 */
	public String getNomUtilisateur() {
		return nomUtilisateur;
	}
	
	/**
	 * @param nomUtilisateur the nomUtilisateur to set
	 */
	public void setNomUtilisateur(String nomUtilisateur) {
		this.nomUtilisateur = nomUtilisateur;
	}
	
	/**
	 * @return the motDePasse
	 */
	public String getMotDePasse() {
		return motDePasse;
	}

	/**
	 * @param motDePasse the motDePasse to set
	 */
	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	/**
	 * @return the idUtilisateur
	 */
	public int getIdUtilisateur() {
		return idUtilisateur;
	}

	/**
	 * @param idUtilisateur the idUtilisateur to set
	 */
	public void setIdUtilisateur(int idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

	public Connexion getConnexion() {
		return connexion;
	}
	
}
