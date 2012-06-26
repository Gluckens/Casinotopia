package ca.uqam.casinotopia.vue.roulette;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import ca.uqam.casinotopia.controleur.ControleurClient;
import ca.uqam.casinotopia.controleur.client.ControleurRouletteClient;
import ca.uqam.casinotopia.modele.client.ModelePartieRouletteClient;
import ca.uqam.casinotopia.objet.JoueurClient;
import ca.uqam.casinotopia.objet.JoueurRouletteClient;
import ca.uqam.casinotopia.observateur.Observable;
import ca.uqam.casinotopia.vue.GridBagHelper;
import ca.uqam.casinotopia.vue.Vue;
import ca.uqam.casinotopia.vue.roulette.vertical_header.JoueursRouletteTableModel;
import ca.uqam.casinotopia.vue.roulette.vertical_header.RowHeaderRenderer;
import ca.uqam.casinotopia.vue.roulette.vertical_header.TableHeaderListModel;

/**
 * Sous-vue de la roulette
 * Contient la liste des joueurs de la partie
 */
@SuppressWarnings("serial")
public class VueRouletteListeJoueurs extends Vue {
	
	/**
	 * Controleur associé à la vue
	 */
	private ControleurRouletteClient controleur;

	public VueRouletteListeJoueurs(ControleurClient controleur) {
		this.controleur = (ControleurRouletteClient) controleur;
		
		this.setPanelOptions();
		this.addComponents();
		this.createComponentsMap();
		
		this.updateListeJoueurs(this.controleur.getListeJoueurs());
	}

	@Override
	protected void addComponents() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 322 };
		gridBagLayout.rowHeights = new int[] { 30, 270 };
		this.setLayout(gridBagLayout);
		
		JLabel lblTitre = new JLabel("Liste des joueurs");
		lblTitre.setFont(new Font("Tahoma", Font.ITALIC, 24));
		this.add(lblTitre, new GridBagHelper().setXY(0, 0).end());
		
		JTable tblLstJoueurs = new JTable(new JoueursRouletteTableModel());
		tblLstJoueurs.setName("tblLstJoueurs");
		tblLstJoueurs.setRowHeight(40);
		tblLstJoueurs.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		JList rowHeader = new JList(new TableHeaderListModel());
		rowHeader.setName("rowHeader");
	    rowHeader.setFixedCellWidth(150); 
	    rowHeader.setFixedCellHeight(40);
	 
	    rowHeader.setFixedCellHeight(tblLstJoueurs.getRowHeight());
	    rowHeader.setCellRenderer(new RowHeaderRenderer(tblLstJoueurs));

	    rowHeader.setSize(new Dimension(150, 160));
	 
	    JScrollPane scroll = new JScrollPane(tblLstJoueurs); 
	    scroll.setName("jspLstJoueurs");
	    scroll.setRowHeaderView(rowHeader);
	    
	    this.add(scroll, new GridBagHelper().setXY(0, 1).setFill(GridBagConstraints.BOTH).end());
	}
	
	/**
	 * Mise à jour de la liste des joueurs
	 * 
	 * @param lstJoueurs La nouvelle liste de joueurs
	 */
	private void updateListeJoueurs(Set<JoueurClient> lstJoueurs) {
		//TODO Retirer les lblMises des joueurs qui ont quittés
		
		JScrollPane jspLstJoueurs = (JScrollPane) this.getComponentByName("jspLstJoueurs");
		JList rowHeader = (JList) jspLstJoueurs.getRowHeader().getView();
		JTable tblLstJoueurs = (JTable)jspLstJoueurs.getViewport().getView();
		JoueursRouletteTableModel modeleTblLstJoueurs = new JoueursRouletteTableModel();
		tblLstJoueurs.setModel(modeleTblLstJoueurs);
		
		String[] headers = new String[lstJoueurs.size()];
		ImageIcon[] images = new ImageIcon[lstJoueurs.size()];
		int i = 0;
		for(JoueurClient joueur : lstJoueurs) {
			JoueurRouletteClient joueurRoulette = (JoueurRouletteClient) joueur;
			
			headers[i] = joueurRoulette.getClient().getNomUtilisateur();
			images[i] = new ImageIcon(VueRouletteListeJoueurs.class.getResource("/img/" + joueurRoulette.getPathImgJeton()));
			
			i++;
		}

		rowHeader.setModel(new TableHeaderListModel(headers));
		
		modeleTblLstJoueurs.addColumn("", images);
		
		tblLstJoueurs.revalidate();
		tblLstJoueurs.repaint();
	}

	@Override
	public void update(Observable observable) {
		if(observable instanceof ModelePartieRouletteClient) {
			ModelePartieRouletteClient modele = (ModelePartieRouletteClient) observable;
			switch (modele.getTypeModif()) {
				case MODIFICATION_JOUEURS :
					this.updateListeJoueurs(modele.getLstJoueurs());
					break;
			}
		}
	}
}