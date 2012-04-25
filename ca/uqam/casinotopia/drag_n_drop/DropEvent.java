package ca.uqam.casinotopia.drag_n_drop;

import java.awt.Point;

public class DropEvent {
	private Point point;

	public DropEvent(Point point) {
		this.point = point;
	}

	public Point getDropLocation() {
		return this.point;
	}

}
