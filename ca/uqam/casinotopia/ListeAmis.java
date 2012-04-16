/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.uqam.casinotopia;

/**
 *
 * @author Alexei
 */
import java.util.Vector;

public class ListeAmis {
	private Vector<Client> clients = new Vector<Client>();

    public Vector<Client> getClients() {
        return clients;
    }

    public void setClients(Vector<Client> clients) {
        this.clients = clients;
    }
        
}
