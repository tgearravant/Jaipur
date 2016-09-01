package net.tullco.jaipur.models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import net.tullco.jaipur.models.cards.CamelCard;
import net.tullco.jaipur.state.State;

public class Market implements Renderable {
	private static final int MARKET_SIZE=5;
	private static final int STARTING_CAMEL_CARDS=3;
	public static final int MARKET_X=10;
	public static final int MARKET_Y=300;
	public static final int MARKET_SIZE_X=500;
	
	private List<Card> contents;
	private boolean camelsActive=false;

	public Market(){
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
	}
	public void replenish(){
		while(this.contents.size() < MARKET_SIZE)
			this.contents.add(State.getDeck().remove(0));
		resetCardLocations();
	}
	public void addCards(List<Card> cards){
		this.contents.addAll(cards);
	}
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
			if(!State.getClickables().contains(c))
				State.addClickable(c);
	}
	public void unsetClickables(){
		for(Card c : this.contents){
			if (State.getClickables().contains(c))
				State.getClickables().remove(c);
		}
	}
	public void resetClickables(){
		this.unsetClickables();
		this.setClickables();
	}
	public boolean contains(Card c){
		return this.contents.contains(c);
	}
}
