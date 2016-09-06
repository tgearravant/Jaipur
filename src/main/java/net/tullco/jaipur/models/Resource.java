package net.tullco.jaipur.models;

import java.io.InputStream;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Resource implements Renderable, Sprite{
	
	private final static double MOVEMENT_SECONDS=3.0;
	private final static int MOVEMENT_GAIN=2;
	private final static int RESOURCE_SIZE_X=50;
	private final static int RESOURCE_SIZE_Y=50;

	private Image image;
	
	private String type;
	private int value;
	private int xLoc;
	private int yLoc;
	private int startX;
	private int startY;
	private int destinationX;
	private int destinationY;
	private long moveStartTime;
	
	public Resource(String type, int value){
		this.type=type;
		this.value=value;
		this.xLoc=0;
		this.yLoc=0;
		this.startX=0;
		this.startY=0;
		this.destinationX=0;
		this.destinationY=0;
	}
	public int getValue(){
		return this.value;
	}
	public String getType(){
		return this.type;
	}
	@Override
	public void render(GraphicsContext gc){
		updateXY();
		gc.drawImage(getImage(), xLoc, yLoc, RESOURCE_SIZE_X, RESOURCE_SIZE_Y);
	}
	public Image getImage(){
		if (this.image!=null)
			return this.image;
		String path="assets/img/cards/"+this.type.toLowerCase()+".png";
		System.out.println(path);
		InputStream is = this.getClass().getClassLoader().getResourceAsStream(path);
		this.image = new Image(is);
		return this.image;
	}
	public void updateXY(){
		double completionPercentage = getMovementCompletionPercentage();
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
	}
	public double getMovementCompletionPercentage(){
		return (System.currentTimeMillis()-this.moveStartTime)/(Resource.MOVEMENT_SECONDS*1000);
	}
	public int getMovementCoord(double completionPercentage, int start, int destination){
		double newLocation = ((Math.pow(completionPercentage-1, MOVEMENT_GAIN))*(start-destination))+destination;
		return (int) Math.round(newLocation);
	}
	@Override
	public void setDestinationCoordinates(int xDest, int yDest){
		updateXY();
		this.startX=this.xLoc;
		this.startY=this.yLoc;
		this.destinationX=xDest;
		this.destinationY=yDest;
		this.moveStartTime = System.currentTimeMillis();
	}
}