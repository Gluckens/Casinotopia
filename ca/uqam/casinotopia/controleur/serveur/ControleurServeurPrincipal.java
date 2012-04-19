package ca.uqam.casinotopia.controleur.serveur;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import ca.uqam.casinotopia.Jeu;
import ca.uqam.casinotopia.Joueur;
import ca.uqam.casinotopia.Partie;
import ca.uqam.casinotopia.Salle;
import ca.uqam.casinotopia.TypeEtatPartie;
import ca.uqam.casinotopia.TypeJeu;
import ca.uqam.casinotopia.controleur.ControleurServeur;
import ca.uqam.casinotopia.modele.serveur.ModelePartieRouletteServeur;
import ca.uqam.casinotopia.modele.serveur.ModeleServeurPrincipal;

public final class ControleurServeurPrincipal extends ControleurServeur {

	private static ControleurServeurPrincipal instance;
	private ModeleServeurPrincipal modele;
	
	
	
	/*private Map<TypeJeu, Map<Integer, Partie>> lstPartiesEnAttente;
	private Map<TypeJeu, Map<Integer, Partie>> lstPartiesEnCours;*/

	private Map<TypeJeu, Map<Integer, Jeu>> lstJeux;
	private Map<Integer, Partie> lstParties;
	
	public static final int MAX_PARTIES = 10000;
	
	
	private ControleurServeurPrincipal() {
		this.modele = new ModeleServeurPrincipal();
		/*this.lstPartiesEnAttente = new HashMap<TypeJeu, Map<Integer, Partie>>();
		this.lstPartiesEnCours = new HashMap<TypeJeu, Map<Integer, Partie>>();*/
		
		
		this.lstParties = new HashMap<Integer, Partie>();
		
		this.initJeux();
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
		
		/*Map<Jeu, Map<TypeEtatPartie, Map<Integer, Partie>>>[] test;
		test = (HashMap<Jeu, Map<TypeEtatPartie, Map<Integer, Partie>>>[]) new HashMap[3];*/
		
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
	
	/*public static <T, E> T[] getArrKeys(Map<T, E> map) {
		ArrayList<T> lstKeys = new ArrayList<T>();
		
		for(Map.Entry<T, E> entry : map.entrySet()) {
			lstKeys.add(entry.getKey());
		}
		
		T[] arrKeys;
		
		arrKeys = lstKeys.toArray((T[])Array.newInstance(arrKeys.getClass(), map.keySet().size()));
		
		return arrKeys;
	}*/
	
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
		
		/*System.out.println(this.lstJeux.get(partie.getTypeJeu()).get(partie.getInfoJeu()));
		System.out.println(this.lstJeux.get(partie.getTypeJeu()).get(partie.getInfoJeu()).get(TypeEtatPartie.EN_ATTENTE));
		this.lstJeux.get(partie.getTypeJeu()).get(partie.getInfoJeu()).get(TypeEtatPartie.EN_ATTENTE).put(partie.getId(), partie);
		this.lstParties.put(partie.getId(), partie);*/
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
	                //return e1.getValue().compareTo(e2.getValue());
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
	
	/*public Partie getPartie(int idPartie) {
		Partie partie;
		partie = this.getPartieEnAttente(idPartie);
		if(partie == null) {
			partie = this.getPartieEnCours(idPartie);
		}
		
		return partie;
	}
	
	private Partie getPartieEnAttente(int idPartie) {
		//return this.lstPartiesEnAttente.get(idPartie);
		Partie partieEnAttente = null;
		for(Map<Integer, Partie> lstPartie : this.lstPartiesEnAttente.values()) {
			partieEnAttente = lstPartie.get(idPartie);
			if(partieEnAttente != null) {
				break;
			}
		}
		
		return partieEnAttente;
	}
	
	private Partie getPartieEnCours(int idPartie) {
		Partie partieEnCours = null;
		for(Map<Integer, Partie> lstPartie : this.lstPartiesEnCours.values()) {
			partieEnCours = lstPartie.get(idPartie);
			if(partieEnCours != null) {
				break;
			}
		}
		
		return partieEnCours;
	}
	
	public void ajouterPartieEnAttente(TypeJeu type, Partie partie) {
		if(!this.lstPartiesEnAttente.containsKey(type)) {
			this.lstPartiesEnAttente.put(type, new HashMap<Integer, Partie>());
		}
		this.lstPartiesEnAttente.get(type).put(partie.getId(), partie);
	}
	
	public void retirerPartieEnAttente(int idPartie) {
		for(Map<Integer, Partie> lstPartie : this.lstPartiesEnAttente.values()) {
			if(lstPartie.remove(idPartie) != null) {
				break;
			}
		}
	}
	
	public void retirerPartieEnAttente(TypeJeu type, int idPartie) {
		this.lstPartiesEnAttente.get(type).remove(idPartie);
	}
	
	public void ajouterPartieEnCours(TypeJeu type, Partie partie) {
		if(!this.lstPartiesEnCours.containsKey(type)) {
			this.lstPartiesEnCours.put(type, new HashMap<Integer, Partie>());
		}
		this.lstPartiesEnCours.get(type).put(partie.getId(), partie);
	}
	
	public void retirerPartieEnCours(int idPartie) {
		for(Map<Integer, Partie> lstPartie : this.lstPartiesEnCours.values()) {
			if(lstPartie.remove(idPartie) != null) {
				break;
			}
		}
	}
	
	public void retirerPartieEnCours(TypeJeu type, int idPartie) {
		this.lstPartiesEnCours.get(type).remove(idPartie);
	}
	
	public void transfererPartieEnAttenteVersEnCours(int idPartie) {
		Partie partieEnAttente = this.getPartieEnAttente(idPartie);
		if(partieEnAttente != null) {
			this.ajouterPartieEnCours(partieEnAttente.getTypeJeu(), partieEnAttente);
			this.retirerPartieEnAttente(idPartie);
		}
	}
	
	//Peut-être inutile... qu'est-ce qu'on fait quand une partie exige un nombre minimal de joueur et qu'un joueur quitte, entrainant la partie sous le seuil de joueurs?
	public void transfererPartieEnCoursVersEnAttente(int idPartie) {
		Partie partieEnCours = this.getPartieEnCours(idPartie);
		if(partieEnCours != null) {
			this.ajouterPartieEnAttente(partieEnCours.getTypeJeu(), partieEnCours);
			this.retirerPartieEnCours(idPartie);
		}
	}
	
	//Quelle est la politique de recherche de partie en cours? On cherche celle avec le moins de joueur manquant avant d'attendre le nombre maximal?
	public Partie rechercherPartieEnAttente(TypeJeu type) {
		//TODO Cette fonction sera appelé lorsqu'un joueur veut jouer à un jeu.
		//Elle devra regarder dans la liste de partie s'il y en a une du même type de jeu demandé, et que le nombre maximale de joueur n'est pas atteint
		//  (possible? quand le dernier joueur entre dans une partie en attente, elle ne s'en va directement dans partie en cours?)
		
		System.out.println("PartiesEnAttente : " + this.lstPartiesEnAttente.get(type));
		
		if(this.lstPartiesEnAttente.get(type) != null) {
			SortedSet<Entry<Integer, Partie>> lstPartiesSorted = entriesSortedByValues(this.lstPartiesEnAttente.get(type));
		
			System.out.println("PartiesEnAttenteSORTED : " + lstPartiesSorted);
			
			return lstPartiesSorted.first().getValue();
		}
		
		return null;
	}
	
	static <K,V extends Comparable<? super V>>
	SortedSet<Map.Entry<K,V>> entriesSortedByValues(Map<K,V> map) {
	    SortedSet<Map.Entry<K,V>> sortedEntries = new TreeSet<Map.Entry<K,V>>(
	        new Comparator<Map.Entry<K,V>>() {
	            @Override public int compare(Map.Entry<K,V> e1, Map.Entry<K,V> e2) {
	                //return e1.getValue().compareTo(e2.getValue());
	                int res = e1.getValue().compareTo(e2.getValue());
                    return res != 0 ? res : 1;
	            }
	        }
	    );
	    sortedEntries.addAll(map.entrySet());
	    return sortedEntries;
	}*/
}
