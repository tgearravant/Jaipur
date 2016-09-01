package net.tullco.jaipur.models;

import java.io.InputStream;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Card implements Sprite, Clickable, Renderable {

	private final int CARD_SIZE_X=70;
	private final int CARD_SIZE_Y=100;
	private final int CARD_DRAW_LOCATION_X=0;
	private final int CARD_DRAW_LOCATION_Y=0;
	private final double MOVEMENT_SECONDS=2.0;
	private final int MOVEMENT_GAIN=6;

	private String resource;
	private Image image;
	private int xLoc;
	private int yLoc;
	private int startX;
	private int startY;
	private int destinationX;
	private int destinationY;
	private long moveStartTime;
	private boolean active=false;
	
	public Card(String resource){
		this.resource=resource;
		this.xLoc=this.CARD_DRAW_LOCATION_X;
		this.yLoc=this.CARD_DRAW_LOCATION_Y;
		this.startX=this.CARD_DRAW_LOCATION_X;
		this.startY=this.CARD_DRAW_LOCATION_Y;
		this.destinationX=this.CARD_DRAW_LOCATION_X;
		this.destinationY=this.CARD_DRAW_LOCATION_Y;
	}
	
	public String getResource(){
		return this.resource;
	}
	public Image getImage(){
		if (this.image!=null)
			return this.image;
		String path="assets/img/cards/"+this.resource.toLowerCase()+".png";
		System.out.println(path);
		InputStream is = this.getClass().getClassLoader().getResourceAsStream(path);
		this.image = new Image(is);
		return this.image;
	}
	public void render(GraphicsContext gc){
		double completionPercentage = (System.currentTimeMillis()-this.moveStartTime)/(this.MOVEMENT_SECONDS*1000);
		if (completionPercentage > 1){
			this.xLoc=this.destinationX;
			this.yLoc=this.destinationY;
		}
		if (this.destinationX!=this.xLoc){
			this.xLoc = getMovementCoord(completionPercentage,this.startX,this.destinationX);
		}
		if (this.destinationY!=this.yLoc){
			this.yLoc = getMovementCoord(completionPercentage,this.startY,this.destinationY);
		}
		gc.drawImage(getImage(), xLoc, yLoc, CARD_SIZE_X, CARD_SIZE_Y);
	}
	public Rectangle2D getBoundary(){
		return new Rectangle2D(xLoc, yLoc, CARD_SIZE_X, CARD_SIZE_Y);
	}
	public void setDestinationCoordinates(int xDest, int yDest){
		this.startX=this.xLoc;
		this.startY=this.yLoc;
		this.destinationX=xDest;
		this.destinationY=yDest;
		this.moveStartTime = System.currentTimeMillis();
	}
	public int getMovementCoord(double completionPercentage, int start, int destination){
		double newLocation = ((Math.pow(completionPercentage-1, MOVEMENT_GAIN))*(start-destination))+destination;
		return (int) Math.round(newLocation);
	}
	public void click(){
		if(this.active){
			this.deactivate();
		}else{
			this.activate();
		}
	}
	public void deactivate(){
		if (!this.active)
			return;
		this.active=false;
		this.setDestinationCoordinates(destinationX-10, destinationY+10);
	}
	public void activate(){
		if(this.active)
			return;
		this.active=true;
		this.setDestinationCoordinates(destinationX+10, destinationY-10);
	}
	public boolean active(){
		return this.active;
	}
	public void printCoords(){
		System.out.println("X: "+this.xLoc + " Y: "+this.yLoc);
	}
	public void printDestinationCoords(){
		System.out.println("Dest X: "+this.destinationX + " Dest Y: "+this.destinationY);
	}
}
