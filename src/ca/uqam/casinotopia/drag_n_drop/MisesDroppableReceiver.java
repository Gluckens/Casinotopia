package ca.uqam.casinotopia.drag_n_drop;

import java.awt.Point;

import ca.uqam.casinotopia.TypeMise;

public interface MisesDroppableReceiver {
	public void processDrop(Point p, TypeMise type);
}
