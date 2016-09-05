package net.tullco.jaipur.models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import net.tullco.jaipur.models.cards.CamelCard;

public class Player implements Sprite, Renderable, CardContainer {
	
	private final static int HERD_BUFFER_X = 20;
	private final static int HAND_SIZE_X=600;
	
	private ArrayList<Card> hand;
	private ArrayList<CamelCard> herd;
	private ArrayList<Resource> gatheredResources;
	private Card dummyCamel;
	private int xLocation;
	private int yLocation;
	
	public Player(){
		this.hand = new ArrayList<Card>();
		this.herd = new ArrayList<CamelCard>();
		this.gatheredResources = new ArrayList<Resource>();
		this.dummyCamel = new CamelCard();
	}
	@Override
	public void addCard(Card c){
		if(c instanceof CamelCard){
			herd.add((CamelCard) c);
			c.setHidden(true);
			c.setDestinationCoordinates(getHerdX(), getHerdY() );
			c.setClickable(false);
		}
		else{
			hand.add(c);
			hand.sort(null);
		}
	}
	@Override
	public void addCards(List<Card> cards){
		for(Card c: cards)
			addCard(c);
	}
	public List<Card> getHand(){
		return this.hand;
	}
	@Override
	public int getSize() {
		return this.hand.size();
	}
	public int herdSize(){
		return this.herd.size();
	}
	public List<Card> getCamels(int num) throws IndexOutOfBoundsException{
		if(num > this.herdSize())
			throw new IndexOutOfBoundsException();
		List<Card> removedCamels = new ArrayList<Card>();
		for(int i=0;i<num;i++){
			Card c = this.herd.remove(0);
			c.setHidden(false);
			removedCamels.add(c);
		}
		return removedCamels;
	}
	public void addReource(Resource r){
		this.gatheredResources.add(r);
	}
	public int getHerdX(){
		return HERD_BUFFER_X+HAND_SIZE_X;
	}
	public int getHerdY(){
		return this.yLocation;
	}
	@Override
	public void render(GraphicsContext gc) {
		for (Card c: hand){
			c.render(gc);
		}
		this.dummyCamel.render(gc);
		Rectangle2D pic=this.dummyCamel.getBoundary();
		int x = (int) (pic.getMaxX()+HERD_BUFFER_X);
		int y = (int) ((pic.getMaxY()+pic.getMinY())/2);
		gc.strokeText(Integer.toString(this.herdSize()), x, y);
		for(Card c:herd)
			c.render(gc);
	}
	public Rectangle2D getBoundary() {
		//TODO I should do this at some point. ;)
		return null;
	}
	@Override
	public void setDestinationCoordinates(int xDest, int yDest) {
		this.dummyCamel.setDestinationCoordinates(HERD_BUFFER_X+HAND_SIZE_X, yDest);
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
			c.setClickable(true);
	}
	public void unsetClickables(){
		for(Card c : hand){
			c.setClickable(false);
		}
	}
	public void resetClickables(){
		this.unsetClickables();
		this.setClickables();
	}
	@Override
	public Card removeCard(Card c){
		if(this.hand.remove(c))
			return c;
		else
			return null;
	}
	@Override
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
