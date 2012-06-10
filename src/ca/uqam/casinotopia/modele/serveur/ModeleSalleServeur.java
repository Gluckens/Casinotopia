package ca.uqam.casinotopia.modele.serveur;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import ca.uqam.casinotopia.Clavardage;
import ca.uqam.casinotopia.Jeu;
import ca.uqam.casinotopia.JeuClient;
import ca.uqam.casinotopia.modele.Modele;

public class ModeleSalleServeur implements Modele {
	
	private String nom;
	private Map<Integer, Jeu> lstJeux;
	//private Map<Integer, ModeleClientServeur> lstClients;
	private Set<ModeleClientServeur> lstClients;
	private Clavardage clavardage;
	
	public ModeleSalleServeur(String nom) {
		this(nom, new HashMap<Integer, Jeu>());
	}
	
	public ModeleSalleServeur(String nom, Map<Integer, Jeu> lstJeux) {
		this(nom, lstJeux, new HashSet<ModeleClientServeur>(), new Clavardage("Chat salle " + nom));
	}
	
	public ModeleSalleServeur(String nom, Map<Integer, Jeu> lstJeux, Set<ModeleClientServeur> lstClients, Clavardage clavardage) {
		this.nom = nom;
		this.lstJeux = lstJeux;
		this.lstClients = lstClients;
		this.clavardage = clavardage;
	}
	
	public String getNom() {
		return this.nom;
	}
	
	public void ajouterClient(ModeleClientServeur client) {
		this.lstClients.add(client);
	}
	
	//TODO Ou bedon plutot déconnecter?
	public void retirerClient(ModeleClientServeur client) {
		this.lstClients.remove(client);
	}
	
	public Set<ModeleClientServeur> getLstClients() {
		return this.lstClients;
	}
	
	public void ajouterJeu(Jeu jeu) {
		this.lstJeux.put(jeu.getId(), jeu);
	}
	
	public void retirerJeu(Jeu jeu) {
		this.retirerJeu(jeu.getId());
	}
	
	public void retirerJeu(int idJeu) {
		this.lstJeux.remove(idJeu);
	}
	
	public Jeu getJeu(int idJeu) {
		return this.lstJeux.get(idJeu);
	}
	
	public Map<Integer, Jeu> getLstJeux() {
		return this.lstJeux;
	}
	
	/*public void ajouterClient(ModeleClientServeur client) {
		this.lstClients.put(client.getId(), client);
	}
	
	//TODO Ou bedon plutot déconnecter?
	public void retirerClient(ModeleClientServeur client) {
		this.retirerClient(client.getId());
	}
	
	public void retirerClient(int idClient) {
		this.lstClients.remove(idClient);
	}
	
	public Map<Integer, ModeleClientServeur> getLstClients() {
		return this.lstClients;
	}*/
}
