package ca.uqam.casinotopia.modele.serveur;

import java.sql.Date;
import java.util.Vector;

import ca.uqam.casinotopia.Avatar;
import ca.uqam.casinotopia.DonUniqueClient;
import ca.uqam.casinotopia.ListeAmis;
import ca.uqam.casinotopia.PartageGainsClient;
import ca.uqam.casinotopia.bd.CtrlBD;
import ca.uqam.casinotopia.modele.Modele;
import ca.uqam.casinotopia.modele.client.ModeleClientClient;
import ca.uqam.casinotopia.objet.Fondation;
import ca.uqam.casinotopia.objet.Utilisateur;

/**
 * Représente une instance de client
 */
@SuppressWarnings("serial")
public class ModeleClientServeur extends Utilisateur implements Modele  {
	
	/**
	 * Id du client
	 */
	private int id;
	
	/**
	 * Prénom du client
	 */
	private String prenom;
	
	/**
	 * Nom du client
	 */
	private String nom;
	
	/**
	 * Date de naissance du client
	 */
	private Date dateNaissance;
	
	/**
	 * Courriel du client
	 */
	private String courriel;
	
	/**
	 * Solde du client
	 */
	private int solde;
	
	/**
	 * Pourcentage globale de gains du client envoyé aux fondations
	 */
	private int prcGlobal;
	
	/**
	 * La salle dans lequel le client est présentement
	 */
	private ModeleSalleServeur salleCourante;
	
	/**
	 * Liste des partages de gains du client
	 */
	private Vector<PartageGainsClient> partageGains;
	
	/**
	 * Liste des dons unique du client
	 */
	private Vector<DonUniqueClient> donsUniques;
	
	/**
	 * Liste des amis du client
	 */
	private ListeAmis listeAmis;
	
	/**
	 * Avatar du client
	 */
	private Avatar avatar;
	
	
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
	
	/**
	 * Modifier les informations du client
	 * 
	 * @param prenom Son nouveau prénom
	 * @param nom Son nouveau nom
	 * @param dateNaissance Sa nouvelle date de naissaice
	 * @param courriel Son nouveau courriel
	 * @param prcGlobal Son nouveau pourcentage global
	 * @return True si les modifications ont réussi, false sinon
	 */
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
	
	/**
	 * Ajouter un ami au client
	 * 
	 * @param ami L'ami à ajouter
	 * @return True si l'ajout a fonctionné, false sinon
	 */
	public boolean ajouterAmi(ModeleClientServeur ami) {
		if(CtrlBD.BD.ajouterAmiClient(this, ami)) {
			this.listeAmis.ajouterAmi(ami);
			return true;
		}
		
		return false;
	}
	
	/**
	 * Ajouter un don à une fondation
	 * 
	 * @param fondation La fondation sur laquelle ajouter le don
	 * @param montant Le montant du don
	 * @return True si l'ajout a fonctionné, false sinon
	 */
	public boolean ajouterDon(Fondation fondation, int montant) {
		DonUniqueClient don = new DonUniqueClient(this, fondation, montant);
		if(CtrlBD.BD.ajouterDonUnique(don)) {
			this.donsUniques.add(don);
			return true;
		}
		
		return false;
	}
	
	/**
	 * Ajouter un partage de gain à une fondation
	 * 
	 * @param fondation La fondation sur laquelle ajouter le partage
	 * @param pourcentage Le pourcentage de partage des gains
	 * @return True si l'ajout a fonctionné, false sinon
	 */
	public boolean ajouterPartageGains(Fondation fondation, int pourcentage) {
		PartageGainsClient partageGains = new PartageGainsClient(this, fondation, pourcentage);
		if(CtrlBD.BD.ajouterPartageGains(partageGains)) {
			this.partageGains.add(partageGains);
			return true;
		}
		
		return false;
	}
	
	/**
	 * Modifier le pourcentage de partage des gains à une fondation
	 * 
	 * @param fondation La fondation sur laquelle modifier le pourcentage
	 * @param pourcentage Le nouveau pourcentage de partage des gains
	 * @return True si la modification a fonctionnée, false sinon
	 */
	public boolean modifierPartageGains(PartageGainsClient partageGains, int nouveauPourcentage) {
		if(CtrlBD.BD.modifierPartageGains(partageGains.getClient().getId(), partageGains.getFondation().getId(), nouveauPourcentage)) {
			partageGains.setPourcentage(nouveauPourcentage);
			return true;
		}
		
		return false;
	}
	
	/**
	 * Ajouter/Retirer un montant du solde du client
	 * 
	 * @param montant Le montant à ajouter/retirer
	 * @return True si la modification a fonctionnée, false sinon
	 */
	public boolean updateSolde(int montant) {
		return this.modifierSolde(this.solde + montant);
	}

	/**
	 * Mise à jour du solde du client.
	 * 
	 * @param nouveauSolde Le nouveau solde du client
	 * @return True si la modification a fonctionnée, false sinon
	 */
	public boolean modifierSolde(int nouveauSolde) {
		if(CtrlBD.BD.modifierSoldeClient(this, nouveauSolde)) {
			this.solde = nouveauSolde;
			return true;
		}
		
		return false;
	}
	
	/**
	 * Créer la version client du modèle client.
	 * 
	 * @return La version client du modèle client
	 */
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
