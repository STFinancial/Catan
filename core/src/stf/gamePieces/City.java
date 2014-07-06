package stf.gamePieces;

import com.badlogic.gdx.graphics.Texture;

public class City extends Building {

	public City(PlayerColor color) {
		this.color = color;
	}
	
	public PlayerColor getColor() {
		return color;
	}
	
	public Intersection getPosition() {
		return position;
	}
	
	//it is convenient that the resource multiplier
	//is equal to the points that it is worth
	
	@Override
	public int getPoints() {
		return 2;
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
