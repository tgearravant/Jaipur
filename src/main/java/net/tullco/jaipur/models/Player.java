package net.tullco.jaipur.models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import net.tullco.jaipur.models.cards.CamelCard;
import net.tullco.jaipur.state.State;

public class Player implements Sprite, Renderable {
	
	private final static int  HAND_SIZE_X=600;
	
	private ArrayList<Card> hand;
	private ArrayList<CamelCard> herd;
	private ArrayList<Resource> gatheredResources;
	private int xLocation;
	private int yLocation;
	public Player(){
		this.hand = new ArrayList<Card>();
		this.herd = new ArrayList<CamelCard>();
		this.gatheredResources = new ArrayList<Resource>();
	}
	public void addCardToHand(Card c){
		if(c instanceof CamelCard){
			herd.add((CamelCard) c);
		}
		else{
			hand.add(c);
		}
	}
	public void addCardsToHand(List<Card> cards){
		for(Card c: cards)
			addCardToHand(c);
	}
	public List<Card> getHand(){
		return this.hand;
	}
	public int herdSize(){
		return this.herd.size();
	}
	public List<CamelCard> getCamels(int num) throws IndexOutOfBoundsException{
		if(num > this.herdSize())
			throw new IndexOutOfBoundsException();
		List<CamelCard> removedCamels = new ArrayList<CamelCard>();
		for(int i=0;i<num;i++)
			removedCamels.add(this.herd.remove(0));
		return removedCamels;
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
			int xCoord = (xDest+((HAND_SIZE_X-xDest)/hand.size())*i);
			c.setDestinationCoordinates(xCoord, yDest);
		}
	}
	public void resetDestinationCoordinates(){
		setDestinationCoordinates(this.xLocation,this.yLocation);
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
	public void resetClickables(){
		this.unsetClickables();
		this.setClickables();
	}
	public List<Card> removeActiveCards(){
		Iterator<Card> i = this.hand.iterator();
		ArrayList<Card> removedCards = new ArrayList<Card>();
		while(i.hasNext()){
			Card c = i.next();
			if(c.active()){
				c.deactivate();
				removedCards.add(c);
			}
		}
		this.hand.removeAll(removedCards);
		return removedCards;
	}
}
