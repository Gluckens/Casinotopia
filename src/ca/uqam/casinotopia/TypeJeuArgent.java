package ca.uqam.casinotopia;

public enum TypeJeuArgent {
	ARGENT, SANS_ARGENT,
	NO_VALUE;
	
	public static TypeJeuArgent getType(String typeArgent) {
		try {
			return valueOf(typeArgent);
		}
		catch (Exception ex) {
            return NO_VALUE;
        }
	}
	
	/*public static TypeJeuArgent getType(String typeArgent) {
		TypeJeuArgent type = null;
		if(typeArgent.equals(ARGENT.toString())) {
			type = ARGENT;
		}
		else if(typeArgent.equals(SANS_ARGENT.toString())) {
			type = SANS_ARGENT;
		}
		
		return type;
	}*/
}
