package ca.uqam.casinotopia.commande.serveur.salle;

import java.awt.Point;

import ca.uqam.casinotopia.commande.CommandeServeurControleurSalle;
import ca.uqam.casinotopia.controleur.Controleur;
import ca.uqam.casinotopia.controleur.serveur.ControleurSalleServeur;

public class CmdDeplacerAvatar implements CommandeServeurControleurSalle {
	
	private static final long serialVersionUID = 8823210478748084311L;
	
	private Point position;
	
	public CmdDeplacerAvatar(Point position) {
		this.position = position;
	}

	@Override
	public void action(Controleur controleur) {
		((ControleurSalleServeur) controleur).actionDeplacerAvatar(this.position);
	}
}