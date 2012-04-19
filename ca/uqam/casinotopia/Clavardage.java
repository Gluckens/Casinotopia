package ca.uqam.casinotopia;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import ca.uqam.casinotopia.commande.client.AjouterMessageChat;
import ca.uqam.casinotopia.commande.client.EnvoyerInformationChat;
import ca.uqam.casinotopia.commande.client.MettreAJourUtilisateurChat;
import ca.uqam.casinotopia.connexion.Connectable;
import ca.uqam.casinotopia.controleur.serveur.ControleurServeurThread;
import ca.uqam.casinotopia.serveur.MainServeur;

public class Clavardage implements Connectable{
	
	private static int MAXMESSAGE = 10;

	private String nom;
	
	private List<String> messages = new ArrayList<String>();
	
	private List<Utilisateur> participants = new ArrayList<Utilisateur>();
	
	public Clavardage(String nom) {
		this.nom = nom;
		messages.add("Serveur: Bonjours à toi!");
		
	}

	public List<String> getMessage() {
		// TODO Auto-generated method stub
		return messages;
	}

	public void addMessage(String message) {
		while(this.messages.size() >= MAXMESSAGE){
			messages.remove(0);
		}
		if(!message.isEmpty() && message != null){
			this.messages.add(message);
		}

		for (int i = 0; i < participants.size(); i++) {
			participants.get(i).getConnexion().envoyerCommande(new AjouterMessageChat(message));
		}
	}

	public void connect(Utilisateur utilisateur) {
		if(!participants.contains(utilisateur)){
			//déconnecté l'utilisateur des autres clavardages
			for (int i = 0; i < utilisateur.getConnectables().size(); i++) {
				if(utilisateur.getConnectables().get(i) instanceof Clavardage){
					utilisateur.getConnectables().get(i).deconnect(utilisateur);
				}
			}

			//ajouter l'utilisateur au clavardage et le clavardage a l'utilisateur
			participants.add(utilisateur);
			utilisateur.getConnectables().add(this);
			
			//initialisé le chat coté client
			EnvoyerInformationChat cmd = new EnvoyerInformationChat(getParticipantsToString(),getMessage(),nom);
			utilisateur.getConnexion().envoyerCommande(cmd);
			
			//mettre a jour les autres utilisateurs du chat
			for (int i = 0; i < participants.size(); i++) {
				if(!participants.get(i).equals(utilisateur)){
					participants.get(i).getConnexion().envoyerCommande(new MettreAJourUtilisateurChat(getParticipantsToString()));
				}
			}
			
			addMessage("l'utilisateur "+utilisateur.getNomUtilisateur()+ " s'est connecté");
		}
		
	}
	
	public void deconnect(Utilisateur utilisateur){
		if(participants.contains(utilisateur)){
			participants.remove(utilisateur);
			for (int i = 0; i < participants.size(); i++) {
				if(!participants.get(i).equals(utilisateur)){
					participants.get(i).getConnexion().envoyerCommande(new MettreAJourUtilisateurChat(getParticipantsToString()));
				}
			}
			addMessage("l'utilisateur "+utilisateur.getNomUtilisateur()+ " s'est déconnecté");
		}
	}
	
	public List<String> getParticipantsToString() {
		ArrayList<String> liste = new ArrayList<String>();
		
		for (int i = 0; i < participants.size(); i++) {
			liste.add(participants.get(i).getNomUtilisateur());
		}
		return liste;
	}
	
	
	
}