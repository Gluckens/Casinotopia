package ca.uqam.casinotopia;

import java.util.Vector;

import ca.uqam.casinotopia.modele.serveur.ModeleClientServeur;

public class JoueurBlackJack extends JoueurServeur {
	
	private static final long serialVersionUID = -4389812083197595474L;
	
	private int mise;
	private boolean optionMiseDouble;
	private Vector<Main> main = new Vector<Main>();
	

	public JoueurBlackJack(ModeleClientServeur client, Partie partie) {
		super(client, partie);
	}
}