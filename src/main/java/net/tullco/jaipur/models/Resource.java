package net.tullco.jaipur.models;

import javafx.scene.image.Image;

public abstract class Resource {
	private String type;
	private int value;
	public Resource(String type, int value){
		this.type=type;
		this.value=value;
	}
	public int getValue(){
		return this.value;
	}
	public String getType(){
		return this.type;
	}
	public abstract Image getImage();
}