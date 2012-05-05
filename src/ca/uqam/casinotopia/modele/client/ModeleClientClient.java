package ca.uqam.casinotopia.modele.client;

import java.awt.Point;
import java.sql.Date;
import java.util.Vector;

import ca.uqam.casinotopia.AvatarClient;
import ca.uqam.casinotopia.DonUniqueClient;
import ca.uqam.casinotopia.ListeAmis;
import ca.uqam.casinotopia.PartageGainsClient;
import ca.uqam.casinotopia.Salle;
import ca.uqam.casinotopia.Utilisateur;
import ca.uqam.casinotopia.connexion.Connexion;
import ca.uqam.casinotopia.modele.Modele;
import ca.uqam.casinotopia.modif.TypeModif;
import ca.uqam.casinotopia.observateur.BaseObservable;
import ca.uqam.casinotopia.observateur.Observable;
import ca.uqam.casinotopia.observateur.Observateur;

public class ModeleClientClient extends Utilisateur implements Modele, Observable {

	private static final long serialVersionUID = -1820292102084346435L;
	
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
	private AvatarClient avatar;

	private BaseObservable sujet = new BaseObservable(this);
	
	public ModeleClientClient() {
		
	}

	public ModeleClientClient(int id) {
		this.id = id;
		this.avatar = new AvatarClient(this, id, "MON_SUPER_AVATAR");
	}
	
	public ModeleClientClient(int id, String pathImage) {
		this.id = id;
		this.avatar = new AvatarClient(this, id, pathImage);
	}
	
	public ModeleClientClient(int id, String pathImage, Point position) {
		this.id = id;
		this.avatar = new AvatarClient(this, id, pathImage, position);
	}
	
	public ModeleClientClient(String nomUtilisateur, Connexion connexion, int id, String prenom, String nom, Date dateNaissance, String courriel, int solde) {
		this(nomUtilisateur, connexion, id, prenom, nom, dateNaissance, courriel, solde, "AvatarClient" + id);
	}
	
	public ModeleClientClient(String nomUtilisateur, Connexion connexion, int id, String prenom, String nom, Date dateNaissance, String courriel, int solde, String pathImage) {
		this(nomUtilisateur, "", connexion, id, prenom, nom, dateNaissance, courriel, solde, 0, new Vector<PartageGainsClient>(), new Vector<DonUniqueClient>(), new ListeAmis(), pathImage);
	}
	
	public ModeleClientClient(String nomUtilisateur, Connexion connexion, int id, String prenom, String nom, Date dateNaissance, String courriel, int solde, AvatarClient avatar) {
		this(nomUtilisateur, "", connexion, id, prenom, nom, dateNaissance, courriel, solde, 0, new Vector<PartageGainsClient>(), new Vector<DonUniqueClient>(), new ListeAmis(), avatar);
	}
	
	public ModeleClientClient(String nomUtilisateur, String motDePasse, Connexion connexion, int id, String prenom, String nom, Date dateNaissance, String courriel, int solde) {
		this(nomUtilisateur, motDePasse, connexion, id, prenom, nom, dateNaissance, courriel, solde, "AvatarClient" + id);
	}
	
	public ModeleClientClient(String nomUtilisateur, String motDePasse, Connexion connexion, int id, String prenom, String nom, Date dateNaissance, String courriel, int solde, String pathImage) {
		this(nomUtilisateur, motDePasse, connexion, id, prenom, nom, dateNaissance, courriel, solde, 0, new Vector<PartageGainsClient>(), new Vector<DonUniqueClient>(), new ListeAmis(), pathImage);
	}
	
	public ModeleClientClient(String nomUtilisateur, String motDePasse, Connexion connexion, int id, String prenom, String nom, Date dateNaissance, String courriel, int solde, AvatarClient avatar) {
		this(nomUtilisateur, motDePasse, connexion, id, prenom, nom, dateNaissance, courriel, solde, 0, new Vector<PartageGainsClient>(), new Vector<DonUniqueClient>(), new ListeAmis(), avatar);
	}
	
	private ModeleClientClient(String nomUtilisateur, String motDePasse, Connexion connexion,
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
		
		this.avatar = new AvatarClient(this, this.id, pathImage);
	}
	
	private ModeleClientClient(String nomUtilisateur, String motDePasse, Connexion connexion,
								int id, String prenom, String nom, Date dateNaissance, String courriel, int solde, int pourcentageGlobal,
								Vector<PartageGainsClient> partageGains, Vector<DonUniqueClient> donsUniques, ListeAmis listeAmis, AvatarClient avatar) {
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
	
	
	/*public ModeleClientClient(String nomUtilisateur, Connexion connexion, int id, String prenom, String nom, Date dateNaissance, String courriel, int solde) {
		this(nomUtilisateur, connexion, id, prenom, nom, dateNaissance, courriel, solde, null);
	}
	
	public ModeleClientClient(String nomUtilisateur, Connexion connexion, int id, String prenom, String nom, Date dateNaissance, String courriel, int solde, AvatarClient avatar) {
		this(nomUtilisateur, "", connexion, id, prenom, nom, dateNaissance, courriel, solde, 0, new Vector<PartageGainsClient>(), new Vector<DonUniqueClient>(), new ListeAmis(), avatar);
	}
	
	public ModeleClientClient(String nomUtilisateur, String motDePasse, Connexion connexion, int id, String prenom, String nom, Date dateNaissance, String courriel, int solde) {
		this(nomUtilisateur, motDePasse, connexion, id, prenom, nom, dateNaissance, courriel, solde, null);
	}
	
	public ModeleClientClient(String nomUtilisateur, String motDePasse, Connexion connexion, int id, String prenom, String nom, Date dateNaissance, String courriel, int solde, AvatarClient avatar) {
		this(nomUtilisateur, motDePasse, connexion, id, prenom, nom, dateNaissance, courriel, solde, 0, new Vector<PartageGainsClient>(), new Vector<DonUniqueClient>(), new ListeAmis(), avatar);
	}
	
	private ModeleClientClient(String nomUtilisateur, String motDePasse, Connexion connexion,
								int id, String prenom, String nom, Date dateNaissance, String courriel, int solde, int pourcentageGlobal,
								Vector<PartageGainsClient> partageGains, Vector<DonUniqueClient> donsUniques, ListeAmis listeAmis, AvatarClient avatar) {
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
		
		if(avatar == null) {
			avatar = new AvatarClient(this, this.id, "AvatarClient" + this.id);
		}
		
		this.avatar = avatar;
	}*/

	public AvatarClient getAvatar() {
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

	public void setAvatar(AvatarClient avatar) {
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
	public void ajouterObservateur(Observateur obs) {
		this.sujet.ajouterObservateur(obs);
	}

	@Override
	public void retirerObservateur(Observateur obs) {
		this.sujet.retirerObservateur(obs);
	}

	@Override
	public boolean estObservePar(Observateur obs) {
		return this.sujet.estObservePar(obs);
	}

	@Override
	public void notifierObservateur() {
		this.sujet.notifierObservateur();
	}

	@Override
	public TypeModif getTypeModif() {
		// TODO Auto-generated method stub
		return null;
	}
}
