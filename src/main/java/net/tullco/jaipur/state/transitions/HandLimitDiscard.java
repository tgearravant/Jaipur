package net.tullco.jaipur.state.transitions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.tullco.jaipur.exceptions.InvalidStateTransitionException;
import net.tullco.jaipur.models.Card;
import net.tullco.jaipur.models.Player;
import net.tullco.jaipur.state.State;

public class HandLimitDiscard implements StateTransition {

	@Override
	public void update() throws InvalidStateTransitionException {
		Player p;
		if(State.getState().equals("PLAYER1DISCARD"))
			p=State.getPlayer1();
		else
			p=State.getPlayer2();
		ArrayList<Card> playerSelected = new ArrayList<Card>();
		for(Card c: p.getHand()){
			if(c.active())
				playerSelected.add(c);
		}
		
		if(p.getSize()-playerSelected.size()!= State.HAND_LIMIT){
			System.out.println("Hand Size: "+p.getSize());
			System.out.println("Selected: "+playerSelected.size());
			throw new InvalidStateTransitionException();
		}
		else{
			State.getDiscard().addCards(p.removeActiveCards());
			State.getMarket().setClickables();
			p.unsetClickables();
			if(State.getState().equals("PLAYER1DISCARD")){
				State.setState("PLAYER2");
				State.getPlayer2().setClickables();
			}
			else{
				State.setState("PLAYER1");
				State.getPlayer1().setClickables();
			}
		}
	}
	@Override
	public List<String> getValidOldStates() {
		String[] states={"PLAYER1DISCARD","PLAYER2DISCARD"};
		return new ArrayList<String>(Arrays.asList(states));
	}
}
