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
import ca.uqam.casinotopia.vue.FrameApplication;
import ca.uqam.casinotopia.vue.Vue;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * JPannelRoue.java
 *
 * Created on 2012-04-21, 07:52:52
 */
/**
 *
 * @author Alexei
 */
public class VueRouletteRoue extends Vue  implements ActionListener {

    BufferedImage image;
    private int vitesse;
    private double position;
    private double ajoutDegree;
    private Timer m_rotateTimer;
    private int nbTours;
    private HashMap<Integer, Double> positionsNumeros;
    private int resultat; 
    
	private ControleurRouletteClient controleur;
	private FrameApplication frame;

    /** Creates new form JPannelRoue */
    public VueRouletteRoue(ControleurClient controleur, FrameApplication frame) {
        initComponents();
		this.controleur = (ControleurRouletteClient) controleur;
		this.frame = frame;		
        try {
            image = ImageIO.read(new File("src/img/rouletteRoue.gif"));
        } catch (IOException ex) {
            Logger.getLogger(VueRouletteRoue.class.getName()).log(Level.SEVERE, null, ex);
        }
        positionsNumeros = initialiserPositions();
        m_rotateTimer = new Timer(40, this);
        resultat = 0;
        //tournerRoulette(6);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

    	System.out.println("DESSIN DE ROUE");
//        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
//        this.setLayout(layout);
//        layout.setHorizontalGroup(
//            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//            .addGap(0, 100, Short.MAX_VALUE)
//        );
//        layout.setVerticalGroup(
//            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//            .addGap(0, 80, Short.MAX_VALUE)
//        );
        
		GridBagLayout gridBagLayout = new GridBagLayout();
		/*gridBagLayout.columnWidths = new int[]{75, 75, 75};
		gridBagLayout.rowHeights = new int[]{15, 18, 21, 24, 27, 30, 33, 36, 39, 42, 45};*/
		gridBagLayout.columnWidths = new int[]{390};
		gridBagLayout.rowHeights = new int[]{500};
		gridBagLayout.columnWeights = new double[]{0.0};
		gridBagLayout.rowWeights = new double[]{0.0};
		setLayout(gridBagLayout);
//		
//
		setPreferredSize(new Dimension(300, 300));
//		
//		JLabel lblImgTapis = new JLabel(new ImageIcon(VueRouletteTapis.class.getResource("/img/roulette-table.jpg")));
//		lblImgTapis.setName("imgTapis");
//		this.add(lblImgTapis, new GridBagHelper().setXY(0, 0).setFill(GridBagConstraints.BOTH).end());
    }// </editor-fold>                        
    // Variables declaration - do not modify                     
    // End of variables declaration                   

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            //System.out.println("test : " + position);
            Graphics2D g2d = (Graphics2D) g.create();
            try {
                g2d.rotate(Math.toRadians(position), image.getWidth() / 2, image.getHeight() / 2);
                g2d.drawImage(image, 0, 0, null);
            } finally {
                g2d.dispose();
            }
        }
    }

    public HashMap<Integer, Double> initialiserPositions() {
        positionsNumeros = new HashMap<Integer, Double>();
        
        positionsNumeros.put(0, 0.0);
        positionsNumeros.put(32, 348.8);
        positionsNumeros.put(15, 339.1);
        positionsNumeros.put(19, 329.4);
        positionsNumeros.put(4, 319.7);
        positionsNumeros.put(21, 310.0);
        positionsNumeros.put(2, 300.3);
        positionsNumeros.put(25, 290.6);
        positionsNumeros.put(17, 280.9);
        positionsNumeros.put(34, 271.2);
        positionsNumeros.put(6, 261.5);
        positionsNumeros.put(27, 251.8);
        positionsNumeros.put(13, 242.1);
        positionsNumeros.put(36, 232.4);
        positionsNumeros.put(11, 222.7);
        positionsNumeros.put(30, 213.0);
        positionsNumeros.put(8, 203.3);
        positionsNumeros.put(23, 193.6);
        positionsNumeros.put(10, 183.9);
        positionsNumeros.put(5, 174.2);
        positionsNumeros.put(24, 164.5);
        positionsNumeros.put(16, 154.8);
        positionsNumeros.put(33, 145.1);
        positionsNumeros.put(1, 135.4);
        positionsNumeros.put(20, 125.7);
        positionsNumeros.put(14, 116.0);
        positionsNumeros.put(31, 106.3);
        positionsNumeros.put(9, 96.6);
        positionsNumeros.put(22, 86.9);
        positionsNumeros.put(18, 77.4);
        positionsNumeros.put(29, 67.9);
        positionsNumeros.put(7, 58.2);
        positionsNumeros.put(28, 48.5);
        positionsNumeros.put(12, 38.8);
        positionsNumeros.put(35, 29.1);
        positionsNumeros.put(3, 19.4);
        positionsNumeros.put(26, 9.7);
        
        return positionsNumeros;
    }
    
//    public int genererResultat(){
//        int res;
//        res = (int)(Math.random()*36);
//        tournerRoulette(res);
//        return res;
//    }

    public void tournerRoulette(int result) {
        nbTours = 0;
        ajoutDegree = 5;
        resultat = result;
        m_rotateTimer.start();
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource().equals(m_rotateTimer)) {
            tourner();
        }
    }

    public void tourner() {

        position += ajoutDegree;
        if ((position + ajoutDegree) > 360) {
            System.out.println("nbTours : " + nbTours);
            nbTours++;
        }
        position %= 360;
        repaint();

        if (nbTours == 2) {
            
            if ((position + ajoutDegree)>=positionsNumeros.get(resultat))
            {
                position = positionsNumeros.get(resultat).doubleValue();
                repaint();
                m_rotateTimer.stop();
            }
        }

    }

	@Override
	public void update(Observable observable) {
		if (observable instanceof ModelePartieRouletteClient) {
			System.out.println("Alexei --> vueRouletteClient.update()");
			this.tournerRoulette(((ModelePartieRouletteClient) observable).getCaseResultat().getNumero());
			//int j = ((ModelePartieRouletteClient) observable).getGain();
			//System.out.println("gain = " + j);
		}
		System.out.println("Update fonctionne!!!");
	}

	@Override
	protected void addComponents() {
		// TODO Auto-generated method stub
		
	}
}
