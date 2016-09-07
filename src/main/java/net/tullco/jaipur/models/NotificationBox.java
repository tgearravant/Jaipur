package net.tullco.jaipur.models;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class NotificationBox implements Renderable {

	private static final long ERROR_TIME_MILLIS = 2000;
	private static final int NOTIFICATION_FIELD_X = 950;
	private static final int NOTIFICATION_FIELD_Y = 150;
	private static final int NOTIFICATION_FIELD_W = 300;
	
	private String errorMessage;
	private String regularMessage;
	private long errorStartTime;
	
	public NotificationBox(){
		this.regularMessage = "Initializing...";
		this.errorMessage = "Initializing...";
		this.errorStartTime=100L;
	}
	
	@Override
	public void render(GraphicsContext gc) {
		if((System.currentTimeMillis()-this.errorStartTime)<ERROR_TIME_MILLIS){
			gc.setFill(Color.RED);
			gc.fillText(this.errorMessage, NOTIFICATION_FIELD_X, NOTIFICATION_FIELD_Y, NOTIFICATION_FIELD_W);
		}
		else{
			gc.setFill(Color.BLACK);
			gc.fillText(this.regularMessage, NOTIFICATION_FIELD_X, NOTIFICATION_FIELD_Y, NOTIFICATION_FIELD_W);
		}
	}
	public void setMessage(String s){
		this.regularMessage=s;
	}
	public  void setErrorMessage(String s){
		this.errorStartTime=System.currentTimeMillis();
		this.errorMessage=s;
	}
}
