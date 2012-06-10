package ca.uqam.casinotopia.vue;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import ca.uqam.casinotopia.controleur.client.ControleurPrincipalClient;
import ca.uqam.casinotopia.drag_n_drop.GhostGlassPane;
import ca.uqam.casinotopia.vue.gestion.CompteClient;

import java.awt.GridBagLayout;

@SuppressWarnings("serial")
public class FrameGestionCompte extends JFrame implements Runnable {

	private JPanel pnlCompte;
	private ControleurPrincipalClient controleur;


	/**
	 * Create the frame.
	 */
	public FrameGestionCompte(ControleurPrincipalClient ctrl, boolean nouvCompte) {
		this.controleur = ctrl;
		setResizable(false);
		this.setTitle("Compte : Casinotopia");

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setAlwaysOnTop(true); 

		this.pnlCompte = new JPanel();

		this.setContentPane(this.pnlCompte);
		GridBagLayout gbl = new GridBagLayout();
		gbl.columnWidths = new int[] { 1024 };
		gbl.rowHeights = new int[] { 680, 50 };
		gbl.columnWeights = new double[] { Double.MIN_VALUE };
		gbl.rowWeights = new double[] { 0.0 };
		this.pnlCompte.setLayout(gbl);
		pnlCompte.add(new CompteClient(ctrl, nouvCompte));

		this.setSize(400, 400);
		this.setLocationRelativeTo(null);

		GhostGlassPane glassPane = new GhostGlassPane();
		this.setGlassPane(glassPane);
	}


	@Override
	public void run() {
		this.setVisible(true);
	}

	public void changeContentPane(JPanel contentPane) {
		this.setContentPane(contentPane);
		this.setVisible(true);
	}

}
