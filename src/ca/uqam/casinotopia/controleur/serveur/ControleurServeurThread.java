package ca.uqam.casinotopia.controleur.serveur;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import ca.uqam.casinotopia.Avatar;
import ca.uqam.casinotopia.Partie;
import ca.uqam.casinotopia.bd.CtrlBD;
import ca.uqam.casinotopia.commande.Commande;
import ca.uqam.casinotopia.commande.CommandeServeur;
import ca.uqam.casinotopia.commande.CommandeServeurControleurChat;
import ca.uqam.casinotopia.commande.CommandeServeurControleurClient;
import ca.uqam.casinotopia.commande.CommandeServeurControleurMachine;
import ca.uqam.casinotopia.commande.CommandeServeurControleurPrincipal;
import ca.uqam.casinotopia.commande.CommandeServeurControleurRoulette;
import ca.uqam.casinotopia.commande.CommandeServeurControleurSalle;
import ca.uqam.casinotopia.commande.CommandeServeurControleurThread;

import ca.uqam.casinotopia.commande.client.authentification.CmdInformationInvalide;
import ca.uqam.casinotopia.commande.client.authentification.CmdInitClient;
import ca.uqam.casinotopia.commande.client.chat.CmdQuitterChatClient;
import ca.uqam.casinotopia.commande.client.compte.CmdInformationCreationCompte;
import ca.uqam.casinotopia.commande.client.machine.CmdAfficherJeuMachine;
import ca.uqam.casinotopia.commande.client.machine.CmdQuitterPartieMachineClient;
import ca.uqam.casinotopia.commande.client.navigation.CmdAfficherAttentePartie;
import ca.uqam.casinotopia.commande.client.roulette.CmdAfficherJeuRoulette;
import ca.uqam.casinotopia.commande.client.roulette.CmdQuitterPartieRouletteClient;
import ca.uqam.casinotopia.commande.client.salle.CmdQuitterSalleClient;
import ca.uqam.casinotopia.connexion.Connexion;
import ca.uqam.casinotopia.controleur.ControleurServeur;
import ca.uqam.casinotopia.modele.client.ModeleClientClient;
import ca.uqam.casinotopia.modele.serveur.ModeleClientServeur;
import ca.uqam.casinotopia.modele.serveur.ModelePartieMachineServeur;
import ca.uqam.casinotopia.modele.serveur.ModelePartieRouletteServeur;
import ca.uqam.casinotopia.modele.serveur.ModeleSalleServeur;
import ca.uqam.casinotopia.objet.Clavardage;
import ca.uqam.casinotopia.type.TypeEtatPartie;
import ca.uqam.casinotopia.type.TypeJeuArgent;
import ca.uqam.casinotopia.type.TypeJeuMultijoueurs;

/**
 * Controleur principal de chaque utilisateur côté serveur.
 * C'est lui qui crée les autres controleur, au besoin.
 */
public class ControleurServeurThread extends ControleurServeur implements Runnable {
	
	/**
	 * liste des controleurs associés au thread
	 */
	protected Map<String, ControleurServeur> lstControleurs = new HashMap<String, ControleurServeur>();
	
	/**
	 * Le numéro du thread
	 */
	private int number;

	/**
	 * Le modèle du client associé au thread
	 */
	private ModeleClientServeur modele;

	public ControleurServeurThread(Socket clientSocket, int number) {
		super(new Connexion(clientSocket));
		this.number = number;
		this.modele = null;
	}

	/**
	 * Ajouter un nouveau controleur à l'environnement du client
	 * 
	 * @param nom Le nom du controleur
	 * @param ctrl L'instance du controleur
	 */
	private void ajouterControleur(String nom, ControleurServeur ctrl) {
		this.lstControleurs.put(nom, ctrl);
	}
	
	/**
	 * Initialiser les controleurs de base de l'environnement du client
	 */
	private void initControleur() {
		//this.ajouterControleur("ControleurPrincipalServeur", ControleurPrincipalServeur.getInstance());
		this.ajouterControleur("ControleurClientServeur", new ControleurClientServeur(this.getConnexion(), this, this.modele));
	}

