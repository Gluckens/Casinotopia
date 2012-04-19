/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.uqam.casinotopia;

/**
 *
 * @author Alexei
 */
import java.sql.Date;
import java.util.Iterator;
import java.util.Vector;

public class Client extends Utilisateur {

    private int id;
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

    public Avatar getAvatar() {
        return avatar;
    }

    public String getCourriel() {
        return courriel;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public Vector<DonUniqueClient> getDonsUniques() {
        return donsUniques;
    }

    public ListeAmis getListeAmis() {
        return listeAmis;
    }

    public String getNom() {
        return nom;
    }

    public Vector<PartageGainsClient> getPartageGains() {
        return partageGains;
    }

    public int getPourcentageGlobal() {
        return pourcentageGlobal;
    }

    public String getPrenom() {
        return prenom;
    }

    public Salle getSalleCourante() {
        return salleCourante;
    }

    public int getSolde() {
        return solde;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }

    public void setCourriel(String courriel) {
        this.courriel = courriel;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public void setDonsUniques(Vector<DonUniqueClient> donsUniques) {
        this.donsUniques = donsUniques;
    }

    public void setListeAmis(ListeAmis listeAmis) {
        this.listeAmis = listeAmis;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPartageGains(Vector<PartageGainsClient> partageGains) {
        this.partageGains = partageGains;
    }

    public void setPourcentageGlobal(int pourcentageGlobal) {
        this.pourcentageGlobal = pourcentageGlobal;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setSalleCourante(Salle salleCourante) {
        this.salleCourante = salleCourante;
    }

    public void setSolde(int solde) {
        this.solde = solde;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    @Override
    public String toString() {
        String client;
        client = this.getNomUtilisateur() + " " + this.getNom() + " " + this.getPrenom() + " " + this.getCourriel() + " " + this.getSolde() + "\n";

        Iterator itr = this.getPartageGains().iterator();

        while (itr.hasNext()) {
            client = client + " partage gains: " + itr.next() + " -- \t ";
        }
        return client;
    }
}
