package ca.uqam.casinotopia.controleur.client;

import java.awt.EventQueue;

import ca.uqam.casinotopia.commande.Commande;
import ca.uqam.casinotopia.commande.serveur.CmdAuthentifierClient;
import ca.uqam.casinotopia.connexion.Connexion;
import ca.uqam.casinotopia.controleur.ControleurClient;
import ca.uqam.casinotopia.modele.client.ModelePrincipalClient;
import ca.uqam.casinotopia.modele.client.ModeleChatClient;
import ca.uqam.casinotopia.modele.client.ModelePartieRouletteClient;

public class ControleurPrincipalClient extends ControleurClient {

	private static final long serialVersionUID = 7304176355439547634L;

	private String[] listeServeur;
	
	private boolean enReceptionDeCommande = false;
	
	//private ModelePrincipalClient modele;
	
	
	public ControleurPrincipalClient() {
		super(new ModelePrincipalClient());
		//this.modele = new ModelePrincipalClient();
		this.modeleNav.ajouterControleur("ControleurPrincipalClient", this);
		this.modeleNav.initFrame();
		this.listeServeur = new String[]{"localhost","oli.dnsd.me","dan.dnsd.me"};
		this.enReceptionDeCommande = false;
		this.afficherConnexion();
	}
	
	/*public void ajouterControleur(String nom, ControleurClient ctrl) {
		this.lstControleurs.put(nom, ctrl);
	}
	
	public void afficherFrameApplication() {
		ModelePrincipalClient.frameApplication = new FrameApplication();
		EventQueue.invokeLater(ModelePrincipalClient.frameApplication);
	}*/
	
	
	/*private void initControleur() {
		this.ctrlClientClient = new ControleurClientClient(this.getConnexion());
		this.ctrlRouletteClient = new ControleurRouletteClient(this.getConnexion());
	}*/
	
	/**
	 * Afficher l'interface de connexion au serveur
	 */
	private void afficherConnexion() {
		//this.modele.frameConnexion = new FrameConnexion(this);
		EventQueue.invokeLater(this.modeleNav.getFrameConnexion());
	}
	
	public void afficherFrameApplication() {
		//ModelePrincipalClient.frameApplication = new FrameApplication();
		EventQueue.invokeLater(this.modeleNav.getFrameApplication());
	}
	
	public void connexionAuServeur() {
		if(!this.connexion.isConnected()){
			System.out.println("recherche de serveur...");
			this.setMessageConnexion("recherche de serveur...");
			int i = 0;
			while(this.connexion.isConnected() == false && i < this.listeServeur.length){
				this.setConnexion(new Connexion(this.listeServeur[i], 7777));
				i++;
			}
		}
		
		if(this.connexion.isConnected()) {
			this.setMessageConnexion("connecté!");
			
			//this.initControleur();
			//this.afficherFrameApplicationRoulette();
			//this.afficherMenuPrincipal();

			Commande cmd = new CmdAuthentifierClient(this.modeleNav.getFrameConnexion().getTxtNomUtilisateur().getText(), this.modeleNav.getFrameConnexion().getTxtMotDePasse().getPassword());
			this.connexion.envoyerCommande(cmd);
			
			
			this.receptionCommandes();
			
			System.out.println("FIN DE CONNEXION");
			
		}
	}
	
	private void receptionCommandes() {
		if(!this.enReceptionDeCommande){
			this.enReceptionDeCommande = true;
			new Thread(new ClientThread(this)).start();
		}
	}

	/*public FrameConnexion getFrameConnexion() {
		return this.frameConnexion;
	}*/


	/*public void setVueFrameConnexion(FrameConnexion vueConnexionFrame) {
		this.frameConnexion = vueConnexionFrame;
	}*/

	public void setMessageConnexionErreur(String message) {
		this.modeleNav.getFrameConnexion().setMessageErreur(message);
	}
	
	public void setMessageConnexion(String message){
		this.modeleNav.getFrameConnexion().setMessage(message);
	}
	
