package ca.uqam.casinotopia.vue.machine;

import java.awt.GridBagLayout;

import javax.imageio.ImageIO;
import javax.swing.JLabel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;

import ca.uqam.casinotopia.controleur.client.ControleurChatClient;
import ca.uqam.casinotopia.controleur.client.ControleurMachineClient;
import ca.uqam.casinotopia.observateur.Observable;
import ca.uqam.casinotopia.vue.FrameConnexion;
import ca.uqam.casinotopia.vue.Vue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.io.IOException;

@SuppressWarnings("serial")
public class VueMachine extends Vue {

	private static int ICONWIDTH = 126;
	private static int ICONHEIGHT = 101;
	
	private ControleurMachineClient controleur;
	
	private JLabel lblArgent;
	private JButton btnMiser;
	private JTextField txtMontant;
	

	private BufferedImage img;
	private BufferedImage[] icones = new BufferedImage[9];
	
	private JLabel resultat1;
	private JLabel resultat2;
	private JLabel resultat3;
	
	public int val1 = 2, val2 = 5, val3 = 8;

	/**
	 * Create the panel.
	 */
	public VueMachine(ControleurMachineClient controleurMachineClient) {

		this.controleur = controleurMachineClient;
		try {
			img = ImageIO.read(FrameConnexion.class.getResource("/img/iconMachine.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//icones[0] = createImage(new FilteredImageSource(img.getSource(), new CropImageFilter(0, 0, ICONWIDTH, ICONHEIGHT)) );
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				icones[i*3+j] = img.getSubimage(ICONWIDTH*i, ICONHEIGHT*j, ICONWIDTH, ICONHEIGHT);
			}
		}
		addComponents();
	}

	@Override
	public void update(Observable observable) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void addComponents() {

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, ICONWIDTH, ICONWIDTH, ICONWIDTH, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		lblArgent = new JLabel("argent");
		GridBagConstraints gbc_lblArgent = new GridBagConstraints();
		gbc_lblArgent.insets = new Insets(0, 0, 5, 5);
		gbc_lblArgent.gridx = 1;
		gbc_lblArgent.gridy = 1;
		add(lblArgent, gbc_lblArgent);
		
		txtMontant = new JTextField();
		txtMontant.setText("2");
		GridBagConstraints gbc_txtMontant = new GridBagConstraints();
		gbc_txtMontant.insets = new Insets(0, 0, 5, 0);
		gbc_txtMontant.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtMontant.gridx = 4;
		gbc_txtMontant.gridy = 1;
		add(txtMontant, gbc_txtMontant);
		txtMontant.setColumns(10);
		
		resultat1 = new JLabel("");
		resultat1.setFont(new Font("Tahoma", Font.PLAIN, 150));
		resultat1.setMinimumSize(new Dimension(ICONWIDTH, ICONHEIGHT));
		GridBagConstraints gbc_resultat1 = new GridBagConstraints();
		gbc_resultat1.insets = new Insets(0, 0, 0, 5);
		gbc_resultat1.gridx = 1;
		gbc_resultat1.gridy = 2;
		add(resultat1, gbc_resultat1);
		
		resultat2 = new JLabel("");
		resultat2.setFont(new Font("Tahoma", Font.PLAIN, 150));
		resultat2.setMinimumSize(new Dimension(ICONWIDTH, ICONHEIGHT));
		GridBagConstraints gbc_resultat2 = new GridBagConstraints();
		gbc_resultat2.insets = new Insets(0, 0, 0, 5);
		gbc_resultat2.gridx = 2;
		gbc_resultat2.gridy = 2;
		add(resultat2, gbc_resultat2);
		
		resultat3 = new JLabel("");
		resultat3.setFont(new Font("Tahoma", Font.PLAIN, 150));
		resultat3.setMinimumSize(new Dimension(ICONWIDTH, ICONHEIGHT));
		GridBagConstraints gbc_resultat3 = new GridBagConstraints();
		gbc_resultat3.insets = new Insets(0, 0, 0, 5);
		gbc_resultat3.gridx = 3;
		gbc_resultat3.gridy = 2;
		add(resultat3, gbc_resultat3);
		
		btnMiser = new JButton("Miser");
		btnMiser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controleur.cmdMiser();
			}
		});
		GridBagConstraints gbc_btnMiser = new GridBagConstraints();
		gbc_btnMiser.gridx = 4;
		gbc_btnMiser.gridy = 2;
		add(btnMiser, gbc_btnMiser);
		
	}
	
	
	@Override
	public void paintComponent(Graphics g) {
		//super.paintComponent(g);
		g.drawImage(icones[val1], resultat1.getX(), resultat1.getY(), null);
		g.drawImage(icones[val2], resultat2.getX(), resultat2.getY(), null);
		g.drawImage(icones[val3], resultat3.getX(), resultat3.getY(), null);
	}
	
	
	
	public JTextField getTxtMontant() {
		return txtMontant;
	}
	
	public void setVal(int val1, int val2, int val3) {
		this.val1 = val1;
		this.val2 = val2;
		this.val3 = val3;
		paintComponent(getGraphics());
	}
	
	public JLabel getResultat1() {
		return resultat1;
	}

	public JLabel getResultat2() {
		return resultat2;
	}
	
	public JLabel getResultat3() {
		return resultat3;
	}
	
}
