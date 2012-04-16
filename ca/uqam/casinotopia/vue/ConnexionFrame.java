package ca.uqam.casinotopia.vue;

import java.awt.Dimension;
import java.awt.EventQueue;
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

import ca.uqam.casinotopia.Utilisateur;
/*import ca.uqam.casinotopia.command.Command;
import ca.uqam.casinotopia.command.EnvoyerInformation;*/
import ca.uqam.casinotopia.controleur.Controleur;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class ConnexionFrame extends JFrame {

	private JPanel contentPane;
	
	private Image img = new ImageIcon("src/img/splash.jpg").getImage();
	
	private JTextField txtNomUtilisateur;
	private JPasswordField txtMotDePasse;

	private JButton btnConnexion;
	private JButton btnCrerUnCompte;


	private Controleur controleur;
	private JLabel lblInformations;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConnexionFrame frame = new ConnexionFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ConnexionFrame(Controleur ctrl){
		this();
		this.controleur = ctrl;
		btnConnexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				connexion(controleur);
			}
		});

		txtMotDePasse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connexion(controleur);
			}
		});
	}
	
	/**
	 * Create the frame.
	 */
	public ConnexionFrame() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 406, 428);
		contentPane = new JPanel(){
			public void paintComponent(Graphics g) {
			    g.drawImage(img, 0, 0, null);
		    }			
		};
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
		contentPane.setPreferredSize(size);
		contentPane.setMinimumSize(size);
		contentPane.setMaximumSize(size);
		contentPane.setSize(size);
		contentPane.setLayout(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtNomUtilisateur = new JTextField();
		txtNomUtilisateur.setBounds(82, 209, 227, 20);
		contentPane.add(txtNomUtilisateur);
		txtNomUtilisateur.setColumns(10);
		
		JLabel lblNomUtilisateur = new JLabel("Nom d'utilisateur");
		lblNomUtilisateur.setLabelFor(txtNomUtilisateur);
		lblNomUtilisateur.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblNomUtilisateur.setBounds(82, 194, 227, 14);
		contentPane.add(lblNomUtilisateur);
		
		txtMotDePasse = new JPasswordField();
		txtMotDePasse.setEchoChar('\u263B');
		txtMotDePasse.setBounds(82, 249, 227, 20);
		contentPane.add(txtMotDePasse);
		txtMotDePasse.setColumns(10);
		
		JLabel lblMotDePasse = new JLabel("Mot de passe (même affaire!)");
		lblMotDePasse.setLabelFor(txtMotDePasse);
		lblMotDePasse.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblMotDePasse.setBounds(82, 233, 227, 14);
		contentPane.add(lblMotDePasse);
		
		btnConnexion = new JButton("Connexion");
		btnConnexion.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnConnexion.setBounds(82, 280, 113, 23);
		contentPane.add(btnConnexion);
		
		btnCrerUnCompte = new JButton("Cr\u00E9er un compte");
		btnCrerUnCompte.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnCrerUnCompte.setBounds(253, 366, 137, 23);
		contentPane.add(btnCrerUnCompte);
		
		lblInformations = new JLabel("informations");
		lblInformations.setForeground(new Color(255, 0, 0));
		lblInformations.setBounds(82, 177, 227, 14);
		lblInformations.setVisible(false);
		contentPane.add(lblInformations);
	}
	
	public void connexion(Controleur controleur){
		
		
		Utilisateur utilisateur = new Utilisateur(this.txtNomUtilisateur.getText(),this.txtMotDePasse.getPassword());
		/*Command cmd = new EnvoyerInformation(utilisateur);
		controleur.getConnexion().envoyerCommand(cmd);*/
	}

	public JLabel getLblInformations() {
		return lblInformations;
	}

	public void setLblInformations(JLabel lblInformations) {
		this.lblInformations = lblInformations;
	}
}
