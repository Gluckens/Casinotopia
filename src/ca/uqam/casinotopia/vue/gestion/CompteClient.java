package ca.uqam.casinotopia.vue.gestion;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;

import ca.uqam.casinotopia.controleur.client.ControleurPrincipalClient;
import ca.uqam.casinotopia.modele.client.ModeleClientClient;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

@SuppressWarnings("serial")
public class CompteClient extends JPanel implements FocusListener{
	private JTextField txtPrenom;
	private JTextField txtNom;
	private JTextField txtDateDeNaissance;
	private JTextField txtCourriel;
	private JTextField txtUtilisateur;
	private JPasswordField txtMotPasse;
	private ArrayList<String> listeImagesAvatar = new ArrayList<String>();
	private int posAvatar;
	private JLabel imageAvatar;
	private ControleurPrincipalClient controleur;
	private boolean creerCompte;

	/**
	 * Create the panel.
	 */
	public CompteClient(ControleurPrincipalClient ctrl, boolean nouvCompte) {
		
		this.controleur = ctrl;
		this.creerCompte = nouvCompte;
		
		listeImagesAvatar.add("c1.gif");
		listeImagesAvatar.add("c2.gif");
		listeImagesAvatar.add("c3.gif");
		listeImagesAvatar.add("c4.gif");
		listeImagesAvatar.add("c5.gif");
		listeImagesAvatar.add("c6.gif");
		listeImagesAvatar.add("c7.gif");
		listeImagesAvatar.add("c8.gif");
		listeImagesAvatar.add("c9.gif");
		listeImagesAvatar.add("c10.gif");
		listeImagesAvatar.add("c11.gif");
		
		posAvatar = 0;
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{83, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblCompte = new JLabel("Compte Client");
		lblCompte.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_lblCompte = new GridBagConstraints();
		gbc_lblCompte.gridwidth = 4;
		gbc_lblCompte.insets = new Insets(0, 0, 5, 5);
		gbc_lblCompte.gridx = 0;
		gbc_lblCompte.gridy = 0;
		add(lblCompte, gbc_lblCompte);
		
		JLabel lblPrenom = new JLabel("Pr\u00E9nom :");
		GridBagConstraints gbc_lblPrenom = new GridBagConstraints();
		gbc_lblPrenom.insets = new Insets(3, 3, 5, 5);
		gbc_lblPrenom.anchor = GridBagConstraints.EAST;
		gbc_lblPrenom.gridx = 0;
		gbc_lblPrenom.gridy = 1;
		add(lblPrenom, gbc_lblPrenom);
		
		txtPrenom = new JTextField();
		lblPrenom.setLabelFor(txtPrenom);
		GridBagConstraints gbc_txtPrenom = new GridBagConstraints();
		gbc_txtPrenom.gridwidth = 3;
		gbc_txtPrenom.insets = new Insets(3, 3, 5, 0);
		gbc_txtPrenom.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPrenom.gridx = 1;
		gbc_txtPrenom.gridy = 1;
		add(txtPrenom, gbc_txtPrenom);
		txtPrenom.setColumns(10);
		
		JLabel lblNom = new JLabel("Nom :");
		GridBagConstraints gbc_lblNom = new GridBagConstraints();
		gbc_lblNom.insets = new Insets(3, 3, 5, 5);
		gbc_lblNom.anchor = GridBagConstraints.EAST;
		gbc_lblNom.gridx = 0;
		gbc_lblNom.gridy = 2;
		add(lblNom, gbc_lblNom);
		
		txtNom = new JTextField();
		lblNom.setLabelFor(txtNom);
		GridBagConstraints gbc_txtNom = new GridBagConstraints();
		gbc_txtNom.gridwidth = 3;
		gbc_txtNom.insets = new Insets(3, 3, 5, 0);
		gbc_txtNom.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNom.gridx = 1;
		gbc_txtNom.gridy = 2;
		add(txtNom, gbc_txtNom);
		txtNom.setColumns(10);
		
		JLabel lblDateDeNaissance = new JLabel("Date de naissance :");
		GridBagConstraints gbc_lblDateDeNaissance = new GridBagConstraints();
		gbc_lblDateDeNaissance.anchor = GridBagConstraints.EAST;
		gbc_lblDateDeNaissance.insets = new Insets(3, 3, 5, 5);
		gbc_lblDateDeNaissance.gridx = 0;
		gbc_lblDateDeNaissance.gridy = 3;
		add(lblDateDeNaissance, gbc_lblDateDeNaissance);
		
		txtDateDeNaissance = new JTextField();
		txtDateDeNaissance.addFocusListener(this);

		txtDateDeNaissance.setText("aaaa-mm-jj");
		lblDateDeNaissance.setLabelFor(txtDateDeNaissance);
		GridBagConstraints gbc_txtDateDeNaissance = new GridBagConstraints();
		gbc_txtDateDeNaissance.gridwidth = 3;
		gbc_txtDateDeNaissance.insets = new Insets(3, 3, 5, 0);
		gbc_txtDateDeNaissance.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtDateDeNaissance.gridx = 1;
		gbc_txtDateDeNaissance.gridy = 3;
		add(txtDateDeNaissance, gbc_txtDateDeNaissance);
		txtDateDeNaissance.setColumns(10);
		
		JLabel lblCourriel = new JLabel("Courriel :");
		GridBagConstraints gbc_lblCourriel = new GridBagConstraints();
		gbc_lblCourriel.insets = new Insets(3, 3, 5, 5);
		gbc_lblCourriel.anchor = GridBagConstraints.EAST;
		gbc_lblCourriel.gridx = 0;
		gbc_lblCourriel.gridy = 4;
		add(lblCourriel, gbc_lblCourriel);
		
		txtCourriel = new JTextField();
		lblCourriel.setLabelFor(txtCourriel);
		GridBagConstraints gbc_txtCourriel = new GridBagConstraints();
		gbc_txtCourriel.gridwidth = 3;
		gbc_txtCourriel.insets = new Insets(3, 3, 5, 0);
		gbc_txtCourriel.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCourriel.gridx = 1;
		gbc_txtCourriel.gridy = 4;
		add(txtCourriel, gbc_txtCourriel);
		txtCourriel.setColumns(10);
		
		JLabel lblUtilisateur = new JLabel("Nom d'utilisateur :");
		GridBagConstraints gbc_lblUtilisateur = new GridBagConstraints();
		gbc_lblUtilisateur.anchor = GridBagConstraints.EAST;
		gbc_lblUtilisateur.insets = new Insets(3, 3, 5, 5);
		gbc_lblUtilisateur.gridx = 0;
		gbc_lblUtilisateur.gridy = 5;
		add(lblUtilisateur, gbc_lblUtilisateur);
		
		txtUtilisateur = new JTextField();
		lblUtilisateur.setLabelFor(txtUtilisateur);
		GridBagConstraints gbc_txtUtilisateur = new GridBagConstraints();
		gbc_txtUtilisateur.gridwidth = 3;
		gbc_txtUtilisateur.insets = new Insets(3, 3, 5, 0);
		gbc_txtUtilisateur.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtUtilisateur.gridx = 1;
		gbc_txtUtilisateur.gridy = 5;
		add(txtUtilisateur, gbc_txtUtilisateur);
		txtUtilisateur.setColumns(10);
		
		JLabel lblMotPasse = new JLabel("Nom de passe :");
		GridBagConstraints gbc_lblMotPasse = new GridBagConstraints();
		gbc_lblMotPasse.anchor = GridBagConstraints.EAST;
		gbc_lblMotPasse.insets = new Insets(3, 3, 5, 5);
		gbc_lblMotPasse.gridx = 0;
		gbc_lblMotPasse.gridy = 6;
		add(lblMotPasse, gbc_lblMotPasse);
		
		txtMotPasse = new JPasswordField();
		lblMotPasse.setLabelFor(txtMotPasse);
		GridBagConstraints gbc_txtMotPasse = new GridBagConstraints();
		gbc_txtMotPasse.gridwidth = 3;
		gbc_txtMotPasse.insets = new Insets(3, 3, 5, 0);
		gbc_txtMotPasse.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtMotPasse.gridx = 1;
		gbc_txtMotPasse.gridy = 6;
		add(txtMotPasse, gbc_txtMotPasse);
		txtMotPasse.setColumns(10);
		
		
		JLabel lblAvatar = new JLabel("Avatar :");
		GridBagConstraints gbc_lblAvatar = new GridBagConstraints();
		gbc_lblAvatar.anchor = GridBagConstraints.EAST;
		gbc_lblAvatar.insets = new Insets(3, 3, 5, 5);
		gbc_lblAvatar.gridx = 0;
		gbc_lblAvatar.gridy = 7;
		add(lblAvatar, gbc_lblAvatar);
		
		
		JButton btnFlecheGauche = new JButton("<<");
		
		btnFlecheGauche.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (posAvatar==0)
				{
					posAvatar=listeImagesAvatar.size()-1;
				}
				else
				{
					posAvatar--;
				}
				changerImage();
			}
		});
		
		GridBagConstraints gbc_btnFlecheGauche = new GridBagConstraints();
		gbc_btnFlecheGauche.anchor = GridBagConstraints.EAST;
		gbc_btnFlecheGauche.insets = new Insets(3, 3, 5, 0);
		gbc_btnFlecheGauche.gridx = 1;
		gbc_btnFlecheGauche.gridy = 7;
		add(btnFlecheGauche, gbc_btnFlecheGauche);
		
		imageAvatar = new JLabel(new ImageIcon("src/img/avatar/"+listeImagesAvatar.get(posAvatar)));
		imageAvatar.setPreferredSize(new Dimension(300,300));
		GridBagConstraints gbc_imageAvatar = new GridBagConstraints();
		gbc_imageAvatar.anchor = GridBagConstraints.EAST;
		gbc_imageAvatar.insets = new Insets(3, 3, 3, 5);
		gbc_imageAvatar.gridx = 2;
		gbc_imageAvatar.gridy = 7;
		add(imageAvatar, gbc_imageAvatar);
		
		JButton btnFlecheDroite = new JButton(">>");
		
		btnFlecheDroite.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (posAvatar==(listeImagesAvatar.size()-1))
				{
					posAvatar=0;
				}
				else
				{
					posAvatar++;
				}
				changerImage();
			}
		});
		
		GridBagConstraints gbc_btnFlecheDroite = new GridBagConstraints();
		gbc_btnFlecheDroite.anchor = GridBagConstraints.EAST;
		gbc_btnFlecheDroite.insets = new Insets(3, 3, 5, 5);
		gbc_btnFlecheDroite.gridx = 3;
		gbc_btnFlecheDroite.gridy = 7;
		add(btnFlecheDroite, gbc_btnFlecheDroite);
		
		JButton btnAppliquer = new JButton("Appliquer");
		
		btnAppliquer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (txtUtilisateur.getText().length()>0 && txtMotPasse.getText().length()>0 && txtPrenom.getText().length()>0 && txtNom.getText().length()>0 && txtCourriel.getText().length()>0)
				{
					try
					{
						  String [] sd = txtDateDeNaissance.getText().split("-");
						  int year=Integer.parseInt(sd[0].trim());
						  int month=Integer.parseInt(sd[1].trim());
						  int date=Integer.parseInt(sd[2].trim());

						  Calendar cal=Calendar.getInstance();
						  cal.set(year,month,date);
						  java.sql.Date sD = new java.sql.Date(cal.getTimeInMillis());
						  if (creerCompte){
							  System.out.println("creation compte");
							  controleur.cmdCreerCompte(new ModeleClientClient(-1, txtUtilisateur.getText(), txtMotPasse.getText(), -1, txtPrenom.getText(),txtNom.getText(), sD, txtCourriel.getText(), 0,"/img/avatar/" + listeImagesAvatar.get(posAvatar)));
						  }
						  else {
							  //System.out.println("modification compte");
							  //ModeleClientClient c = new ModeleClientClient(controleur.getModeleClient().getId(),txtUtilisateur.getText(),txtMotPasse.getText(), controleur.getModeleClient().getId(), txtPrenom.getText(),txtNom.getText(),sD, txtCourriel.getText(), controleur.getModeleClient().getSolde(),"/img/avatar/" + listeImagesAvatar.get(posAvatar));
							  //System.out.println("modification : compte client " + " " + c.getId() + " " +  c.getNomUtilisateur() + " " +  c.getNom() + " " +  c.getPrenom() + " " +  c.getDateNaissance().toString());
							  //controleur.cmdModificationCompte(c);
							 controleur.cmdModifierCompte(new ModeleClientClient(controleur.getModeleClient().getId(), txtUtilisateur.getText(), txtMotPasse.getText(), controleur.getModeleClient().getId(), txtPrenom.getText(), txtNom.getText(), sD, txtCourriel.getText(), controleur.getModeleClient().getSolde(), "/img/avatar/" + listeImagesAvatar.get(posAvatar)));
						  }
						  
					}
					catch (Exception e) {
						System.out.println("erreur : " + e);
						JOptionPane.showMessageDialog(null, "La date de naissance n'est pas valide");
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "Remplissez tous les champs");
				}
			}
		});
		
		GridBagConstraints gbc_btnAppliquer = new GridBagConstraints();
		gbc_btnAppliquer.anchor = GridBagConstraints.EAST;
		gbc_btnAppliquer.insets = new Insets(0, 0, 0, 5);
		gbc_btnAppliquer.gridx = 1;
		gbc_btnAppliquer.gridy = 8;
		add(btnAppliquer, gbc_btnAppliquer);
		
		JButton btnFermer = new JButton("Fermer");
		
		btnFermer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				controleur.getModeleNav().cacherFrameGestionCompte();
			}

		});
		
		GridBagConstraints gbc_btnFermer = new GridBagConstraints();
		gbc_btnFermer.gridx = 3;
		gbc_btnFermer.gridy = 8;
		add(btnFermer, gbc_btnFermer);
		
		if (!nouvCompte){
			ModeleClientClient modeleClient = controleur.getModeleClient();
			txtUtilisateur.setText(modeleClient.getNomUtilisateur());
			txtUtilisateur.setEditable(false);
			txtMotPasse.setText("******");
			txtPrenom.setText(modeleClient.getPrenom());
			txtNom.setText(modeleClient.getNom());
			txtCourriel.setText(modeleClient.getCourriel());
			Date dNaissance = modeleClient.getDateNaissance();
			txtDateDeNaissance.setText(dNaissance.toString());
			txtMotPasse.setEditable(false);
			imageAvatar.setIcon(new ImageIcon("src/"+modeleClient.getAvatar().getPathImage()));
			this.repaint();
		}
	}
	
	private void changerImage() {
		imageAvatar.setIcon(new ImageIcon("src/img/avatar/"+listeImagesAvatar.get(posAvatar)));
		this.repaint();
	}

	@Override
	public void focusGained(FocusEvent arg0) {
		this.txtDateDeNaissance.setText("");
	}

	@Override
	public void focusLost(FocusEvent arg0) {

	}
}
