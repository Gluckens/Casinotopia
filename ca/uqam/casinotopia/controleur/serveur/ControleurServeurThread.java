package ca.uqam.casinotopia.controleur.serveur;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


import ca.uqam.casinotopia.TypeEtatPartie;
import ca.uqam.casinotopia.TypeJeu;
import ca.uqam.casinotopia.commande.Commande;
import ca.uqam.casinotopia.commande.CommandeServeur;
import ca.uqam.casinotopia.commande.CommandeServeurControleurChat;
import ca.uqam.casinotopia.commande.CommandeServeurControleurClient;
import ca.uqam.casinotopia.commande.CommandeServeurControleurPrincipal;
import ca.uqam.casinotopia.commande.CommandeServeurControleurRoulette;
import ca.uqam.casinotopia.commande.CommandeServeurControleurThread;
import ca.uqam.casinotopia.commande.client.CmdAfficherJeuRoulette;
import ca.uqam.casinotopia.connexion.Connexion;
import ca.uqam.casinotopia.controleur.Controleur;
import ca.uqam.casinotopia.controleur.ControleurServeur;
import ca.uqam.casinotopia.modele.serveur.ModelePartieRouletteServeur;
import ca.uqam.casinotopia.modele.serveur.ModeleUtilisateurServeur;

public class ControleurServeurThread extends ControleurServeur implements Runnable {
	
	protected Map<String, Controleur> lstControleurs = new HashMap<String, Controleur>();
	
	private ModeleUtilisateurServeur modele = new ModeleUtilisateurServeur();
	
	public ControleurServeurThread(Socket clientSocket, int number) {
		this.setConnexion(new Connexion(clientSocket));
		this.modele.number = number;
		
		this.ajouterControleur("ControleurServeurPrincipal", ControleurServeurPrincipal.getInstance());
		this.ajouterControleur("ControleurClientServeur", new ControleurClientServeur(this.getConnexion()));
	}
	
	public void ajouterControleur(String nom, Controleur ctrl) {
		this.lstControleurs.put(nom, ctrl);
	}
	
