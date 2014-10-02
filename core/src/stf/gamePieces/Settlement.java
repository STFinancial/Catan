package stf.gamePieces;

import com.badlogic.gdx.graphics.Texture;

public class Settlement extends Placeable {

	public Settlement(PlayerColor color) {
		super(color);
	}
	
	//it is convenient that the resource multiplier
	//is equal to the points that it is worth
	public int getPoints() {
		return 1;
	}
	
	public Texture getImg() {
		// TODO Auto-generated method stub
		return null;
	}
}
