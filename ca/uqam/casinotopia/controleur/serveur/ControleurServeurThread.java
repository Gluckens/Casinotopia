package ca.uqam.casinotopia.controleur.serveur;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


import ca.uqam.casinotopia.Jeu;
import ca.uqam.casinotopia.Partie;
import ca.uqam.casinotopia.Salle;
import ca.uqam.casinotopia.TypeEtatPartie;
import ca.uqam.casinotopia.TypeJeu;
import ca.uqam.casinotopia.commande.Commande;
import ca.uqam.casinotopia.commande.CommandeServeur;
import ca.uqam.casinotopia.commande.CommandeServeurControleurClient;
import ca.uqam.casinotopia.commande.CommandeServeurControleurPrincipal;
import ca.uqam.casinotopia.commande.CommandeServeurControleurRoulette;
import ca.uqam.casinotopia.commande.CommandeServeurControleurThread;
import ca.uqam.casinotopia.commande.client.CmdAfficherJeuRoulette;
import ca.uqam.casinotopia.connexion.Connexion;
import ca.uqam.casinotopia.controleur.Controleur;
import ca.uqam.casinotopia.controleur.ControleurServeur;
import ca.uqam.casinotopia.modele.Modele;
import ca.uqam.casinotopia.modele.serveur.ModelePartieBlackJackServeur;
import ca.uqam.casinotopia.modele.serveur.ModelePartieRouletteServeur;
import ca.uqam.casinotopia.modele.serveur.ModeleUtilisateurServeur;
import ca.uqam.casinotopia.serveur.MainServeur;

public class ControleurServeurThread extends ControleurServeur implements Runnable {
	
	protected Map<String, Controleur> lstControleurs = new HashMap<String, Controleur>();
		
	/*private ControleurServeurPrincipal ctrlPrincipal;
	private ControleurClientServeur ctrlClientServeur;
	private ControleurRouletteServeur ctrlRouletteServeur;*/
	
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
			            		if(this.lstControleurs.get("ControleurClientServeur") == null) {
			            			System.out.println("ERREUR : Envoie d'une commande à un controleur non-instancié! (ControleurClientServeur)");
			            			//THROW EXCEPTION
			            			//On ne devrait jamais recevoir une commande pour un controleur en particulier sans que ce dernier ait été créé
			            			//  (par l'envoie d'une commande du client, généralement au ControleurServeurThread)
			            		}
			            		cmd.action(this.lstControleurs.get("ControleurClientServeur"));
			            	}
			            	else if(cmd instanceof CommandeServeurControleurRoulette) {
			            		if(this.lstControleurs.get("ControleurRouletteServeur") == null) {
			            			System.out.println("ERREUR : Envoie d'une commande à un controleur non-instancié! (ControleurRouletteServeur)");
			            			//THROW EXCEPTION
			            			//On ne devrait jamais recevoir une commande pour un controleur en particulier sans que ce dernier ait été créé
			            			//  (par l'envoie d'une commande du client, généralement au ControleurServeurThread)
			            		}
			            		cmd.action(this.lstControleurs.get("ControleurRouletteServeur"));
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
		
		/*this.ctrlPrincipal.ajouterPartieEnAttente(TypeJeu.ROULETTE, new ModelePartieRouletteServeur(1, true, true, new Jeu("nom1", "description1", "reglesJeu1", 1, 1, 4, 8, new Salle(), TypeJeu.ROULETTE)));
		this.ctrlPrincipal.ajouterPartieEnAttente(TypeJeu.ROULETTE, new ModelePartieRouletteServeur(2, true, true, new Jeu("nom2", "description2", "reglesJeu2", 2, 2, 2, 4, new Salle(), TypeJeu.ROULETTE)));
		this.ctrlPrincipal.ajouterPartieEnAttente(TypeJeu.ROULETTE, new ModelePartieRouletteServeur(3, true, true, new Jeu("nom3", "description3", "reglesJeu3", 3, 3, 2, 8, new Salle(), TypeJeu.ROULETTE)));
		this.ctrlPrincipal.ajouterPartieEnAttente(TypeJeu.ROULETTE, new ModelePartieRouletteServeur(4, true, true, new Jeu("nom4", "description4", "reglesJeu4", 4, 4, 3, 5, new Salle(), TypeJeu.ROULETTE)));*/
		
		ControleurServeurPrincipal ctrlPrincipal = (ControleurServeurPrincipal) this.lstControleurs.get("ControleurServeurPrincipal");
		
		ModelePartieRouletteServeur partieRoulette = (ModelePartieRouletteServeur) ((ControleurServeurPrincipal)this.lstControleurs.get("ControleurServeurPrincipal")).rechercherPartieEnAttente(idJeu);
		
		/*Jeu jeu = this.ctrlPrincipal.getLstJeux().get(TypeJeu.ROULETTE).keySet().iterator().next();
		
		ModelePartieRouletteServeur partieRoulette = (ModelePartieRouletteServeur) this.ctrlPrincipal.rechercherPartieEnAttente(jeu);*/
		
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
	
	public ArrayList<String> getAllUtilisateurs(){
		ArrayList<String> liste = new ArrayList<String>();
		for (int i = 0; i < MainServeur.NUMCONNEXION; i++) {
			if(MainServeur.thread[i] != null && 
					MainServeur.thread[i].isAlive() && 
					MainServeur.serverThread[i].getModele().getUtilisateur().getNomUtilisateur() != null){
				liste.add( MainServeur.serverThread[i].getModele().getUtilisateur().getNomUtilisateur());
			}
		}
		return liste;
	}
	
	public void envoyerCommandeATous(Commande cmd){
		for (int i = 0; i < MainServeur.NUMCONNEXION; i++) {
			if(MainServeur.thread[i] != null && 
					MainServeur.thread[i].isAlive() && 
					MainServeur.serverThread[i].getModele().getUtilisateur().getNomUtilisateur() != null){
				MainServeur.serverThread[i].getConnexion().envoyerCommande(cmd);
			}
		}
	}
	
}
