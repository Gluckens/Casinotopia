package ca.uqam.casinotopia.vue;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ca.uqam.casinotopia.drag_n_drop.GhostGlassPane;

import java.awt.FlowLayout;
import java.awt.GridBagLayout;

@SuppressWarnings("serial")
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
		this.setTitle("Casinotopia");
		//this.setExtendedState(Frame.MAXIMIZED_BOTH);
		//this.setUndecorated(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.setBounds(100, 100, 500, 400);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		//this.contentPane.setPreferredSize(new Dimension(1024, 768));
		this.setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0};
		gbl_contentPane.rowHeights = new int[]{0};
		gbl_contentPane.columnWeights = new double[]{Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		this.setSize(1024, 768);
		this.setLocationRelativeTo(null);
		
		GhostGlassPane glassPane = new GhostGlassPane();
		this.setGlassPane(glassPane);
	}
	
	public void run() {
		this.setVisible(true);
	}
	
	public void changeContentPane(JPanel contentPane) {
		this.setContentPane(contentPane);
		this.setVisible(true);
	}
	
	public void addOrReplace(String name, JPanel panel) {
		System.out.println("Frame contient avant add : " + this.getComponentCount());
		System.out.println("ContentPane contient avant add : " + this.getContentPane().getComponentCount());
		
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

		/*this.setVisible(true);
		this.invalidate();

		this.repaint();*/
		
		this.contentPane.revalidate();
		this.contentPane.repaint();
		
		this.setVisible(true);
		
		System.out.println("Frame contient apres add : " + this.getComponentCount());
		System.out.println("ContentPane contient apres add : " + this.getContentPane().getComponentCount());
	}
	
	public Map<String, Component> getComponentMap() {
		return this.componentMap;
	}
	
	@Override
	public void removeAll() {
		//super.removeAll();
		
		this.contentPane.removeAll();
		this.componentMap.clear();
		
		/*this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.contentPane.setLayout(new BorderLayout(0, 0));
		this.setContentPane(contentPane);*/
	}

}
