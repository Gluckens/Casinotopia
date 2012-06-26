package ca.uqam.casinotopia.vue.navigation;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

import ca.uqam.casinotopia.controleur.ControleurClient;
import ca.uqam.casinotopia.controleur.client.ControleurBarreMenuBas;
import ca.uqam.casinotopia.modele.client.ModeleClientClient;
import ca.uqam.casinotopia.observateur.Observable;
import ca.uqam.casinotopia.vue.GridBagHelper;
import ca.uqam.casinotopia.vue.Vue;

/**
 * Vue principale de la barre de menu au bas de l'application
 */
@SuppressWarnings("serial")
public class VueBarreMenuBas extends Vue {
	
	/**
	 * Controleur associé à la vue
	 */
	private ControleurBarreMenuBas controleur;

	public VueBarreMenuBas(ControleurClient controleur) {
		this.controleur = (ControleurBarreMenuBas) controleur;

		this.setPanelOptions();
		this.addComponents();
		this.createComponentsMap();
	}

	@Override
	protected void addComponents() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 128, 128, 512, 128, 128 };
		gridBagLayout.rowHeights = new int[] { 45 };
		this.setLayout(gridBagLayout);

		JButton btnMenu = new JButton("Menu");
		btnMenu.setName("btnMenu");
		this.add(btnMenu, new GridBagHelper().setXY(0, 0).end());
		
		JButton btnGestionCompte = new JButton("Gestion compte");
		btnMenu.setName("btnGestionCompte");
		btnGestionCompte.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				controleur.afficherGestionCompte();
			}
		});
		this.add(btnGestionCompte, new GridBagHelper().setXY(1, 0).end());
		
		JLabel lblSoldeClient = new JLabel(String.valueOf(this.controleur.getModeleClient().getSolde()));
		lblSoldeClient.setName("lblSoldeClient");
		this.add(lblSoldeClient, new GridBagHelper().setXY(4, 0).end());
	}
	
	/**
	 * Mise à jour du solde du client dans l'interface
	 * 
	 * @param nouveauSolde Le nouveau solde du client
	 */
	private void updateSolde(int nouveauSolde) {
		((JLabel)this.getComponentByName("lblSoldeClient")).setText(String.valueOf(nouveauSolde));
	}

	@Override
	public void update(Observable observable) {
		if(observable instanceof ModeleClientClient) {
			ModeleClientClient modele = (ModeleClientClient) observable;
			switch (modele.getTypeModif()) {
				case UPDATE_SOLDE :
					this.updateSolde(modele.getSolde());
					break;
			}
		}
	}
}