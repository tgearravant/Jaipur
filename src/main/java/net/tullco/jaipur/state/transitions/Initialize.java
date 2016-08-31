package net.tullco.jaipur.state.transitions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import net.tullco.jaipur.models.Card;
import net.tullco.jaipur.models.Player;
import net.tullco.jaipur.models.cards.CamelCard;
import net.tullco.jaipur.models.cards.ClothCard;
import net.tullco.jaipur.models.cards.DiamondCard;
import net.tullco.jaipur.models.cards.GoldCard;
import net.tullco.jaipur.models.cards.LeatherCard;
import net.tullco.jaipur.models.cards.SilverCard;
import net.tullco.jaipur.models.cards.SpiceCard;
import net.tullco.jaipur.state.State;

public class Initialize implements StateTransition {
	public Initialize(){}

	public void update() {
		ArrayList<Card> deck = new ArrayList<Card>();
		for(int i=0; i<6; i++)
			deck.add(new DiamondCard());
		for(int i=0; i<6; i++)
			deck.add(new GoldCard());
		for(int i=0; i<6; i++)
			deck.add(new SilverCard());
		for(int i=0; i<8; i++)
			deck.add(new ClothCard());
		for(int i=0; i<8; i++)
			deck.add(new SpiceCard());
		for(int i=0; i<10; i++)
			deck.add(new LeatherCard());
		for(int i=0; i<8; i++)
			deck.add(new CamelCard());
		Collections.shuffle(deck);
		ArrayList<Card> market = new ArrayList<Card>();
		market.add(new CamelCard());
		market.add(new CamelCard());
		market.add(new CamelCard());
		market.add(deck.remove(0));
		market.add(deck.remove(0));
		Player player1 = new Player();
		Player player2 = new Player();
		for(int i=0; i<5;i++){
			player1.addCardToHand(deck.remove(0));
			player2.addCardToHand(deck.remove(0));
		}
		State.setPlayer1(player1);
		State.setPlayer2(player2);
		State.setMarket(market);
		State.setDeck(deck);
	}
	public String getNewState(){
		return "PLAYER1";
	}
	public List<String> getValidOldStates(){
		String[] states={"INIT","FINISH"};
		return new ArrayList<String>(Arrays.asList(states));
	}
}