	/**
	 * La fonction principale du thread est d'attendre l'arrivée de commande du client et des les dispatcher au bon controleur
	 */
	@Override
	public void run() {
		//attend l'envoie d'une commande et l'exécute avec le bon controleur
		try {
			System.out.println("client no " + this.number + " connecté");
			while (this.connexion.isConnected()) {
				Commande cmd = null;
				try {
					cmd = (Commande) this.connexion.getObjectInputStream().readObject();
					if (cmd != null) {
						if (cmd instanceof CommandeServeur) {
							if (cmd instanceof CommandeServeurControleurThread) {
								cmd.action(this);
							}
							else if (cmd instanceof CommandeServeurControleurClient) {
								this.executerCommande(cmd, "ControleurClientServeur");
							}
							else if (cmd instanceof CommandeServeurControleurRoulette) {
								this.executerCommande(cmd, "ControleurRouletteServeur");
							}
							else if (cmd instanceof CommandeServeurControleurChat) {
								this.executerCommande(cmd, "ControleurChatServeur");
							}
							else if (cmd instanceof CommandeServeurControleurSalle) {
								this.executerCommande(cmd, "ControleurSalleServeur");
							}
							else if (cmd instanceof CommandeServeurControleurMachine) {
								this.executerCommande(cmd, "ControleurMachineServeur");
							}
							else if (cmd instanceof CommandeServeurControleurPrincipal) {
								this.executerCommande(cmd, "ControleurPrincipalServeur");
							}
							else {
								System.err.println("Ce type de commande n'est pas géré par le serveur");
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
					this.connexion.close();
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
	
	/**
	 * Récupère le controleur associé à la commande et exécute son action en envoyant le controleur en paramètre.
	 * Si le controleur n'est pas déjà instancié, il s'agit d'une erreur.
	 * 
	 * @param cmd La commande reçue
	 * @param nomControleur Le nom du controleur associé à la commande
	 */
	private void executerCommande(Commande cmd, String nomControleur) {
		ControleurServeur ctrl = this.getControleur(nomControleur);
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
	public ControleurServeur getControleur(String nom) {
		return this.lstControleurs.get(nom);
	}

	/**
	 * Créer un nouveau client.
	 * Cette action est exécutée suite à la demande du client (commande)
	 * 
	 * @param nouvClient Le modèle temporaire contenant les informations du client.
	 */
	public void actionCreerCompte(ModeleClientClient nouvClient) {
		this.modele = new ModeleClientServeur(
				nouvClient.getNomUtilisateur(),
				nouvClient.getMotDePasse(),
				nouvClient.getPrenom(),
				nouvClient.getNom(),
				nouvClient.getDateNaissance(),
				nouvClient.getCourriel(),
				0,
				new Avatar(-1, nouvClient.getAvatar().getPathImage())
		);
		
		if (CtrlBD.BD.ajouterClient(this.modele)) {
			this.connexion.envoyerCommande(new CmdInformationCreationCompte("Votre compte a été crée"));
		}
		else {
			this.connexion.envoyerCommande(new CmdInformationCreationCompte("La création du compte n'a pas réussi"));
		}
	}

	/**
	 * Authentifier le client.
	 * Si les informations sont valides, on envoi une commande au client pour initialiser l'interface.
	 * Sinon, on envoi un message d'erreur au client.
	 * 
	 * @param nomUtilisateur Nom d'utilisateur envoyé
	 * @param motDePasse Mot de passe envoyé
	 */
	public void actionAuthentifierClient(String nomUtilisateur, char[] motDePasse) {
		int no = this.number;
		System.out.println("le client " + no + " a envoyer le username " + nomUtilisateur + "!");
		
		ModeleClientServeur client = CtrlBD.BD.authentifierClient(nomUtilisateur, new String(motDePasse));
		
		if(client != null) {
			this.modele = client;
			this.modele.setConnexion(this.connexion);
			
			this.initControleur();
			
			ModeleClientClient modeleClient = this.modele.creerModeleClient();
			this.connexion.envoyerCommande(new CmdInitClient(modeleClient));
		}
		else {
			this.connexion.envoyerCommande(new CmdInformationInvalide("Les données d'authentification sont incorrectes."));
		}
	}
	
	/**
	 * Ajouter le client à un chat (salle de conversation)
	 * 
	 * @param salle Le nom de la salle
	 */
	public void actionSeConnecterAuChat(String salle) {
		//Clavardage chat = ((ControleurPrincipalServeur) this.lstControleurs.get("ControleurPrincipalServeur")).getModele().getChat(salle);
		Clavardage chat = ControleurPrincipalServeur.INSTANCE.getModele().getChat(salle);
		this.ajouterControleur("ControleurChatServeur", new ControleurChatServeur(this.connexion, this, chat));
		
		chat.connecter(this.modele);
	}

	/**
	 * Ajouter le client à une salle.
	 * Cette action est exécutée suite à la demande du client (commande)
	 * 
	 * @param idSalle L'id de la salle
	 */
	public void actionJoindreSalle(int idSalle) {
		//ControleurPrincipalServeur ctrlPrincipal = (ControleurPrincipalServeur) this.lstControleurs.get("ControleurPrincipalServeur");
		
		ModeleSalleServeur salle = ControleurPrincipalServeur.INSTANCE.getSalle(idSalle);
		
		if(salle != null) {
			this.ajouterControleur("ControleurSalleServeur", new ControleurSalleServeur(this.connexion, this, salle));
			this.ajouterControleur("ControleurChatServeur", new ControleurChatServeur(this.connexion, this, salle.getClavardage()));
			
			salle.connecter(this.modele);
		}
		else {
			//TODO Faire une commande pour donner du feedback au client
			System.err.println("LA SALLE \"" + idSalle + "\" N'EXISTE PAS");
		}
	}
	
	/**
	 * Quitter la salle courante.
	 * Cette action est exécutée suite à la demande du client (commande)
	 */
	public void actionQuitterSalle() {
		((ControleurSalleServeur) this.lstControleurs.get("ControleurSalleServeur")).quitterSalle();
		
		this.lstControleurs.remove("ControleurSalleServeur");		
		this.connexion.envoyerCommande(new CmdQuitterSalleClient());
	}
	
	/**
	 * Créer une nouvelle partie de machine à sous et y insérer le joueur.
	 * Cette action est exécutée suite à la demande du client (commande)
	 * 
	 * @param idJeu L'id du jeu de machine à sous
	 */
	public void actionJouerMachine(int idJeu) {
		//ControleurPrincipalServeur ctrlPrincipal = (ControleurPrincipalServeur) this.lstControleurs.get("ControleurPrincipalServeur");
		
		ModelePartieMachineServeur partieMachine = new ModelePartieMachineServeur(this.number, ControleurPrincipalServeur.INSTANCE.getJeu(idJeu));
		
		this.ajouterControleur("ControleurMachineServeur", new ControleurMachineServeur(this.getConnexion(), this, partieMachine));
		
		this.cmdAfficherJeuMachine(partieMachine);
	}

	/**
	 * Ajouter le joueur à une partie de roulette en attente ou à une nouvelle partie si aucune n'existe.
	 * Cette action est exécutée suite à la demande du client (commande)
	 * 
	 * @param idJeu L'id du jeu de roulette
	 * @param typeMultijoueurs Le type de jeu multijoueur
	 * @param typeArgent Le type de jeu d'argent
	 */
	public void actionAjouterJoueurDansRoulette(int idJeu, TypeJeuMultijoueurs typeMultijoueurs, TypeJeuArgent typeArgent) {
		//ControleurPrincipalServeur ctrlPrincipal = (ControleurPrincipalServeur) this.lstControleurs.get("ControleurPrincipalServeur");
		ControleurPrincipalServeur ctrlPrincipal = ControleurPrincipalServeur.INSTANCE;
		
		ModelePartieRouletteServeur partieRoulette = null;
		
		//TODO Rechercher une partie en fonction des options choisies
		//Si c'est seul, on en crée une nouvelle.
		//Si c'est avec des amis, et qu'il est l'initiateur, on crée une nouvelle partie.
		//Si c'est avec des amis, et qu'il est un invité, on récupère directement la partie (une autre fonction qui prends un id de partie directement actionAjouterJoueurDansRoulette(idPartie))
		//Si c'est avec des inconnus, pattern de recherche de base (ie : on recherche la partie en attente dans laquelle il reste le moins de place de libre, et dont le typeArgent est le même. Si aucune, on en crée une nouvelle.
		switch(typeMultijoueurs) {
			case INCONNUS :
			case AMIS :
				partieRoulette = (ModelePartieRouletteServeur) ctrlPrincipal.rechercherPartieEnAttente(idJeu, typeArgent);
				break;
		}
		
		//TODO À enlever (pour des tests)
		/*partieRoulette = null;
		partieRoulette = (ModelePartieRouletteServeur) ctrlPrincipal.getPartie(1);*/

		if (partieRoulette == null) {
			partieRoulette = new ModelePartieRouletteServeur(ctrlPrincipal.getIdPartieLibre(), typeMultijoueurs, typeArgent, TypeEtatPartie.EN_ATTENTE, ctrlPrincipal.getJeu(idJeu));
			ctrlPrincipal.ajouterPartie(partieRoulette, TypeEtatPartie.EN_ATTENTE);
			System.out.println("AUCUNE PARTIE EN ATTENTE DISPONIBLE, CRÉATION D'UNE NOUVELLE, ID : " + String.valueOf(partieRoulette.getId()));
		}
		else {
			System.out.println("PARTIE EN ATTENTE TROUVÉE, ID : " + String.valueOf(partieRoulette.getId()));
		}
		
		partieRoulette.connecter(this.modele);

		this.ajouterControleur("ControleurRouletteServeur", new ControleurRouletteServeur(this.connexion, this, partieRoulette));
		this.ajouterControleur("ControleurChatServeur", new ControleurChatServeur(this.connexion, this, partieRoulette.getClavardage()));

		this.cmdAfficherAttentePartie();
		
		int nbrJoueursCourant = partieRoulette.getNbrJoueurs();
		
		//S'il s'agit d'un jeu à plusieurs
		if(typeMultijoueurs != TypeJeuMultijoueurs.SEUL) {
			while(partieRoulette.isEnAttente()) {
				//Si le nombre minimal de joueur est atteint...
				if(partieRoulette.isNbrMinimalJoueursAtteint()) {
					//... et que le nombre maximal de joueur n'est pas atteint, on attends par tranche de 10sec de nouveau joueurs.
					//Si un délai de 10 secondes s'écoule sans nouveau joueur, ou que le nombre maximal est atteint, on démarre la partie.
					if(!partieRoulette.isNbrMaximalJoueursAtteint()) {
						nbrJoueursCourant = partieRoulette.getNbrJoueurs();
						
						this.sleepActifAttentePartie(partieRoulette, 10);
						
						//Si aucun nouveau joueur s'est ajouté depuis le dernier sleep, on sort de l'attente et on démarre la partie
						if(nbrJoueursCourant == partieRoulette.getNbrJoueurs()) {
							break;
						}
					}
					else {
						break;
					}
				}
				else {
					this.sleepActifAttentePartie(partieRoulette, 10);
				}
			}
		}
		
		if(!partieRoulette.isPartieVide()) {
			//TODO Synchronisation
			ctrlPrincipal.transfererPartieEnAttenteVersEnCours(partieRoulette);
			
			this.cmdAfficherJeuRoulette(partieRoulette);
		}
		else {
			this.actionQuitterPartieRoulette(this.getModeleClient().getId());
		}
	}
	
	/**
	 * Attente active pour le démarrage d'une partie.
	 * À chaque seconde d'attente, on regarde si la partie a été démarrée.
	 * Si c'est le cas, on arrete l'attente.
	 * 
	 * @param partie La partie sur laquelle attendre.
	 * @param secondes Le nombre de secondes d'attente.
	 */
	private void sleepActifAttentePartie(Partie partie, int secondes) {
		for(int i=0; i<secondes; i++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			if(!partie.isEnAttente()) {
				break;
			}
		}
	}
	
	/**
	 * Envoyer une commande au client pour donner du feedback sur la recherche d'une partie.
	 */
	private void cmdAfficherAttentePartie() {
		this.connexion.envoyerCommande(new CmdAfficherAttentePartie());
	}

	/**
	 * Envoyer une commande au client pour afficher le jeu de machine à sous
	 * 
	 * @param modeleServeur Le modèle version serveur du jeu de machine à sous.
	 */
	private void cmdAfficherJeuMachine(ModelePartieMachineServeur modeleServeur) {
		this.connexion.envoyerCommande(new CmdAfficherJeuMachine(modeleServeur.creerModeleClient()));
	}

	/**
	 * Envoyer une commande au client pour afficher le jeu de roulette
	 * 
	 * @param modeleServeur Le modèle version serveur du jeu de roulette.
	 */
	private void cmdAfficherJeuRoulette(ModelePartieRouletteServeur modeleServeur) {
		this.connexion.envoyerCommande(new CmdAfficherJeuRoulette(modeleServeur.creerModeleClient()));
	}

	/**
	 * Quitter le chat et retirer le controleur chat de l'environnement du client.
	 * Cette action est exécutée suite à la demande du client (commande)
	 */
	public void actionQuitterChat() {

		((ControleurChatServeur) this.lstControleurs.get("ControleurChatServeur")).actionQuitterChat();

		this.lstControleurs.remove("ControleurChatServeur");
		this.connexion.envoyerCommande(new CmdQuitterChatClient());

	}
	
	/**
	 * Quitter la partie de roulette
	 * Cette action est exécutée suite à la demande du client (commande)
	 * 
	 * @param idJoueur L'id du joueur qui quitte la partie
	 */
	//TODO Plus nécessaire d'envoyer l'id du joueur
	public void actionQuitterPartieRoulette(int idJoueur) {
		((ControleurRouletteServeur) this.lstControleurs.get("ControleurRouletteServeur")).actionQuitterPartie(idJoueur);
		
		this.lstControleurs.remove("ControleurRouletteServeur");		
		this.connexion.envoyerCommande(new CmdQuitterPartieRouletteClient());
	}
	
	/**
	 * Quitter la partie de machine à sous
	 * Cette action est exécutée suite à la demande du client (commande)
	 * 
	 * @param idJoueur L'id du joueur qui quitte la partie
	 */
	//TODO Plus nécessaire d'envoyer l'id du joueur
	public void actionQuitterMachine(int idJoueur) {
		this.modele.deconnecter();

		this.lstControleurs.remove("ControleurMachineServeur");		
		this.connexion.envoyerCommande(new CmdQuitterPartieMachineClient());
	}

	public ModeleClientServeur getModele() {
		return this.modele;
	}

	/**
	 * Récupérer la liste de tous les utilisateurs loggué
	 * 
	 * @return Liste de tous les utilisateurs
	 */
	public ArrayList<String> getAllUtilisateurs() {
		ArrayList<String> liste = new ArrayList<String>();
		for (int i = 0; i < ControleurPrincipalServeur.NUMCONNEXION; i++) {
			if (ControleurPrincipalServeur.thread[i] != null && ControleurPrincipalServeur.thread[i].isAlive()
					&& ControleurPrincipalServeur.serverThread[i].getModele().getNomUtilisateur() != null) {
				liste.add(ControleurPrincipalServeur.serverThread[i].getModele().getNomUtilisateur());
			}
		}
		return liste;
	}

	/**
	 * Envoyer une commande à tous les utilisateurs loggué
	 * 
	 * @param cmd La commande à envoyer
	 */
	public void envoyerCommandeATous(Commande cmd) {
		for (int i = 0; i < ControleurPrincipalServeur.NUMCONNEXION; i++) {
			if (ControleurPrincipalServeur.thread[i] != null && ControleurPrincipalServeur.thread[i].isAlive()
					&& ControleurPrincipalServeur.serverThread[i].getModele().getNomUtilisateur() != null) {
				ControleurPrincipalServeur.serverThread[i].getConnexion().envoyerCommande(cmd);
			}
		}
	}
}