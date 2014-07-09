package stf.gamePieces;

import java.awt.image.BufferedImage;

public enum PortType {
	RANDOM(null),
	LOGS(ResourceType.LOGS),
	WHEAT(ResourceType.WHEAT),
	SHEEP(ResourceType.SHEEP),
	BRICK(ResourceType.BRICK),
	ORE(ResourceType.ORE);
	
	//toString() already outputs the proper text
	
	ResourceType resourceEquiv;
	BufferedImage image = null;
	
	private PortType(ResourceType type) {
		resourceEquiv = type;
	}
	
//	private PortType(String filename){
//		
//	}
}
