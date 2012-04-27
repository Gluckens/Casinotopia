package ca.uqam.casinotopia.modele.serveur;

import java.sql.Date;
import java.util.Vector;

import ca.uqam.casinotopia.Avatar;
import ca.uqam.casinotopia.DonUniqueClient;
import ca.uqam.casinotopia.ListeAmis;
import ca.uqam.casinotopia.PartageGainsClient;
import ca.uqam.casinotopia.Salle;
import ca.uqam.casinotopia.Utilisateur;
import ca.uqam.casinotopia.modele.Modele;

public class ModeleClientServeur extends ModeleUtilisateurServeur implements Modele {
	
	private int id;
	private String prenom;
	private String nom;
	private Date dateNaissance;
	private String courriel;
	private int solde;
	private int pourcentageGlobal;
	private Salle salleCourante;
	private Vector<PartageGainsClient> partageGains = new Vector<PartageGainsClient>();
	private Vector<DonUniqueClient> donsUniques = new Vector<DonUniqueClient>();
	private ListeAmis listeAmis;
	private Avatar avatar;

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
