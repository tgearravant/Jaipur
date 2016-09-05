package net.tullco.jaipur.state.transitions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.animation.Timeline;
import net.tullco.jaipur.models.Deck;
import net.tullco.jaipur.models.Discard;
import net.tullco.jaipur.models.Market;
import net.tullco.jaipur.models.Player;
import net.tullco.jaipur.state.State;

public class Initialize implements StateTransition {
	public Initialize(){}

	public void update() {
		Deck deck = new Deck();
		Market market = new Market(deck);
		Player player1 = new Player();
		Player player2 = new Player();
		Discard discard = new Discard();
		for(int i=0; i<5;i++){
			player1.addCard(deck.drawCard());
			player2.addCard(deck.drawCard());
		}
		player1.setDestinationCoordinates(State.HAND_1_X, State.HAND_1_Y);
		player2.setDestinationCoordinates(State.HAND_2_X, State.HAND_2_Y);
		State.addRenderable(player1);
		player1.setClickables();
		State.addRenderable(player2);
		State.addRenderable(market);
		State.setPlayer1(player1);
		State.setPlayer2(player2);
		State.setMarket(market);
		State.setDeck(deck);
		State.addRenderable(deck);
		State.setDiscard(discard);
		State.addRenderable(discard);
		Timeline gameLoop = new Timeline();
        gameLoop.setCycleCount( Timeline.INDEFINITE );
        State.getMarket().replenish();
		State.getMarket().setClickables();
        State.setState("PLAYER1");
	}
	public List<String> getValidOldStates(){
		String[] states={"INIT","FINISH"};
		return new ArrayList<String>(Arrays.asList(states));
	}
}