	public void actionAfficherMenuPrincipal() {
		/*if(this.modele.getFrameApplication() == null) {
			this.afficherFrameApplication();
		}*/
		
		this.afficherFrameApplication();
		
		ControleurMenuPrincipal ctrlMenuPrincipal = new ControleurMenuPrincipal(this.connexion, this.modeleNav);
		this.modeleNav.ajouterControleur("ControleurMenuPrincipal", ctrlMenuPrincipal);
		this.modeleNav.cacherFrameConnexion();
		this.modeleNav.changerVueFrameApplication("VueChat", ctrlMenuPrincipal.getVue());
	}
	
	public void actionAfficherChat(ModeleChatClient modele) {
		ControleurChatClient ctrlChatClient = new ControleurChatClient(this.connexion, modele, this.modeleNav);
		this.modeleNav.ajouterControleur("ControleurChatClient", ctrlChatClient);
		this.modeleNav.changerVueFrameApplication("VueChat", ctrlChatClient.getVue());
	}
	
	public void actionAfficherJeuRoulette(ModelePartieRouletteClient modele) {
		System.out.println("AFFICHER ROULETTE CHEZ CLIENT");
		
		ControleurRouletteClient ctrlRouletteClient = new ControleurRouletteClient(this.connexion, modele, this.modeleNav, this.modeleNav.getFrameApplication());
		this.modeleNav.ajouterControleur("ControleurRouletteClient", ctrlRouletteClient);
		this.modeleNav.changerVueFrameApplication("VueRoulette", ctrlRouletteClient.getVue());
		
		
		//this.modele.ajouterControleur("ControleurRouletteClient", new ControleurRouletteClient(this.connexion, modele, this.modele.getFrameApplication()));
		
		
		/*VueRoulette vueRoulette = new VueRoulette(this.modele.lstControleurs.get("ControleurRouletteClient"), this.frameApplication);
		((ModelePartieRouletteClient)this.lstControleurs.get("ControleurRouletteClient").getModele("ModelePartieRouletteClient")).ajouterObservateur(vueRoulette);
		//VueRouletteTapis_bad vueRoulette = new VueRouletteTapis_bad(this.lstControleurs.get("ControleurRouletteClient"));
		this.frameApplication.removeAll();
		this.frameApplication.addOrReplace("VueRoulette", vueRoulette);
		//this.frameApplication.changeContentPane(vueRoulette);
		//EventQueue.invokeLater(this.frameApplication);
		
		System.out.println(this.frameApplication.getComponentMap());
		
		this.lstControleurs.get("ControleurRouletteClient").ajouterVue(vueRoulette);*/
	}
	


	
	/*public ControleurClientClient getCtrlClientClient() {
		return this.ctrlClientClient;
	}
	
	public ControleurRouletteClient getCtrlRouletteClient() {
		return this.ctrlRouletteClient;
	}
	
	public ControleurChatClient getCtrlChatClient() {
		return ctrlChatClient;
	}*/
	
	
}






/*
 * 	private FrameApplication frameApplication; 
		//Utile de mettre sa dans un if? si on est ici = on est connecté?
		if(this.getConnexion().isConnected()) {
			this.frameApplication = new FrameApplication();
			EventQueue.invokeLater(this.frameApplication);
		}        
		while(this.getConnexion().isConnected()){
            Commande cmd = null;
            try {
				cmd = (Commande) getConnexion().getObjectInputStream().readObject();
	            if(cmd != null){
	            	if(cmd instanceof CommandeClient) {
		            	if(cmd instanceof CommandeClientControleurClient) {
		            		cmd.action(new ControleurClientClient(this.getConnexion()), this.frameApplication);
		            	}
		            	else if(cmd instanceof CommandeClientControleurRoulette) {
		            		cmd.action(new ControleurRouletteClient(this.getConnexion()), this.frameApplication);
		            	}
	            	}
	            	else {
	            		System.err.println("Seulement des commandes destinées aux clients sont recevables!");
	            	}
	            }
	            else{
	            	System.err.println("Un problème est survenu (commande nulle).");
	            }
	            */
