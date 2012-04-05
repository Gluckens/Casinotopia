import java.util.Vector;

public class Salle {
	private String nom;
	private Vector<Client> listeClients = new Vector<Client>();
	private Clavardage clavardage;
	private Vector<Jeu> listeJeu = new Vector<Jeu>();
}