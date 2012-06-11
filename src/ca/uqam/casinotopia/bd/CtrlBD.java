package ca.uqam.casinotopia.bd;

import java.awt.Rectangle;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

import ca.uqam.casinotopia.Avatar;
import ca.uqam.casinotopia.DonUniqueClient;
import ca.uqam.casinotopia.Fondation;
import ca.uqam.casinotopia.Jeu;
import ca.uqam.casinotopia.ListeAmis;
import ca.uqam.casinotopia.PartageGainsClient;
import ca.uqam.casinotopia.TypeJeu;
import ca.uqam.casinotopia.modele.serveur.ModeleClientServeur;
import ca.uqam.casinotopia.modele.serveur.ModeleSalleServeur;

public enum CtrlBD {
	BD;
	
	private Connection connecterBD() throws Exception {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(CtrlBD.class.getName()).log(Level.SEVERE, null, ex);
		}

		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "bd", "admin");

		} catch (Exception x) {
			System.err.println("Couldn't get connection!");
			throw x;
		}
		return conn;
	}
	
	private void fermerConnexion(Connection conn) {
		try {
			if(conn != null) {
				conn.close();
			}
		} catch (Exception e) { }
	}
	
	public boolean execQuery(String query) {
		boolean succes = false;
		
		Connection conn = null;
		
		try {
			conn = this.connecterBD();
			conn.createStatement().execute(query);
			succes = true;
		} catch (SQLException ex) {
			if(ex.getErrorCode() != 942) {
				System.err.println("erreur Client : " + ex.getMessage());
			}
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		} finally {
			this.fermerConnexion(conn);
		}
		
		return succes;
	}
	
	public ModeleClientServeur authentifierClient(String identifiant, String motDePasse) {
		Connection conn = null;
		ModeleClientServeur client = null;
		
		try {
			conn = this.connecterBD();
			Statement stmt = conn.createStatement();
			ResultSet rsUtilisateur = stmt.executeQuery(String.format("SELECT id FROM utilisateur WHERE identifiant='%s' AND motDePasse='%s'", identifiant, motDePasse));
			
			if (rsUtilisateur.next()) {
				client = this.getClientByIdUtilisateur(rsUtilisateur.getInt("id"));
			}
		} catch (SQLException ex) {
			System.err.println("erreur Client : " + ex.getMessage());
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		} finally {
			this.fermerConnexion(conn);
		}
		
		return client;
	}
	
	public ModeleClientServeur getClientByIdUtilisateur(int id) {
		Connection conn = null;
		ModeleClientServeur client = null;
		
		try {
			conn = this.connecterBD();
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
				
				client.setAvatar(this.getAvatar(client.getAvatar().getId()));
				client.setListeAmis(this.getListeAmis(client.getId()));
			}
		} catch (SQLException ex) {
			System.err.println("erreur Client : " + ex.getMessage());
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		} finally {
			this.fermerConnexion(conn);
		}
		
		return client;
	}
	
	public ModeleClientServeur getClientById(int id) {
		Connection conn = null;
		ModeleClientServeur client = null;
		
		try {
			conn = this.connecterBD();
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
				client.setAvatar(this.getAvatarClient(client.getId()));
			}
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		} finally {
			this.fermerConnexion(conn);
		}
		
		return client;
	}
	
	public Avatar getAvatar(int idAvatar) {
		Connection conn = null;
		Avatar avatar = null;
		
		try {
			conn = this.connecterBD();
			Statement stmt = conn.createStatement();
			ResultSet rsAvatar = stmt.executeQuery("SELECT * FROM avatar WHERE id = " + idAvatar);
			
			if(rsAvatar.next()) {
				avatar = new Avatar(rsAvatar.getInt("id"), rsAvatar.getString("nomFichier"), rsAvatar.getString("texte"));
			}
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		} finally {
			this.fermerConnexion(conn);
		}

		return avatar;
	}
	
	public Avatar getAvatarClient(int idClient) {
		Connection conn = null;
		Avatar avatar = null;
		
		try {
			conn = this.connecterBD();
			Statement stmt = conn.createStatement();
			ResultSet rsAvatar = stmt.executeQuery("SELECT avatar.* FROM client INNER JOIN avatar ON client.idAvatar = avatar.id WHERE client.id = " + idClient);
			
			if(rsAvatar.next()) {
				avatar = new Avatar(rsAvatar.getInt("id"), rsAvatar.getString("nomFichier"), rsAvatar.getString("texte"));
			}
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		} finally {
			this.fermerConnexion(conn);
		}

		return avatar;
	}
	
	public ListeAmis getListeAmis(int idClient) {
		Connection conn = null;
		ListeAmis listeAmisClient = new ListeAmis();
		
		try {
			conn = this.connecterBD();
			Statement stmt = conn.createStatement();
			ResultSet rsListeAmis = stmt.executeQuery("SELECT idAmi FROM amiClient WHERE idClient = " + idClient);
			
			while (rsListeAmis.next()) {
				listeAmisClient.ajouterAmi(this.getClientById(rsListeAmis.getInt("idAmi")));
			}

		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		} finally {
			this.fermerConnexion(conn);
		}

		return listeAmisClient;
	}
	
	public List<DonUniqueClient> getDonsUniquesClient(int idClient) {
		Connection conn = null;
		List<DonUniqueClient> lstDonsUniques = new Vector<DonUniqueClient>();
		
		try {
			conn = this.connecterBD();
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
			System.err.println(ex.getMessage());
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		} finally {
			this.fermerConnexion(conn);
		}

		return lstDonsUniques;
	}
	
	public List<PartageGainsClient> getPartageGainsClient(int idClient) {
		Connection conn = null;
		List<PartageGainsClient> lstPartageGains = new Vector<PartageGainsClient>();
		
		try {
			conn = this.connecterBD();
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
			System.err.println(ex.getMessage());
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		} finally {
			this.fermerConnexion(conn);
		}

		return lstPartageGains;
	}
	
	public Fondation getFondation(int idFondation) {
		Connection conn = null;
		Fondation fondation = null;
		
		try {
			conn = this.connecterBD();
			Statement stmt = conn.createStatement();
			ResultSet rsFondation = stmt.executeQuery("SELECT * FROM fondation WHERE id = " + idFondation);
			
			if(rsFondation.next()) {
				fondation = new Fondation(rsFondation.getInt("id"), rsFondation.getString("nom"), rsFondation.getString("description"));
			}
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		} finally {
			this.fermerConnexion(conn);
		}

		return fondation;
	}
	
	//TODO Map avec nom ou ID comme key?
	public Map<Integer, ModeleSalleServeur> getAllSalle() {
		Connection conn = null;
		Map<Integer, ModeleSalleServeur> lstSalles = new HashMap<Integer, ModeleSalleServeur>();
		
		try {
			conn = this.connecterBD();
			Statement stmt = conn.createStatement();
			ResultSet rsSalle = stmt.executeQuery("SELECT * FROM salle");
			
			while(rsSalle.next()) {
				ModeleSalleServeur salle = new ModeleSalleServeur(rsSalle.getInt("id"), rsSalle.getString("nom"));
				this.getJeuDeSalle(salle, conn);
				lstSalles.put(salle.getId(), salle);
			}
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		} finally {
			this.fermerConnexion(conn);
		}

		return lstSalles;
	}
	
	public ModeleSalleServeur getSalle(int idSalle) {
		Connection conn = null;
		ModeleSalleServeur salle = null;
		
		try {
			conn = this.connecterBD();
			Statement stmt = conn.createStatement();
			ResultSet rsSalle = stmt.executeQuery("SELECT * FROM salle WHERE id = " + idSalle);
			
			if(rsSalle.next()) {
				salle = new ModeleSalleServeur(rsSalle.getInt("id"), rsSalle.getString("nom"));
				
				this.getJeuDeSalle(salle, conn);
			}
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		} finally {
			this.fermerConnexion(conn);
		}

		return salle;
	}
	
	private void getJeuDeSalle(ModeleSalleServeur salle, Connection conn) {
		try {
			Statement stmt = conn.createStatement();
			ResultSet rsJeu = stmt.executeQuery("SELECT * FROM jeu WHERE idSalle = " + salle.getId());
			
			while(rsJeu.next()) {
				Rectangle emplacement = new Rectangle(rsJeu.getInt("posX"), rsJeu.getInt("posY"), rsJeu.getInt("largeur"), rsJeu.getInt("hauteur"));
				salle.ajouterJeu(new Jeu(rsJeu.getInt("id"), rsJeu.getString("nom"), rsJeu.getString("description"), rsJeu.getString("reglesJeu"), emplacement, rsJeu.getInt("nbrJoueursMin"), rsJeu.getInt("nbrJoueursMax"), TypeJeu.valueOf(rsJeu.getString("type"))));
			}
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
	}
	
	public Jeu getJeu(int idJeu) {
		Connection conn = null;
		Jeu jeu = null;
		
		try {
			conn = this.connecterBD();
			Statement stmt = conn.createStatement();
			ResultSet rsJeu = stmt.executeQuery("SELECT * FROM jeu WHERE id = " + idJeu);
			
			if(rsJeu.next()) {
				Rectangle emplacement = new Rectangle(rsJeu.getInt("posX"), rsJeu.getInt("posY"), rsJeu.getInt("largeur"), rsJeu.getInt("hauteur"));
				jeu = new Jeu(rsJeu.getInt("id"), rsJeu.getString("nom"), rsJeu.getString("description"), rsJeu.getString("reglesJeu"), emplacement, rsJeu.getInt("nbrJoueursMin"), rsJeu.getInt("nbrJoueursMax"), TypeJeu.valueOf(rsJeu.getString("type")));
			}
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		} finally {
			this.fermerConnexion(conn);
		}

		return jeu;
	}
	
	public boolean ajouterClient(ModeleClientServeur client) {
		boolean succes = false;
		
		Connection conn = null;
		
		try {
			conn = this.connecterBD();
			if(this.ajouterUtilisateur(client, "CLT", conn) && this.ajouterAvatar(client.getAvatar(), client) ) {
				String query = String.format(
					"BEGIN INSERT INTO client (idUtilisateur, prenom, nom, dateNaissance, courriel, solde, prcGlobal, idAvatar) VALUES (%d, '%s', '%s', '%s', '%s', %d, %d, %d) RETURNING id INTO ?; END;",
					client.getIdUtilisateur(), client.getPrenom(), client.getNom(), client.getDateNaissance(), client.getCourriel(), client.getSolde(), client.getPrcGlobal(), client.getAvatar().getId()
				);
				OracleCallableStatement cs = (OracleCallableStatement) conn.prepareCall(query);
				cs.registerOutParameter(1, OracleTypes.NUMBER);
				cs.execute();
				client.setId(cs.getInt(1));
				succes = true;
			}
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		} finally {
			this.fermerConnexion(conn);
		}
		
		return succes;
	}
	
	private boolean ajouterUtilisateur(ModeleClientServeur client, String typeCompte, Connection conn) {
		boolean succes = false;
		
		try {
			String query = String.format(
				"BEGIN INSERT INTO utilisateur (identifiant, motDePasse, typeCompte) VALUES ('%s', '%s', '%s') RETURNING id INTO ?; END;",
				client.getNomUtilisateur(), client.getMotDePasse(), typeCompte
			);
			OracleCallableStatement cs = (OracleCallableStatement) conn.prepareCall(query);
			cs.registerOutParameter(1, OracleTypes.NUMBER);
			cs.execute();
			client.setIdUtilisateur(cs.getInt(1));
			succes = true;
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
		
		return succes;
	}
	
	//TODO ou bedon retourner l'id et valider le succes si pas = -1?
	public boolean ajouterAvatar(Avatar avatar, ModeleClientServeur client) {
		boolean succes = false;
		
		Connection conn = null;
		
		try {
			conn = this.connecterBD();
			String query = String.format(
					"BEGIN INSERT INTO avatar (nomFichier, texte) VALUES ('%s', '%s') RETURNING id INTO ?; END;",
					avatar.getPathImage(), avatar.getTexte()
			);
			OracleCallableStatement cs = (OracleCallableStatement) conn.prepareCall(query);
			cs.registerOutParameter(1, OracleTypes.NUMBER);
			cs.execute();
			client.getAvatar().setId(cs.getInt(1));
			succes = true;
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		} finally {
			this.fermerConnexion(conn);
		}
		return succes;
	}
	
	public boolean ajouterAmiClient(ModeleClientServeur client, ModeleClientServeur ami) {
		boolean succes = false;
		
		Connection conn = null;
		
		try {
			conn = this.connecterBD();
			String query = String.format(
					"INSERT INTO amiClient (idClient, idAmi) VALUES (%d, %d)",
					client.getId(), ami.getId()
			);
			OracleCallableStatement cs = (OracleCallableStatement) conn.prepareCall(query);
			cs.execute();
			//client.ajouterAmi(ami);
			succes = true;

		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		} finally {
			this.fermerConnexion(conn);
		}
		return succes;
	}
	
	public boolean ajouterFondation(Fondation fondation) {
		boolean succes = false;
		
		Connection conn = null;
		
		try {
			conn = this.connecterBD();
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
			System.err.println(ex.getMessage());
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		} finally {
			this.fermerConnexion(conn);
		}
		
		return succes;
	}

	public boolean ajouterDonUnique(DonUniqueClient don) {
		boolean succes = false;
		
		Connection conn = null;
		
		try {
			conn = this.connecterBD();
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
			System.err.println(ex.getMessage());
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		} finally {
			this.fermerConnexion(conn);
		}
		
		return succes;
	}

	public boolean ajouterPartageGains(PartageGainsClient partageGains) {
		boolean succes = false;

		Connection conn = null;
		
		try {
			conn = this.connecterBD();
			String query = String.format(
				"BEGIN INSERT INTO partageGains (idClient, idFondation, pourcentage) VALUES (%d, %d, %d) RETURNING id INTO ?; END;",
				partageGains.getClient().getId(), partageGains.getFondation().getId(), partageGains.getPourcentage()
			);
			OracleCallableStatement cs = (OracleCallableStatement) conn.prepareCall(query);
			cs.registerOutParameter(1, OracleTypes.NUMBER);
			cs.execute();
			partageGains.setId(cs.getInt(1));
			succes = true;

		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		} finally {
			this.fermerConnexion(conn);
		}
		
		return succes;
	}
	
	public boolean ajouterSalle(ModeleSalleServeur salle) {
		boolean succes = false;

		Connection conn = null;
		
		try {
			conn = this.connecterBD();
			String query = String.format(
				"BEGIN INSERT INTO salle (nom) VALUES ('%s') RETURNING id INTO ?; END;",
				salle.getNom()
			);
			OracleCallableStatement cs = (OracleCallableStatement) conn.prepareCall(query);
			cs.registerOutParameter(1, OracleTypes.NUMBER);
			cs.execute();
			salle.setId(cs.getInt(1));
			for(Jeu jeu : salle.getLstJeux().values()) {
				if(!this.ajouterJeu(jeu, salle.getId())) {
					System.out.println("Le jeu \"" + jeu.getNom() + "\" ne s'est pas bien ajouté à la salle \"" + salle.getNom() + "\"");
				}
			}
			
			succes = true;

		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		} finally {
			this.fermerConnexion(conn);
		}
		
		return succes;
	}
	
	public boolean ajouterJeu(Jeu jeu, int idSalle) {
		boolean succes = false;

		Connection conn = null;
		
		try {
			conn = this.connecterBD();
			String query = String.format(
				"BEGIN INSERT INTO jeu (idSalle, type, nom, description, reglesJeu, nbrJoueursMin, nbrJoueursMax, posX, posY, largeur, hauteur) VALUES (%d, '%s', '%s', '%s', '%s', %d, %d, %d, %d, %d, %d) RETURNING id INTO ?; END;",
				idSalle, jeu.getType().toString(), jeu.getNom(), jeu.getDescription(), jeu.getReglesJeu(), jeu.getNbrJoueursMin(), jeu.getNbrJoueursMax(), jeu.getEmplacement().x, jeu.getEmplacement().y, jeu.getEmplacement().width, jeu.getEmplacement().height
			);
			OracleCallableStatement cs = (OracleCallableStatement) conn.prepareCall(query);
			cs.registerOutParameter(1, OracleTypes.NUMBER);
			cs.execute();
			jeu.setId(cs.getInt(1));
			succes = true;

		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		} finally {
			this.fermerConnexion(conn);
		}
		
		return succes;
	}
	
	private boolean ajouterTypeJeu(String nomType) {
		boolean succes = false;

		Connection conn = null;
		
		try {
			conn = this.connecterBD();
			
			String query = String.format(
				"INSERT INTO typeJeu (type) VALUES ('%s')",
				nomType
			);
			OracleCallableStatement cs = (OracleCallableStatement) conn.prepareCall(query);
			cs.execute();
			succes = true;

		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		} finally {
			this.fermerConnexion(conn);
		}
		
		return succes;
	}
	
	public boolean modifierClient(int idClient, String prenom, String nom, Date dateNaissance, String courriel, int prcGlobal) {
		boolean succes = false;
		System.out.println("modification client sur serveur : " + idClient + " " + prenom + " " + nom + " " + dateNaissance + " " + courriel + " " + prcGlobal);
		
		Connection conn = null;
		
		try {
			conn = this.connecterBD();
			String query = String.format(
				"UPDATE client SET prenom = '%s', nom = '%s', dateNaissance = '%s', courriel = '%s', prcGlobal = %d WHERE id = %d",
				prenom, nom, dateNaissance, courriel, prcGlobal, idClient
			);
			OracleCallableStatement cs = (OracleCallableStatement) conn.prepareCall(query);
			cs.execute();
			succes = true;

		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		} finally {
			this.fermerConnexion(conn);
		}
		return succes;
	}
	
	public boolean modifierMotDePasse(int idUtilisateur, String motDePasse) {
		boolean succes = false;
		
		Connection conn = null;
		
		try {
			conn = this.connecterBD();
			String query = String.format(
				"UPDATE utilisateur SET motDePasse = '%s' WHERE id = %d",
				motDePasse, idUtilisateur
			);
			OracleCallableStatement cs = (OracleCallableStatement) conn.prepareCall(query);
			cs.execute();
			succes = true;

		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		} finally {
			this.fermerConnexion(conn);
		}
		return succes;
	}
	
	public boolean modifierAvatar(int idAvatar, String pathImage, String texte) {
		boolean succes = false;
		
		Connection conn = null;
		
		try {
			conn = this.connecterBD();
			String query = String.format(
				"UPDATE avatar SET nomFichier = '%s', texte = '%s' WHERE id = %d",
				pathImage, texte, idAvatar
			);
			OracleCallableStatement cs = (OracleCallableStatement) conn.prepareCall(query);
			cs.execute();
			succes = true;

		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		} finally {
			this.fermerConnexion(conn);
		}
		return succes;
	}

	public boolean modifierPartageGains(int idClient, int idFondation, int nouveauPourcentage) {
		boolean succes = false;
		
		Connection conn = null;
		
		try {
			conn = this.connecterBD();
			String query = String.format(
				"UPDATE partageGains SET pourcentage = %d WHERE idClient = %d AND idFondation = %d",
				nouveauPourcentage, idClient, idFondation
			);
			OracleCallableStatement cs = (OracleCallableStatement) conn.prepareCall(query);
			cs.execute();
			succes = true;

		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		} finally {
			this.fermerConnexion(conn);
		}
		return succes;
	}
	
	public boolean modifierSoldeClient(ModeleClientServeur client, int nouveauSolde) {
		boolean succes = false;
		
		Connection conn = null;
		
		try {
			conn = this.connecterBD();
			String query = String.format(
				"UPDATE client SET solde = %d WHERE id = %d",
				nouveauSolde, client.getId()
			);
			OracleCallableStatement cs = (OracleCallableStatement) conn.prepareCall(query);
			cs.execute();
			succes = true;

		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		} finally {
			this.fermerConnexion(conn);
		}
		return succes;
	}
	
	public boolean modifierFondation(int idFondation, String nouveauNom, String nouvelleDescription) {
		boolean succes = false;
		
		Connection conn = null;
		
		try {
			conn = this.connecterBD();
			String query = String.format(
				"UPDATE fondation SET nom = '%s', description = '%s' WHERE id = %d",
				nouveauNom, nouvelleDescription, idFondation
			);
			OracleCallableStatement cs = (OracleCallableStatement) conn.prepareCall(query);
			cs.execute();
			succes = true;

		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		} finally {
			this.fermerConnexion(conn);
		}
		return succes;
	}
	
	//TODO Supprimer un client implique, via ON DELETE CASCADE, de supprimer toutes les données afférentes?
	//Utilisateur, Avatar, AssociationAmi, DonUnique, PartageGains
	public boolean supprimerClient(ModeleClientServeur client) {
		boolean succes = false;
		
		Connection conn = null;
		
		try {
			conn = this.connecterBD();
			String query = "DELETE FROM client WHERE id = " + client.getId();
			OracleCallableStatement cs = (OracleCallableStatement) conn.prepareCall(query);
			cs.execute();
			succes = true;

		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		} finally {
			this.fermerConnexion(conn);
		}
		
		return succes;
	}
	
	public boolean supprimerAvatar(Avatar avatar) {
		boolean succes = false;
		
		Connection conn = null;
		
		try {
			conn = this.connecterBD();
			String query = "DELETE FROM avatar WHERE id = " + avatar.getId();
			OracleCallableStatement cs = (OracleCallableStatement) conn.prepareCall(query);
			cs.execute();
			succes = true;

		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		} finally {
			this.fermerConnexion(conn);
		}
		
		return succes;
	}
	
	public boolean supprimerAssociationAmi(ModeleClientServeur client, ModeleClientServeur ami) {
		boolean succes = false;
		
		Connection conn = null;
		
		try {
			conn = this.connecterBD();
			String query = String.format("DELETE FROM amiClient WHERE idClient = %d AND idAmi = %d", client.getId(), ami.getId());
			OracleCallableStatement cs = (OracleCallableStatement) conn.prepareCall(query);
			cs.execute();
			succes = true;

		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		} finally {
			this.fermerConnexion(conn);
		}
		
		return succes;
	}
	
	public boolean supprimerFondation(ModeleClientServeur client) {
		boolean succes = false;
		
		Connection conn = null;
		
		try {
			conn = this.connecterBD();
			String query = "DELETE FROM client WHERE id = " + client.getId();
			OracleCallableStatement cs = (OracleCallableStatement) conn.prepareCall(query);
			cs.execute();
			succes = true;

		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		} finally {
			this.fermerConnexion(conn);
		}
		
		return succes;
	}
	
	public void initDatabase() {
		this.createDatabase();
		this.initData();
	}
		
	private void createDatabase() {
		this.dropTables();
		this.dropSequence();
		this.createTable();
		this.createSequence();
		this.createTriggers();
	}
	
	private void dropTables() {
		System.out.println("Drop des tables...");
		
		this.execQuery("DROP TABLE typeJeu CASCADE CONSTRAINTS PURGE");
		this.execQuery("DROP TABLE jeu CASCADE CONSTRAINTS PURGE");
		this.execQuery("DROP TABLE salle CASCADE CONSTRAINTS PURGE");
		this.execQuery("DROP TABLE donUnique CASCADE CONSTRAINTS PURGE");
		this.execQuery("DROP TABLE partageGains CASCADE CONSTRAINTS PURGE");
		this.execQuery("DROP TABLE fondation CASCADE CONSTRAINTS PURGE");
		this.execQuery("DROP TABLE amiClient CASCADE CONSTRAINTS PURGE");
		this.execQuery("DROP TABLE avatar CASCADE CONSTRAINTS PURGE");
		this.execQuery("DROP TABLE utilisateur CASCADE CONSTRAINTS PURGE");
		this.execQuery("DROP TABLE client CASCADE CONSTRAINTS PURGE");
	}
	
	private void dropSequence() {
		System.out.println("Drop des sequences...");
		
		this.execQuery("DROP SEQUENCE utilisateur_id_seq");
		this.execQuery("DROP SEQUENCE client_id_seq");
		this.execQuery("DROP SEQUENCE avatar_id_seq");
		this.execQuery("DROP SEQUENCE fondation_id_seq");
		this.execQuery("DROP SEQUENCE donUnique_id_seq");
		this.execQuery("DROP SEQUENCE partageGains_id_seq");
		this.execQuery("DROP SEQUENCE jeu_id_seq");
		this.execQuery("DROP SEQUENCE salle_id_seq");
	}
	
	private void createTable() {
		System.out.println("Création des tables...");
		
		String query =	"create table utilisateur" +
				"(" +
				"  id     				number not null," +
				"  identifiant    		varchar2(50) not null," +
				"  motDePasse 			varchar2(50) not null," +
				"  typeCompte char(3) 	not null," +
				"  constraint utilisateur_pk primary key (id)," +
				"  constraint identifiant_uk1 unique (identifiant)," +
				"  constraint typeCompte_ck check (typeCompte in ('ADM', 'TEC', 'CLT'))" +
				")";
		this.execQuery(query);
				 
		query =	"create table avatar" +
				"(" +
				"  id               	number not null," +
				"  nomFichier			varchar2(50) not null," +
				"  texte				varchar2(300)," +
				"  constraint avatar_pk primary key (id)" +
				")";
		this.execQuery(query);
				
		query =	"create table client" +
				"(" +
				"  id               	number not null," +
				"  idUtilisateur		number not null," +
				"  prenom				varchar2(50) not null," +
				"  nom					varchar2(50) not null," +
				"  dateNaissance		date not null," +
				"  courriel				varchar2(200) not null," +
				"  solde				number(9,2)," +
				"  prcGlobal			number(3)," +
				"  idAvatar				number not null," +
				"  constraint client_pk primary key (id)," +
				"  constraint client_utilisateur_fk foreign key (idUtilisateur) references utilisateur (id) on delete cascade," +
				"  constraint client_avatar_fk foreign key (idAvatar) references avatar (id) on delete cascade," +
				"  constraint courriel_uk1 unique (courriel)," +
				"  constraint prcGlobal_ck check (prcGlobal <= 100 AND prcGlobal >= 0)" +
				")";
		this.execQuery(query);
				 
		query =	"create table amiClient" +
				"(" +
				"  idClient          	number not null," +
				"  idAmi				number not null," +
				"  constraint amiclient_pk primary key (idClient, idAmi)," +
				"  constraint amiclient_client_fk foreign key (idClient) references client (id) on delete cascade," +
				"  constraint amiclient_ami_fk foreign key (idAmi) references client (id) on delete cascade" +
				")";
		this.execQuery(query);
				 
		query =	"create table fondation" +
				"(" +
				"  id               	number not null," +
				"  nom					varchar2(150) not null," +
				"  description			varchar2(500) not null," +
				"  constraint fondation_pk primary key (id)," +
				"  constraint nom_uk1 unique (nom)" +
				" )";
		this.execQuery(query);
				 
		query =	"create table donUnique" +
				"(" +
				"  id               	number not null," +
				"  idClient				number not null," +
				"  idFondation			number not null," +
				"  montant				number(6,2) not null," +
				"  dateDon 				date default trunc(sysdate) not null, " +
				"  constraint donUnique_pk primary key (id)," +
				"  constraint donUnique_client_fk foreign key (idClient) references client (id) on delete cascade," +
				"  constraint donUnique_fondation_fk foreign key (idFondation) references fondation (id) on delete cascade" +
				")";
		this.execQuery(query);
				 
		query =	"create table partageGains" +
				"(" +
				"  id               	number not null," +
				"  idClient				number not null," +
				"  idFondation			number not null," +
				"  pourcentage			number(3) not null," +
				"  constraint partageGains_pk primary key (id)," +
				"  constraint partageGains_client_fk foreign key (idClient) references client (id) on delete cascade," +
				"  constraint partageGains_fondation_fk foreign key (idFondation) references fondation (id) on delete cascade" +
				")";
		this.execQuery(query);
		
		query =	"CREATE TABLE SALLE" +
				"(" +
				"	ID 					NUMBER NOT NULL ENABLE," +
				" 	NOM 				VARCHAR2(50) NOT NULL ENABLE," +
				" 	CONSTRAINT SALLE_PK PRIMARY KEY (ID) ENABLE," +
				" 	CONSTRAINT SALLE_UK1 UNIQUE (NOM) ENABLE" +
				")";
		this.execQuery(query);
		
		query =	"CREATE TABLE TYPEJEU" +
				"(" +
			    "	TYPE				VARCHAR2(50) NOT NULL," +
			    "	CONSTRAINT TYPEJEU_PK PRIMARY KEY (TYPE) ENABLE" +
			    ")";
		this.execQuery(query);
		
		query =	"CREATE TABLE JEU" +
				"(" +
				" 	ID 					NUMBER NOT NULL ENABLE," +
				"	IDSALLE				NUMBER NOT NULL ENABLE," +
				"	TYPE				VARCHAR2(50) NOT NULL," +
				" 	NOM 				VARCHAR2(50) NOT NULL ENABLE," +
				" 	DESCRIPTION 		VARCHAR2(1024)," +
				" 	REGLESJEU 			VARCHAR2(2048)," +
				" 	NBRJOUEURSMIN 		NUMBER(2,0) NOT NULL ENABLE," +
				" 	NBRJOUEURSMAX 		NUMBER(2,0) NOT NULL ENABLE," +
				" 	POSX 				NUMBER(4,0) NOT NULL ENABLE," +
				" 	POSY 				NUMBER(4,0) NOT NULL ENABLE," +
				" 	LARGEUR 			NUMBER(4,0) NOT NULL ENABLE," +
				" 	HAUTEUR 			NUMBER(4,0) NOT NULL ENABLE," +
				" 	CONSTRAINT JEU_PK PRIMARY KEY (ID) ENABLE," + 
				"	CONSTRAINT JEU_SALLE_FK FOREIGN KEY (IDSALLE) REFERENCES SALLE (ID) ON DELETE CASCADE ENABLE," +
				"	CONSTRAINT JEU_TYPE_FK FOREIGN KEY (TYPE) REFERENCES TYPEJEU (TYPE) ON DELETE CASCADE ENABLE," +
				" 	CONSTRAINT JEU_UK1 UNIQUE (NOM) ENABLE," +
				" 	CONSTRAINT JEU_CK1 CHECK (NBRJOUEURSMIN > 0) ENABLE," +
				" 	CONSTRAINT JEU_CK2 CHECK (NBRJOUEURSMAX > 0) ENABLE," +
				" 	CONSTRAINT JEU_CK3 CHECK (POSX >= 0) ENABLE," +
				" 	CONSTRAINT JEU_CK4 CHECK (POSY >= 0) ENABLE," +
				" 	CONSTRAINT JEU_CK5 CHECK (LARGEUR > 0) ENABLE," +
				" 	CONSTRAINT JEU_CK6 CHECK (HAUTEUR > 0) ENABLE" +
				")";
		this.execQuery(query);
	}
	
	private void createSequence() {
		System.out.println("Création des séquences...");
		
		this.execQuery("create sequence utilisateur_id_seq start with 1 increment by 1 nocache");
		this.execQuery("create sequence client_id_seq start with 1 increment by 1 nocache");
		this.execQuery("create sequence avatar_id_seq start with 1 increment by 1 nocache");
		this.execQuery("create sequence fondation_id_seq start with 1 increment by 1 nocache");
		this.execQuery("create sequence donUnique_id_seq start with 1 increment by 1 nocache");
		this.execQuery("create sequence partageGains_id_seq start with 1 increment by 1 nocache");
		this.execQuery("create sequence JEU_ID_SEQ start with 1 increment by 1 nocache");
		this.execQuery("create sequence SALLE_ID_SEQ start with 1 increment by 1 nocache");
	}
	
	private void createTriggers() {
		System.out.println("Création des triggers...");
		
		String query =	"create trigger utilisateur_bir_id_trg" +
				"  before insert on utilisateur" +
				"  for each row" +
				"    when (new.id is null)" +
				"      begin" +
				"        SELECT utilisateur_id_seq.nextval INTO :new.id from dual;" +
				"      end;";
		this.execQuery(query);
				
		query =	"create trigger client_bir_id_trg" +
				"  before insert on client" +
				"  for each row" +
				"    when (new.id is null)" +
				"      begin" +
				"        SELECT client_id_seq.nextval INTO :new.id from dual;" +
				"      end;";
		this.execQuery(query);
				
		query =	"create trigger avatar_bir_id_trg" +
				"  before insert on avatar" +
				"  for each row" +
				"    when (new.id is null)" +
				"      begin" +
				"        SELECT avatar_id_seq.nextval INTO :new.id from dual;" +
				"      end;";
		this.execQuery(query);
				
		query =	"create trigger fondation_bir_id_trg" +
				"  before insert on fondation" +
				"  for each row" +
				"    when (new.id is null)" +
				"      begin" +
				"        SELECT fondation_id_seq.nextval INTO :new.id from dual;" +
				"      end;";
		this.execQuery(query);
				
		query =	"create trigger donUnique_bir_id_trg" +
				"  before insert on donUnique" +
				"  for each row" +
				"    when (new.id is null)" +
				"      begin" +
				"        SELECT donUnique_id_seq.nextval INTO :new.id from dual;" +
				"      end;";
		this.execQuery(query);
				
		query =	"create trigger prtgGains_bir_prcnt_100_trg" +
				"  before insert on partageGains" +
				"  for each row" +
				"    when (new.idClient is not null)" +
				"      declare" +
				"        l_total_pourcentage number;" +
				"      begin" +
				"        SELECT sum(1) INTO l_total_pourcentage" +
				"          FROM partageGains" +
				"        WHERE idClient = :new.idClient;" +
				
				"        if ((l_total_pourcentage + :new.pourcentage) > 100) then" +
				"          raise_application_error(-20000, 'Un client ne peut pas partager plus que 100% de ses gains.');" +
				"        end if;" +
				"      end;";
		this.execQuery(query);
		
		query =	"create trigger JEU_BI_TRG" +
				"  before insert on JEU" +
				"  for each row" +
				"    when (new.id is null)" +
				"      begin" +
				"        SELECT JEU_ID_SEQ.nextval INTO :new.id from dual;" +
				"      end;";
		this.execQuery(query);
		
		query =	"create trigger SALLE_BI_TRG" +
				"  before insert on SALLE" +
				"  for each row" +
				"    when (new.id is null)" +
				"      begin" +
				"        SELECT SALLE_ID_SEQ.nextval INTO :new.id from dual;" +
				"      end;";
		this.execQuery(query);
	}
	
	public void initData() {
		System.out.println("Initialisation des données de base...");
		
		
		for(TypeJeu type : TypeJeu.values()) {
			this.ajouterTypeJeu(type.toString());
		}
	}
}
