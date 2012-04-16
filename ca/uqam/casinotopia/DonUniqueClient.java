/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.uqam.casinotopia;

import java.sql.Date;

/**
 *
 * @author Alexei
 */
public class DonUniqueClient {
        private int id;
	private int montant;
	private Client unClient;
	private Fondation uneFondation;
        private Date dateDon;

	public void setClient_(Client unnamed_Client_) {
		this.unClient = unnamed_Client_;
	}

	public Client getClient_() {
		return this.unClient;
	}

	public void setFondation_(Fondation unnamed_Fondation_) {
		this.uneFondation = unnamed_Fondation_;
	}

	public Fondation getFondation_() {
		return this.uneFondation;
	}

    public int getId() {
        return id;
    }

    public Date getDateDon() {
        return dateDon;
    }

    public int getMontant() {
        return montant;
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
