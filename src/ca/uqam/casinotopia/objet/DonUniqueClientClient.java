package ca.uqam.casinotopia.objet;

import java.io.Serializable;
import java.sql.Date;
import java.util.Calendar;

import ca.uqam.casinotopia.modele.client.ModeleClientClient;


/**
 * Regroupe les informations d'un don d'un utilisateur côté client
 */
public class DonUniqueClientClient implements Serializable {
	
	private static final long serialVersionUID = -1286250629324268018L;
	
	/**
	 * Id du don
	 */
	private int id;
	
	/**
	 * Client ayant effectué le don
	 */
	private ModeleClientClient client;
	
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
	
	public DonUniqueClientClient(ModeleClientClient client, Fondation fondation, int montant) {
		this(-1, client, fondation, montant, (Date) Calendar.getInstance().getTime());
	}
	
	public DonUniqueClientClient(int id, ModeleClientClient client, Fondation fondation, int montant) {
		this(id, client, fondation, montant, (Date) Calendar.getInstance().getTime());
	}
	
	public DonUniqueClientClient(int id, ModeleClientClient client, Fondation fondation, int montant, Date dateDon) {
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

	public void setClient_(ModeleClientClient client) {
		this.client = client;
	}

	public ModeleClientClient getClient() {
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