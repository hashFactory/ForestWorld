package World;

public class World {

    public int[][] myWorld;
    public int width;
    public int height;
    public int tick;

    public World(int width, int height) {
        this.width = width;
        this.height = height;
        this.myWorld = new int[width][height];

        this.tick = 0;

        this.generateWorld();
    }

    public void generateWorld() {
        for ( int i = 0 ; i != this.width ; i++ )
            for ( int j = 0 ; j != this.height ; j++ )
                this.myWorld[i][j] = (int)(Math.random() * 3.0);
    }

    public void update() {

    }
}
