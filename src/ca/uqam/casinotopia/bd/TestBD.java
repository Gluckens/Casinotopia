package ca.uqam.casinotopia.bd;

import ca.uqam.casinotopia.modele.serveur.ModeleClientServeur;

public abstract class TestBD {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*ModeleClientServeur client = new ModeleClientServeur("username1", "mdp1", "prenom1", "nom1", java.sql.Date.valueOf("1988-03-01"), "courriel1", 1000);
		CtrlBD.BD.ajouterClient(client);
		
		ModeleClientServeur client2 = new ModeleClientServeur("username2", "mdp2", "prenom2", "nom2", java.sql.Date.valueOf("1988-03-02"), "courriel2", 1000);
		CtrlBD.BD.ajouterClient(client2);
		
		ModeleClientServeur client3 = new ModeleClientServeur("username3", "mdp3", "prenom3", "nom3", java.sql.Date.valueOf("1988-03-03"), "courriel3", 1000);
		CtrlBD.BD.ajouterClient(client3);
		
		CtrlBD.BD.ajouterAmiClient(client, client3);
		CtrlBD.BD.ajouterAmiClient(client, client2);*/
		
		ModeleClientServeur client4 = CtrlBD.BD.authentifierClient("username1", "mdp1");
		
		/*ModeleClientServeur client2 = CtrlBD.BD.authentifierClient("username1", "mdp1");
		
		ModeleClientServeur client3 = CtrlBD.BD.getClientByIdUtilisateur(client.getIdUtilisateur());
		
		ModeleClientServeur client4 = CtrlBD.BD.getClientById(client.getId());*/
		
		
		System.out.println("FIN DES TESTS");
	}
}
