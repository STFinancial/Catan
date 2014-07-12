package stf.main;
import java.util.ArrayList;

import stf.gamePieces.Board;
import stf.gamePieces.Intersection;
import stf.gamePieces.Port;
import stf.gamePieces.Tile;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector3;

public class CoordinateUtil {
	static final int TILESIZE = 256;
	static final int PORTSIZE = 256;
	static final int INTERSECTIONSIZE = 64;
	static ArrayList<Circle> tileSpots;
	static ArrayList<Circle> intSpots;
	static ArrayList<Circle> portSpots;

	//may be too much overhead, now more for testing
	public static Object getClickObject(Vector3 gameCoords, Board board){
		int i = 0;
		for(Circle c: tileSpots){
			if (c.contains(gameCoords.x, gameCoords.y)){
				return board.getTiles()[i];
			}
			i++;
		}
		i = 0;
		for(Circle c: intSpots){
			if (c.contains(gameCoords.x, gameCoords.y)){
				return board.getIntersections()[i];
			}
			i++;
		}
		i = 0;
		for(Circle c: portSpots){
			if (c.contains(gameCoords.x, gameCoords.y)){
				return board.getPorts()[i];
			}
			i++;
		}
		return null;
	}

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

	public static void setupGametoTile(Tile[] tiles){
		tileSpots = new ArrayList<Circle>();
		for (Tile t: tiles){
			float x = 0;
			float y = 0;
			x = Math.abs(2 - t.getY()) * TILESIZE/2 + TILESIZE * t.getX();
			y = (3*TILESIZE/4) * (4-t.getY());
			x += TILESIZE/2;
			y += TILESIZE/2;
			tileSpots.add(new Circle(x,y,.35f*TILESIZE));	
		}
	}

	public static Vector3 intToGame(int ID) {
		
		float x;
		float y;
		if(ID < 7){
			y = 3.75f*TILESIZE;
			x = TILESIZE;
			x+=TILESIZE/2*(ID-0);
			if(ID%2 == 1){
				y+=TILESIZE/5;
			}
		}else if(ID > 6 && ID < 16){
			y = 3f*TILESIZE;
			x = .5f*TILESIZE;
			x+=TILESIZE/2*(ID-7);
			if(ID%2 == 0){
				y+=TILESIZE/5;
			}
		}else if(ID > 15 && ID < 27){
			y = 2.25f*TILESIZE;
			x = 0;
			x+=TILESIZE/2*(ID-16);
			if(ID%2 == 1){
				y+=TILESIZE/5;
			}
		}else if(ID > 26 && ID < 38){
			y = 1.5f*TILESIZE;
			x = 0;
			x+=TILESIZE/2*(ID-27);
			if(ID%2 == 1){
				y+=TILESIZE/5;
			}
		}else if(ID > 37 && ID < 47){
			y = .75f * TILESIZE;
			x = .5f*TILESIZE;
			x+=TILESIZE/2*(ID-38);
			if(ID%2 == 0){
				y+=TILESIZE/5;
			}
		}else{
			y = 0;
			x = TILESIZE;
			x+=TILESIZE/2*(ID-47);
			if(ID%2 == 1){
				y+=TILESIZE/5;
			}
		}
		x -= INTERSECTIONSIZE /2;
		y -= INTERSECTIONSIZE /2 - 10;
		
		Vector3 toReturn = new Vector3(x,y,0);
		return toReturn;
	}
	
	public static void setupGametoInt(Intersection[] ints){
		intSpots = new ArrayList<Circle>();
		for (Intersection i: ints){
			Vector3 temp = intToGame(i.getID());
			temp.x += INTERSECTIONSIZE /2;
			temp.y += INTERSECTIONSIZE /2 - 10;
			intSpots.add(new Circle(temp.x,temp.y,.5f*INTERSECTIONSIZE));	
		}
	}
	
	
	public static Vector3 portToGame(int ID) {
		float x, y;

		switch (ID) {
		case 0:
			x = TILESIZE*.65f;
			y = TILESIZE*3.75f;
			break;
		case 1:
			x = TILESIZE*-0.35f;
			y = TILESIZE*2.4f;
			break;
		case 2:
			x = TILESIZE*-0.4f;
			y = TILESIZE*.7f;
			break;
		case 3:
			x = TILESIZE* 0.6f;
			y = TILESIZE * -0.7f;
			break;
		case 4:
			x = TILESIZE * 2.3f;
			y = TILESIZE * -0.7f;
			break;
		case 5:
			x = TILESIZE * 3.9f;
			y = TILESIZE* 0.0f;
			break;
		case 6:
			x = TILESIZE * 4.9f;
			y = TILESIZE*1.5f;
			break;
		case 7:
			x = TILESIZE * 3.95f;
			y = TILESIZE*2.95f;
			break;
		default:
			x = TILESIZE * 2.4f;
			y = TILESIZE*3.75f;
			break;
		}
		
		Vector3 toReturn = new Vector3(x,y,0);
		return toReturn;
	}
	public static int portToGameRotation(int ID) {
		switch (ID) {
		case 0:
			return -128;
		case 1:
			return -65;
		case 2:
			return -55;
		case 3:
			return 10;
		case 4:
			return 50;
		case 5:
			return 55;
		case 6:
			return 120;
		case 7:
			return 180;
		default:
			return 190;
		}
	}
	public static Vector3 portTypeToGame(int ID) {
		float x, y;
		x = portToGame(ID).x;
		y = portToGame(ID).y;
		switch (ID) {
		case 0:
			x += TILESIZE*0.29f;
			y += TILESIZE*0.58f;
			break;
		case 1:
			x += TILESIZE*0.15f;
			y += TILESIZE*0.40f;
			break;
		case 2:
			x += TILESIZE*0.15f;
			y += TILESIZE*0.35f;
			break;
		case 3:
			x += TILESIZE*0.29f;
			y += TILESIZE*0.15f;
			break;
		case 4:
			x += TILESIZE*0.45f;
			y += TILESIZE*0.15f;
			break;
		case 5:
			x += TILESIZE*0.47f;
			y += TILESIZE*0.16f;
			break;
		case 6:
			x += TILESIZE*0.60f;
			y += TILESIZE*0.39f;
			break;
		case 7:
			x += TILESIZE*0.48f;
			y += TILESIZE*0.57f;
			break;
		default:
			x += TILESIZE*0.45f;
			y += TILESIZE*0.58f;
			break;
		}
		
		Vector3 toReturn = new Vector3(x,y,0);
		return toReturn;
	}
	
	public static void setupGametoPort(Port[] ports){
		portSpots = new ArrayList<Circle>();
		int ID = 0;
		for (Port p: ports){
			float x,y;
			x = CoordinateUtil.portTypeToGame(ID).x + 32;
			y = CoordinateUtil.portTypeToGame(ID).y + 32;
			portSpots.add(new Circle(x,y,.6f*64));	
			ID++;
		}
	}
}
