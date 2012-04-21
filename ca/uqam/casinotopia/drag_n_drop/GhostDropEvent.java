package ca.uqam.casinotopia.drag_n_drop;

import java.awt.Point;

public class GhostDropEvent {
	private Point point;
	private String action;

	public GhostDropEvent(String action, Point point) {
		this.action = action;
		this.point = point;
	}

	public String getAction() {
		return action;
	}

	public Point getDropLocation() {
		return point;
	}
}
