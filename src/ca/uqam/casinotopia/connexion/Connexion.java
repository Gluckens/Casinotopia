package ca.uqam.casinotopia.connexion;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

import ca.uqam.casinotopia.commande.Commande;

public class Connexion {
	
	/**
	 * le socket de la connexion
	 */
	private Socket socket;
	
	/**
	 * indique si la connection est active
	 */
	private boolean connected = false;

	/**
	 * les données d'entrées 
	 */
	private InputStream input;

	/**
	 * les données de sorties
	 */
	private OutputStream output;

	/**
	 * les données d'entrées en objet
	 */	
	private ObjectOutputStream oOutputStream;

	/**
	 * les données de sorties en objet
	 */	
	private ObjectInputStream oInputStream;

	/**
	 * les données d'entrées en DataStream
	 */	
	private DataOutputStream dOutputStream;

	/**
	 * les données de sorties en DataStream
	 */	
	private DataInputStream dInputStream;

	/**
	 * connexion vide
	 */
	public Connexion() {
		this.connected = false;
	}

	/**
	 * conexion à un serveur
	 * @param ip l'adresse ip du serveur
	 * @param port le port du serveur
	 */
	public Connexion(String ip, int port) {
		System.out.println("Connection à " + ip);
		int essaie = 2;
		while (essaie != 0) {
			try {
				this.socket = new Socket(ip, port);
				this.init();
				essaie = 0;
				System.out.println("Connecté a " + ip);
			} catch (UnknownHostException e) {
				System.out.println("Unable to connect to server, UnknownHostException");
				this.connected = false;
				essaie--;
			} catch (IOException e) {
				System.out.println("Incapable de se connecter au serveur");
				this.connected = false;
				essaie--;
			}
		}
	}

	/**
	 * création d'une connexion pour un serveur
	 * @param socket le socket du serveur
	 */
	public Connexion(Socket socket) {
		this.socket = socket;
		this.init();
	}

	/**
	 * initialize les données après une connexion
	 */
	private void init() {
		this.connected = true;
		try {
			this.socket.setTcpNoDelay(true);
		} catch (SocketException e) {
			this.close();
		}

		try {
			this.output = this.socket.getOutputStream();
			this.input = this.socket.getInputStream();

			this.oOutputStream = new ObjectOutputStream(this.output);
			this.oInputStream = new ObjectInputStream(this.input);

			this.dOutputStream = new DataOutputStream(this.output);
			this.dInputStream = new DataInputStream(this.input);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public DataInputStream getDataInputStream() {
		return this.dInputStream;
	}

	public DataOutputStream getDataOutputStream() {
		return this.dOutputStream;
	}

	public ObjectInputStream getObjectInputStream() {
		return this.oInputStream;
	}

	public ObjectOutputStream getObjectOutputStream() {
		return this.oOutputStream;
	}

	/**
	 * indique si la connexion est active
	 * @return
	 */
	public boolean isConnected() {
		return this.connected;
	}

	/**
	 * fermer la connexion
	 */
	public void close() {

		this.connected = false;
		try {
			this.oOutputStream.close();
			this.oInputStream.close();
			this.dOutputStream.close();
			this.dInputStream.close();
			this.socket.close();
		} catch (IOException e) {
			System.err.println("erreur lors de la fermeture de la connexion");
		}
	}

	/**
	 * envoyer une commande sur la connexion
	 * @param cmd la commande à envoyer
	 */
	public void envoyerCommande(Commande cmd) {
		try {
			this.getObjectOutputStream().writeObject(cmd);
			this.getObjectOutputStream().reset();
		} catch (IOException e) {
			System.err.println("la commande n'a pas pu être envoyé car le serveur ne répond pas");
			/*if(this.modeleUtilisateur != null) {
				this.modeleUtilisateur.deconnecter();
			}*/
		}
	}
}