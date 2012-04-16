package ca.uqam.casinotopia.vue;

import java.awt.Dimension;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;

import ca.uqam.casinotopia.Case;
import ca.uqam.casinotopia.modele.ModeleRouletteClient;
import ca.uqam.casinotopia.observateur.Sujet;


public class VueRoulette extends Vue {

	/**
	 * Create the panel.
	 */
	public VueRoulette() {
		this.setPanelOptions();
		this.addComponents();
		this.createComponentsMap();
	}
	
	@Override
	protected void addComponents() {
		JLabel lblMonLabel = new JLabel("Mon label");
		lblMonLabel.setBounds(162, 104, 45, 14);
		lblMonLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMonLabel.setName("lblMonLabel");
		this.add(lblMonLabel);
		
		JTextField txtDefault = new JTextField();
		txtDefault.setBounds(217, 101, 86, 20);
		txtDefault.setHorizontalAlignment(SwingConstants.LEFT);
		txtDefault.setText("default");
		txtDefault.setColumns(10);
		txtDefault.setPreferredSize(new Dimension(100, 20));
		txtDefault.setName("txtDefault");
		this.add(txtDefault);
		
		JButton btnTest = new JButton("Test");
		btnTest.setBounds(180, 144, 89, 23);
		this.add(btnTest);
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
