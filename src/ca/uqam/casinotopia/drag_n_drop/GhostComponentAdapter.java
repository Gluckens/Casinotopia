package ca.uqam.casinotopia.drag_n_drop;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.SwingUtilities;

public class GhostComponentAdapter extends GhostDropAdapter {
	public GhostComponentAdapter(GhostGlassPane glassPane) {
		super(glassPane);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		Component c = e.getComponent();

		BufferedImage image = new BufferedImage(c.getWidth(), c.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics g = image.getGraphics();
		c.paint(g);

		this.glassPane.setVisible(true);

		Point p = (Point) e.getPoint().clone();
		SwingUtilities.convertPointToScreen(p, c);
		SwingUtilities.convertPointFromScreen(p, this.glassPane);

		this.glassPane.setPoint(p);
		this.glassPane.setImage(image);
		this.glassPane.repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		Component c = e.getComponent();

		Point p = (Point) e.getPoint().clone();
		SwingUtilities.convertPointToScreen(p, c);

		Point eventPoint = (Point) p.clone();
		SwingUtilities.convertPointFromScreen(p, this.glassPane);

		this.glassPane.setPoint(p);
		this.glassPane.setVisible(false);
		this.glassPane.setImage(null);

		this.sendGhostDropEvent(eventPoint);
	}

	protected void sendGhostDropEvent(Point eventPoint) {
		this.fireGhostDropEvent(new GhostDropEvent(eventPoint));
	}
}