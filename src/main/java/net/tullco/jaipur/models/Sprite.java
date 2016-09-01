package net.tullco.jaipur.models;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;

public interface Sprite {
	public void render(GraphicsContext gc);
	public Rectangle2D getBoundary();
	public void setDestinationCoordinates(int xDest, int yDest);
}
