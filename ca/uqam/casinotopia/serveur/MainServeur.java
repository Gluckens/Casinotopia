package ca.uqam.casinotopia.serveur;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class MainServeur {


	static final int NUMCONNEXION = 10;
	static ServerSocket server;
	static Thread[] thread = new Thread[NUMCONNEXION];
	static ServerThread[] serverThread = new ServerThread[NUMCONNEXION];
	private static Boolean actif = true; 
	
	public static void main(String[] args) {
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
						serverThread[i] = new ServerThread(skt,i);
						thread[i] = new Thread(serverThread[i]);
						thread[i].start();
						System.out.println("client "+i+" connecté");
						break;
					}
				}
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
	

	

}
