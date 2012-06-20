package ca.uqam.casinotopia.vue;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import ca.uqam.casinotopia.drag_n_drop.GhostGlassPane;

import java.awt.GridBagLayout;

@SuppressWarnings("serial")
public class FrameApplication extends JFrame implements Runnable {

	private JPanel contentPane;
	private JPanel pnlVue;
	private JPanel pnlMenu;

	protected Map<String, Component> componentMapVue = new HashMap<String, Component>();
	protected Map<String, Component> componentMapMenu = new HashMap<String, Component>();

	/**
	 * Create the frame.
	 */
	public FrameApplication() {
		setResizable(false);
		this.setTitle("Casinotopia");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.contentPane = new JPanel();
		this.setContentPane(this.contentPane);
		GridBagLayout gbl = new GridBagLayout();
		gbl.columnWidths = new int[] { 1024 };
		gbl.rowHeights = new int[] { 680, 50 };
		this.contentPane.setLayout(gbl);

		this.pnlVue = new JPanel();
		this.pnlVue.setLayout(new GridBagLayout());
		this.contentPane.add(this.pnlVue, new GridBagHelper().setXY(0, 0).setAnchor(GridBagConstraints.NORTH).end());

		this.pnlMenu = new JPanel();
		this.pnlMenu.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
		this.pnlMenu.setLayout(new GridBagLayout());
		this.contentPane.add(this.pnlMenu, new GridBagHelper().setXY(0, 1).setFill(GridBagConstraints.BOTH).setAnchor(GridBagConstraints.SOUTH).end());
		
		GhostGlassPane glassPane = new GhostGlassPane();
		this.setGlassPane(glassPane);

		this.setSize(1024, 768);
		this.setLocationRelativeTo(null);
	}

	@Override
	public void run() {
		this.setVisible(true);
	}

	protected void createComponentsMap() {
		Component[] components = this.pnlVue.getComponents();
		for (int i = 0; i < components.length; i++) {
			this.componentMapVue.put(components[i].getName(), components[i]);
		}

		components = this.pnlMenu.getComponents();
		for (int i = 0; i < components.length; i++) {
			this.componentMapMenu.put(components[i].getName(), components[i]);
		}
	}

	public Component getVueComponentByName(String name) {
		return this.componentMapVue.get(name);
	}

	public Component getMenuComponentByName(String name) {
		return this.componentMapMenu.get(name);
	}

	public void changeContentPane(JPanel contentPane) {
		this.setContentPane(contentPane);
		this.setVisible(true);
	}

	public void addOrReplaceVue(String name, JPanel panel) {
		/*System.out.println("Frame contient avant add : " + this.getComponentCount());
		System.out.println("ContentPane contient apres add : " + this.contentPane.getComponentCount());
		System.out.println("PanelVue contient avant add : " + this.pnlVue.getComponentCount());*/

		// Si je retourne le panel directement et que je le modifie, sa va pas
		// modifier le vrai dans le frame?
		// Comment positionner les vues dans le frame quand on les ajoutent?
		Component component = this.getVueComponentByName(name);
		if (component != null) {
			if (component instanceof JPanel) {
				// remplacer le panel
				component = panel;
			}
		}
		else {
			this.componentMapVue.put(name, panel);
			this.pnlVue.add(panel, new GridBagHelper().setXY(0, 0).end());
			// this.contentPane.add(panel, new GridBagHelper().setXY(0, 0).setFill(GridBagConstraints.BOTH).end());
		}

		/*this.setVisible(true);
		this.invalidate();

		this.repaint();*/

		this.pnlVue.revalidate();
		this.pnlVue.repaint();

		this.setVisible(true);

		/*System.out.println("Frame contient apres add : " + this.getComponentCount());
		System.out.println("ContentPane contient apres add : " + this.contentPane.getComponentCount());
		System.out.println("PanelVue contient apres add : " + this.pnlVue.getComponentCount());*/
	}

	public void addOrReplaceMenu(String name, JPanel panel) {
		/*System.out.println("Frame contient avant add : " + this.getComponentCount());
		System.out.println("ContentPane contient apres add : " + this.contentPane.getComponentCount());
		System.out.println("PanelMenu contient avant add : " + this.pnlMenu.getComponentCount());*/

		Component component = this.getMenuComponentByName(name);
		if (component != null) {
			if (component instanceof JPanel) {
				// remplacer le panel
				component = panel;
			}
		}
		else {
			this.componentMapMenu.put(name, panel);
			this.pnlMenu.add(panel, new GridBagHelper().setXY(0, 0).end());
			// this.contentPane.add(panel, new GridBagHelper().setXY(0,
			// 0).setFill(GridBagConstraints.BOTH).end());
		}

		this.pnlMenu.revalidate();
		this.pnlMenu.repaint();

		this.setVisible(true);

		/*System.out.println("Frame contient apres add : " + this.getComponentCount());
		System.out.println("ContentPane contient apres add : " + this.contentPane.getComponentCount());
		System.out.println("PanelMenu contient apres add : " + this.pnlMenu.getComponentCount());*/
	}

	public Map<String, Component> getComponentMapVue() {
		return this.componentMapVue;
	}

	public Map<String, Component> getComponentMapMenu() {
		return this.componentMapMenu;
	}

	@Override
	public void removeAll() {
		this.removeAllVue();
		this.removeAllMenu();
	}

	public void removeAllVue() {
		this.pnlVue.removeAll();
		this.componentMapVue.clear();
	}

	public void removeAllMenu() {
		// super.removeAll();

		this.pnlMenu.removeAll();
		this.componentMapMenu.clear();
	}
}
