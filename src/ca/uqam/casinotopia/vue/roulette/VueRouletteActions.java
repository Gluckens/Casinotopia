package ca.uqam.casinotopia.vue.roulette;

import java.awt.Dimension;
import java.awt.GridBagLayout;

import ca.uqam.casinotopia.TypeMise;
import ca.uqam.casinotopia.controleur.ControleurClient;
import ca.uqam.casinotopia.controleur.client.ControleurRouletteClient;
import ca.uqam.casinotopia.drag_n_drop.MisesDroppableReceiver;
import ca.uqam.casinotopia.drag_n_drop.GhostDropListener;
import ca.uqam.casinotopia.drag_n_drop.GhostGlassPane;
import ca.uqam.casinotopia.drag_n_drop.GhostMotionAdapter;
import ca.uqam.casinotopia.drag_n_drop.MisesGhostComponentAdapter;
import ca.uqam.casinotopia.observateur.Observable;
import ca.uqam.casinotopia.vue.FrameApplication;
import ca.uqam.casinotopia.vue.GridBagHelper;
import ca.uqam.casinotopia.vue.Vue;

import javax.swing.JLabel;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.TransferHandler;

import javax.swing.border.EtchedBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class VueRouletteActions extends Vue {

	private ControleurRouletteClient controleur;
	private FrameApplication frame;

	/**
	 * Create the panel.
	 * 
	 * @param tapis
	 */
	public VueRouletteActions(ControleurClient controleur, FrameApplication frame, JComponent dropTarget, MisesDroppableReceiver dropReceiver) {
		this.setMinimumSize(new Dimension(700, 80));
		this.setMaximumSize(new Dimension(700, 80));
		this.controleur = (ControleurRouletteClient) controleur;
		this.frame = frame;
		this.setPanelOptions();
		this.addComponents();
		this.createComponentsMap();
		this.initDragAndDrop(dropTarget, dropReceiver);
	}

	@Override
	protected void addComponents() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 248, 151, 151 };
		gridBagLayout.rowHeights = new int[] { 30, 50 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0 };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0 };
		this.setLayout(gridBagLayout);

		this.setPreferredSize(new Dimension(550, 80));
		this.setMaximumSize(new Dimension(550, 80));
		this.setMinimumSize(new Dimension(550, 80));

		JLabel lblVosOptions = new JLabel("Vos options :");
		lblVosOptions.setFont(new Font("Tahoma", Font.ITALIC, 24));
		/*
		 * GridBagConstraints gbc_lblVosOptions = new GridBagConstraints();
		 * gbc_lblVosOptions.insets = new Insets(0, 0, 5, 5);
		 * gbc_lblVosOptions.gridx = 0; gbc_lblVosOptions.gridy = 0;
		 * add(lblVosOptions, gbc_lblVosOptions);
		 */
		this.add(lblVosOptions, new GridBagHelper().setXY(0, 0).end());

		JButton btnMiser = new JButton("Miser");
		btnMiser.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnMiser.setToolTipText("Ouvrir la fen\u00EAtre de mise");
		/*
		 * GridBagConstraints gbc_btnMiser = new GridBagConstraints();
		 * gbc_btnMiser.insets = new Insets(0, 0, 5, 5); gbc_btnMiser.gridx = 1;
		 * gbc_btnMiser.gridy = 1; add(btnMiser, gbc_btnMiser);
		 */
		this.add(btnMiser, new GridBagHelper().setXY(1, 1).end());

		JButton btnPret = new JButton("Pr\u00EAt");
		btnPret.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controleur.cmdMisesTermineesRoulette();
			}
		});
		btnPret.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnPret.setToolTipText("Pr\u00EAt \u00E0 tourner la roulette");
		/*
		 * GridBagConstraints gbc_btnPrt = new GridBagConstraints();
		 * gbc_btnPrt.insets = new Insets(0, 0, 5, 0); gbc_btnPrt.gridx = 2;
		 * gbc_btnPrt.gridy = 1; add(btnPrt, gbc_btnPrt);
		 */
		this.add(btnPret, new GridBagHelper().setXY(2, 1).end());

		JPanel pnlJetons = new JPanel();
		pnlJetons.setName("pnlJetons");
		/*
		 * lblChip5.addMouseListener(misesGhostComponentAdapter = new
		 * MisesGhostComponentAdapter((GhostGlassPane)
		 * this.frame.getGlassPane(), TypeMise.MISE_5));
		 * misesGhostComponentAdapter.addGhostDropListener(ghostDropListener);
		 * lblChip5.addMouseMotionListener(new
		 * GhostMotionAdapter((GhostGlassPane) this.frame.getGlassPane()));
		 */
		pnlJetons.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblChip5 = new JLabel(new ImageIcon(VueRouletteActions.class.getResource("/img/chip_5.png")));
		lblChip5.setName("lblChip5");
		lblChip5.setTransferHandler(new TransferHandler("text"));
		pnlJetons.add(lblChip5);

		JLabel lblChip10 = new JLabel(new ImageIcon(VueRouletteActions.class.getResource("/img/chip_10.png")));
		lblChip10.setName("lblChip10");
		lblChip10.setTransferHandler(new TransferHandler("text"));
		pnlJetons.add(lblChip10);

		JLabel lblChip25 = new JLabel(new ImageIcon(VueRouletteActions.class.getResource("/img/chip_25.png")));
		lblChip25.setName("lblChip25");
		lblChip25.setTransferHandler(new TransferHandler("text"));
		pnlJetons.add(lblChip25);
		/*
		 * lblChip10.addMouseListener(misesGhostComponentAdapter = new
		 * MisesGhostComponentAdapter((GhostGlassPane)
		 * this.frame.getGlassPane(), TypeMise.MISE_10));
		 * misesGhostComponentAdapter.addGhostDropListener(ghostDropListener);
		 * lblChip10.addMouseMotionListener(new
		 * GhostMotionAdapter((GhostGlassPane) this.frame.getGlassPane()));
		 */
		/*
		 * lblChip25.addMouseListener(misesGhostComponentAdapter = new
		 * MisesGhostComponentAdapter((GhostGlassPane)
		 * this.frame.getGlassPane(), TypeMise.MISE_25));
		 * misesGhostComponentAdapter.addGhostDropListener(ghostDropListener);
		 * lblChip25.addMouseMotionListener(new
		 * GhostMotionAdapter((GhostGlassPane) this.frame.getGlassPane()));
		 */
		/*
		 * lblChip50.addMouseListener(misesGhostComponentAdapter = new
		 * MisesGhostComponentAdapter((GhostGlassPane)
		 * this.frame.getGlassPane(), TypeMise.MISE_50));
		 * misesGhostComponentAdapter.addGhostDropListener(ghostDropListener);
		 * lblChip50.addMouseMotionListener(new
		 * GhostMotionAdapter((GhostGlassPane) this.frame.getGlassPane()));
		 */

		JLabel lblChip50 = new JLabel(new ImageIcon(VueRouletteActions.class.getResource("/img/chip_50.png")));
		lblChip50.setName("lblChip50");
		lblChip50.setTransferHandler(new TransferHandler("text"));
		pnlJetons.add(lblChip50);

		this.add(pnlJetons, new GridBagHelper().setXY(0, 1).end());

		this.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	}

	public void initDragAndDrop(JComponent target, MisesDroppableReceiver receiver) {
		this.initMisesDragAndDrop(target, receiver);
	}

	private void initMisesDragAndDrop(JComponent target, MisesDroppableReceiver receiver) {
		// VueRouletteTapis tapis = (VueRouletteTapis)
		// ((Vue)this.frame.getVueComponentByName("VueRoulette")).getComponentByName("tapis");
		GhostDropListener ghostDropListener = new MisesGhostDropManager(target, receiver);
		// JPanel pnlJetons = this.getComponentByName("lblChip5")
		this.setMisesDragAndDrop(ghostDropListener, this.getComponentByName("lblChip5"), TypeMise.MISE_5);
		this.setMisesDragAndDrop(ghostDropListener, this.getComponentByName("lblChip10"), TypeMise.MISE_10);
		this.setMisesDragAndDrop(ghostDropListener, this.getComponentByName("lblChip25"), TypeMise.MISE_25);
		this.setMisesDragAndDrop(ghostDropListener, this.getComponentByName("lblChip50"), TypeMise.MISE_50);
	}

	private void setMisesDragAndDrop(GhostDropListener ghostDropListener, Component component, TypeMise typeMise) {
		MisesGhostComponentAdapter misesGhostComponentAdapter = new MisesGhostComponentAdapter((GhostGlassPane) this.frame.getGlassPane(), typeMise);
		component.addMouseListener(misesGhostComponentAdapter);
		misesGhostComponentAdapter.addGhostDropListener(ghostDropListener);
		component.addMouseMotionListener(new GhostMotionAdapter((GhostGlassPane) this.frame.getGlassPane()));
	}

	@Override
	public void update(Observable observable) {
		// TODO Auto-generated method stub

	}

}
