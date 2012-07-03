package ca.uqam.casinotopia;

import java.util.Vector;

/**
 * Regroupe les informations du compte Casinotopia
 */
@SuppressWarnings("unused")
public class CompteCasino {
	private int recettes;
	private int pourcentageGlobal;
	private Vector<PartageGainsCasino> partageGains = new Vector<PartageGainsCasino>();
	private Vector<DonUniqueCasino> donsUniques = new Vector<DonUniqueCasino>();
}