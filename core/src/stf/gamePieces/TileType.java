package stf.gamePieces;

import com.badlogic.gdx.graphics.Texture;

public enum TileType {
	DESERT ("desert.gif"),
	FIELDS ("fields.gif"),
	FOREST ("forest.gif"),
	HILLS ("hills.gif"), 
	MOUNTAINS ("mountains.gif"), 
	PASTURE ("pasture.gif");
	
	Texture image;
	
	private TileType(String filePath) {	
		image = new Texture(filePath);
	}
	
	public Texture getImage(){
		return image;
	}
}
