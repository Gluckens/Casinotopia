package ca.uqam.casinotopia.vue;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ca.uqam.casinotopia.Avatar;
import ca.uqam.casinotopia.AvatarClient;
import ca.uqam.casinotopia.TypeDeplacement;
import ca.uqam.casinotopia.connexion.Connexion;
import ca.uqam.casinotopia.controleur.ControleurClient;
import ca.uqam.casinotopia.controleur.client.ControleurSalleClient;
import ca.uqam.casinotopia.controleur.client.MondeVirtuel;
import ca.uqam.casinotopia.modele.client.ModeleClientClient;
import ca.uqam.casinotopia.modele.client.ModeleSalleClient;
import ca.uqam.casinotopia.observateur.Observable;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@SuppressWarnings("serial")
public class VueSalle extends Vue implements Runnable, KeyListener {
	
	private ControleurSalleClient controleur;
	
	//private Avatar avatarClient;
	
	private MondeVirtuel mondeVirtuel;
	
	//Sert a gérer les multiple keyPress
	private Map<TypeDeplacement, Boolean> keysPressed;
	
	private Map<Integer, ModeleClientClient> lstClients;
	//private Set<ModeleClientClient> lstClients;
	
	public int idClient;
	
	
	
	private Point position;
	
	private boolean running = false;
	private int fps = 60;
	private int frameCount = 0;
	
	

	public VueSalle(ControleurClient controleur, Map<Integer, ModeleClientClient> lstClients, int idClient) {
		this.controleur = (ControleurSalleClient) controleur;
		
		//TODO gérer l'ajout des avatars en dehors du constructeur (events update)
		
		/*this.avatarClient = new Avatar(new ModeleClientClient(1), 2, "bobo", 40, 40, "", new Point(0, 0));
		this.avatarClient.ajouterObservateur(this);
		
		this.position = this.avatarClient.getPosition();*/
		
		this.position = new Point(0, 0);
		
		this.keysPressed = new HashMap<TypeDeplacement, Boolean>();
		this.keysPressed.put(TypeDeplacement.HAUT, false);
		this.keysPressed.put(TypeDeplacement.BAS, false);
		this.keysPressed.put(TypeDeplacement.GAUCHE, false);
		this.keysPressed.put(TypeDeplacement.DROITE, false);
		
		this.lstClients = lstClients;
		
		this.idClient = idClient;
		
		/*this.setFocusable(true);
		this.requestFocus();*/

		this.setPanelOptions();
		this.addComponents();
		this.createComponentsMap();
	}
	
	public void demarrerMondeVirtuel() {
		//MondeVirtuel mondeVirtuel = new MondeVirtuel(this.avatarClient);
		//this.mondeVirtuel = new MondeVirtuel(this.avatarClient);
		this.mondeVirtuel = new MondeVirtuel(this);
		
		Thread threadMondeVirtuel = new Thread(mondeVirtuel);
		threadMondeVirtuel.start();
	}

	public void deplacement() {
		/*Point dest = new Point(this.avatarClient.getPosition());
		
		if(this.keysPressed.get(TypeDeplacement.HAUT)) {
			dest.y -= 5;
		}
		if(this.keysPressed.get(TypeDeplacement.BAS)) {
			dest.y += 5;
		}
		if(this.keysPressed.get(TypeDeplacement.GAUCHE)) {
			dest.x -= 5;
		}
		if(this.keysPressed.get(TypeDeplacement.DROITE)) {
			dest.x += 5;
		}
		
		this.avatarClient.setPosition(dest);*/
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
				//TODO Envoyer une commande au serveur pour modifer la position de l'avatar sur le serveur et renvoyer une commande à tous les clients de la salle pour redessiner l'avatar.
				//System.out.println("KEY PRESS SALLE PNL AVATARS");
				
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
				
				//deplacement();
			}
			
