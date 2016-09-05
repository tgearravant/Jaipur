package net.tullco.jaipur.state;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.scene.canvas.Canvas;
import net.tullco.jaipur.exceptions.InvalidStateTransitionException;
import net.tullco.jaipur.models.Player;
import net.tullco.jaipur.models.Renderable;
import net.tullco.jaipur.models.Clickable;
import net.tullco.jaipur.models.Deck;
import net.tullco.jaipur.models.Discard;
import net.tullco.jaipur.models.Market;
import net.tullco.jaipur.state.transitions.StateTransition;

public class State {
	public static int CANVAS_X=1280;
	public static int CANVAS_Y=720;
	public static int HAND_1_X=10;
	public static int HAND_1_Y=10;
	public static int HAND_2_X=10;
	public static int HAND_2_Y=600;
	
	private static String state = "INIT";
	private static String oldState;
	private static Canvas canvas;
	private static List<Renderable> renderables=new ArrayList<Renderable>();
	private static List<Clickable> clickables=new ArrayList<Clickable>();
	private static List<String> validStates;
	private static Deck deck;
	private static Player player1;
	private static Player player2;
	private static Market market;
	private static Discard discard;
	public static void changeState(StateTransition t) throws InvalidStateTransitionException{
		if(!t.getValidOldStates().contains(State.state))
			throw new InvalidStateTransitionException();
		t.update();
	}
	public static void setState(String state){
		State.oldState = State.state;
		State.state=state;
	}
	public static String getOldState(){
		return State.oldState;
	}
	public static String getState(){
		return State.state;
	}
	public static Deck getDeck() {
		return deck;
	}
	public static void setDeck(Deck deck) {
		State.deck = deck;
	}
	public static Player getPlayer1() {
		return player1;
	}
	public static void setPlayer1(Player player1) {
		State.player1 = player1;
	}
	public static Player getPlayer2() {
		return player2;
	}
	public static void setPlayer2(Player player2) {
		State.player2 = player2;
	}
	public static Market getMarket() {
		return market;
	}
	public static void setMarket(Market market) {
		State.market = market;
	}
	public static Discard getDiscard() {
		return discard;
	}
	public static void setDiscard(Discard discard) {
		State.discard = discard;
	}
	public static void setCanvas(Canvas c){
		State.canvas=c;
	}
	public static void addRenderable(Renderable s){
		State.renderables.add(s);
	}
	public static List<Renderable> getRenderable(){
		return State.renderables;
	}
	public static boolean addClickable(Clickable c){
		if (!State.clickables.contains(c)){
			State.clickables.add(c);
			return true;
		}
		else{
			return false;
		}
	}
	public static boolean removeClickable(Clickable c){
		return State.clickables.remove(c);
	}
	public static List<Clickable> getClickables(){
		return State.clickables;
	}
	public static List<String> getValidStates(){
		if(State.validStates!=null)
			return State.validStates;
		String[] array = {"INIT","PLAYER1","PLAYER2","SCORE","FINISH","STOP"};
		State.validStates = new ArrayList<String>(Arrays.asList(array));
		return State.validStates;
	}
/*	public static void renderSprites(){
		ListIterator<Sprite> spriteIterator = State.sprites.listIterator();
		while(spriteIterator.hasNext()){
			spriteIterator.next().render(canvas.getGraphicsContext2D());
		}
	}*/
	public static void render(){
        canvas.getGraphicsContext2D().clearRect(0, 0, State.CANVAS_X, State.CANVAS_Y);
        for(Renderable r:State.renderables)
        	r.render(canvas.getGraphicsContext2D());
	}
	
}
