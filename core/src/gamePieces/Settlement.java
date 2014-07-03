package gamePieces;

public class Settlement extends Building {

	public Settlement(PlayerColor color) {
		this.color = color;
	}
	
	public PlayerColor getColor() {
		return color;
	}
	
	public Intersection getPosition() {
		return position;
	}
	
	//it is convenient that the resource multiplier
	//is equal to the points that it is worth
	
	@Override
	public int getPoints() {
		return 1;
	}
	
	public void setPosition(Intersection intersection) {
		position = intersection;
	}
}
