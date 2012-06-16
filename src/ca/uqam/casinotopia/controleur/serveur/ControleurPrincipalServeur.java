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
import ca.uqam.casinotopia.controleur.ControleurServeur;
import ca.uqam.casinotopia.modele.serveur.ModeleSalleServeur;
import ca.uqam.casinotopia.modele.serveur.ModeleServeurPrincipal;
import ca.uqam.casinotopia.type.TypeEtatPartie;
import ca.uqam.casinotopia.type.TypeJeu;
import ca.uqam.casinotopia.type.TypeJeuArgent;

public final class ControleurPrincipalServeur extends ControleurServeur {
	
	public static final int NUMCONNEXION = 10;
	public static final int MAX_PARTIES = 10000;

	private static ControleurPrincipalServeur instance;
	private static ServerSocket server;
	private ModeleServeurPrincipal modele;
	public static Thread[] thread = new Thread[NUMCONNEXION];
	public static ControleurServeurThread[] serverThread = new ControleurServeurThread[NUMCONNEXION];
	
	private static Boolean actif = true;

	private Map<TypeJeu, Map<Integer, Jeu>> lstJeux;
	private Map<Integer, Partie> lstParties;
	
	private Map<Integer, ModeleSalleServeur> lstSalles;

	private ControleurPrincipalServeur() {
		this.modele = new ModeleServeurPrincipal();

		this.lstParties = new HashMap<Integer, Partie>();
		
		this.lstSalles = new HashMap<Integer, ModeleSalleServeur>();

		this.initJeux();
	}

	public void ecouterConnexions() {
		try {
			InetAddress address = InetAddress.getLocalHost();
			System.out.println("Ton ip est surement : " + address.getHostAddress());
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

	public static ControleurPrincipalServeur getInstance() {
		if (instance == null) {
			instance = new ControleurPrincipalServeur();
		}
		return instance;
	}

	private void initJeux() {
		this.lstSalles = CtrlBD.BD.getAllSalle();
		this.lstJeux = new HashMap<TypeJeu, Map<Integer, Jeu>>();
		this.lstJeux.put(TypeJeu.ROULETTE, new HashMap<Integer, Jeu>());
		this.lstJeux.put(TypeJeu.BLACKJACK, new HashMap<Integer, Jeu>());
		this.lstJeux.put(TypeJeu.MACHINE, new HashMap<Integer, Jeu>());
		
		/* TODO Générer des parties aléatoires?
		Random randomGenerator = new Random();
		for(ModeleSalleServeur salle : this.lstSalles.values()) {
			for(Jeu jeu : salle.getLstJeux().values()) {
				randomGenerator.
			}
		}*/
		
		for(ModeleSalleServeur salle : this.lstSalles.values()) {
			for(Jeu jeu : salle.getLstJeux().values()) {
				this.lstJeux.get(jeu.getType()).put(jeu.getId(), jeu);
			}
		}
	}

	public Partie getPartie(int idPartie) {
		return this.lstParties.get(idPartie);
	}

	public Partie getPartie(Partie partie) {
		return this.getPartie(partie.getId());
	}

	private Map<Integer, Partie> getMapPartie(int idPartie) {
		Map<Integer, Partie> mapPartie = null;
		for (Map<Integer, Jeu> mapJeu : this.lstJeux.values()) {
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

	public int getIdPartieLibre() {
		do {
			for (int i = 1; i < MAX_PARTIES; i++) {
				if (this.lstParties.get(i) == null) {
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

	public void ajouterPartie(Partie partie, TypeEtatPartie etat) {
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

	//TODO Calculer le temps d'attente avant le lancement d'une partie et la lancer si elle atteint un certain seuil d'attente sans avoir le nombre maximale de joueur
	public void transfererPartieEnAttenteVersEnCours(int idPartie) {
		this.transfererPartieEnAttenteVersEnCours(this.getPartie(idPartie));
	}

	public void transfererPartieEnAttenteVersEnCours(Partie partie) {
		//TODO Synchronisation
		if(partie != null && partie.isEnAttente()) {
			// TODO est-ce que sa dérange si je supprime la partie avant de l'insérer?
			// (Je peux pas faire l'inverse car retirer se retrouverait avec 2 parties identiques)
			this.retirerPartie(partie.getId());
			this.ajouterPartie(partie, TypeEtatPartie.EN_COURS);
			
			partie.demarrerPartie();
		}
	}

	// Peut-être inutile... qu'est-ce qu'on fait quand une partie exige un nombre minimal de joueur et qu'un joueur quitte, entrainant la partie sous le seuil de joueurs?
	public void transfererPartieEnCoursVersEnAttente(int idPartie) {
		Partie partieEnCours = this.getPartie(idPartie);
		if (partieEnCours != null) {
			// TODO est-ce que sa dérange si je supprime la partie avant de l'insérer?
			// (Je peux pas faire l'inverse car retirer se retrouverait avec 2 parties identiques)
			this.retirerPartie(idPartie);
			this.ajouterPartie(partieEnCours, TypeEtatPartie.EN_ATTENTE);
		}
	}

	public void transfererPartieEnCoursVersEnAttente(Partie partie) {
		this.transfererPartieEnCoursVersEnAttente(partie.getId());
	}

	public Partie rechercherPartieEnAttente(int idJeu, TypeJeuArgent typeArgent) {
		Partie partieEnAttente = null;

		Jeu jeu = this.getJeu(idJeu);
		if (jeu != null) {
			partieEnAttente = jeu.rechercherPartieEnAttente(typeArgent);
		}

		return partieEnAttente;
	}

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

		for (Map<Integer, Jeu> mapJeu : this.lstJeux.values()) {
			jeu = mapJeu.get(idJeu);
			if (jeu != null) {
				break;
			}
		}

		return jeu;
	}

	public Map<TypeJeu, Map<Integer, Jeu>> getLstJeux() {
		return this.lstJeux;
	}
	
	public ModeleSalleServeur getSalle(int id) {
		return this.lstSalles.get(id);
	}
	
	public void ajouterSalle(ModeleSalleServeur salle) {
		this.lstSalles.put(salle.getId(), salle);
	}
	
	public void retirerSalle(ModeleSalleServeur salle) {
		this.retirerSalle(salle.getId());
	}
	
	public void retirerSalle(int id) {
		this.lstSalles.remove(id);
	}

	/**
	 * @return the modele
	 */
	public ModeleServeurPrincipal getModele() {
		return this.modele;
	}
}