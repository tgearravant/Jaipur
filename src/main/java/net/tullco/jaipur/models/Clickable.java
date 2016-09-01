package net.tullco.jaipur.models;

import javafx.geometry.Rectangle2D;

public interface Clickable {
	public void click();
	public Rectangle2D getBoundary();
}
