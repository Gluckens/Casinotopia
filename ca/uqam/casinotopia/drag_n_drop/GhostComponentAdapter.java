package ca.uqam.casinotopia.drag_n_drop;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.SwingUtilities;

public class GhostComponentAdapter extends GhostDropAdapter
{
    public GhostComponentAdapter(GhostGlassPane glassPane) {
        super(glassPane);
    }

    public void mousePressed(MouseEvent e) {
        Component c = e.getComponent();

        BufferedImage image = new BufferedImage(c.getWidth(), c.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics g = image.getGraphics();
        c.paint(g);

        glassPane.setVisible(true);

        Point p = (Point) e.getPoint().clone();
        SwingUtilities.convertPointToScreen(p, c);
        SwingUtilities.convertPointFromScreen(p, glassPane);

        glassPane.setPoint(p);
        glassPane.setImage(image);
        glassPane.repaint();
    }

    public void mouseReleased(MouseEvent e) {
        Component c = e.getComponent();

        Point p = (Point) e.getPoint().clone();
        SwingUtilities.convertPointToScreen(p, c);

        Point eventPoint = (Point) p.clone();
        SwingUtilities.convertPointFromScreen(p, glassPane);

        glassPane.setPoint(p);
        glassPane.setVisible(false);
        glassPane.setImage(null);

        this.sendGhostDropEvent(eventPoint);
    }
    
    protected void sendGhostDropEvent(Point eventPoint) {
    	System.out.println("GHOST DROPPED GHOST");
    	fireGhostDropEvent(new GhostDropEvent(eventPoint));
    }
}