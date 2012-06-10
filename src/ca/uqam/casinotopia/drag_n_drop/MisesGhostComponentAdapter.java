package ca.uqam.casinotopia.drag_n_drop;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;

public class MisesGhostComponentAdapter extends GhostComponentAdapter {

	private int montant;
	private String componentName;
	private Point posDepart;

	public MisesGhostComponentAdapter(GhostGlassPane glassPane, int montant, String componentName) {
		super(glassPane);
		this.montant = montant;
		this.componentName = componentName;
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println("MOUSEPRESSED MISES_GHOST");
		
		Component c = e.getComponent();
		
		Point p = (Point) e.getPoint().clone();
		SwingUtilities.convertPointToScreen(p, c);
		
		this.posDepart = p;
		
		super.mousePressed(e);
	}

	@Override
	protected void sendGhostDropEvent(Point eventPoint) {
		this.fireGhostDropEvent(new MisesGhostDropEvent(eventPoint, this.montant, this.componentName, this.posDepart));
	}

}
