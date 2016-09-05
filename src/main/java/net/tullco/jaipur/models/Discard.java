package net.tullco.jaipur.models;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;

public class Discard implements CardContainer, Renderable {
	private static final int DISCARD_LOCATION_X=600;
	private static final int DISCARD_LOCATION_Y=200;

	private ArrayList<Card> discard;
	
	public Discard(){
		this.discard = new ArrayList<Card>();
	}
	@Override
	public void render(GraphicsContext gc) {
		if(this.discard.isEmpty())
			return;
		else if(this.getSize()==1){
			Card c = this.discard.get(0);
			c.setHidden(false);
			c.render(gc);
		}
		else{
			Card c0 = this.discard.get(this.discard.size()-1);
			c0.setHidden(false);
			c0.render(gc);
			Card c1 = this.discard.get(this.discard.size()-2);
			if(!c0.atDestination()){
				c1.setHidden(false);
				c1.render(gc);
			}else{
				c1.setHidden(true);
			}
		}
	}

	@Override
	public int getSize() {
		return this.discard.size();
	}

	@Override
	public void addCards(List<Card> l) {
		for(Card c:l){
			addCard(c);
		}
	}

	@Override
	public void addCard(Card c) {
		c.setDestinationCoordinates(DISCARD_LOCATION_X, DISCARD_LOCATION_Y);
		c.setHidden(true);
		c.setClickable(false);
		c.deactivate();
	}

	@Override
	public List<Card> removeActiveCards() {
		return null;
	}

	@Override
	public Card removeCard(Card c) {
		if (this.discard.remove(c))
			return c;
		else
			return null;
			
	}

}
