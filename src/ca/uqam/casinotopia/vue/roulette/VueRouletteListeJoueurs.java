package ca.uqam.casinotopia.vue.roulette;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Set;
import java.util.Vector;

import javax.swing.AbstractListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListModel;
import javax.swing.table.DefaultTableModel;

import ca.uqam.casinotopia.JoueurClient;
import ca.uqam.casinotopia.JoueurRouletteClient;
import ca.uqam.casinotopia.controleur.ControleurClient;
import ca.uqam.casinotopia.controleur.client.ControleurRouletteClient;
import ca.uqam.casinotopia.modele.client.ModelePartieRouletteClient;
import ca.uqam.casinotopia.observateur.Observable;
import ca.uqam.casinotopia.vue.GridBagHelper;
import ca.uqam.casinotopia.vue.Vue;

@SuppressWarnings("serial")
public class VueRouletteListeJoueurs extends Vue {
	
	private ControleurRouletteClient controleur;

	/**
	 * Create the panel.
	 */
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

		/*this.setPreferredSize(new Dimension(550, 80));
		this.setMaximumSize(new Dimension(550, 80));
		this.setMinimumSize(new Dimension(550, 80));*/
		
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
	 
	    //rowHeader.setFixedCellHeight(table.getRowHeight(0));
	    rowHeader.setFixedCellHeight(tblLstJoueurs.getRowHeight());
	    rowHeader.setCellRenderer(new RowHeaderRenderer(tblLstJoueurs));

	    rowHeader.setSize(new Dimension(150, 160));
	    
	    //dm.addColumn("", data);
	 
	    JScrollPane scroll = new JScrollPane(tblLstJoueurs); 
	    scroll.setName("jspLstJoueurs");
	    scroll.setRowHeaderView(rowHeader);
	    
	    this.add(scroll, new GridBagHelper().setXY(0, 1).setFill(GridBagConstraints.BOTH).end());
		
		
		
		
		
		
		
