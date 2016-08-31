package net.tullco.jaipur.state;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.tullco.jaipur.exceptions.InvalidStateTransitionException;
import net.tullco.jaipur.models.Card;
import net.tullco.jaipur.models.Player;
import net.tullco.jaipur.state.transitions.StateTransition;

public class State {
	private static String state = "INIT";
	private static List<String> validStates;
	private static ArrayList<Card> deck = new ArrayList<Card>();
	private static Player player1;
	private static Player player2;
	private static ArrayList<Card> market = new ArrayList<Card>();
	private static ArrayList<Card> discard = new ArrayList<Card>();
	public static void changeState(StateTransition t) throws InvalidStateTransitionException{
		if(t.getValidOldStates().contains(State.state))
			throw new InvalidStateTransitionException();
		t.update();
		
	}
	public static ArrayList<Card> getDeck() {
		return deck;
	}
	public static void setDeck(ArrayList<Card> deck) {
		State.deck = deck;
	}
	public static Player getPlayer1() {
		return player1;
	}
	public static void setPlayer1(Player player1) {
		State.player1 = player1;
	}
	public static Player getPlayer2() {
		return player2;
	}
	public static void setPlayer2(Player player2) {
		State.player2 = player2;
	}
	public static ArrayList<Card> getMarket() {
		return market;
	}
	public static void setMarket(ArrayList<Card> market) {
		State.market = market;
	}
	public static ArrayList<Card> getDiscard() {
		return discard;
	}
	public static void setDiscard(ArrayList<Card> discard) {
		State.discard = discard;
	}
	public static List<String> getValidStates(){
		if(State.validStates!=null)
			return State.validStates;
		String[] array = {"INIT","PLAYER1","PLAYER2","SCORE","FINISH","STOP"};
		State.validStates = new ArrayList<String>(Arrays.asList(array));
		return State.validStates;
	}
}
