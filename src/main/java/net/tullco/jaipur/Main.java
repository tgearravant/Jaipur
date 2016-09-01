package net.tullco.jaipur;

import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;
import net.tullco.jaipur.models.Card;
import net.tullco.jaipur.models.cards.DiamondCard;
import net.tullco.jaipur.state.State;
import net.tullco.jaipur.state.transitions.Initialize;

public class Main extends Application {
	ArrayList<Card> deck = new ArrayList<Card>();
	ArrayList<Card> hand1 = new ArrayList<Card>();
	ArrayList<Card> hand2 = new ArrayList<Card>();
	ArrayList<Card> market = new ArrayList<Card>();
	ArrayList<Card> discard = new ArrayList<Card>();
	
	public static final double FPS=30;
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Jaipur");
		
		Group root = new Group();
		Scene theScene = new Scene( root );
		primaryStage.setScene( theScene );
		Canvas canvas = new Canvas(State.CANVAS_X, State.CANVAS_Y);
		root.getChildren().add(canvas);
		State.setCanvas(canvas);
		final GraphicsContext gc = canvas.getGraphicsContext2D();
		Card dc = new DiamondCard();
		dc.setDestinationCoordinates(300, 100);
	    gc.setFill( Color.RED );
	    gc.setStroke( Color.BLACK );
	    gc.setLineWidth(2);
	    final Font theFont = Font.font( "Times New Roman", FontWeight.BOLD, 48 );
	    gc.setFont( theFont );
		Timeline gameLoop = new Timeline();
		Timeline debugLoop = new Timeline();
        gameLoop.setCycleCount( Timeline.INDEFINITE );
        debugLoop.setCycleCount( Timeline.INDEFINITE );
        State.changeState(new Initialize());
        
        KeyFrame kf = new KeyFrame(
            Duration.seconds(1d/FPS),
            new EventHandler<ActionEvent>()
            {
                public void handle(ActionEvent ae)
                {
                	State.render();
                }
            });
        KeyFrame kf_debug = new KeyFrame(
                Duration.seconds(1),
                new EventHandler<ActionEvent>()
                {
                    public void handle(ActionEvent ae)
                    {
                    	for(Card c:State.getMarket().getContents())
                    		System.out.println(c.getResource());
                    }
                });
        gameLoop.getKeyFrames().add( kf );
        debugLoop.getKeyFrames().add( kf_debug );
        gameLoop.play();
        debugLoop.play();
        
        primaryStage.show();
        Controller.setScene(theScene);
	}
	
	public static void main(String[] args){
		launch(args);
	}
}
