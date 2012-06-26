package ca.uqam.casinotopia.drag_n_drop.mises;

import java.awt.Point;

/**
 * Interface représentant un objet qui peut recevoir un drop
 */
public interface MisesDroppableReceiver {
	public void processDrop(Point p, int montant, String componentName, Point posDepart);
}