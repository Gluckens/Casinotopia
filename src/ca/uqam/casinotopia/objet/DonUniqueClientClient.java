package ca.uqam.casinotopia.objet;

import java.io.Serializable;
import java.sql.Date;
import java.util.Calendar;

import ca.uqam.casinotopia.modele.client.ModeleClientClient;

public class DonUniqueClientClient implements Serializable {
	
	private static final long serialVersionUID = 1796189943288471211L;
	
	private int id;
	private ModeleClientClient client;
	private Fondation fondation;
	private int montant;
	private Date dateDon;
	
	public DonUniqueClientClient(ModeleClientClient client, Fondation fondation, int montant) {
		//TODO Au lieu de null, mettre la date du jour
		this(-1, client, fondation, montant, (Date) Calendar.getInstance().getTime());
		
		/*Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String time = sdf.format(cal.getTime());*/
	}
	
	public DonUniqueClientClient(int id, ModeleClientClient client, Fondation fondation, int montant) {
		this(id, client, fondation, montant, (Date) Calendar.getInstance().getTime());
	}
	
	public DonUniqueClientClient(int id, ModeleClientClient client, Fondation fondation, int montant, Date dateDon) {
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

	public void setClient_(ModeleClientClient client) {
		this.client = client;
	}

	public ModeleClientClient getClient() {
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