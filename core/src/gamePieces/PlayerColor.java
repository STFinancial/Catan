package gamePieces;

import java.awt.Color;

public enum PlayerColor {
	BLUE(new Color(73, 93, 221)),
	ORANGE(new Color(237, 155, 78)),
	RED(new Color(255, 69, 12)),
	WHITE(new Color(255, 255, 255));
	
	Color color;
	
	private PlayerColor(Color color) {
		this.color = color;
	}
}
