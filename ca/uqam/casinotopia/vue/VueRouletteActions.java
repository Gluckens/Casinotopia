package ca.uqam.casinotopia.vue;

import java.awt.Dimension;
import java.awt.GridBagLayout;

import ca.uqam.casinotopia.controleur.ControleurClient;
import ca.uqam.casinotopia.controleur.client.ControleurRouletteClient;
import ca.uqam.casinotopia.drag_n_drop.GhostComponentAdapter;
import ca.uqam.casinotopia.drag_n_drop.GhostGlassPane;
import ca.uqam.casinotopia.drag_n_drop.GhostMotionAdapter;
import ca.uqam.casinotopia.observateur.Observable;
import javax.swing.JLabel;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.TransferHandler;

import javax.swing.border.EtchedBorder;


@SuppressWarnings("serial")
public class VueRouletteActions extends Vue {

	private ControleurRouletteClient controleur;
	private FrameApplication frame;

	/**
	 * Create the panel.
	 */
	public VueRouletteActions(ControleurClient controleur, FrameApplication frame) {
		this.controleur = (ControleurRouletteClient) controleur;
		this.frame = frame;
		this.setPanelOptions();
		this.addComponents();
		this.createComponentsMap();
	}

	@Override
	protected void addComponents() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{300, 250, 250};
		gridBagLayout.rowHeights = new int[]{0, 43, 82};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0};
		setLayout(gridBagLayout);
		
		setPreferredSize(new Dimension(800, 180));
		
		JLabel lblVosOptions = new JLabel("Vos options :");
		lblVosOptions.setFont(new Font("Tahoma", Font.ITALIC, 24));
		GridBagConstraints gbc_lblVosOptions = new GridBagConstraints();
		gbc_lblVosOptions.insets = new Insets(0, 0, 5, 5);
		gbc_lblVosOptions.gridx = 0;
		gbc_lblVosOptions.gridy = 0;
		add(lblVosOptions, gbc_lblVosOptions);
		
		JButton btnMiser = new JButton("Miser");
		btnMiser.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnMiser.setToolTipText("Ouvrir la fen\u00EAtre de mise");
		GridBagConstraints gbc_btnMiser = new GridBagConstraints();
		gbc_btnMiser.insets = new Insets(0, 0, 5, 5);
		gbc_btnMiser.gridx = 1;
		gbc_btnMiser.gridy = 1;
		add(btnMiser, gbc_btnMiser);
		
		JButton btnPrt = new JButton("Pr\u00EAt");
		btnPrt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnPrt.setToolTipText("Pr\u00EAt \u00E0 tourner la roulette");
		GridBagConstraints gbc_btnPrt = new GridBagConstraints();
		gbc_btnPrt.insets = new Insets(0, 0, 5, 0);
		gbc_btnPrt.gridx = 2;
		gbc_btnPrt.gridy = 1;
		add(btnPrt, gbc_btnPrt);
		
		JPanel pnlJetons = new JPanel();
		pnlJetons.setLayout(new FlowLayout());
		
		
		JLabel lblChip5 = new JLabel(new ImageIcon(VueRouletteActions.class.getResource("/img/chip_5.png")));
		lblChip5.setTransferHandler(new TransferHandler("text"));
		lblChip5.addMouseListener(new GhostComponentAdapter((GhostGlassPane) this.frame.getGlassPane(), "action_de_chip_5"));
		lblChip5.addMouseMotionListener(new GhostMotionAdapter((GhostGlassPane) this.frame.getGlassPane()));
		pnlJetons.add(lblChip5);
		
		JLabel lblChip10 = new JLabel(new ImageIcon(VueRouletteActions.class.getResource("/img/chip_10.png")));
		lblChip10.setTransferHandler(new TransferHandler("text"));
		lblChip10.addMouseListener(new GhostComponentAdapter((GhostGlassPane) this.frame.getGlassPane(), "action_de_chip_10"));
		lblChip10.addMouseMotionListener(new GhostMotionAdapter((GhostGlassPane) this.frame.getGlassPane()));
		pnlJetons.add(lblChip10);
		
		JLabel lblChip25 = new JLabel(new ImageIcon(VueRouletteActions.class.getResource("/img/chip_25.png")));
		lblChip25.setTransferHandler(new TransferHandler("text"));
		lblChip25.addMouseListener(new GhostComponentAdapter((GhostGlassPane) this.frame.getGlassPane(), "action_de_chip_25"));
		lblChip25.addMouseMotionListener(new GhostMotionAdapter((GhostGlassPane) this.frame.getGlassPane()));
		pnlJetons.add(lblChip25);
		
		JLabel lblChip50 = new JLabel(new ImageIcon(VueRouletteActions.class.getResource("/img/chip_50.png")));
		lblChip50.setTransferHandler(new TransferHandler("text"));
		lblChip50.addMouseListener(new GhostComponentAdapter((GhostGlassPane) this.frame.getGlassPane(), "action_de_chip_50"));
		lblChip50.addMouseMotionListener(new GhostMotionAdapter((GhostGlassPane) this.frame.getGlassPane()));
		pnlJetons.add(lblChip50);
		
		this.add(pnlJetons, new GridBagHelper().setXY(0,  2).end());
		
		
		this.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	}

	@Override
	public void update(Observable observable) {
		// TODO Auto-generated method stub
		
	}

}
