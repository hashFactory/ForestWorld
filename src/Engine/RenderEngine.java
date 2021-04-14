package Engine;

import Assets.Texture;
import Assets.TextureManager;
import Misc.Output;
import World.World;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public class RenderEngine implements Runnable {
    long timeAtLastFrame = 0;
    long[] lastFrameTimes = new long[100];
    int lastFrameNumber = 0;

    private BufferedImage frame;
    public long timeAtLastTick = 0;
    public int width = 800;
    public int height = 800;

    public int reqFPS = 60;
    public long mspf = 1000 / this.reqFPS;

    public double xcenter = 0;
    public double ycenter = 0;
    public double zoom = 1;

    public World w;
    public Engine en;

    //private TextureManager tm;

    public RenderEngine(int width, int height, int reqFPS, Engine en) {
        this.width = width;
        this.height = height;

        this.reqFPS = reqFPS;
        this.mspf = 1000 / this.reqFPS;

        this.frame = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        this.w = en.w;
        this.en = en;

        this.xcenter = this.en.getWidth() / 2.0;
        this.ycenter = this.en.getHeight() / 2.0;
    }

    public boolean draw(Graphics2D g2, World w, TextureManager tm, ImageObserver io) {
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, this.width, this.height);

        // hardcode des valeurs pour l'instant
        int[] textures = {"Volcan".hashCode(), "Tree".hashCode(), "Grass".hashCode(),
            "Sky".hashCode(), "Cloud".hashCode(), "Water".hashCode()};

        for ( int i = 0 ; i < w.myWorld.length ; i++ ) {
            for ( int j = 0 ; j < w.myWorld[0].length ; j++ ) {
                // récupérer la texture et l'afficher
                Texture tex = tm.get( textures[ w.myWorld[i][j] ] );
                int x = (int)((tex.width * i) * zoom) - (int)xcenter;
                int y = (int)((tex.height * j) * zoom) - (int)ycenter;
                if (x + (tex.width * tex.scale) >= 0 && x < this.width && y + (tex.height * tex.scale) >= 0 && y < this.height)
                    g2.drawImage(tex.scaledImage, x, y, io);
            }
        }

        Output.infoln("Time since last frame: " + (System.currentTimeMillis() - this.timeAtLastFrame) + "ms");
        this.timeAtLastFrame = System.currentTimeMillis();

        return true;
    }

    @Override
    public void run() {
        this.timeAtLastTick = System.currentTimeMillis();
        while (true)
        {
            //Output.debugln("Updated");
            this.width = this.en.getWidth();
            this.height = this.en.getHeight();

            //this.xcenter = this.width / 2.0;
            //this.ycenter = this.height / 2.0;

            en.repaint();

            long timeSinceLastFrame = System.currentTimeMillis() - this.timeAtLastTick;
            //Output.infoln("Time since last frame: " + timeSinceLastTick + "ms");

            try {
                Thread.sleep( this.mspf - timeSinceLastFrame);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.exit(1);
            }

            this.timeAtLastTick = System.currentTimeMillis();
        }
    }
}
