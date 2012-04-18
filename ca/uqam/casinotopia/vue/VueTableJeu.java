package ca.uqam.casinotopia.vue;

import javax.swing.JPanel;

import ca.uqam.casinotopia.Case;
import ca.uqam.casinotopia.controleur.client.ControleurRouletteClient;
import ca.uqam.casinotopia.modele.client.ModeleTableJeuClient;
import ca.uqam.casinotopia.observateur.Sujet;
import java.awt.GridBagLayout;
import javax.swing.JTable;
import java.awt.GridBagConstraints;
import java.util.Map;

import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;

import sun.awt.DefaultMouseInfoPeer;

public class VueTableJeu extends Vue {
	
	private ControleurRouletteClient controleur;

	/**
	 * Create the panel.
	 */
	public VueTableJeu() {
		this.setPanelOptions();
		this.addComponents();
		this.createComponentsMap();
	}

	@Override
	protected void addComponents() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JTable table = new JTable();
		table.setName("tblCases");
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(new DefaultTableModel(
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
			},
			new String[] {
				"Cases", "Joueur 1", "Joueur 2", "Joueur 3", "Joueur 4"
			}
		));
		GridBagConstraints gbc_table = new GridBagConstraints();
		gbc_table.fill = GridBagConstraints.BOTH;
		gbc_table.gridx = 0;
		gbc_table.gridy = 0;
		add(table, gbc_table);
	}
	
	private void updateTableJeu(Map<Case, Map<Integer, Integer>> cases) {
		DefaultTableModel tblModel = (DefaultTableModel) ((JTable) this.getComponentByName("tblCases")).getModel();
		
		//Vector<>
		
		//tblModel.set
		
	}

	@Override
	public void update(Sujet sujet) {
		if(sujet instanceof ModeleTableJeuClient) {
			this.updateTableJeu(((ModeleTableJeuClient)sujet).getCases());
		}
	}

}
