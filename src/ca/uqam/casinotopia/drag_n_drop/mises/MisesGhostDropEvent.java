package ca.uqam.casinotopia.drag_n_drop.mises;

import java.awt.Point;

import ca.uqam.casinotopia.drag_n_drop.GhostDropEvent;

public class MisesGhostDropEvent extends GhostDropEvent {

	private int montant;
	private String componentName;
	private Point pDepart;

	public MisesGhostDropEvent(Point point, int montant, String componentName, Point pDepart) {
		super(point);

		this.montant = montant;
		this.componentName = componentName;
		this.pDepart = pDepart;
	}

	public int getMontantMise() {
		return this.montant;
	}

	public String getComponentname() {
		return this.componentName;
	}

	public Point getPositionDepart() {
		return this.pDepart;
	}
}