			@Override
			public void keyReleased(KeyEvent ke) {
				//TODO Envoyer une commande au serveur pour modifer la position de l'avatar sur le serveur et renvoyer une commande à tous les clients de la salle pour redessiner l'avatar.
				
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
				
				//deplacement();
			}
		});
		
		//for(ModeleClientClient client : this.lstClients) {
		for(ModeleClientClient client : this.lstClients.values()) {
			AvatarClient avatar = client.getAvatar();
			JLabel imgAvatar = new JLabel(new ImageIcon(VueSalle.class.getResource(avatar.getPathImage())));
			imgAvatar.setName("avatarClient" + client.getId());
			imgAvatar.setBounds(avatar.getX(), avatar.getY(), avatar.getLargeur(), avatar.getHauteur());
			pnlAvatars.add(imgAvatar);
			
			
			/*if(client.getId() == 1) {
				JLabel imgAvatar = new JLabel(new ImageIcon(VueSalle.class.getResource("/img/chip_5.png")));
				imgAvatar.setName("avatarClient" + client.getId());
				AvatarClient avatar = client.getAvatar();
				imgAvatar.setBounds(avatar.getX(), avatar.getY(), avatar.getLargeur(), avatar.getHauteur());
				//imgAvatar.setBounds(0, 0, 40, 40);
				pnlAvatars.add(imgAvatar);
			}
			else if(client.getId() == 2) {
				JLabel imgAvatar = new JLabel(new ImageIcon(VueSalle.class.getResource("/img/chip_10.png")));
				imgAvatar.setName("avatarClient" + client.getId());
				AvatarClient avatar = client.getAvatar();
				imgAvatar.setBounds(avatar.getX(), avatar.getY(), avatar.getLargeur(), avatar.getHauteur());
				//imgAvatar.setBounds(0, 0, 40, 40);
				pnlAvatars.add(imgAvatar);
			}
			else if(client.getId() == 3) {
				JLabel imgAvatar = new JLabel(new ImageIcon(VueSalle.class.getResource("/img/chip_25.png")));
				imgAvatar.setName("avatarClient" + client.getId());
				AvatarClient avatar = client.getAvatar();
				imgAvatar.setBounds(avatar.getX(), avatar.getY(), avatar.getLargeur(), avatar.getHauteur());
				//imgAvatar.setBounds(0, 0, 40, 40);
				pnlAvatars.add(imgAvatar);
			}*/
		}
		
		/*JLabel imgAvatar = new JLabel(new ImageIcon(VueSalle.class.getResource("/img/chip_25.png")));
		imgAvatar.setName("avatarClient1");
		imgAvatar.setBounds(0, 0, 40, 40);
		pnlAvatars.add(imgAvatar);*/
		
		this.add(pnlAvatars, new GridBagHelper().setXY(0, 0).setFill(GridBagConstraints.BOTH).end());
	}
	
	/*private void updateAvatar(Map<Integer, ModeleClientClient> lstClients) {
		Iterator<Map.Entry<String, JComponent>> iterComponents = this.getComponentMap().entrySet().iterator();
		while (iterComponents.hasNext()) {
		    Map.Entry<String, JComponent> entry = iterComponents.next();
		    if(entry.getKey().startsWith("avatarClient")) {
		    	iterComponents.remove();
		    }
		}
		
		
		
		for(Map.Entry<String, JComponent> map : this.getComponentMap().entrySet()) {
			if(map.getKey().startsWith("avatarClient")) {
				this.remove(map.getValue());
				
			}
		}
	}*/
	
	private void updateAvatar(AvatarClient avatar) {
		//System.out.println("UPDATE D'AVATAR " + avatar.getClient().getId() + " : (" + avatar.getX() + ", " + avatar.getY() + ", " + avatar.getLargeur() + ", " + avatar.getHauteur() + ")");
		
		
		if(this.idClient == 1) {
			System.out.println("CLIENT 1 : UPDATE D'AVATAR " + avatar.getClient().getId() + " : (" + avatar.getX() + ", " + avatar.getY() + ", " + avatar.getLargeur() + ", " + avatar.getHauteur() + ")");
		}
		
		if(this.idClient != avatar.getClient().getId()) {
			System.out.println("UPDATE D'AVATAR " + avatar.getClient().getId() + " : (" + avatar.getX() + ", " + avatar.getY() + ", " + avatar.getLargeur() + ", " + avatar.getHauteur() + ")");
		}
		
		
		JLabel imgAvatar = (JLabel) this.getComponentByName("avatarClient" + avatar.getClient().getId());
		imgAvatar.setBounds(avatar.getX(), avatar.getY(), avatar.getLargeur(), avatar.getHauteur());
		//imgAvatar.getBounds();
		
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
		if(this.idClient == 1) {
			System.out.println("TEST");
		}
		
		System.out.println("UPDATE DE SALLE POUR CLIENT " + this.idClient);
		//Regrouper les mouvements d'avatar en groupe?
		if(observable instanceof AvatarClient) {
			AvatarClient modele = (AvatarClient) observable;
			System.out.println("UPDATE D'AVATAR " + modele.getId() + " POUR CLIENT " + this.idClient + " (" + modele.getTypeModif() + ")");
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
	
	/*public Avatar getAVatar() {
		return this.avatarClient;
	}*/
	
	public ControleurSalleClient getControleur() {
		return this.controleur;
	}
	
	public Map<TypeDeplacement, Boolean> getKeysPressed() {
		return this.keysPressed;
	}

	@Override
	public void keyPressed(KeyEvent ke) {
		//TODO Envoyer une commande au serveur pour modifer la position de l'avatar sur le serveur et renvoyer une commande à tous les clients de la salle pour redessiner l'avatar.
		System.out.println("KEY PRESS SALLE VUE");
		
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
		
		deplacement();
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
		
		deplacement();
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run() {
		this.running = true;
		//this.movementLoop();
	}
	
	public void movementLoop() {
		//This value would probably be stored elsewhere.
		final double GAME_HERTZ = 30.0;
		//Calculate how many ns each frame should take for our target game hertz.
		final double TIME_BETWEEN_UPDATES = 1000000000 / GAME_HERTZ;
		//At the very most we will update the game this many times before a new render.
		final int MAX_UPDATES_BEFORE_RENDER = 5;
		//We will need the last update time.
		double lastUpdateTime = System.nanoTime();
		//Store the last time we rendered.
		double lastRenderTime = System.nanoTime();
		
		//If we are able to get as high as this FPS, don't render again.
		final double TARGET_FPS = 60;
		final double TARGET_TIME_BETWEEN_RENDERS = 1000000000 / TARGET_FPS;
	  
		//Simple way of finding FPS.
		int lastSecondTime = (int) (lastUpdateTime / 1000000000);
	  
		while (running) {
			double now = System.nanoTime();
			int updateCount = 0;
	      
			//Do as many game updates as we need to, potentially playing catchup.
			while( now - lastUpdateTime > TIME_BETWEEN_UPDATES && updateCount < MAX_UPDATES_BEFORE_RENDER ) {
				this.update();
				lastUpdateTime += TIME_BETWEEN_UPDATES;
				updateCount++;
			}
	  
			//If for some reason an update takes forever, we don't want to do an insane number of catchups.
			//If you were doing some sort of game that needed to keep EXACT time, you would get rid of this.
			if (lastUpdateTime - now > TIME_BETWEEN_UPDATES) {
				lastUpdateTime = now - TIME_BETWEEN_UPDATES;
			}
	  
			//Render. To do so, we need to calculate interpolation for a smooth render.
			float interpolation = Math.min(1.0f, (float) ((now - lastUpdateTime) / TIME_BETWEEN_UPDATES) );
			this.draw(interpolation);
			lastRenderTime = now;
	  
			//Update the frames we got.
			int thisSecond = (int) (lastUpdateTime / 1000000000);
			if (thisSecond > lastSecondTime) {
				System.out.println("NEW SECOND " + thisSecond + " " + frameCount);
				fps = frameCount;
				frameCount = 0;
				lastSecondTime = thisSecond;
			}
			
			//Yield until it has been at least the target time between renders. This saves the CPU from hogging.
			while ( now - lastRenderTime < TARGET_TIME_BETWEEN_RENDERS && now - lastUpdateTime < TIME_BETWEEN_UPDATES) {
				Thread.yield();
				
				//This stops the app from consuming all your CPU. It makes this slightly less accurate, but is worth it.
				//You can remove this line and it will still work (better), your CPU just climbs on certain OSes.
				//FYI on some OS's this can cause pretty bad stuttering. Scroll down and have a look at different peoples' solutions to this.
				try {Thread.sleep(1);} catch(Exception e) {} 

				now = System.nanoTime();
			}
		}
	}
	
	private void update() {
		if(this.keysPressed.get(TypeDeplacement.HAUT)) {
			this.position.y -= 5;
		}
		if(this.keysPressed.get(TypeDeplacement.BAS)) {
			this.position.y += 5;
		}
		if(this.keysPressed.get(TypeDeplacement.GAUCHE)) {
			this.position.x -= 5;
		}
		if(this.keysPressed.get(TypeDeplacement.DROITE)) {
			this.position.x += 5;
		}
	}
	
	private void draw(float interpolation) {
		//this.avatarClient.setPosition(this.position);
	}
}
