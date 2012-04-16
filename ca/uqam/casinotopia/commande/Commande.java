package ca.uqam.casinotopia.commande;

import java.io.Serializable;

import javax.swing.JFrame;

import ca.uqam.casinotopia.controleur.Controleur;

public interface Commande extends Serializable {
	
	//TODO pourquoi ya JFrame
	public void action(Controleur controleur);//, JFrame frame);
}
