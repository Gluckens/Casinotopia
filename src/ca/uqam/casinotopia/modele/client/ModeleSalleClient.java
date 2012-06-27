package ca.uqam.casinotopia.modele.client;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

import ca.uqam.casinotopia.modele.Modele;
import ca.uqam.casinotopia.objet.AvatarClient;
import ca.uqam.casinotopia.objet.Clavardage;
import ca.uqam.casinotopia.objet.JeuClient;
import ca.uqam.casinotopia.observateur.BaseObservable;
import ca.uqam.casinotopia.observateur.Observable;
import ca.uqam.casinotopia.observateur.Observateur;
import ca.uqam.casinotopia.type.modif.TypeModifSalle;

/**
 * Représente une instance de salle
 */
public class ModeleSalleClient implements Modele, Observable {
	
	private static final long serialVersionUID = -6479440113594316065L;

	/**
	 * Id de la salle
	 */
	private int id;
	
	/**
	 * Nom de la salle
	 */
	@SuppressWarnings("unused")
	private String nom;
	
	/**
	 * Liste de jeux associés à la salle
	 */
	private Map<Integer, JeuClient> lstJeux;
	
	/**
	 * Liste de clients dans la salle
	 */
	private Map<Integer, ModeleClientClient> lstClients;
	
	/**
	 * Clavardage lié à la salle
	 */
	@SuppressWarnings("unused")
	private Clavardage clavardage;
	
	/**
	 * Gestiond es ajout/retrait de client dans la salle
	 */
	//TODO trouver une meilleure méthode...
	private ModeleClientClient dernierClient;
	private ModeleClientClient clientRetire;
	
	/**
	 * Type de modification effectué sur le modèle.
	 * Ceci sera lu par l'observateur pour savoir quelle fonction appeler
	 */
	private TypeModifSalle typeModif;

	/**
	 * Délégation des fonctions de l'interface observable à l'objet BaseObservable
	 */
	private BaseObservable sujet = new BaseObservable(this);
	
	public ModeleSalleClient(int id, String nom) {
		this(id, nom, new HashMap<Integer, JeuClient>());
	}
	
	public ModeleSalleClient(int id, String nom, Map<Integer, JeuClient> lstJeux) {
		this(id, nom, lstJeux, new HashMap<Integer, ModeleClientClient>(), new Clavardage("Chat salle " + nom));
	}
	
	public ModeleSalleClient(int id, String nom, Map<Integer, JeuClient> lstJeux, Map<Integer, ModeleClientClient> lstClients, Clavardage clavardage) {
		this.id = id;
		this.nom = nom;
		this.lstJeux = lstJeux;
		this.lstClients = lstClients;
		this.clavardage = clavardage;
	}
	
