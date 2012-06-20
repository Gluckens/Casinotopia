package ca.uqam.casinotopia.vue.roulette;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import ca.uqam.casinotopia.controleur.ControleurClient;
import ca.uqam.casinotopia.controleur.client.ControleurRouletteClient;
import ca.uqam.casinotopia.drag_n_drop.GhostDropListener;
import ca.uqam.casinotopia.drag_n_drop.GhostGlassPane;
import ca.uqam.casinotopia.drag_n_drop.GhostMotionAdapter;
import ca.uqam.casinotopia.drag_n_drop.mises.MisesDroppableReceiver;
import ca.uqam.casinotopia.drag_n_drop.mises.MisesGhostComponentAdapter;
import ca.uqam.casinotopia.modele.client.ModeleTableJeuClient;
import ca.uqam.casinotopia.objet.Case;
import ca.uqam.casinotopia.objet.JoueurRouletteClient;
import ca.uqam.casinotopia.objet.ListeCases;
import ca.uqam.casinotopia.observateur.Observable;
import ca.uqam.casinotopia.type.TypeCouleurCase;
import ca.uqam.casinotopia.type.TypePariteCase;
import ca.uqam.casinotopia.vue.FrameApplication;
import ca.uqam.casinotopia.vue.GridBagHelper;
import ca.uqam.casinotopia.vue.Vue;

@SuppressWarnings("serial")
public class VueRouletteTapis extends Vue implements MisesDroppableReceiver {
	
	private ControleurRouletteClient controleur;
	
	private GhostGlassPane glassPane;
	
	private ImageMap<Case> imageMapsTapis;
	
	private GhostDropListener ghostDropListener;
	
	/**
	 * 
	 */
	/**
	 * @param controleur Le controleur client qui gère la roulette
	 * @param frame Le frame de l'application. Utilisé pour 
	 */
	public VueRouletteTapis(ControleurClient controleur, GhostGlassPane glassPane) {
		this.controleur = (ControleurRouletteClient) controleur;
		this.glassPane = glassPane;
		
		this.initImageMaps();

		this.setPanelOptions();
		this.addComponents();
		this.createComponentsMap();
		
		this.initDragAndDrop(this, this);
	}

