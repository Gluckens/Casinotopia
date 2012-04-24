package ca.uqam.casinotopia.vue;

import java.awt.Component;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ca.uqam.casinotopia.drag_n_drop.GhostGlassPane;

import java.awt.GridBagLayout;

@SuppressWarnings("serial")
public class FrameApplication extends JFrame implements Runnable {

	private JPanel contentPane;
	private JPanel pnlVue;
	private JPanel pnlMenu;
	
	protected Map<String, Component> componentMap = new HashMap<String, Component>();
	
	protected void createComponentsMap() {
		Component[] components = pnlVue.getComponents();
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
		GridBagLayout gbl = new GridBagLayout();
		gbl.columnWidths = new int[]{1024};
		gbl.rowHeights = new int[]{720, 48};
		gbl.columnWeights = new double[]{Double.MIN_VALUE};
		gbl.rowWeights = new double[]{Double.MIN_VALUE};
		contentPane.setLayout(gbl);
		
		pnlVue = new JPanel();
		contentPane.add(pnlVue, new GridBagHelper().setXY(0, 0));
		
		pnlMenu = new JPanel();
		contentPane.add(pnlMenu, new GridBagHelper().setXY(0, 1));
		
		//this.contentPane.add(new VueChat(null, this));
		
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
		System.out.println("ContentPane contient avant add : " + this.pnlVue.getComponentCount());
		
		
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
			this.pnlVue.add(panel);
			//this.contentPane.add(panel, new GridBagHelper().setXY(0, 0).setFill(GridBagConstraints.BOTH).end());
		}
		
		/*this.setVisible(true);
		this.invalidate();

		this.repaint();*/
		
		this.pnlVue.revalidate();
		this.pnlVue.repaint();
		
		this.setVisible(true);
		
		System.out.println("Frame contient apres add : " + this.getComponentCount());
		System.out.println("ContentPane contient apres add : " + this.pnlVue.getComponentCount());
	}
	
	public Map<String, Component> getComponentMap() {
		return this.componentMap;
	}
	
	@Override
	public void removeAll() {
		//super.removeAll();
		
		this.pnlVue.removeAll();
		this.componentMap.clear();
		
		/*this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.contentPane.setLayout(new BorderLayout(0, 0));
		this.setContentPane(contentPane);*/
	}

}
