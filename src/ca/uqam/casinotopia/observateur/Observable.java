package ca.uqam.casinotopia.observateur;

import java.io.Serializable;

import ca.uqam.casinotopia.type.modif.TypeModif;

/**
 * Représente un objet observable par des observateurs
 */
public interface Observable extends Serializable {
	
	/**
	 * Ajouter un observateur
	 * 
	 * @param obs L'observateur à ajouter
	 */
	public void ajouterObservateur(Observateur obs);

	/**
	 * Retirer un observateur
	 * 
	 * @param obs L'observateur à retirer
	 */
	public void retirerObservateur(Observateur obs);

	/**
	 * Détermine si l'objet est observé par un observateur particulier
	 * 
	 * @param obs L'observateur à vérifier
	 * @return True si déjà observé, false sinon
	 */
	public boolean estObservePar(Observateur obs);

	/**
	 * Notifier tous les observateurs d'un changement de l'état de l'objet observable
	 */
	public void notifierObservateur();
	
	/**
	 * Récupérer le type de modification effectuée suite à la notification
	 * 
	 * @return Le type de modification
	 */
	public TypeModif getTypeModif();
}