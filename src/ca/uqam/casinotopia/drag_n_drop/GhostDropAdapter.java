package ca.uqam.casinotopia.drag_n_drop;

import java.awt.event.MouseAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class GhostDropAdapter extends MouseAdapter {
	protected GhostGlassPane glassPane;

	private List<GhostDropListener> listeners;

	public GhostDropAdapter(GhostGlassPane glassPane) {
		this.glassPane = glassPane;
		this.listeners = new ArrayList<GhostDropListener>();
	}

	public void addGhostDropListener(GhostDropListener listener) {
		if (listener != null) {
			this.listeners.add(listener);
		}
	}

	public void removeGhostDropListener(GhostDropListener listener) {
		if (listener != null) {
			this.listeners.remove(listener);
		}
	}

	protected void fireGhostDropEvent(GhostDropEvent evt) {
		Iterator<GhostDropListener> it = this.listeners.iterator();
		while (it.hasNext()) {
			it.next().ghostDropped(evt);
		}
	}
}