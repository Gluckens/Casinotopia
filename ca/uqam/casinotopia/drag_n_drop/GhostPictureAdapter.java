package ca.uqam.casinotopia.drag_n_drop;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import java.io.BufferedInputStream;
import java.io.IOException;

import java.net.MalformedURLException;

import javax.imageio.ImageIO;

import javax.swing.SwingUtilities;

public class GhostPictureAdapter extends GhostDropAdapter {
	private BufferedImage image;

	public GhostPictureAdapter(GhostGlassPane glassPane, String picture) {
		super(glassPane);
		try {
			this.image = ImageIO.read(new BufferedInputStream(GhostPictureAdapter.class.getResourceAsStream(picture)));
		} catch (MalformedURLException mue) {
			throw new IllegalStateException("Invalid picture URL.");
		} catch (IOException ioe) {
			throw new IllegalStateException("Invalid picture or picture URL.");
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		Component c = e.getComponent();

		this.glassPane.setVisible(true);

		Point p = (Point) e.getPoint().clone();
		SwingUtilities.convertPointToScreen(p, c);
		SwingUtilities.convertPointFromScreen(p, this.glassPane);

		this.glassPane.setPoint(p);
		this.glassPane.setImage(this.image);
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