package ca.uqam.casinotopia.command;

import java.io.ObjectOutputStream;
import java.io.Serializable;

public class EnvoyerListeUser implements Command, Serializable {

	private static final long serialVersionUID = -8255491512871662438L;
	
	private String liste;
	
	@Override
	public void action() {
		System.out.println("les user connecté sont : \n"+liste);
	}

	@Override
	public void repondre(ObjectOutputStream oos) {
		// TODO Auto-generated method stub

	}

	/**
	 * @return the liste
	 */
	public String getListe() {
		return this.liste;
	}

	/**
	 * @param liste the liste to set
	 */
	public void setListe(String liste) {
		this.liste = liste;
	}

}
