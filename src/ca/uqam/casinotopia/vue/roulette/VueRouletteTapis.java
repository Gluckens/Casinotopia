package ca.uqam.casinotopia.vue.roulette;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Shape;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import ca.uqam.casinotopia.Case;
import ca.uqam.casinotopia.ListeCases;
import ca.uqam.casinotopia.TypeCouleurCase;
import ca.uqam.casinotopia.TypeMise;
import ca.uqam.casinotopia.TypePariteCase;
import ca.uqam.casinotopia.controleur.ControleurClient;
import ca.uqam.casinotopia.controleur.client.ControleurRouletteClient;
import ca.uqam.casinotopia.drag_n_drop.MisesDroppableReceiver;
import ca.uqam.casinotopia.modele.client.ModeleTableJeuClient;
import ca.uqam.casinotopia.observateur.Observable;
import ca.uqam.casinotopia.vue.FrameApplication;
import ca.uqam.casinotopia.vue.GridBagHelper;
import ca.uqam.casinotopia.vue.Vue;

@SuppressWarnings("serial")
public class VueRouletteTapis extends Vue implements MisesDroppableReceiver {

	private ControleurRouletteClient controleur;
	private FrameApplication frame;

	private Map<Shape, Case> imageMaps;

	/**
	 * Create the panel.
	 */
	// TODO logiquement à la création de la Roulette, devrais-je envoyer
	// l'attribut case du modele tableJeu?
	public VueRouletteTapis(ControleurClient controleur, FrameApplication frame) {
		this.controleur = (ControleurRouletteClient) controleur;
		this.frame = frame;

		this.initImageMap();

		this.setPanelOptions();
		this.addComponents();
		this.createComponentsMap();
	}

