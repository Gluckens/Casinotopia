package ca.uqam.casinotopia.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CInput {

	private static volatile CInput instance = null;
	private static BufferedReader in;
	
	private CInput() {
		in = new BufferedReader(new InputStreamReader(System.in));
	}
	
	public static String readline(){

		if(CInput.instance == null){
			synchronized (CInput.class) {
				if(CInput.instance == null){
					CInput.instance = new CInput();
				}
			}
		}
		
		String input = null;
        try {
			 input = in.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return input;
	}
	
}
