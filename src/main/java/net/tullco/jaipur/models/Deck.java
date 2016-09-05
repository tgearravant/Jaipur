package net.tullco.jaipur.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import net.tullco.jaipur.models.cards.CamelCard;
import net.tullco.jaipur.models.cards.ClothCard;
import net.tullco.jaipur.models.cards.DiamondCard;
import net.tullco.jaipur.models.cards.GoldCard;
import net.tullco.jaipur.models.cards.LeatherCard;
import net.tullco.jaipur.models.cards.SilverCard;
import net.tullco.jaipur.models.cards.SpiceCard;

public class Deck implements CardContainer, Renderable{

	private static final int DECK_LOCATION_X=800;
	private static final int DECK_LOCATION_Y=400;
	
	private static final int DIAMOND_CARDS=6;
	private static final int GOLD_CARDS=6;
	private static final int SILVER_CARDS=6;
	private static final int CLOTH_CARDS=8;
	private static final int SPICE_CARDS=6;
	private static final int LEATHER_CARDS=6;
	private static final int DECK_CAMEL_CARDS=8;
	
	private List<Card> deck;
	
	public Deck(){
		this.deck=new ArrayList<Card>();
		for(int i=0; i < DIAMOND_CARDS; i++)
			this.deck.add(new DiamondCard());
		for(int i=0; i < GOLD_CARDS; i++)
			this.deck.add(new GoldCard());
		for(int i=0; i < SILVER_CARDS; i++)
			this.deck.add(new SilverCard());
		for(int i=0; i < CLOTH_CARDS; i++)
			this.deck.add(new ClothCard());
		for(int i=0; i < SPICE_CARDS; i++)
			this.deck.add(new SpiceCard());
		for(int i=0; i < LEATHER_CARDS; i++)
			this.deck.add(new LeatherCard());
		for(int i=0; i < DECK_CAMEL_CARDS; i++)
			this.deck.add(new CamelCard());
		Collections.shuffle(this.deck);
		for(Card c : this.deck){
			c.setHidden(true);
			c.setDestinationCoordinates(DECK_LOCATION_X, DECK_LOCATION_Y);
		}
	}
	
	public Card drawCard(){
		Card c = this.deck.remove(0);
		c.setHidden(false);
		return c;
	}
	
	@Override
	public int getSize() {
		return this.deck.size();
	}

	@Override
	public void addCards(List<Card> cards) {
		this.deck.addAll(cards);
	}

	@Override
	public void addCard(Card c) {
		this.deck.add(c);
	}

	@Override
	public List<Card> removeActiveCards() {
		return new ArrayList<Card>();
	}

	@Override
	public Card removeCard(Card c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void render(GraphicsContext gc) {
		if (this.getSize()==0)
			return;
		else
			gc.drawImage(this.getImage(), Deck.DECK_LOCATION_X, Deck.DECK_LOCATION_Y, Card.CARD_SIZE_X, Card.CARD_SIZE_Y);
	}
	public Image getImage(){
		return Card.getCardBack();
	}

}