	@Override
	protected void addComponents() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 302 };
		gridBagLayout.rowHeights = new int[] { 600 };
		gridBagLayout.columnWeights = new double[] { 0.0 };
		gridBagLayout.rowWeights = new double[] { 0.0 };
		this.setLayout(gridBagLayout);

		// setPreferredSize(new Dimension(302, 600));
		/*
		 * this.setPreferredSize(new Dimension(302, 600));
		 * this.setMinimumSize(new Dimension(302, 600)); this.setMaximumSize(new Dimension(302, 600));
		 */

		JLabel lblImgTapis = new JLabel(new ImageIcon(VueRouletteTapis.class.getResource("/img/roulette-table-grand.jpg")));
		lblImgTapis.setName("imgTapis");
		this.add(lblImgTapis, new GridBagHelper().setXY(0, 0).setFill(GridBagConstraints.NONE).end());
	}

	private void initImageMap() {
		this.imageMaps = new HashMap<Shape, Case>();
		
		this.imageMaps.put(new Rectangle(new Point(9, 236), new Dimension(29, 77)), ListeCases.INSTANCE.getCaseCouleur(TypeCouleurCase.ROUGE));
		this.imageMaps.put(new Rectangle(new Point(9, 314), new Dimension(29, 77)), ListeCases.INSTANCE.getCaseCouleur(TypeCouleurCase.NOIRE));
		
		this.imageMaps.put(new Rectangle(new Point(9, 157), new Dimension(29, 77)), ListeCases.INSTANCE.getCaseParite(TypePariteCase.PAIRE));
		this.imageMaps.put(new Rectangle(new Point(9, 392), new Dimension(29, 77)), ListeCases.INSTANCE.getCaseParite(TypePariteCase.IMPAIRE));
		
		int[] x = {67, 67, 178, 291, 291};
		int[] y = {77, 41, 7, 41, 77};
		this.imageMaps.put(new Polygon(x, y, 5), ListeCases.INSTANCE.getCaseNumero(0));
		
		
		this.imageMaps.put(new Rectangle(new Point(67, 78), new Dimension(74, 40)), ListeCases.INSTANCE.getCaseNumero(1));
		this.imageMaps.put(new Rectangle(new Point(142, 78), new Dimension(74, 40)), ListeCases.INSTANCE.getCaseNumero(2));
		this.imageMaps.put(new Rectangle(new Point(217, 78), new Dimension(74, 40)), ListeCases.INSTANCE.getCaseNumero(3));

		this.imageMaps.put(new Rectangle(new Point(67, 119), new Dimension(74, 38)), ListeCases.INSTANCE.getCaseNumero(4));
		this.imageMaps.put(new Rectangle(new Point(142, 119), new Dimension(74, 38)), ListeCases.INSTANCE.getCaseNumero(5));
		this.imageMaps.put(new Rectangle(new Point(217, 119), new Dimension(74, 38)), ListeCases.INSTANCE.getCaseNumero(6));

		this.imageMaps.put(new Rectangle(new Point(67, 158), new Dimension(74, 38)), ListeCases.INSTANCE.getCaseNumero(7));
		this.imageMaps.put(new Rectangle(new Point(142, 158), new Dimension(74, 38)), ListeCases.INSTANCE.getCaseNumero(8));
		this.imageMaps.put(new Rectangle(new Point(217, 158), new Dimension(74, 38)), ListeCases.INSTANCE.getCaseNumero(9));

		this.imageMaps.put(new Rectangle(new Point(67, 197), new Dimension(74, 38)), ListeCases.INSTANCE.getCaseNumero(10));
		this.imageMaps.put(new Rectangle(new Point(142, 197), new Dimension(74, 38)), ListeCases.INSTANCE.getCaseNumero(11));
		this.imageMaps.put(new Rectangle(new Point(217, 197), new Dimension(74, 38)), ListeCases.INSTANCE.getCaseNumero(12));

		this.imageMaps.put(new Rectangle(new Point(67, 236), new Dimension(74, 38)), ListeCases.INSTANCE.getCaseNumero(13));
		this.imageMaps.put(new Rectangle(new Point(142, 236), new Dimension(74, 38)), ListeCases.INSTANCE.getCaseNumero(14));
		this.imageMaps.put(new Rectangle(new Point(217, 236), new Dimension(74, 38)), ListeCases.INSTANCE.getCaseNumero(15));

		this.imageMaps.put(new Rectangle(new Point(67, 275), new Dimension(74, 38)), ListeCases.INSTANCE.getCaseNumero(16));
		this.imageMaps.put(new Rectangle(new Point(142, 275), new Dimension(74, 38)), ListeCases.INSTANCE.getCaseNumero(17));
		this.imageMaps.put(new Rectangle(new Point(217, 275), new Dimension(74, 38)), ListeCases.INSTANCE.getCaseNumero(18));

		this.imageMaps.put(new Rectangle(new Point(67, 314), new Dimension(74, 38)), ListeCases.INSTANCE.getCaseNumero(19));
		this.imageMaps.put(new Rectangle(new Point(142, 314), new Dimension(74, 38)), ListeCases.INSTANCE.getCaseNumero(20));
		this.imageMaps.put(new Rectangle(new Point(217, 314), new Dimension(74, 38)), ListeCases.INSTANCE.getCaseNumero(21));

		this.imageMaps.put(new Rectangle(new Point(67, 353), new Dimension(74, 38)), ListeCases.INSTANCE.getCaseNumero(22));
		this.imageMaps.put(new Rectangle(new Point(142, 353), new Dimension(74, 38)), ListeCases.INSTANCE.getCaseNumero(23));
		this.imageMaps.put(new Rectangle(new Point(217, 353), new Dimension(74, 38)), ListeCases.INSTANCE.getCaseNumero(24));

		this.imageMaps.put(new Rectangle(new Point(67, 392), new Dimension(74, 38)), ListeCases.INSTANCE.getCaseNumero(25));
		this.imageMaps.put(new Rectangle(new Point(142, 392), new Dimension(74, 38)), ListeCases.INSTANCE.getCaseNumero(26));
		this.imageMaps.put(new Rectangle(new Point(217, 392), new Dimension(74, 38)), ListeCases.INSTANCE.getCaseNumero(27));

		this.imageMaps.put(new Rectangle(new Point(67, 431), new Dimension(74, 38)), ListeCases.INSTANCE.getCaseNumero(28));
		this.imageMaps.put(new Rectangle(new Point(142, 431), new Dimension(74, 38)), ListeCases.INSTANCE.getCaseNumero(29));
		this.imageMaps.put(new Rectangle(new Point(217, 431), new Dimension(74, 38)), ListeCases.INSTANCE.getCaseNumero(30));

		this.imageMaps.put(new Rectangle(new Point(67, 470), new Dimension(74, 38)), ListeCases.INSTANCE.getCaseNumero(31));
		this.imageMaps.put(new Rectangle(new Point(142, 470), new Dimension(74, 38)), ListeCases.INSTANCE.getCaseNumero(32));
		this.imageMaps.put(new Rectangle(new Point(217, 470), new Dimension(74, 38)), ListeCases.INSTANCE.getCaseNumero(33));

		this.imageMaps.put(new Rectangle(new Point(67, 509), new Dimension(74, 38)), ListeCases.INSTANCE.getCaseNumero(34));
		this.imageMaps.put(new Rectangle(new Point(142, 509), new Dimension(74, 38)), ListeCases.INSTANCE.getCaseNumero(35));
		this.imageMaps.put(new Rectangle(new Point(217, 509), new Dimension(74, 38)), ListeCases.INSTANCE.getCaseNumero(36));
	}

	public void updateTableJeu(Map<Case, Map<Integer, Integer>> cases) {
		// TODO Mettre à jour le tapis/tableau
	}

	@Override
	public void update(Observable observable) {
		if (observable instanceof ModeleTableJeuClient) {
			this.updateTableJeu(((ModeleTableJeuClient) observable).getCases());
		}
	}

	@Override
	public void processDrop(Point p, TypeMise typeMise) {
		Case droppedCase = this.getDroppedCase(p);

		System.out.println("DROPPED CASE ==> " + droppedCase);
		
		this.controleur.cmdMiserRoulette(droppedCase, typeMise);
		
		//TODO appeler une méthode du controleur en envoyant la case misée (vus qu'on update a chaque mise?)
		//Le controleur pourra retrouver l'id du joueur via le modele, et envoyer sa au serveur.
		//Ca ne devrait plus etre utile d'envoyer un map si on met à jour à chaque mise de chaque client (est-ce que ce sera trop demandant pour le serveur?)
	}

	private Case getDroppedCase(Point p) {
		Case droppedCase = null;
		for (Map.Entry<Shape, Case> map : this.imageMaps.entrySet()) {
			if (map.getKey().contains(p)) {
				droppedCase = map.getValue();
				break;
			}
		}
		return droppedCase;
	}
}
