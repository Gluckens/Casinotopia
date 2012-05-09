package ca.uqam.casinotopia.controleur.serveur;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Arrays;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import ca.uqam.casinotopia.Clavardage;
import ca.uqam.casinotopia.TypeEtatPartie;
import ca.uqam.casinotopia.TypeJeu;
import ca.uqam.casinotopia.commande.Commande;
import ca.uqam.casinotopia.commande.CommandeServeur;
import ca.uqam.casinotopia.commande.CommandeServeurControleurChat;
import ca.uqam.casinotopia.commande.CommandeServeurControleurClient;
import ca.uqam.casinotopia.commande.CommandeServeurControleurMachine;
import ca.uqam.casinotopia.commande.CommandeServeurControleurPrincipal;
import ca.uqam.casinotopia.commande.CommandeServeurControleurRoulette;
import ca.uqam.casinotopia.commande.CommandeServeurControleurThread;
import ca.uqam.casinotopia.commande.client.CmdAfficherJeuRoulette;
import ca.uqam.casinotopia.commande.client.CmdAfficherMenuPrincipal;
import ca.uqam.casinotopia.commande.client.CmdInformationInvalide;
import ca.uqam.casinotopia.commande.client.machine.CmdAfficherJeuMachine;
import ca.uqam.casinotopia.connexion.Connexion;
import ca.uqam.casinotopia.controleur.ControleurServeur;
import ca.uqam.casinotopia.modele.client.ModelePartieRouletteClient;
import ca.uqam.casinotopia.modele.serveur.ModeleClientServeur;
import ca.uqam.casinotopia.modele.serveur.ModeleMachineServeur;
import ca.uqam.casinotopia.modele.serveur.ModelePartieRouletteServeur;

public class ControleurServeurThread extends ControleurServeur implements Runnable {

	private static final long serialVersionUID = -656190032558998826L;

	protected Map<String, ControleurServeur> lstControleurs = new HashMap<String, ControleurServeur>();
	private int number;

	//private ModeleUtilisateurServeur modele;
	private ModeleClientServeur modele;

	public ControleurServeurThread(Socket clientSocket, int number) {
		super(new Connexion(clientSocket), new ModeleClientServeur());
		this.number = number;
		this.modele = this.client;
	}

	private void ajouterControleur(String nom, ControleurServeur ctrl) {
		this.lstControleurs.put(nom, ctrl);
	}
	
	private void initControleur() {
		this.ajouterControleur("ControleurPrincipalServeur", ControleurPrincipalServeur.getInstance());
		this.ajouterControleur("ControleurClientServeur", new ControleurClientServeur(this.getConnexion(), this.client));
	}

