package ca.uqam.casinotopia.vue.roulette;

import java.awt.Component;
import java.awt.Point;

import ca.uqam.casinotopia.TypeMise;
import ca.uqam.casinotopia.drag_n_drop.AbstractGhostDropManager;
import ca.uqam.casinotopia.drag_n_drop.MisesDroppableReceiver;
import ca.uqam.casinotopia.drag_n_drop.GhostDropEvent;
import ca.uqam.casinotopia.drag_n_drop.MisesGhostDropEvent;

public class MisesGhostDropManager extends AbstractGhostDropManager {

	private MisesDroppableReceiver receiver;

	public MisesGhostDropManager(Component target, MisesDroppableReceiver receiver) {
		super(target);
		this.receiver = receiver;
	}

	@Override
	public void ghostDropped(GhostDropEvent e) {
		MisesGhostDropEvent evt = (MisesGhostDropEvent) e;
		TypeMise type = evt.getTypeMise();
		Point p = this.getTranslatedPoint(evt.getDropLocation());

		if (this.isInTarget(p)) {
			//JOptionPane.showMessageDialog(this.target, "Type de mise : " + type);
			this.receiver.processDrop(p, type);
		}
	}

}
