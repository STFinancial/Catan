package stf.main;

import com.badlogic.gdx.math.Vector3;

public class CoordinateUtil {

	public static Vector3 tileToGame(Vector3 tileCoord){
		Vector3 toReturn = new Vector3();
		float x = 0;
		float y = 0;
		x = Math.abs(2 - tileCoord.y) * 128 + 256 * tileCoord.x;
		y = (128+64) * (4-tileCoord.y);
		toReturn.x = x;
		toReturn.y = y;
		return toReturn;
	}
	
	
}
