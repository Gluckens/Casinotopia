package ca.uqam.casinotopia.drag_n_drop;

import java.awt.Point;

import ca.uqam.casinotopia.TypeMise;

public class MisesGhostComponentAdapter extends GhostComponentAdapter {

	private TypeMise typeMise;

	public MisesGhostComponentAdapter(GhostGlassPane glassPane, TypeMise typeMise) {
		super(glassPane);
		this.typeMise = typeMise;
	}

	@Override
	protected void sendGhostDropEvent(Point eventPoint) {
		this.fireGhostDropEvent(new MisesGhostDropEvent(eventPoint, this.typeMise));
	}

}
