package ca.uqam.casinotopia.bd;

import ca.uqam.casinotopia.*;
import ca.uqam.casinotopia.modele.serveur.ModeleClientServeur;

import java.sql.*;
import java.util.Vector;
import java.util.logging.*;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

/**
 * 
 * @author Alexei
 */
public class ControleurBD {

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {

		ModeleClientServeur unCl = new ModeleClientServeur();
		unCl.setNomUtilisateur("CassieCLT");
		// unCl.setMotDePasse("Cassie1");
		ajouterUtilisateur(unCl);

		System.out.println("utilisateur id inseré : " + unCl.getIdUtilisateur());

		unCl.setNom("Lavrov");
		unCl.setPrenom("Cassandre");
		java.sql.Date jsqlD = java.sql.Date.valueOf("2012-03-14");
		unCl.setDateNaissance(jsqlD);
		unCl.setCourriel("cassie@gmail.com");
		unCl.setSolde(1000);
		unCl.setPourcentageGlobal(25);
		ajouterClient(unCl);

		System.out.println("client id inseré : " + unCl.getId());

		Avatar unAvatar = new Avatar();
		unAvatar.setPathImage("cassie.jpeg");
		unAvatar.setTexte("Yooo");

		ajouterAvatar(unAvatar, unCl);
		unCl.setAvatar(unAvatar);
		System.out.println("avatar id inseré : " + unAvatar.getId());

		Fondation uneF = new Fondation();
		uneF.setNom("Fondation test");
		uneF.setDescription("un test est important");
		ajouterFondation(uneF);
		System.out.println("fondation id inseré : " + uneF.getId());

		DonUniqueClient dU = new DonUniqueClient();
		dU.setClient_(unCl);
		dU.setFondation(uneF);
		dU.setMontant(100);
		ajouterDonUnique(dU);
		System.out.println("don unique id inseré : " + dU.getId());

		PartageGainsClient pG = new PartageGainsClient();
		pG.setClient(unCl);
		pG.setFondation(uneF);
		pG.setPourcentage(100);
		ajouterPartageGain(pG);

		Vector<PartageGainsClient> v = unCl.getPartageGains();
		v.add(pG);
		unCl.setPartageGains(v);

		// --------------------

		ModeleClientServeur unCl2 = new ModeleClientServeur();
		unCl2.setNomUtilisateur("Mariue");
		// unCl2.setMotDePasse("Marius1");
		ajouterUtilisateur(unCl2);

		System.out.println("utilisateur id inseré : " + unCl2.getIdUtilisateur());

		unCl2.setNom("Boucher");
		unCl2.setPrenom("Marius");
		java.sql.Date jsqlD2 = java.sql.Date.valueOf("2000-01-10");
		unCl2.setDateNaissance(jsqlD2);
		unCl2.setCourriel("marius@gmail.com");
		unCl2.setSolde(10);
		unCl2.setPourcentageGlobal(2);
		ajouterClient(unCl2);

		System.out.println("client id inseré : " + unCl2.getId());

		Avatar unAvatar2 = new Avatar();
		unAvatar2.setPathImage("marius.jpeg");
		unAvatar2.setTexte("Miauuuuuu Miauuuuuuu");

		ajouterAvatar(unAvatar2, unCl2);
		unCl2.setAvatar(unAvatar2);
		System.out.println("avatar id inseré : " + unAvatar2.getId());

		ajouterAmiClient(unCl, unCl2);
		ajouterAmiClient(unCl2, unCl);

		supprimerUtilisateur(unCl2);

		ModeleClientServeur clAlex = getClient("AlexeiCLT", "AlexeiCLT1");
		System.out.println("client select : " + clAlex);

	}

