package packman;

public class Character {
	int Cx, Cy;
	Map map;
	Character(Map map){
		Cx = 1;
		Cy = 1;
		this.map = map;
	}
	public void moveRight(){
		if(!(map.isWall(Cx+1, Cy)))
		Cx++;
	}
	public void moveLeft(){
		if(!(map.isWall(Cx-1, Cy)))
		Cx--;
	}
	public void moveUp(){
		if(!(map.isWall(Cx, Cy-1)))
		Cy--;
	}
	public void moveDown(){
		if(!(map.isWall(Cx, Cy+1)))
		Cy++;
	}
	public int getCx(){
		return Cx;
	}
	public int getCy(){
		return Cy;
	}
}
