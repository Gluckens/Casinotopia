package ca.uqam.casinotopia.controleur.serveur;

import java.io.IOException;
import java.net.BindException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import ca.uqam.casinotopia.modele.serveur.ModeleServeurPrincipal;


public class ControleurServeurPrincipal {


	public static final int NUMCONNEXION = 10;
	private static ServerSocket server;
	public static Thread[] thread = new Thread[NUMCONNEXION];
	public static ControleurServeurThread[] serverThread = new ControleurServeurThread[NUMCONNEXION];
	private static Boolean actif = true; 
	
	public static ModeleServeurPrincipal model = new ModeleServeurPrincipal();
	
	public ControleurServeurPrincipal() {
		//TODO modifier la création de thread en bag ?

	    try {
	      InetAddress address = InetAddress.getLocalHost();
	      	System.out.println("Ton ip est surement : "+address.getHostAddress());
	    }
	    catch (UnknownHostException e) {
	    	System.out.println("Could not find this computer's address.");
	    }
		try {
			System.out.println("création du server");
			server = new ServerSocket(7777);
			System.out.println("server démarré");
			while(actif){
				Socket skt = server.accept();
				for (int i = 0; i < NUMCONNEXION; i++) {
					if(thread[i] != null && !thread[i].isAlive()){
						thread[i] = null;
					}
					if(thread[i] == null){
						serverThread[i] = new ControleurServeurThread(skt,i);
						thread[i] = new Thread(serverThread[i]);
						thread[i].start();
						System.err.println("client "+i+" connecté");
						break;
					}
				}
				//indiquer au client que le serveur est plein
			}
		} catch (BindException e) {
			System.out.println("Il y a déjà un serveur sur même port");
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
	

	

}
