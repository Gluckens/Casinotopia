package ca.uqam.casinotopia.vue.roulette;

import ca.uqam.casinotopia.Case;
import ca.uqam.casinotopia.controleur.ControleurClient;
import ca.uqam.casinotopia.controleur.client.ControleurRouletteClient;
import ca.uqam.casinotopia.modele.client.ModeleTableJeuClient;
import ca.uqam.casinotopia.observateur.Observable;
import ca.uqam.casinotopia.vue.FrameApplication;
import ca.uqam.casinotopia.vue.GridBagHelper;
import ca.uqam.casinotopia.vue.Vue;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import java.awt.GridBagConstraints;
import java.util.Map;

import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;

@SuppressWarnings("serial")
public class VueRouletteTableau extends Vue {
	private ControleurRouletteClient controleur;
	private FrameApplication frame;

	/**
	 * Create the panel.
	 */
	public VueRouletteTableau(ControleurClient controleur, FrameApplication frame) {
		this.controleur = (ControleurRouletteClient) controleur;
		this.frame = frame;
		
		this.setPanelOptions();
		this.addComponents();
		this.createComponentsMap();
	}

	@Override
	protected void addComponents() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{224};
		gridBagLayout.rowHeights = new int[]{24, 270};
		gridBagLayout.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		setPreferredSize(new Dimension(224, 294));
		
		
		JLabel lblTitre = new JLabel("Résumé");
		lblTitre.setFont(new Font("Tahoma", Font.ITALIC, 16));
		lblTitre.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(lblTitre, new GridBagHelper().setXY(0, 0).setFill(GridBagConstraints.BOTH).end());
		
		JTable tblResume = new JTable();
		tblResume.setName("tblResume");
		tblResume.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblResume.setModel(new DefaultTableModel(
			new Object[][] {
				{1, null, null, null, null},
				{2, null, null, null, null},
				{3, null, null, null, null},
				{4, null, null, null, null},
				{5, null, null, null, null},
				{6, null, null, null, null},
				{7, null, null, null, null},
				{8, null, null, null, null},
				{9, null, null, null, null},
				{10, null, null, null, null},
				{11, null, null, null, null},
				{12, null, null, null, null},
				{13, null, null, null, null},
				{14, null, null, null, null},
				{15, null, null, null, null},
				{16, null, null, null, null},
			},
			new String[] {
				"Cases", "Joueur 1", "Joueur 2", "Joueur 3", "Joueur 4"
			}
		));
		
		this.add(tblResume, new GridBagHelper().setXY(0, 1).setFill(GridBagConstraints.HORIZONTAL).end());
	}
	
	private void updateTableJeu(Map<Case, Map<Integer, Integer>> cases) {
		DefaultTableModel tblModel = (DefaultTableModel) ((JTable) this.getComponentByName("tblResume")).getModel();
		
		//Vector<>
		
		//tblModel.set
		
	}

	@Override
	public void update(Observable observable) {
		if(observable instanceof ModeleTableJeuClient) {
			this.updateTableJeu(((ModeleTableJeuClient)observable).getCases());
		}
	}

}
