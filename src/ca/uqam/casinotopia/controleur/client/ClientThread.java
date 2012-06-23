package ca.uqam.casinotopia.controleur.client;

import java.io.IOException;

import ca.uqam.casinotopia.commande.Commande;
import ca.uqam.casinotopia.commande.CommandeClient;
import ca.uqam.casinotopia.commande.CommandeClientControleurChat;
import ca.uqam.casinotopia.commande.CommandeClientControleurClient;
import ca.uqam.casinotopia.commande.CommandeClientControleurMachine;
import ca.uqam.casinotopia.commande.CommandeClientControleurPrincipal;
import ca.uqam.casinotopia.commande.CommandeClientControleurRoulette;
import ca.uqam.casinotopia.commande.CommandeClientControleurSalle;
import ca.uqam.casinotopia.controleur.ControleurClient;
import ca.uqam.casinotopia.controleur.client.ControleurPrincipalClient;

/**
 * Écoute le serveur en attente de commandes
 * Redirige les commandes reçues au bon controleur et exécute l'action
 */
public class ClientThread implements Runnable {

	/**
	 * controleur du thread
	 */
	private ControleurPrincipalClient controleur;

	public ClientThread(ControleurPrincipalClient controleur) {
		this.controleur = controleur;
	}

	@Override
	public void run() {
		//tant que la connexion est ouverte, on vérifie si une commande a été envoyée et on exécute son action
		while (this.controleur.getConnexion().isConnected()) {
			Commande cmd = null;
			try {
				cmd = (Commande) this.controleur.getConnexion().getObjectInputStream().readObject();
				if (cmd != null) {
					if (cmd instanceof CommandeClient) {
						if (cmd instanceof CommandeClientControleurPrincipal) {
							cmd.action(this.controleur);
						}
						else if (cmd instanceof CommandeClientControleurClient) {
							this.executerCommande(cmd, "ControleurClientClient");
						}
						else if (cmd instanceof CommandeClientControleurRoulette) {
							this.executerCommande(cmd, "ControleurRouletteClient");
						}
						else if (cmd instanceof CommandeClientControleurChat) {
							this.executerCommande(cmd, "ControleurChatClient");
						}
						else if (cmd instanceof CommandeClientControleurMachine) {
							this.executerCommande(cmd, "ControleurMachineClient");
						}
						else if (cmd instanceof CommandeClientControleurSalle) {
							this.executerCommande(cmd, "ControleurSalleClient");
						}
						else {
							System.err.println("Ce type de commande n'est pas géré par le client");
						}
					}
					else {
						System.err.println("Seulement des commandes destinées au client sont recevables!");
					}
				}
				else {
					System.err.println("Un problème est survenu (commande nulle).");
				}

			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				System.err.println("le serveur ne répond plus");
				this.controleur.getConnexion().close();
			}
		}
	}
	
	/**
	 * Récupère le controleur associé à la commande et exécute son action en envoyant le controleur en paramètre.
	 * Si le controleur n'est pas déjà instancié, il s'agit d'une erreur.
	 * 
	 * @param cmd La commande reçue
	 * @param nomControleur Le nom du controleur associé à la commande
	 */
	private void executerCommande(Commande cmd, String nomControleur) {
		ControleurClient ctrl = this.getControleur(nomControleur);
		if (ctrl == null) {
			System.err.format("ERREUR : Envoie d'une commande à un controleur non-instancié! (%s)", nomControleur);
		}
		else {
			cmd.action(ctrl);
		}
	}
	
	/**
	 * Récupère un controleur par son nom
	 * 
	 * @param nom Le nom du controleur à récupérer
	 * @return Le controleur demandé
	 */
	public ControleurClient getControleur(String nom) {
		return this.controleur.getModeleNav().getControleur(nom);
	}
}