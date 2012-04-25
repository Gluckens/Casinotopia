package ca.uqam.casinotopia.controleur.client;

import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JScrollBar;

import ca.uqam.casinotopia.commande.serveur.CmdEnvoyerMessageChat;
import ca.uqam.casinotopia.commande.serveur.CmdSeConnecterAuChat;
import ca.uqam.casinotopia.connexion.Connexion;
import ca.uqam.casinotopia.controleur.ControleurClient;
import ca.uqam.casinotopia.modele.client.ModeleChatClient;
import ca.uqam.casinotopia.modele.client.ModelePrincipalClient;
import ca.uqam.casinotopia.vue.VueChat;

public class ControleurChatClient extends ControleurClient {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2286997653732479251L;

	private ModeleChatClient modele;
	private VueChat vue;

	public ControleurChatClient(Connexion connexion, ModeleChatClient modele, ModelePrincipalClient modeleNavigation) {
		super(connexion, modeleNavigation);
		this.vue = new VueChat(this);
		this.modele = modele;
		this.modele.ajouterObservateur(this.vue);
	}

	public VueChat getVue() {
		return this.vue;
	}

	public ModeleChatClient getModele() {
		return this.modele;
	}

	public void setChatList(List<String> listeUtilisateur, List<String> listeMessages, String salle) {
		this.setChatUtilisateur(listeUtilisateur);

		this.setChatMessages(listeMessages);
		this.vue.lblTitre.setText(salle);
	}

	public void setChatUtilisateur(List<String> listeUtilisateur) {

		DefaultListModel model = (DefaultListModel) this.vue.lstConnecte.getModel();
		model.clear();
		for (int i = 0; i < listeUtilisateur.size(); i++) {
			model.add(i, listeUtilisateur.get(i));
		}
	}

	public void setChatMessages(List<String> listeMessages) {
		String messages = "";
		for (int i = 0; i < listeMessages.size(); i++) {
			if (!listeMessages.get(i).isEmpty()) {
				messages += listeMessages.get(i);
				if (i != listeMessages.size() - 1) {
					messages += "\n";
				}
			}
		}
		this.vue.txtChat.setText(messages);

		JScrollBar jsb = this.vue.scrollPane.getVerticalScrollBar();
		jsb.setValue(jsb.getMaximum());
	}

	public void cmdSeConnecterAuChat(String salle) {
		// TODO le setting de la salle devrait se faire uniquement si le serveur
		// le décide (ie, si l'utilisateur a acces, si sa fonctionné, bug du
		// serveur, etc)
		// ((ModeleChatClient)
		// this.lstModeles.get("ModeleChatClient")).setSalle(((VueChat)
		// this.lstVues.get("VueChat")).txtSeConnecterA.getText());
		this.connexion.envoyerCommande(new CmdSeConnecterAuChat(salle));
	}

	public void actionChangementSalle(String salle) {
		this.modele.setSalle(salle);
	}

	public void cmdEnvoyerMessageChat(String message) {
		this.connexion.envoyerCommande(new CmdEnvoyerMessageChat(message, this.modele.getSalle()));
	}

	public void actionAjouterMessageChat(String message) {
		// TODO modifier le modele, et avec l'observateur la vue va se mettre à
		// jour

		this.vue.txtChat.setText(this.vue.txtChat.getText() + "\n" + message);
		this.vue.txtChat.setCaretPosition(this.vue.txtChat.getText().length());
		JScrollBar jsb = this.vue.scrollPane.getVerticalScrollBar();
		jsb.setValue(jsb.getMaximum());
	}
}
