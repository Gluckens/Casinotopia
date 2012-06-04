package ca.uqam.casinotopia.modele.client;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

import ca.uqam.casinotopia.AvatarClient;
import ca.uqam.casinotopia.Clavardage;
import ca.uqam.casinotopia.JeuClient;
import ca.uqam.casinotopia.modele.Modele;
import ca.uqam.casinotopia.modif.TypeModifSalle;
import ca.uqam.casinotopia.observateur.BaseObservable;
import ca.uqam.casinotopia.observateur.Observable;
import ca.uqam.casinotopia.observateur.Observateur;

public class ModeleSalleClient implements Modele, Observable {
	
	private static final long serialVersionUID = 4068837934110957774L;
	
	private int id;
	private String nom;
	private Map<Integer, JeuClient> lstJeux;
	private Map<Integer, ModeleClientClient> lstClients;
	//private Set<ModeleClientClient> lstClients;
	private Clavardage clavardage;
	
	//TODO trouver une meilleure méthode...
	private ModeleClientClient dernierClient;
	private ModeleClientClient clientRetire;
	
	private TypeModifSalle typeModif;

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
	
	public boolean validerDeplacement(AvatarClient avatar) {
		for(JeuClient jeu : this.lstJeux.values()) {
			//if(jeu.getEmplacement().contains(position)) {
			if(jeu.getEmplacement().intersects(avatar.getBounds())) {
				return false;
			}
		}
		
		return true;
	}
	
	public boolean validerDeplacement(AvatarClient avatar, Point position) {
		for(JeuClient jeu : this.lstJeux.values()) {
			//if(jeu.getEmplacement().contains(position)) {
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
	
	public boolean validerDeplacement(Point position) {
		for(JeuClient jeu : this.lstJeux.values()) {
			if(jeu.getEmplacement().contains(position)) {
				return false;
			}
		}
		
		return true;
	}

	public JeuClient checkProximites(AvatarClient avatar, Point position) {
		for(JeuClient jeu : this.lstJeux.values()) {
			if(jeu.getEmplacement(20).intersects(avatar.getBounds(position))) {
				return jeu;
			}
		}
		
		return null;
	}
	
	/*public void ajouterClient(ModeleClientClient client) {
		this.lstClients.add(client);
	}
	
	public void retirerClient(ModeleClientClient client) {
		this.lstClients.remove(client);
	}
	
	public Set<ModeleClientClient> getLstClients() {
		return this.lstClients;
	}*/
	
	public void ajouterClient(ModeleClientClient client) {
		this.lstClients.put(client.getId(), client);
		this.dernierClient = client;
		this.typeModif = TypeModifSalle.NOUVEAU_CLIENT;
		this.notifierObservateur();
	}
	
	//TODO Ou bedon plutot déconnecter?
	public void retirerClient(ModeleClientClient client) {
		this.retirerClient(client.getId());
	}
	
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
	 * @return the dernierClient
	 */
	public ModeleClientClient getDernierClient() {
		return this.dernierClient;
	}

	/**
	 * @param dernierClient the dernierClient to set
	 */
	public void setDernierClient(ModeleClientClient dernierClient) {
		this.dernierClient = dernierClient;
	}
	
	/**
	 * @return the clientRetire
	 */
	public ModeleClientClient getClientRetire() {
		return clientRetire;
	}

	/**
	 * @param clientRetire the clientRetire to set
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
