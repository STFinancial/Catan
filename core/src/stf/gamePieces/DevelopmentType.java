package stf.gamePieces;

import com.badlogic.gdx.graphics.Texture;

public enum DevelopmentType {
	PALACE ("palace.gif", true), 
	UNIVERSITY ("university.gif", true), 
	MARKET ("market.gif", true), 
	CHAPEL ("chapel.gif", true), 
	LIBRARY ("library.gif", true), 
	MONOPOLY ("monopoly.gif", false), 
	YEAROFPLENTY ("YOP.gif", false), 
	ROADBUILDING ("RB.gif", false), 
	KNIGHT ("knight.gif", false);
	
	Texture image;
	boolean isVictoryPoint;
	
	private DevelopmentType(String filePath, boolean isVictoryPoint) {
		this.isVictoryPoint = isVictoryPoint;
		image = new Texture(filePath);
	}
	
	public Texture getImage(){
		return image;
	}
}
