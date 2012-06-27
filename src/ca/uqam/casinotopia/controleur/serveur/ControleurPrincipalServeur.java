package ca.uqam.casinotopia.controleur.serveur;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedSet;
import java.util.TreeSet;

import ca.uqam.casinotopia.Jeu;
import ca.uqam.casinotopia.Partie;

import java.io.IOException;
import java.net.BindException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import ca.uqam.casinotopia.bd.CtrlBD;
import ca.uqam.casinotopia.modele.serveur.ModeleSalleServeur;
import ca.uqam.casinotopia.modele.serveur.ModeleServeurPrincipal;
import ca.uqam.casinotopia.type.TypeEtatPartie;
import ca.uqam.casinotopia.type.TypeJeu;
import ca.uqam.casinotopia.type.TypeJeuArgent;

/**
 * Controleur principal du serveur.
 * C'est ici que les objets globaux sont stockés (les jeux, les parties, les salles)
 */
//public final class ControleurPrincipalServeur extends ControleurServeur {
public enum ControleurPrincipalServeur{
	INSTANCE;
	
	/**
	 * nombre de connexions simultanées
	 */
	public static final int NUMCONNEXION = 10;
	
	/**
	 * nombre de parties simultanées
	 */
	public static final int MAX_PARTIES = 10000;

	/**
	 * Instance de singleton
	 */
	//private static ControleurPrincipalServeur instance;
	
	/**
	 * Socket du serveur
	 */
	private static ServerSocket server;
	
	/**
	 * Modèle du serveur
	 */
	private static ModeleServeurPrincipal modele;
	
	/**
	 * Threads des clients
	 */
	public static Thread[] thread = new Thread[NUMCONNEXION];
	public static ControleurServeurThread[] serverThread = new ControleurServeurThread[NUMCONNEXION];
	
	private static Boolean actif = true;

	/**
	 * Liste des jeux
	 */
	private static Map<TypeJeu, Map<Integer, Jeu>> lstJeux;
	
	/**
	 * Liste des parties
	 */
	private static Map<Integer, Partie> lstParties;
	
	/**
	 * Liste des salles
	 */
	private static Map<Integer, ModeleSalleServeur> lstSalles;

	//ControleurPrincipalServeur() {
	static {
		modele = new ModeleServeurPrincipal();

		lstParties = new HashMap<Integer, Partie>();
		
		lstSalles = new HashMap<Integer, ModeleSalleServeur>();

		initJeux();
	}

