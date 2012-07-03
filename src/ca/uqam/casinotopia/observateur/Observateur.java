package ca.uqam.casinotopia.observateur;

/**
 * Représente un objet qui peut en observer un autre
 */
public interface Observateur {
	/**
	 * Méthode appelé lors d'un changement à l'objet observé
	 * 
	 * @param observable L'objet observé ayant changé
	 */
	public void update(Observable observable);
}
