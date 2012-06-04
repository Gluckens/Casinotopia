package ca.uqam.casinotopia.controleur.serveur;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import ca.uqam.casinotopia.Avatar;
import ca.uqam.casinotopia.AvatarClient;
import ca.uqam.casinotopia.Clavardage;
import ca.uqam.casinotopia.Jeu;
import ca.uqam.casinotopia.JeuClient;
import ca.uqam.casinotopia.TypeEtatPartie;
import ca.uqam.casinotopia.TypeJeuArgent;
import ca.uqam.casinotopia.TypeJeuMultijoueurs;
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

import ca.uqam.casinotopia.commande.client.CmdInitClient;
import ca.uqam.casinotopia.commande.client.CmdAfficherJeuRoulette;
import ca.uqam.casinotopia.commande.client.CmdAfficherSalle;
import ca.uqam.casinotopia.commande.client.CmdAjouterClientSalle;
import ca.uqam.casinotopia.commande.client.CmdInformationInvalide;
import ca.uqam.casinotopia.commande.client.machine.CmdAfficherJeuMachine;
import ca.uqam.casinotopia.commande.client.CmdQuitterPartieRouletteClient;
import ca.uqam.casinotopia.commande.client.CmdQuitterSalleClient;
import ca.uqam.casinotopia.connexion.Connexion;
import ca.uqam.casinotopia.controleur.ControleurServeur;
import ca.uqam.casinotopia.modele.client.ModeleClientClient;
import ca.uqam.casinotopia.modele.client.ModelePartieRouletteClient;
import ca.uqam.casinotopia.modele.client.ModeleSalleClient;
import ca.uqam.casinotopia.modele.serveur.ModeleClientServeur;
import ca.uqam.casinotopia.modele.serveur.ModeleMachineServeur;
import ca.uqam.casinotopia.modele.serveur.ModelePartieRouletteServeur;
import ca.uqam.casinotopia.modele.serveur.ModeleSalleServeur;

public class ControleurServeurThread extends ControleurServeur implements Runnable {

	private static final long serialVersionUID = -656190032558998826L;

	protected Map<String, ControleurServeur> lstControleurs = new HashMap<String, ControleurServeur>();
	private int number;

	//private ModeleUtilisateurServeur modele;
	private ModeleClientServeur modele;

	public ControleurServeurThread(Socket clientSocket, int number) {
		super(new Connexion(clientSocket));
		this.number = number;
		//TODO Hum...
		//this.modele = this.getModeleClient();
		this.modele = null;
	}

	private void ajouterControleur(String nom, ControleurServeur ctrl) {
		this.lstControleurs.put(nom, ctrl);
	}
	
	private void initControleur() {
		this.ajouterControleur("ControleurPrincipalServeur", ControleurPrincipalServeur.getInstance());
		this.ajouterControleur("ControleurClientServeur", new ControleurClientServeur(this.getConnexion(), this, this.modele));
	}

