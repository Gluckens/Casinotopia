package ca.uqam.casinotopia.vue;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import ca.uqam.casinotopia.JeuClient;
import ca.uqam.casinotopia.TypeJeuArgent;
import ca.uqam.casinotopia.TypeJeuMultijoueurs;
import ca.uqam.casinotopia.controleur.ControleurClient;
import ca.uqam.casinotopia.controleur.client.ControleurSalleClient;
import ca.uqam.casinotopia.modif.TypeModifAvatar;
import ca.uqam.casinotopia.observateur.Observable;

@SuppressWarnings("serial")
public class VueSelectionOptionJeu extends Vue {
	
	private ControleurSalleClient controleur;
	
	//TODO Mettre dans un modele?
	private JeuClient jeu;

	public VueSelectionOptionJeu(ControleurClient controleur, JeuClient jeu) {
		this.controleur = (ControleurSalleClient) controleur;
		this.jeu = jeu;
		
		this.setPanelOptions();
		this.addComponents();
		this.createComponentsMap();
	}

	@Override
	protected void addComponents() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 130, 130 };
		gridBagLayout.rowHeights = new int[] { 40, 90, 20 };
		this.setLayout(gridBagLayout);
		
		GridBagLayout gblPnlMultijoueur = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 130 };
		gridBagLayout.rowHeights = new int[] { 30, 30, 30 };
		
		JLabel lblTitre = new JLabel("S�lection des options de jeu");
		this.add(lblTitre, new GridBagHelper().setXY(0, 0).setWH(2, 1).end());
		
		JPanel pnlMultijoueurs = new JPanel(gblPnlMultijoueur);
		pnlMultijoueurs.setName("pnlMultijoueurs");
		//pnlMultijoueurs.setBackground(new Color(0, 0, 0, 50));
		
		JRadioButton radInconnus = new JRadioButton("Inconnus");
		radInconnus.setName("radInconnus");
		radInconnus.setActionCommand(TypeJeuMultijoueurs.INCONNUS.toString());
		radInconnus.setSelected(true);
		pnlMultijoueurs.add(radInconnus, new GridBagHelper().setXY(0, 0).setAnchor(GridBagConstraints.WEST).end());
		
		JRadioButton radAmis = new JRadioButton("Amis");
		radAmis.setName("radAmis");
		radAmis.setActionCommand(TypeJeuMultijoueurs.AMIS.toString());
		pnlMultijoueurs.add(radAmis, new GridBagHelper().setXY(0, 1).setAnchor(GridBagConstraints.WEST).end());
		
		JRadioButton radSeul = new JRadioButton("Seul");
		radSeul.setName("radSeul");
		radSeul.setActionCommand(TypeJeuMultijoueurs.SEUL.toString());
		pnlMultijoueurs.add(radSeul, new GridBagHelper().setXY(0, 2).setAnchor(GridBagConstraints.WEST).end());
		
		final ButtonGroup grpMultijoueurs = new ButtonGroup();
		grpMultijoueurs.add(radInconnus);
		grpMultijoueurs.add(radAmis);
		grpMultijoueurs.add(radSeul);
		
		this.add(pnlMultijoueurs, new GridBagHelper().setXY(0, 1).end());
		
		
		GridBagLayout gblPnlArgent = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 130 };
		gridBagLayout.rowHeights = new int[] { 30, 30 };
		
		JPanel pnlArgent = new JPanel(gblPnlArgent);
		pnlArgent.setName("pnlArgent");
		//pnlArgent.setBackground(new Color(0, 0, 0, 50));
		
		JRadioButton radAvecArgent = new JRadioButton("Avec argent");
		radAvecArgent.setName("radAvecArgent");
		radAvecArgent.setActionCommand(TypeJeuArgent.ARGENT.toString());
		radAvecArgent.setSelected(true);
		pnlArgent.add(radAvecArgent, new GridBagHelper().setXY(0, 0).setAnchor(GridBagConstraints.WEST).end());
		
		JRadioButton radSansArgent = new JRadioButton("Amis");
		radAmis.setName("radSansArgent");
		radAmis.setActionCommand(TypeJeuArgent.SANS_ARGENT.toString());
		pnlArgent.add(radSansArgent, new GridBagHelper().setXY(0, 1).setAnchor(GridBagConstraints.WEST).end());
		
		final ButtonGroup grpArgent = new ButtonGroup();
		grpArgent.add(radAvecArgent);
		grpArgent.add(radSansArgent);
		
		this.add(pnlArgent, new GridBagHelper().setXY(1, 1).end());
		
		
		//TODO Dans le cas d'un jeu avec amis, faut ouvrir une 2e fenetre pour choisir les amis.
		JButton btnSuivant = new JButton("Suivant");
		btnSuivant.setName("btnSuivant");
		btnSuivant.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				TypeJeuMultijoueurs typeMultijoueur = TypeJeuMultijoueurs.getType(grpMultijoueurs.getSelection().getActionCommand());
				TypeJeuArgent typeArgent = TypeJeuArgent.getType(grpArgent.getSelection().getActionCommand());
				controleur.cmdJouerJeu(jeu, typeMultijoueur, typeArgent);
			}
		});
		this.add(btnSuivant, new GridBagHelper().setXY(0, 2).setWH(2, 1).end());
		
		//this.setBackground(new Color(128, 128, 128, 60));
		//this.setBackgroundChildrens(this, new Color(0, 0, 0, 30));
	}
	
	private void setBackgroundChildrens(JComponent comp, Color color) {
		comp.setBackground(color);
		for(Component child : comp.getComponents()) {
			child.setBackground(color);
			
			if(((JComponent) child).getComponentCount() > 0) {
				this.setBackgroundChildrens((JComponent) child, color);
			}
		}
	}

	@Override
	public void update(Observable observable) {
		
	}
}
