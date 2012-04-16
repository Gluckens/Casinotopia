package ca.uqam.casinotopia.vue;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Frame;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class FrameApplication extends JFrame implements Runnable {

	private JPanel contentPane;
	
	protected Map<String, Component> componentMap = new HashMap<String, Component>();
	
	protected void createComponentsMap() {
		Component[] components = this.getContentPane().getComponents();
        for (int i=0; i < components.length; i++) {
                this.componentMap.put(components[i].getName(), components[i]);
        }
	}
	
	public Component getComponentByName(String name) {
		return this.componentMap.get(name);
	}

	/**
	 * Create the frame.
	 */
	public FrameApplication() {
		setTitle("Casinotopia");
		//this.setExtendedState(Frame.MAXIMIZED_BOTH);
		//this.setUndecorated(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 450, 300);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.contentPane.setLayout(new BorderLayout(0, 0));
		this.setContentPane(contentPane);
	}
	
	public void run() {
		this.setVisible(true);
	}
	
	public void changeContentPane(JPanel contentPane) {
		this.setContentPane(contentPane);
		this.setVisible(true);
	}
	
	public void addOrReplace(String name, JPanel panel) {
		//Si je retourne le panel directement et que je le modifie, sa va pas modifier le vrai dans le frame?
		//Comment positionner les vues dans le frame quand on les ajoutent?
		Component component = this.getComponentByName(name);
		if(component != null) {
			if(component instanceof JPanel) {
				//remplacer le panel
				component = panel;
			}
		}
		else {
			this.componentMap.put(name, panel);
			this.contentPane.add(panel);
		}
	}

}
