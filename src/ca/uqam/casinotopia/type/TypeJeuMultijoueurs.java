package ca.uqam.casinotopia.type;

public enum TypeJeuMultijoueurs {
	INCONNUS, AMIS, SEUL,
	NO_VALUE;
	
	public static TypeJeuMultijoueurs getType(String typeMultijoueurs) {
		try {
			return valueOf(typeMultijoueurs);
		}
		catch (Exception ex) {
            return NO_VALUE;
        }
	}
	
	/*public static TypeJeuMultijoueurs getType(String typeMultijoueurs) {
		TypeJeuMultijoueurs type = null;
		if(typeMultijoueurs.equals(INCONNUS.toString())) {
			type = INCONNUS;
		}
		else if(typeMultijoueurs.equals(AMIS.toString())) {
			type = AMIS;
		}
		else if(typeMultijoueurs.equals(SEUL.toString())) {
			type = SEUL;
		}
		
		return type;
	}*/
}