	@Override
	protected void addComponents() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 302 };
		gridBagLayout.rowHeights = new int[] { 600 };
		this.setLayout(gridBagLayout);

		JLabel lblImgTapis = new JLabel(new ImageIcon(VueRouletteTapis.class.getResource("/img/roulette-table-grand.jpg")));
		lblImgTapis.setName("imgTapis");
		lblImgTapis.setLayout(null);
		this.add(lblImgTapis, new GridBagHelper().setXY(0, 0).end());
	}
	
	private void initImageMaps() {
		this.imageMapsTapis = new ImageMap<Case>(22, 23);
		
		this.imageMapsTapis.addMapping(ListeCases.INSTANCE.getCaseCouleur(TypeCouleurCase.ROUGE), new Rectangle(new Point(9, 236), new Dimension(29, 77)));
		this.imageMapsTapis.addMapping(ListeCases.INSTANCE.getCaseCouleur(TypeCouleurCase.NOIRE), new Rectangle(new Point(9, 314), new Dimension(29, 77)));
		
		this.imageMapsTapis.addMapping(ListeCases.INSTANCE.getCaseParite(TypePariteCase.PAIRE), new Rectangle(new Point(9, 157), new Dimension(29, 77)));
		this.imageMapsTapis.addMapping(ListeCases.INSTANCE.getCaseParite(TypePariteCase.IMPAIRE), new Rectangle(new Point(9, 392), new Dimension(29, 77)));
		
		
		List<Point> lstPoints = new ArrayList<Point>();
		
		lstPoints.add(new Point(67, 41));
		lstPoints.add(new Point(269, 41));
		lstPoints.add(new Point(269, 54));
		lstPoints.add(new Point(67, 54));
		
		int[] x = {67, 67, 178, 291, 291};
		int[] y = {77, 41, 7, 41, 77};
		this.imageMapsTapis.addMapping(ListeCases.INSTANCE.getCaseNumero(0), new ImagePosition(new Polygon(x, y, 5), lstPoints));
		
		this.imageMapsTapis.addMapping(ListeCases.INSTANCE.getCaseNumero(1), new Rectangle(new Point(67, 78), new Dimension(74, 40)));
		this.imageMapsTapis.addMapping(ListeCases.INSTANCE.getCaseNumero(2), new Rectangle(new Point(142, 78), new Dimension(74, 40)));
		this.imageMapsTapis.addMapping(ListeCases.INSTANCE.getCaseNumero(3), new Rectangle(new Point(217, 78), new Dimension(74, 40)));

		this.imageMapsTapis.addMapping(ListeCases.INSTANCE.getCaseNumero(4), new Rectangle(new Point(67, 119), new Dimension(74, 38)));
		this.imageMapsTapis.addMapping(ListeCases.INSTANCE.getCaseNumero(5), new Rectangle(new Point(142, 119), new Dimension(74, 38)));
		this.imageMapsTapis.addMapping(ListeCases.INSTANCE.getCaseNumero(6), new Rectangle(new Point(217, 119), new Dimension(74, 38)));

		this.imageMapsTapis.addMapping(ListeCases.INSTANCE.getCaseNumero(7), new Rectangle(new Point(67, 158), new Dimension(74, 38)));
		this.imageMapsTapis.addMapping(ListeCases.INSTANCE.getCaseNumero(8), new Rectangle(new Point(142, 158), new Dimension(74, 38)));
		this.imageMapsTapis.addMapping(ListeCases.INSTANCE.getCaseNumero(9), new Rectangle(new Point(217, 158), new Dimension(74, 38)));

		this.imageMapsTapis.addMapping(ListeCases.INSTANCE.getCaseNumero(10), new Rectangle(new Point(67, 197), new Dimension(74, 38)));
		this.imageMapsTapis.addMapping(ListeCases.INSTANCE.getCaseNumero(11), new Rectangle(new Point(142, 197), new Dimension(74, 38)));
		this.imageMapsTapis.addMapping(ListeCases.INSTANCE.getCaseNumero(12), new Rectangle(new Point(217, 197), new Dimension(74, 38)));

		this.imageMapsTapis.addMapping(ListeCases.INSTANCE.getCaseNumero(13), new Rectangle(new Point(67, 236), new Dimension(74, 38)));
		this.imageMapsTapis.addMapping(ListeCases.INSTANCE.getCaseNumero(14), new Rectangle(new Point(142, 236), new Dimension(74, 38)));
		this.imageMapsTapis.addMapping(ListeCases.INSTANCE.getCaseNumero(15), new Rectangle(new Point(217, 236), new Dimension(74, 38)));

		this.imageMapsTapis.addMapping(ListeCases.INSTANCE.getCaseNumero(16), new Rectangle(new Point(67, 275), new Dimension(74, 38)));
		this.imageMapsTapis.addMapping(ListeCases.INSTANCE.getCaseNumero(17), new Rectangle(new Point(142, 275), new Dimension(74, 38)));
		this.imageMapsTapis.addMapping(ListeCases.INSTANCE.getCaseNumero(18), new Rectangle(new Point(217, 275), new Dimension(74, 38)));

		this.imageMapsTapis.addMapping(ListeCases.INSTANCE.getCaseNumero(19), new Rectangle(new Point(67, 314), new Dimension(74, 38)));
		this.imageMapsTapis.addMapping(ListeCases.INSTANCE.getCaseNumero(20), new Rectangle(new Point(142, 314), new Dimension(74, 38)));
		this.imageMapsTapis.addMapping(ListeCases.INSTANCE.getCaseNumero(21), new Rectangle(new Point(217, 314), new Dimension(74, 38)));

		this.imageMapsTapis.addMapping(ListeCases.INSTANCE.getCaseNumero(22), new Rectangle(new Point(67, 353), new Dimension(74, 38)));
		this.imageMapsTapis.addMapping(ListeCases.INSTANCE.getCaseNumero(23), new Rectangle(new Point(142, 353), new Dimension(74, 38)));
		this.imageMapsTapis.addMapping(ListeCases.INSTANCE.getCaseNumero(24), new Rectangle(new Point(217, 353), new Dimension(74, 38)));

		this.imageMapsTapis.addMapping(ListeCases.INSTANCE.getCaseNumero(25), new Rectangle(new Point(67, 392), new Dimension(74, 38)));
		this.imageMapsTapis.addMapping(ListeCases.INSTANCE.getCaseNumero(26), new Rectangle(new Point(142, 392), new Dimension(74, 38)));
		this.imageMapsTapis.addMapping(ListeCases.INSTANCE.getCaseNumero(27), new Rectangle(new Point(217, 392), new Dimension(74, 38)));

		this.imageMapsTapis.addMapping(ListeCases.INSTANCE.getCaseNumero(28), new Rectangle(new Point(67, 431), new Dimension(74, 38)));
		this.imageMapsTapis.addMapping(ListeCases.INSTANCE.getCaseNumero(29), new Rectangle(new Point(142, 431), new Dimension(74, 38)));
		this.imageMapsTapis.addMapping(ListeCases.INSTANCE.getCaseNumero(30), new Rectangle(new Point(217, 431), new Dimension(74, 38)));

		this.imageMapsTapis.addMapping(ListeCases.INSTANCE.getCaseNumero(31), new Rectangle(new Point(67, 470), new Dimension(74, 38)));
		this.imageMapsTapis.addMapping(ListeCases.INSTANCE.getCaseNumero(32), new Rectangle(new Point(142, 470), new Dimension(74, 38)));
		this.imageMapsTapis.addMapping(ListeCases.INSTANCE.getCaseNumero(33), new Rectangle(new Point(217, 470), new Dimension(74, 38)));

		this.imageMapsTapis.addMapping(ListeCases.INSTANCE.getCaseNumero(34), new Rectangle(new Point(67, 509), new Dimension(74, 38)));
		this.imageMapsTapis.addMapping(ListeCases.INSTANCE.getCaseNumero(35), new Rectangle(new Point(142, 509), new Dimension(74, 38)));
		this.imageMapsTapis.addMapping(ListeCases.INSTANCE.getCaseNumero(36), new Rectangle(new Point(217, 509), new Dimension(74, 38)));
	}
	
	public void initDragAndDrop(JComponent target, MisesDroppableReceiver receiver) {
		this.ghostDropListener = new MisesGhostDropManager(target, receiver);
	}

	private void setMisesDragAndDrop(Component component, int montant) {
		MisesGhostComponentAdapter misesGhostComponentAdapter = new MisesGhostComponentAdapter(this.glassPane, montant, component.getName());
		component.addMouseListener(misesGhostComponentAdapter);
		misesGhostComponentAdapter.addGhostDropListener(this.ghostDropListener);
		component.addMouseMotionListener(new GhostMotionAdapter(this.glassPane));
	}

	public void updateTableJeu(Map<Case, Map<Integer, Integer>> cases) {
		JLabel tapis = (JLabel) getComponentByName("imgTapis");
		tapis.removeAll();
		for(Map.Entry<Case, Map<Integer, Integer>> mapCase : cases.entrySet()) {
			Case caseCourante = mapCase.getKey();
			int i = 0;
			for(Map.Entry<Integer, Integer> mapMises : mapCase.getValue().entrySet()) {
				int idJoueur = mapMises.getKey();
				JoueurRouletteClient joueur = (JoueurRouletteClient) this.controleur.getModele().getJoueur(idJoueur);
				int mise = mapMises.getValue();
				
				JLabel lblMise = new JLabel(new ImageIcon(VueRouletteTapis.class.getResource("/img/" + joueur.getPathImgJeton())));
				lblMise.setName("lblMise_" + caseCourante.toString() + "_" + idJoueur);
				lblMise.setToolTipText(joueur.getClient().getNomUtilisateur() + " : " + mise + " jetons");
				Point p = this.imageMapsTapis.getPositionAt(caseCourante, i);
				
				lblMise.setBounds(p.x, p.y, 22, 23);
				
				if(idJoueur == this.controleur.getModeleClient().getId()) {
					this.setMisesDragAndDrop(lblMise, mise);
				}
				
				tapis.add(lblMise);
				
				i++;
			}
		}
		
		revalidate();
		repaint();
	}

	@Override
	public void update(Observable observable) {
		if (observable instanceof ModeleTableJeuClient) {
			this.updateTableJeu(((ModeleTableJeuClient) observable).getCases());
		}
	}

	@Override
	public void processDrop(Point p, int montant, String componentName, Point posDepart) {
		Case droppedCase = this.getCaseAt(p);

		Map<Integer, Map<Case, Integer>> mises = this.controleur.creerMapMises();
		
		//S'il s'agit d'un déplacement d'une mise, on a pas besoin de revalider le solde.
		if(componentName.startsWith("lblMise_")) {
			Case caseDepart = this.getCaseAt(posDepart);
			//S'il s'agit d'un déplacement d'une mise vers une case différente
			if(!droppedCase.equals(caseDepart)) {
				this.controleur.ajouterMise(caseDepart, -montant, mises);
				this.controleur.ajouterMise(droppedCase, montant, mises);
			}
		}
		//Sinon, on valide le solde avant d'accepter la mise
		else {
			if(this.controleur.validerMise(montant)) {
				this.controleur.ajouterMise(droppedCase, montant, mises);
			}
			else {
				JOptionPane.showMessageDialog(null, "Vous n'avez pas assez d'argent dans votre compte.");
			}
		}
		
		if(!mises.isEmpty()) {
			this.controleur.cmdMiserRoulette(mises);
		}
		
		//TODO Ca ne devrait plus etre utile d'envoyer un map si on met à jour à chaque mise de chaque client (est-ce que ce sera trop demandant pour le serveur?)
	}

	private Case getCaseAt(Point p) {
		return this.imageMapsTapis.getElementAt(p);
	}
}