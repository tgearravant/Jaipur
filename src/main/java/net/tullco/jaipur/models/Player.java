package net.tullco.jaipur.models;

import java.util.ArrayList;

public class Player {
	private ArrayList<Card> hand;
	private ArrayList<Resource> gatheredResources;
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
}
