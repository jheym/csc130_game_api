/* This will handle the "Hot Key" system. */

package Main;

import logic.Control;
import timer.stopWatchX;

public class KeyProcessor{
	// Static Fields
	private static char last = ' ';			// For debouncing purposes
	private static stopWatchX sw = new stopWatchX(250); // Debouncing Timer
	private static stopWatchX timer = new stopWatchX(85); // Timer for movement
	private static int i, j;
	
	// Static Method(s)
	public static void processKey(char key){

//		/* USE THIS BLOCK FOR ACTION KEYS CASES ONLY */
//		if(key == ' ')				return;
//		// Debounce routine below...
//		if(key == last)
//			if(sw.isTimeUp() == false)			return;
//		last = key;
//		sw.resetWatch();
//		/* END BLOCK */
		
		/* TODO: You can modify values below here! */
		switch(key){
		case '%':								// ESC key
			System.exit(0);
			break;
		
		/* Spacebar */	
		case '$':
			
			if(key == ' ')				return;
			// Debounce routine below...
			if(key == last)
				if(sw.isTimeUp() == false)			return;
			last = key;
			sw.resetWatch();
			
			/* Code to open dialog boxes */
			if(Main.checkCollision(Main.playerBox, Main.keyboard)){
				Main.trigger = "You've hacked the mainframe!";
				Main.textTrigger = "textbg";
			}
			
			if(Main.checkCollision(Main.playerBox, Main.elevator)){
				Main.trigger = "The elevator is broken!";
				Main.textTrigger = "textbg";
			}

			break;
		
		/* Mouse Coordinates */ 
		case 'm':
			
			if(key == ' ')				return;
			// Debounce routine below...
			if(key == last)
				if(sw.isTimeUp() == false)			return;
			last = key;
			sw.resetWatch();
			
			Control.isMouseCoordsDisplayed = !Control.isMouseCoordsDisplayed;
			break;
		
		/* Movement Keys - Each keypress should have its own set of spriteInfo and currentVec adjust and timer */	
		/* Move North */
		case 'w':
			Main.trigger = "";
			Main.textTrigger = "nothing";
			if (timer.isTimeUp()){
				int x = Main.playerSprite.getCoords().getX();
				int y = Main.playerSprite.getCoords().getY();
				Main.lastPos.setCoords(x, y);
				Main.playerSprite.getCoords().adjustY(-16);
				Main.playerSprite.setTag("stickV"+j);
				j++;
				if (j >= 16){
					j = 0;
				}
				timer.resetWatch();
			}
			
			break;
		
		/* Move West */	
		case 'a':
			Main.trigger = "";
			Main.textTrigger = "nothing";
			if (timer.isTimeUp()){
				int x = Main.playerSprite.getCoords().getX();
				int y = Main.playerSprite.getCoords().getY();
				Main.lastPos.setCoords(x, y);
				Main.playerSprite.getCoords().adjustX(-16);
				Main.playerSprite.setTag("stickL"+i);
				i++;
				if (i >= 8){
					i = 0;
				}
				timer.resetWatch();
			}
			break;
			
		/* Move South */
		case 's':
			Main.trigger = "";
			Main.textTrigger = "nothing";
			if (timer.isTimeUp()){
				int x = Main.playerSprite.getCoords().getX();
				int y = Main.playerSprite.getCoords().getY();
				Main.lastPos.setCoords(x, y);
				Main.playerSprite.getCoords().adjustY(16);
				Main.playerSprite.setTag("stickV"+j);
				j++;
				if (j >= 16){
					j = 0;
				}
				timer.resetWatch();
			}
			break;
		
		/* Move East */	
		case 'd':
			Main.trigger = "";
			Main.textTrigger = "nothing";
			if (timer.isTimeUp()){
				int x = Main.playerSprite.getCoords().getX();
				int y = Main.playerSprite.getCoords().getY();
				Main.lastPos.setCoords(x, y);
				Main.playerSprite.getCoords().adjustX(16);
				Main.playerSprite.setTag("stickR"+i);
				i++;
				if (i >= 8){
					i = 0;
				}
				timer.resetWatch();
			}
			break;
		}
	}
}

