package ca.uqam.casinotopia.modele.client;

import java.util.List;

import javax.swing.DefaultListModel;

import ca.uqam.casinotopia.modele.Modele;
import ca.uqam.casinotopia.observateur.BaseObservable;
import ca.uqam.casinotopia.observateur.Observable;
import ca.uqam.casinotopia.observateur.Observateur;
import ca.uqam.casinotopia.type.modif.TypeModif;

/**
 * Représente une instance de chat
 */
public class ModeleChatClient implements Modele, Observable {

	private static final long serialVersionUID = -5918518651954748935L;

	/**
	 * nom du chat
	 */
	private String salle;

	/**
	 * les messages envoyés au chat
	 */
	private String messages;
	
	/**
	 * liste des utilisateurs du chat
	 */
	private DefaultListModel lstUtilisateurModel;
	
	
	private BaseObservable sujet = new BaseObservable(this);

	
	public ModeleChatClient() {
		this.salle = "";
		this.messages = "";
		this.lstUtilisateurModel = new DefaultListModel();
	}
	public ModeleChatClient(String salle, String messages, DefaultListModel lstUtilisateurModel) {
		this.salle = salle;
		this.messages = messages;
		this.lstUtilisateurModel = lstUtilisateurModel;
	}
	
	/**
	 * obtient la salle
	 * @return la salle
	 */
	public String getSalle() {
		return this.salle;
	}

	/**
	 * définit la salle
	 * @param salle le nom de la salle
	 */
	public void setSalle(String salle) {
		this.salle = salle;
		this.notifierObservateur();
	}
	
	/**
	 * obtient les messages
	 * @return les messages
	 */
	public String getMessages() {
		return this.messages;
	}
	
	/**
	 * définit les messages
	 * @param messages les messages
	 */
	public void setMessages(String messages) {
		this.messages = messages;
		this.notifierObservateur();
	}
	
	/**
	 * définit les messages à partir d'un array
	 * @param listeMessages array de messages
	 */
	public void setMessages(List<String> listeMessages){
		String messages = "";
		for (int i = 0; i < listeMessages.size(); i++) {
			if(!listeMessages.get(i).isEmpty()){
				messages += listeMessages.get(i);
				if(i != listeMessages.size()-1){
					messages += "\n";
				}
			}
		}
		
		this.messages = messages;
		
		this.notifierObservateur();
	}
	
	/**
	 * obtient le modele de la liste d'utilisateurs
	 * @return le modele de la liste d'utilisateurs
	 */
	public DefaultListModel getLstUtilisateurModel() {
		return this.lstUtilisateurModel;
	}
	
	/**
	 * définit le modele de la liste d'utilisateur
	 * @param lstUtilisateurModel le modele de la liste d'utilisateur
	 */
	public void setLstUtilisateurModel(DefaultListModel lstUtilisateurModel) {
		this.lstUtilisateurModel = lstUtilisateurModel;
	}

	/**
	 * définit le modele de la liste d'utilisateur
	 * @param lstUtilisateurModel le modele de la liste d'utilisateur
	 */
	public void setChatUtilisateur(List<String> listeUtilisateur){
		this.lstUtilisateurModel.clear();
		for (int i = 0; i < listeUtilisateur.size(); i++) {
			this.lstUtilisateurModel.add(i, listeUtilisateur.get(i));
		}
	}
	

	@Override
	public void ajouterObservateur(Observateur obs) {
		this.sujet.ajouterObservateur(obs);
	}

	@Override
	public void retirerObservateur(Observateur obs) {
		this.sujet.retirerObservateur(obs);
	}

	@Override
	public boolean estObservePar(Observateur obs) {
		return this.sujet.estObservePar(obs);
	}

	@Override
	public void notifierObservateur() {
		this.sujet.notifierObservateur();
	}
	@Override
	public TypeModif getTypeModif() {
		// TODO Auto-generated method stub
		return null;
	}
}