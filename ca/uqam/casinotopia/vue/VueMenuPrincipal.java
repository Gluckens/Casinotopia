package ca.uqam.casinotopia.vue;


import ca.uqam.casinotopia.controleur.client.ControleurClientPrincipal;
import ca.uqam.casinotopia.observateur.Sujet;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VueMenuPrincipal extends Vue {
	
	private ControleurClientPrincipal controleur;

	/**
	 * Create the panel.
	 */
	public VueMenuPrincipal(ControleurClientPrincipal controleur) {
		this.controleur = controleur;
		
		this.setPanelOptions();
		this.addComponents();
		this.createComponentsMap();
	}
	


	@Override
	protected void addComponents() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{450, 0};
		gridBagLayout.rowHeights = new int[]{139, 159, 0};
		gridBagLayout.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JButton btnJoueurLa = new JButton("Joueur \u00E0 la roulette");
		btnJoueurLa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controleur.cmdJouerRoulette();
			}
		});
		GridBagConstraints gbc_btnJoueurLa = new GridBagConstraints();
		gbc_btnJoueurLa.anchor = GridBagConstraints.SOUTH;
		gbc_btnJoueurLa.insets = new Insets(0, 0, 5, 0);
		gbc_btnJoueurLa.gridx = 0;
		gbc_btnJoueurLa.gridy = 0;
		add(btnJoueurLa, gbc_btnJoueurLa);
		
		JButton btnAllerSurLe = new JButton("Aller sur le chat");
		GridBagConstraints gbc_btnAllerSurLe = new GridBagConstraints();
		gbc_btnAllerSurLe.anchor = GridBagConstraints.NORTH;
		gbc_btnAllerSurLe.gridx = 0;
		gbc_btnAllerSurLe.gridy = 1;
		add(btnAllerSurLe, gbc_btnAllerSurLe);
	}

	@Override
	public void update(Sujet sujet) {
		// TODO Auto-generated method stub
		
	}

}