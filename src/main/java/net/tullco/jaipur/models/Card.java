package net.tullco.jaipur.models;

import java.io.InputStream;

import javafx.scene.image.Image;

public abstract class Card {
	private String resource;
	private Image image;
	
	public Card(String resource){
		this.resource=resource;
	}
	
	public String getResource(){
		return this.resource;
	}
	public Image getImage(){
		if (this.image!=null)
			return this.image;
		String path="assets/img/cards/"+this.resource.toLowerCase()+".png";
		System.out.println(path);
		InputStream is = this.getClass().getClassLoader().getResourceAsStream(path);
		this.image = new Image(is);
		return this.image;
	}
}
