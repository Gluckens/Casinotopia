package ca.uqam.casinotopia.controleur.serveur;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedSet;
import java.util.TreeSet;

import ca.uqam.casinotopia.Jeu;
import ca.uqam.casinotopia.Joueur;
import ca.uqam.casinotopia.Partie;
import ca.uqam.casinotopia.Salle;
import ca.uqam.casinotopia.TypeEtatPartie;
import ca.uqam.casinotopia.TypeJeu;
import java.io.IOException;
import java.net.BindException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import ca.uqam.casinotopia.controleur.ControleurServeur;
import ca.uqam.casinotopia.modele.serveur.ModelePartieRouletteServeur;
import ca.uqam.casinotopia.modele.serveur.ModeleServeurPrincipal;


public final class ControleurServeurPrincipal extends ControleurServeur {
	
	public static final int NUMCONNEXION = 10;
	public static final int MAX_PARTIES = 10000;

	private static ControleurServeurPrincipal instance;
	private static ServerSocket server;
	private ModeleServeurPrincipal modele;
	public static Thread[] thread = new Thread[NUMCONNEXION];
	public static ControleurServeurThread[] serverThread = new ControleurServeurThread[NUMCONNEXION];
	private static Boolean actif = true; 
	
	private Map<TypeJeu, Map<Integer, Jeu>> lstJeux;
	private Map<Integer, Partie> lstParties;
	
	
	private ControleurServeurPrincipal() {
		this.setModeleServeur(new ModeleServeurPrincipal());
		
		this.lstParties = new HashMap<Integer, Partie>();
		
		this.initJeux();
		
		try {
	      InetAddress address = InetAddress.getLocalHost();
	      	System.out.println("Ton ip est surement : "+address.getHostAddress());
	    }
	    catch (UnknownHostException e) {
	    	System.out.println("Could not find this computer's address.");
	    }
		try {
			System.out.println("création du server");
			server = new ServerSocket(7777);
			System.out.println("server démarré");
			while(actif){
				Socket skt = server.accept();
				for (int i = 0; i < NUMCONNEXION; i++) {
					if(thread[i] != null && !thread[i].isAlive()){
						thread[i] = null;
					}
					if(thread[i] == null){
						serverThread[i] = new ControleurServeurThread(skt,i);
						thread[i] = new Thread(serverThread[i]);
						thread[i].start();
						System.err.println("client "+i+" connecté");
						break;
					}
				}
				//indiquer au client que le serveur est plein
			}
		} catch (BindException e) {
			System.out.println("Il y a déjà un serveur sur même port");
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static ControleurServeurPrincipal getInstance() {
		if(instance == null) {
			instance = new ControleurServeurPrincipal();
		}
		return instance;
	}
	
	private void initJeux() {
		//TODO Créer les instance de jeux via la BD
		
		//TODO Créer des partie dans chaque jeu pour les tests
		
		this.lstJeux = new HashMap<TypeJeu, Map<Integer, Jeu>>();
		
		this.lstJeux.put(TypeJeu.ROULETTE, new HashMap<Integer, Jeu>());
		this.creerJeuTest(1, "nom1", "description1", "reglesJeu1", 1, 1, 4, 8, new Salle(), TypeJeu.ROULETTE);
		this.creerJeuTest(2, "nom2", "description2", "reglesJeu2", 2, 2, 2, 4, new Salle(), TypeJeu.ROULETTE);
		this.creerJeuTest(3, "nom3", "description3", "reglesJeu3", 3, 3, 2, 8, new Salle(), TypeJeu.ROULETTE);
		this.creerJeuTest(4, "nom4", "description4", "reglesJeu4", 4, 4, 3, 5, new Salle(), TypeJeu.ROULETTE);
		

		this.ajouterPartie(new ModelePartieRouletteServeur(3, true, true, this.getJeu(1)), TypeEtatPartie.EN_ATTENTE);
		this.ajouterPartie(new ModelePartieRouletteServeur(5, true, false, this.getJeu(1)), TypeEtatPartie.EN_COURS);
		this.ajouterPartie(new ModelePartieRouletteServeur(7, true, true, this.getJeu(1)), TypeEtatPartie.EN_COURS);
		this.ajouterPartie(new ModelePartieRouletteServeur(9, false, true, this.getJeu(1)), TypeEtatPartie.EN_ATTENTE);
		this.ajouterPartie(new ModelePartieRouletteServeur(11, true, true, this.getJeu(1)), TypeEtatPartie.EN_ATTENTE);
		
		this.ajouterPartie(new ModelePartieRouletteServeur(13, true, true, this.getJeu(2)), TypeEtatPartie.EN_COURS);
		this.ajouterPartie(new ModelePartieRouletteServeur(14, true, false, this.getJeu(2)), TypeEtatPartie.EN_COURS);
		this.ajouterPartie(new ModelePartieRouletteServeur(15, true, true, this.getJeu(2)), TypeEtatPartie.EN_COURS);
		this.ajouterPartie(new ModelePartieRouletteServeur(8, false, true, this.getJeu(2)), TypeEtatPartie.EN_ATTENTE);
		this.ajouterPartie(new ModelePartieRouletteServeur(16, false, false, this.getJeu(2)), TypeEtatPartie.EN_ATTENTE);
		

		this.getPartie(8).ajouterJoueur(new Joueur());
		this.getPartie(8).ajouterJoueur(new Joueur());
		this.getPartie(16).ajouterJoueur(new Joueur());
		
		
		this.ajouterPartie(new ModelePartieRouletteServeur(1, true, true, this.getJeu(4)), TypeEtatPartie.EN_COURS);
		this.ajouterPartie(new ModelePartieRouletteServeur(24, true, false, this.getJeu(4)), TypeEtatPartie.EN_ATTENTE);
		this.ajouterPartie(new ModelePartieRouletteServeur(6, true, true, this.getJeu(4)), TypeEtatPartie.EN_COURS);
		this.ajouterPartie(new ModelePartieRouletteServeur(4, false, true, this.getJeu(4)), TypeEtatPartie.EN_ATTENTE);
		this.ajouterPartie(new ModelePartieRouletteServeur(18, false, false, this.getJeu(4)), TypeEtatPartie.EN_COURS);
		
		
		this.lstJeux.put(TypeJeu.BLACKJACK, new HashMap<Integer, Jeu>());
	}
	
	private Jeu[] getArrJeux(TypeJeu type) {
		ArrayList<Jeu> lstKeys = new ArrayList<Jeu>();
		
		Collection<Jeu> colJeu = this.lstJeux.get(type).values();
		
		for(Jeu j : colJeu) {
			lstKeys.add(j);
		}
		
		return lstKeys.toArray(new Jeu[colJeu.size()]);
	}
	
	public void creerJeuTest(int id, String nomJeu, String descJeu, String reglesJeu, int posXJeu, int posYJeu, int minJoueursJeu, int maxJoueursJeu, Salle salle, TypeJeu type) {
		Jeu jeu = new Jeu(id, nomJeu, descJeu, reglesJeu, posXJeu, posYJeu, minJoueursJeu, maxJoueursJeu, salle, type);
		System.out.println("JEU DANS SERVEUR_PRINCIPAL : " + jeu);
		this.lstJeux.get(TypeJeu.ROULETTE).put(jeu.getId(), jeu);
	}
	
	public Partie getPartie(int idPartie) {
		return this.lstParties.get(idPartie);
	}
	
	public Partie getPartie(Partie partie) {
		return this.getPartie(partie.getId());
	}
	
	private Map<Integer, Partie> getMapPartie(int idPartie) {
		Map<Integer, Partie> mapPartie = null;
		for(Map<Integer, Jeu> mapJeu : this.lstJeux.values()) {
			for(Jeu jeu : mapJeu.values()) {
				mapPartie = jeu.getMapPartie(idPartie);
				if(mapPartie != null) {
					break;
				}
			}
			if(mapPartie != null) {
				break;
			}
		}
		
		return mapPartie;
	}
	
	public int getIdPartieLibre() {
		do {
			for(int i=1; i < MAX_PARTIES; i++) {
				if(this.lstParties.get(i) == null) {
					return i;
				}
			}
			//Attente de 30 secondes avant de rechercher à nouveau.
			try {
				Thread.sleep(30000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		while(true);
	}
	
	public void ajouterPartie(Partie partie, TypeEtatPartie etat) {
		System.out.println("AJOUTER_PARTIE_" + etat + " : " + partie);
		System.out.println(partie.getTypeJeu());
		System.out.println(this.lstJeux);
		System.out.println(this.lstJeux.get(partie.getTypeJeu()));
		
		this.lstJeux.get(partie.getTypeJeu()).get(partie.getInfoJeu().getId()).ajouterPartie(partie, etat);
		this.lstParties.put(partie.getId(), partie);
	}
	
	public void retirerPartie(int idPartie) {
		this.getMapPartie(idPartie).remove(idPartie);
		this.lstParties.remove(idPartie);
	}
	
	public void retirerPartie(Partie partie) {
		this.retirerPartie(partie.getId());
	}
	
	public void transfererPartieEnAttenteVersEnCours(int idPartie) {
		Partie partieEnAttente = this.getPartie(idPartie);
		if(partieEnAttente != null) {
			//TODO est-ce que sa dérange si je supprime la partie avant de l'insérer? (Je peux pas faire l'inverse car retirer se retrouverait avec 2 parties identiques
			this.retirerPartie(idPartie);
			this.ajouterPartie(partieEnAttente, TypeEtatPartie.EN_COURS);
		}
	}
	
	public void transfererPartieEnAttenteVersEnCours(Partie partie) {
		this.transfererPartieEnAttenteVersEnCours(partie.getId());
	}
	
	//Peut-être inutile... qu'est-ce qu'on fait quand une partie exige un nombre minimal de joueur et qu'un joueur quitte, entrainant la partie sous le seuil de joueurs?
	public void transfererPartieEnCoursVersEnAttente(int idPartie) {
		Partie partieEnCours = this.getPartie(idPartie);
		if(partieEnCours != null) {
			//TODO est-ce que sa dérange si je supprime la partie avant de l'insérer? (Je peux pas faire l'inverse car retirer se retrouverait avec 2 partie identique
			this.retirerPartie(idPartie);
			this.ajouterPartie(partieEnCours, TypeEtatPartie.EN_ATTENTE);
		}
	}
	
	public void transfererPartieEnCoursVersEnAttente(Partie partie) {
		this.transfererPartieEnCoursVersEnAttente(partie.getId());
	}
	
	public Partie rechercherPartieEnAttente(int idJeu) {
		Partie partieEnAttente = null;
		
		Jeu jeu = this.getJeu(idJeu);
		if(jeu != null) {
			partieEnAttente = jeu.rechercherPartieEnAttente();
		}
		
		return partieEnAttente;
	}
	
	public static <K,V extends Comparable<? super V>>
	SortedSet<Map.Entry<K,V>> entriesSortedByValues(Map<K,V> map) {
	    SortedSet<Map.Entry<K,V>> sortedEntries = new TreeSet<Map.Entry<K,V>>(
	        new Comparator<Map.Entry<K,V>>() {
	            @Override public int compare(Map.Entry<K,V> e1, Map.Entry<K,V> e2) {
	                int res = e1.getValue().compareTo(e2.getValue());
                    return res != 0 ? res : 1;
	            }
	        }
	    );
	    sortedEntries.addAll(map.entrySet());
	    return sortedEntries;
	}
	
	public static <T, E> T getKeyByValue(Map<T, E> map, E value) {
	    for (Entry<T, E> entry : map.entrySet()) {
	        if (value.equals(entry.getValue())) {
	            return entry.getKey();
	        }
	    }
	    return null;
	}
	
	public Jeu getJeu(int idJeu) {
		Jeu jeu = null;
		
		for(Map<Integer, Jeu> mapJeu : this.lstJeux.values()) {
			jeu = mapJeu.get(idJeu);
			if(jeu != null) {
				break;
			}
		}
		
		return jeu;
	}
	
	public Map<TypeJeu, Map<Integer, Jeu>> getLstJeux() {
		return this.lstJeux;
	}

	/**
	 * @return the modele
	 */
	public ModeleServeurPrincipal getModeleServeur() {
		return modele;
	}

	/**
	 * @param modele the modele to set
	 */
	public void setModeleServeur(ModeleServeurPrincipal modele) {
		this.modele = modele;
	}
}
