package net.tullco.jaipur.models;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import net.tullco.jaipur.models.cards.ClothCard;
import net.tullco.jaipur.models.cards.DiamondCard;
import net.tullco.jaipur.models.cards.GoldCard;
import net.tullco.jaipur.models.cards.LeatherCard;
import net.tullco.jaipur.models.cards.SilverCard;
import net.tullco.jaipur.models.cards.SpiceCard;

public class ResourceMarket implements Renderable{
	
	public final static int RESOURCE_MARKET_X = 900;
	public final static int RESOURCE_MARKET_Y = 250;
	public final static int RESOURCE_MARKET_W = 200;
	public final static int RESOURCE_MARKET_H = 125;
	public final static int RESOURCE_BUFFER = 5;
	
	private ArrayList<Resource> diamondPile=new ArrayList<Resource>();
	private ArrayList<Resource> goldPile=new ArrayList<Resource>();
	private ArrayList<Resource> silverPile=new ArrayList<Resource>();
	private ArrayList<Resource> clothPile=new ArrayList<Resource>();
	private ArrayList<Resource> spicePile=new ArrayList<Resource>();
	private ArrayList<Resource> leatherPile=new ArrayList<Resource>();
	private ArrayList<Resource> threePile=new ArrayList<Resource>();
	private ArrayList<Resource> fourPile=new ArrayList<Resource>();
	private ArrayList<Resource> fivePile=new ArrayList<Resource>();
	
	public ResourceMarket(){
		diamondPile.add(new Resource("diamond", 5));
		diamondPile.add(new Resource("diamond", 5));
		diamondPile.add(new Resource("diamond", 5));
		diamondPile.add(new Resource("diamond", 7));
		diamondPile.add(new Resource("diamond", 7));
		goldPile.add(new Resource("gold", 5));
		goldPile.add(new Resource("gold", 5));
		goldPile.add(new Resource("gold", 5));
		goldPile.add(new Resource("gold", 6));
		goldPile.add(new Resource("gold", 6));
		silverPile.add(new Resource("silver", 5));
		silverPile.add(new Resource("silver", 5));
		silverPile.add(new Resource("silver", 5));
		silverPile.add(new Resource("silver", 5));
		silverPile.add(new Resource("silver", 5));
		clothPile.add(new Resource("cloth",1));
		clothPile.add(new Resource("cloth",1));
		clothPile.add(new Resource("cloth",1));
		clothPile.add(new Resource("cloth",2));
		clothPile.add(new Resource("cloth",2));
		clothPile.add(new Resource("cloth",3));
		clothPile.add(new Resource("cloth",5));
		spicePile.add(new Resource("spice",1));
		spicePile.add(new Resource("spice",1));
		spicePile.add(new Resource("spice",1));
		spicePile.add(new Resource("spice",2));
		spicePile.add(new Resource("spice",2));
		spicePile.add(new Resource("spice",3));
		spicePile.add(new Resource("spice",5));
		leatherPile.add(new Resource("leather",1));
		leatherPile.add(new Resource("leather",1));
		leatherPile.add(new Resource("leather",1));
		leatherPile.add(new Resource("leather",2));
		leatherPile.add(new Resource("leather",2));
		leatherPile.add(new Resource("leather",3));
		leatherPile.add(new Resource("leather",3));
		leatherPile.add(new Resource("leather",4));
		leatherPile.add(new Resource("leather",5));
		threePile.add(new Resource("three",1));
		threePile.add(new Resource("three",1));
		threePile.add(new Resource("three",2));
		threePile.add(new Resource("three",2));
		threePile.add(new Resource("three",3));
		threePile.add(new Resource("three",3));
		fourPile.add(new Resource("four",4));
		fourPile.add(new Resource("four",4));
		fourPile.add(new Resource("four",5));
		fourPile.add(new Resource("four",5));
		fourPile.add(new Resource("four",6));
		fourPile.add(new Resource("four",6));
		fivePile.add(new Resource("five",7));
		fivePile.add(new Resource("five",7));
		fivePile.add(new Resource("five",8));
		fivePile.add(new Resource("five",8));
		fivePile.add(new Resource("five",9));
		fivePile.add(new Resource("five",9));
		int xloc = RESOURCE_MARKET_X;
		int yloc = RESOURCE_MARKET_Y;
		for(int i=0;i<diamondPile.size();i++)
			diamondPile.get(i).setDestinationCoordinates(xloc+(i*RESOURCE_BUFFER), yloc);
		xloc = RESOURCE_MARKET_X;
		yloc = RESOURCE_MARKET_Y+(RESOURCE_MARKET_H/2);
		for(int i=0;i<goldPile.size();i++)
			goldPile.get(i).setDestinationCoordinates(xloc+(i*RESOURCE_BUFFER), yloc);
		xloc = RESOURCE_MARKET_X;
		yloc = RESOURCE_MARKET_Y+RESOURCE_MARKET_H;
		for(int i=0;i<silverPile.size();i++)
			silverPile.get(i).setDestinationCoordinates(xloc+(i*RESOURCE_BUFFER), yloc);
		xloc = RESOURCE_MARKET_X+(RESOURCE_MARKET_W/2);
		yloc = RESOURCE_MARKET_Y;
		for(int i=0;i<clothPile.size();i++)
			clothPile.get(i).setDestinationCoordinates(xloc+(i*RESOURCE_BUFFER), yloc);
		xloc = RESOURCE_MARKET_X+(RESOURCE_MARKET_W/2);
		yloc = RESOURCE_MARKET_Y+(RESOURCE_MARKET_H/2);
		for(int i=0;i<spicePile.size();i++)
			spicePile.get(i).setDestinationCoordinates(xloc+(i*RESOURCE_BUFFER), yloc);
		xloc = RESOURCE_MARKET_X+(RESOURCE_MARKET_W/2);
		yloc = RESOURCE_MARKET_Y+RESOURCE_MARKET_H;
		for(int i=0;i<leatherPile.size();i++)
			leatherPile.get(i).setDestinationCoordinates(xloc+(i*RESOURCE_BUFFER), yloc);
		xloc = RESOURCE_MARKET_X+RESOURCE_MARKET_W;
		yloc = RESOURCE_MARKET_Y;
		for(int i=0;i<threePile.size();i++)
			threePile.get(i).setDestinationCoordinates(xloc+(i*RESOURCE_BUFFER), yloc);
		xloc = RESOURCE_MARKET_X+RESOURCE_MARKET_W;
		yloc = RESOURCE_MARKET_Y+(RESOURCE_MARKET_H/2);
		for(int i=0;i<fourPile.size();i++)
			fourPile.get(i).setDestinationCoordinates(xloc+(i*RESOURCE_BUFFER), yloc);
		xloc = RESOURCE_MARKET_X+RESOURCE_MARKET_W;
		yloc = RESOURCE_MARKET_Y+RESOURCE_MARKET_H;
		for(int i=0;i<fivePile.size();i++)
			fivePile.get(i).setDestinationCoordinates(xloc+(i*RESOURCE_BUFFER), yloc);
	}
	
