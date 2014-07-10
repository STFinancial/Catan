package stf.gamePieces;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public enum ResourceType {
	BRICK ("images/brick.png"), 
	LOGS ("images/logs.png"),
	ORE ("images/ore.png"), 
	SHEEP ("images/sheep.png"), 
	WHEAT ("images/wheat.png");
	
	
	BufferedImage image;
	
	private ResourceType(String filePath) {
		File file = new File(filePath);
		
		try {
			image = ImageIO.read(file);
		} catch (IOException e) {
			System.out.println("Could not find specified file. Resource Type: " + filePath);
			e.printStackTrace();
		}
	}
}
