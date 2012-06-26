package ca.uqam.casinotopia.vue.salle;

import java.awt.Point;

import ca.uqam.casinotopia.type.TypeDeplacement;

/**
 * Classe représentant la game loop de l'environnement virtuel
 */
public class MondeVirtuel implements Runnable {
	
	/**
	 * Position courante de l'avatar du client
	 */
	private Point position;
	
	/**
	 * Position précédente de l'avatar du client
	 */
	private Point lastPosition;
	
	private int lastX;
	private int lastY;
	
	/**
	 * La vue contenant le monde virtuel
	 */
	private VueSalle vue;
	
	private boolean running = false;
	private int fps = 60;
	private int frameCount = 0;

	
	public MondeVirtuel(VueSalle vue) {
		this.vue = vue;
		
		this.position = new Point(0, 0);
		this.lastPosition = (Point) this.position.clone();
		this.lastX = this.position.x;
		this.lastY = this.position.y;
	}

	@Override
	public void run() {
		this.running = true;
		this.movementLoop();
	}
	
	/**
	 * La game loop
	 */
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
				//System.out.println("NEW SECOND " + thisSecond + " " + frameCount);
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
	
	/**
	 * Mise à jour de la position de l'avatar
	 */
	private void update() {
		Point temp = (Point) this.position.clone();
		if(this.vue.getKeysPressed().get(TypeDeplacement.HAUT)) {
			temp.y -= 5;
			if(this.validerDeplacement(temp)) {
				this.position.y = temp.y;
			}
		}
		temp = (Point) this.position.clone();
		if(this.vue.getKeysPressed().get(TypeDeplacement.BAS)) {
			temp.y += 5;
			if(this.validerDeplacement(temp)) {
				this.position.y = temp.y;
			}
		}
		temp = (Point) this.position.clone();
		if(this.vue.getKeysPressed().get(TypeDeplacement.GAUCHE)) {
			temp.x -= 5;
			if(this.validerDeplacement(temp)) {
				this.position.x = temp.x;
			}
		}
		temp = (Point) this.position.clone();
		if(this.vue.getKeysPressed().get(TypeDeplacement.DROITE)) {
			temp.x += 5;
			if(this.validerDeplacement(temp)) {
				this.position.x = temp.x;
			}
		}
		
		//TODO Check de proximité seulement lors des draw?
		this.checkProximites(this.position);
	}
	
	private void checkProximites(Point p) {
		this.vue.getControleur().checkProximites(p);
	}
	
	private boolean validerDeplacement(Point p) {
		return this.vue.getControleur().validerDeplacement(p);
	}
	
	/**
	 * Dessiner la nouvelle position de l'avatar via l'envoi d'une commande au serveur (pour avertir tous les autres client de la salle)
	 * 
	 * @param interpolation L'interpolation, si nécessaire
	 */
	private void draw(float interpolation) {
		//TODO Dépendamment d'où la validation de déplacement se fait, modifier ou non le client avant l'envoi de la commande
		if(!this.position.equals(this.lastPosition)) {
        	this.vue.getControleur().cmdDeplacerAvatar(this.position);
        	this.lastPosition = (Point) this.position.clone();
        	this.lastX = this.position.x;
    		this.lastY = this.position.y;
        }
	}
}