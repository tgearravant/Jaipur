package net.tullco.jaipur.state.transitions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.tullco.jaipur.exceptions.InvalidStateTransitionException;
import net.tullco.jaipur.models.Card;
import net.tullco.jaipur.models.Player;
import net.tullco.jaipur.models.Resource;
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
			this.resetAndThrowException("Select something!",marketSelections, handSelections);
		// if one market card is selected, take it
		else if(marketSelections.size()==1 && handSelections.size()==0 ){
			player.addCards(State.getMarket().removeActiveCards());
			System.out.println("Taking Cards");
		}
		//if only hand cards are selected, turn them in. If they are not all of the same type, throw an exception
		else if(marketSelections.size()==0 && handSelections.size()>0 ){
			if(handSelections.size()>1){
				Card firstCard = handSelections.get(0);
				for(int i=1;i<handSelections.size();i++){
					if (firstCard.getResource()!=handSelections.get(i).getResource())
						this.resetAndThrowException("Can only turn in one resource at a time.",marketSelections, handSelections);
				}
			}
			List<Card> cardsForDiscard = player.removeActiveCards();
			List<Resource> resourcesToGain = State.getResourceMarket().getResources(cardsForDiscard);
			player.addResources(resourcesToGain);
			State.getDiscard().addCards(cardsForDiscard);
		}
		// If you selected more hand cards than market cards, throw an exception 
		else if(marketSelections.size()<handSelections.size()){
			this.resetAndThrowException("Need to select more market cards.",marketSelections, handSelections);
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
			//if hand size is zero, are either taking camels or trading camels for market cards.
			if(handSelections.size()==0){
				if(allCamels&&anyCamels){
					player.addCards(State.getMarket().removeActiveCards());
				}else if(!allCamels&&!anyCamels&&player.herdSize()>=marketSelections.size()){
					int camelsNeeded = marketSelections.size();
					player.addCards(State.getMarket().removeActiveCards());
					State.getMarket().addCards(player.getCamels(camelsNeeded));
				}else{
					this.resetAndThrowException("Invalid trade!",marketSelections, handSelections);
				}
			}else{
				if(anyCamels && !allCamels)
					this.resetAndThrowException("Can't take camels and non camels.",marketSelections, handSelections);
				else{
					marketSelections=State.getMarket().removeActiveCards();
					handSelections=player.removeActiveCards();
					int camelsNeeded = marketSelections.size() - handSelections.size();
					List<Card> playerCamels = (camelsNeeded > 0?player.getCamels(camelsNeeded):new ArrayList<Card>());
					State.getMarket().addCards(handSelections);
					State.getMarket().addCards(playerCamels);
					player.addCards(marketSelections);
				}
			}
		}
		State.getMarket().replenish();
		player.resetDestinationCoordinates();
		State.getMarket().resetClickables();
		player.unsetClickables();
		if(player.getSize() > State.HAND_LIMIT){
			player.resetClickables();
			State.getMarket().unsetClickables();
			if(State.getState().equals("PLAYER1")){
				State.setState("PLAYER1DISCARD");
			}else{
				State.setState("PLAYER2DISCARD");
			}
		}else{
			if(State.getState().equals("PLAYER1")){
				State.getPlayer2().setClickables();
				State.setState("PLAYER2");
			}else{
				State.getPlayer1().setClickables();
				State.setState("PLAYER1");
			}
		}
	}

	private void resetAndThrowException(String message,List<Card> marketSelections, List<Card> handSelections) throws InvalidStateTransitionException{
		for(Card c: marketSelections)
			c.deactivate();
		for(Card c: handSelections)
			c.deactivate();
		throw new InvalidStateTransitionException(message);
	}
	
	@Override
	public List<String> getValidOldStates() {
		String[] states={"PLAYER1","PLAYER2"};
		return new ArrayList<String>(Arrays.asList(states));
	}

}
