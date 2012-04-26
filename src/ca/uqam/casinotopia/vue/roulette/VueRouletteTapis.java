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
import ca.uqam.casinotopia.TypeCase;
import ca.uqam.casinotopia.TypeCouleurCase;
import ca.uqam.casinotopia.TypePariteCase;
import ca.uqam.casinotopia.controleur.ControleurClient;
import ca.uqam.casinotopia.controleur.client.ControleurRouletteClient;
import ca.uqam.casinotopia.drag_n_drop.DroppableReceiver;
import ca.uqam.casinotopia.modele.client.ModeleTableJeuClient;
import ca.uqam.casinotopia.observateur.Observable;
import ca.uqam.casinotopia.vue.FrameApplication;
import ca.uqam.casinotopia.vue.GridBagHelper;
import ca.uqam.casinotopia.vue.Vue;

@SuppressWarnings("serial")
public class VueRouletteTapis extends Vue implements DroppableReceiver {

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
		System.out.println("DESSIN DE TAPIS");
		GridBagLayout gridBagLayout = new GridBagLayout();
		/*
		 * gridBagLayout.columnWidths = new int[]{75, 75, 75};
		 * gridBagLayout.rowHeights = new int[]{15, 18, 21, 24, 27, 30, 33, 36,
		 * 39, 42, 45};
		 */
		/*
		 * gridBagLayout.columnWidths = new int[]{300}; gridBagLayout.rowHeights
		 * = new int[]{588};
		 */
		gridBagLayout.columnWidths = new int[] { 302 };
		gridBagLayout.rowHeights = new int[] { 600 };
		gridBagLayout.columnWeights = new double[] { 0.0 };
		gridBagLayout.rowWeights = new double[] { 0.0 };
		this.setLayout(gridBagLayout);

		// setPreferredSize(new Dimension(302, 600));
		/*
		 * this.setPreferredSize(new Dimension(302, 600));
		 * this.setMinimumSize(new Dimension(302, 600)); this.setMaximumSize(new
		 * Dimension(302, 600));
		 */

