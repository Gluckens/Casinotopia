package ca.uqam.casinotopia.modele.serveur;

import java.util.Random;

import ca.uqam.casinotopia.Jeu;
import ca.uqam.casinotopia.Partie;
import ca.uqam.casinotopia.modele.Modele;
import ca.uqam.casinotopia.modele.client.ModelePartieMachineClient;
import ca.uqam.casinotopia.objet.Utilisateur;

/**
 * Repr�sente une instance de partie de machine � sous
 */
@SuppressWarnings("serial")
public class ModelePartieMachineServeur extends Partie implements Modele {

	public ModelePartieMachineServeur(int id, Jeu infoJeu) {
		super(id, infoJeu);
	}
	
	/**
	 * Les trois cases tir�es au hasard
	 */
	private int no1;
	private int no2;
	private int no3;

	/**
	 * G�n�rer le hasard
	 */
	public void actionnerMachine() {
		Random random = new Random();
		this.no1 = random.nextInt(9);
		this.no2 = random.nextInt(9);
		this.no3 = random.nextInt(9);
	}
	
	/**
	 * Cr�er la version client du mod�le de machine � sous.
	 * 
	 * @return La version client du mod�le de machine � sous
	 */
	public ModelePartieMachineClient creerModeleClient() {
		ModelePartieMachineClient modeleClient = new ModelePartieMachineClient(
				this.id,
				this.infoJeu.creerModeleClient()
		);
		
		return modeleClient;
	}

	/**
	 * R�cup�rer les gains en fonction de la mise
	 * 
	 * @param mise La mise envoy�e
	 * @return Le montant des gains
	 */
	public int getGain(int mise) {
		int gain = 0;
		if(this.no1 == this.no2 && this.no2 == this.no3) {
			gain = mise;
		}
		else if(this.no1 == this.no2 || this.no2 == this.no3 || this.no3 == this.no1) {
			
		}
		else {
			gain = -mise;
		}
		
		return gain;
	}
	
	public int getNo1() {
		return this.no1;
	}
	
	public int getNo2() {
		return this.no2;
	}
	
	public int getNo3() {
		return this.no3;
	}

	@Override
	public void connecter(Utilisateur utilisateur) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deconnecter(Utilisateur utilisateur) {
		// TODO Auto-generated method stub
		
	}
}