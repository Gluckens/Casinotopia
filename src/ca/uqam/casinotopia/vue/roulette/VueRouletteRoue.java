package ca.uqam.casinotopia.vue.roulette;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import javax.swing.*;

import ca.uqam.casinotopia.controleur.ControleurClient;
import ca.uqam.casinotopia.controleur.client.ControleurRouletteClient;
import ca.uqam.casinotopia.modele.client.ModelePartieRouletteClient;
import ca.uqam.casinotopia.observateur.Observable;
import ca.uqam.casinotopia.vue.Vue;

@SuppressWarnings("serial")
public class VueRouletteRoue extends Vue  implements ActionListener {

    private BufferedImage imgRoue;
    private BufferedImage imgBille;
    private int vitesse;
    private double position;
    private double ajoutDegree;
    private Timer m_rotateTimer;
    private int nbTours;
    private HashMap<Integer, Double> positionsNumeros;
    private int resultat; 
    private int gain;
    
	private ControleurRouletteClient controleur;

    /** Creates new form JPannelRoue */
    public VueRouletteRoue(ControleurClient controleur) {
		this.controleur = (ControleurRouletteClient) controleur;
    	
    	this.setPanelOptions();
		this.addComponents();
		this.createComponentsMap();
		
        try {
        	this.imgRoue = ImageIO.read(new File("src/img/rouletteRoue.gif"));
        	this.imgBille = ImageIO.read(new File("src/img/bille.png"));
        } catch (IOException ex) {
            Logger.getLogger(VueRouletteRoue.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.positionsNumeros = initialiserPositions();
        this.m_rotateTimer = new Timer(40, this);
        this.resultat = 0;
    }

	@Override
	protected void addComponents() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{390};
		gridBagLayout.rowHeights = new int[]{500};
		setLayout(gridBagLayout);

		setPreferredSize(new Dimension(400, 400));
	}                  

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.imgRoue != null) {
            Graphics2D g2d = (Graphics2D) g.create();
            Graphics2D g2d2 = (Graphics2D) g.create();
            try {
                g2d.rotate(Math.toRadians(this.position), this.imgRoue.getWidth() / 2, this.imgRoue.getHeight() / 2);
                g2d.drawImage(this.imgRoue, 0, 0, null);
                g2d2.drawImage(this.imgBille, 178, 4, 30, 30,  null);
            } finally {
                g2d.dispose();
            }
        }
    }

    public HashMap<Integer, Double> initialiserPositions() {
    	this.positionsNumeros = new HashMap<Integer, Double>();
        
    	this.positionsNumeros.put(0, 0.0);
    	this.positionsNumeros.put(32, 348.8);
    	this.positionsNumeros.put(15, 339.1);
    	this.positionsNumeros.put(19, 329.4);
    	this.positionsNumeros.put(4, 319.7);
    	this.positionsNumeros.put(21, 310.0);
    	this.positionsNumeros.put(2, 300.3);
    	this.positionsNumeros.put(25, 290.6);
    	this.positionsNumeros.put(17, 280.9);
    	this.positionsNumeros.put(34, 271.2);
    	this.positionsNumeros.put(6, 261.5);
    	this.positionsNumeros.put(27, 251.8);
    	this.positionsNumeros.put(13, 242.1);
    	this.positionsNumeros.put(36, 232.4);
    	this.positionsNumeros.put(11, 222.7);
    	this.positionsNumeros.put(30, 213.0);
    	this.positionsNumeros.put(8, 203.3);
    	this.positionsNumeros.put(23, 193.6);
    	this.positionsNumeros.put(10, 183.9);
    	this.positionsNumeros.put(5, 174.2);
    	this.positionsNumeros.put(24, 164.5);
    	this.positionsNumeros.put(16, 154.8);
        this.positionsNumeros.put(33, 145.1);
        this.positionsNumeros.put(1, 135.4);
        this.positionsNumeros.put(20, 125.7);
        this.positionsNumeros.put(14, 116.0);
        this.positionsNumeros.put(31, 106.3);
        this.positionsNumeros.put(9, 96.6);
        this.positionsNumeros.put(22, 86.9);
        this.positionsNumeros.put(18, 77.4);
        this.positionsNumeros.put(29, 67.9);
        this.positionsNumeros.put(7, 58.2);
        this.positionsNumeros.put(28, 48.5);
        this.positionsNumeros.put(12, 38.8);
        this.positionsNumeros.put(35, 29.1);
        this.positionsNumeros.put(3, 19.4);
        this.positionsNumeros.put(26, 9.7);
        
        return this.positionsNumeros;
    }
    
//    public int genererResultat(){
//        int res;
//        res = (int)(Math.random()*36);
//        tournerRoulette(res);
//        return res;
//    }

    public void tournerRoulette(int result) {
        this.nbTours = 0;
        this.ajoutDegree = 5;
        this.resultat = result;
        this.m_rotateTimer.start();
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource().equals(this.m_rotateTimer)) {
            tourner();
        }
    }

    public void tourner() {

        this.position += this.ajoutDegree;
        if ((this.position + this.ajoutDegree) > 360) {
            System.out.println("nbTours : " + this.nbTours);
            this.nbTours++;
        }
        this.position %= 360;
        repaint();

        if (this.nbTours == 2) {
            if ((this.position + this.ajoutDegree) >= this.positionsNumeros.get(this.resultat)) {
            	this.position = this.positionsNumeros.get(this.resultat).doubleValue();
                repaint();
                this.m_rotateTimer.stop();
                if(this.gain > 0) {
                	JOptionPane.showMessageDialog(null, "Vous avez gagné : " + this.gain);
                }
                else {
                	JOptionPane.showMessageDialog(null, "Vous n'avez pas gagné.");
                }
            }
        }
    }

	@Override
	public void update(Observable observable) {
		if (observable instanceof ModelePartieRouletteClient) {
			ModelePartieRouletteClient modele = (ModelePartieRouletteClient) observable;
			
			switch (modele.getTypeModif()) {
				case SET_GAINS :
					this.tournerRoulette(((ModelePartieRouletteClient) observable).getCaseResultat().getNumero());
					this.gain = ((ModelePartieRouletteClient) observable).getGain();
					System.out.println("gain = " + this.gain);
					break;
			}
		}
	}
}