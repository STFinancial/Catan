package stf.main;
import com.badlogic.gdx.math.Vector3;

public class CoordinateUtil {
	static final int TILESIZE = 256;
	
	public static Vector3 tileToGame(Vector3 tileCoord){
		Vector3 toReturn = new Vector3();
		float x = 0;
		float y = 0;
		x = Math.abs(2 - tileCoord.y) * TILESIZE/2 + TILESIZE * tileCoord.x;
		y = (3*TILESIZE/4) * (4-tileCoord.y);
		toReturn.x = x;
		toReturn.y = y;
		return toReturn;
	}
	
	
}
