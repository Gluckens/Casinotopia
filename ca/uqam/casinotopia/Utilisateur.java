package ca.uqam.casinotopia;

import java.util.ArrayList;
import java.util.List;

import ca.uqam.casinotopia.connexion.Connectable;
import ca.uqam.casinotopia.connexion.Connexion;

public class Utilisateur{
	
	private int id;
	private String nomUtilisateur;
	private String motDePasse;
	
	List<Connectable> connectables = new ArrayList<Connectable>();
	
	private Connexion connexion;

	private int id;
	
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
		return this.id;
	}

	/**
	 * @param idUtilisateur the idUtilisateur to set
	 */
	public void setIdUtilisateur(int id) {
		this.id = id;
	}

	public Connexion getConnexion() {
		return connexion;
	}
	
	
	public List<Connectable> getConnectables() {
		return connectables;
	}
	
	public void deconnect(){
		for (int i = 0; i < connectables.size(); i++) {
			if(connectables.get(i) instanceof Clavardage){
				connectables.get(i).deconnect(this);
			}
		}
	}
	
}
