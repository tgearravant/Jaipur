package net.tullco.jaipur;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.geometry.Point2D;
import net.tullco.jaipur.exceptions.InvalidStateTransitionException;
import net.tullco.jaipur.models.Card;
import net.tullco.jaipur.models.Clickable;
import net.tullco.jaipur.state.State;
import net.tullco.jaipur.state.transitions.HandLimitDiscard;
import net.tullco.jaipur.state.transitions.TakeAction;

public class Controller {
	private static Scene scene;
	public static void setScene(Scene s){
		Controller.scene=s;
		scene.setOnMouseClicked(
				new EventHandler<MouseEvent>(){
					public void handle(MouseEvent e){
						Point2D point = new Point2D(e.getX(),e.getY());
						for (Clickable c: State.getClickables()){
							if(c.getBoundary().contains(point)){
								c.click();
								if(c instanceof Card && State.getMarket().contains((Card) c)){
									State.getMarket().homogenizeCamels();
								}
							}
						}
					}
				}
			);
		scene.setOnKeyReleased(
				new EventHandler<KeyEvent>(){
					public void handle(KeyEvent e){
						//Space means we're trying to do stuff! Let's do it! :)
						if(e.getText().equals(" "))
							try {
								String currentState=State.getState();
								if(currentState.equals("PLAYER1")||currentState.equals("PLAYER2"))
									State.changeState(new TakeAction());
								if(currentState.equals("PLAYER1DISCARD")||currentState.equals("PLAYER2DISCARD"))
									State.changeState(new HandLimitDiscard());
							} catch (InvalidStateTransitionException e1) {
								e1.printStackTrace();
							}
					}
				}
			);
	}
	
}
