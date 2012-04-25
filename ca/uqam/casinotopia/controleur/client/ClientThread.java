package ca.uqam.casinotopia.controleur.client;

import java.io.IOException;

import ca.uqam.casinotopia.commande.Commande;
import ca.uqam.casinotopia.commande.CommandeClient;
import ca.uqam.casinotopia.commande.CommandeClientControleurChat;
import ca.uqam.casinotopia.commande.CommandeClientControleurClient;
import ca.uqam.casinotopia.commande.CommandeClientControleurPrincipal;
import ca.uqam.casinotopia.commande.CommandeClientControleurRoulette;
import ca.uqam.casinotopia.controleur.client.ControleurPrincipalClient;

/**
 * �coute le serveur et ex�cute les actions envoy�
 * 
 * @author Olivier
 * 
 */
public class ClientThread implements Runnable {

	private ControleurPrincipalClient controleur;

	public ClientThread(ControleurPrincipalClient controleur) {
		this.controleur = controleur;
	}

	@Override
	public void run() {
		while (this.controleur.getConnexion().isConnected()) {
			Commande cmd = null;
			try {
				System.out.println("ATTENTE DE COMMANDE DU SERVEUR");
				cmd = (Commande) this.controleur.getConnexion().getObjectInputStream().readObject();
				System.out.println("COMMANDE SERVEUR OBTENUE");
				if (cmd != null) {
					if (cmd instanceof CommandeClient) {
						if (cmd instanceof CommandeClientControleurClient) {
							if (!this.controleur.getModeleNav().hasControleur("ControleurClientClient")) {
								System.out.println("ERREUR : Envoie d'une commande � un controleur non-instanci�! (ControleurClientClient)");
								// THROW EXCEPTION
								// On ne devrait jamais recevoir une commande
								// pour un controleur en particulier sans que ce
								// dernier ait �t� cr��
								// (par l'envoie d'une commande du client,
								// g�n�ralement au ControleurServeurThread)
							}
							cmd.action(this.controleur.getModeleNav().getControleur("ControleurClientClient"));
						}
						else if (cmd instanceof CommandeClientControleurRoulette) {
							if (!this.controleur.getModeleNav().hasControleur("ControleurRouletteClient")) {
								System.out.println("ERREUR : Envoie d'une commande � un controleur non-instanci�! (ControleurRouletteClient)");
								// THROW EXCEPTION
								// On ne devrait jamais recevoir une commande
								// pour un controleur en particulier sans que ce
								// dernier ait �t� cr��
								// (par l'envoie d'une commande du client,
								// g�n�ralement au ControleurServeurThread)
							}
							cmd.action(this.controleur.getModeleNav().getControleur("ControleurRouletteClient"));
						}
						else if (cmd instanceof CommandeClientControleurChat) {
							if (!this.controleur.getModeleNav().hasControleur("ControleurChatClient")) {
								System.out.println("ERREUR : Envoie d'une commande � un controleur non-instanci�! (ControleurChatClient)");
								// THROW EXCEPTION
								// On ne devrait jamais recevoir une commande
								// pour un controleur en particulier sans que ce
								// dernier ait �t� cr��
								// (par l'envoie d'une commande du client,
								// g�n�ralement au ControleurServeurThread)
							}
							cmd.action(this.controleur.getModeleNav().getControleur("ControleurChatClient"));
						}
						else if (cmd instanceof CommandeClientControleurPrincipal) {
							cmd.action(this.controleur);
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
}
