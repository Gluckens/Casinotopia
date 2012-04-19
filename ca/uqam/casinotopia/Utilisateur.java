package ca.uqam.casinotopia;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ca.uqam.casinotopia.connexion.Connectable;
import ca.uqam.casinotopia.connexion.Connexion;

public class Utilisateur{
	
	
	List<Connectable> connectables = new ArrayList<Connectable>();
	
	private String nomUtilisateur = null;
	
	private Connexion connexion;

	private int id;
	
	public Utilisateur() {
		
	}
	
	public Utilisateur(String nomUtilisateur, Connexion connexion) {
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
	
	public Connexion getConnexion() {
		return connexion;
	}

	public int getIdUtilisateur(){
		return this.id;
	}

	public void setIdUtilisateur(int id){
		this.id = id;
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
