// Name: 				Justin Heyman
// Due Date:		 	08 Nov 2021
// Assignment: 			Checkpoint 4		
// Note to Professor: 	Thanks for letting me add my own little touch!


package Main;

import java.awt.Color;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.Random;
import FileIO.EZFileRead;
import logic.Control;
import timer.stopWatchX;

public class Main{
	/* Begin Static Fields*/
	public static stopWatchX timer = new stopWatchX(5000); /* Set timer here (milliseconds) */
	public static EZFileRead ezr = new EZFileRead("Dialogue/jeremy.txt"); /* Stores each line from file into an array */
	public static String raw; /* Holds the raw text for StringTokenizer */
	public static int randInt = 0; 
	public static int tmpInt = 0; 
	public static StringTokenizer st; /* The StringTokenizer variable need to be public */
	public static HashMap<String, String> map = new HashMap<>(); /* map needs to be public */
	
	public static String trigger = "";
	
	/* End Static Fields */

	public static void main(String[] args) {
		Control ctrl = new Control();				/* Do NOT remove! */
		ctrl.gameLoop();							/* Do NOT remove! */
	}
	
	/* Starting Conditions before game loop */
	public static void start() {
		
		/* Populate the hashmap all in one loop */
		int i = 0;	
		while (i < ezr.getNumLines()){ 
			raw = ezr.getNextLine();
			st = new StringTokenizer(raw, "*");
			String Key = st.nextToken();
			String Value = st.nextToken();
			map.put(Key, Value);
			i++;
		}
		
		/* Initial Random Int Assignment (from 0 to 4) */
		randInt = new Random().nextInt(5); 
	}
	
	
	/* The game loop */
	public static void update(Control ctrl) {
		
		ctrl.addSpriteToFrontBuffer(100, 296, "stick3");
		ctrl.drawString(100, 250, trigger, Color.WHITE);
		
		/* New dialogue every 5 seconds */
		if (timer.isTimeUp()){ 
			/* Never repeat the same line twice in a row*/
			do {
				tmpInt = new Random().nextInt(5);
			} while (tmpInt == randInt); 
			randInt = tmpInt;
			timer.resetWatch();
		}	
	} 
	
	/* Additional Static methods below...(if needed) */

}
