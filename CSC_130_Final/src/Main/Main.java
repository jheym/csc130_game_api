// Name: 				Justin Heyman
// Due Date:		 	17 Dec 2021
// Assignment: 			Final Project		
// Note to Professor: 	I'm gonna need you to rate my code on a scale of 1 to spaghetti


package Main;

import java.awt.Color;
import java.util.ArrayList;

import Data.Vector2D;
import Data.boundingBox;
import Data.spriteInfo;


import logic.Control;


public class Main{
	
	/* Begin Static Fields*/
	public static int frameCounter = 0; 			/* To hold reference to current png */
	
	public static String trigger = ""; 				/* For toggling dialog */
	public static String textTrigger = "nothing"; 	/* A really tiny transparent png for toggling */
	
	public static Vector2D currentVec = new Vector2D(100, 296); 	/* Holds current position of player */
	public static Vector2D lastVec = new Vector2D(0, 0); 			/* Holds previous position of player */
	
	public static boundingBox playerBox;
	public static boundingBox keyboard = new boundingBox(770, 815, 155, 270); /* Interactable object */
	public static boundingBox elevator = new boundingBox(960, 1020, 75, 145); /* Interactable object */
	
	public static spriteInfo playerSprite = new spriteInfo(currentVec, "stickR"+frameCounter);
	public static spriteInfo dialogBox = new spriteInfo(new Vector2D(410, 500), textTrigger);
	public static spriteInfo lastPos = new spriteInfo (lastVec, playerSprite.getTag()); 
	
	public static ArrayList<boundingBox> boxes = new ArrayList<>(); 	/* Holds bounding boxes */
	public static ArrayList<spriteInfo> sprites = new ArrayList<>(); 	/* Holds sprites */
	/* End Static Fields */

	
	/* No touchy! */
	public static void main(String[] args) {
		Control ctrl = new Control();				/* Do NOT remove! */
		ctrl.gameLoop();							/* Do NOT remove! */
	}
	/* End no touchy */
	
	
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
		/* Add all spriteInfo objects to an arrayList */
		sprites.add(new spriteInfo(new Vector2D(0, 0), "background")); 
		sprites.add(playerSprite);
		sprites.add(dialogBox);
	}
		
	
	
	/* THE GAME LOOP */
	public static void update(Control ctrl) {
		
		/* Player bounding box updated by current playerSprite position and adjusted bounds relative to origin (top left) */
		playerBox = new boundingBox(playerSprite, 20, 108, 108, 120);  	
		
		/* Check collision between player and any rigid body stored in the array and bounce the player if true */
		for (int i = 0; i < boxes.size(); i++)
			if (checkCollision(playerBox, boxes.get(i)))
				bouncePlayer(); 
		
		/* Drawing all sprites by iterating through an ArrayList */
		for (int i = 0; i < sprites.size(); i++)
			ctrl.addSpriteToFrontBuffer(sprites.get(i).getCoords().getX(), sprites.get(i).getCoords().getY(), 
					sprites.get(i).getTag());
		
		/* Keyprocessor Event, dialog is in KeyProcessor.java because there are no points for using File IO or tokenizers */
		ctrl.drawString(415, 600, trigger, Color.GREEN);
	}
	
	
	/////////* STATIC METHODS */////////
	
	/* If any of the comparisons are true in the entire clause, there is no collision. */
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
			if ((playerSprite.getCoords().getX() - lastPos.getCoords().getX()) > 0)		// If moved from West to East
				playerSprite.getCoords().adjustX(-16);								
			if ((playerSprite.getCoords().getX() - lastPos.getCoords().getX()) < 0)  	// If moved from East to West
				playerSprite.getCoords().adjustX(+16);
		}
		if ((playerSprite.getCoords().getY() - lastPos.getCoords().getY()) != 0){
			if ((playerSprite.getCoords().getY() - lastPos.getCoords().getY()) > 0)		// If moved from North to South
				playerSprite.getCoords().adjustY(-16);
			if ((playerSprite.getCoords().getY() - lastPos.getCoords().getY()) < 0)		// If moved from South to North
				playerSprite.getCoords().adjustY(+16);
		}
	}
}