	@Override
	public void run() {
		try {
			System.out.println("client no "+this.modele.number+" connecté");
			while(getConnexion().isConnected()) {
				Commande cmd = null;
	            try {
					cmd = (Commande) this.getConnexion().getObjectInputStream().readObject();
		            if(cmd != null) {
		            	if(cmd instanceof CommandeServeur) {
		            		System.out.println("COMMANDE RECU DE TYPE COMMANDE_SERVEUR");
			            	if(cmd instanceof CommandeServeurControleurClient) {
			            		if(!this.lstControleurs.containsKey("ControleurClientServeur")) {
			            			System.out.println("ERREUR : Envoie d'une commande à un controleur non-instancié! (ControleurClientServeur)");
			            			//THROW EXCEPTION
			            			//On ne devrait jamais recevoir une commande pour un controleur en particulier sans que ce dernier ait été créé
			            			//  (par l'envoie d'une commande du client, généralement au ControleurServeurThread)
			            		}
			            		cmd.action(this.lstControleurs.get("ControleurClientServeur"));
			            	}
			            	else if(cmd instanceof CommandeServeurControleurRoulette) {
			            		if(!this.lstControleurs.containsKey("ControleurRouletteServeur")) {
			            			System.out.println("ERREUR : Envoie d'une commande à un controleur non-instancié! (ControleurRouletteServeur)");
			            			//THROW EXCEPTION
			            			//On ne devrait jamais recevoir une commande pour un controleur en particulier sans que ce dernier ait été créé
			            			//  (par l'envoie d'une commande du client, généralement au ControleurServeurThread)
			            		}
			            		cmd.action(this.lstControleurs.get("ControleurRouletteServeur"));
			            	}
			            	else if(cmd instanceof CommandeServeurControleurChat) {
			            		if(!this.lstControleurs.containsKey("CommandeServeurControleurChat")) {
			            			System.out.println("ERREUR : Envoie d'une commande à un controleur non-instancié! (CommandeServeurControleurChat)");
			            			//THROW EXCEPTION
			            			//On ne devrait jamais recevoir une commande pour un controleur en particulier sans que ce dernier ait été créé
			            			//  (par l'envoie d'une commande du client, généralement au ControleurServeurThread)
			            		}
			            		cmd.action(this.lstControleurs.get("CommandeServeurControleurChat"));
			            	}
			            	else if(cmd instanceof CommandeServeurControleurPrincipal) {
			            		cmd.action(this.lstControleurs.get("ControleurServeurPrincipal"));
			            	}
			            	else if(cmd instanceof CommandeServeurControleurThread) {
			            		cmd.action(this);
			            	}
		            	}
		            	else {
		            		System.err.println("Seulement des commandes destinées au serveur sont recevables!");
		            	}
		            }
		            else{
		            	System.err.println("Un problème est survenu (commande nulle).");
		            }
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SocketException e) {
					// TODO Auto-generated catch block
					System.out.println("Déconnexion du client " + this.modele.number);
					this.modele.getUtilisateur().deconnect();
					getConnexion().close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Thread.sleep(1);//sauve du cpu
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	/*public void actionAjouterJoueurDansPartie(TypeJeu type) {
		switch(type) {
			case ROULETTE :
				ModelePartieRouletteServeur partieRoulette = (ModelePartieRouletteServeur) this.ctrlPrincipal.rechercherPartieEnAttente(TypeJeu.ROULETTE);
				break;
			case BLACKJACK :
				ModelePartieBlackJackServeur partieBlackJack = (ModelePartieBlackJackServeur) this.ctrlPrincipal.rechercherPartieEnAttente(TypeJeu.BLACKJACK);
				break;
		}
	}*/
	
	//TODO Un Jeu possède une liste de parties associées... donc au départ on crée les jeu, et on ajoute des partie aux jeux?
	public void actionAjouterJoueurDansRoulette(int idJeu) {
		//TODO créer la partie dans la liste de partie sur le controleur principal et aussi dans le controleurServeurThread du client
		
		ControleurServeurPrincipal ctrlPrincipal = (ControleurServeurPrincipal) this.lstControleurs.get("ControleurServeurPrincipal");
		
		ModelePartieRouletteServeur partieRoulette = (ModelePartieRouletteServeur) ctrlPrincipal.rechercherPartieEnAttente(idJeu);
		
		System.out.println("JEU DANS SERVEUR_THREAD : " + ctrlPrincipal.getJeu(idJeu));
		
		System.out.println("MAP<JEU> DANS SERVEUR_THREAD : " + ctrlPrincipal.getLstJeux().get(TypeJeu.ROULETTE));
		
		System.out.println("PARTIE DANS SERVEUR_THREAD : " + partieRoulette);
		
		//TODO comment faire pour trouver un id unique a une partie? Parcourir le map de jeu AU COMPLET (tout type de jeux confondus)?
		//Genre, dans une boucle de i=0 ... 99999999, pour chaque i on test si une partie avec cet id existe?
		//Ou encore, se faire un Map simple de Map<Integer, Partie>, et à chaque fois qu'on crée/supprime une partie, on met à jourle map de partie et le map de Jeu.
		//Donc, toutes les parties du map de partie se retrouvent à quelque part dans le map de jeu
		if(partieRoulette == null) {
			partieRoulette = new ModelePartieRouletteServeur(ctrlPrincipal.getIdPartieLibre(), true, true, ctrlPrincipal.getJeu(idJeu));
			ctrlPrincipal.ajouterPartie(partieRoulette, TypeEtatPartie.EN_ATTENTE);
			System.out.println("AUCUNE PARTIE EN ATTENTE DISPONIBLE, CRÉATION D'UNE NOUVELLE, ID : " + String.valueOf(partieRoulette.getId()));
		}
		else {
			System.out.println("PARTIE EN ATTENTE TROUVÉE, ID : " + String.valueOf(partieRoulette.getId()));
		}		
		
		this.ajouterControleur("ControleurRouletteServeur", new ControleurRouletteServeur(this.connexion, partieRoulette));
		
		this.cmdAfficherJeuRoulette(partieRoulette.getId());
	}
	
	public void cmdAfficherJeuRoulette(int idPartie) {
		this.connexion.envoyerCommande(new CmdAfficherJeuRoulette(idPartie));
	}


	/**
	 * @return the modele
	 */
	public ModeleUtilisateurServeur getModele() {
		return modele;
	}

	/**
	 * @param modele the modele to set
	 */
	public void setModele(ModeleUtilisateurServeur modele) {
		this.modele = modele;
	}
	
	public ArrayList<String> getAllUtilisateurs() {
		System.out.println("GET_ALL_UTILISATEURS");
		ArrayList<String> liste = new ArrayList<String>();
		for (int i = 0; i < ControleurServeurPrincipal.NUMCONNEXION; i++) {
			if(ControleurServeurPrincipal.thread[i] != null && 
					ControleurServeurPrincipal.thread[i].isAlive() && 
					ControleurServeurPrincipal.serverThread[i].getModele().getUtilisateur().getNomUtilisateur() != null) {
				liste.add( ControleurServeurPrincipal.serverThread[i].getModele().getUtilisateur().getNomUtilisateur());
			}
		}
		return liste;
	}
	
	public void envoyerCommandeATous(Commande cmd) {
		System.out.println("ENVOYER_COMMANDE_TOUS");
		for (int i = 0; i < ControleurServeurPrincipal.NUMCONNEXION; i++) {
			if(ControleurServeurPrincipal.thread[i] != null && 
					ControleurServeurPrincipal.thread[i].isAlive() && 
					ControleurServeurPrincipal.serverThread[i].getModele().getUtilisateur().getNomUtilisateur() != null){
				ControleurServeurPrincipal.serverThread[i].getConnexion().envoyerCommande(cmd);
			}
		}
	}
	
}
