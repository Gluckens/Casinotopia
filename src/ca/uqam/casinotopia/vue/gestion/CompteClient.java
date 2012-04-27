package ca.uqam.casinotopia.vue.gestion;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JButton;

public class CompteClient extends JPanel {
	private JTextField txtPrenom;
	private JTextField txtNom;
	private JTextField txtDateDeNaissance;
	private JTextField txtCourriel;
	private JTextField txtUtilisateur;

	/**
	 * Create the panel.
	 */
	public CompteClient() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{83, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblPrenom = new JLabel("Pr\u00E9nom :");
		GridBagConstraints gbc_lblPrenom = new GridBagConstraints();
		gbc_lblPrenom.insets = new Insets(3, 3, 5, 5);
		gbc_lblPrenom.anchor = GridBagConstraints.EAST;
		gbc_lblPrenom.gridx = 0;
		gbc_lblPrenom.gridy = 0;
		add(lblPrenom, gbc_lblPrenom);
		
		txtPrenom = new JTextField();
		lblPrenom.setLabelFor(txtPrenom);
		GridBagConstraints gbc_txtPrenom = new GridBagConstraints();
		gbc_txtPrenom.gridwidth = 3;
		gbc_txtPrenom.insets = new Insets(3, 3, 5, 5);
		gbc_txtPrenom.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPrenom.gridx = 1;
		gbc_txtPrenom.gridy = 0;
		add(txtPrenom, gbc_txtPrenom);
		txtPrenom.setColumns(10);
		
		JLabel lblNom = new JLabel("Nom :");
		GridBagConstraints gbc_lblNom = new GridBagConstraints();
		gbc_lblNom.insets = new Insets(3, 3, 5, 5);
		gbc_lblNom.anchor = GridBagConstraints.EAST;
		gbc_lblNom.gridx = 0;
		gbc_lblNom.gridy = 1;
		add(lblNom, gbc_lblNom);
		
		txtNom = new JTextField();
		lblNom.setLabelFor(txtNom);
		GridBagConstraints gbc_txtNom = new GridBagConstraints();
		gbc_txtNom.gridwidth = 3;
		gbc_txtNom.insets = new Insets(3, 3, 5, 5);
		gbc_txtNom.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNom.gridx = 1;
		gbc_txtNom.gridy = 1;
		add(txtNom, gbc_txtNom);
		txtNom.setColumns(10);
		
		JLabel lblDateDeNaissance = new JLabel("Date de naissance :");
		GridBagConstraints gbc_lblDateDeNaissance = new GridBagConstraints();
		gbc_lblDateDeNaissance.anchor = GridBagConstraints.EAST;
		gbc_lblDateDeNaissance.insets = new Insets(3, 3, 5, 5);
		gbc_lblDateDeNaissance.gridx = 0;
		gbc_lblDateDeNaissance.gridy = 2;
		add(lblDateDeNaissance, gbc_lblDateDeNaissance);
		
		txtDateDeNaissance = new JTextField();
		lblDateDeNaissance.setLabelFor(txtDateDeNaissance);
		GridBagConstraints gbc_txtDateDeNaissance = new GridBagConstraints();
		gbc_txtDateDeNaissance.gridwidth = 3;
		gbc_txtDateDeNaissance.insets = new Insets(3, 3, 5, 5);
		gbc_txtDateDeNaissance.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtDateDeNaissance.gridx = 1;
		gbc_txtDateDeNaissance.gridy = 2;
		add(txtDateDeNaissance, gbc_txtDateDeNaissance);
		txtDateDeNaissance.setColumns(10);
		
		JLabel lblCourriel = new JLabel("Courriel :");
		GridBagConstraints gbc_lblCourriel = new GridBagConstraints();
		gbc_lblCourriel.insets = new Insets(3, 3, 5, 5);
		gbc_lblCourriel.anchor = GridBagConstraints.EAST;
		gbc_lblCourriel.gridx = 0;
		gbc_lblCourriel.gridy = 3;
		add(lblCourriel, gbc_lblCourriel);
		
		txtCourriel = new JTextField();
		lblCourriel.setLabelFor(txtCourriel);
		GridBagConstraints gbc_txtCourriel = new GridBagConstraints();
		gbc_txtCourriel.gridwidth = 3;
		gbc_txtCourriel.insets = new Insets(3, 3, 5, 5);
		gbc_txtCourriel.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCourriel.gridx = 1;
		gbc_txtCourriel.gridy = 3;
		add(txtCourriel, gbc_txtCourriel);
		txtCourriel.setColumns(10);
		
		JLabel lblUtilisateur = new JLabel("Nom d'utilisateur :");
		GridBagConstraints gbc_lblUtilisateur = new GridBagConstraints();
		gbc_lblUtilisateur.anchor = GridBagConstraints.EAST;
		gbc_lblUtilisateur.insets = new Insets(3, 3, 5, 5);
		gbc_lblUtilisateur.gridx = 0;
		gbc_lblUtilisateur.gridy = 4;
		add(lblUtilisateur, gbc_lblUtilisateur);
		
		txtUtilisateur = new JTextField();
		lblUtilisateur.setLabelFor(txtUtilisateur);
		GridBagConstraints gbc_txtUtilisateur = new GridBagConstraints();
		gbc_txtUtilisateur.gridwidth = 3;
		gbc_txtUtilisateur.insets = new Insets(3, 3, 5, 5);
		gbc_txtUtilisateur.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtUtilisateur.gridx = 1;
		gbc_txtUtilisateur.gridy = 4;
		add(txtUtilisateur, gbc_txtUtilisateur);
		txtUtilisateur.setColumns(10);
		
		JButton btnAppliquer = new JButton("Appliquer");
		GridBagConstraints gbc_btnAppliquer = new GridBagConstraints();
		gbc_btnAppliquer.anchor = GridBagConstraints.EAST;
		gbc_btnAppliquer.insets = new Insets(0, 0, 0, 5);
		gbc_btnAppliquer.gridx = 1;
		gbc_btnAppliquer.gridy = 5;
		add(btnAppliquer, gbc_btnAppliquer);
		
		JButton btnOk = new JButton("Ok");
		GridBagConstraints gbc_btnOk = new GridBagConstraints();
		gbc_btnOk.insets = new Insets(0, 0, 0, 5);
		gbc_btnOk.gridx = 2;
		gbc_btnOk.gridy = 5;
		add(btnOk, gbc_btnOk);
		
		JButton btnAnnuler = new JButton("Annuler");
		GridBagConstraints gbc_btnAnnuler = new GridBagConstraints();
		gbc_btnAnnuler.gridx = 3;
		gbc_btnAnnuler.gridy = 5;
		add(btnAnnuler, gbc_btnAnnuler);

	}

}
