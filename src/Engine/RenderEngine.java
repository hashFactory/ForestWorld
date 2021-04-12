package Engine;

import Assets.Texture;
import Assets.TextureManager;
import World.World;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class RenderEngine {

    private BufferedImage frame;
    public int width = 800;
    public int height = 800;

    //private TextureManager tm;

    public RenderEngine(int width, int height) {
        this.width = width;
        this.height = height;

        this.frame = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
    }

    public Image newImage(World w, TextureManager tm, JFrame frame) {
        Graphics2D g2 = (Graphics2D)this.frame.getGraphics();
        g2.clearRect(0, 0, this.width, this.height);

        Texture volcan = tm.getTexture("Volcan");
        Texture tree = tm.getTexture("Tree");
        Texture grass = tm.getTexture("Grass");
        Texture sky = tm.getTexture("Sky");
        Texture cloud = tm.getTexture("Cloud");
        Texture water = tm.getTexture("Water");

        //Création d'un volcan (aucune erruption)
        for ( int i = 10 ; i < 11 ; i++ ) {
            for ( int j = 10; j < w.myWorld[i][11]; j++ ) {
                g2.drawImage(volcan.image, volcan.width, volcan.height * j, volcan.width, volcan.height, frame);
            }
        }

        // Création de la terre et de la roche

        //Création de verdure (arbres,herbes)
        /* Disperser les arbres au hasard grâce à un maths.random */
        for ( int i = 0 ; i < w.myWorld.length ; i++ ) {
            for ( int j = 0 ; j < w.myWorld[0].length ; j++ ) {
                if ( w.myWorld[i][j] == 2 ) {
                    g2.drawImage(tree.image, tree.width * i, tree.height * j, tree.width, tree.height, frame);
                } else {
                    g2.drawImage(grass.image, grass.width * i, grass.height * j, grass.width, grass.height, frame);
                }
            }
        }

        // Création d'un ciel bleu avec nuage (sans pluie)
        /* La position du ciel et des nuages est fixe, l'image doit cependant changer durant la pluie */
        for ( int i = 0 ; i < w.myWorld.length ; i++ ) {
            for ( int j = 1 ; j < 3 ; j++ ) {
                g2.drawImage(sky.image, sky.width * i, sky.height * j, sky.width, sky.height, frame);
            }
            for ( int j = 0 ; j < 1 ; j++ ) {
                g2.drawImage(cloud.image, cloud.width * i, cloud.height * j, cloud.width, cloud.height, frame);
            }
        }


        //Création de l'étang
        for ( int i = 9; i < (w.myWorld.length/2) ; i++ ) {
            for ( int j = 10 ; j < 14 ; j++ ) {
                g2.drawImage(water.image, water.width * i, water.height * j, water.width, water.height, frame);
            }
        }

        return this.frame;
    }

}
