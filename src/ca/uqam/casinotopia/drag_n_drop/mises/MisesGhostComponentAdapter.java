package ca.uqam.casinotopia.drag_n_drop.mises;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;

import ca.uqam.casinotopia.drag_n_drop.GhostComponentAdapter;
import ca.uqam.casinotopia.drag_n_drop.GhostGlassPane;

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