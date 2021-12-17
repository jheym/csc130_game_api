// Name: 				Justin Heyman
// Due Date:		 	17 Dec 2021
// Assignment: 			Final Project		
// Note to Professor: 	Thanks for making Data Structures and Algorithms a fun class!


package Main;

import java.awt.Color;
import java.util.ArrayList;

import Data.Vector2D;
import Data.boundingBox;
import Data.spriteInfo;


import logic.Control;


public class Main{
	
	/* Begin Static Fields*/
//	public static int currentSpriteIndex = 0; /* Points to index in sprites ArrayList */
	public static int frameCounter = 0; /* To hold reference to current png */
	public static Vector2D currentVec = new Vector2D(100, 296); /* To hold temporary 2DVector Position */
	public static Vector2D lastVec = new Vector2D(0, 0);
	public static spriteInfo playerSprite = new spriteInfo(currentVec, "stickR"+frameCounter); /* To hold temporary spriteInfo */
	public static spriteInfo lastPos = new spriteInfo (lastVec, playerSprite.getTag()); //This is only getting assigned at the beginning
	
	public static boundingBox playerBox;
	public static ArrayList<boundingBox> boxes = new ArrayList<>();
	public static ArrayList<spriteInfo> sprites = new ArrayList<>();
	
	public static boundingBox keyboard = new boundingBox(770, 815, 155, 270);
	public static boundingBox elevator = new boundingBox(960, 1020, 75, 145);
	public static String trigger = "";
	public static String textTrigger = "nothing";
	
	public static spriteInfo dialogBox = new spriteInfo(new Vector2D(410, 500), textTrigger);
	
	/* End Static Fields */

	public static void main(String[] args) {
		Control ctrl = new Control();				/* Do NOT remove! */
		ctrl.gameLoop();							/* Do NOT remove! */
	}
	
	/* Starting Conditions before game loop */
	public static void start() {
		
		/* Defining all bounding boxes for static objects */
		boxes.add(new boundingBox(-128, 1408, 0, 128));  	// Top boundary
		boxes.add(new boundingBox(-128, 1408, 700, 848));	// Bottom boundary
		boxes.add(new boundingBox(-128, 50, -128, 848));	// Left Boundary
		boxes.add(new boundingBox(1280, 1408, -128, 848));	// Right Boundary
		boxes.add(new boundingBox(120, 290, 540, 720)); // Bottom left server
		boxes.add(new boundingBox(450, 620, 530, 720)); // Bottom middle server
		boxes.add(new boundingBox(780, 945, 520, 720)); // Bottom right server
		boxes.add(new boundingBox(200, 370, 128, 420)); // top left server
		boxes.add(new boundingBox(610, 780, 128, 430)); // top right server	
		
		/* To fulfill "Image Data Java Collection" Requirement */
		sprites.add(new spriteInfo(new Vector2D(0, 0), "background")); 
		sprites.add(dialogBox);
	}
		
	
	
	/* The game loop */
	public static void update(Control ctrl) {
		
		/* Player bounding box updated by current playerSprite position and adjusted bounds relative to origin (top left) */
		playerBox = new boundingBox(playerSprite, 20, 108, 108, 120);  	
		
		/* Check collision between player and any rigid body stored in the array */
		for (int i = 0; i < boxes.size(); i++)
			if (checkCollision(playerBox, boxes.get(i)))
				bouncePlayer(); 				// Move the player to previous position before sprite gets added to front buffer!
		
		/* Draws the level sprites that are not the player (there is only one sprite) */
		ctrl.addSpriteToFrontBuffer(sprites.get(0).getCoords().getX(), sprites.get(0).getCoords().getY(), 
				sprites.get(0).getTag());
		
		/* Player Sprite */
		 ctrl.addSpriteToFrontBuffer(playerSprite.getCoords().getX(), playerSprite.getCoords().getY(), 
				 playerSprite.getTag());
		
		/* Dialog Prompts */
		ctrl.addSpriteToFrontBuffer(410, 500, textTrigger);
		ctrl.drawString(415, 600, trigger, Color.GREEN);
	}
	
	
	/* Additional Static methods below...(if needed) */
	
	/* Method for checking a collision. If any of the comparisons are true, there is no collision. */
	public static boolean checkCollision(boundingBox box1, boundingBox box2){
		if (((box1.getX1() > box2.getX2()) 
			|| (box1.getX2() < box2.getX1()) 
			|| (box1.getY1() > box2.getY2()) 
			|| (box1.getY2() < box2.getY1())))
			return false;
		else 
			return true;
		}

	/* Method for bouncing the player back to the last position after a collision detection */
	public static void bouncePlayer(){
		if ((playerSprite.getCoords().getX() - lastPos.getCoords().getX()) != 0){
			if ((playerSprite.getCoords().getX() - lastPos.getCoords().getX()) > 0)		// Moved from West to East
				playerSprite.getCoords().adjustX(-16);
			if ((playerSprite.getCoords().getX() - lastPos.getCoords().getX()) < 0)  	// Moved from East to West
				playerSprite.getCoords().adjustX(+16);
		}
		if ((playerSprite.getCoords().getY() - lastPos.getCoords().getY()) != 0){
			if ((playerSprite.getCoords().getY() - lastPos.getCoords().getY()) > 0)		// Moved from North to South
				playerSprite.getCoords().adjustY(-16);
			if ((playerSprite.getCoords().getY() - lastPos.getCoords().getY()) < 0)		// Moved from South to North
				playerSprite.getCoords().adjustY(+16);
		}
	}
}
