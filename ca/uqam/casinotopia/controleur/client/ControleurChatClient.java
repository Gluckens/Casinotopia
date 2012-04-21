package ca.uqam.casinotopia.controleur.client;

import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JScrollBar;

import ca.uqam.casinotopia.commande.serveur.CmdEnvoyerMessageChat;
import ca.uqam.casinotopia.commande.serveur.CmdSeConnecterAuChat;
import ca.uqam.casinotopia.connexion.Connexion;
import ca.uqam.casinotopia.controleur.ControleurClient;
import ca.uqam.casinotopia.modele.client.ModeleChatClient;
import ca.uqam.casinotopia.vue.VueChat;

public class ControleurChatClient extends ControleurClient {

	ModeleChatClient modele;	
	
	VueChat vue;
	

	public ControleurChatClient(Connexion connexion, ModeleChatClient model) {
		super(connexion);
		this.vue = new VueChat(this);
		this.modele = model;
		this.modele.ajouterObservateur(this.vue);
		this.vue.setLstConnecteModel(this.modele.getLstUtilisateurModel());
	}

	public VueChat getVue() {
		return vue;
	}
	
	public void setVue(VueChat vue) {
		this.vue = vue;
	}
	
	public ModeleChatClient getModele() {
		return modele;
	}

	
	public void initChat(List<String> listeUtilisateur, List<String> listeMessages, String salle){
		modele.setChatUtilisateur(listeUtilisateur);
		modele.setMessages(listeMessages);
		modele.setSalle(salle);
	}


	public void seConnecterAuChat() {
		//changer le setSalle
		getModele().setSalle(getVue().txtSeConnecterA.getText());
		
		
		connexion.envoyerCommande(new CmdSeConnecterAuChat(getVue().txtSeConnecterA.getText()));
	}
	
	public void envoyerMessageChat() {
		connexion.envoyerCommande(new CmdEnvoyerMessageChat(vue.txtMessage.getText(), modele.getSalle()));
		getVue().txtMessage.setText("");
		getVue().txtMessage.setFocusable(true);
	}


	public void ajouterMessageChat(String message) {
		modele.setMessages(modele.getMessages()+"\n"+message);
		//getVue().txtChat.setCaretPosition(getVue().txtChat.getText().length());
		//JScrollBar jsb = getVue().scrollPane.getVerticalScrollBar();
		//jsb.setValue(jsb.getMaximum());

	}

	public void setChatUtilisateur(List<String> listeUtilisateur) {
		modele.setChatUtilisateur(listeUtilisateur);
		
	}	
}
