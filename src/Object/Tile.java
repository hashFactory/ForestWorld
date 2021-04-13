package Object;

import World.World;
import java.awt.*;

public class Tile {
    int x;
    int y;
    int z = 0;
    static int uidTracker = 0;
    int uid = 0;
    int state = 0;
    int type = 0;
    int texture = "Default".hashCode();

    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
        this.uid = Tile.uidTracker;
        Tile.uidTracker++;
    }

    public void setTexture(String name) {
        this.texture = name.hashCode();
    }

    public void setTexture(int uid) {
        this.texture = uid;
    }

    public boolean step(World w) {
        return true;
    }

    public int getTexture() {
        return this.texture;
    }
}
