package ca.uqam.casinotopia;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ca.uqam.casinotopia.commande.client.CmdAjouterMessageChat;
import ca.uqam.casinotopia.commande.client.CmdEnvoyerInformationChat;
import ca.uqam.casinotopia.commande.client.CmdMettreAJourUtilisateurChat;
import ca.uqam.casinotopia.connexion.Connectable;

public class Clavardage implements Connectable, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6879038283303975273L;

	private static int MAXMESSAGE = 10;

	private String nom;
	
	private List<String> messages = new ArrayList<String>();
	
	private List<Utilisateur> participants = new ArrayList<Utilisateur>();
	
	public Clavardage(String nom) {
		this.nom = nom;
		this.messages.add("Serveur: Bonjour � toi!");
		
	}

	public List<String> getMessage() {
		// TODO Auto-generated method stub
		return this.messages;
	}

	public void addMessage(String message) {
		while(this.messages.size() >= MAXMESSAGE){
			this.messages.remove(0);
		}
		if(!message.isEmpty() && message != null){
			this.messages.add(message);
		}

		for (int i = 0; i < participants.size(); i++) {
			this.participants.get(i).getConnexion().envoyerCommande(new CmdAjouterMessageChat(message));
		}
	}

	public void connecter(Utilisateur utilisateur) {
		if(!this.participants.contains(utilisateur)){
			//d�connect� l'utilisateur des autres clavardages
			for (int i = 0; i < utilisateur.getConnectables().size(); i++) {
				if(utilisateur.getConnectables().get(i) instanceof Clavardage){
					utilisateur.getConnectables().get(i).deconnecter(utilisateur);
				}
			}

			//ajouter l'utilisateur au clavardage et le clavardage a l'utilisateur
			this.participants.add(utilisateur);
			utilisateur.getConnectables().add(this);
			
			//initialis� le chat cot� client
			CmdEnvoyerInformationChat cmd = new CmdEnvoyerInformationChat(getParticipantsToString(),getMessage(),nom);
			utilisateur.getConnexion().envoyerCommande(cmd);
			
			//mettre a jour les autres utilisateurs du chat
			for (int i = 0; i < participants.size(); i++) {
				if(!this.participants.get(i).equals(utilisateur)){
					this.participants.get(i).getConnexion().envoyerCommande(new CmdMettreAJourUtilisateurChat(getParticipantsToString()));
				}
			}
			
			this.addMessage("l'utilisateur "+utilisateur.getNomUtilisateur()+ " s'est connect�");
		}
		
	}
	
	public void deconnecter(Utilisateur utilisateur){
		if(this.participants.contains(utilisateur)){
			this.participants.remove(utilisateur);
			for (int i = 0; i < this.participants.size(); i++) {
				if(!this.participants.get(i).equals(utilisateur)){
					this.participants.get(i).getConnexion().envoyerCommande(new CmdMettreAJourUtilisateurChat(getParticipantsToString()));
				}
			}
			this.addMessage("l'utilisateur " + utilisateur.getNomUtilisateur() + " s'est d�connect�");
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