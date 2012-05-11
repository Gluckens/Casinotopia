package ca.uqam.casinotopia.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

import ca.uqam.casinotopia.Avatar;
import ca.uqam.casinotopia.DonUniqueClient;
import ca.uqam.casinotopia.Fondation;
import ca.uqam.casinotopia.ListeAmis;
import ca.uqam.casinotopia.PartageGainsClient;
import ca.uqam.casinotopia.modele.serveur.ModeleClientServeur;

public enum CtrlBD {
	BD;
	
	private Connection connecterBD() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(ControleurBD.class.getName()).log(Level.SEVERE, null, ex);
		}

		Connection conn;
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "bd", "admin");

		} catch (Exception x) {
			System.out.println("Couldn't get connection!");
			conn = null;
		}
		return conn;
	}
	
	public ModeleClientServeur authentifierClient(String identifiant, String motPasse) {
		ModeleClientServeur client = null;
		
		Connection conn = this.connecterBD();
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rsUtilisateur = stmt.executeQuery(String.format("SELECT id FROM utilisateur WHERE identifiant='%s' AND motpasse='%s'", identifiant, motPasse));
			
			if (rsUtilisateur.next()) {
				client = this.getClientByIdUtilisateur(rsUtilisateur.getInt("id"));
			}
		} catch (SQLException ex) {
			System.out.println("erreur Client : " + ex.getMessage());
		} finally {
		    try { conn.close(); } catch (Exception e) { }
		}
		
		return client;
	}
	
	public ModeleClientServeur getClientByIdUtilisateur(int id) {
		Connection conn = this.connecterBD();
		
		ModeleClientServeur client = null;
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rsClient = stmt.executeQuery(
					"SELECT client.*, utilisateur.identifiant " +
					" FROM client INNER JOIN utilisateur ON client.idUtilisateur = utilisateur.id " +
					"WHERE client.idUtilisateur = " + id
			);

			if (rsClient.next()) {
				client = new ModeleClientServeur(
					rsClient.getInt("idUtilisateur"),
					rsClient.getString("identifiant"),
					"",
					rsClient.getInt("id"),
					rsClient.getString("prenom"),
					rsClient.getString("nom"),
					rsClient.getDate("datenaissance"),
					rsClient.getString("courriel"),
					rsClient.getInt("solde")
				);
				
				client.setListeAmis(this.getListeAmis(client.getId()));
			}
		} catch (SQLException ex) {
			System.out.println("erreur Client : " + ex.getMessage());
		} finally {
		    try { conn.close(); } catch (Exception e) { }
		}
		
		return client;
	}
	
	public ModeleClientServeur getClientById(int id) {
		Connection conn = this.connecterBD();
		
		ModeleClientServeur client = null;
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rsClient = stmt.executeQuery(
					"SELECT client.*, utilisateur.identifiant " +
					" FROM client INNER JOIN utilisateur ON client.idUtilisateur = utilisateur.id " +
					"WHERE client.id = " + id
			);

			if (rsClient.next()) {
				client = new ModeleClientServeur(
					rsClient.getInt("idUtilisateur"),
					rsClient.getString("identifiant"),
					"",
					rsClient.getInt("id"),
					rsClient.getString("prenom"),
					rsClient.getString("nom"),
					rsClient.getDate("datenaissance"),
					rsClient.getString("courriel"),
					rsClient.getInt("solde")
				);
				
				client.setListeAmis(this.getListeAmis(client.getId()));
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		} finally {
		    try { conn.close(); } catch (Exception e) { }
		}
		
		return client;
	}
	
	public ListeAmis getListeAmis(int idClient) {

		Connection conn = this.connecterBD();
		ListeAmis listeAmisClient = new ListeAmis();

		try {
			Statement stmt = conn.createStatement();
			ResultSet rsListeAmis = stmt.executeQuery("SELECT idAmi FROM amiClient WHERE idClient = " + idClient);
			
			while (rsListeAmis.next()) {
				listeAmisClient.ajouterAmi(this.getClientById(rsListeAmis.getInt("idAmi")));
			}

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		} finally {
		    try { conn.close(); } catch (Exception e) { }
		}

		return listeAmisClient;
	}
	
	public List<DonUniqueClient> getDonsUniquesClient(int idClient) {

		Connection conn = this.connecterBD();
		List<DonUniqueClient> lstDonsUniques = new Vector<DonUniqueClient>();

		try {
			Statement stmt = conn.createStatement();
			ResultSet rsDonUnique = stmt.executeQuery("SELECT * FROM donUnique WHERE idClient = " + idClient);

			while (rsDonUnique.next()) {
				ModeleClientServeur client = this.getClientById(rsDonUnique.getInt("idClient"));
				Fondation fondation = this.getFondation(rsDonUnique.getInt("idfondation"));
				DonUniqueClient donUnique = new DonUniqueClient(
					rsDonUnique.getInt("id"),
					client,
					fondation,
					rsDonUnique.getInt("montant"),
					rsDonUnique.getDate("dateDon")
				);
				
				lstDonsUniques.add(donUnique);
			}

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		} finally {
		    try { conn.close(); } catch (Exception e) { }
		}

		return lstDonsUniques;
	}
	
	public List<PartageGainsClient> getPartageGainsClient(int idClient) {

		Connection conn = this.connecterBD();
		List<PartageGainsClient> lstPartageGains = new Vector<PartageGainsClient>();

		try {
			Statement stmt = conn.createStatement();
			ResultSet rsPartageGains = stmt.executeQuery("SELECT * FROM partageGains WHERE idClient = " + idClient);

			while (rsPartageGains.next()) {
				ModeleClientServeur client = this.getClientById(rsPartageGains.getInt("idClient"));
				Fondation fondation = this.getFondation(rsPartageGains.getInt("idfondation"));
				PartageGainsClient partageGains = new PartageGainsClient(
					rsPartageGains.getInt("id"),
					client,
					fondation,
					rsPartageGains.getInt("pourcentage")
				);
				
				lstPartageGains.add(partageGains);
			}

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		} finally {
		    try { conn.close(); } catch (Exception e) { }
		}

		return lstPartageGains;
	}
	
	public Fondation getFondation(int idFondation) {
		Connection conn = this.connecterBD();
		Fondation fondation = null;

		try {
			Statement stmt = conn.createStatement();
			ResultSet rsFondation = stmt.executeQuery("SELECT * FROM fondation WHERE id = " + idFondation);
			
			if(rsFondation.next()) {
				fondation = new Fondation(rsFondation.getInt("id"), rsFondation.getString("nom"), rsFondation.getString("description"));
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		} finally {
		    try { conn.close(); } catch (Exception e) { }
		}

		return fondation;
	}
	
	public boolean ajouterClient(ModeleClientServeur client) {
		boolean succes = false;
		
		Connection conn = this.connecterBD();
		
		if(ajouterUtilisateur(client, "CLT", conn)) {
			try {
				/*Calendar cal = Calendar.getInstance(); SimpleDateFormat sdf = new
				SimpleDateFormat("HH:mm:ss"); String time = sdf.format(cal.getTime());
				txt.setText(String.valueOf(cases.size()) + " " + time);*/
				
				String query = String.format(
					"BEGIN INSERT INTO client (idUtilisateur, prenom, nom, dateNaissance, courriel, solde, prcGlobal) VALUES (%d, '%s', '%s', '%s', '%s', %d, %d) RETURNING id INTO ?; END;",
					client.getIdUtilisateur(), client.getPrenom(), client.getNom(), client.getDateNaissance(), client.getCourriel(), client.getSolde(), client.getPrcGlobal()
				);
				OracleCallableStatement cs = (OracleCallableStatement) conn.prepareCall(query);
				cs.registerOutParameter(1, OracleTypes.NUMBER);
				cs.execute();
				System.out.println("Ajout client id : " + cs.getInt(1));
				client.setId(cs.getInt(1));
				succes = true;
			} catch (SQLException ex) {
				Logger.getLogger(ControleurBD.class.getName()).log(Level.SEVERE, null, ex);
			} finally {
			    try { conn.close(); } catch (Exception e) { }
			}
		}
		else {
			try { conn.close(); } catch (Exception e) { }
		}
		
		return succes;
	}
	
	private boolean ajouterUtilisateur(ModeleClientServeur client, String typeCompte, Connection conn) {
		boolean succes = false;
		
		try {
			String query = String.format(
				"BEGIN INSERT INTO utilisateur (identifiant, motPasse, typeCompte) VALUES ('%s', '%s', '%s') RETURNING id INTO ?; END;",
				client.getNomUtilisateur(), client.getMotDePasse(), typeCompte
			);
			OracleCallableStatement cs = (OracleCallableStatement) conn.prepareCall(query);
			cs.registerOutParameter(1, OracleTypes.NUMBER);
			cs.execute();
			System.out.println("Ajout utilisateur id : " + cs.getInt(1));
			client.setIdUtilisateur(cs.getInt(1));
			succes = true;
		} catch (SQLException ex) {
			Logger.getLogger(ControleurBD.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return succes;
	}
	
	public boolean ajouterAmiClient(ModeleClientServeur client, ModeleClientServeur ami) {
		boolean succes = false;
		
		Connection conn = this.connecterBD();
		
		try {
			String query = String.format(
					"INSERT INTO amiClient (idClient, idAmi) VALUES (%d, %d)",
					client.getId(), ami.getId()
			);
			OracleCallableStatement cs = (OracleCallableStatement) conn.prepareCall(query);
			cs.execute();
			//client.ajouterAmi(ami);
			succes = true;

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		} finally {
		    try { conn.close(); } catch (Exception e) { }
		}
		return succes;
	}
	
	public boolean ajouterAvatar(Avatar avatar, ModeleClientServeur client) {
		boolean succes = false;
		
		Connection conn = this.connecterBD();
		
		try {
			String query = String.format(
					"INSERT INTO avatar (idClient, nomFichier, texte) VALUES (%d, '%s', '%s')",
					client.getId(), avatar.getPathImage(), avatar.getTexte()
			);
			OracleCallableStatement cs = (OracleCallableStatement) conn.prepareCall(query);
			cs.execute();
			succes = true;

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		} finally {
		    try { conn.close(); } catch (Exception e) { }
		}
		return succes;
	}
	
	public boolean ajouterFondation(Fondation fondation) {
		boolean succes = false;
		
		Connection conn = this.connecterBD();
		
		try {
			String query = String.format(
				"BEGIN INSERT INTO fondation (nom, description) VALUES ('%s', '%s') RETURNING id INTO ?; END;",
				fondation.getNom(), fondation.getDescription()
			);
			OracleCallableStatement cs = (OracleCallableStatement) conn.prepareCall(query);
			cs.registerOutParameter(1, OracleTypes.NUMBER);
			cs.execute();
			fondation.setId(cs.getInt(1));
			succes = true;

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		} finally {
		    try { conn.close(); } catch (Exception e) { }
		}
		
		return succes;
	}

	public boolean ajouterDonUnique(DonUniqueClient don) {
		boolean succes = false;
		
		Connection conn = this.connecterBD();
		
		try {
			String query = String.format(
				"BEGIN INSERT INTO donUnique (idClient, idFondation, montant) VALUES (%d, %d, %d) RETURNING id INTO ?; END;",
				don.getClient().getId(), don.getFondation().getId(), don.getMontant()
			);
			OracleCallableStatement cs = (OracleCallableStatement) conn.prepareCall(query);
			cs.registerOutParameter(1, OracleTypes.NUMBER);
			cs.execute();
			don.setId(cs.getInt(1));
			succes = true;

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		} finally {
		    try { conn.close(); } catch (Exception e) { }
		}
		
		return succes;
	}

	public boolean ajouterPartageGains(PartageGainsClient partageGains) {
		boolean succes = false;

		Connection conn = this.connecterBD();
		
		try {
			String query = String.format(
				"BEGIN INSERT INTO partageGains (idClient, idFondation, pourcentage) VALUES (%d, %d, %d) RETURNING id INTO ?; END;",
				partageGains.getClient().getId(), partageGains.getFondation().getId(), partageGains.getPourcentage()
			);
			OracleCallableStatement cs = (OracleCallableStatement) conn.prepareCall(query);
			cs.execute();
			partageGains.setId(cs.getInt(1));
			succes = true;

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		} finally {
		    try { conn.close(); } catch (Exception e) { }
		}
		return succes;
	}
	
	public boolean modifierClient(int idClient, String prenom, String nom, Date dateNaissance, String courriel, int prcGlobal) {
		boolean succes = false;
		
		Connection conn = this.connecterBD();
		
		try {
			String query = String.format(
				"UPDATE client SET prenom = '%s', nom = '%s', dateNaissance = '%s', courriel = '%s', prcGlobal = %d WHERE id = %d",
				prenom, nom, dateNaissance, courriel, prcGlobal, idClient
			);
			OracleCallableStatement cs = (OracleCallableStatement) conn.prepareCall(query);
			cs.execute();
			succes = true;

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		} finally {
		    try { conn.close(); } catch (Exception e) { }
		}
		return succes;
	}
	
	public boolean modifierMotDePasse(int idUtilisateur, String motDePasse) {
		boolean succes = false;
		
		Connection conn = this.connecterBD();
		
		try {
			String query = String.format(
				"UPDATE utilisateur SET motPasse = '%s' WHERE id = %d",
				motDePasse, idUtilisateur
			);
			OracleCallableStatement cs = (OracleCallableStatement) conn.prepareCall(query);
			cs.execute();
			succes = true;

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		} finally {
		    try { conn.close(); } catch (Exception e) { }
		}
		return succes;
	}
	
	public boolean modifierAvatar(int idAvatar, String pathImage, String texte) {
		boolean succes = false;
		
		Connection conn = this.connecterBD();
		
		try {
			String query = String.format(
				"UPDATE avatar SET nomFichier = '%s', texte = '%s' WHERE id = %d",
				pathImage, texte, idAvatar
			);
			OracleCallableStatement cs = (OracleCallableStatement) conn.prepareCall(query);
			cs.execute();
			succes = true;

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		} finally {
		    try { conn.close(); } catch (Exception e) { }
		}
		return succes;
	}

	public boolean modifierPartageGains(int idClient, int idFondation, int nouveauPourcentage) {
		boolean succes = false;
		
		Connection conn = this.connecterBD();
		
		try {
			String query = String.format(
				"UPDATE partageGains SET pourcentage = %d WHERE idClient = %d AND idFondation = %d",
				nouveauPourcentage, idClient, idFondation
			);
			OracleCallableStatement cs = (OracleCallableStatement) conn.prepareCall(query);
			cs.execute();
			succes = true;

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		} finally {
		    try { conn.close(); } catch (Exception e) { }
		}
		return succes;
	}
	
	public boolean modifierSoldeClient(ModeleClientServeur client, int nouveauSolde) {
		boolean succes = false;
		
		Connection conn = this.connecterBD();
		
		try {
			String query = String.format(
				"UPDATE client SET solde = %d WHERE id = %d",
				nouveauSolde, client.getId()
			);
			OracleCallableStatement cs = (OracleCallableStatement) conn.prepareCall(query);
			cs.execute();
			succes = true;

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		} finally {
		    try { conn.close(); } catch (Exception e) { }
		}
		return succes;
	}
	
	public boolean modifierFondation(int idFondation, String nouveauNom, String nouvelleDescription) {
		boolean succes = false;
		
		Connection conn = this.connecterBD();
		
		try {
			String query = String.format(
				"UPDATE fondation SET nom = '%s', description = '%s' WHERE id = %d",
				nouveauNom, nouvelleDescription, idFondation
			);
			OracleCallableStatement cs = (OracleCallableStatement) conn.prepareCall(query);
			cs.execute();
			succes = true;

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		} finally {
		    try { conn.close(); } catch (Exception e) { }
		}
		return succes;
	}
}
