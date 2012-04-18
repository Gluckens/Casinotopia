package ca.uqam.casinotopia.controleur;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import ca.uqam.casinotopia.commande.Commande;
import ca.uqam.casinotopia.connexion.Connexion;
import ca.uqam.casinotopia.modele.Modele;
import ca.uqam.casinotopia.vue.Vue;

public abstract class Controleur {
	
	//Possiblement que chaque controleur aurait une seule vue... qui contiendrais plusieurs panel donc ce serait a la vue de gérer la mise a jour de certaine sections...
	protected Map<String, Modele> lstModeles = new HashMap<String, Modele>();
	protected Map<String, Vue> lstVues = new HashMap<String, Vue>();

	protected Connexion connexion;
	
	public Controleur() {
		connexion = new Connexion();
	}
	
	public Controleur(Connexion connexion) {
		this.connexion = connexion;
	}

	/**
	 * @return the connexion
	 */
	public Connexion getConnexion() {
		return this.connexion;
	}

	/**
	 * @param connexion the connexion to set
	 */
	public void setConnexion(Connexion connexion) {
		this.connexion = connexion;
	}
	
	public Modele getModele(String nom) {
		return lstModeles.get(nom);
	}
	
	
	public void ajouterModele(Modele modele) {
		this.lstModeles.put(modele.getClass().getSimpleName(), modele);
	}
	
	public void retirerModele(Modele modele) {
		this.lstModeles.remove(modele.getClass().getSimpleName());
	}
	
	public void ajouterVue(Vue vue) {
		this.lstVues.put(vue.getClass().getSimpleName(), vue);
	}
	
	public void retirerVue(Vue vue) {
		this.lstVues.remove(vue.getClass().getSimpleName());
	}
	
}
