package stf.gamePieces;

import com.badlogic.gdx.graphics.Texture;

public class Road extends Placeable {
	private Path position;
	
	public Road(PlayerColor color) {
		super(color);
	}

	public int getPoints() {
		return 0;
	}

	@Override
	public Texture getImg() {
		// TODO Auto-generated method stub
		return null;
	}
}
