package ca.uqam.casinotopia.modele.serveur;

import java.sql.Date;
import java.util.Vector;

import ca.uqam.casinotopia.Avatar;
import ca.uqam.casinotopia.DonUniqueClient;
import ca.uqam.casinotopia.ListeAmis;
import ca.uqam.casinotopia.PartageGainsClient;
import ca.uqam.casinotopia.Salle;
import ca.uqam.casinotopia.Utilisateur;
import ca.uqam.casinotopia.connexion.Connexion;
import ca.uqam.casinotopia.modele.Modele;

@SuppressWarnings("serial")
public class ModeleClientServeur extends Utilisateur implements Modele {
	
	private int id;
	private String prenom;
	private String nom;
	private Date dateNaissance;
	private String courriel;
	private int solde;
	private int pourcentageGlobal;
	private Salle salleCourante;
	private Vector<PartageGainsClient> partageGains;
	private Vector<DonUniqueClient> donsUniques;
	private ListeAmis listeAmis;
	private Avatar avatar;
	
	public ModeleClientServeur() {
		
	}
	
	public ModeleClientServeur(String nomUtilisateur, Connexion connexion, int id, String prenom, String nom, Date dateNaissance, String courriel, int solde) {
		this(nomUtilisateur, connexion, id, prenom, nom, dateNaissance, courriel, solde, "AvatarClient" + id);
	}
	
	public ModeleClientServeur(String nomUtilisateur, Connexion connexion, int id, String prenom, String nom, Date dateNaissance, String courriel, int solde, String pathImage) {
		this(nomUtilisateur, "", connexion, id, prenom, nom, dateNaissance, courriel, solde, 0, new Vector<PartageGainsClient>(), new Vector<DonUniqueClient>(), new ListeAmis(), pathImage);
	}
	
	public ModeleClientServeur(String nomUtilisateur, Connexion connexion, int id, String prenom, String nom, Date dateNaissance, String courriel, int solde, Avatar avatar) {
		this(nomUtilisateur, "", connexion, id, prenom, nom, dateNaissance, courriel, solde, 0, new Vector<PartageGainsClient>(), new Vector<DonUniqueClient>(), new ListeAmis(), avatar);
	}
	
	public ModeleClientServeur(String nomUtilisateur, String motDePasse, Connexion connexion, int id, String prenom, String nom, Date dateNaissance, String courriel, int solde) {
		this(nomUtilisateur, motDePasse, connexion, id, prenom, nom, dateNaissance, courriel, solde, "AvatarClient" + id);
	}
	
	public ModeleClientServeur(String nomUtilisateur, String motDePasse, Connexion connexion, int id, String prenom, String nom, Date dateNaissance, String courriel, int solde, String pathImage) {
		this(nomUtilisateur, motDePasse, connexion, id, prenom, nom, dateNaissance, courriel, solde, 0, new Vector<PartageGainsClient>(), new Vector<DonUniqueClient>(), new ListeAmis(), pathImage);
	}
	
	public ModeleClientServeur(String nomUtilisateur, String motDePasse, Connexion connexion, int id, String prenom, String nom, Date dateNaissance, String courriel, int solde, Avatar avatar) {
		this(nomUtilisateur, motDePasse, connexion, id, prenom, nom, dateNaissance, courriel, solde, 0, new Vector<PartageGainsClient>(), new Vector<DonUniqueClient>(), new ListeAmis(), avatar);
		
		this.avatar = new Avatar(this, this.id, "AvatarClient" + this.id);
	}
	
	private ModeleClientServeur(String nomUtilisateur, String motDePasse, Connexion connexion,
								int id, String prenom, String nom, Date dateNaissance, String courriel, int solde, int pourcentageGlobal,
								Vector<PartageGainsClient> partageGains, Vector<DonUniqueClient> donsUniques, ListeAmis listeAmis, String pathImage) {
		super(nomUtilisateur, motDePasse, connexion);
		
		this.id = id;
		this.prenom = prenom;
		this.nom = nom;
		this.dateNaissance = dateNaissance;
		this.courriel = courriel;
		this.solde = solde;
		this.pourcentageGlobal = pourcentageGlobal;
		this.partageGains = partageGains;
		this.donsUniques = donsUniques;
		this.listeAmis = listeAmis;
		
		this.avatar = new Avatar(this, this.id, pathImage);
	}
	
	private ModeleClientServeur(String nomUtilisateur, String motDePasse, Connexion connexion,
								int id, String prenom, String nom, Date dateNaissance, String courriel, int solde, int pourcentageGlobal,
								Vector<PartageGainsClient> partageGains, Vector<DonUniqueClient> donsUniques, ListeAmis listeAmis, Avatar avatar) {
		super(nomUtilisateur, motDePasse, connexion);
		
		this.id = id;
		this.prenom = prenom;
		this.nom = nom;
		this.dateNaissance = dateNaissance;
		this.courriel = courriel;
		this.solde = solde;
		this.pourcentageGlobal = pourcentageGlobal;
		this.partageGains = partageGains;
		this.donsUniques = donsUniques;
		this.listeAmis = listeAmis;
		
		this.avatar = avatar;
	}

	public Avatar getAvatar() {
		return this.avatar;
	}

	public String getCourriel() {
		return this.courriel;
	}

	public Date getDateNaissance() {
		return this.dateNaissance;
	}

	public Vector<DonUniqueClient> getDonsUniques() {
		return this.donsUniques;
	}

	public ListeAmis getListeAmis() {
		return this.listeAmis;
	}

	public String getNom() {
		return this.nom;
	}

	public Vector<PartageGainsClient> getPartageGains() {
		return this.partageGains;
	}

	public int getPourcentageGlobal() {
		return this.pourcentageGlobal;
	}

	public String getPrenom() {
		return this.prenom;
	}

	public Salle getSalleCourante() {
		return this.salleCourante;
	}

	public int getSolde() {
		return this.solde;
	}

	public void setAvatar(Avatar avatar) {
		this.avatar = avatar;
	}

	public void setCourriel(String courriel) {
		this.courriel = courriel;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public void setDonsUniques(Vector<DonUniqueClient> donsUniques) {
		this.donsUniques = donsUniques;
	}

	public void setListeAmis(ListeAmis listeAmis) {
		this.listeAmis = listeAmis;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setPartageGains(Vector<PartageGainsClient> partageGains) {
		this.partageGains = partageGains;
	}

	public void setPourcentageGlobal(int pourcentageGlobal) {
		this.pourcentageGlobal = pourcentageGlobal;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public void setSalleCourante(Salle salleCourante) {
		this.salleCourante = salleCourante;
	}

	public void setSolde(int solde) {
		this.solde = solde;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		String client;
		client = this.getNomUtilisateur() + " " + this.getNom() + " " + this.getPrenom() + " " + this.getCourriel() + " " + this.getSolde() + "\n";
		
		for(PartageGainsClient partageGain : this.partageGains) {
			client += " partage gains : " + partageGain + " -- \t ";
		}
		
		for(DonUniqueClient donUnique : this.donsUniques) {
			client += " don unique : " + donUnique + " -- \t ";
		}
		
		return client;
	}
}
