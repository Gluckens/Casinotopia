package ca.uqam.casinotopia.command;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import ca.uqam.casinotopia.console.CInput;

public class AfficherMenu implements Command, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2456319886577648875L;

	@Override
	public void action() {
		System.out.println("Que veut tu faire\n 1. afficher les autres users");
	}

	@Override
	public void repondre(ObjectOutputStream oos) {
		String message = CInput.readline();
		try {
			oos.writeUTF(message);
			oos.reset();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
