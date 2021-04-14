package World;

import Object.Tile;

public class World {

    public int[][] myWorld; // représente les tuiles où chaque ID est le hashcode de cette tuile
    public Tile[][] tileWorld; // va représenter avec des objets riches
    public int twidth;
    public int theight;
    public int tick;

    public World(int twidth, int theight) {
        this.twidth = twidth;
        this.theight = theight;
        this.myWorld = new int[twidth][theight];
        this.tileWorld = new Tile[twidth][theight];

        this.tick = 0;

        this.generateWorld();
    }

    public void generateWorld() {
        // générer aléatoirement les tuiles
        // TODO: implémenter générateur plus intelligent
        for (int i = 0; i != this.twidth; i++ )
            for ( int j = 0 ; j != this.theight ; j++ )
                this.myWorld[i][j] = (int)(Math.random() * 6.0);
    }

    public void update() {
        // modifier aléatoirement les tuiles avec 1% de chance
        for (int i = 0; i < this.twidth; i++) {
            for (int j = 0; j < this.theight; j++) {
                if (Math.random() < 0.01)
                    this.myWorld[i][j] = (int)(Math.random() * 6.0);
            }
        }
        // TODO: mettre une boucle qui itère sur toute les tuiles
    }
}
