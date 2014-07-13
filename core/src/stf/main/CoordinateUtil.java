package stf.main;
import java.util.ArrayList;

import stf.gamePieces.Board;
import stf.gamePieces.Intersection;
import stf.gamePieces.Path;
import stf.gamePieces.Port;
import stf.gamePieces.Tile;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector3;

public class CoordinateUtil {
	static final int TILEHEIGHT = 256;
	static final int TILEWIDTH = 256;
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
		x = Math.abs(2 - tileCoord.y) * TILEWIDTH/2 + TILEWIDTH * tileCoord.x;
		y = (3*TILEHEIGHT/4) * (4-tileCoord.y);
		toReturn.x = x;
		toReturn.y = y;
		return toReturn;
	}

	public static void setupGametoTile(Tile[] tiles){
		tileSpots = new ArrayList<Circle>();
		for (Tile t: tiles){
			float x = 0;
			float y = 0;
			x = Math.abs(2 - t.getY()) * TILEWIDTH/2 + TILEWIDTH * t.getX();
			y = (3*TILEHEIGHT/4) * (4-t.getY());
			x += TILEWIDTH/2;
			y += TILEHEIGHT/2;
			tileSpots.add(new Circle(x,y,.35f*TILEHEIGHT));	
		}
	}

	public static Vector3 intToGame(int ID) {
		
		float x;
		float y;
		if(ID < 7){
			y = 3.75f*TILEHEIGHT;
			x = TILEWIDTH;
			x+=TILEWIDTH/2*(ID-0);
			if(ID%2 == 1){
				y+=TILEHEIGHT/5;
			}
		}else if(ID > 6 && ID < 16){
			y = 3f*TILEHEIGHT;
			x = .5f*TILEWIDTH;
			x+=TILEWIDTH/2*(ID-7);
			if(ID%2 == 0){
				y+=TILEHEIGHT/5;
			}
		}else if(ID > 15 && ID < 27){
			y = 2.25f*TILEHEIGHT;
			x = 0;
			x+=TILEWIDTH/2*(ID-16);
			if(ID%2 == 1){
				y+=TILEHEIGHT/5;
			}
		}else if(ID > 26 && ID < 38){
			y = 1.5f*TILEHEIGHT;
			x = 0;
			x+=TILEWIDTH/2*(ID-27);
			if(ID%2 == 1){
				y+=TILEHEIGHT/5;
			}
		}else if(ID > 37 && ID < 47){
			y = .75f * TILEHEIGHT;
			x = .5f*TILEWIDTH;
			x+=TILEWIDTH/2*(ID-38);
			if(ID%2 == 0){
				y+=TILEHEIGHT/5;
			}
		}else{
			y = 0;
			x = TILEWIDTH;
			x+=TILEWIDTH/2*(ID-47);
			if(ID%2 == 1){
				y+=TILEHEIGHT/5;
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
			x = TILEWIDTH*.65f;
			y = TILEHEIGHT*3.75f;
			break;
		case 1:
			x = TILEWIDTH*-0.35f;
			y = TILEHEIGHT*2.4f;
			break;
		case 2:
			x = TILEWIDTH*-0.4f;
			y = TILEHEIGHT*.7f;
			break;
		case 3:
			x = TILEWIDTH* 0.6f;
			y = TILEHEIGHT * -0.7f;
			break;
		case 4:
			x = TILEWIDTH * 2.3f;
			y = TILEHEIGHT * -0.7f;
			break;
		case 5:
			x = TILEWIDTH * 3.9f;
			y = TILEHEIGHT* 0.0f;
			break;
		case 6:
			x = TILEWIDTH * 4.9f;
			y = TILEHEIGHT*1.5f;
			break;
		case 7:
			x = TILEWIDTH * 3.95f;
			y = TILEHEIGHT*2.95f;
			break;
		default:
			x = TILEWIDTH * 2.4f;
			y = TILEHEIGHT*3.75f;
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
			x += TILEWIDTH*0.285f;
			y += TILEHEIGHT*0.59f;
			break;
		case 1:
			x += TILEWIDTH*0.145f;
			y += TILEHEIGHT*0.395f;
			break;
		case 2:
			x += TILEWIDTH*0.145f;
			y += TILEHEIGHT*0.35f;
			break;
		case 3:
			x += TILEWIDTH*0.298f;
			y += TILEHEIGHT*0.155f;
			break;
		case 4:
			x += TILEWIDTH*0.455f;
			y += TILEHEIGHT*0.155f;
			break;
		case 5:
			x += TILEWIDTH*0.475f;
			y += TILEHEIGHT*0.165f;
			break;
		case 6:
			x += TILEWIDTH*0.605f;
			y += TILEHEIGHT*0.38f;
			break;
		case 7:
			x += TILEWIDTH*0.485f;
			y += TILEHEIGHT*0.575f;
			break;
		default:
			x += TILEWIDTH*0.45f;
			y += TILEHEIGHT*0.595f;
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

	public static Vector3 pathToGame(int ID) {
		float x = 0, y = 0;
		
		if(ID < 6){
			y = 3.75f*TILEHEIGHT;
			x = TILEWIDTH;
			x+=TILEWIDTH/2*(ID-0);
			if(ID%2 == 1){
				y+=TILEHEIGHT * 0.0f;
			}
		}else if(ID > 5 && ID < 14){
			y = 3f*TILEHEIGHT;
			x = .5f*TILEWIDTH;
			x+=TILEWIDTH/2*(ID-6);
			if(ID%2 == 0){
				y+=TILEHEIGHT * 0.0f;
			}
		}else if(ID > 13 && ID < 24){
			y = 2.25f*TILEHEIGHT;
			x = 0;
			x+=TILEWIDTH/2*(ID-14);
			if(ID%2 == 1){
				y+=TILEHEIGHT * 0.0f;
			}
		}else if(ID > 23 && ID < 34){
			y = 1.5f*TILEHEIGHT;
			x = 0;
			x+=TILEWIDTH/2*(ID-24);
			if(ID%2 == 1){
				y+=TILEHEIGHT * 0.0f;
			}
		}else if(ID > 33 && ID < 42){
			y = .75f * TILEHEIGHT;
			x = .5f*TILEWIDTH;
			x+=TILEWIDTH/2*(ID-34);
			if(ID%2 == 0){
				y+=TILEHEIGHT * 0.0f;
			}
		}else if(ID > 41 && ID < 48){
			y = 0;
			x = TILEWIDTH;
			x+=TILEWIDTH/2*(ID-42);
			if(ID%2 == 1){
				y+=TILEHEIGHT * 0.0f;
			}
		}else if(ID > 47 && ID < 52){
			x = .55f * TILEWIDTH;
			x+=TILEWIDTH*(ID-48);
			y = 3.36f * TILEHEIGHT;
		}else if(ID > 51 && ID < 57){
			x = .05f * TILEWIDTH;
			x+=TILEWIDTH*(ID-52);
			y = 2.605f * TILEHEIGHT;
			
		}else if(ID > 56 && ID < 63){
			x = -.45f * TILEWIDTH;
			x+=TILEWIDTH*(ID-57);
			y = 1.855f * TILEHEIGHT;
			
		}else if(ID > 62 && ID < 68){
			x = .05f * TILEWIDTH;
			x+=TILEWIDTH*(ID-63);
			y = 1.105f * TILEHEIGHT;
			
		}else if(ID > 67 && ID < 72){
			x = .55f * TILEWIDTH;
			x+=TILEWIDTH*(ID-68);
			y = 0.355f * TILEHEIGHT;
			
		}
		
		if(pathRotation(ID) == -27){
			x+=TILEWIDTH * .175f;
			y+=TILEHEIGHT * .017f;
		}else if(pathRotation(ID) == 27){
			x+= TILEWIDTH * -0.00f;
			y+=TILEHEIGHT * .105f;
		}
		
		
		return new Vector3(x,y,0);
	}

	public static float pathRotation(int ID) {
		if(ID < 24){
			if(ID%2 == 0){
				return 27;
			}else{
				return -27;
			}
		}else if(ID < 48){
			if(ID%2 == 0){
				return -27;
			}else{
				return 27;
			}
		}
		return 90;
	}

	public static void setupGametoPath(Path[] paths) {
		// TODO Auto-generated method stub
		
	}
}
