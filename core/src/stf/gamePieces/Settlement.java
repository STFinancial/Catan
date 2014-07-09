package stf.gamePieces;

import com.badlogic.gdx.graphics.Texture;

public class Settlement extends Building {

	public Settlement(PlayerColor color) {
		this.ownerColor = color;
	}
	
	public PlayerColor getColor() {
		return ownerColor;
	}
	
	//it is convenient that the resource multiplier
	//is equal to the points that it is worth
	
	@Override
	public int getPoints() {
		return 1;
	}
	
	public void setPosition(Intersection intersection) {
		position = intersection;
	}

	@Override
	public Texture getImg() {
		// TODO Auto-generated method stub
		return null;
	}
}
