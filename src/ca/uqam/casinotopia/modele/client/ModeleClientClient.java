package ca.uqam.casinotopia.modele.client;

import java.sql.Date;
import java.util.Vector;

import ca.uqam.casinotopia.modele.Modele;
import ca.uqam.casinotopia.objet.AvatarClient;
import ca.uqam.casinotopia.objet.DonUniqueClientClient;
import ca.uqam.casinotopia.objet.ListeAmisClient;
import ca.uqam.casinotopia.objet.PartageGainsClientClient;
import ca.uqam.casinotopia.objet.Utilisateur;
import ca.uqam.casinotopia.observateur.BaseObservable;
import ca.uqam.casinotopia.observateur.Observable;
import ca.uqam.casinotopia.observateur.Observateur;
import ca.uqam.casinotopia.type.modif.TypeModifClient;

public class ModeleClientClient extends Utilisateur implements Modele, Observable {
	
	private static final long serialVersionUID = -8344939066149150548L;
	
	private int id;
	private String prenom;
	private String nom;
	private Date dateNaissance;
	private String courriel;
	private int solde;
	private int prcGlobal;
	private ModeleSalleClient salleCourante;
	private Vector<PartageGainsClientClient> partageGains;
	private Vector<DonUniqueClientClient> donsUniques;
	private ListeAmisClient listeAmis;
	private AvatarClient avatar;
	
	private TypeModifClient typeModif;

	private BaseObservable sujet = new BaseObservable(this);
	
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
		this(idUtilisateur, nomUtilisateur, motDePasse, idClient, prenom, nom, dateNaissance, courriel, solde, 0, new Vector<PartageGainsClientClient>(), new Vector<DonUniqueClientClient>(), new ListeAmisClient(), new AvatarClient(idClient, pathImage));
	}
	
	public ModeleClientClient(int idUtilisateur, String nomUtilisateur, String motDePasse, int idClient, String prenom, String nom, Date dateNaissance, String courriel, int solde, AvatarClient avatar) {
		this(idUtilisateur, nomUtilisateur, motDePasse, idClient, prenom, nom, dateNaissance, courriel, solde, 0, new Vector<PartageGainsClientClient>(), new Vector<DonUniqueClientClient>(), new ListeAmisClient(), avatar);
	}
	
	private ModeleClientClient(int idUtilisateur, String nomUtilisateur, String motDePasse,
								int idClient, String prenom, String nom, Date dateNaissance, String courriel, int solde, int prcGlobal,
								Vector<PartageGainsClientClient> partageGains, Vector<DonUniqueClientClient> donsUniques, ListeAmisClient listeAmis, AvatarClient avatar) {
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
	
	public void modifierCompte(String prenom, String nom, Date dateNaissance, String courriel, int prcGlobal) {
		this.prenom = prenom;
		this.nom = nom;
		this.dateNaissance = dateNaissance;
		this.courriel = courriel;
		this.prcGlobal = prcGlobal;
	}

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

	public Vector<DonUniqueClientClient> getDonsUniques() {
		return this.donsUniques;
	}

	public void setDonsUniques(Vector<DonUniqueClientClient> donsUniques) {
		this.donsUniques = donsUniques;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ListeAmisClient getListeAmis() {
		return this.listeAmis;
	}

	public void setListeAmis(ListeAmisClient listeAmis) {
		this.listeAmis = listeAmis;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Vector<PartageGainsClientClient> getPartageGains() {
		return this.partageGains;
	}

	public void setPartageGains(Vector<PartageGainsClientClient> partageGains) {
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

	public ModeleSalleClient getSalleCourante() {
		return this.salleCourante;
	}

	public void setSalleCourante(ModeleSalleClient salleCourante) {
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