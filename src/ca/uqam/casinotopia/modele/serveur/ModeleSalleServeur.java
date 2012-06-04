package ca.uqam.casinotopia.modele.serveur;

import java.util.HashMap;
import java.util.Map;

import ca.uqam.casinotopia.Clavardage;
import ca.uqam.casinotopia.Jeu;
import ca.uqam.casinotopia.JeuClient;
import ca.uqam.casinotopia.modele.Modele;
import ca.uqam.casinotopia.modele.client.ModeleClientClient;
import ca.uqam.casinotopia.modele.client.ModeleSalleClient;

public class ModeleSalleServeur implements Modele {
	
	//TODO Nécessaire de mettre un id?
	private int id;
	private String nom;
	private Map<Integer, Jeu> lstJeux;
	private Map<Integer, ModeleClientServeur> lstClients;
	//private Set<ModeleClientServeur> lstClients;
	private Clavardage clavardage;
	
	public ModeleSalleServeur(int id, String nom) {
		this(id, nom, new HashMap<Integer, Jeu>());
	}
	
	public ModeleSalleServeur(int id, String nom, Map<Integer, Jeu> lstJeux) {
		//this(id, nom, lstJeux, new HashSet<ModeleClientServeur>(), new Clavardage("Chat salle " + nom));
		this(id, nom, lstJeux, new HashMap<Integer, ModeleClientServeur>(), new Clavardage("Chat salle " + nom));
	}
	
	//public ModeleSalleServeur(int id, String nom, Map<Integer, Jeu> lstJeux, Set<ModeleClientServeur> lstClients, Clavardage clavardage) {
	public ModeleSalleServeur(int id, String nom, Map<Integer, Jeu> lstJeux, Map<Integer, ModeleClientServeur> lstClients, Clavardage clavardage) {
		this.id = id;
		this.nom = nom;
		this.lstJeux = lstJeux;
		this.lstClients = lstClients;
		this.clavardage = clavardage;
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNom() {
		return this.nom;
	}
	
	public void ajouterClient(ModeleClientServeur client) {
		//this.lstClients.add(client);
		this.lstClients.put(client.getId(), client);
	}
	
	//TODO Ou bedon plutot déconnecter?
	public void retirerClient(ModeleClientServeur client) {
		this.lstClients.remove(client);
	}
	
	//public Set<ModeleClientServeur> getLstClients() {
	public Map<Integer, ModeleClientServeur> getLstClients() {
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
	
	public ModeleSalleClient creerModeleClient() {
		return new ModeleSalleClient(
			this.id,
			this.nom,
			this.creerMapJeuClient(),
			this.creerMapClientClient(),
			this.clavardage
		);
	}
	
	public Map<Integer, JeuClient> creerMapJeuClient() {
		Map<Integer, JeuClient> mapJeuClient = new HashMap<Integer, JeuClient>();
		for(Map.Entry<Integer, Jeu> entryServeur : this.lstJeux.entrySet()) {
			mapJeuClient.put(entryServeur.getKey(), entryServeur.getValue().creerModeleClient());
		}
		
		return mapJeuClient;
	}
	
	public Map<Integer, ModeleClientClient> creerMapClientClient() {
		Map<Integer, ModeleClientClient> mapClientClient = new HashMap<Integer, ModeleClientClient>();
		for(Map.Entry<Integer, ModeleClientServeur> entryServeur : this.lstClients.entrySet()) {
			mapClientClient.put(entryServeur.getKey(), entryServeur.getValue().creerModeleClient());
		}
		
		return mapClientClient;
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
