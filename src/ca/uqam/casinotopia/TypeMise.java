package ca.uqam.casinotopia;

public enum TypeMise {
	MISE_5, MISE_10, MISE_25, MISE_50;
	
	public int getMontant() {
		switch(this) {
			case MISE_5 :
				return 5;
			case MISE_10 :
				return 10;
			case MISE_25 :
				return 25;
			case MISE_50 :
				return 50;
			default :
				return 0;
		}
	}
	
	public static TypeMise getType(int montant) {
		switch(montant) {
			case 5 :
				return MISE_5;
			case 10 :
				return MISE_10;
			case 25 :
				return MISE_25;
			case 50 :
				return MISE_50;
			default :
				return null;
		}
	}
}
