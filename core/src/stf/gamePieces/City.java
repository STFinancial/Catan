package stf.gamePieces;

import com.badlogic.gdx.graphics.Texture;

public class City extends Placeable {

	public City(PlayerColor color) {
		super(color);
	}
	
	//it is convenient that the resource multiplier
	//is equal to the points that it is worth
	public int getPoints() {
		return 2;
	}

	public Texture getImg() {
		// TODO Auto-generated method stub
		return null;
	}

}
