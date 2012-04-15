package ca.uqam.casinotopia.modele;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public abstract class Modele {
	protected PropertyChangeSupport propertyChangeSupport;
	
	public Modele() {
        propertyChangeSupport = new PropertyChangeSupport(this);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    protected void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
        propertyChangeSupport.firePropertyChange(propertyName, oldValue, newValue);
    }
}
