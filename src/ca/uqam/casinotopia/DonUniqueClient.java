package ca.uqam.casinotopia;

import java.io.Serializable;
import java.sql.Date;

import ca.uqam.casinotopia.modele.serveur.ModeleClientServeur;

/**
 * 
 * @author Alexei
 */
public class DonUniqueClient implements Serializable {
	
	private static final long serialVersionUID = 1796189943288471211L;
	
	private int id;
	private int montant;
	private ModeleClientServeur client;
	private Fondation fondation;
	private Date dateDon;

	public void setClient_(ModeleClientServeur client) {
		this.client = client;
	}

	public ModeleClientServeur getClient() {
		return this.client;
	}

	public void setFondation(Fondation fondation) {
		this.fondation = fondation;
	}

	public Fondation getFondation() {
		return this.fondation;
	}

	public int getId() {
		return this.id;
	}

	public Date getDateDon() {
		return this.dateDon;
	}

	public int getMontant() {
		return this.montant;
	}

	public void setDateDon(Date dateDon) {
		this.dateDon = dateDon;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setMontant(int montant) {
		this.montant = montant;
	}

}
