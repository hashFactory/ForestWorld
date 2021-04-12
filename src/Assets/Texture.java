package Assets;

import java.awt.image.BufferedImage;

public class Texture {
    public static int _id = 0;

    public int id;
    public String name;
    public BufferedImage image;

    public Texture(String name) {
        //this.id = id;
        this.name = name;
    }

    public Texture(String name, BufferedImage image) {
        //this.id = id;
        this.name = name;
        this.image = image;
    }

    public BufferedImage getImage() {
        return image;
    }
}
