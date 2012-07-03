package ca.uqam.casinotopia.observateur;

import java.io.Serializable;

import ca.uqam.casinotopia.type.modif.TypeModif;

/**
 * Repr�sente un objet observable par des observateurs
 */
public interface Observable extends Serializable {
	
	/**
	 * Ajouter un observateur
	 * 
	 * @param obs L'observateur � ajouter
	 */
	public void ajouterObservateur(Observateur obs);

	/**
	 * Retirer un observateur
	 * 
	 * @param obs L'observateur � retirer
	 */
	public void retirerObservateur(Observateur obs);

	/**
	 * D�termine si l'objet est observ� par un observateur particulier
	 * 
	 * @param obs L'observateur � v�rifier
	 * @return True si d�j� observ�, false sinon
	 */
	public boolean estObservePar(Observateur obs);

	/**
	 * Notifier tous les observateurs d'un changement de l'�tat de l'objet observable
	 */
	public void notifierObservateur();
	
	/**
	 * R�cup�rer le type de modification effectu�e suite � la notification
	 * 
	 * @return Le type de modification
	 */
	public TypeModif getTypeModif();
}