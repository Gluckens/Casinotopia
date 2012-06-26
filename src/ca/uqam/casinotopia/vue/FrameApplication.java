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

/**
 * Frame utilisé pour contenir toutes les vues de l'application lorsque l'utilisateur est authentifié
 */
@SuppressWarnings("serial")
public class FrameApplication extends JFrame implements Runnable {

	private JPanel contentPane;
	private JPanel pnlVue;
	private JPanel pnlMenu;

	/**
	 * Map contenant tous les components de la section principale
	 */
	protected Map<String, Component> componentMapVue = new HashMap<String, Component>();
	
	/**
	 * Map contenant tous les components de la section menu
	 */
	protected Map<String, Component> componentMapMenu = new HashMap<String, Component>();

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

	/**
	 * Changer le content pane du frame
	 * 
	 * @param contentPane Le nouveau contentPane
	 */
	public void changeContentPane(JPanel contentPane) {
		this.setContentPane(contentPane);
		this.setVisible(true);
	}

	/**
	 * Ajouter un nouveau panel ou le remplacer (s'il existe déjà) dans la section principale
	 * 
	 * @param name Le nom du panel à ajouter/remplacer
	 * @param panel Le panel à ajouter/remplacer
	 */
	public void addOrReplaceVue(String name, JPanel panel) {
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
		}

		this.pnlVue.revalidate();
		this.pnlVue.repaint();

		this.setVisible(true);
	}

	/**
	 * Ajouter un nouveau panel ou le remplacer (s'il existe déjà) dans la section menu
	 * 
	 * @param name Le nom du panel à ajouter/remplacer
	 * @param panel Le panel à ajouter/remplacer
	 */
	public void addOrReplaceMenu(String name, JPanel panel) {
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
		}

		this.pnlMenu.revalidate();
		this.pnlMenu.repaint();

		this.setVisible(true);
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
		this.pnlMenu.removeAll();
		this.componentMapMenu.clear();
	}
}