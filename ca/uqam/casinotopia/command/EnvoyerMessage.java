package ca.uqam.casinotopia.command;

import java.io.ObjectOutputStream;
import java.io.Serializable;

public class EnvoyerMessage implements Command, Serializable {

	private String message;
	public EnvoyerMessage(String message) {
		this.message = message;
	}
	
	@Override
	public void action() {
		System.out.println(message);

	}

	@Override
	public void repondre(ObjectOutputStream oos) {
		// TODO Auto-generated method stub

	}

}
