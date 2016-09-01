package net.tullco.jaipur.state.transitions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.tullco.jaipur.exceptions.InvalidStateTransitionException;
import net.tullco.jaipur.models.Card;
import net.tullco.jaipur.models.Player;
import net.tullco.jaipur.models.cards.CamelCard;
import net.tullco.jaipur.state.State;

public class TakeAction implements StateTransition {

	public TakeAction(){}
	
	@Override
	public void update() throws InvalidStateTransitionException {
		List<Card> marketSelections=new ArrayList<Card>();
		List<Card> handSelections=new ArrayList<Card>();
		Player player;

		// Decide the player
		if(State.getState().equals("PLAYER1"))
			player=State.getPlayer1();
		else
			player=State.getPlayer2();
		
		//get selections
		for(Card c : State.getMarket().getContents())
			if(c.active()){
				marketSelections.add(c);
			}
		for(Card c : player.getHand())
			if(c.active()){
				handSelections.add(c);
			}
		//if nothing is selected, throw an invalid transition exception
		if(marketSelections.size()==0 && handSelections.size()==0)
			throw new InvalidStateTransitionException();
		// if one market card is selected, take it
		else if(marketSelections.size()==1 && handSelections.size()==0 ){
			player.addCardsToHand(State.getMarket().removeActiveCards());
			System.out.println("Taking Cards");
		}
		//if only hand cards are selected, turn them in. If they are not all of the same type, throw an exception
		else if(marketSelections.size()==0 && handSelections.size()>0 ){
			if(handSelections.size()>1){
				Card firstCard = handSelections.get(0);
				for(int i=1;i<handSelections.size();i++){
					if (firstCard.getResource()!=handSelections.get(i).getResource())
						throw new InvalidStateTransitionException();
				}
			}
			State.getDiscard().addAll(player.removeActiveCards());
		}
		// If you selected more hand cards than market cards, throw an exception 
		else if(marketSelections.size()<handSelections.size()){
			throw new InvalidStateTransitionException();
		}
		// Otherwise, we're trying to trade cards or take camels, so let's figure out which.
		else{
			//Lets figure out camels first
			boolean allCamels=true;
			boolean anyCamels=false;
			for(Card c:marketSelections){
				if(!(c instanceof CamelCard)){
					allCamels=false;
				}
				if((c instanceof CamelCard)){
					anyCamels=true;
				}
			}
			//if hand size is zero, we might be taking camels. Let's figure that out...
			if(handSelections.size()==0){
				if(allCamels&&anyCamels){
					player.addCardsToHand(State.getMarket().removeActiveCards());
				}else if(!allCamels&&!anyCamels&&player.herdSize()>=marketSelections.size()){
					int camelsNeeded = marketSelections.size();
					player.addCardsToHand(State.getMarket().removeActiveCards());
					State.getMarket().addCards(player.getCamels(camelsNeeded));
				}
			}
		}
		State.getMarket().replenish();
		player.resetDestinationCoordinates();
		State.getMarket().resetClickables();
		player.unsetClickables();
		if(State.getState().equals("PLAYER1")){
			State.getPlayer2().setClickables();
			State.setState("PLAYER2");
		}else{
			State.getPlayer1().setClickables();
			State.setState("PLAYER1");
		}
	}

	@Override
	public List<String> getValidOldStates() {
		String[] states={"PLAYER1","PLAYER2"};
		return new ArrayList<String>(Arrays.asList(states));
	}

}
