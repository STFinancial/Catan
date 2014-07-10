package stf.gamePieces;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public enum DevelopmentType {
	PALACE ("images/palace.png", true), 
	UNIVERSITY ("images/university.png", true), 
	MARKET ("images/market.png", true), 
	CHAPEL ("images/chapel.png", true), 
	LIBRARY ("images/library.png", true), 
	MONOPOLY ("images/monopoly.png", false), 
	YEAROFPLENTY ("images/YOP.png", false), 
	ROADBUILDING ("images/RB.png", false), 
	KNIGHT ("images/knight.png", false);
	
	BufferedImage image;
	boolean isVictoryPoint;
	
	private DevelopmentType(String filePath, boolean isVictoryPoint) {
		this.isVictoryPoint = isVictoryPoint;
		File file = new File(filePath);
		try {
			image = ImageIO.read(file);
		} catch (IOException e) {
			System.out.println("Could not find specified file. Development Type: " + filePath);
			e.printStackTrace();
		}
	}
}
