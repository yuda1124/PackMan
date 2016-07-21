package packman;

public class Map {
	public int[][] wall;
	int numX;
	int numY;

	public Map() {
		numX = 20;
		numY = 20;
		wall = new int[numX][numY];
		for (int i = 0; i < 20; i++)
			for (int j = 0; j < 20; j++)
				wall[i][j] = 0;

	}

	public void printWall() {
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++){
				System.out.print(wall[j][i] + " ");
				if(wall[j][i] == 0) System.out.print(" ");
			}
				System.out.println();
		}
	}

	public void makeWall() {
		for (int i = 0; i < 20; i++)
			wall[0][i] = -1;
		for (int i = 0; i < 20; i++)
			wall[19][i] = -1;
		for (int i = 0; i < 20; i++)
			wall[i][0] = -1;
		for (int i = 0; i < 20; i++)
			wall[i][19] = -1;
		for (int i = 0; i < 40; i++) {
			int x = (int) (Math.random() * 18 + 1);
			int y = (int) (Math.random() * 18 + 1);
			if (wall[x][y] == -1)
				i--;
			else
				wall[x][y] = -1;
		}
	}

	public boolean isWall(int x, int y) {
		if (wall[x][y] == -1)
			return true;
		
		return false;
	}
}
