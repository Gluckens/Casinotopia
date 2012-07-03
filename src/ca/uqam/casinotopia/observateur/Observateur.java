package ca.uqam.casinotopia.observateur;

/**
 * Repr�sente un objet qui peut en observer un autre
 */
public interface Observateur {
	/**
	 * M�thode appel� lors d'un changement � l'objet observ�
	 * 
	 * @param observable L'objet observ� ayant chang�
	 */
	public void update(Observable observable);
}
