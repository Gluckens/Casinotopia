/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.uqam.casinotopia;

/**
 *
 * @author Alexei
 */
import java.io.Serializable;
import java.util.Vector;

public class ListeAmis implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5810268166115357333L;
	private Vector<Client> clients = new Vector<Client>();

	public Vector<Client> getClients() {
		return this.clients;
	}

	public void setClients(Vector<Client> clients) {
		this.clients = clients;
	}

}
