// Name: 				Justin Heyman
// Submission Date: 	2021-10-18
// Assignment: 			Checkpoint 3		
// Note to Professor: 	Drawing the animation frames in paint.net took me longer than I'd like to admit. Not my forte.


package Main;

import java.util.ArrayList;
import Data.Vector2D;
import Data.spriteInfo;
import logic.Control;
import timer.stopWatchX;

public class Main{
	/* Fields (Static) below... */
	
	public static stopWatchX timer = new stopWatchX(85); /* Set timer here (milliseconds) */
	public static Vector2D currentVec = new Vector2D(-128, 296); /* To hold temporary 2DVector Position */
	public static int currentSpriteIndex = 0; /* Points to index in sprites ArrayList */
	public static int frameCounter = 0; /* To hold reference to current png */
	public static spriteInfo currentSprite = new spriteInfo(currentVec, "stick"+frameCounter); /* To hold temporary spriteInfo */
	public static ArrayList<spriteInfo> sprites = new ArrayList<>(); /* Holds all sprite animation frames and vector positions */
	
	/* Fields Not Currently in Use 
	public static Queue<Vector2D> vecs1 = new LinkedList<>();
	public static Queue<Vector2D> vecs2 = new LinkedList<>(); 
	public static boolean isImageDrawn = false;
	public static Color c = new Color(255, 255, 51); 
	*/
	
	/* End Static fields... */
	
	public static void main(String[] args) {
		Control ctrl = new Control();				/* Do NOT remove! */
		ctrl.gameLoop();							/* Do NOT remove! */
	}
	
	/* This is your access to things BEFORE the game loop starts */
	public static void start() {
		
		/* Populate array with new spriteInfo Objects */
		while (currentVec.getX() <= 1280){
			if (frameCounter >= 8){
				frameCounter = 0;
			}
			sprites.add(new spriteInfo(new Vector2D(currentVec.getX(), currentVec.getY()), "stick"+frameCounter));
			currentVec.adjustX(16); /* Controls Degree of Sprite Movement */
			frameCounter++;
		}
	}
	
	
	/* This is your access to the "game loop" (It is a "callback" method from the Control class (do NOT modify that class!))*/
	public static void update(Control ctrl) {
		
		ctrl.addSpriteToFrontBuffer(currentSprite.getCoords().getX(), currentSprite.getCoords().getY(), currentSprite.getTag());
		
		/* Time-dependent array iteration */
		if(timer.isTimeUp()){
			currentSprite = sprites.get(currentSpriteIndex);
			currentSpriteIndex++; 
			if (currentSpriteIndex == (sprites.size() - 1)){ /* Restart Array Pointer */
				currentSpriteIndex = 0;
			}
			timer.resetWatch(); /* Adjust timer in fields */
		}
	
		
	} 
	
	// Additional Static methods below...(if needed)

}
