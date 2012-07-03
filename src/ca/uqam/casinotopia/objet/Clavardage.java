package ca.uqam.casinotopia.objet;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ca.uqam.casinotopia.commande.client.chat.CmdAjouterMessageChat;
import ca.uqam.casinotopia.commande.client.chat.CmdEnvoyerInformationChat;
import ca.uqam.casinotopia.commande.client.chat.CmdMettreAJourUtilisateurChat;
import ca.uqam.casinotopia.connexion.Connectable;
import ca.uqam.casinotopia.modele.client.ModeleChatClient;

/**
 * Mod�le de chat cot� serveur
 */
public class Clavardage implements Connectable, Serializable {
	
	private static final long serialVersionUID = 1221820908161003468L;
	
	/**
	 * nombre maximum de messages sauvegard�s dans la m�moire
	 */
	private static int MAXMESSAGE = 10;
	/**
	 * le titre du chat
	 */
	private String nom;
	/**
	 * la liste des messages envoy�s au chat
	 */
	private List<String> messages = new ArrayList<String>();
	/**
	 * liste des participants au chat
	 */
	private List<Utilisateur> participants = new ArrayList<Utilisateur>();

	/**
	 * constructeur
	 * @param nom titre du chat
	 */
	public Clavardage(String nom) {
		this.nom = nom;
		this.messages.add("Serveur: Bonjour � toi!");
	}
	
	public ModeleChatClient creerModeleClient() {
		return new ModeleChatClient(
				this.nom,
				this.messages,
				this.getParticipantsToString()
		);
	}

	/**
	 * r�cup�re les derniers messages
	 * @return liste des messages
	 */
	public List<String> getMessage() {
		return this.messages;
	}

	/**
	 * ajoute un message � la liste des messages
	 * enl�ve le message le plus ancien si on d�passe le maximum de messages
	 * indique � tous les participants qu'un nouveau message s'est ajout�
	 * @param message le message � ajouter
	 */
	public void addMessage(String message) {
		while (this.messages.size() >= MAXMESSAGE) {
			this.messages.remove(0);
		}
		if (!message.isEmpty() && message != null) {
			this.messages.add(message);
		}

		for (int i = 0; i < this.participants.size(); i++) {
			this.participants.get(i).getConnexion().envoyerCommande(new CmdAjouterMessageChat(message));
		}
	}


	@Override
	public void connecter(Utilisateur utilisateur) {
		if (!this.participants.contains(utilisateur)) {
			//D�connecter l'utilisateur des autres clavardages
			for(Connectable connectable : utilisateur.getConnectables()) {
				if(connectable instanceof Clavardage) {
					connectable.deconnecter(utilisateur);
				}
			}

			//Ajouter l'utilisateur au clavardage et le clavardage � l'utilisateur
			this.participants.add(utilisateur);
			utilisateur.ajouterConnectable(this);

			//Initialiser le chat cot� client
			CmdEnvoyerInformationChat cmd = new CmdEnvoyerInformationChat(this.getParticipantsToString(), this.getMessage(), this.nom);
			utilisateur.getConnexion().envoyerCommande(cmd);

			//Mettre � jour les autres utilisateurs du chat
			for (int i = 0; i < this.participants.size(); i++) {
				if (!this.participants.get(i).equals(utilisateur)) {
					this.participants.get(i).getConnexion().envoyerCommande(new CmdMettreAJourUtilisateurChat(this.getParticipantsToString()));
				}
			}

			this.addMessage("l'utilisateur " + utilisateur.getNomUtilisateur() + " s'est connect�");
		}
	}

	@Override
	public void deconnecter(Utilisateur utilisateur) {

		if (this.participants.contains(utilisateur)) {
			this.participants.remove(utilisateur);
			for (int i = 0; i < this.participants.size(); i++) {
				if (!this.participants.get(i).equals(utilisateur)) {
					//indique aux autres participants que l'utilisateur a �t� d�connect�
					this.participants.get(i).getConnexion().envoyerCommande(new CmdMettreAJourUtilisateurChat(this.getParticipantsToString()));
				}
			}
			//ajoute un message au chat
			this.addMessage("l'utilisateur " + utilisateur.getNomUtilisateur() + " s'est d�connect�");
		}
	}

	/**
	 * retourne le nom des participants sous une arrayliste
	 * @return liste des participants
	 */
	public List<String> getParticipantsToString() {
		ArrayList<String> liste = new ArrayList<String>();

		for (int i = 0; i < this.participants.size(); i++) {
			liste.add(this.participants.get(i).getNomUtilisateur());
		}
		return liste;
	}
}