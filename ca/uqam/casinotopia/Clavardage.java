package ca.uqam.casinotopia;

import java.util.Vector;

public class Clavardage {
	
	private static int MAXMESSAGE = 10;
	
	private Vector<String> messages = new Vector<String>();
	
	public Clavardage() {
		messages.add("Boujours à toi!");
		
	}

	public Vector<String> getMessage() {
		// TODO Auto-generated method stub
		return messages;
	}

	public void addMessage(String message) {
		while(this.messages.size() >= MAXMESSAGE){
			messages.remove(0);
		}
		if(!message.isEmpty() && message != null){
			this.messages.add(message);
		}
		
	}
	
	
	
}