	/**
	 * Valider un déplacement d'avatar selon les bornes des tables de jeux
	 * 
	 * @param avatar L'avatar qui tente de se déplacer
	 * @return True si le déplacement est valide, false sinon.
	 */
	public boolean validerDeplacement(AvatarClient avatar) {
		for(JeuClient jeu : this.lstJeux.values()) {
			if(jeu.getEmplacement().intersects(avatar.getBounds())) {
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Valider un déplacement d'avatar selon les bornes des tables de jeux
	 * 
	 * @param avatar L'avatar qui tente de se déplacer
	 * @param position La nouvelle position demandée pour l'avatar
	 * @return True si le déplacement est valide, false sinon.
	 */
	public boolean validerDeplacement(AvatarClient avatar, Point position) {
		for(JeuClient jeu : this.lstJeux.values()) {
			if(jeu.getEmplacement().intersects(avatar.getBounds(position))) {
				return false;
			}
		}
		
		//TODO Gérer les collisions inter-clients?
		/*for(ModeleClientClient client : this.getLstClients().values()) {
			if(client.getAvatar().getId() != avatar.getId() && client.getAvatar().getBounds().intersects(avatar.getBounds(position))) {
				return false;
			}
		}*/
		
		return true;
	}
	
	/**
	 * Valider si une position n'entre pas en conflit avec les bornes des tables de jeux
	 * 
	 * @param position La position à valider
	 * @returnTrue si la position est valide, false sinon
	 */
	public boolean validerDeplacement(Point position) {
		for(JeuClient jeu : this.lstJeux.values()) {
			if(jeu.getEmplacement().contains(position)) {
				return false;
			}
		}
		
		return true;
	}

	/**
	 * Détermine si le déplacement d'un avatar le fera entrer dans la zone de proximité d'un jeu, et ainsi faire apparaitre ses options.
	 * 
	 * @param avatar L'avatar qui veut se déplacer
	 * @param position La nouvelle position de l'avatar
	 * @return L'instance d'un jeu si l'avatar entre dans sa zone de proximitié, null sinon
	 */
	public JeuClient checkProximites(AvatarClient avatar, Point position) {
		for(JeuClient jeu : this.lstJeux.values()) {
			if(jeu.getEmplacement(20).intersects(avatar.getBounds(position))) {
				return jeu;
			}
		}
		
		return null;
	}
	
	/**
	 * Ajouter un client à la salle et notifier les observateurs
	 * @param client
	 */
	public void ajouterClient(ModeleClientClient client) {
		this.lstClients.put(client.getId(), client);
		this.dernierClient = client;
		this.typeModif = TypeModifSalle.NOUVEAU_CLIENT;
		this.notifierObservateur();
	}
	
	/**
	 * Retirer un client de la salle par son instance
	 * 
	 * @param client L'instance du client à retirer
	 */
	public void retirerClient(ModeleClientClient client) {
		this.retirerClient(client.getId());
	}
	
	/**
	 * Retirer un client de la salle par son id
	 * 
	 * @param idClient L'id du client à retirer
	 */
	public void retirerClient(int idClient) {
		this.clientRetire = this.lstClients.remove(idClient);
		this.typeModif = TypeModifSalle.RETIRER_CLIENT;
		this.notifierObservateur();
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public ModeleClientClient getClient(int idClient) {
		return this.lstClients.get(idClient);
	}
	
	public Map<Integer, ModeleClientClient> getLstClients() {
		return this.lstClients;
	}
	
	/**
	 * Récupérer le dernier client ajouté à la salle
	 * 
	 * @return Le dernier client
	 */
	public ModeleClientClient getDernierClient() {
		return this.dernierClient;
	}

	/**
	 * Définir le dernier client ajouté à la salle
	 * 
	 * @param dernierClient L'instance du client
	 */
	public void setDernierClient(ModeleClientClient dernierClient) {
		this.dernierClient = dernierClient;
	}
	
	/**
	 * Récupérer le dernier client qui a quitté la salle
	 * 
	 * @return Le dernier client qui a quitté
	 */
	public ModeleClientClient getClientRetire() {
		return clientRetire;
	}

	/**
	 * Définir le dernier client qui a quitté la salle
	 * @param dernierClient L'instance du client
	 */
	public void setClientRetire(ModeleClientClient clientRetire) {
		this.clientRetire = clientRetire;
	}

	public void ajouterJeu(JeuClient jeu) {
		this.lstJeux.put(jeu.getId(), jeu);
	}
	
	public void retirerJeu(JeuClient jeu) {
		this.retirerJeu(jeu.getId());
	}
	
	public void retirerJeu(int idJeu) {
		this.lstJeux.remove(idJeu);
	}
	
	public JeuClient getJeu(int idJeu) {
		return this.lstJeux.get(idJeu);
	}
	
	public Map<Integer, JeuClient> getLstJeux() {
		return this.lstJeux;
	}

	@Override
	public void ajouterObservateur(Observateur obs) {
		this.sujet.ajouterObservateur(obs);
	}

	@Override
	public void retirerObservateur(Observateur obs) {
		this.sujet.retirerObservateur(obs);
	}

	@Override
	public boolean estObservePar(Observateur obs) {
		return this.sujet.estObservePar(obs);
	}

	@Override
	public void notifierObservateur() {
		this.sujet.notifierObservateur();
	}
	
	@Override
	public TypeModifSalle getTypeModif() {
		return this.typeModif;
	}
}