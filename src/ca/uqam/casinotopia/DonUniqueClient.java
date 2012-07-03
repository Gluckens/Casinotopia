package ca.uqam.casinotopia;

import java.sql.Date;
import java.util.Calendar;

import ca.uqam.casinotopia.modele.serveur.ModeleClientServeur;
import ca.uqam.casinotopia.objet.Fondation;

/**
 * Regroupe les informations d'un don d'un utilisateur côté serveur
 */
public class DonUniqueClient {
	
	/**
	 * Id du don
	 */
	private int id;
	
	/**
	 * Client ayant effectué le don
	 */
	private ModeleClientServeur client;
	
	/**
	 * Fondation ayant reçu le don
	 */
	private Fondation fondation;
	
	/**
	 * Montant du don
	 */
	private int montant;
	
	/**
	 * Date du don
	 */
	private Date dateDon;
	
	public DonUniqueClient(ModeleClientServeur client, Fondation fondation, int montant) {
		this(-1, client, fondation, montant, (Date) Calendar.getInstance().getTime());
	}
	
	public DonUniqueClient(int id, ModeleClientServeur client, Fondation fondation, int montant) {
		this(id, client, fondation, montant, (Date) Calendar.getInstance().getTime());
	}
	
	public DonUniqueClient(int id, ModeleClientServeur client, Fondation fondation, int montant, Date dateDon) {
		this.id = id;
		this.client = client;
		this.fondation = fondation;
		this.montant = montant;
		this.dateDon = dateDon;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setFondation(Fondation fondation) {
		this.fondation = fondation;
	}

	public Fondation getFondation() {
		return this.fondation;
	}

	public void setClient_(ModeleClientServeur client) {
		this.client = client;
	}

	public ModeleClientServeur getClient() {
		return this.client;
	}

	public int getMontant() {
		return this.montant;
	}

	public void setMontant(int montant) {
		this.montant = montant;
	}

	public Date getDateDon() {
		return this.dateDon;
	}

	public void setDateDon(Date dateDon) {
		this.dateDon = dateDon;
	}
}