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

	ModeleChatClient modele = new ModeleChatClient();
	
	
	VueChat vue;
	
	
	public ControleurChatClient(Connexion connexion) {
		super(connexion);
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

	
	
	public void setChatList(List<String> listeUtilisateur, List<String> listeMessages, String salle){
		setChatUtilisateur(listeUtilisateur);
		
		setChatMessages(listeMessages);
		getVue().lblTitre.setText(salle);
	}


	public void setChatUtilisateur(List<String> listeUtilisateur){

		DefaultListModel model = (DefaultListModel) getVue().lstConnecte.getModel();
		model.clear();
		for (int i = 0; i < listeUtilisateur.size(); i++) {
			model.add(i, listeUtilisateur.get(i));
		}
	}

	public void setChatMessages(List<String> listeMessages){
		String messages = "";
		for (int i = 0; i < listeMessages.size(); i++) {
			if(!listeMessages.get(i).isEmpty()){
				messages += listeMessages.get(i);
				if(i != listeMessages.size()-1){
					messages += "\n";
				}
			}
		}
		getVue().txtChat.setText(messages);

		JScrollBar jsb = getVue().scrollPane.getVerticalScrollBar();
		jsb.setValue(jsb.getMaximum());
	}

		

	public void seConnecterAuChat() {
		if(getVue().txtSeConnecterA.getText().isEmpty()){
			getVue().txtSeConnecterA.setText("entrez un nom de salle ici");
		}else{
			getModele().setSalle(getVue().txtSeConnecterA.getText());
			connexion.envoyerCommande(new CmdSeConnecterAuChat(getModele().getSalle()));
		}
	}
	
	public void envoyerMessageChat(String message) {
		if(!message.isEmpty()) {
			connexion.envoyerCommande(new CmdEnvoyerMessageChat(message, getModele().getSalle()));
			getVue().txtMessage.setText("");
			getVue().txtMessage.setFocusable(true);
		}
	}




	public void ajouterMessageChat(String message) {
		getVue().txtChat.setText(getVue().txtChat.getText()+"\n"+message);
		getVue().txtChat.setCaretPosition(getVue().txtChat.getText().length());
		JScrollBar jsb = getVue().scrollPane.getVerticalScrollBar();
		jsb.setValue(jsb.getMaximum());

	}	
}
