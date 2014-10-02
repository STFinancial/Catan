package stf.gamePieces;

import com.badlogic.gdx.graphics.Texture;

public abstract class Placeable {
	protected PlayerColor ownerColor;
	protected Intersection position;
	
	public Placeable(PlayerColor color) {
		this.ownerColor = color;
	}
	
	public abstract int getPoints();
	
	public Intersection getPosition() { 
		return position; 
	}

	public abstract Texture getImg();
	
	public void setPosition(Intersection position) { 
		this.position = position;
	}
	
	public PlayerColor getColor() {
		return ownerColor;
	}
}
