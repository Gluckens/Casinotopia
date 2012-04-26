/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.uqam.casinotopia;

import java.io.Serializable;

/**
 * 
 * @author Alexei
 */
public class PartageGainsClient implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4431084544215397445L;
	private int pourcentage;
	public Client client;
	public Fondation fondation;

	public Client getClient() {
		return this.client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Fondation getFondation() {
		return this.fondation;
	}

	public void setFondation(Fondation fondation) {
		this.fondation = fondation;
	}

	public int getPourcentage() {
		return this.pourcentage;
	}

	public void setPourcentage(int pourcentage) {
		this.pourcentage = pourcentage;
	}

	@Override
	public String toString() {
		return this.client.getPrenom() + " " + this.fondation.getNom() + " : " + this.getPourcentage();
	}

}