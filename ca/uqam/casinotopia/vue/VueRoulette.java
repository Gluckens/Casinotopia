package ca.uqam.casinotopia.vue;
import java.awt.Component;
import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;

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
	
	public void updateTableJeu(String arg) {
		String toto = arg;
	}

	@Override
	public void update(Sujet sujet) {
		//boolean test = Sujet.getClass(). .isAssignableFrom(sujet);
		
		String name = "Sujet";
		
		Class<? extends Sujet> c = sujet.getClass();
			
		System.out.println(c.getSimpleName());
	}
}
