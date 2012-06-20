package ca.uqam.casinotopia.drag_n_drop;

import java.awt.Point;

public class GhostDropEvent {
	private Point point;

	public GhostDropEvent(Point point) {
		this.point = point;
	}

	public Point getDropLocation() {
		return this.point;
	}
}