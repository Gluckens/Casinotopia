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
	//Autrement je vois pas comment faire pour retrouver un jpanel en particulier...
	//À part de faire une recherche dans le contentPane du FrameApplication, et avec les setName des panel, retrouver le bon et le remplacer... possible solution.
	protected Map<String, Modele> lstModeles = new HashMap<String, Modele>();
	protected Map<String, Vue> lstVues = new HashMap<String, Vue>();

	protected Connexion connexion;
	
	public Controleur() {
		this.connexion = new Connexion();
	}
	
	public Controleur(Connexion connexion) {
		this.connexion = connexion;
	}

	/**
	 * @return the connexion
	 */
	public Connexion getConnexion() {
		return connexion;
	}

	/**
	 * @param connexion the connexion to set
	 */
	public void setConnexion(Connexion connexion) {
		this.connexion = connexion;
	}
	
	public void envoyerCommande(Commande cmd) {
		try {
			this.connexion.getObjectOutputStream().writeObject(cmd);
			this.connexion.getObjectOutputStream().reset();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Modele getModele(String nom) {
		return lstModeles.get(nom);
	}
	
	
	public void ajouterModele(Modele modele) {
		System.out.println(modele.getClass().getSimpleName());
		this.lstModeles.put(modele.getClass().getSimpleName(), modele);
	}
	
	public void retirerModele(Modele modele) {
		System.out.println(modele.getClass().getSimpleName());
		this.lstModeles.remove(modele.getClass().getSimpleName());
	}
	
	public void ajouterVue(Vue vue) {
		System.out.println(vue.getClass().getSimpleName());
		this.lstVues.put(vue.getClass().getSimpleName(), vue);
	}
	
	public void retirerVue(Vue vue) {
		System.out.println(vue.getClass().getSimpleName());
		this.lstVues.remove(vue.getClass().getSimpleName());
	}
	
}