	@Override
	public void run() {
		try {
			System.out.println("client no " + this.number + " connecté");
			while (this.connexion.isConnected()) {
				Commande cmd = null;
				try {
					//System.out.println("ATTENTE DE COMMANDE DU CLIENT");
					cmd = (Commande) this.connexion.getObjectInputStream().readObject();
					//System.out.println("COMMANDE CLIENT OBTENUE");
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
	
	private void executerCommande(Commande cmd, String nomControleur) {
		ControleurServeur ctrl = this.getControleur(nomControleur);
		if (ctrl == null) {
			System.err.format("ERREUR : Envoie d'une commande à un controleur non-instancié! (%s)", nomControleur);
		}
		else {
			cmd.action(ctrl);
		}
	}
	
	public ControleurServeur getControleur(String nom) {
		return this.lstControleurs.get(nom);
	}
	
	

	public void actionJoindreSalle(int idSalle) {
		ControleurPrincipalServeur ctrlPrincipal = (ControleurPrincipalServeur) this.lstControleurs.get("ControleurPrincipalServeur");
		
		ModeleSalleServeur salle = ctrlPrincipal.getSalle(idSalle);
		
		if(salle != null) {
			salle.ajouterClient(this.modele);
			this.ajouterControleur("ControleurSalleServeur", new ControleurSalleServeur(this.connexion, this, salle));
			
			this.cmdAfficherSalle(salle);
		}
		else {
			//TODO Faire une commande pour donner du feedback au client
			System.err.println("LA SALLE \"" + idSalle + "\" N'EXISTE PAS");
		}
		
		/*if(salle == null) {
			salle = new ModeleSalleServeur(nom);
			ctrlPrincipal.ajouterSalle(salle);
			System.out.println("AUCUNE SALLE \"" + nom + "\" N'EXISTE AVEC CE NOM, CRÉATION D'UNE NOUVELLE");
		}
		else {
			System.out.println("LA SALLE \"" + nom + "\" EXISTE");
		}
		
		salle.ajouterClient(this.modele);
		this.ajouterControleur("ControleurSalleServeur", new ControleurSalleServeur(this.connexion, this, salle));
		
		this.cmdAfficherSalle(salle);*/
	}
	
	private void cmdAfficherSalle(ModeleSalleServeur modeleServeur) {
		ModeleSalleClient modeleClient = modeleServeur.creerModeleClient();
		ModeleClientClient modeleClientClient = this.modele.creerModeleClient();
		
		for(ModeleClientServeur modeleClientServeur : modeleServeur.getLstClients().values()) {
			if(modeleClientServeur.getId() != this.modele.getId()) {
				modeleClientServeur.getConnexion().envoyerCommande(new CmdAjouterClientSalle(modeleClientClient));
			}
		}
		
		this.connexion.envoyerCommande(new CmdAfficherSalle(modeleClient));
		
		
		/*ModeleSalleClient modeleClient = new ModeleSalleClient(modeleServeur.getNom());
		for(Jeu jeu : modeleServeur.getLstJeux().values()) {
			modeleClient.ajouterJeu(new JeuClient(jeu.getId(), jeu.getNom(), jeu.getDescription(), jeu.getReglesJeu(), jeu.getEmplacement(), jeu.getNbrJoueursMin(), jeu.getNbrJoueursMax(), modeleClient, jeu.getType()));
		}
		
		ModeleClientClient modeleClientClient = this.client.creerModeleClient();
		
		for(ModeleClientServeur client : modeleServeur.getLstClients()) {
			//modeleClient.ajouterClient(new ModeleClientClient(client.getId(), client.getAvatar().getPathImage(), client.getAvatar().getPosition()));
			modeleClient.ajouterClient(client.creerModeleClient());
			if(client.getId() != this.client.getId()) {
				client.getConnexion().envoyerCommande(new CmdAjouterClientSalle(modeleClientClient));
			}
		}
		
		this.connexion.envoyerCommande(new CmdAfficherSalle(modeleClient));*/
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

	public void actionAjouterJoueurDansRoulette(int idJeu, TypeJeuMultijoueurs typeMultijoueurs, TypeJeuArgent typeArgent) {
		// TODO créer la partie dans la liste de partie sur le controleur principal et aussi dans le controleurServeurThread du client

		ControleurPrincipalServeur ctrlPrincipal = (ControleurPrincipalServeur) this.lstControleurs.get("ControleurPrincipalServeur");

		//TODO Rechercher une partie en fonction des options choisies
		//Si c'est seul, on en crée une nouvelle.
		//Si c'est avec des amis, et qu'il est l'initiateur, on crée une nouvelle partie.
		//Si c'est avec des amis, et qu'il est un invité, on récupère directement la partie (une autre fonction qui prends un id de partie directement actionAjouterJoueurDansRoulette(idPartie))
		//Si c'est avec des inconnus, pattern de recherche de base (ie : on recherche la partie en attente dans laquelle il reste le moins de place de libre, et dont le typeArgent est le même. Si aucune, on en crée une nouvelle.
		ModelePartieRouletteServeur partieRoulette = (ModelePartieRouletteServeur) ctrlPrincipal.rechercherPartieEnAttente(idJeu);
		
		//TODO À enlever (pour des tests)
		partieRoulette = null;
		partieRoulette = (ModelePartieRouletteServeur) ctrlPrincipal.getPartie(3);

		if (partieRoulette == null) {
			partieRoulette = new ModelePartieRouletteServeur(ctrlPrincipal.getIdPartieLibre(), typeMultijoueurs, typeArgent, ctrlPrincipal.getJeu(idJeu));
			ctrlPrincipal.ajouterPartie(partieRoulette, TypeEtatPartie.EN_ATTENTE);
			System.out.println("AUCUNE PARTIE EN ATTENTE DISPONIBLE, CRÉATION D'UNE NOUVELLE, ID : " + String.valueOf(partieRoulette.getId()));
		}
		else {
			System.out.println("PARTIE EN ATTENTE TROUVÉE, ID : " + String.valueOf(partieRoulette.getId()));
		}
		
		partieRoulette.ajouterJoueur(this.modele);

		this.ajouterControleur("ControleurRouletteServeur", new ControleurRouletteServeur(this.connexion, this, partieRoulette));

		this.cmdAfficherJeuRoulette(partieRoulette);
	}

	private void cmdAfficherJeuRoulette(ModelePartieRouletteServeur modeleServeur) {
		//TODO ??? INITIALISER CASE SERVEUR POUR CLIENT
		
		ModelePartieRouletteClient modeleClient = modeleServeur.creerModeleClient();
		this.connexion.envoyerCommande(new CmdAfficherJeuRoulette(modeleClient));
		
		//TODO WHAT A MESS!!!
		//Trouver une facon de gerer correctement la génération des modeles clients... trop d'associations?
		/*Jeu jeuServeur = modeleServeur.getInfoJeu();
		ModeleSalleServeur salleServeur = jeuServeur.getSalle();
		ModeleSalleClient salleClient = new ModeleSalleClient(salleServeur.getNom());
		for(Jeu jeu : salleServeur.getLstJeux().values()) {
			salleClient.getLstJeux().put(jeu.getId(), new JeuClient(jeu.getId(), jeu.getNom(), jeu.getDescription(), jeu.getReglesJeu(), jeu.getEmplacement(), jeu.getNbrJoueursMin(), jeu.getNbrJoueursMax(), salleClient, jeu.getType()));
		}*/
		/*ModeleSalleClient salleClient = new ModeleSalleClient(salleServeur.getNom(), lstJeux, lstClients, clavardage)
		JeuClient jeuClient = new JeuClient(jeuServeur.getId(), jeuServeur.getNom(), jeuServeur.getDescription(), jeuServeur.getReglesJeu(), jeuServeur.getEmplacement(), jeuServeur.getNbrJoueursMin(), jeuServeur.getNbrJoueursMax(), jeuServeur.getSalle(), jeuServeur.getType());*/
		/*ModelePartieRouletteClient modeleClient = new ModelePartieRouletteClient(modeleServeur.getId(), modeleServeur.getTypeMultijoueurs(), modeleServeur.getTypeArgent(), salleClient.getLstJeux().get(modeleServeur.getInfoJeu().getId()), modeleServeur.getTableJeu().getCases());
		this.connexion.envoyerCommande(new CmdAfficherJeuRoulette(modeleClient));*/
	}

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
	
	public void lancerPartieMachine() {
		this.ajouterControleur("ControleurMachineServeur", new ControleurMachineServeur(this.getConnexion(), this, new ModeleMachineServeur()));
		this.connexion.envoyerCommande(new CmdAfficherJeuMachine());
	}
	
	public void actionQuitterPartieRoulette(int idJoueur) {
		((ControleurRouletteServeur) this.lstControleurs.get("ControleurRouletteServeur")).actionQuitterPartie(idJoueur);
		
		//TODO Mettre à jour la vue de tous les joueurs de la partie
		
		this.lstControleurs.remove("ControleurRouletteServeur");		
		this.connexion.envoyerCommande(new CmdQuitterPartieRouletteClient());
	}
	
	public void actionQuitterSalle() {
		((ControleurSalleServeur) this.lstControleurs.get("ControleurSalleServeur")).quitterSalle();
		
		//TODO Mettre à jour la vue de tous les joueurs de la partie
		
		this.lstControleurs.remove("ControleurSalleServeur");		
		this.connexion.envoyerCommande(new CmdQuitterSalleClient());
	}
}
