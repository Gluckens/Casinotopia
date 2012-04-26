package ca.uqam.casinotopia.vue;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

import ca.uqam.casinotopia.controleur.client.ControleurPrincipalClient;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

@SuppressWarnings("serial")
public class FrameConnexion extends JFrame implements Runnable {

	private JPanel contentPane;

	private Image img = new ImageIcon(FrameConnexion.class.getResource("/img/splash.jpg")).getImage();

	private JTextField txtNomUtilisateur;
	private JPasswordField txtMotDePasse;

	private JButton btnConnexion;
	private JButton btnCrerUnCompte;

	private ControleurPrincipalClient controleur;
	private JLabel lblInformations;

	@Override
	public void run() {
		this.setVisible(true);
	}

	public FrameConnexion(ControleurPrincipalClient ctrl) {
		this();
		this.controleur = ctrl;
		this.btnConnexion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				FrameConnexion.this.controleur.connexionAuServeur();
			}
		});

		this.txtMotDePasse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FrameConnexion.this.controleur.connexionAuServeur();
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrameConnexion() {
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 406, 428);
		this.contentPane = new JPanel() {
			@Override
			public void paintComponent(Graphics g) {
				g.drawImage(FrameConnexion.this.img, 0, 0, null);
			}
		};
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		Dimension size = new Dimension(this.img.getWidth(null), this.img.getHeight(null));
		this.contentPane.setPreferredSize(size);
		this.contentPane.setMinimumSize(size);
		this.contentPane.setMaximumSize(size);
		this.contentPane.setSize(size);
		this.contentPane.setLayout(null);

		this.setContentPane(this.contentPane);
		this.contentPane.setLayout(null);

		this.txtNomUtilisateur = new JTextField();
		this.txtNomUtilisateur.setBounds(82, 209, 227, 20);
		this.contentPane.add(this.txtNomUtilisateur);
		this.txtNomUtilisateur.setColumns(10);

		JLabel lblNomUtilisateur = new JLabel("Nom d'utilisateur");
		lblNomUtilisateur.setLabelFor(this.txtNomUtilisateur);
		lblNomUtilisateur.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblNomUtilisateur.setBounds(82, 194, 227, 14);
		this.contentPane.add(lblNomUtilisateur);

		this.txtMotDePasse = new JPasswordField();
		this.txtMotDePasse.setEchoChar('\u263B');
		this.txtMotDePasse.setBounds(82, 249, 227, 20);
		this.contentPane.add(this.txtMotDePasse);
		this.txtMotDePasse.setColumns(10);

		JLabel lblMotDePasse = new JLabel("Mot de passe (même affaire!)");
		lblMotDePasse.setLabelFor(this.txtMotDePasse);
		lblMotDePasse.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblMotDePasse.setBounds(82, 233, 227, 14);
		this.contentPane.add(lblMotDePasse);

		this.btnConnexion = new JButton("Connexion");
		this.btnConnexion.setFont(new Font("Calibri", Font.PLAIN, 14));
		this.btnConnexion.setBounds(82, 280, 113, 23);
		this.contentPane.add(this.btnConnexion);

		this.btnCrerUnCompte = new JButton("Cr\u00E9er un compte");
		this.btnCrerUnCompte.setFont(new Font("Calibri", Font.PLAIN, 14));
		this.btnCrerUnCompte.setBounds(253, 366, 137, 23);
		this.contentPane.add(this.btnCrerUnCompte);

		this.lblInformations = new JLabel("informations");
		this.lblInformations.setBounds(82, 177, 227, 14);
		this.lblInformations.setVisible(false);
		this.contentPane.add(this.lblInformations);
	}

	public JLabel getLblInformations() {
		return this.lblInformations;
	}

	public void setLblInformations(JLabel lblInformations) {
		this.lblInformations = lblInformations;
	}

	public void setMessage(String message) {
		this.lblInformations.setForeground(new Color(0, 0, 0));
		this.lblInformations.setText(message);
		this.lblInformations.setVisible(true);
	}

	public void setMessageErreur(String message) {
		this.lblInformations.setForeground(new Color(255, 0, 0));
		this.lblInformations.setText(message);
		this.lblInformations.setVisible(true);
	}

	public JTextField getTxtNomUtilisateur() {
		return this.txtNomUtilisateur;
	}

	public void setTxtNomUtilisateur(JTextField txtNomUtilisateur) {
		this.txtNomUtilisateur = txtNomUtilisateur;
	}

	public JPasswordField getTxtMotDePasse() {
		return this.txtMotDePasse;
	}

	public void setTxtMotDePasse(JPasswordField txtMotDePasse) {
		this.txtMotDePasse = txtMotDePasse;
	}

}
