package ca.uqam.casinotopia.vue.roulette;

import java.awt.Color;
import java.awt.Graphics;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;

import ca.uqam.casinotopia.controleur.ControleurClient;
import ca.uqam.casinotopia.controleur.client.ControleurRouletteClient;
import ca.uqam.casinotopia.observateur.Observable;
import ca.uqam.casinotopia.vue.GridBagHelper;
import ca.uqam.casinotopia.vue.Vue;

import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@SuppressWarnings("serial")
public class VueRouletteTapis_bad extends Vue {

	private ControleurRouletteClient controleur;

	/**
	 * Create the panel.
	 */
	public VueRouletteTapis_bad(ControleurClient controleur) {
		this.controleur = (ControleurRouletteClient) controleur;
		this.setPanelOptions();
		this.addComponents();
		this.createComponentsMap();
	}

	@Override
	protected void addComponents() {
		System.out.println("DESSIN DE TAPIS");
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 75, 75, 75 };
		gridBagLayout.rowHeights = new int[] { 15, 18, 21, 24, 27, 30, 33, 36, 39, 42, 45 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0 };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
		this.setLayout(gridBagLayout);

		try {
			BufferedImage imgTapis = ImageIO.read(new File("path-to-file"));
			JLabel lblImgTapis = new JLabel(new ImageIcon(imgTapis));
			lblImgTapis.setName("imgTapis");
			this.add(lblImgTapis, new GridBagHelper().setXY(0, 0).setFill(GridBagConstraints.BOTH).end());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		MonTapis tapis = new MonTapis();
		tapis.setName("tapis");
		/*
		 * gridBagLayout.setConstraints(tapis, gbh.setXY(0, 10).setWH(3,
		 * 1).setFill(gbh.BOTH).end()); this.add(tapis);
		 */
		this.add(tapis, new GridBagHelper().setXY(0, 10).setWH(3, 1).setFill(GridBagConstraints.BOTH).end());
		// this.add(tapis, (GridBagConstraints)gbh);

		MonTapisLigne2 tapis2 = new MonTapisLigne2();
		tapis.setName("tapis2");
		/*
		 * gbc.gridx = 0; gbc.gridy = 9;
		 */
		/*
		 * gridBagLayout.setConstraints(tapis2, gbh.setXY(0, 9).end());
		 * this.add(tapis2);
		 */
		this.add(tapis2, new GridBagHelper().setXY(0, 9).setWH(3, 1).setFill(GridBagConstraints.BOTH).end());

		MonTapisLigne3 tapis3 = new MonTapisLigne3();
		tapis3.setName("tapis3");
		this.add(tapis3, new GridBagHelper().setXY(0, 8).setWH(3, 1).setFill(GridBagConstraints.BOTH).end());

		/*
		 * JLabel lblTiti = new JLabel("titi"); GridBagConstraints gbc_lblTiti =
		 * new GridBagConstraints(); gbc_lblTiti.anchor =
		 * GridBagConstraints.NORTHWEST; gbc_lblTiti.gridx = 4;
		 * gbc_lblTiti.gridy = 7; add(lblTiti, gbc_lblTiti);
		 */
	}

	@Override
	public void update(Observable observable) {
		// TODO Auto-generated method stub
	}

}

@SuppressWarnings("serial")
class MonTapis extends JComponent {

	@Override
	public void paint(Graphics g) {

		g.setColor(Color.CYAN);
		int[] x = new int[] { 0, 5, 74, 72 };
		int[] y = new int[] { 45, 0, 0, 45 };
		g.fillPolygon(x, y, x.length);

		g.setColor(Color.GREEN);
		x = new int[] { 72, 74, 146, 148 };
		y = new int[] { 45, 0, 0, 45 };
		g.fillPolygon(x, y, x.length);

		g.setColor(Color.YELLOW);
		x = new int[] { 148, 146, 219, 224 };
		y = new int[] { 45, 0, 0, 45 };
		g.fillPolygon(x, y, x.length);

		/*
		 * g.drawRect(10, 10, 200, 200); g.fillRect(10, 10, 200, 200);
		 */
	}
}

@SuppressWarnings("serial")
class MonTapisLigne2 extends JComponent {

	@Override
	public void paint(Graphics g) {

		g.setColor(Color.GREEN);
		int[] x = new int[] { 5, 10, 76, 74 };
		int[] y = new int[] { 42, 0, 0, 42 };
		g.fillPolygon(x, y, x.length);

		g.setColor(Color.YELLOW);
		x = new int[] { 74, 76, 144, 146 };
		y = new int[] { 42, 0, 0, 42 };
		g.fillPolygon(x, y, x.length);

		g.setColor(Color.CYAN);
		x = new int[] { 146, 144, 214, 219 };
		y = new int[] { 42, 0, 0, 42 };
		g.fillPolygon(x, y, x.length);
	}
}

@SuppressWarnings("serial")
class MonTapisLigne3 extends JComponent {

	@Override
	public void paint(Graphics g) {

		g.setColor(Color.YELLOW);
		int[] x = new int[] { 10, 15, 78, 76 };
		int[] y = new int[] { 39, 0, 0, 39 };
		g.fillPolygon(x, y, x.length);

		g.setColor(Color.CYAN);
		x = new int[] { 76, 78, 142, 144 };
		y = new int[] { 39, 0, 0, 39 };
		g.fillPolygon(x, y, x.length);

		g.setColor(Color.GREEN);
		x = new int[] { 144, 142, 209, 214 };
		y = new int[] { 39, 0, 0, 39 };
		g.fillPolygon(x, y, x.length);
	}
}