package ca.uqam.casinotopia.vue.roulette;

import java.awt.Component;
import java.awt.Point;

import ca.uqam.casinotopia.drag_n_drop.AbstractGhostDropManager;
import ca.uqam.casinotopia.drag_n_drop.GhostDropEvent;
import ca.uqam.casinotopia.drag_n_drop.mises.MisesDroppableReceiver;
import ca.uqam.casinotopia.drag_n_drop.mises.MisesGhostDropEvent;

public class MisesGhostDropManager extends AbstractGhostDropManager {

	private MisesDroppableReceiver receiver;

	public MisesGhostDropManager(Component target, MisesDroppableReceiver receiver) {
		super(target);
		this.receiver = receiver;
	}

	@Override
	public void ghostDropped(GhostDropEvent e) {
		MisesGhostDropEvent evt = (MisesGhostDropEvent) e;
		Point p = this.getTranslatedPoint(evt.getDropLocation());
		int montant = evt.getMontantMise();
		String componentName = evt.getComponentname();
		Point posDepart = this.getTranslatedPoint(evt.getPositionDepart());

		if (this.isInTarget(p)) {
			this.receiver.processDrop(p, montant, componentName, posDepart);
		}
	}
}