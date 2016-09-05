package net.tullco.jaipur.models;

import java.util.List;

public interface CardContainer {
	public int getSize();
	public void addCards( List<Card> l);
	public void addCard(Card c);
	public List<Card> removeActiveCards();
	public Card removeCard(Card c);
}
