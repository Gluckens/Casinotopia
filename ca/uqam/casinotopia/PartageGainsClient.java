/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.uqam.casinotopia;

/**
 *
 * @author Alexei
 */
public class PartageGainsClient {
	private int pourcentage;
	public Client unnamed_Client_;
	public Fondation unnamed_Fondation_;

	public void setUnnamed_Client_(Client unnamed_Client_) {
		this.unnamed_Client_ = unnamed_Client_;
	}

	public Client getUnnamed_Client_() {
		return this.unnamed_Client_;
	}

	public void setUnnamed_Fondation_(Fondation unnamed_Fondation_) {
		this.unnamed_Fondation_ = unnamed_Fondation_;
	}

	public Fondation getUnnamed_Fondation_() {
		return this.unnamed_Fondation_;
	}

    public int getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(int pourcentage) {
        this.pourcentage = pourcentage;
    }

    @Override
    public String toString() {
        return this.getUnnamed_Client_().getPrenom() + " " + this.getUnnamed_Fondation_().getNom() + " : " + this.getPourcentage();
    }
    
    
        
}