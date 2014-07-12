package stf.gamePieces;

import com.badlogic.gdx.graphics.Texture;

public enum PortType {
	RANDOM(null),
	LOGS(ResourceType.LOGS),
	WHEAT(ResourceType.WHEAT),
	SHEEP(ResourceType.SHEEP),
	BRICK(ResourceType.BRICK),
	ORE(ResourceType.ORE);
	
	//toString() already outputs the proper text
	
	ResourceType resourceEquiv;
	Texture image = null;
	
	private PortType(ResourceType type) {
		resourceEquiv = type;
		if(type != null){
			image = type.getImage();
		}else{
			image = new Texture("randomPort.png");
		}
	}
	
	public Texture getImage(){
		return image;
	}
//	private PortType(String filename){
//		
//	}
}
