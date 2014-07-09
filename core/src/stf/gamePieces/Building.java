package stf.gamePieces;

import com.badlogic.gdx.graphics.Texture;

public abstract class Building {
	protected PlayerColor ownerColor;
	protected Intersection position;
	
	
	
	public abstract int getPoints();
	
	public Intersection getPosition() { return position; }

	public abstract Texture getImg();
	
	public void setPosition(Intersection position) { this.position = position; }
}
