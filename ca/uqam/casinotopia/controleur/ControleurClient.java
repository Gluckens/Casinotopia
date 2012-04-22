package ca.uqam.casinotopia.controleur;

import ca.uqam.casinotopia.connexion.Connexion;
import ca.uqam.casinotopia.modele.client.ModelePrincipalClient;

public abstract class ControleurClient extends Controleur {
	
	private static final long serialVersionUID = 7316156272561396052L;
	
	protected ModelePrincipalClient modeleNav;
	
	public ControleurClient(ModelePrincipalClient modeleNav) {
		this.modeleNav = modeleNav;
	}
	
	public ControleurClient(Connexion connexion, ModelePrincipalClient modeleNav) {
		super(connexion);
		this.modeleNav = modeleNav;
	}
	
	public ModelePrincipalClient getModeleNav() {
		return this.modeleNav;
	}
}
