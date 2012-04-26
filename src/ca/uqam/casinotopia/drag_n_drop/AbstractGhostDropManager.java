package ca.uqam.casinotopia.drag_n_drop;

import java.awt.Component;
import java.awt.Point;
import javax.swing.SwingUtilities;

public abstract class AbstractGhostDropManager implements GhostDropListener {
	protected Component target;

	public AbstractGhostDropManager(Component target) {
		this.target = target;
	}

	protected Point getTranslatedPoint(Point point) {
		Point p = (Point) point.clone();
		SwingUtilities.convertPointFromScreen(p, this.target);
		return p;
	}

	protected boolean isInTarget(Point point) {
		return this.target.contains(point);
	}

	@Override
	public abstract void ghostDropped(GhostDropEvent e);
}