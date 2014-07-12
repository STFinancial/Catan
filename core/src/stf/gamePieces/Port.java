package stf.gamePieces;

import stf.main.CoordinateUtil;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Port {
	PortType type;
	int index;
	public Port(PortType portType, int index) {
		type = portType;
		this.index = index;
		updateSprite();
	}
	Sprite sprite;
	Sprite typeSprite;
	
	private void updateSprite(){
		sprite = new Sprite(new Texture("port.gif"));
		sprite.setBounds(CoordinateUtil.portToGame(index).x,CoordinateUtil.portToGame(index).y, 256, 256);
		sprite.rotate(CoordinateUtil.portToGameRotation(index));
		typeSprite = new Sprite(type.getImage());
		typeSprite.setBounds(CoordinateUtil.portTypeToGame(index).x,CoordinateUtil.portTypeToGame(index).y, 64, 64);
	}

	public Sprite getSprite() {
		return sprite;
	}

	public Sprite getTypeSprite() {
		return typeSprite;
	}
	
	public String toString(){
		return "number " + index + " of type " + type;
	}
}
