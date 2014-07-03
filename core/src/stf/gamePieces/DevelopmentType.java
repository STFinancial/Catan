package stf.gamePieces;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public enum DevelopmentType {
	PALACE ("images/palace.png"), 
	UNIVERSITY ("images/university.png"), 
	MARKET ("images/market.png"), 
	CHAPEL ("images/chapel.png"), 
	LIBRARY ("images/library.png"), 
	MONOPOLY ("images/monopoly.png"), 
	YEAROFPLENTY ("images/YOP.png"), 
	ROADBUILDING ("images/RB.png"), 
	KNIGHT ("images/knight.png");
	
	BufferedImage image;
	
	private DevelopmentType(String filePath) {
		File file = new File(filePath);
		try {
			image = ImageIO.read(file);
		} catch (IOException e) {
			System.out.println("Could not find specified file. Development Type: " + filePath);
			e.printStackTrace();
		}
	}
}
