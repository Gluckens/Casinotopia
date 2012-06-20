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
 * �coute le serveur en attente de commandes
 * Redirige les commandes re�ues au bon controleur et ex�cute l'action
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
		//tant que la connexion est ouverte, on v�rifie si une commande a �t� envoy�e et on ex�cute son action
		while (this.controleur.getConnexion().isConnected()) {
			Commande cmd = null;
			try {
				//System.out.println("ATTENTE DE COMMANDE DU SERVEUR");
				cmd = (Commande) this.controleur.getConnexion().getObjectInputStream().readObject();
				//System.out.println("COMMANDE SERVEUR OBTENUE");
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
							System.err.println("Ce type de commande n'est pas g�r� par le client");
						}
					}
					else {
						System.err.println("Seulement des commandes destin�es au client sont recevables!");
					}
				}
				else {
					System.err.println("Un probl�me est survenu (commande nulle).");
				}

			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				System.err.println("le serveur ne r�pond plus");
				this.controleur.getConnexion().close();
			}
		}
	}
	
	private void executerCommande(Commande cmd, String nomControleur) {
		ControleurClient ctrl = this.getControleur(nomControleur);
		if (ctrl == null) {
			System.err.format("ERREUR : Envoie d'une commande � un controleur non-instanci�! (%s)", nomControleur);
		}
		else {
			cmd.action(ctrl);
		}
	}
	
	public ControleurClient getControleur(String nom) {
		return this.controleur.getModeleNav().getControleur(nom);
	}
}