		JLabel lblImgTapis = new JLabel(new ImageIcon(VueRouletteTapis.class.getResource("/img/roulette-table-grand.jpg")));
		lblImgTapis.setName("imgTapis");
		this.add(lblImgTapis, new GridBagHelper().setXY(0, 0).setFill(GridBagConstraints.NONE).end());
	}

	private void initImageMap() {
		this.imageMaps = new HashMap<Shape, Case>();

		
		this.imageMaps.put(new Rectangle(new Point(9, 236), new Dimension(29, 77)), new Case(TypeCouleurCase.ROUGE, TypeCase.COULEUR, 4));
		this.imageMaps.put(new Rectangle(new Point(9, 314), new Dimension(29, 77)), new Case(TypeCouleurCase.NOIRE, TypeCase.COULEUR, 4));
		
		this.imageMaps.put(new Rectangle(new Point(9, 157), new Dimension(29, 77)), new Case(TypePariteCase.PAIRE, TypeCase.PARITE, 4));
		this.imageMaps.put(new Rectangle(new Point(9, 392), new Dimension(29, 77)), new Case(TypePariteCase.IMPAIRE, TypeCase.PARITE, 4));
		
		int[] x = {67, 67, 178, 291, 291};
		int[] y = {77, 41, 7, 41, 77};
		this.imageMaps.put(new Polygon(x, y, 5), new Case(0, TypeCase.CHIFFRE, 2));
		
		
		this.imageMaps.put(new Rectangle(new Point(67, 78), new Dimension(74, 40)), new Case(1, TypeCouleurCase.ROUGE, TypeCase.CHIFFRE, 18));
		this.imageMaps.put(new Rectangle(new Point(142, 78), new Dimension(74, 40)), new Case(2, TypeCouleurCase.NOIRE, TypeCase.CHIFFRE, 18));
		this.imageMaps.put(new Rectangle(new Point(217, 78), new Dimension(74, 40)), new Case(3, TypeCouleurCase.ROUGE, TypeCase.CHIFFRE, 18));

		this.imageMaps.put(new Rectangle(new Point(67, 119), new Dimension(74, 38)), new Case(4, TypeCouleurCase.NOIRE, TypeCase.CHIFFRE, 18));
		this.imageMaps.put(new Rectangle(new Point(142, 119), new Dimension(74, 38)), new Case(5, TypeCouleurCase.ROUGE, TypeCase.CHIFFRE, 18));
		this.imageMaps.put(new Rectangle(new Point(217, 119), new Dimension(74, 38)), new Case(6, TypeCouleurCase.NOIRE, TypeCase.CHIFFRE, 18));

		this.imageMaps.put(new Rectangle(new Point(67, 158), new Dimension(74, 38)), new Case(7, TypeCouleurCase.ROUGE, TypeCase.CHIFFRE, 18));
		this.imageMaps.put(new Rectangle(new Point(142, 158), new Dimension(74, 38)), new Case(8, TypeCouleurCase.NOIRE, TypeCase.CHIFFRE, 18));
		this.imageMaps.put(new Rectangle(new Point(217, 158), new Dimension(74, 38)), new Case(9, TypeCouleurCase.ROUGE, TypeCase.CHIFFRE, 18));

		this.imageMaps.put(new Rectangle(new Point(67, 197), new Dimension(74, 38)), new Case(10, TypeCouleurCase.NOIRE, TypeCase.CHIFFRE, 18));
		this.imageMaps.put(new Rectangle(new Point(142, 197), new Dimension(74, 38)), new Case(11, TypeCouleurCase.NOIRE, TypeCase.CHIFFRE, 18));
		this.imageMaps.put(new Rectangle(new Point(217, 197), new Dimension(74, 38)), new Case(12, TypeCouleurCase.ROUGE, TypeCase.CHIFFRE, 18));

		this.imageMaps.put(new Rectangle(new Point(67, 236), new Dimension(74, 38)), new Case(13, TypeCouleurCase.NOIRE, TypeCase.CHIFFRE, 18));
		this.imageMaps.put(new Rectangle(new Point(142, 236), new Dimension(74, 38)), new Case(14, TypeCouleurCase.ROUGE, TypeCase.CHIFFRE, 18));
		this.imageMaps.put(new Rectangle(new Point(217, 236), new Dimension(74, 38)), new Case(15, TypeCouleurCase.NOIRE, TypeCase.CHIFFRE, 18));

		this.imageMaps.put(new Rectangle(new Point(67, 275), new Dimension(74, 38)), new Case(16, TypeCouleurCase.ROUGE, TypeCase.CHIFFRE, 18));
		this.imageMaps.put(new Rectangle(new Point(142, 275), new Dimension(74, 38)), new Case(17, TypeCouleurCase.NOIRE, TypeCase.CHIFFRE, 18));
		this.imageMaps.put(new Rectangle(new Point(217, 275), new Dimension(74, 38)), new Case(18, TypeCouleurCase.ROUGE, TypeCase.CHIFFRE, 18));

		this.imageMaps.put(new Rectangle(new Point(67, 314), new Dimension(74, 38)), new Case(19, TypeCouleurCase.ROUGE, TypeCase.CHIFFRE, 18));
		this.imageMaps.put(new Rectangle(new Point(142, 314), new Dimension(74, 38)), new Case(20, TypeCouleurCase.NOIRE, TypeCase.CHIFFRE, 18));
		this.imageMaps.put(new Rectangle(new Point(217, 314), new Dimension(74, 38)), new Case(21, TypeCouleurCase.ROUGE, TypeCase.CHIFFRE, 18));

		this.imageMaps.put(new Rectangle(new Point(67, 353), new Dimension(74, 38)), new Case(22, TypeCouleurCase.NOIRE, TypeCase.CHIFFRE, 18));
		this.imageMaps.put(new Rectangle(new Point(142, 353), new Dimension(74, 38)), new Case(23, TypeCouleurCase.ROUGE, TypeCase.CHIFFRE, 18));
		this.imageMaps.put(new Rectangle(new Point(217, 353), new Dimension(74, 38)), new Case(24, TypeCouleurCase.NOIRE, TypeCase.CHIFFRE, 18));

		this.imageMaps.put(new Rectangle(new Point(67, 392), new Dimension(74, 38)), new Case(25, TypeCouleurCase.ROUGE, TypeCase.CHIFFRE, 18));
		this.imageMaps.put(new Rectangle(new Point(142, 392), new Dimension(74, 38)), new Case(26, TypeCouleurCase.NOIRE, TypeCase.CHIFFRE, 18));
		this.imageMaps.put(new Rectangle(new Point(217, 392), new Dimension(74, 38)), new Case(27, TypeCouleurCase.ROUGE, TypeCase.CHIFFRE, 18));

		this.imageMaps.put(new Rectangle(new Point(67, 431), new Dimension(74, 38)), new Case(28, TypeCouleurCase.NOIRE, TypeCase.CHIFFRE, 18));
		this.imageMaps.put(new Rectangle(new Point(142, 431), new Dimension(74, 38)), new Case(29, TypeCouleurCase.NOIRE, TypeCase.CHIFFRE, 18));
		this.imageMaps.put(new Rectangle(new Point(217, 431), new Dimension(74, 38)), new Case(30, TypeCouleurCase.ROUGE, TypeCase.CHIFFRE, 18));

		this.imageMaps.put(new Rectangle(new Point(67, 470), new Dimension(74, 38)), new Case(31, TypeCouleurCase.NOIRE, TypeCase.CHIFFRE, 18));
		this.imageMaps.put(new Rectangle(new Point(142, 470), new Dimension(74, 38)), new Case(32, TypeCouleurCase.ROUGE, TypeCase.CHIFFRE, 18));
		this.imageMaps.put(new Rectangle(new Point(217, 470), new Dimension(74, 38)), new Case(33, TypeCouleurCase.NOIRE, TypeCase.CHIFFRE, 18));

		this.imageMaps.put(new Rectangle(new Point(67, 509), new Dimension(74, 38)), new Case(34, TypeCouleurCase.ROUGE, TypeCase.CHIFFRE, 18));
		this.imageMaps.put(new Rectangle(new Point(142, 509), new Dimension(74, 38)), new Case(35, TypeCouleurCase.NOIRE, TypeCase.CHIFFRE, 18));
		this.imageMaps.put(new Rectangle(new Point(217, 509), new Dimension(74, 38)), new Case(36, TypeCouleurCase.ROUGE, TypeCase.CHIFFRE, 18));
	}

	private void ajouterImageMap() {

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
	public void processDrop(Point p) {
		System.out.println("JE PROCESS LE DROP ==> " + p);

		Case droppedCase = this.getDroppedCase(p);

		System.out.println(droppedCase);
		
		//TODO appeler une méthode du controleur en envoyant la case misée (vus qu'on update a chaque mise?)
		//Le controleur pourra retrouver l'id du joueur via le modele, et envoyer sa au serveur.
		//Ca ne devrait plus etre utile d'envoyer un map si on met à jour à chaque mise de chaque client (est-ce que ce sera trop demandant pour le serveur?)
		//this.controleur.

		// TODO Faire un map pour regarder dans quelle case tombe le point
		// (ajouter sa au map déjà existant des case? (ou encore à l'objet case
		// direct? non pcq le serveur utilise la meme classe et il a pas besoin
		// de savoir sa))
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
