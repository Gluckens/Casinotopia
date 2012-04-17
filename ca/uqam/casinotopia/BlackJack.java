package ca.uqam.casinotopia;
public class BlackJack extends Partie {
	
	public BlackJack(int id, boolean optionArgent, boolean optionMultijoueur, Clavardage clavardage) {
		super(id, optionArgent, optionMultijoueur, clavardage);
		// TODO Auto-generated constructor stub
	}
	private Croupier croupier;
	private JeuCartes jeuCartes;
}