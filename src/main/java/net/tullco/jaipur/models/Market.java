package net.tullco.jaipur.models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import net.tullco.jaipur.models.cards.CamelCard;

public class Market implements Renderable, CardContainer {
	private static final int MARKET_SIZE=5;
	private static final int STARTING_CAMEL_CARDS=3;
	public static final int MARKET_X=10;
	public static final int MARKET_Y=300;
	public static final int MARKET_SIZE_X=500;
	
	private List<Card> contents;
	private boolean camelsActive=false;
	private Deck deck;

	public Market(Deck d){
		this.deck=d;
		this.contents=new ArrayList<Card>();
		this.setUp();
	}
	public List<Card> getContents(){
		return this.contents;
	}
	
	@Override
	public void render(GraphicsContext gc) {
		for (Card c: contents){
			c.render(gc);
		}
	}
	public void setUp(){
		for(int i = 0; i < Market.STARTING_CAMEL_CARDS; i++)
			this.contents.add(new CamelCard());
		replenish();
	}
	public void replenish(){
		while(this.contents.size() < MARKET_SIZE)
			this.contents.add(deck.drawCard());
		this.contents.sort(null);
		resetCardLocations();
	}
	@Override
	public void addCards(List<Card> cards){
		for(Card c: cards)
			this.addCard(c);
	}
	@Override
	public List<Card> removeActiveCards(){
		Iterator<Card> i = this.contents.iterator();
		ArrayList<Card> removedCards = new ArrayList<Card>();
		while(i.hasNext()){
			Card c = i.next();
			if(c.active()){
				c.deactivate();
				removedCards.add(c);
			}
		}
		this.contents.removeAll(removedCards);
		return removedCards;
	}
	public void homogenizeCamels(){
		ArrayList<CamelCard> camels = new ArrayList<CamelCard>();
		
		//get a list of the camels
		for(Card c : this.contents)
			if(c instanceof CamelCard)
				camels.add((CamelCard) c);
		
		//figure out if we need to homogenize the camels
		boolean homogenize=false;
		for(CamelCard c : camels){
			if(this.camelsActive && !c.active()){
				homogenize=true;
			}
			if(!this.camelsActive && c.active()){
				homogenize=true;
			}
		}
		//if we need to homogenize, then lets do it! Flip the camel boolean while we're at it.
		if(homogenize){
			this.camelsActive=!this.camelsActive;
			for(CamelCard c:camels){
				if(!this.camelsActive)
					c.deactivate();
				if(this.camelsActive)
					c.activate();
			}
		}
	}
	public void resetCardLocations(){
		for (int i=0; i < this.contents.size(); i++){
			this.contents.get(i).setDestinationCoordinates(MARKET_X + i * ((MARKET_SIZE_X)/MARKET_SIZE), MARKET_Y);
		}
	}
	public void setClickables(){
		for(Card c : this.contents)
			c.setClickable(true);
	}
	public void unsetClickables(){
		for(Card c : this.contents){
			c.setClickable(false);
		}
	}
	public void resetClickables(){
		this.unsetClickables();
		this.setClickables();
	}
	public boolean contains(Card c){
		return this.contents.contains(c);
	}
	@Override
	public int getSize() {
		return this.contents.size();
	}
	@Override
	public void addCard(Card c) {
		c.setClickable(true);
		this.contents.add(c);
	}
	@Override
	public Card removeCard(Card c) {
		if(this.contents.remove(c))
			return c;
		else
			return null;
	}
}
