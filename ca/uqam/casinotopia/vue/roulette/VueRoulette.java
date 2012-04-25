package ca.uqam.casinotopia.vue.roulette;

import java.awt.Dimension;
import java.awt.GridBagConstraints;

import ca.uqam.casinotopia.controleur.ControleurClient;
import ca.uqam.casinotopia.controleur.client.ControleurRouletteClient;
import ca.uqam.casinotopia.observateur.Observable;
import ca.uqam.casinotopia.vue.FrameApplication;
import ca.uqam.casinotopia.vue.GridBagHelper;
import ca.uqam.casinotopia.vue.Vue;

import java.awt.GridBagLayout;

@SuppressWarnings("serial")
public class VueRoulette extends Vue {

	private ControleurRouletteClient controleur;
	private FrameApplication frame;

	/**
	 * Create the panel.
	 */
	public VueRoulette(ControleurClient controleur) {
		this.controleur = (ControleurRouletteClient) controleur;
		this.frame = this.controleur.getFrame();

		this.setPanelOptions();
		this.addComponents();
		this.createComponentsMap();
	}

	@Override
	protected void addComponents() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		/*
		 * gridBagLayout.columnWidths = new int[]{500, 300, 224};
		 * gridBagLayout.rowHeights = new int[]{294, 294, 192};
		 */
		gridBagLayout.columnWidths = new int[] { 248, 302, 474 };
		gridBagLayout.rowHeights = new int[] { 300, 300, 80 };
		// gridBagLayout.rowHeights = new int[]{300, 300, 140};
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0 };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0 };
		this.setLayout(gridBagLayout);

		// setPreferredSize(new Dimension(1024, 740));
		this.setPreferredSize(new Dimension(1024, 680));
		this.setMaximumSize(new Dimension(1024, 680));

		VueRouletteTapis tapis = new VueRouletteTapis(this.controleur, this.frame);
		tapis.setName("tapis");
		this.add(tapis, new GridBagHelper().setXY(1, 0).setWH(1, 2).end());

		/*
		 * VueRouletteTableau tableau = new VueRouletteTableau(this.controleur,
		 * this.frame); tableau.setName("tableau"); this.add(tableau, new
		 * GridBagHelper().setXY(2, 0).end());
		 */

		// VueRouletteActions actions = new VueRouletteActions(this.controleur,
		// this.frame, tapis.getComponentByName("imgTapis"));
		VueRouletteActions actions = new VueRouletteActions(this.controleur, this.frame, tapis, tapis);
		actions.setName("actions");
		this.add(actions, new GridBagHelper().setXY(0, 2).setWH(2, 1).setFill(GridBagConstraints.HORIZONTAL).setAnchor(GridBagConstraints.SOUTH).end());
	}

	/*
	 * public void updateTableJeu(Map<Case, Map<Integer, Integer>> cases) {
	 * JTextField txt = (JTextField) this.getComponentByName("txtDefault");
	 * Calendar cal = Calendar.getInstance(); SimpleDateFormat sdf = new
	 * SimpleDateFormat("HH:mm:ss"); String time = sdf.format(cal.getTime());
	 * txt.setText(String.valueOf(cases.size()) + " " + time); }
	 */

	@Override
	public void update(Observable observable) {
		/*
		 * if(observable instanceof ModelePartieRouletteClient) {
		 * this.updateTableJeu
		 * (((ModelePartieRouletteClient)observable).getTableJeu().getCases());
		 * }
		 */
	}
}
