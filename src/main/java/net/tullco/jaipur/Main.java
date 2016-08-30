package net.tullco.jaipur;

import java.util.ArrayList;
import java.util.Collections;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;
import net.tullco.jaipur.models.Card;
import net.tullco.jaipur.models.Player;

public class Main extends Application {
	ArrayList<Card> deck = new ArrayList<Card>();
	ArrayList<Card> hand1 = new ArrayList<Card>();
	ArrayList<Card> hand2 = new ArrayList<Card>();
	ArrayList<Card> market = new ArrayList<Card>();
	ArrayList<Card> discard = new ArrayList<Card>();
	
	private void setUp(){
		deck.add(new Card("Camel"));
		for(int i=0; i<6; i++)
			deck.add(new Card("Gems"));
		for(int i=0; i<6; i++)
			deck.add(new Card("Gold"));
		for(int i=0; i<6; i++)
			deck.add(new Card("Silver"));
		for(int i=0; i<8; i++)
			deck.add(new Card("Cloth"));
		for(int i=0; i<8; i++)
			deck.add(new Card("Spices"));
		for(int i=0; i<10; i++)
			deck.add(new Card("Leather"));
		Collections.shuffle(deck);
		market.add(new Card("Camel"));
		market.add(new Card("Camel"));
		market.add(new Card("Camel"));
		market.add(deck.remove(0));
		market.add(deck.remove(0));
		Player player1 = new Player();
		Player player2 = new Player();
		for(int i=0; i<5;i++){
			player1.addCardToHand(deck.remove(0));
			player2.addCardToHand(deck.remove(0));
		}
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		setUp();
		primaryStage.setTitle("Jaipur");
		
		Group root = new Group();
		Scene theScene = new Scene( root );
		primaryStage.setScene( theScene );
		Canvas canvas = new Canvas( 400, 200 );
		root.getChildren().add(canvas);
		final GraphicsContext gc = canvas.getGraphicsContext2D();
	    gc.setFill( Color.RED );
	    gc.setStroke( Color.BLACK );
	    gc.setLineWidth(2);
	    final Font theFont = Font.font( "Times New Roman", FontWeight.BOLD, 48 );
	    gc.setFont( theFont );
		Timeline gameLoop = new Timeline();
        gameLoop.setCycleCount( Timeline.INDEFINITE );
        
        final long timeStart = System.currentTimeMillis();
        
        KeyFrame kf = new KeyFrame(
            Duration.seconds(1d/30),
            new EventHandler<ActionEvent>()
            {
                public void handle(ActionEvent ae)
                {
                    double t = (System.currentTimeMillis() - timeStart) / 1000.0; 

                    // Clear the canvas
                    gc.clearRect(0, 0, 512,512);
                    
                    // background image clears canvas
            	    gc.setFont( theFont );
            	    gc.fillText( "Hello, World!", 60, t*100 );
            	    gc.strokeText( "Hello, World!", 60, t*100 );
                }
            });
        
        gameLoop.getKeyFrames().add( kf );
        gameLoop.play();
        
        primaryStage.show();
	}
	
	public static void main(String[] args){
		launch(args);
	}
}
