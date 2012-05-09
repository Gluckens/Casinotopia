package ca.uqam.casinotopia.vue;

import ca.uqam.casinotopia.controleur.client.ControleurMenuPrincipal;
import ca.uqam.casinotopia.modele.client.ModeleChatClient;
import ca.uqam.casinotopia.observateur.Observable;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class VueMenuPrincipal extends Vue {
	private ControleurMenuPrincipal controleur;

	/**
	 * Create the panel.
	 */
	public VueMenuPrincipal(ControleurMenuPrincipal controleur) {
		this.controleur = controleur;

		this.setPanelOptions();
		this.addComponents();
		this.createComponentsMap();
	}

	@Override
	protected void addComponents() {
		GridBagLayout gridBagLayout = new GridBagLayout();
<<<<<<< HEAD
		gridBagLayout.columnWidths = new int[] { 450, 0 };
		gridBagLayout.rowHeights = new int[] { 100, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
=======
		gridBagLayout.columnWidths = new int[] { 450 };
		gridBagLayout.rowHeights = new int[] { 100, 100, 100 };
		/*gridBagLayout.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };*/
>>>>>>> 6178ed470c5b0c0592e4f6f85187bc2aa922a10a
		this.setLayout(gridBagLayout);
		
		JButton btnSalle = new JButton("Afficher la salle");
		btnSalle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				controleur.cmdJoindreSalle("MEGAFUN");
				//controleur.afficherSalle("MEGAFUN");
			}
		});
		this.add(btnSalle, new GridBagHelper().setXY(0, 0).end());

		JButton btnRoulette = new JButton("Joueur \u00E0 la roulette");
		btnRoulette.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				controleur.cmdJouerRoulette();
			}
		});
		this.add(btnRoulette, new GridBagHelper().setXY(0, 1).end());

		JButton btnChat = new JButton("Aller sur le chat");
		btnChat.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				controleur.actionAfficherChat(new ModeleChatClient());
			}
		});
<<<<<<< HEAD
		GridBagConstraints gbc_btnAllerSurLe = new GridBagConstraints();
		gbc_btnAllerSurLe.insets = new Insets(0, 0, 5, 0);
		gbc_btnAllerSurLe.anchor = GridBagConstraints.NORTH;
		gbc_btnAllerSurLe.gridx = 0;
		gbc_btnAllerSurLe.gridy = 1;
		this.add(btnAllerSurLe, gbc_btnAllerSurLe);
		
		JButton btnMachine = new JButton("Jouer \u00E0 la machine \u00E0 sous");
		btnMachine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controleur.actionJouerMachine();
			}
		});
		GridBagConstraints gbc_btnJouerLa = new GridBagConstraints();
		gbc_btnJouerLa.gridx = 0;
		gbc_btnJouerLa.gridy = 2;
		add(btnMachine, gbc_btnJouerLa);
=======
		this.add(btnChat, new GridBagHelper().setXY(0, 2).end());
>>>>>>> 6178ed470c5b0c0592e4f6f85187bc2aa922a10a
	}

	@Override
	public void update(Observable observable) {
		// TODO Auto-generated method stub

	}

}
