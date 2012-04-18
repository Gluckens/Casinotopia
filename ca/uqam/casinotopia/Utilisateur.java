package ca.uqam.casinotopia;


/**
 *
 * @author Alexei
 */
public class Utilisateur {
    private int idUtilisateur;
	private String nomUtilisateur;
	private String motDePasse;

    public Utilisateur(String text, char[] password) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public Utilisateur() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public String getNomUtilisateur() {
        return nomUtilisateur;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public void setNomUtilisateur(String nomUtilisateur) {
        this.nomUtilisateur = nomUtilisateur;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int id) {
        this.idUtilisateur = id;
    }
    
        
}
