package stf.gamePieces;

import com.badlogic.gdx.graphics.Texture;

public enum ResourceType {
	BRICK ("brick.gif"), 
	LOGS ("logs.gif"),
	ORE ("ore.gif"), 
	SHEEP ("sheep.gif"), 
	WHEAT ("wheat.gif");
	
	
	Texture image;
	
	private ResourceType(String filePath) {	
		image = new Texture(filePath);
	}
	
	public Texture getImage(){
		return image;
	}
}
