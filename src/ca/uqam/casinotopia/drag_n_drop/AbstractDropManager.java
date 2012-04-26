package ca.uqam.casinotopia.drag_n_drop;

import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JComponent;
import javax.swing.SwingUtilities;

public abstract class AbstractDropManager {
	protected JComponent component;

	public AbstractDropManager(JComponent component) {
		this.component = component;
	}

	protected Point getTranslatedPoint(Point point) {
		Point p = (Point) point.clone();
		SwingUtilities.convertPointFromScreen(p, this.component);
		return p;
	}

	protected boolean isInTarget(Point point) {
		Rectangle bounds = this.component.getBounds();
		return bounds.contains(point);
	}

	public abstract void ghostDropped(DropEvent e);
}
