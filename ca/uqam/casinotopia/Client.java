package ca.uqam.casinotopia;
import java.util.Date;
import java.util.Vector;

public class Client extends Utilisateur {
	private String prenom;
	private String nom;
	private Date dateNaissance;
	private String courriel;
	private int solde;
	private int pourcentageGlobal;
	private Salle salleCourante;
	private Vector<PartageGainsClient> partageGains = new Vector<PartageGainsClient>();
	private Vector<DonUniqueClient> donsUniques = new Vector<DonUniqueClient>();
	private ListeAmis listeAmis;
	private Avatar avatar;
}