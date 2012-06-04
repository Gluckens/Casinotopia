package ca.uqam.casinotopia.commande.serveur;

import java.awt.Point;

import ca.uqam.casinotopia.commande.CommandeServeurControleurSalle;
import ca.uqam.casinotopia.controleur.Controleur;
import ca.uqam.casinotopia.controleur.serveur.ControleurSalleServeur;

public class CmdDeplacerAvatar implements CommandeServeurControleurSalle {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3403015283306065119L;
	private Point position;
	
	public CmdDeplacerAvatar(Point position) {
		this.position = position;
	}

	@Override
	public void action(Controleur controleur) {
		((ControleurSalleServeur) controleur).actionDeplacerAvatar(this.position);
	}
}
