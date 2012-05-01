package ca.uqam.casinotopia.modele.client;

import java.util.List;

import javax.swing.DefaultListModel;

import ca.uqam.casinotopia.modele.Modele;
import ca.uqam.casinotopia.observateur.BaseObservable;
import ca.uqam.casinotopia.observateur.Observable;
import ca.uqam.casinotopia.observateur.Observateur;

public class ModeleChatClient implements Modele, Observable {

	private static final long serialVersionUID = 1197460785333129913L;

	private String salle;

	private String messages;
	
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
	public String getSalle() {
		return this.salle;
	}

	public void setSalle(String salle) {
		this.salle = salle;
		this.notifierObservateur();
	}
	
	public String getMessages() {
		return this.messages;
	}
	
	public void setMessages(String messages) {
		this.messages = messages;
		this.notifierObservateur();
	}
	
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

	
	
	public DefaultListModel getLstUtilisateurModel() {
		return this.lstUtilisateurModel;
	}
	
	public void setLstUtilisateurModel(DefaultListModel lstUtilisateurModel) {
		this.lstUtilisateurModel = lstUtilisateurModel;
	}
	


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

}
