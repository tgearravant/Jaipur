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
						if(e.getText().equals(" "))
							try {
								State.changeState(new TakeAction());
							} catch (InvalidStateTransitionException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
					}
				}
			);
	}
	
}