	/**
	 * écoute sur un port en attente de connexion de client et leur affecte un thread
	 */
	public void ecouterConnexions() {
		try {
			InetAddress address = InetAddress.getLocalHost();
			System.out.println("Ton ip : " + address.getHostAddress());
		} catch (UnknownHostException e) {
			System.out.println("Could not find this computer's address.");
		}
		try {
			System.out.println("création du server");
			server = new ServerSocket(7777);
			System.out.println("server démarré");
			while (actif) {
				System.out.println("ATTENTE DE NOUVELLES CONNEXIONS...");
				Socket skt = server.accept();
				System.out.println("NOUVELLE CONNEXION RECUE");
				
				for (int i = 0; i < NUMCONNEXION; i++) {
					if (thread[i] != null && !thread[i].isAlive()) {
						thread[i] = null;
					}
					if (thread[i] == null) {
						serverThread[i] = new ControleurServeurThread(skt, i);
						thread[i] = new Thread(serverThread[i]);
						thread[i].start();
						System.err.println("client " + i + " connecté");
						break;
					}
				}
				// indiquer au client que le serveur est plein
			}
		} catch (BindException e) {
			System.err.println("Il y a déjà un serveur sur le même port");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*public static ControleurPrincipalServeur getInstance() {
		if (instance == null) {
			instance = new ControleurPrincipalServeur();
		}
		return instance;
	}*/

	/**
	 * Initialiser les jeux en fonction des données de la BD
	 */
	private static void initJeux() {
		lstSalles = CtrlBD.BD.getAllSalle();
		lstJeux = new HashMap<TypeJeu, Map<Integer, Jeu>>();
		lstJeux.put(TypeJeu.ROULETTE, new HashMap<Integer, Jeu>());
		lstJeux.put(TypeJeu.BLACKJACK, new HashMap<Integer, Jeu>());
		lstJeux.put(TypeJeu.MACHINE, new HashMap<Integer, Jeu>());
		
		/* TODO Générer des parties aléatoires?
		Random randomGenerator = new Random();
		for(ModeleSalleServeur salle : this.lstSalles.values()) {
			for(Jeu jeu : salle.getLstJeux().values()) {
				randomGenerator.
			}
		}*/
		
		for(ModeleSalleServeur salle : lstSalles.values()) {
			for(Jeu jeu : salle.getLstJeux().values()) {
				lstJeux.get(jeu.getType()).put(jeu.getId(), jeu);
			}
		}
	}

	/**
	 * Récupérer une partie à partir de son id
	 * 
	 * @param idPartie L'id de la partie à récupérer
	 * @return La partie demandée
	 */
	public static Partie getPartie(int idPartie) {
		return lstParties.get(idPartie);
	}

	/**
	 * Récupérer une partie à partir d'une instance de partie
	 * 
	 * @param partie La partie à récupérer
	 * @return La partie demandée
	 */
	public static Partie getPartie(Partie partie) {
		return getPartie(partie.getId());
	}

	/**
	 * Récupérer le map de la partie (Map<Id de partie, Objet partie>)
	 * 
	 * @param idPartie l'id de la partie
	 * @return Le map de la partie demandée
	 */
	private static Map<Integer, Partie> getMapPartie(int idPartie) {
		Map<Integer, Partie> mapPartie = null;
		for (Map<Integer, Jeu> mapJeu : lstJeux.values()) {
			for (Jeu jeu : mapJeu.values()) {
				mapPartie = jeu.getMapPartie(idPartie);
				if (mapPartie != null) {
					break;
				}
			}
			if (mapPartie != null) {
				break;
			}
		}

		return mapPartie;
	}

	/**
	 * Récupérer un id de partie libre
	 * 
	 * @return un id de partie libre
	 */
	public static int getIdPartieLibre() {
		do {
			for (int i = 1; i < MAX_PARTIES; i++) {
				if (lstParties.get(i) == null) {
					return i;
				}
			}
			//TODO Attente de 30 secondes avant de rechercher à nouveau.
			try {
				Thread.sleep(30000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} while (true);
	}

	/**
	 * Ajouter une partie sur le serveur
	 * 
	 * @param partie La partie à ajouter
	 * @param etat L'état de la nouvelle partie
	 */
	public static void ajouterPartie(Partie partie, TypeEtatPartie etat) {
		lstJeux.get(partie.getTypeJeu()).get(partie.getInfoJeu().getId()).ajouterPartie(partie, etat);
		lstParties.put(partie.getId(), partie);
	}

	/**
	 * Retirer une partie du serveur par son id
	 * 
	 * @param idPartie L'id de la partie à retirer
	 */
	public static void retirerPartie(int idPartie) {
		getMapPartie(idPartie).remove(idPartie);
		lstParties.remove(idPartie);
	}

	/**
	 * Retirer une partie du serveur par son instance
	 * 
	 * @param partie L'instance de la partie à retirer
	 */
	public static void retirerPartie(Partie partie) {
		retirerPartie(partie.getId());
	}

	/**
	 * Démarrer une partie par son id
	 * 
	 * @param idPartie L'id de la partie à démarrer
	 */
	public static void transfererPartieEnAttenteVersEnCours(int idPartie) {
		transfererPartieEnAttenteVersEnCours(getPartie(idPartie));
	}

	/**
	 * Démarrer une partie par son instance
	 * 
	 * @param partie L'instance de la partie à démarrer
	 */
	public static void transfererPartieEnAttenteVersEnCours(Partie partie) {
		//TODO Synchronisation
		if(partie != null && partie.isEnAttente()) {
			retirerPartie(partie.getId());
			ajouterPartie(partie, TypeEtatPartie.EN_COURS);
			
			partie.demarrerPartie();
		}
	}

	/**
	 * Arrêter une partie par son id
	 * 
	 * @param idPartie L'id de la partie à arrêter
	 */
	// Peut-être inutile... qu'est-ce qu'on fait quand une partie exige un nombre minimal de joueur et qu'un joueur quitte, entrainant la partie sous le seuil de joueurs?
	public static void transfererPartieEnCoursVersEnAttente(int idPartie) {
		Partie partieEnCours = getPartie(idPartie);
		if (partieEnCours != null) {
			// TODO est-ce que sa dérange si je supprime la partie avant de l'insérer?
			// (Je peux pas faire l'inverse car retirer se retrouverait avec 2 parties identiques)
			retirerPartie(idPartie);
			ajouterPartie(partieEnCours, TypeEtatPartie.EN_ATTENTE);
		}
	}

	/**
	 * Arrêter une partie par son instance
	 * 
	 * @param partie L'instance de la partie à arrêter
	 */
	public static void transfererPartieEnCoursVersEnAttente(Partie partie) {
		transfererPartieEnCoursVersEnAttente(partie.getId());
	}

	/**
	 * Rechercher une partie en attente pour le jeu demandé et pour le type de jeu d'argent demandé
	 * 
	 * @param idJeu L'id du jeu pour lequel on recherche une partie en attente
	 * @param typeArgent Le type de jeu d'argent
	 * @return Une partie en attente, null si aucune
	 */
	public static Partie rechercherPartieEnAttente(int idJeu, TypeJeuArgent typeArgent) {
		Partie partieEnAttente = null;

		Jeu jeu = getJeu(idJeu);
		if (jeu != null) {
			partieEnAttente = jeu.rechercherPartieEnAttente(typeArgent);
		}

		return partieEnAttente;
	}

	/**
	 * Fonction générique pour trier un map
	 * 
	 * @param map Le map à trier
	 * @return Le map trié sous forme de SortedSet
	 */
	public static <K, V extends Comparable<? super V>> SortedSet<Map.Entry<K, V>> entriesSortedByValues(Map<K, V> map) {
		SortedSet<Map.Entry<K, V>> sortedEntries = new TreeSet<Map.Entry<K, V>>(new Comparator<Map.Entry<K, V>>() {
			@Override
			public int compare(Map.Entry<K, V> e1, Map.Entry<K, V> e2) {
				int res = e1.getValue().compareTo(e2.getValue());
				return res != 0 ? res : 1;
			}
		});
		sortedEntries.addAll(map.entrySet());
		return sortedEntries;
	}

	/**
	 * Fonction générique pour rechercher une key de map par sa valeur
	 * 
	 * @param map Le map sur lequel faire la recherche
	 * @param value La valeur recherchée
	 * @return La key correspondant à la valeur, null sinon
	 */
	public static <T, E> T getKeyByValue(Map<T, E> map, E value) {
		for (Entry<T, E> entry : map.entrySet()) {
			if (value.equals(entry.getValue())) {
				return entry.getKey();
			}
		}
		return null;
	}

	/**
	 * Récupérer un jeu par son id
	 * 
	 * @param idJeu l'id du jeu à récupérer
	 * @return Le jeu demandé
	 */
	public static Jeu getJeu(int idJeu) {
		Jeu jeu = null;

		for (Map<Integer, Jeu> mapJeu : lstJeux.values()) {
			jeu = mapJeu.get(idJeu);
			if (jeu != null) {
				break;
			}
		}

		return jeu;
	}

	public static Map<TypeJeu, Map<Integer, Jeu>> getLstJeux() {
		return lstJeux;
	}
	
	/**
	 * Récupérer une salle par son id
	 * 
	 * @param id L'id de la salle à récupérer
	 * @return La salle demandée
	 */
	public static ModeleSalleServeur getSalle(int id) {
		return lstSalles.get(id);
	}
	
	public static void ajouterSalle(ModeleSalleServeur salle) {
		lstSalles.put(salle.getId(), salle);
	}
	
	public static void retirerSalle(ModeleSalleServeur salle) {
		retirerSalle(salle.getId());
	}
	
	public static void retirerSalle(int id) {
		lstSalles.remove(id);
	}
	
	public static ModeleServeurPrincipal getModele() {
		return modele;
	}
}