
package ca.uqam.casinotopia.vue.roulette;

import java.awt.Dimension;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;

import ca.uqam.casinotopia.Case;
import ca.uqam.casinotopia.TypeCase;
import ca.uqam.casinotopia.controleur.ControleurClient;
import ca.uqam.casinotopia.controleur.client.ControleurRouletteClient;
import ca.uqam.casinotopia.modele.client.ModelePartieRouletteClient;
import ca.uqam.casinotopia.observateur.Observable;
import ca.uqam.casinotopia.vue.FrameApplication;
import ca.uqam.casinotopia.vue.GridBagHelper;
import ca.uqam.casinotopia.vue.Vue;
import ca.uqam.casinotopia.vue.VueRouletteRoue;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;

import ca.uqam.casinotopia.drag_n_drop.GhostComponentAdapter;
import ca.uqam.casinotopia.drag_n_drop.GhostGlassPane;
import ca.uqam.casinotopia.drag_n_drop.GhostMotionAdapter;

@SuppressWarnings("serial")
public class VueRoulette extends Vue {
	
	private ControleurRouletteClient controleur;
	private FrameApplication frame;

	/**
	 * Create the panel.
	 */
	public VueRoulette(ControleurClient controleur) {
		this.controleur = (ControleurRouletteClient) controleur;
		this.frame = this.controleur.getFrame();
		
		this.setPanelOptions();
		this.addComponents();
		this.createComponentsMap();
		
		this.initDragAndDrop();
	}
	
	@Override
	protected void addComponents() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		/*gridBagLayout.columnWidths = new int[]{500, 300, 224};
		gridBagLayout.rowHeights = new int[]{294, 294, 192};*/
		gridBagLayout.columnWidths = new int[]{498, 302, 224};
		gridBagLayout.rowHeights = new int[]{300, 300, 140};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		setPreferredSize(new Dimension(1024, 740));	
				
		VueRouletteTapis tapis = new VueRouletteTapis(this.controleur, this.frame);
		tapis.setName("tapis");
		this.add(tapis, new GridBagHelper().setXY(1, 0).setWH(1, 2).end());
		
		/*VueRouletteTableau tableau = new VueRouletteTableau(this.controleur, this.frame);
		tableau.setName("tableau");
		this.add(tableau, new GridBagHelper().setXY(2, 0).end());*/
		
		VueRouletteActions actions = new VueRouletteActions(this.controleur, this.frame);
		actions.setName("actions");
		this.add(actions, new GridBagHelper().setXY(0, 2).setWH(2, 1).end());
		
		VueRouletteRoue roue = new VueRouletteRoue(this.controleur, this.frame);
		roue.setName("roue");
		this.add(roue, new GridBagHelper().setXY(0, 0).setWH(1, 2).end());
		

	}
	
	private void initDragAndDrop() {
		
	}
	
	/*public void updateTableJeu(Map<Case, Map<Integer, Integer>> cases) {
		JTextField txt = (JTextField) this.getComponentByName("txtDefault");
		Calendar cal = Calendar.getInstance();
	    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	    String time = sdf.format(cal.getTime());
		txt.setText(String.valueOf(cases.size()) + " " + time);
	}*/

	@Override
	public void update(Observable observable) {
		/*if(observable instanceof ModelePartieRouletteClient) {
			this.updateTableJeu(((ModelePartieRouletteClient)observable).getTableJeu().getCases());
		}*/
	}
}

