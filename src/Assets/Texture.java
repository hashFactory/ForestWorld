package Assets;

import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;

public class Texture {
    public static int _id = 0;

    public int id;
    public String name;
    public BufferedImage image;
    public BufferedImage scaledImage;
    public double scale;
    public int width;
    public int height;

    public Texture(String name) {
        //this.id = id;
        this.name = name;
    }

    public Texture(String name, BufferedImage image) {
        //this.id = id;
        this.name = name;
        this.image = image;
        this.width = image.getWidth();
        this.height = image.getHeight();
    }

    public BufferedImage getImage() {
        return this.image;
    }

    public BufferedImage getScaledImage() {
        return this.scaledImage;
    }

    public void updateScaledImage(AffineTransformOp op, double scale) {
        // effectue la transformation
        this.scale = scale;
        this.scaledImage = new BufferedImage((int)(this.width * scale) + 2, (int)(this.height * scale) + 2, BufferedImage.TYPE_INT_ARGB);
        this.scaledImage = op.filter(this.image, this.scaledImage);
    }
}
