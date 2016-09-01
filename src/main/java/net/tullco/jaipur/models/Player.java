package net.tullco.jaipur.models;

import java.util.ArrayList;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import net.tullco.jaipur.state.State;

public class Player implements Sprite {
	private ArrayList<Card> hand;
	private ArrayList<Resource> gatheredResources;
	@SuppressWarnings("unused")
	private int xLocation;
	@SuppressWarnings("unused")
	private int yLocation;
	public Player(){
		this.hand = new ArrayList<Card>();
		this.gatheredResources = new ArrayList<Resource>();
	}
	public void addCardToHand(Card c){
		hand.add(c);
	}
	public void addReource(Resource r){
		this.gatheredResources.add(r);
	}
	@Override
	public void render(GraphicsContext gc) {
		for (Card c: hand){
			c.render(gc);
		}
	}
	@Override
	public Rectangle2D getBoundary() {
		//TODO I should do this at some point. ;)
		return null;
	}
	@Override
	public void setDestinationCoordinates(int xDest, int yDest) {
		xLocation=xDest;
		yLocation=yDest;
		for(int i=0;i<hand.size();i++){
			Card c = hand.get(i);
			int xCoord = (xDest+(((State.CANVAS_X-10)-xDest)/hand.size())*i);
			c.setDestinationCoordinates(xCoord, yDest);
		}
	}
	public void setClickables(){
		for(Card c : hand)
			State.addClickable(c);
	}
	public void unsetClickables(){
		for(Card c : hand){
			if (State.getClickables().contains(c))
				State.getClickables().remove(c);
		}
	}
}
