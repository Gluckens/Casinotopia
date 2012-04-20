package ca.uqam.casinotopia.vue;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;


import ca.uqam.casinotopia.Case;
import ca.uqam.casinotopia.controleur.ControleurClient;
import ca.uqam.casinotopia.controleur.client.ControleurRouletteClient;
import ca.uqam.casinotopia.modele.client.ModeleTableJeuClient;
import ca.uqam.casinotopia.observateur.Observable;

@SuppressWarnings("serial")
public class VueRouletteTapis extends Vue {

	private ControleurRouletteClient controleur;
	private FrameApplication frame;
	
	/**
	 * Create the panel.
	 */
	public VueRouletteTapis(ControleurClient controleur, FrameApplication frame) {
		this.controleur = (ControleurRouletteClient) controleur;
		this.frame = frame;
		this.setPanelOptions();
		this.addComponents();
		this.createComponentsMap();
	}

	@Override
	protected void addComponents() {
		System.out.println("DESSIN DE TAPIS");
		GridBagLayout gridBagLayout = new GridBagLayout();
		/*gridBagLayout.columnWidths = new int[]{75, 75, 75};
		gridBagLayout.rowHeights = new int[]{15, 18, 21, 24, 27, 30, 33, 36, 39, 42, 45};*/
		gridBagLayout.columnWidths = new int[]{300};
		gridBagLayout.rowHeights = new int[]{588};
		gridBagLayout.columnWeights = new double[]{0.0};
		gridBagLayout.rowWeights = new double[]{0.0};
		setLayout(gridBagLayout);
		

		setPreferredSize(new Dimension(300, 588));
		
		JLabel lblImgTapis = new JLabel(new ImageIcon(VueRouletteTapis.class.getResource("/img/roulette-table.jpg")));
		lblImgTapis.setName("imgTapis");
		this.add(lblImgTapis, new GridBagHelper().setXY(0, 0).setFill(GridBagConstraints.BOTH).end());
	}
	
	public void updateTableJeu(Map<Case, Map<Integer, Integer>> cases) {
		//TODO Mettre à jour le tapis/tableau
	}

	@Override
	public void update(Observable observable) {
		if(observable instanceof ModeleTableJeuClient) {
			this.updateTableJeu(((ModeleTableJeuClient)observable).getCases());
		}
	}

}
