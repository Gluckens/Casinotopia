package ca.uqam.casinotopia.modele.client;

import java.util.HashMap;
import java.util.Map;

import ca.uqam.casinotopia.Clavardage;
import ca.uqam.casinotopia.Jeu;
import ca.uqam.casinotopia.modele.Modele;
import ca.uqam.casinotopia.modif.TypeModifSalle;
import ca.uqam.casinotopia.observateur.BaseObservable;
import ca.uqam.casinotopia.observateur.Observable;
import ca.uqam.casinotopia.observateur.Observateur;

public class ModeleSalleClient implements Modele, Observable {
	
	private static final long serialVersionUID = 4068837934110957774L;
	
	private String nom;
	private Map<Integer, Jeu> lstJeux;
	private Map<Integer, ModeleClientClient> lstClients;
	//private Set<ModeleClientClient> lstClients;
	private Clavardage clavardage;
	
	private ModeleClientClient dernierClient;
	
	private TypeModifSalle typeModif;

	private BaseObservable sujet = new BaseObservable(this);
	
	public ModeleSalleClient(String nom) {
		this(nom, new HashMap<Integer, Jeu>());
	}
	
	public ModeleSalleClient(String nom, Map<Integer, Jeu> lstJeux) {
		this(nom, lstJeux, new HashMap<Integer, ModeleClientClient>(), new Clavardage("Chat salle " + nom));
	}
	
	public ModeleSalleClient(String nom, Map<Integer, Jeu> lstJeux, Map<Integer, ModeleClientClient> lstClients, Clavardage clavardage) {
		this.nom = nom;
		this.lstJeux = lstJeux;
		this.lstClients = lstClients;
		this.clavardage = clavardage;
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
		this.lstClients.remove(idClient);
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
