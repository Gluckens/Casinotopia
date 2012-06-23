package ca.uqam.casinotopia.bd;

import java.awt.Rectangle;

import ca.uqam.casinotopia.Jeu;
import ca.uqam.casinotopia.modele.serveur.ModeleClientServeur;
import ca.uqam.casinotopia.modele.serveur.ModeleSalleServeur;
import ca.uqam.casinotopia.type.TypeJeu;

public abstract class TestBD {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CtrlBD.BD.initDatabase();
		
		creationDonneesTest();
		
		System.out.println("FIN DES TESTS");
	}
	
	public static void creationDonneesTest() {
		System.out.println("Création des données de test...");
		
		ModeleClientServeur client = new ModeleClientServeur("username1", "mdp1", "prenom1", "nom1", java.sql.Date.valueOf("1988-03-01"), "courriel1", 1001, "/img/chip_5.png");
		CtrlBD.BD.ajouterClient(client);
		
		ModeleClientServeur client2 = new ModeleClientServeur("username2", "mdp2", "prenom2", "nom2", java.sql.Date.valueOf("1988-03-02"), "courriel2", 1002, "/img/chip_10.png");
		CtrlBD.BD.ajouterClient(client2);
		
		ModeleClientServeur client3 = new ModeleClientServeur("username3", "mdp3", "prenom3", "nom3", java.sql.Date.valueOf("1988-03-03"), "courriel3", 1003, "/img/chip_25.png");
		CtrlBD.BD.ajouterClient(client3);
		
		CtrlBD.BD.ajouterAmiClient(client, client3);
		CtrlBD.BD.ajouterAmiClient(client, client2);
		
		@SuppressWarnings("unused")
		ModeleClientServeur client4 = CtrlBD.BD.authentifierClient("username1", "mdp1");
		
		ModeleSalleServeur salle = new ModeleSalleServeur(-1, "MEGAFUN");
		CtrlBD.BD.ajouterSalle(salle);
		CtrlBD.BD.ajouterJeu(new Jeu(1, salle.getId(), "nom1", "description1", "reglesJeu1", new Rectangle(70, 70, 180, 104), 1, 4, TypeJeu.ROULETTE));
		CtrlBD.BD.ajouterJeu(new Jeu(2, salle.getId(), "nom2", "description2", "reglesJeu2", new Rectangle(370, 70, 180, 104), 2, 4, TypeJeu.ROULETTE));
		CtrlBD.BD.ajouterJeu(new Jeu(3, salle.getId(), "nom3", "description3", "reglesJeu3", new Rectangle(70, 270, 180, 104), 3, 4, TypeJeu.ROULETTE));
		CtrlBD.BD.ajouterJeu(new Jeu(4, salle.getId(), "nom4", "description4", "reglesJeu4", new Rectangle(370, 320, 180, 104), 4, 4, TypeJeu.ROULETTE));
		CtrlBD.BD.ajouterJeu(new Jeu(5, salle.getId(), "nom5", "description5", "reglesJeu5", new Rectangle(85, 450, 150, 117), 1, 1, TypeJeu.MACHINE));
	}
}