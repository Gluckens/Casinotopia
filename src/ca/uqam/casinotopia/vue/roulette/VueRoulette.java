package ca.uqam.casinotopia.vue.roulette;

import java.awt.Dimension;
import java.awt.GridBagConstraints;

import ca.uqam.casinotopia.controleur.ControleurClient;
import ca.uqam.casinotopia.controleur.client.ControleurChatClient;
import ca.uqam.casinotopia.controleur.client.ControleurPrincipalClient;
import ca.uqam.casinotopia.controleur.client.ControleurRouletteClient;
import ca.uqam.casinotopia.drag_n_drop.GhostGlassPane;
import ca.uqam.casinotopia.modele.client.ModeleChatClient;
import ca.uqam.casinotopia.modele.client.ModelePartieRouletteClient;
import ca.uqam.casinotopia.observateur.Observable;
import ca.uqam.casinotopia.vue.FrameApplication;
import ca.uqam.casinotopia.vue.GridBagHelper;
import ca.uqam.casinotopia.vue.Vue;
import ca.uqam.casinotopia.vue.chat.VueChat;

import java.awt.GridBagLayout;

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
	}

	@Override
	protected void addComponents() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 400, 302, 322 };
		gridBagLayout.rowHeights = new int[] { 300, 300, 80 };
		this.setLayout(gridBagLayout);

		this.setPreferredSize(new Dimension(1024, 680));
		this.setMaximumSize(new Dimension(1024, 680));
		
		VueRouletteRoue roue = new VueRouletteRoue(this.controleur);
		roue.setName("roue");
		this.add(roue, new GridBagHelper().setXY(0, 0).setWH(1, 2).end());
		
		VueRouletteTapis tapis = new VueRouletteTapis(this.controleur, (GhostGlassPane) this.frame.getGlassPane());
		tapis.setName("tapis");
		this.add(tapis, new GridBagHelper().setXY(1, 0).setWH(1, 2).end());
		
		VueRouletteListeJoueurs lstJoueurs = new VueRouletteListeJoueurs(this.controleur);
		lstJoueurs.setName("vueListeJoueurs");
		this.add(lstJoueurs, new GridBagHelper().setXY(2, 0).end());

		VueRouletteActions actions = new VueRouletteActions(this.controleur, this.frame, tapis, tapis);
		actions.setName("actions");
		this.add(actions, new GridBagHelper().setXY(0, 2).setWH(2, 1).setFill(GridBagConstraints.HORIZONTAL).setAnchor(GridBagConstraints.SOUTH).end());

		
		ControleurChatClient ctrlChatClient = new ControleurChatClient(this.controleur.getConnexion(), new ModeleChatClient(), this.controleur.getModeleClient(), this.controleur.getModeleNav());
		this.controleur.getModeleNav().ajouterControleur("ControleurChatClient", ctrlChatClient);
		
		
		VueChat chat = ctrlChatClient.getVue();
		chat.cacherSalle();
		ctrlChatClient.cmdSeConnecterAuChat("RoulettePrincipal");//TODO ajouter un chiffre
		chat.setName("chat");
		this.add(chat, new GridBagHelper().setXY(2, 1).setWH(1, 1).setFill(GridBagConstraints.BOTH).setAnchor(GridBagConstraints.SOUTH).end());
	}

	@Override
	public void update(Observable observable) {
		if(observable instanceof ModelePartieRouletteClient) {
			VueRouletteRoue vR = (VueRouletteRoue)this.getComponentByName("roue");
			vR.update(controleur.getModele());
		}
	}
}