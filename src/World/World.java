package World;

public class World {

    public int[][] myWorld;
    public int width;
    public int height;

    public World(int width, int height) {
        this.width = width;
        this.height = height;
        this.myWorld = new int[width][height];

        for ( int i = 0 ; i != width ; i++ )
            for ( int j = 0 ; j != height ; j++ )
                this.myWorld[i][j] = (int)(Math.random()*3.0);
    }
}
