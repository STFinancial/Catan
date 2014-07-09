package stf.gamePieces;

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
	
	static PlayerColor getColor(int index) {
		switch(index) {
		case 0: return BLUE;
		case 1: return ORANGE;
		case 2: return RED;
		case 3: return WHITE;
		default: System.out.println("It shouldn't get here, PlayerColor getColor");
		return null;
		}
	}
}
