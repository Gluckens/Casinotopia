package ca.uqam.casinotopia.vue.roulette;

import java.awt.Component;

import javax.swing.JOptionPane;

import ca.uqam.casinotopia.TypeMise;
import ca.uqam.casinotopia.drag_n_drop.AbstractGhostDropManager;
import ca.uqam.casinotopia.drag_n_drop.GhostDropEvent;
import ca.uqam.casinotopia.drag_n_drop.MisesGhostDropEvent;

public class MisesGhostDropManager extends AbstractGhostDropManager {

	public MisesGhostDropManager(Component target) {
		super(target);
	}

	public void ghostDropped(GhostDropEvent e) {
		MisesGhostDropEvent evt = (MisesGhostDropEvent) e;
		TypeMise type = evt.getTypeMise();
		System.out.println("##### MISES GHOST DROPPED EVENT #####");
		if(this.isInTarget(evt.getDropLocation())) {
			JOptionPane.showMessageDialog(this.component, "Type de mise : " + type);
		}
	}

}
