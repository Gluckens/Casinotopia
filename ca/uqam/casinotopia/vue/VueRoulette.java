package ca.uqam.casinotopia.vue;

import java.awt.Dimension;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;

import ca.uqam.casinotopia.Case;
import ca.uqam.casinotopia.TypeCase;
import ca.uqam.casinotopia.controleur.Controleur;
import ca.uqam.casinotopia.controleur.client.ControleurRouletteClient;
import ca.uqam.casinotopia.modele.client.ModeleRouletteClient;
import ca.uqam.casinotopia.observateur.Sujet;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class VueRoulette extends Vue {
	
	private ControleurRouletteClient controleur;

	/**
	 * Create the panel.
	 */
	public VueRoulette(ControleurRouletteClient controleur) {
		this.controleur = controleur;
		
		this.setPanelOptions();
		this.addComponents();
		this.createComponentsMap();
	}
	
	@Override
	protected void addComponents() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{128, 73, 86, 53, 0};
		gridBagLayout.rowHeights = new int[]{23, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		JLabel lblMonLabel = new JLabel("Mon label");
		lblMonLabel.setBounds(162, 104, 45, 14);
		lblMonLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMonLabel.setName("lblMonLabel");
		GridBagConstraints gbc_lblMonLabel = new GridBagConstraints();
		gbc_lblMonLabel.anchor = GridBagConstraints.WEST;
		gbc_lblMonLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblMonLabel.gridx = 1;
		gbc_lblMonLabel.gridy = 3;
		this.add(lblMonLabel, gbc_lblMonLabel);
		
		JTextField txtDefault = new JTextField();
		txtDefault.setBounds(217, 101, 86, 20);
		txtDefault.setHorizontalAlignment(SwingConstants.LEFT);
		txtDefault.setText("default");
		txtDefault.setColumns(10);
		txtDefault.setPreferredSize(new Dimension(100, 20));
		txtDefault.setName("txtDefault");
		GridBagConstraints gbc_txtDefault = new GridBagConstraints();
		gbc_txtDefault.anchor = GridBagConstraints.WEST;
		gbc_txtDefault.insets = new Insets(0, 0, 5, 5);
		gbc_txtDefault.gridx = 2;
		gbc_txtDefault.gridy = 3;
		this.add(txtDefault, gbc_txtDefault);
		
		JButton btnTest = new JButton("Test");
		btnTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Map<Integer, Map<Case, Integer>> mises = new HashMap<Integer, Map<Case, Integer>>();
				
				int joueurId = 4;
				
				Map<Case, Integer> misesCases = new HashMap<Case, Integer>();
				
				misesCases.put(new Case(1, "noire", false, TypeCase.CHIFFRE), 5);
				misesCases.put(new Case(2, "rouge", true, TypeCase.CHIFFRE), 2);
				misesCases.put(new Case(3, "rouge", false, TypeCase.CHIFFRE), 8);
				misesCases.put(new Case(4, "noire", true, TypeCase.CHIFFRE), 8);
				misesCases.put(new Case(5, "noire", false, TypeCase.CHIFFRE), 1);
				misesCases.put(new Case(6, "rouge", true, TypeCase.CHIFFRE), 3);
				
				mises.put(joueurId, misesCases);
				
				controleur.cmdMiserRoulette(mises);
			}
		});
		btnTest.setBounds(180, 144, 89, 23);
		GridBagConstraints gbc_btnTest = new GridBagConstraints();
		gbc_btnTest.insets = new Insets(0, 0, 0, 5);
		gbc_btnTest.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnTest.gridx = 1;
		gbc_btnTest.gridy = 5;
		this.add(btnTest, gbc_btnTest);
		
		JButton btnTest_1 = new JButton("Test 2");
		btnTest_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Map<Integer, Map<Case, Integer>> mises = new HashMap<Integer, Map<Case, Integer>>();
				
				int joueurId2 = 9;
				
				Map<Case, Integer> misesCases = new HashMap<Case, Integer>();
				
				misesCases.put(new Case(1, "noire", false, TypeCase.CHIFFRE), 2);
				misesCases.put(new Case(2, "rouge", true, TypeCase.CHIFFRE), 7);
				misesCases.put(new Case(5, "noire", false, TypeCase.CHIFFRE), 6);
				
				mises.put(joueurId2, misesCases);
				
				controleur.cmdMiserRoulette(mises);
			}
		});
		GridBagConstraints gbc_btnTest_1 = new GridBagConstraints();
		gbc_btnTest_1.insets = new Insets(0, 0, 0, 5);
		gbc_btnTest_1.gridx = 2;
		gbc_btnTest_1.gridy = 5;
		add(btnTest_1, gbc_btnTest_1);
	}
	
	public void updateTableJeu(Map<Case, Map<Integer, Integer>> cases) {
		JTextField txt = (JTextField) this.getComponentByName("txtDefault");
		Calendar cal = Calendar.getInstance();
	    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	    String time = sdf.format(cal.getTime());
		txt.setText(String.valueOf(cases.size()) + " " + time);
	}

	@Override
	public void update(Sujet sujet) {
		if(sujet instanceof ModeleRouletteClient) {
			this.updateTableJeu(((ModeleRouletteClient)sujet).getTableJeu().getCases());
		}
	}
}