	public int emptyPiles(){
		int emptyPiles=0;
		if(diamondPile.isEmpty())
			emptyPiles++;
		if(goldPile.isEmpty())
			emptyPiles++;
		if(silverPile.isEmpty())
			emptyPiles++;
		if(clothPile.isEmpty())
			emptyPiles++;
		if(spicePile.isEmpty())
			emptyPiles++;
		if(leatherPile.isEmpty())
			emptyPiles++;
		return emptyPiles;
	}
	
	public List<Resource> getResources(List<Card> cards){
		ArrayList<Resource> resources = new ArrayList<Resource>();
		for(Card c: cards){
			if(c instanceof DiamondCard && !diamondPile.isEmpty())
				resources.add(diamondPile.remove(0));
			if(c instanceof GoldCard && !goldPile.isEmpty())
				resources.add(goldPile.remove(0));
			if(c instanceof SilverCard && !silverPile.isEmpty())
				resources.add(silverPile.remove(0));
			if(c instanceof ClothCard && !clothPile.isEmpty())
				resources.add(clothPile.remove(0));
			if(c instanceof SpiceCard && !spicePile.isEmpty())
				resources.add(spicePile.remove(0));
			if(c instanceof LeatherCard && !leatherPile.isEmpty())
				resources.add(leatherPile.remove(0));
		}
		if (cards.size()==3 && !threePile.isEmpty())
			resources.add(threePile.remove(0));
		if (cards.size()==4 && !fourPile.isEmpty())
			resources.add(fourPile.remove(0));
		if (cards.size()>=5 && !fivePile.isEmpty())
			resources.add(fivePile.remove(0));
		return resources;
	}

	@Override
	public void render(GraphicsContext gc) {
		for(Resource r: diamondPile)
			r.render(gc);
		for(Resource r: goldPile)
			r.render(gc);
		for(Resource r: silverPile)
			r.render(gc);
		for(Resource r: clothPile)
			r.render(gc);
		for(Resource r: spicePile)
			r.render(gc);
		for(Resource r: leatherPile)
			r.render(gc);
		for(Resource r: threePile)
			r.render(gc);
		for(Resource r: fourPile)
			r.render(gc);
		for(Resource r: fivePile)
			r.render(gc);
	}
}
