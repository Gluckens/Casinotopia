package ca.uqam.casinotopia.vue;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import ca.uqam.casinotopia.AvatarClient;
import ca.uqam.casinotopia.JeuClient;
import ca.uqam.casinotopia.TypeDeplacement;
import ca.uqam.casinotopia.controleur.ControleurClient;
import ca.uqam.casinotopia.controleur.client.ControleurSalleClient;
import ca.uqam.casinotopia.modele.client.ModeleClientClient;
import ca.uqam.casinotopia.modele.client.ModeleSalleClient;
import ca.uqam.casinotopia.observateur.Observable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
	
	private Map<Integer, JeuClient> lstJeux;
	
	public int idClient;
	
	

	public VueSalle(ControleurClient controleur, Map<Integer, ModeleClientClient> lstClients, int idClient, Map<Integer, JeuClient> lstJeux) {
		this.controleur = (ControleurSalleClient) controleur;
		
		//TODO gérer l'ajout des avatars en dehors du constructeur (events update)
		
		this.keysPressed = new HashMap<TypeDeplacement, Boolean>();
		this.keysPressed.put(TypeDeplacement.HAUT, false);
		this.keysPressed.put(TypeDeplacement.BAS, false);
		this.keysPressed.put(TypeDeplacement.GAUCHE, false);
		this.keysPressed.put(TypeDeplacement.DROITE, false);
		
		this.lstClients = lstClients;
		
		this.idClient = idClient;
		
		this.lstJeux = lstJeux;

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
		gridBagLayout.columnWidths = new int[] { 700, 308 };
		gridBagLayout.rowHeights = new int[] { 50, 250, 380 };
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
		
		for(JeuClient jeu : this.lstJeux.values()) {
			TableJeu table = new TableJeu(jeu.getEmplacement());
			table.setName("tableJeu" + jeu.getId());
			pnlAvatars.add(table);
		}
		
		
		
		//for(ModeleClientClient client : this.lstClients) {
		for(ModeleClientClient client : this.lstClients.values()) {
			AvatarClient avatar = client.getAvatar();
			JLabel imgAvatar = new JLabel(new ImageIcon(VueSalle.class.getResource(avatar.getPathImage())));
			imgAvatar.setName("avatarClient" + client.getId());
			imgAvatar.setBounds(avatar.getX(), avatar.getY(), avatar.getLargeur(), avatar.getHauteur());
			pnlAvatars.add(imgAvatar);
		}
		
		pnlAvatars.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		this.add(pnlAvatars, new GridBagHelper().setXY(0, 0).setWH(1, 3).setFill(GridBagConstraints.BOTH).end());
		
		JButton btnQuitter = new JButton("Quitter");
		btnQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controleur.cmdQuitterSalle();
			}
		});
		//btnQuitter.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnQuitter.setToolTipText("Quitter la salle");
		this.add(btnQuitter, new GridBagHelper().setXY(1, 0).setAnchor(GridBagConstraints.EAST).end());
	}
	
	/*public void afficherSelectionOptionJeu(JeuClient jeu) {
		VueSelectionOptionJeu vueOptionsJeu = (VueSelectionOptionJeu) this.getComponentByName("vueOptionsJeu");
		
		if(vueOptionsJeu == null) {
			JPanel pnlAvatars = (JPanel) this.getComponentByName("pnlAvatars");
			
			vueOptionsJeu = new VueSelectionOptionJeu(this.controleur);
			vueOptionsJeu.setName("vueOptionsJeu");
			//vueOptionsJeu.setBounds(jeu.getEmplacement().x, jeu.getEmplacement().y, 260, 150);
			//vueOptionsJeu.setBackground(new Color(255, 255, 255, 60));
			
			pnlAvatars.add(vueOptionsJeu);
			
			pnlAvatars.setComponentZOrder(vueOptionsJeu, 0);
	
			this.componentMap.put(vueOptionsJeu.getName(), vueOptionsJeu);
			
			revalidate();
			repaint();
		}
	}*/
	
	/*public void afficherSelectionOptionJeu(JeuClient jeu) {
		JInternalFrame frmOptionsJeu = (JInternalFrame) this.getComponentByName("frmOptionsJeu");
		
		if(frmOptionsJeu == null) {
			JPanel pnlAvatars = (JPanel) this.getComponentByName("pnlAvatars");
			
			frmOptionsJeu = new JInternalFrame("Options Jeu", false, false, false, false);
			frmOptionsJeu.setName("frmOptionsJeu");
			frmOptionsJeu.setBounds(jeu.getEmplacement().x, jeu.getEmplacement().y, 260, 150);
			frmOptionsJeu.setOpaque(false);
			frmOptionsJeu.getContentPane().setBackground(new Color(0,0,0,30));
			frmOptionsJeu.setVisible(true);
			
			VueSelectionOptionJeu vueOptionsJeu = new VueSelectionOptionJeu(this.controleur);
			vueOptionsJeu.setName("vueOptionsJeu");
			//vueOptionsJeu.setBounds(jeu.getEmplacement().x, jeu.getEmplacement().y, 260, 150);
			//vueOptionsJeu.setBackground(new Color(255, 255, 255, 60));
			
			frmOptionsJeu.getContentPane().add(vueOptionsJeu);
			
			pnlAvatars.add(frmOptionsJeu);
			
			pnlAvatars.setComponentZOrder(frmOptionsJeu, 0);
	
			this.componentMap.put(frmOptionsJeu.getName(), frmOptionsJeu);
			this.componentMap.put(vueOptionsJeu.getName(), vueOptionsJeu);
			
			revalidate();
			repaint();
		}
	}*/
	
	public void afficherSelectionOptionJeu(JeuClient jeu) {
		VueSelectionOptionJeu vueOptionsJeu = (VueSelectionOptionJeu) this.getComponentByName("vueOptionsJeu");
		
		if(vueOptionsJeu == null) {
			vueOptionsJeu = new VueSelectionOptionJeu(this.controleur, jeu);
			vueOptionsJeu.setName("vueOptionsJeu");
			vueOptionsJeu.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			
			this.add(vueOptionsJeu, new GridBagHelper().setXY(1, 1).setFill(GridBagConstraints.BOTH).end());
	
			this.componentMap.put(vueOptionsJeu.getName(), vueOptionsJeu);
			
			revalidate();
			repaint();
		}
	}
	
	public void cacherSelectionOptionJeu() {
		VueSelectionOptionJeu vueOptionsJeu = (VueSelectionOptionJeu) this.getComponentByName("vueOptionsJeu");
		
		if(vueOptionsJeu != null) {
			this.remove(vueOptionsJeu);
			this.componentMap.remove("vueOptionsJeu");
			
			revalidate();
			repaint();
		}
	}
	
	/*public void cacherSelectionOptionJeu() {
		VueSelectionOptionJeu vueOptionsJeu = (VueSelectionOptionJeu) this.getComponentByName("vueOptionsJeu");
		
		if(vueOptionsJeu != null) {
			JPanel pnlAvatars = (JPanel) this.getComponentByName("pnlAvatars");
			pnlAvatars.remove(vueOptionsJeu);
			this.componentMap.remove("vueOptionsJeu");
			
			revalidate();
			repaint();
		}
	}*/
	
	private void updateAvatar(AvatarClient avatar) {
		//System.out.println("CLIENT " + this.idClient + " : UPDATE D'AVATAR " + avatar.getId() + " : (" + avatar.getX() + ", " + avatar.getY() + ", " + avatar.getLargeur() + ", " + avatar.getHauteur() + ")");
		
		JLabel imgAvatar = (JLabel) this.getComponentByName("avatarClient" + avatar.getId());
		//TODO NullPointerException quand on est dans une salle et qu'on pèse sur le "X"
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
	
	private void retirerClient(ModeleClientClient clientRetire) {
		JPanel pnlAvatars = (JPanel) this.getComponentByName("pnlAvatars");
		
		JLabel imgAvatar = (JLabel) this.getComponentByName("avatarClient" + clientRetire.getId());
		pnlAvatars.remove(imgAvatar);
		
		revalidate();
		repaint();
		
		//TODO Utile???
		this.lstClients.remove(clientRetire.getId());
		this.componentMap.remove(imgAvatar.getName());
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
					
				case RETIRER_CLIENT :
					this.retirerClient(modele.getClientRetire());
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

@SuppressWarnings("serial")
class TableJeu extends JComponent {
	
	private Rectangle emplacement;
	
	public TableJeu(Rectangle emplacement) {
		this.emplacement = emplacement;
		
		this.setBounds(this.emplacement);
	}

	public void paint(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, this.emplacement.width, this.emplacement.height);
		//g.fillRect(this.emplacement.x, this.emplacement.y, this.emplacement.width, this.emplacement.height);
	}
}
