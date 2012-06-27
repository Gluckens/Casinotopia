package ca.uqam.casinotopia.modele.serveur;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

import ca.uqam.casinotopia.Jeu;
import ca.uqam.casinotopia.commande.client.salle.CmdAfficherSalle;
import ca.uqam.casinotopia.commande.client.salle.CmdAjouterClientSalle;
import ca.uqam.casinotopia.commande.client.salle.CmdRetirerClientSalle;
import ca.uqam.casinotopia.connexion.Connectable;
import ca.uqam.casinotopia.controleur.serveur.ControleurPrincipalServeur;
import ca.uqam.casinotopia.modele.Modele;
import ca.uqam.casinotopia.modele.client.ModeleClientClient;
import ca.uqam.casinotopia.modele.client.ModeleSalleClient;
import ca.uqam.casinotopia.objet.Clavardage;
import ca.uqam.casinotopia.objet.JeuClient;
import ca.uqam.casinotopia.objet.Utilisateur;

/**
 * Représente une instance de salle
 */
@SuppressWarnings("serial")
public class ModeleSalleServeur implements Modele, Connectable {
	
	/**
	 * Id de la salle
	 */
	private int id;
	
	/**
	 * Nom de la salle
	 */
	private String nom;
	
	/**
	 * Liste de jeux associés à la salle
	 */
	private Map<Integer, Jeu> lstJeux;
	
	/**
	 * Liste de clients dans la salle
	 */
	private Map<Integer, ModeleClientServeur> lstClients;
	
	/**
	 * Clavardage lié à la salle
	 */
	private Clavardage clavardage;
	
	public ModeleSalleServeur(int id, String nom) {
		this(id, nom, new HashMap<Integer, Jeu>());
	}
	
	public ModeleSalleServeur(int id, String nom, Map<Integer, Jeu> lstJeux) {
		this(id, nom, lstJeux, new HashMap<Integer, ModeleClientServeur>(), ControleurPrincipalServeur.INSTANCE.getModele().getChat(nom));
	}
	
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
	
	public Clavardage getClavardage() {
		return this.clavardage;
	}
	
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
	
	/**
	 * Créer la version client du modèle de salle
	 * 
	 * @return La version client du modèle de salle
	 */
	public ModeleSalleClient creerModeleClient() {
		return new ModeleSalleClient(
			this.id,
			this.nom,
			this.creerMapJeuClient(),
			this.creerMapClientClient(),
			this.clavardage
		);
	}
	
	/**
	 * Créer la version client du Map de jeux
	 * 
	 * @return La version client du Map de jeux
	 */
	public Map<Integer, JeuClient> creerMapJeuClient() {
		Map<Integer, JeuClient> mapJeuClient = new HashMap<Integer, JeuClient>();
		for(Map.Entry<Integer, Jeu> entryServeur : this.lstJeux.entrySet()) {
			mapJeuClient.put(entryServeur.getKey(), entryServeur.getValue().creerModeleClient());
		}
		
		return mapJeuClient;
	}
	
	/**
	 * Créer la version client du Map de clients
	 * 
	 * @return La version client du Map de clients
	 */
	public Map<Integer, ModeleClientClient> creerMapClientClient() {
		Map<Integer, ModeleClientClient> mapClientClient = new HashMap<Integer, ModeleClientClient>();
		for(Map.Entry<Integer, ModeleClientServeur> entryServeur : this.lstClients.entrySet()) {
			mapClientClient.put(entryServeur.getKey(), entryServeur.getValue().creerModeleClient());
		}
		
		return mapClientClient;
	}

	@Override
	public void connecter(Utilisateur utilisateur) {
		ModeleClientServeur client = (ModeleClientServeur) utilisateur;
		this.lstClients.put(client.getId(), client);
		utilisateur.ajouterConnectable(this);
		
		client.getConnexion().envoyerCommande(new CmdAfficherSalle(this.creerModeleClient()));
		
		for(ModeleClientServeur autreClient : this.lstClients.values()) {
			if(autreClient.getId() != client.getId()) {
				autreClient.getConnexion().envoyerCommande(new CmdAjouterClientSalle(client.creerModeleClient()));
			}
		}
	}

	@Override
	public void deconnecter(Utilisateur utilisateur) {
		this.clavardage.deconnecter(utilisateur);
		
		ModeleClientServeur client = (ModeleClientServeur) utilisateur;
		
		this.lstClients.remove(client.getId());
		client.getAvatar().setPosition(new Point(0, 0));
		for(ModeleClientServeur autreClient : this.lstClients.values()) {
			autreClient.getConnexion().envoyerCommande(new CmdRetirerClientSalle(client.getId()));
		}
	}
}