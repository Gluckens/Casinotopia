package ca.uqam.casinotopia.command;

import java.io.ObjectOutputStream;
import java.io.Serializable;

public class EnvoyerUsername implements Serializable, Command {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7775344224886698483L;
	private String username;
	
	public EnvoyerUsername(String username) {
		this.username = username;
	}

	@Override
	public void action() {
		System.out.println("le client a envoyer le username "+username+" mais je sais pas comment l'affecter ptete avec un model?");

	}

	@Override
	public void repondre(ObjectOutputStream oos) {
		// TODO Auto-generated method stub

	}
	
	public String getUsername() {
		return username;
	}

}
