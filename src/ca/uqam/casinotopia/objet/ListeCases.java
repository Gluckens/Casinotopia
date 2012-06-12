package ca.uqam.casinotopia.objet;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import ca.uqam.casinotopia.type.TypeCase;
import ca.uqam.casinotopia.type.TypeCouleurCase;
import ca.uqam.casinotopia.type.TypePariteCase;

public enum ListeCases {
	INSTANCE;
	
	private static final Map<Integer, Case> LST_CASES_NUMERO;
	static
	{
		Map<Integer, Case> tempMap = new HashMap<Integer, Case>();
		
		tempMap.put(0, new Case(0, TypeCouleurCase.VERT, TypeCase.CHIFFRE, 24));
		
		tempMap.put(1, new Case(1, TypeCouleurCase.ROUGE, TypeCase.CHIFFRE, 18));
		tempMap.put(2, new Case(2, TypeCouleurCase.NOIRE, TypeCase.CHIFFRE, 18));
		tempMap.put(3, new Case(3, TypeCouleurCase.ROUGE, TypeCase.CHIFFRE, 18));
		
		tempMap.put(4, new Case(4, TypeCouleurCase.NOIRE, TypeCase.CHIFFRE, 18));
		tempMap.put(5, new Case(5, TypeCouleurCase.ROUGE, TypeCase.CHIFFRE, 18));
		tempMap.put(6, new Case(6, TypeCouleurCase.NOIRE, TypeCase.CHIFFRE, 18));
		
		tempMap.put(7, new Case(7, TypeCouleurCase.ROUGE, TypeCase.CHIFFRE, 18));
		tempMap.put(8, new Case(8, TypeCouleurCase.NOIRE, TypeCase.CHIFFRE, 18));
		tempMap.put(9, new Case(9, TypeCouleurCase.ROUGE, TypeCase.CHIFFRE, 18));
		
		tempMap.put(10, new Case(10, TypeCouleurCase.NOIRE, TypeCase.CHIFFRE, 18));
		tempMap.put(11, new Case(11, TypeCouleurCase.NOIRE, TypeCase.CHIFFRE, 18));
		tempMap.put(12, new Case(12, TypeCouleurCase.ROUGE, TypeCase.CHIFFRE, 18));
		
		tempMap.put(13, new Case(13, TypeCouleurCase.NOIRE, TypeCase.CHIFFRE, 18));
		tempMap.put(14, new Case(14, TypeCouleurCase.ROUGE, TypeCase.CHIFFRE, 18));
		tempMap.put(15, new Case(15, TypeCouleurCase.NOIRE, TypeCase.CHIFFRE, 18));
		
		tempMap.put(16, new Case(16, TypeCouleurCase.ROUGE, TypeCase.CHIFFRE, 18));
		tempMap.put(17, new Case(17, TypeCouleurCase.NOIRE, TypeCase.CHIFFRE, 18));
		tempMap.put(18, new Case(18, TypeCouleurCase.ROUGE, TypeCase.CHIFFRE, 18));
		
		tempMap.put(19, new Case(19, TypeCouleurCase.ROUGE, TypeCase.CHIFFRE, 18));
		tempMap.put(20, new Case(20, TypeCouleurCase.NOIRE, TypeCase.CHIFFRE, 18));
		tempMap.put(21, new Case(21, TypeCouleurCase.ROUGE, TypeCase.CHIFFRE, 18));
		
		tempMap.put(22, new Case(22, TypeCouleurCase.NOIRE, TypeCase.CHIFFRE, 18));
		tempMap.put(23, new Case(23, TypeCouleurCase.ROUGE, TypeCase.CHIFFRE, 18));
		tempMap.put(24, new Case(24, TypeCouleurCase.NOIRE, TypeCase.CHIFFRE, 18));
		
		tempMap.put(25, new Case(25, TypeCouleurCase.ROUGE, TypeCase.CHIFFRE, 18));
		tempMap.put(26, new Case(26, TypeCouleurCase.NOIRE, TypeCase.CHIFFRE, 18));
		tempMap.put(27, new Case(27, TypeCouleurCase.ROUGE, TypeCase.CHIFFRE, 18));
		
		tempMap.put(28, new Case(28, TypeCouleurCase.NOIRE, TypeCase.CHIFFRE, 18));
		tempMap.put(29, new Case(29, TypeCouleurCase.NOIRE, TypeCase.CHIFFRE, 18));
		tempMap.put(30, new Case(30, TypeCouleurCase.ROUGE, TypeCase.CHIFFRE, 18));

		tempMap.put(31, new Case(31, TypeCouleurCase.NOIRE, TypeCase.CHIFFRE, 18));
		tempMap.put(32, new Case(32, TypeCouleurCase.ROUGE, TypeCase.CHIFFRE, 18));
		tempMap.put(33, new Case(33, TypeCouleurCase.NOIRE, TypeCase.CHIFFRE, 18));
		
		tempMap.put(34, new Case(34, TypeCouleurCase.ROUGE, TypeCase.CHIFFRE, 18));
		tempMap.put(35, new Case(35, TypeCouleurCase.NOIRE, TypeCase.CHIFFRE, 18));
		tempMap.put(36, new Case(36, TypeCouleurCase.ROUGE, TypeCase.CHIFFRE, 18));
		
		LST_CASES_NUMERO = Collections.unmodifiableMap(tempMap);
	}
	
	private static final Map<TypeCouleurCase, Case> LST_CASES_COULEUR;
	static
	{
		Map<TypeCouleurCase, Case> tempMap = new HashMap<TypeCouleurCase, Case>();
		
		tempMap.put(TypeCouleurCase.ROUGE, new Case(TypeCouleurCase.ROUGE, TypeCase.COULEUR, 4));
		tempMap.put(TypeCouleurCase.NOIRE, new Case(TypeCouleurCase.NOIRE, TypeCase.COULEUR, 4));
		
		LST_CASES_COULEUR = Collections.unmodifiableMap(tempMap);
	}
	
	private static final Map<TypePariteCase, Case> LST_CASES_PARITE;
	static
	{
		Map<TypePariteCase, Case> tempMap = new HashMap<TypePariteCase, Case>();
		
		tempMap.put(TypePariteCase.PAIRE, new Case(TypePariteCase.PAIRE, TypeCase.PARITE, 4));
		tempMap.put(TypePariteCase.IMPAIRE, new Case(TypePariteCase.IMPAIRE, TypeCase.PARITE, 4));
		
		LST_CASES_PARITE = Collections.unmodifiableMap(tempMap);
	}
	
	public Case getCaseNumero(int numero) {
		return LST_CASES_NUMERO.get(numero);
	}
	
	public Case getCaseCouleur(TypeCouleurCase typeCouleur) {
		return LST_CASES_COULEUR.get(typeCouleur);
	}
	
	public Case getCaseParite(TypePariteCase typeParite) {
		return LST_CASES_PARITE.get(typeParite);
	}
}
