package ca.uqam.casinotopia.drag_n_drop;

import java.awt.Point;

import ca.uqam.casinotopia.TypeMise;

public class MisesGhostDropEvent extends GhostDropEvent {
	
	TypeMise type;

	public MisesGhostDropEvent(Point point, TypeMise type) {
		super(point);
		
		this.type = type;
	}
	
	public TypeMise getTypeMise() {
		return this.type;
	}

}
