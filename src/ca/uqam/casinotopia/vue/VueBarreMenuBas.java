package ca.uqam.casinotopia.vue;

import java.awt.Dimension;
import java.awt.GridBagLayout;

import javax.swing.JButton;

import ca.uqam.casinotopia.controleur.ControleurClient;
import ca.uqam.casinotopia.controleur.client.ControleurBarreMenuBas;
import ca.uqam.casinotopia.observateur.Observable;

@SuppressWarnings("serial")
public class VueBarreMenuBas extends Vue {

	private ControleurBarreMenuBas controleur;
	private FrameApplication frame;

	/**
	 * Create the panel.
	 */
	public VueBarreMenuBas(ControleurClient controleur) {
		this.controleur = (ControleurBarreMenuBas) controleur;
		this.frame = this.controleur.getFrame();

		this.setPanelOptions();
		this.addComponents();
		this.createComponentsMap();
	}

	@Override
	protected void addComponents() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 128, 128, 512, 128, 128 };
		gridBagLayout.rowHeights = new int[] { 45 };
		gridBagLayout.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		this.setLayout(gridBagLayout);

		//setPreferredSize(new Dimension(1024, 45));

		JButton btnMenu = new JButton("Menu");
		btnMenu.setName("btnMenu");
		this.add(btnMenu, new GridBagHelper().setXY(0, 0).end());
	}

	@Override
	public void update(Observable observable) {
		// TODO Auto-generated method stub

	}

}
