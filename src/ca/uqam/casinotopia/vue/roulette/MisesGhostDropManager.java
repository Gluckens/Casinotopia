package ca.uqam.casinotopia.vue.roulette;

import java.awt.Component;
import java.awt.Point;

import javax.swing.JOptionPane;
import ca.uqam.casinotopia.TypeMise;
import ca.uqam.casinotopia.drag_n_drop.AbstractGhostDropManager;
import ca.uqam.casinotopia.drag_n_drop.DroppableReceiver;
import ca.uqam.casinotopia.drag_n_drop.GhostDropEvent;
import ca.uqam.casinotopia.drag_n_drop.MisesGhostDropEvent;

public class MisesGhostDropManager extends AbstractGhostDropManager {

	private DroppableReceiver receiver;

	public MisesGhostDropManager(Component target, DroppableReceiver receiver) {
		super(target);
		this.receiver = receiver;
	}

	@Override
	public void ghostDropped(GhostDropEvent e) {
		MisesGhostDropEvent evt = (MisesGhostDropEvent) e;
		TypeMise type = evt.getTypeMise();
		System.out.println("##### MISES GHOST DROPPED EVENT #####");
		Point p = this.getTranslatedPoint(evt.getDropLocation());

		if (this.isInTarget(p)) {
			JOptionPane.showMessageDialog(this.target, "Type de mise : " + type);
			/*
			 * System.out.println("P1 ==> " + p); Point p2 = (Point)
			 * evt.getDropLocation().clone();
			 * //SwingUtilities.convertPointFromScreen(p2, target);
			 * System.out.println("P2 ==> " + p2); Point p3 = (Point)
			 * target.getLocationOnScreen().clone();
			 * System.out.println("P3 ==> " + p3); System.out.println("P4 ==> "
			 * + new Point((int)p2.getX() - (int)p3.getX(), (int)p2.getY() -
			 * (int)p3.getY())); Point p5 = (Point)
			 * evt.getDropLocation().clone();
			 * SwingUtilities.convertPointFromScreen(p5, target);
			 * System.out.println("P5 ==> " + p5); Point p6 = (Point)
			 * evt.getDropLocation().clone();
			 * SwingUtilities.convertPointFromScreen(p6, target.getParent());
			 * System.out.println("P6 ==> " + p6); /*Point p2 = (Point)
			 * evt.getDropLocation().clone();
			 * SwingUtilities.convertPointFromScreen(p2, this.target);
			 * System.out.println(p2);
			 */
			this.receiver.processDrop(p);
		}
	}

}
