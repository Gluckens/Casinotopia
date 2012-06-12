package ca.uqam.casinotopia;

import java.io.Serializable;
import java.sql.Date;
import java.util.Calendar;

import ca.uqam.casinotopia.modele.serveur.ModeleClientServeur;
import ca.uqam.casinotopia.objet.Fondation;

public class DonUniqueClient implements Serializable {
	
	private static final long serialVersionUID = 1796189943288471211L;
	
	private int id;
	private ModeleClientServeur client;
	private Fondation fondation;
	private int montant;
	private Date dateDon;
	
	public DonUniqueClient(ModeleClientServeur client, Fondation fondation, int montant) {
		//TODO Au lieu de null, mettre la date du jour
		this(-1, client, fondation, montant, (Date) Calendar.getInstance().getTime());
		
		/*Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String time = sdf.format(cal.getTime());*/
	}
	
	public DonUniqueClient(int id, ModeleClientServeur client, Fondation fondation, int montant) {
		this(id, client, fondation, montant, (Date) Calendar.getInstance().getTime());
	}
	
	public DonUniqueClient(int id, ModeleClientServeur client, Fondation fondation, int montant, Date dateDon) {
		this.id = id;
		this.client = client;
		this.fondation = fondation;
		this.montant = montant;
		this.dateDon = dateDon;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setFondation(Fondation fondation) {
		this.fondation = fondation;
	}

	public Fondation getFondation() {
		return this.fondation;
	}

	public void setClient_(ModeleClientServeur client) {
		this.client = client;
	}

	public ModeleClientServeur getClient() {
		return this.client;
	}

	public int getMontant() {
		return this.montant;
	}

	public void setMontant(int montant) {
		this.montant = montant;
	}

	public Date getDateDon() {
		return this.dateDon;
	}

	public void setDateDon(Date dateDon) {
		this.dateDon = dateDon;
	}
}