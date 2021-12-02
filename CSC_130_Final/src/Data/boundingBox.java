package Data;

/* This will create a bounding box. You can either create a static bounding box or you can base it off of a spriteInfo object */

public class boundingBox {
	// Fields
	private spriteInfo spriteData;
	private int x1, x2, y1, y2;
	
	
	// Methods
	
	/* Constructor using definite Boundaries */
	public boundingBox(int _x1, int _x2, int _y1, int _y2){
		x1 = _x1;
		x2 = _x2;
		y1 = _y1;
		y2 = _y2;		
	}
	
	/* @Overload constructor if using sprite. Automatically sets the box size to 128x128 relative to sprite position (top left). */
	public boundingBox(spriteInfo _sprite){	
		spriteData = _sprite;
		x1 = spriteData.getCoords().getX();
		x2 = spriteData.getCoords().getX() + 128;
		y1 = spriteData.getCoords().getY();
		y2 = spriteData.getCoords().getY() + 128;
	}
	
	/* @Overload sprite constructor with adjustments for each boundary relative to the origin (top left) */
	public boundingBox(spriteInfo _sprite, int adjustX1, int adjustX2, int adjustY1, int adjustY2){	
		spriteData = _sprite;
		x1 = spriteData.getCoords().getX() + adjustX1;
		x2 = spriteData.getCoords().getX() + adjustX2;
		y1 = spriteData.getCoords().getY() + adjustY1;
		y2 = spriteData.getCoords().getY() + adjustY2;
	}
	
	public String toString(){
		return "x1 = " + x1 + " x2 = " + x2 + " y1 = " + y1 + " y2 = " + y2;
	}
	
	/* Getters */
	public int getX1(){
		return x1;
	}
	
	public int getX2(){
		return x2;
	}
	
	public int getY1(){
		return y1;
	}
	
	public int getY2(){
		return y2;
	}
	
	/* Setters */
	public void setX1(int val){
		x1 = val;
	}
	
	public void setX2(int val){
		x2 = val;
	}
	
	public void setY1(int val){
		y1 = val;
	}
	
	public void setY2(int val){
		y2 = val;
	}

}



/* Possible way to adjust bounding boxes during gameplay (incomplete) */

///* Adjusts one of the sides of the bounding box by a percentage relative to the sprite location (top left corner) */
///* Usage: box1.adjust(x1, 0.2) */
//public void adjust(String axis, float adjust){
//	axis = axis.toLowerCase();
//	if (adjust >= 0 && adjust <= 1){
//		if (axis == "x1" || axis == "x2" || axis == "y1" || axis == "y2"){
//			switch (axis){
//			case "x1":
//				x1 = Math.round(spriteData.getCoords().getX() + (spriteData.getCoords().getX() * adjust));
//				break;
//			case "x2":
//				x2 = Math.round(spriteData.getCoords().getX() + (spriteData.getCoords().getX() * adjust));
//				break;
//			case "y1":
//				y1 = Math.round(spriteData.getCoords().getY() + (spriteData.getCoords().getY() * adjust));
//				break;
//			case "y2":
//				y2 = Math.round(spriteData.getCoords().getY() + (spriteData.getCoords().getY() * adjust));
//				break;
//			}	
//		}
//		else
//			System.out.println("Axis parameter not recognized. Please enter x1, x2, y1, or y2.");
//	}	
//
//	else
//		System.out.println("Adjustment parameter out of bounds! Make sure it is a decimal between 0 and 1.");
//}