	public static Connection connecterBD() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(ControleurBD.class.getName()).log(Level.SEVERE, null, ex);
		}

		Connection conn;
		try {
			//conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "SYSTEM", "admin");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "BD", "admin");

		} catch (Exception x) {
			System.out.println("Couldn't get connection!");
			conn = null;
		}
		return conn;
	}

	public static ModeleClientServeur getClient(String identifiant, String motPasse) {
		ModeleClientServeur unClient = new ModeleClientServeur();

		Connection conn = connecterBD();

		try {
			Statement stmt = conn.createStatement();
			ResultSet rsUtilisateur = stmt.executeQuery("SELECT * FROM USERS.UTILISATEUR WHERE IDENTIFIANT = '" + identifiant + "' AND MOTPASSE = '"
					+ motPasse + "'");

			if (rsUtilisateur.next()) {
				unClient = getClientByIdUtilisateur(rsUtilisateur.getInt("id"));
				System.out.println("id client : " + rsUtilisateur.getInt("id"));
				unClient.setNomUtilisateur(identifiant);
				// unClient.setMotDePasse(motPasse);
				unClient.setIdUtilisateur(rsUtilisateur.getInt("id"));
			}
		} catch (SQLException ex) {
			System.out.println("erreur Client");
		} finally {
		    try { conn.close(); } catch (Exception e) { }
		}

		return unClient;
	}

	public static ModeleClientServeur getClientByIdUtilisateur(int id) {
		/*Connection conn = connecterBD();
		
		ModeleClientServeur client = null;
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rsClient = stmt.executeQuery("SELECT * FROM SYSTEM.CLIENT INNER JOIN SYSTEM.UTILISATEUR WHERE idutilisateur = " + id);

			if (rsClient.next()) {
				client = new ModeleClientServeur(
					rsClient.getInt("UTILISATEUR.id"),
					rsClient.getString("identifiant"),
					rsClient.getInt("CLIENT.id"),
					rsClient.getString("prenom"),
					rsClient.getString("nom"),
					rsClient.getDate("nom"),
					rsClient.getString("datenaissance"),
					rsClient.getInt("solde")
				);
			}
		} catch (SQLException ex) {
			System.out.println("erreur Client");
		} finally {
		    try { conn.close(); } catch (Exception e) { }
		}
		
		return client;*/
		
		
		ModeleClientServeur unClient = new ModeleClientServeur();

		Connection conn = connecterBD();

		try {
			Statement stmt = conn.createStatement();
			ResultSet rsClient = stmt.executeQuery("SELECT * FROM USERS.CLIENT WHERE idutilisateur = " + id);

			if (rsClient.next()) {
				unClient.setId(rsClient.getInt("id"));
				unClient.setPrenom(rsClient.getString("prenom"));
				unClient.setNom(rsClient.getString("nom"));
				unClient.setCourriel(rsClient.getString("courriel"));
				unClient.setSolde(rsClient.getInt("solde"));
				unClient.setPourcentageGlobal(rsClient.getInt("prcglobal"));
			}
		} catch (SQLException ex) {
			System.out.println("erreur Client");
		} finally {
		    try { conn.close(); } catch (Exception e) { }
		}

		return unClient;
	}

	public static Fondation getFondationById(int idFondation) {
		Fondation uneFondation = new Fondation();

		Connection dbConnection = connecterBD();

		try {
			Statement stmt = dbConnection.createStatement();
			ResultSet rsFondation = stmt.executeQuery("SELECT * FROM USERS.FONDATION WHERE ID = " + idFondation);

			if (rsFondation.next()) {
				uneFondation.setId(idFondation);
				uneFondation.setNom(rsFondation.getString("nom"));
				uneFondation.setDescription(rsFondation.getString("description"));
			}

			dbConnection.close();

		} catch (SQLException ex) {
			System.out.println("erreur");
		}

		return uneFondation;
	}

	public static Vector<PartageGainsClient> getpartageGainsClient(int idClient) {

		Connection dbConnection = connecterBD();
		Vector<PartageGainsClient> partageGains = new Vector<PartageGainsClient>();

		try {
			Statement stmt = dbConnection.createStatement();
			ResultSet rsPartageGain = stmt.executeQuery("SELECT * FROM USERS.PARTAGEGAIN WHERE IDCLIENT = " + idClient);

			while (rsPartageGain.next()) {
				Fondation uneFondation = getFondationById(rsPartageGain.getInt("idfondation"));
				ModeleClientServeur unClient = getClientById(idClient);
				PartageGainsClient unPartageGain = new PartageGainsClient();

				unPartageGain.setClient(unClient);
				unPartageGain.setFondation(uneFondation);
				unPartageGain.setPourcentage(rsPartageGain.getInt("pourcentage"));

				partageGains.add(unPartageGain);
			}

			dbConnection.close();

		} catch (SQLException ex) {
			System.out.println("erreur");
		}

		return partageGains;
	}

	public static Utilisateur getUtilisateurById(int id) {
		Utilisateur unUtilisateur = new Utilisateur();

		Connection dbConnection = connecterBD();

		try {
			Statement stmt = dbConnection.createStatement();
			ResultSet rsUtilisateur = stmt.executeQuery("SELECT * FROM USERS.Utilisateur WHERE id = " + id);

			if (rsUtilisateur.next()) {
				unUtilisateur.setIdUtilisateur(id);
				unUtilisateur.setNomUtilisateur(rsUtilisateur.getString("identifiant"));
				// unUtilisateur.setMotDePasse(rsUtilisateur.getString("motPasse"));

			}

			dbConnection.close();
		} catch (SQLException ex) {
			System.out.println("erreur Client");
		}

		return unUtilisateur;
	}

	public static ModeleClientServeur getClientById(int id) {
		ModeleClientServeur unClient = new ModeleClientServeur();

		Connection dbConnection = connecterBD();

		try {
			Statement stmt = dbConnection.createStatement();
			ResultSet rsClient = stmt.executeQuery("SELECT * FROM USERS.CLIENT WHERE id = " + id);

			if (rsClient.next()) {
				unClient.setIdUtilisateur(id);
				unClient.setPrenom(rsClient.getString("prenom"));
				unClient.setNom(rsClient.getString("nom"));
				unClient.setCourriel(rsClient.getString("courriel"));
				unClient.setSolde(rsClient.getInt("solde"));
				unClient.setPourcentageGlobal(rsClient.getInt("prcglobal"));
				unClient.setDateNaissance(rsClient.getDate("dateNaissance"));
			}

			dbConnection.close();
		} catch (SQLException ex) {
			System.out.println("erreur Client");
		}

		return unClient;
	}

	public static ListeAmis getListAmis(int idClient) {

		Connection dbConnection = connecterBD();
		ListeAmis listeAmisClient = new ListeAmis();

		try {
			Statement stmt = dbConnection.createStatement();
			ResultSet rsListeAmis = stmt.executeQuery("SELECT * FROM USERS.amiClient WHERE IDCLIENT = " + idClient);

			Vector<ModeleClientServeur> listeClients = new Vector<ModeleClientServeur>();
			while (rsListeAmis.next()) {

				listeClients.add(getClientById(rsListeAmis.getInt("idAmi")));
			}
			listeAmisClient.setClients(listeClients);

			dbConnection.close();

		} catch (SQLException ex) {
			System.out.println("erreur");
		}

		return listeAmisClient;
	}

	public static Avatar getAvatarByClientId(int id) {
		Avatar unAvatar = new Avatar();

		Connection dbConnection = connecterBD();

		try {
			Statement stmt = dbConnection.createStatement();
			ResultSet rsAvatar = stmt.executeQuery("SELECT * FROM USERS.AVATAR WHERE idClient = " + id);

			if (rsAvatar.next()) {

				unAvatar.setId(id);
				unAvatar.setPathImage(rsAvatar.getString("nomFichier"));
				unAvatar.setTexte(rsAvatar.getString("nomFichier"));
			}

			dbConnection.close();
		} catch (SQLException ex) {
			System.out.println("erreur Client");
		}

		return unAvatar;
	}

	public static Vector<DonUniqueClient> getDonsUniquesClient(int idClient) {

		Connection dbConnection = connecterBD();
		Vector<DonUniqueClient> donsUniques = new Vector<DonUniqueClient>();

		try {
			Statement stmt = dbConnection.createStatement();
			ResultSet rsDonUnique = stmt.executeQuery("SELECT * FROM USERS.DONUNIQUE WHERE IDCLIENT = " + idClient);

			while (rsDonUnique.next()) {
				Fondation uneFondation = getFondationById(rsDonUnique.getInt("idfondation"));
				ModeleClientServeur unClient = getClientById(rsDonUnique.getInt("idClient"));

				DonUniqueClient unDonUnique = new DonUniqueClient();
				unDonUnique.setClient_(unClient);
				unDonUnique.setFondation(uneFondation);
				unDonUnique.setDateDon(rsDonUnique.getDate("dateDon"));
				unDonUnique.setId(rsDonUnique.getInt("id"));
				unDonUnique.setMontant((int) (rsDonUnique.getDouble("montant") * 100));

				donsUniques.add(unDonUnique);
			}

			dbConnection.close();

		} catch (SQLException ex) {
			System.out.println("erreur");
		}

		return donsUniques;
	}

	public static boolean ajouterUtilisateur(Utilisateur unUtilisateur) {
		boolean ajoutReussi = false;
		try {
			Connection conn = connecterBD();
			String query = "BEGIN insert into USERS.Utilisateur (identifiant, motPasse, typeCompte) VALUES ('" + unUtilisateur.getNomUtilisateur()
					+ /* "', '" + unUtilisateur.getMotDePasse() + */"' , 'CLT') returning id into ?; END;  ";
			OracleCallableStatement cs = (OracleCallableStatement) conn.prepareCall(query);
			cs.registerOutParameter(1, OracleTypes.NUMBER);
			cs.execute();
			System.out.println(cs.getInt(1));
			unUtilisateur.setIdUtilisateur(cs.getInt(1));
			ajoutReussi = true;

		} catch (SQLException ex) {
			Logger.getLogger(ControleurBD.class.getName()).log(Level.SEVERE, null, ex);
		}
		return ajoutReussi;
	}

	public static boolean ajouterClient(ModeleClientServeur unClient) {
		boolean ajoutReussi = false;
		
		Connection conn = connecterBD();
		
		try {
			String query = "BEGIN insert into USERS.Client (idUtilisateur, prenom, nom, dateNaissance, courriel, solde, prcGlobal) VALUES ('"
					+ unClient.getIdUtilisateur() + "', '" + unClient.getPrenom() + "' , '" + unClient.getNom() + "' , '" + unClient.getDateNaissance()
					+ "' ,'" + unClient.getCourriel() + "' ," + unClient.getSolde() + " , " + unClient.getPourcentageGlobal() + ") returning id into ?; END;  ";
			OracleCallableStatement cs = (OracleCallableStatement) conn.prepareCall(query);
			cs.registerOutParameter(1, OracleTypes.NUMBER);
			cs.execute();
			System.out.println(cs.getInt(1));
			unClient.setId(cs.getInt(1));
			ajoutReussi = true;

		} catch (SQLException ex) {
			Logger.getLogger(ControleurBD.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
		    try { conn.close(); } catch (Exception e) { /* ignored */ }
		}
		
		return ajoutReussi;
	}

	public static boolean ajouterAmiClient(ModeleClientServeur unClient, ModeleClientServeur ami) {
		boolean ajoutReussi = false;
		try {
			Connection conn = connecterBD();
			String query = "insert into USERS.amiClient (idClient, idAmi) VALUES (" + unClient.getId() + ", " + ami.getId() + ")";
			OracleCallableStatement cs = (OracleCallableStatement) conn.prepareCall(query);
			cs.execute();
			ajoutReussi = true;

		} catch (SQLException ex) {
			Logger.getLogger(ControleurBD.class.getName()).log(Level.SEVERE, null, ex);
		}
		return ajoutReussi;
	}

	public static boolean ajouterAvatar(Avatar unAvatar, ModeleClientServeur unClient) {
		boolean ajoutReussi = false;
		try {
			Connection conn = connecterBD();
			String query = "BEGIN insert into USERS.Avatar (idClient, nomFichier, texte) VALUES (" + unClient.getId() + ", '" + unAvatar.getPathImage()
					+ "' , '" + unAvatar.getTexte() + "') returning id into ?; END;  ";
			OracleCallableStatement cs = (OracleCallableStatement) conn.prepareCall(query);
			cs.registerOutParameter(1, OracleTypes.NUMBER);
			cs.execute();
			System.out.println(cs.getInt(1));
			unAvatar.setId(cs.getInt(1));
			ajoutReussi = true;

		} catch (SQLException ex) {
			Logger.getLogger(ControleurBD.class.getName()).log(Level.SEVERE, null, ex);
		}
		return ajoutReussi;
	}

	public static boolean ajouterFondation(Fondation uneFondation) {
		boolean ajoutReussi = false;
		try {
			Connection conn = connecterBD();
			String query = "BEGIN insert into USERS.Fondation (nom, description) VALUES ('" + uneFondation.getNom() + "', '"
					+ uneFondation.getDescription() + "') returning id into ?; END;  ";
			OracleCallableStatement cs = (OracleCallableStatement) conn.prepareCall(query);
			cs.registerOutParameter(1, OracleTypes.NUMBER);
			cs.execute();
			uneFondation.setId(cs.getInt(1));
			ajoutReussi = true;

		} catch (SQLException ex) {
			Logger.getLogger(ControleurBD.class.getName()).log(Level.SEVERE, null, ex);
		}
		return ajoutReussi;
	}

	public static boolean ajouterDonUnique(DonUniqueClient unDon) {
		boolean ajoutReussi = false;
		try {
			Connection conn = connecterBD();
			String query = "BEGIN insert into USERS.donUnique (idClient, idFondation, montant) VALUES (" + unDon.getClient().getId() + ", "
					+ unDon.getFondation().getId() + ", " + unDon.getMontant() + " ) returning id into ?; END;  ";
			OracleCallableStatement cs = (OracleCallableStatement) conn.prepareCall(query);
			cs.registerOutParameter(1, OracleTypes.NUMBER);
			cs.execute();
			unDon.setId(cs.getInt(1));
			ajoutReussi = true;

		} catch (SQLException ex) {
			Logger.getLogger(ControleurBD.class.getName()).log(Level.SEVERE, null, ex);
		}
		return ajoutReussi;
	}

	public static boolean ajouterPartageGain(PartageGainsClient unPartageGain) {
		boolean ajoutReussi = false;
		try {
			Connection conn = connecterBD();
			String query = "insert into USERS.partageGain (idClient, idFondation, pourcentage) VALUES (" + unPartageGain.getClient().getId() + ", "
					+ unPartageGain.getFondation().getId() + ", " + unPartageGain.getPourcentage() + " )";
			OracleCallableStatement cs = (OracleCallableStatement) conn.prepareCall(query);
			cs.execute();
			ajoutReussi = true;

		} catch (SQLException ex) {
			Logger.getLogger(ControleurBD.class.getName()).log(Level.SEVERE, null, ex);
		}
		return ajoutReussi;
	}

	public static boolean supprimerUtilisateur(Utilisateur unUtilisateur) {
		boolean supprimerReussi = false;
		try {
			Connection conn = connecterBD();
			String query = "delete from USERS.utilisateur where id = " + unUtilisateur.getIdUtilisateur();
			OracleCallableStatement cs = (OracleCallableStatement) conn.prepareCall(query);
			cs.execute();
			supprimerReussi = true;

		} catch (SQLException ex) {
			Logger.getLogger(ControleurBD.class.getName()).log(Level.SEVERE, null, ex);
		}
		return supprimerReussi;
	}

	public static boolean supprimerClient(Client unClient) {
		boolean supprimerReussi = false;
		try {
			Connection conn = connecterBD();
			String query = "delete from USERS.client where id = " + unClient.getId();
			OracleCallableStatement cs = (OracleCallableStatement) conn.prepareCall(query);
			cs.execute();
			supprimerReussi = true;

		} catch (SQLException ex) {
			Logger.getLogger(ControleurBD.class.getName()).log(Level.SEVERE, null, ex);
		}
		return supprimerReussi;
	}

	public static boolean supprimerAmiClient(Client unClient, Client ami) {
		boolean supprimerReussi = false;
		try {
			Connection conn = connecterBD();
			String query = "delete from USERS.amiClient where idClient = " + unClient.getId() + " AND idAmi = " + ami.getId();
			OracleCallableStatement cs = (OracleCallableStatement) conn.prepareCall(query);
			cs.execute();
			supprimerReussi = true;

		} catch (SQLException ex) {
			Logger.getLogger(ControleurBD.class.getName()).log(Level.SEVERE, null, ex);
		}
		return supprimerReussi;
	}

	public static boolean supprimerAvatar(Avatar unAvatar) {
		boolean supprimerReussi = false;
		try {
			Connection conn = connecterBD();
			String query = "delete from USERS.avatar where id = " + unAvatar.getId();
			OracleCallableStatement cs = (OracleCallableStatement) conn.prepareCall(query);
			cs.execute();
			supprimerReussi = true;

		} catch (SQLException ex) {
			Logger.getLogger(ControleurBD.class.getName()).log(Level.SEVERE, null, ex);
		}
		return supprimerReussi;
	}

	public static boolean supprimerFondation(Fondation uneFondation) {
		boolean supprimerReussi = false;
		try {
			Connection conn = connecterBD();
			String query = "delete from USERS.fondation where id = " + uneFondation.getId();
			OracleCallableStatement cs = (OracleCallableStatement) conn.prepareCall(query);
			cs.execute();
			supprimerReussi = true;

		} catch (SQLException ex) {
			Logger.getLogger(ControleurBD.class.getName()).log(Level.SEVERE, null, ex);
		}
		return supprimerReussi;
	}

	public static boolean supprimerPartageGain(PartageGainsClient unPartageGain) {
		boolean supprimerReussi = false;
		try {
			Connection conn = connecterBD();
			String query = "delete from USERS.partageGain where idClient = " + unPartageGain.getClient().getId() + " AND "
					+ unPartageGain.getFondation().getId();
			OracleCallableStatement cs = (OracleCallableStatement) conn.prepareCall(query);
			cs.execute();
			supprimerReussi = true;

		} catch (SQLException ex) {
			Logger.getLogger(ControleurBD.class.getName()).log(Level.SEVERE, null, ex);
		}
		return supprimerReussi;
	}

	public static boolean modifierUtilisateur(Utilisateur unUtilisateur) {
		boolean modifierReussi = false;
		try {
			Connection conn = connecterBD();
			String query = "update USERS.utilisateur set identifiant = '" + unUtilisateur.getNomUtilisateur() + /*
																														 * "', motPasse = '"
																														 * +
																														 * unUtilisateur
																														 * .
																														 * getMotDePasse
																														 * (
																														 * )
																														 * +
																														 */"' where id = "
					+ unUtilisateur.getIdUtilisateur();
			OracleCallableStatement cs = (OracleCallableStatement) conn.prepareCall(query);
			cs.execute();
			modifierReussi = true;

		} catch (SQLException ex) {
			Logger.getLogger(ControleurBD.class.getName()).log(Level.SEVERE, null, ex);
		}
		return modifierReussi;
	}

	public static boolean modifierClient(Client unClient) {
		boolean modifierReussi = false;
		try {
			Connection conn = connecterBD();
			String query = "update USERS.client set prenom = '" + unClient.getPrenom() + "', nom = '" + unClient.getNom() + "', dateNaissance = '"
					+ unClient.getDateNaissance() + "', courriel = '" + unClient.getCourriel() + "', solde = " + unClient.getSolde() + ", prcGlobal = "
					+ unClient.getPourcentageGlobal() + " where id = " + unClient.getId();
			OracleCallableStatement cs = (OracleCallableStatement) conn.prepareCall(query);
			cs.execute();
			modifierReussi = true;

		} catch (SQLException ex) {
			Logger.getLogger(ControleurBD.class.getName()).log(Level.SEVERE, null, ex);
		}
		return modifierReussi;
	}

	public static boolean modifierAvatar(Avatar unAvatar) {
		boolean modifierReussi = false;
		try {
			Connection conn = connecterBD();
			String query = "update USERS.avatar set nomFichier = '" + unAvatar.getPathImage() + "', texte = '" + unAvatar.getTexte() + " where id = "
					+ unAvatar.getId();
			OracleCallableStatement cs = (OracleCallableStatement) conn.prepareCall(query);
			cs.execute();
			modifierReussi = true;

		} catch (SQLException ex) {
			Logger.getLogger(ControleurBD.class.getName()).log(Level.SEVERE, null, ex);
		}
		return modifierReussi;
	}

	public static boolean modifierFondation(Fondation uneFondation) {
		boolean modifierReussi = false;
		try {
			Connection conn = connecterBD();
			String query = "update USERS.fondation set nom = '" + uneFondation.getNom() + "', description = '" + uneFondation.getDescription()
					+ " where id = " + uneFondation.getId();
			OracleCallableStatement cs = (OracleCallableStatement) conn.prepareCall(query);
			cs.execute();
			modifierReussi = true;

		} catch (SQLException ex) {
			Logger.getLogger(ControleurBD.class.getName()).log(Level.SEVERE, null, ex);
		}
		return modifierReussi;
	}

	public static boolean modifierPartageGain(PartageGainsClient unPartageGain) {
		boolean modifierReussi = false;
		try {
			Connection conn = connecterBD();
			String query = "update USERS.partageGain set pourcentage = " + unPartageGain.getPourcentage() + " where idClient = "
					+ unPartageGain.getClient().getId() + " AND idFondation = " + unPartageGain.getFondation().getId();
			OracleCallableStatement cs = (OracleCallableStatement) conn.prepareCall(query);
			cs.execute();
			modifierReussi = true;

		} catch (SQLException ex) {
			Logger.getLogger(ControleurBD.class.getName()).log(Level.SEVERE, null, ex);
		}
		return modifierReussi;
	}

	public static boolean modifierMontantClient(Client unClient) {
		boolean modifierReussi = false;
		try {
			Connection conn = connecterBD();
			String query = "update USERS.client set solde = " + unClient.getSolde() + " where id = " + unClient.getId();
			OracleCallableStatement cs = (OracleCallableStatement) conn.prepareCall(query);
			cs.execute();
			modifierReussi = true;

		} catch (SQLException ex) {
			Logger.getLogger(ControleurBD.class.getName()).log(Level.SEVERE, null, ex);
		}
		return modifierReussi;
	}
}
