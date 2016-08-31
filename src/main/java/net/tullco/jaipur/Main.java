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
            Duration.seconds(1d/FPS),
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
            	    gc.drawImage(new DiamondCard().getImage(), 60, 50);
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
