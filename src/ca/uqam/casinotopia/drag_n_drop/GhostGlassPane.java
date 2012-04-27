package ca.uqam.casinotopia.drag_n_drop;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GhostGlassPane extends JPanel {
	private AlphaComposite composite;
	private BufferedImage dragged = null;
	private Point location = new Point(0, 0);

	public GhostGlassPane() {
		this.setOpaque(false);
		this.composite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f);
	}

	public void setImage(BufferedImage dragged) {
		this.dragged = dragged;
	}

	public void setPoint(Point location) {
		this.location = location;
	}

	@Override
	public void paintComponent(Graphics g) {
		if (this.dragged == null) {
			return;
		}

		Graphics2D g2 = (Graphics2D) g;
		g2.setComposite(this.composite);
		g2.drawImage(this.dragged, (int) (this.location.getX() - (this.dragged.getWidth(this) / 2)),
				(int) (this.location.getY() - (this.dragged.getHeight(this) / 2)), null);
	}
}