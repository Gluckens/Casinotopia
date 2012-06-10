package ca.uqam.casinotopia.modele.serveur;

import java.io.Serializable;
import java.sql.Date;
import java.util.Vector;

import ca.uqam.casinotopia.Avatar;
import ca.uqam.casinotopia.AvatarClient;
import ca.uqam.casinotopia.DonUniqueClient;
import ca.uqam.casinotopia.Fondation;
import ca.uqam.casinotopia.ListeAmis;
import ca.uqam.casinotopia.PartageGainsClient;
import ca.uqam.casinotopia.Utilisateur;
import ca.uqam.casinotopia.bd.CtrlBD;
import ca.uqam.casinotopia.connexion.Connexion;
import ca.uqam.casinotopia.modele.Modele;
import ca.uqam.casinotopia.modele.client.ModeleClientClient;

@SuppressWarnings("serial")
public class ModeleClientServeur extends Utilisateur implements Modele  {
	
	private int id;
	private String prenom;
	private String nom;
	private Date dateNaissance;
	private String courriel;
	private int solde;
	private int prcGlobal;
	private ModeleSalleServeur salleCourante;
	private Vector<PartageGainsClient> partageGains;
	private Vector<DonUniqueClient> donsUniques;
	private ListeAmis listeAmis;
	private Avatar avatar;
	
	public ModeleClientServeur() {
		
	}
	
	
	
	
	
	
	
	/*
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
		
		//this.avatar = new Avatar(this, this.id, "AvatarClient" + this.id);
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
	}*/
	
	
	
	
	/*
	
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
		
		//this.avatar = new Avatar(this, this.id, "AvatarClient" + this.id);
	}*/
	
	
	
	
	
	
	public ModeleClientServeur(String nomUtilisateur, String motDePasse, String prenom, String nom, Date dateNaissance, String courriel, int solde) {
		this(-1, nomUtilisateur, motDePasse, -1, prenom, nom, dateNaissance, courriel, solde);
	}
	
	public ModeleClientServeur(String nomUtilisateur, String motDePasse, String prenom, String nom, Date dateNaissance, String courriel, int solde, String pathImage) {
		this(-1, nomUtilisateur, motDePasse, -1, prenom, nom, dateNaissance, courriel, solde, pathImage);
	}
	
	public ModeleClientServeur(String nomUtilisateur, String motDePasse, String prenom, String nom, Date dateNaissance, String courriel, int solde, Avatar avatar) {
		this(-1, nomUtilisateur, motDePasse, -1, prenom, nom, dateNaissance, courriel, solde, avatar);
	}
	
	public ModeleClientServeur(int idUtilisateur, String nomUtilisateur, String motDePasse, int idClient, String prenom, String nom, Date dateNaissance, String courriel, int solde) {
		this(idUtilisateur, nomUtilisateur, motDePasse, idClient, prenom, nom, dateNaissance, courriel, solde, "/img/sans_avatar.gif");
	}
	
	public ModeleClientServeur(int idUtilisateur, String nomUtilisateur, String motDePasse, int idClient, String prenom, String nom, Date dateNaissance, String courriel, int solde, String pathImage) {
		this(idUtilisateur, nomUtilisateur, motDePasse, idClient, prenom, nom, dateNaissance, courriel, solde, 0, new Vector<PartageGainsClient>(), new Vector<DonUniqueClient>(), new ListeAmis(), new Avatar(idClient, pathImage));
	}
	
	public ModeleClientServeur(int idUtilisateur, String nomUtilisateur, String motDePasse, int idClient, String prenom, String nom, Date dateNaissance, String courriel, int solde, Avatar avatar) {
		this(idUtilisateur, nomUtilisateur, motDePasse, idClient, prenom, nom, dateNaissance, courriel, solde, 0, new Vector<PartageGainsClient>(), new Vector<DonUniqueClient>(), new ListeAmis(), avatar);
	}
	
