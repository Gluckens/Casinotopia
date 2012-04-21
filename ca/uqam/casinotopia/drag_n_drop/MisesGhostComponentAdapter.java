package ca.uqam.casinotopia.drag_n_drop;

import java.awt.Point;

import ca.uqam.casinotopia.TypeMise;

public class MisesGhostComponentAdapter extends GhostComponentAdapter {
	
	private TypeMise type;

	public MisesGhostComponentAdapter(GhostGlassPane glassPane, TypeMise type) {
		super(glassPane);
		this.type = type;
	}
	
	protected void sendGhostDropEvent(Point eventPoint) {
    	System.out.println("GHOST DROPPED MISES");
    	fireGhostDropEvent(new MisesGhostDropEvent(eventPoint, this.type));
    }

}