		/*String[] headers = { "J1", "J2", "J3", "J4" };
		
		ImageIcon[] data =
				{ 	new ImageIcon(VueRouletteListeJoueurs.class.getResource("/img/jeton_JAUNE.png")),
					new ImageIcon(VueRouletteListeJoueurs.class.getResource("/img/jeton_ROUGE.png")),
					new ImageIcon(VueRouletteListeJoueurs.class.getResource("/img/jeton_NOIR.png")),
					new ImageIcon(VueRouletteListeJoueurs.class.getResource("/img/jeton_BLEU.png"))
				};
		
		JoueursRouletteTableModel dm = new JoueursRouletteTableModel(); 
	    JTable table = new JTable(dm);
	    table.setRowHeight(40);
	    //table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
	    table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	 
	    JList rowHeader = new JList(new TableHeaderListModel(headers));    
	    rowHeader.setFixedCellWidth(150); 
	    rowHeader.setFixedCellHeight(40);
	 
	    //rowHeader.setFixedCellHeight(table.getRowHeight(0));
	    rowHeader.setFixedCellHeight(table.getRowHeight());
	    rowHeader.setCellRenderer(new RowHeaderRenderer(table));

	    rowHeader.setSize(new Dimension(150, 160));
	    
	    dm.addColumn("", data);
	 
	    JScrollPane scroll = new JScrollPane( table ); 
	    scroll.setRowHeaderView(rowHeader); 
	    //table.setTableHeader(null);
	    
	    this.add(scroll, new GridBagHelper().setXY(0, 1).setFill(GridBagConstraints.BOTH).end());
	    //getContentPane().add(scroll, BorderLayout.CENTER);*/
		
		
		
		
		
		
		/*
		JTable tblLstJoueurs = new JTable(new JoueursRouletteTableModel());
		tblLstJoueurs.setName("tblLstJoueurs");
		Vector<Vector<ImageIcon>> vectorImages = new Vector<Vector<ImageIcon>>();
		Vector<ImageIcon> lstImages = new Vector<ImageIcon>();
		lstImages.add(new ImageIcon(VueRouletteListeJoueurs.class.getResource("/img/jeton_JAUNE.png")));
		lstImages.add(new ImageIcon(VueRouletteListeJoueurs.class.getResource("/img/jeton_ROUGE.png")));
		lstImages.add(new ImageIcon(VueRouletteListeJoueurs.class.getResource("/img/jeton_NOIR.png")));
		lstImages.add(new ImageIcon(VueRouletteListeJoueurs.class.getResource("/img/jeton_BLEU.png")));
		vectorImages.add(lstImages);
		
		((JoueursRouletteTableModel) tblLstJoueurs.getModel()).addColumn("Couleur", vectorImages);
		
		
		JScrollPane jsp = new JScrollPane();
		//JScrollPane jsp = new JScrollPane(tblLstJoueurs);
		
		Vector<Vector<String>> vectorHeaders = new Vector<Vector<String>>();
		Vector<String> lstHeaders = new Vector<String>();
		lstHeaders.add("J1");
		lstHeaders.add("J2");
		lstHeaders.add("J3");
		lstHeaders.add("J4");
		vectorHeaders.add(lstHeaders);
		
		JTable tblHeaders = new JTable(new DefaultTableModel());
		((DefaultTableModel) tblHeaders.getModel()).addColumn("HEaders", vectorHeaders);
		jsp.setViewportView(tblLstJoueurs);
		jsp.setRowHeaderView(tblHeaders);
		
		this.add(jsp, new GridBagHelper().setXY(0, 1).setFill(GridBagConstraints.BOTH).end());
		
		//this.add(new JScrollPane(tblLstJoueurs), new GridBagHelper().setXY(0, 1).setFill(GridBagConstraints.BOTH).end());*/
	}
	
	private void updateListeJoueurs(Set<JoueurClient> lstJoueurs) {
		//JoueursRouletteTableModel modeleTblLstJoueurs = (JoueursRouletteTableModel) ((JTable) this.getComponentByName("tblLstJoueurs")).getModel();
		
		JScrollPane jspLstJoueurs = (JScrollPane) this.getComponentByName("jspLstJoueurs");
		JList rowHeader = (JList) jspLstJoueurs.getRowHeader().getView();
		JoueursRouletteTableModel modeleTblLstJoueurs = (JoueursRouletteTableModel) ((JTable)jspLstJoueurs.getViewport().getView()).getModel();
		String[] headers = new String[lstJoueurs.size()];
		ImageIcon[] images = new ImageIcon[lstJoueurs.size()];
		int i = 0;
		for(JoueurClient joueur : lstJoueurs) {
			JoueurRouletteClient joueurRoulette = (JoueurRouletteClient) joueur;
			//modeleTblLstJoueurs.addColumn(joueurRoulette.getClient().getNomUtilisateur());

			/*ImageIcon imgJeton = new ImageIcon(VueRouletteListeJoueurs.class.getResource("/img/" + joueurRoulette.getPathImgJeton()));
			modeleTblLstJoueurs.setValueAt(imgJeton, row, column)*/
			
			headers[i] = joueurRoulette.getClient().getNomUtilisateur();
			images[i] = new ImageIcon(VueRouletteListeJoueurs.class.getResource("/img/" + joueurRoulette.getPathImgJeton()));
			
			/*lstHeaders.add(joueurRoulette.getClient().getNomUtilisateur());
			lstImages.add(new ImageIcon(VueRouletteListeJoueurs.class.getResource("/img/" + joueurRoulette.getPathImgJeton())));*/
			
			i++;
		}
		
		((TableHeaderListModel) rowHeader.getModel()).setHeaders(headers);
		modeleTblLstJoueurs.addColumn("", images);
		
		
		
		
		
		
		/*Vector<String> lstHeaders = new Vector<String>();
		Vector<ImageIcon> lstImages = new Vector<ImageIcon>();
		
		for(JoueurClient joueur : lstJoueurs) {
			JoueurRouletteClient joueurRoulette = (JoueurRouletteClient) joueur;
			//modeleTblLstJoueurs.addColumn(joueurRoulette.getClient().getNomUtilisateur());

			//ImageIcon imgJeton = new ImageIcon(VueRouletteListeJoueurs.class.getResource("/img/" + joueurRoulette.getPathImgJeton()));
			//modeleTblLstJoueurs.setValueAt(imgJeton, row, column)
			
			lstHeaders.add(joueurRoulette.getClient().getNomUtilisateur());
			lstImages.add(new ImageIcon(VueRouletteListeJoueurs.class.getResource("/img/" + joueurRoulette.getPathImgJeton())));
		}
		
		JTable tblHeaders = new JTable(lstHeaders, null);
		
		modeleTblLstJoueurs.addRow(lstImages);*/
		
	}

	@Override
	public void update(Observable observable) {
		if(observable instanceof ModelePartieRouletteClient) {
			ModelePartieRouletteClient modele = (ModelePartieRouletteClient) observable;
			//System.out.println("UPDATE D'AVATAR " + modele.getId() + " POUR CLIENT " + this.idClient + " (" + modele.getTypeModif() + ")");
			switch (modele.getTypeModif()) {
				case MODIFICATION_JOUEURS :
					this.updateListeJoueurs(modele.getLstJoueurs());
					break;
			}
		}
	}
}
