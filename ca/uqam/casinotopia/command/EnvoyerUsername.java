package ca.uqam.casinotopia.command;

import java.io.Serializable;

public class EnvoyerUsername implements Command, Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7429819389572695085L;
	
	private String username;
	
	@Override
	public void action() {
		
		
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
}