	private ModeleClientServeur(int idUtilisateur, String nomUtilisateur, String motDePasse,
								int idClient, String prenom, String nom, Date dateNaissance, String courriel, int solde, int prcGlobal,
								Vector<PartageGainsClient> partageGains, Vector<DonUniqueClient> donsUniques, ListeAmis listeAmis, Avatar avatar) {
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
	
	
	
	
	
	
	
	
	
	
	
	/*public ModeleClientServeur(String nomUtilisateur, String motDePasse, int idClient, String prenom, String nom, Date dateNaissance, String courriel, int solde) {
		this(-1, nomUtilisateur, idClient, prenom, nom, dateNaissance, courriel, solde);
	}
	
	public ModeleClientServeur(String nomUtilisateur, String motDePasse, int idClient, String prenom, String nom, Date dateNaissance, String courriel, int solde, String pathImage) {
		this(-1, nomUtilisateur, idClient, prenom, nom, dateNaissance, courriel, solde, pathImage);
	}
	
	public ModeleClientServeur(String nomUtilisateur, String motDePasse, int idClient, String prenom, String nom, Date dateNaissance, String courriel, int solde, Avatar avatar) {
		this(-1, nomUtilisateur, idClient, prenom, nom, dateNaissance, courriel, solde, avatar);
	}
	
	public ModeleClientServeur(int idUtilisateur, String nomUtilisateur, int idClient, String prenom, String nom, Date dateNaissance, String courriel, int solde) {
		this(idUtilisateur, nomUtilisateur, idClient, prenom, nom, dateNaissance, courriel, solde, "/img/sans_avatar.gif");
	}
	
	public ModeleClientServeur(int idUtilisateur, String nomUtilisateur, int idClient, String prenom, String nom, Date dateNaissance, String courriel, int solde, String pathImage) {
		this(idUtilisateur, nomUtilisateur, "", idClient, prenom, nom, dateNaissance, courriel, solde, 0, new Vector<PartageGainsClient>(), new Vector<DonUniqueClient>(), new ListeAmis(), new Avatar(idClient, pathImage));
	}
	
	public ModeleClientServeur(int idUtilisateur, String nomUtilisateur, int idClient, String prenom, String nom, Date dateNaissance, String courriel, int solde, Avatar avatar) {
		this(idUtilisateur, nomUtilisateur, "", idClient, prenom, nom, dateNaissance, courriel, solde, 0, new Vector<PartageGainsClient>(), new Vector<DonUniqueClient>(), new ListeAmis(), avatar);
	}*/
	
	
	
	
	
	
	
	
	/*public ModeleClientServeur(int idUtilisateur, String nomUtilisateur, int idClient, String prenom, String nom, Date dateNaissance, String courriel, int solde) {
		this(idUtilisateur, nomUtilisateur, idClient, prenom, nom, dateNaissance, courriel, solde, "/img/sans_avatar.gif");
	}
	
	public ModeleClientServeur(int idUtilisateur, String nomUtilisateur, int idClient, String prenom, String nom, Date dateNaissance, String courriel, int solde, String pathImage) {
		this(idUtilisateur, nomUtilisateur, "", idClient, prenom, nom, dateNaissance, courriel, solde, 0, new Vector<PartageGainsClient>(), new Vector<DonUniqueClient>(), new ListeAmis(), pathImage);
	}
	
	public ModeleClientServeur(int idUtilisateur, String nomUtilisateur, int idClient, String prenom, String nom, Date dateNaissance, String courriel, int solde, Avatar avatar) {
		this(idUtilisateur, nomUtilisateur, "", idClient, prenom, nom, dateNaissance, courriel, solde, 0, new Vector<PartageGainsClient>(), new Vector<DonUniqueClient>(), new ListeAmis(), avatar);
	}*/
	
	/*private ModeleClientServeur(int idUtilisateur, String nomUtilisateur, String motDePasse,
								int idClient, String prenom, String nom, Date dateNaissance, String courriel, int solde, int pourcentageGlobal,
								Vector<PartageGainsClient> partageGains, Vector<DonUniqueClient> donsUniques, ListeAmis listeAmis, String pathImage) {
		super(idUtilisateur, nomUtilisateur, motDePasse);
		
		this.id = idClient;
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
	
	private ModeleClientServeur(int idUtilisateur, String nomUtilisateur, String motDePasse,
								int idClient, String prenom, String nom, Date dateNaissance, String courriel, int solde, int pourcentageGlobal,
								Vector<PartageGainsClient> partageGains, Vector<DonUniqueClient> donsUniques, ListeAmis listeAmis, Avatar avatar) {
		super(idUtilisateur, nomUtilisateur, motDePasse);
		
		this.id = idClient;
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
	}*/
	
	
	
	
	public boolean modifierInformations(String prenom, String nom, Date dateNaissance, String courriel, int prcGlobal) {
		if(CtrlBD.BD.modifierClient(this.id, prenom, nom, dateNaissance, courriel, prcGlobal)) {
			this.prenom = prenom;
			this.nom = nom;
			this.dateNaissance = dateNaissance;
			this.courriel = courriel;
			this.prcGlobal = prcGlobal;
			return true;
		}
		
		return false;
	}
	
	public boolean ajouterAmi(ModeleClientServeur ami) {
		if(CtrlBD.BD.ajouterAmiClient(this, ami)) {
			this.listeAmis.ajouterAmi(ami);
			return true;
		}
		
		return false;
	}
	
	public boolean ajouterDon(Fondation fondation, int montant) {
		DonUniqueClient don = new DonUniqueClient(this, fondation, montant);
		if(CtrlBD.BD.ajouterDonUnique(don)) {
			this.donsUniques.add(don);
			return true;
		}
		
		return false;
	}
	
	public boolean ajouterPartageGains(Fondation fondation, int pourcentage) {
		PartageGainsClient partageGains = new PartageGainsClient(this, fondation, pourcentage);
		if(CtrlBD.BD.ajouterPartageGains(partageGains)) {
			this.partageGains.add(partageGains);
			return true;
		}
		
		return false;
	}
	
	public boolean modifierPartageGains(PartageGainsClient partageGains, int nouveauPourcentage) {
		if(CtrlBD.BD.modifierPartageGains(partageGains.getClient().getId(), partageGains.getFondation().getId(), nouveauPourcentage)) {
			partageGains.setPourcentage(nouveauPourcentage);
			return true;
		}
		
		return false;
	}
	
	public boolean updateSolde(int montant) {
		return this.modifierSolde(this.solde + montant);
	}

	public boolean modifierSolde(int nouveauSolde) {
		if(CtrlBD.BD.modifierSoldeClient(this, nouveauSolde)) {
			this.solde = nouveauSolde;
			return true;
		}
		
		return false;
	}
	
	public ModeleClientClient creerModeleClient() {
		ModeleClientClient modeleClientClient = new ModeleClientClient(
				this.idUtilisateur,
				this.nomUtilisateur,
				this.motDePasse,
				this.id,
				this.prenom,
				this.nom,
				this.dateNaissance,
				this.courriel,
				this.solde,
				this.avatar.creerModeleClient()
		);
		
		return modeleClientClient;
	}
	


	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPrenom() {
		return this.prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Date getDateNaissance() {
		return this.dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public String getCourriel() {
		return this.courriel;
	}

	public void setCourriel(String courriel) {
		this.courriel = courriel;
	}

	public int getSolde() {
		return this.solde;
	}

	public void setSolde(int solde) {
		this.solde = solde;
	}

	public Avatar getAvatar() {
		return this.avatar;
	}

	public void setAvatar(Avatar avatar) {
		this.avatar = avatar;
	}

	public ListeAmis getListeAmis() {
		return this.listeAmis;
	}

	public void setListeAmis(ListeAmis listeAmis) {
		this.listeAmis = listeAmis;
	}

	public int getPrcGlobal() {
		return this.prcGlobal;
	}
	
	public void setPrcGlobal(int prcGlobal) {
		this.prcGlobal = prcGlobal;
	}

	public ModeleSalleServeur getSalleCourante() {
		return this.salleCourante;
	}

	public void setSalleCourante(ModeleSalleServeur salleCourante) {
		this.salleCourante = salleCourante;
	}

	public Vector<DonUniqueClient> getDonsUniques() {
		return this.donsUniques;
	}

	public void setDonsUniques(Vector<DonUniqueClient> donsUniques) {
		this.donsUniques = donsUniques;
	}
	
	public Vector<PartageGainsClient> getPartageGains() {
		return this.partageGains;
	}

	public void setPartageGains(Vector<PartageGainsClient> partageGains) {
		this.partageGains = partageGains;
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
