package ca.uqam.casinotopia.modele.serveur;

import java.util.Random;

import ca.uqam.casinotopia.modele.Modele;

@SuppressWarnings("serial")
public class ModeleMachineServeur implements Modele {
	
	private int no1;
	private int no2;
	private int no3;

	public void actionnerMachine() {
		Random random = new Random();
		this.no1 = random.nextInt(9);
		this.no2 = random.nextInt(9);
		this.no3 = random.nextInt(9);
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
}