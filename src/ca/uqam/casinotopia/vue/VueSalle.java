package ca.uqam.casinotopia.vue;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ca.uqam.casinotopia.AvatarClient;
import ca.uqam.casinotopia.TypeDeplacement;
import ca.uqam.casinotopia.controleur.ControleurClient;
import ca.uqam.casinotopia.controleur.client.ControleurSalleClient;
import ca.uqam.casinotopia.modele.client.ModeleClientClient;
import ca.uqam.casinotopia.modele.client.ModeleSalleClient;
import ca.uqam.casinotopia.observateur.Observable;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("serial")
public class VueSalle extends Vue {
	
	private ControleurSalleClient controleur;
	
	//private Avatar avatarClient;
	
	private MondeVirtuel mondeVirtuel;
	
	//Sert a gérer les multiple keyPress
	private Map<TypeDeplacement, Boolean> keysPressed;
	
	private Map<Integer, ModeleClientClient> lstClients;
	//private Set<ModeleClientClient> lstClients;
	
	public int idClient;
	
	

	public VueSalle(ControleurClient controleur, Map<Integer, ModeleClientClient> lstClients, int idClient) {
		this.controleur = (ControleurSalleClient) controleur;
		
		//TODO gérer l'ajout des avatars en dehors du constructeur (events update)
		
		this.keysPressed = new HashMap<TypeDeplacement, Boolean>();
		this.keysPressed.put(TypeDeplacement.HAUT, false);
		this.keysPressed.put(TypeDeplacement.BAS, false);
		this.keysPressed.put(TypeDeplacement.GAUCHE, false);
		this.keysPressed.put(TypeDeplacement.DROITE, false);
		
		this.lstClients = lstClients;
		
		this.idClient = idClient;

		this.setPanelOptions();
		this.addComponents();
		this.createComponentsMap();
	}
	
	public void demarrerMondeVirtuel() {
		this.mondeVirtuel = new MondeVirtuel(this);
		
		Thread threadMondeVirtuel = new Thread(mondeVirtuel);
		threadMondeVirtuel.start();
	}

	@Override
	protected void addComponents() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 1000 };
		gridBagLayout.rowHeights = new int[] { 700 };
		gridBagLayout.columnWeights = new double[] { 0.0 };
		gridBagLayout.rowWeights = new double[] { 0.0 };
		this.setLayout(gridBagLayout);
		
		JPanel pnlAvatars = new JPanel();
		pnlAvatars.setName("pnlAvatars");
		pnlAvatars.setLayout(null);
		pnlAvatars.setFocusable(true);
		pnlAvatars.requestFocus();
		//pnlAvatars.addKeyListener(this.mondeVirtuel);
		pnlAvatars.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ke) {
				switch (ke.getKeyCode()) {
		            case KeyEvent.VK_UP :
		            	keysPressed.put(TypeDeplacement.HAUT, true);
		            	break;
		            case KeyEvent.VK_DOWN :
		            	keysPressed.put(TypeDeplacement.BAS, true);
		            	break;
		            case KeyEvent.VK_LEFT :
		            	keysPressed.put(TypeDeplacement.GAUCHE, true);
		            	break;
		            case KeyEvent.VK_RIGHT :
		            	keysPressed.put(TypeDeplacement.DROITE, true);
		            	break;
		        }
			}
			
			@Override
			public void keyReleased(KeyEvent ke) {
				switch (ke.getKeyCode()) {
		            case KeyEvent.VK_UP :
		            	keysPressed.put(TypeDeplacement.HAUT, false);
		            	break;
		            case KeyEvent.VK_DOWN :
		            	keysPressed.put(TypeDeplacement.BAS, false);
		            	break;
		            case KeyEvent.VK_LEFT :
		            	keysPressed.put(TypeDeplacement.GAUCHE, false);
		            	break;
		            case KeyEvent.VK_RIGHT :
		            	keysPressed.put(TypeDeplacement.DROITE, false);
		            	break;
		        }
			}
		});
		
		//for(ModeleClientClient client : this.lstClients) {
		for(ModeleClientClient client : this.lstClients.values()) {
			AvatarClient avatar = client.getAvatar();
			JLabel imgAvatar = new JLabel(new ImageIcon(VueSalle.class.getResource(avatar.getPathImage())));
			imgAvatar.setName("avatarClient" + client.getId());
			imgAvatar.setBounds(avatar.getX(), avatar.getY(), avatar.getLargeur(), avatar.getHauteur());
			pnlAvatars.add(imgAvatar);
		}
		
		this.add(pnlAvatars, new GridBagHelper().setXY(0, 0).setFill(GridBagConstraints.BOTH).end());
	}
	
	private void updateAvatar(AvatarClient avatar) {
		//System.out.println("CLIENT " + this.idClient + " : UPDATE D'AVATAR " + avatar.getId() + " : (" + avatar.getX() + ", " + avatar.getY() + ", " + avatar.getLargeur() + ", " + avatar.getHauteur() + ")");
		
		JLabel imgAvatar = (JLabel) this.getComponentByName("avatarClient" + avatar.getId());
		imgAvatar.setBounds(avatar.getX(), avatar.getY(), avatar.getLargeur(), avatar.getHauteur());
		
		revalidate();
		repaint();
	}
	
	private void ajouterClient(ModeleClientClient nouveauClient) {
		JPanel pnlAvatars = (JPanel) this.getComponentByName("pnlAvatars");
		AvatarClient avatar = nouveauClient.getAvatar();
		JLabel imgAvatar = new JLabel(new ImageIcon(VueSalle.class.getResource(avatar.getPathImage())));
		imgAvatar.setName("avatarClient" + nouveauClient.getId());
		imgAvatar.setBounds(avatar.getX(), avatar.getY(), avatar.getLargeur(), avatar.getHauteur());
		pnlAvatars.add(imgAvatar);
		
		revalidate();
		repaint();
		
		this.lstClients.put(nouveauClient.getId(), nouveauClient);
		this.componentMap.put(imgAvatar.getName(), imgAvatar);
	}

	@Override
	public void update(Observable observable) {
		//System.out.println("UPDATE DE SALLE POUR CLIENT " + this.idClient);
		//Regrouper les mouvements d'avatar en groupe?
		if(observable instanceof AvatarClient) {
			AvatarClient modele = (AvatarClient) observable;
			//System.out.println("UPDATE D'AVATAR " + modele.getId() + " POUR CLIENT " + this.idClient + " (" + modele.getTypeModif() + ")");
			switch (modele.getTypeModif()) {
				case DEPLACEMENT :
					this.updateAvatar(modele);
					break;
			}
		}
		else if(observable instanceof ModeleSalleClient) {
			ModeleSalleClient modele = (ModeleSalleClient) observable;
			switch (modele.getTypeModif()) {
				case NOUVEAU_CLIENT :
					this.ajouterClient(modele.getDernierClient());
					break;
			}
		}
	}
	
	public ControleurSalleClient getControleur() {
		return this.controleur;
	}
	
	public Map<TypeDeplacement, Boolean> getKeysPressed() {
		return this.keysPressed;
	}
}
