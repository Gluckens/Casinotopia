package ca.uqam.casinotopia.controleur;

import ca.uqam.casinotopia.connexion.Connexion;
import ca.uqam.casinotopia.modele.client.ModeleClientClient;
import ca.uqam.casinotopia.modele.client.ModelePrincipalClient;

public abstract class ControleurClient extends Controleur {

	private static final long serialVersionUID = 7316156272561396052L;

	protected ModelePrincipalClient modeleNav;
	protected ModeleClientClient client;
	
	public ControleurClient(ModelePrincipalClient modeleNav) {
		this(new Connexion(), null, modeleNav);
	}

	public ControleurClient(ModeleClientClient client, ModelePrincipalClient modeleNav) {
		this(new Connexion(), client, modeleNav);
	}

	public ControleurClient(Connexion connexion, ModeleClientClient client, ModelePrincipalClient modeleNav) {
		super(connexion);
		this.client = client;
		this.modeleNav = modeleNav;
	}

	public ModelePrincipalClient getModeleNav() {
		return this.modeleNav;
	}
}
