package net.tullco.jaipur.models.resources;

import java.io.InputStream;

import javafx.scene.image.Image;
import net.tullco.jaipur.models.Resource;

public class Diamond extends Resource {
	Image image;

	public Diamond(int value){
		super("Diamond",value);
	}

	@Override
	public Image getImage(){
		if (image!=null)
			return image;
		InputStream is = this.getClass().getClassLoader().getResourceAsStream("img/diamonds.png");
		this.image = new Image(is);
		return this.image;
	}
}
