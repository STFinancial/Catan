package stf.gamePieces;

import com.badlogic.gdx.graphics.Texture;

public enum ResourceType {
	BRICK ("brick.png"), 
	LOGS ("logs.png"),
	ORE ("ore.png"), 
	SHEEP ("sheep.png"), 
	WHEAT ("wheat.png");
	
	
	Texture image;
	
	private ResourceType(String filePath) {	
		image = new Texture(filePath);
	}
	
	public Texture getImage(){
		return image;
	}
}
