/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.uqam.casinotopia;

/**
 *
 * @author Alexei
 */
public class Avatar {
        private int id;
	private String nomImage;
	private String texte;

    public int getId() {
        return id;
    }

    public String getNomImage() {
        return nomImage;
    }

    public String getTexte() {
        return texte;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNomImage(String nomImage) {
        this.nomImage = nomImage;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }
        
        
}
