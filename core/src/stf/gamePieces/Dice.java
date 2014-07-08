package stf.gamePieces;

import java.util.Random;

public class Dice {
	private int left;
	private int right;
	private int total;
	private Random random = new Random();
	
	public void roll() {
		left = random.nextInt(6) + 1;
		right = random.nextInt(6) + 1;
		total = left + right;
	}
	
	public int getLeft() {
		return left;
	}
	
	public int getRight() {
		return right;
	}
	
	public int getTotal() {
		return total;
	}
}
