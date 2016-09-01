package net.tullco.jaipur;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.geometry.Point2D;
import net.tullco.jaipur.models.Clickable;
import net.tullco.jaipur.state.State;

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
							}
						}
					}
				}
			);
	}
	
}
