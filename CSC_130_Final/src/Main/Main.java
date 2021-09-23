// Author: 				Justin Heyman
// Date: 				
// Assignment: 			
// Note to Professor: 	


package Main;

import java.awt.Color;
import java.util.LinkedList;
import java.util.Queue;

import Data.Vector2D;
import logic.Control;
import timer.stopWatchX;

public class Main{
	// Fields (Static) below...
	public static Color c = new Color(255, 255, 51);
	public static boolean isImageDrawn = false;
	public static stopWatchX timer = new stopWatchX(15); // modify timer here -- 15 ms looks like 66.7 fps -- when does gameloop bottleneck?
	public static Queue<Vector2D> vecs1 = new LinkedList<>();
	public static Queue<Vector2D> vecs2 = new LinkedList<>();
	public static Vector2D currentVec = new Vector2D(-128, 296); // -128, 296 is just off the screen and in the middle ((720/2) - (128/2) = 296)
	// End Static fields...
	
	public static void main(String[] args) {
		Control ctrl = new Control();				// Do NOT remove!
		ctrl.gameLoop();							// Do NOT remove!
	}
	
	/* This is your access to things BEFORE the game loop starts */
	public static void start() {
		// initial vec1 queue population
		while (currentVec.getX() <= 1280){ // populates the queue with vector objects that go up to 1280 px
			vecs1.add(new Vector2D(currentVec.getX(),currentVec.getY())); // Adds a new <Vector2D> type object to the queue with the current coords
			currentVec.adjustX(4); // will increase coords of next object by 4 px
		}		
	}
	
	/* This is your access to the "game loop" (It is a "callback" method from the Control class (do NOT modify that class!))*/
	public static void update(Control ctrl) {
		
		ctrl.addSpriteToFrontBuffer(currentVec.getX(), currentVec.getY(), "ufo"); // draws the sprite in a new position each game loop
		
		if (vecs1.size() == 1){ // we do not lose the final elem here because it has already copied to vecs2 in the previous game loop
			vecs1 = vecs2;
		}
		
		if(timer.isTimeUp()){ // this is a time-based condition that updates the sprite position every 15 milliseconds (can modify the timer in static fields)
			vecs1.remove(); 
			vecs2.add(currentVec); 
			currentVec = vecs1.peek();
			timer.resetWatch();
		}		 
	
	} 
	
	// Additional Static methods below...(if needed)

}