	@Override
	public void run() {
		try {
			System.out.println("client no " + this.number + " connecté");
			while (this.connexion.isConnected()) {
				Commande cmd = null;
				try {
					System.out.println("ATTENTE DE COMMANDE DU CLIENT");
					cmd = (Commande) this.connexion.getObjectInputStream().readObject();
					System.out.println("COMMANDE CLIENT OBTENUE");
					if (cmd != null) {
						if (cmd instanceof CommandeServeur) {
							if (cmd instanceof CommandeServeurControleurClient) {
								if (!this.lstControleurs.containsKey("ControleurClientServeur")) {
									System.out.println("ERREUR : Envoie d'une commande à un controleur non-instancié! (ControleurClientServeur)");
								}
								cmd.action(this.lstControleurs.get("ControleurClientServeur"));
							}
							else if (cmd instanceof CommandeServeurControleurRoulette) {
								if (!this.lstControleurs.containsKey("ControleurRouletteServeur")) {
									System.out.println("ERREUR : Envoie d'une commande à un controleur non-instancié! (ControleurRouletteServeur)");
								}
								cmd.action(this.lstControleurs.get("ControleurRouletteServeur"));
							}
							else if (cmd instanceof CommandeServeurControleurChat) {
								if (!this.lstControleurs.containsKey("CommandeServeurControleurChat")) {
									System.out.println("ERREUR : Envoie d'une commande à un controleur non-instancié! (CommandeServeurControleurChat)");
								}
								cmd.action(this.lstControleurs.get("CommandeServeurControleurChat"));
							}
							else if (cmd instanceof CommandeServeurControleurPrincipal) {
								cmd.action(this.lstControleurs.get("ControleurPrincipalServeur"));
							}
							else if (cmd instanceof CommandeServeurControleurMachine) {
								cmd.action(this.lstControleurs.get("ControleurMachineServeur"));
							}
							else if (cmd instanceof CommandeServeurControleurThread) {
								cmd.action(this);
							}
						}
						else {
							System.err.println("Seulement des commandes destinées au serveur sont recevables!");
						}
					}
					else {
						System.err.println("Un problème est survenu (commande nulle).");
					}
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SocketException e) {
					// TODO Auto-generated catch block
					System.out.println("Déconnexion du client " + this.number);
					this.modele.deconnecter();
					this.getConnexion().close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Thread.sleep(1);// sauve du cpu
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/*
	 * public void actionAjouterJoueurDansPartie(TypeJeu type) { switch(type) {
	 * case ROULETTE : ModelePartieRouletteServeur partieRoulette =
	 * (ModelePartieRouletteServeur)
	 * this.ctrlPrincipal.rechercherPartieEnAttente(TypeJeu.ROULETTE); break;
	 * case BLACKJACK : ModelePartieBlackJackServeur partieBlackJack =
	 * (ModelePartieBlackJackServeur)
	 * this.ctrlPrincipal.rechercherPartieEnAttente(TypeJeu.BLACKJACK); break; }
	 * }
	 */

	// TODO Un Jeu possède une liste de parties associées... donc au départ on crée les jeu, et on ajoute des partie aux jeux?
	public void actionAjouterJoueurDansRoulette(int idJeu) {
		// TODO créer la partie dans la liste de partie sur le controleur principal et aussi dans le controleurServeurThread du client

		ControleurPrincipalServeur ctrlPrincipal = (ControleurPrincipalServeur) this.lstControleurs.get("ControleurPrincipalServeur");

		ModelePartieRouletteServeur partieRoulette = (ModelePartieRouletteServeur) ctrlPrincipal.rechercherPartieEnAttente(idJeu);

		System.out.println("JEU DANS SERVEUR_THREAD : " + ctrlPrincipal.getJeu(idJeu));

		System.out.println("MAP<JEU> DANS SERVEUR_THREAD : " + ctrlPrincipal.getLstJeux().get(TypeJeu.ROULETTE));

		System.out.println("PARTIE DANS SERVEUR_THREAD : " + partieRoulette);
		
		//TODO À enlever (pour des tests)
		partieRoulette = null;

		if (partieRoulette == null) {
			partieRoulette = new ModelePartieRouletteServeur(ctrlPrincipal.getIdPartieLibre(), true, true, ctrlPrincipal.getJeu(idJeu));
			ctrlPrincipal.ajouterPartie(partieRoulette, TypeEtatPartie.EN_ATTENTE);
			System.out.println("AUCUNE PARTIE EN ATTENTE DISPONIBLE, CRÉATION D'UNE NOUVELLE, ID : " + String.valueOf(partieRoulette.getId()));
		}
		else {
			System.out.println("PARTIE EN ATTENTE TROUVÉE, ID : " + String.valueOf(partieRoulette.getId()));
		}

		this.ajouterControleur("ControleurRouletteServeur", new ControleurRouletteServeur(this.connexion, this.client, partieRoulette));

		this.cmdAfficherJeuRoulette(partieRoulette);
	}

	public void cmdAfficherJeuRoulette(ModelePartieRouletteServeur modeleServeur) {
		ModelePartieRouletteClient modeleClient = new ModelePartieRouletteClient(modeleServeur.getId(), modeleServeur.isOptionArgent(),
				modeleServeur.isOptionMultijoueur(), modeleServeur.getInfoJeu());
		System.out.println("CONNEXION DANS CMD_AFFICHER_JEU_ROULETTE --> " + this.connexion);
		this.connexion.envoyerCommande(new CmdAfficherJeuRoulette(modeleClient));
	}

	public void actionAuthentifierClient(String nomUtilisateur, char[] motDePasse) {
		int no = this.number;
		System.out.println("le client " + no + " a envoyer le username " + nomUtilisateur + "!");

		if (Arrays.equals(motDePasse, nomUtilisateur.toCharArray())) {
			this.setModele(nomUtilisateur);
			this.initControleur();
			this.connexion.envoyerCommande(new CmdAfficherMenuPrincipal());
		}
		else {
			this.connexion.envoyerCommande(new CmdInformationInvalide("Les données d'authentification sont incorrectes."));
		}
	}
	
	public void actionSeConnecterAuChat(String salle) {
		Clavardage chat = ((ControleurPrincipalServeur) this.lstControleurs.get("ControleurPrincipalServeur")).getModele().getChat(salle);
		chat.connecter(this.modele);
	}

	/**
	 * @return the modele
	 */
	public ModeleClientServeur getModele() {
		return this.modele;
	}

	public ArrayList<String> getAllUtilisateurs() {
		System.out.println("GET_ALL_UTILISATEURS");
		ArrayList<String> liste = new ArrayList<String>();
		for (int i = 0; i < ControleurPrincipalServeur.NUMCONNEXION; i++) {
			if (ControleurPrincipalServeur.thread[i] != null && ControleurPrincipalServeur.thread[i].isAlive()
					&& ControleurPrincipalServeur.serverThread[i].getModele().getNomUtilisateur() != null) {
				liste.add(ControleurPrincipalServeur.serverThread[i].getModele().getNomUtilisateur());
			}
		}
		return liste;
	}

	public void envoyerCommandeATous(Commande cmd) {
		System.out.println("ENVOYER_COMMANDE_TOUS");
		for (int i = 0; i < ControleurPrincipalServeur.NUMCONNEXION; i++) {
			if (ControleurPrincipalServeur.thread[i] != null && ControleurPrincipalServeur.thread[i].isAlive()
					&& ControleurPrincipalServeur.serverThread[i].getModele().getNomUtilisateur() != null) {
				ControleurPrincipalServeur.serverThread[i].getConnexion().envoyerCommande(cmd);
			}
		}
	}
	
	private void setModele(String nomUtilisateur) {
		//TODO Récupérer les infos du clients dans la BD en rapport avec le nom d'utilisateur
		int id = 1;
		String prenom = "Prénom";
		String nom = "Nom";
		Date dateNaissance = new Date(0);
		String courriel = "courriel@user.com";
		int solde = 0;
		
		this.modele = new ModeleClientServeur(nomUtilisateur, this.connexion, id, prenom, nom, dateNaissance, courriel, solde);
	}

	public void lancerPartieMachine() {
		this.ajouterControleur("ControleurMachineServeur", new ControleurMachineServeur(this.getConnexion(), this.client, new ModeleMachineServeur()));
		this.connexion.envoyerCommande(new CmdAfficherJeuMachine());
		
	}
}
