package ca.uqam.casinotopia.drag_n_drop;

import java.awt.Point;

public interface MisesDroppableReceiver {
	public void processDrop(Point p, int montant, String componentName, Point posDepart);
}
