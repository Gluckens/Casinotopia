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
		/*
		 * Component c = e.getComponent();
		 * 
		 * System.out.println(MouseInfo.getPointerInfo().getLocation());
		 * 
		 * Point p = (Point) e.getPoint().clone();
		 * System.out.println("POINT RELEASE ==> " + p);
		 * SwingUtilities.convertPointToScreen(p, c);
		 * System.out.println("POINT RELEASE SCREEN ==> " + p);
		 * 
		 * Point test = (Point) p.clone();
		 * SwingUtilities.convertPointToScreen(test, c.getParent());
		 * System.out.println("POINT RELEASE SCREEN TEST ==> " + test);
		 * 
		 * Point eventPoint = (Point) p.clone();
		 * System.out.println("POINT EVENT ==> " + eventPoint);
		 * SwingUtilities.convertPointFromScreen(p, glassPane);
		 * System.out.println("POINT FROM GLASS ==> " + p);
		 */

		/*
		 * Component c = e.getComponent();
		 * 
		 * Point p = (Point) e.getPoint().clone();
		 * SwingUtilities.convertPointToScreen(p, c);
		 * SwingUtilities.convertPointFromScreen(p, glassPane);
		 */

		/*
		 * Point eventPoint = (Point) p.clone();
		 * SwingUtilities.convertPointFromScreen(eventPoint, glassPane);
		 * System.out.println("POINT FROM GLASS ==> " + p);
		 */

		Component c = e.getComponent();

		Point p = (Point) e.getPoint().clone();
		SwingUtilities.convertPointToScreen(p, c);

		Point eventPoint = (Point) p.clone();
		SwingUtilities.convertPointFromScreen(p, this.glassPane);

		this.glassPane.setPoint(p);
		this.glassPane.setVisible(false);
		this.glassPane.setImage(null);

		// System.out.println("EVENT POINT ==> " + eventPoint);

		this.sendGhostDropEvent(eventPoint);
		// this.sendGhostDropEvent(p);
	}

	protected void sendGhostDropEvent(Point eventPoint) {
		System.out.println("GHOST DROPPED GHOST");
		this.fireGhostDropEvent(new GhostDropEvent(eventPoint));
	}
}