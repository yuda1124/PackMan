package packman;

public class ghost {
	int Gx, Gy;
	Map map;
	int [][]isSearched;
	public ghost(int x, int y, Map map) {
		Gx = x;
		Gy = y;
		isSearched = new int [20][20];
		resetSearchBoard();
		this.map = map;
				
	}

	public void moveRight() {
		Gx++;
	}

	public void moveLeft() {
		Gx--;
	}

	public void moveUp() {
		Gy--;
	}

	public void moveDown() {
		Gy++;
	}

	public int getGx() {
		return Gx;
	}

	public int getGy() {
		return Gy;
	}

	public void moveGhost(int x, int y) {
		if(isNear(x,y)){
			chaseCharacter(x,y);
			 resetSearchBoard();
		}
		else {
			int rand = (int) (Math.random() * 4);
			if (rand == 0) {
				if (!map.isWall(this.Gx, this.Gy - 1)){
					this.Gy--;
				}
					
			} else if (rand == 1) {
				if (!map.isWall(this.Gx, this.Gy + 1))
					this.Gy++;
			} else if (rand == 2) {
				if (!map.isWall(this.Gx + 1, this.Gy))
					this.Gx++;
			} else if (rand == 3) {
				if (!map.isWall(this.Gx - 1, this.Gy))
					this.Gx--;
			}
		}
	}
	
	public boolean isNear(int x, int y){
		int tempX = Gx - x;
		int tempY = Gy - y;
		tempX *= tempX;
		tempY *= tempY;
		if(tempX + tempY <= 32) return true;
		return false;
	}
	
	public void chaseCharacter(int x, int y){
		int left = 1000;
		int right = 1000;
		int up = 1000;
		int down = 1000;
		if(!map.isWall(Gx-1, Gy)) left = calRoute(x, y, Gx-1, Gy);
		if(!map.isWall(Gx+1, Gy)) right = calRoute(x, y, Gx+1, Gy);
		if(!map.isWall(Gx, Gy+1)) down = calRoute(x, y, Gx, Gy+1);
		if(!map.isWall(Gx, Gy-1))up = calRoute(x, y, Gx, Gy-1);
		int m = min(left,right,down,up);
		if(m == left) Gx--;
		else if(m == right) Gx++;
		else if(m == up) Gy--;
		else Gy++;
	}
	
	public int calRoute(int Ox, int Oy, int Cx, int Cy){
		System.out.println(Cx + " " + Cy);
		if(Ox >= Cx && Oy >= Cy){
			int down = 1000;
			int right = 1000;
			if(Ox == Cx && Oy == Cy) return 0;
			else {
				if(Oy > Cy && Cy+1 < 20 && !map.isWall(Cx, Cy+1)) down = calRoute(Ox, Oy, Cx, Cy+1);
				if(Ox > Cx && Cx+1 < 20 && !map.isWall(Cx+1, Cy)) right = calRoute(Ox, Oy, Cx+1, Cy);
				return min(down, right)+1;
			}
		}
		else if(Ox >= Cx && Oy <= Cy){
			int up = 1000;
			int right = 1000;
			if(Ox == Cx && Oy == Cy) return 0;
			else {
				if(Oy < Cy && Cy-1 > 0 && !map.isWall(Cx, Cy-1)) up = calRoute(Ox, Oy, Cx, Cy-1);
				if(Ox > Cx && Cx+1 < 20 && !map.isWall(Cx+1, Cy)) right = calRoute(Ox, Oy, Cx+1, Cy);
				return min(up, right)+1;
			}
		}
		else if(Ox <= Cx && Oy >= Cy){
			int up = 1000;
			int right = 1000;
			if(Ox == Cx && Oy == Cy) return 0;
			else {
				if(Oy > Cy && Cy+1 <20  && !map.isWall(Cx, Cy+1)) up = calRoute(Ox, Oy, Cx, Cy+1);
				if(Ox < Cx && Cx-1 > 0 && !map.isWall(Cx-1, Cy)) right = calRoute(Ox, Oy, Cx-1, Cy);
				return min(up, right)+1;
			}
		}
		else {
			int up = 1000;
			int right = 1000;
			if(Ox == Cx && Oy == Cy) return 0;
			else {
				if(Oy < Cy && Cy-1 > 0  && !map.isWall(Cx, Cy-1)) up = calRoute(Ox, Oy, Cx, Cy-1);
				if(Ox < Cx && Cx-1 > 0 && !map.isWall(Cx-1, Cy)) right = calRoute(Ox, Oy, Cx-1, Cy);
				return min(up, right)+1;
			}
		}
	}
	
	public int min(int a, int b){
		int min = a;
		if(b < min) min = b;
		return min;
	}
	public int min(int a, int b, int c, int d){
		int min = a;
		if(b < min) min = b;
		if(c < min) min = c;
		if(d < min) min = d;
		return min;
	}
	
	public void resetSearchBoard(){
		for(int i = 0; i < 20; i++)
			for(int j = 0; j < 20; j++)
				isSearched[i][j] = 100;
	}
}
