package ca.uqam.casinotopia.modele.client;

import java.awt.Point;
import java.sql.Date;
import java.util.Vector;

import ca.uqam.casinotopia.Avatar;
import ca.uqam.casinotopia.AvatarClient;
import ca.uqam.casinotopia.DonUniqueClient;
import ca.uqam.casinotopia.ListeAmis;
import ca.uqam.casinotopia.PartageGainsClient;
import ca.uqam.casinotopia.Salle;
import ca.uqam.casinotopia.Utilisateur;
import ca.uqam.casinotopia.connexion.Connexion;
import ca.uqam.casinotopia.modele.Modele;
import ca.uqam.casinotopia.modif.TypeModifClient;
import ca.uqam.casinotopia.modif.TypeModifSalle;
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
	private int prcGlobal;
	private Salle salleCourante;
	private Vector<PartageGainsClient> partageGains;
	private Vector<DonUniqueClient> donsUniques;
	private ListeAmis listeAmis;
	private AvatarClient avatar;
	
	private TypeModifClient typeModif;

	private BaseObservable sujet = new BaseObservable(this);
	
	public ModeleClientClient() {
		
	}
	
	
	public ModeleClientClient(String nomUtilisateur, String motDePasse, String prenom, String nom, Date dateNaissance, String courriel, int solde) {
		this(-1, nomUtilisateur, motDePasse, -1, prenom, nom, dateNaissance, courriel, solde);
	}
	
	public ModeleClientClient(String nomUtilisateur, String motDePasse, String prenom, String nom, Date dateNaissance, String courriel, int solde, String pathImage) {
		this(-1, nomUtilisateur, motDePasse, -1, prenom, nom, dateNaissance, courriel, solde, pathImage);
	}
	
	public ModeleClientClient(String nomUtilisateur, String motDePasse, String prenom, String nom, Date dateNaissance, String courriel, int solde, AvatarClient avatar) {
		this(-1, nomUtilisateur, motDePasse, -1, prenom, nom, dateNaissance, courriel, solde, avatar);
	}
	
	public ModeleClientClient(int idUtilisateur, String nomUtilisateur, String motDePasse, int idClient, String prenom, String nom, Date dateNaissance, String courriel, int solde) {
		this(idUtilisateur, nomUtilisateur, motDePasse, idClient, prenom, nom, dateNaissance, courriel, solde, "/img/sans_avatar.gif");
	}
	
	public ModeleClientClient(int idUtilisateur, String nomUtilisateur, String motDePasse, int idClient, String prenom, String nom, Date dateNaissance, String courriel, int solde, String pathImage) {
		this(idUtilisateur, nomUtilisateur, motDePasse, idClient, prenom, nom, dateNaissance, courriel, solde, 0, new Vector<PartageGainsClient>(), new Vector<DonUniqueClient>(), new ListeAmis(), new AvatarClient(idClient, pathImage));
	}
	
	public ModeleClientClient(int idUtilisateur, String nomUtilisateur, String motDePasse, int idClient, String prenom, String nom, Date dateNaissance, String courriel, int solde, AvatarClient avatar) {
		this(idUtilisateur, nomUtilisateur, motDePasse, idClient, prenom, nom, dateNaissance, courriel, solde, 0, new Vector<PartageGainsClient>(), new Vector<DonUniqueClient>(), new ListeAmis(), avatar);
	}
	
	private ModeleClientClient(int idUtilisateur, String nomUtilisateur, String motDePasse,
								int idClient, String prenom, String nom, Date dateNaissance, String courriel, int solde, int prcGlobal,
								Vector<PartageGainsClient> partageGains, Vector<DonUniqueClient> donsUniques, ListeAmis listeAmis, AvatarClient avatar) {
		super(idUtilisateur, nomUtilisateur, motDePasse);
		
		this.id = idClient;
		this.prenom = prenom;
		this.nom = nom;
		this.dateNaissance = dateNaissance;
		this.courriel = courriel;
		this.solde = solde;
		this.prcGlobal = prcGlobal;
		this.partageGains = partageGains;
		this.donsUniques = donsUniques;
		this.listeAmis = listeAmis;
		
		this.avatar = avatar;
	}
	
	
	
	

	/*public ModeleClientClient(int id) {
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
								int id, String prenom, String nom, Date dateNaissance, String courriel, int solde, int prcGlobal,
								Vector<PartageGainsClient> partageGains, Vector<DonUniqueClient> donsUniques, ListeAmis listeAmis, String pathImage) {
		super(nomUtilisateur, motDePasse, connexion);
		
		this.id = id;
		this.prenom = prenom;
		this.nom = nom;
		this.dateNaissance = dateNaissance;
		this.courriel = courriel;
		this.solde = solde;
		this.pourcentageGlobal = prcGlobal;
		this.partageGains = partageGains;
		this.donsUniques = donsUniques;
		this.listeAmis = listeAmis;
		
		this.avatar = new AvatarClient(this, this.id, pathImage);
	}
	
	private ModeleClientClient(String nomUtilisateur, String motDePasse, Connexion connexion,
								int id, String prenom, String nom, Date dateNaissance, String courriel, int solde, int prcGlobal,
								Vector<PartageGainsClient> partageGains, Vector<DonUniqueClient> donsUniques, ListeAmis listeAmis, AvatarClient avatar) {
		super(nomUtilisateur, motDePasse, connexion);
		
		this.id = id;
		this.prenom = prenom;
		this.nom = nom;
		this.dateNaissance = dateNaissance;
		this.courriel = courriel;
		this.solde = solde;
		this.pourcentageGlobal = prcGlobal;
		this.partageGains = partageGains;
		this.donsUniques = donsUniques;
		this.listeAmis = listeAmis;
		
		this.avatar = avatar;
	}*/
	
	//////////////////////
	
	
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
								int id, String prenom, String nom, Date dateNaissance, String courriel, int solde, int prcGlobal,
								Vector<PartageGainsClient> partageGains, Vector<DonUniqueClient> donsUniques, ListeAmis listeAmis, AvatarClient avatar) {
		super(nomUtilisateur, motDePasse, connexion);
		
		this.id = id;
		this.prenom = prenom;
		this.nom = nom;
		this.dateNaissance = dateNaissance;
		this.courriel = courriel;
		this.solde = solde;
		this.pourcentageGlobal = prcGlobal;
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

	public void setAvatar(AvatarClient avatar) {
		this.avatar = avatar;
	}

	public String getCourriel() {
		return this.courriel;
	}

	public void setCourriel(String courriel) {
		this.courriel = courriel;
	}

	public Date getDateNaissance() {
		return this.dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public Vector<DonUniqueClient> getDonsUniques() {
		return this.donsUniques;
	}

	public void setDonsUniques(Vector<DonUniqueClient> donsUniques) {
		this.donsUniques = donsUniques;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ListeAmis getListeAmis() {
		return this.listeAmis;
	}

	public void setListeAmis(ListeAmis listeAmis) {
		this.listeAmis = listeAmis;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Vector<PartageGainsClient> getPartageGains() {
		return this.partageGains;
	}

	public void setPartageGains(Vector<PartageGainsClient> partageGains) {
		this.partageGains = partageGains;
	}

	public int getPrcGlobal() {
		return this.prcGlobal;
	}

	public void setPrcGlobal(int prcGlobal) {
		this.prcGlobal = prcGlobal;
	}

	public String getPrenom() {
		return this.prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Salle getSalleCourante() {
		return this.salleCourante;
	}

	public void setSalleCourante(Salle salleCourante) {
		this.salleCourante = salleCourante;
	}

	public int getSolde() {
		return this.solde;
	}

	public void setSolde(int solde) {
		this.solde = solde;
		this.typeModif = TypeModifClient.UPDATE_SOLDE;
		this.notifierObservateur();
	}
	
	public void updateSolde(int montant) {
		this.setSolde(this.solde + montant);
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
	public TypeModifClient getTypeModif() {
		return this.typeModif;
	}
}
