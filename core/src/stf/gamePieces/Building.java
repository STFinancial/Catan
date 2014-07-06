package stf.gamePieces;

import com.badlogic.gdx.graphics.Texture;

public abstract class Building {
	protected PlayerColor ownerColor;
	protected Intersection position;
	
	public abstract int getPoints();

	public abstract Texture getImg();
}
