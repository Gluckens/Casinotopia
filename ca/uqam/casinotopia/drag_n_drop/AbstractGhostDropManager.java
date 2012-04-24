package ca.uqam.casinotopia.drag_n_drop;

import java.awt.Component;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.SwingUtilities;


public abstract class AbstractGhostDropManager implements GhostDropListener {
	protected Component component;

	public AbstractGhostDropManager(Component component) {
		this.component = component;
	}

	protected Point getTranslatedPoint(Point point) {
        Point p = (Point) point.clone();
        SwingUtilities.convertPointFromScreen(p, component);
		return p;
	}

	protected boolean isInTarget(Point point) {
		System.out.println(this.component);
		Rectangle bounds = component.getBounds();
		return bounds.contains(point);
	}

	public abstract void ghostDropped(GhostDropEvent e);
}