package ca.uqam.casinotopia.controleur.client;

import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JScrollBar;

import ca.uqam.casinotopia.commande.serveur.CmdEnvoyerMessageChat;
import ca.uqam.casinotopia.commande.serveur.CmdSeConnecterAuChat;
import ca.uqam.casinotopia.connexion.Connexion;
import ca.uqam.casinotopia.controleur.ControleurClient;
import ca.uqam.casinotopia.modele.client.ModeleChatClient;
import ca.uqam.casinotopia.modele.client.ModeleClientClient;
import ca.uqam.casinotopia.modele.client.ModelePrincipalClient;
import ca.uqam.casinotopia.vue.VueChat;

public class ControleurChatClient extends ControleurClient {

	private static final long serialVersionUID = 2286997653732479251L;

	private ModeleChatClient modele;
	private VueChat vue;

	
	public ControleurChatClient(Connexion connexion, ModeleChatClient modele, ModeleClientClient client, ModelePrincipalClient modeleNavigation) {
		super(connexion, client, modeleNavigation);
		this.vue = new VueChat(this);
		this.modele = modele;
		this.modele.ajouterObservateur(this.vue);
		this.vue.setLstConnecteModel(this.modele.getLstUtilisateurModel());
	}

	public VueChat getVue() {
		return this.vue;
	}

	public void setVue(VueChat vue) {
		this.vue = vue;
	}
	
	public ModeleChatClient getModele() {
		return this.modele;
	}

	public void initChat(List<String> listeUtilisateur, List<String> listeMessages, String salle){
		this.modele.setChatUtilisateur(listeUtilisateur);
		
		this.modele.setMessages(listeMessages);
		this.modele.setSalle(salle);
	}


	public void cmdSeConnecterAuChat(/*String salle*/) {
		//TODO le setting de la salle devrait se faire uniquement si le serveur le décide (ie, si l'utilisateur a acces, si sa fonctionné, bug du serveur, etc)
		//changer le setSalle
		this.modele.setSalle(this.vue.txtSeConnecterA.getText());
		
		//this.connexion.envoyerCommande(new CmdSeConnecterAuChat(salle));
		this.connexion.envoyerCommande(new CmdSeConnecterAuChat(this.vue.txtSeConnecterA.getText()));
	}
	
	public void actionChangementSalle(String salle) {
		this.modele.setSalle(salle);
	}

	public void cmdEnvoyerMessageChat(String string) {
		this.connexion.envoyerCommande(new CmdEnvoyerMessageChat(this.vue.txtMessage.getText(), this.modele.getSalle()));
		this.vue.txtMessage.setText("");
		this.vue.txtMessage.setFocusable(true);
	}


	public void actionAjouterMessageChat(String message) {
		this.modele.setMessages(this.modele.getMessages() + "\n" + message);
		//getVue().txtChat.setCaretPosition(getVue().txtChat.getText().length());
		//JScrollBar jsb = getVue().scrollPane.getVerticalScrollBar();
		//jsb.setValue(jsb.getMaximum());

	}

	public void setChatUtilisateur(List<String> listeUtilisateur) {
		this.modele.setChatUtilisateur(listeUtilisateur);
	}
}
