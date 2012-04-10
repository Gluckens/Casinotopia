package ca.uqam.casinotopia.command;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import ca.uqam.casinotopia.console.CInput;

public class DemanderUsername implements Command, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2874282636134230051L;

	@Override
	public void action() {

		System.out.println("Quel est ton nom?");
		
	}

	@Override
	public void repondre(ObjectOutputStream oos) {
		String message = CInput.readline();
		try {
			Command cmd = new EnvoyerUsername(message);
			oos.writeObject(cmd);
			oos.reset();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	
}