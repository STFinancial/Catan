package stf.gamePieces;

public class Road {
	PlayerColor ownerColor;
	Path position;
	
	public Road(PlayerColor ownerColor) {
		this.ownerColor = ownerColor;
	}
	
	
	PlayerColor getOwnerColor() {
		return ownerColor;
	}
	
	public Path getPosition() {
		return position;
	}
	
	
	public void setPosition(Path position) {
		this.position = position;
	}
}
