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

        int[] textures = {"Volcan".hashCode(), "Tree".hashCode(), "Grass".hashCode(),
            "Sky".hashCode(), "Cloud".hashCode(), "Water".hashCode()};

        for ( int i = 0 ; i < w.myWorld.length ; i++ ) {
            for ( int j = 0 ; j < w.myWorld[0].length ; j++ ) {
                Texture tex = tm.get( textures[ w.myWorld[i][j] ] );
                int x = (int)((tex.width * i - (int)xcenter) * zoom);
                int y = (int)((tex.height * j - (int)ycenter) * zoom);
                if (x + (tex.width * tex.scale) >= 0 && x < this.width && y + (tex.height * tex.scale) >= 0 && y < this.height)
                    g2.drawImage(tex.scaledImage, x, y, io);
            }
        }

        Output.infoln("Time since last frame: " + (System.currentTimeMillis() - this.timeAtLastFrame) + "ms");
        this.timeAtLastFrame = System.currentTimeMillis();

        return true;
    }

    @Deprecated
    public Image newImage(World w, TextureManager tm, BufferedImage bi, ImageObserver io) {
        Graphics2D g2 = (Graphics2D)bi.getGraphics();
        //g2.clearRect(0, 0, this.width, this.height);

        int[] textures = {"Volcan".hashCode(), "Tree".hashCode(), "Default".hashCode(),
                            "Sky".hashCode(), "Cloud".hashCode(), "Water".hashCode()};

        // Création de la terre et de la roche
        //Création de verdure (arbres,herbes)
        /* Disperser les arbres au hasard grâce à un maths.random */
        for ( int i = 0 ; i < w.myWorld.length ; i++ ) {
            for ( int j = 0 ; j < w.myWorld[0].length ; j++ ) {
                Texture tex = tm.get( textures[ w.myWorld[i][j] ] );
                g2.drawImage(tex.scaledImage, tex.width * i, tex.height * j, tex.width, tex.height, io);
            }
        }

        // Création d'un ciel bleu avec nuage (sans pluie)
        /* La position du ciel et des nuages est fixe, l'image doit cependant changer durant la pluie */
        /*for ( int i = 0 ; i < w.myWorld.length ; i++ ) {
            for ( int j = 1 ; j < 3 ; j++ ) {
                g2.drawImage(sky.image, sky.width * i, sky.height * j, sky.width, sky.height, io);
            }
            for ( int j = 0 ; j < 1 ; j++ ) {
                g2.drawImage(cloud.image, cloud.width * i, cloud.height * j, cloud.width, cloud.height, io);
            }
        }*/

        //Création de l'étang
        /*for ( int i = 9; i < (w.myWorld.length/2) ; i++ ) {
            for ( int j = 10 ; j < 14 ; j++ ) {
                g2.drawImage(water.image, water.width * i, water.height * j, water.width, water.height, io);
            }
        }*/

        Output.infoln("Time since last frame: " + (System.currentTimeMillis() - this.timeAtLastFrame) + "ms");
        this.timeAtLastFrame = System.currentTimeMillis();
        return bi;
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
