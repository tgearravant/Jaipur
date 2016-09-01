package net.tullco.jaipur.models;

import javafx.scene.canvas.GraphicsContext;

public interface Sprite {
	public void render(GraphicsContext gc);
	public void setDestinationCoordinates(int xDest, int yDest);
}
