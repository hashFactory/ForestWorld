import Assets.TextureManager;
import Engine.TickHandler;
import Misc.Output;
import World.World;
import Engine.RenderEngine;

import javax.swing.*;
import java.awt.*;

public class ForestWorld extends JPanel implements Runnable {
    private JFrame frame;
    private TextureManager tm;
    private RenderEngine r;

    private int spriteLength = 32;
    private World w;
    private int reqFPS = 60;
    private double mspfs = (1000.0 / (double)reqFPS);
    //private TickHandler th;

    //private int[][] myWorld;

    public ForestWorld() {
        // précharger les textures pour le jeu
        try {
            tm = new TextureManager("res/images/");
        }
        catch(Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }

        this.r = new RenderEngine(1600, 1600);

        JFrame.setDefaultLookAndFeelDecorated(true);
        frame = new JFrame("World of Sprite");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
        frame.setSize(800,800); //agrandi la fenêtre d'affichage
        frame.setVisible(true);

        this.w = new World(50, 50);
        //th = new TickHandler(60);
    }

    public void paint(Graphics g) {
        g.drawImage(this.r.newImage(this.w, this.tm, this.frame), 0, 0, 800, 800, this);
        //this.r.paint(g, this.w, this.tm, this.frame);
    }

    @Override
    public void run()
    {
        Thread.currentThread().setPriority(Thread.MAX_PRIORITY);

        long timeAtLastTick = System.currentTimeMillis();
        long timeSinceLastTick = System.currentTimeMillis();
        while (true)
        {
            repaint();
            timeSinceLastTick = System.currentTimeMillis()-timeAtLastTick;

            try {
                Thread.sleep((long)(this.mspfs-timeSinceLastTick));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            double FPS = 1000.0 / (System.currentTimeMillis()-timeAtLastTick);
            Output.infoln("Render - " + FPS + " , " + timeSinceLastTick);

            timeAtLastTick = System.currentTimeMillis();
        }

    }

    /* Création de rives pour entourer l'étang si possible ??? */

	/* Bruler un arbre
		- if lava sur la meme case que herbe ou tree
			- Faire itérer les 3 images de l'arbre qui brêule*
			- Remplacer l'herbe par la terre (la lave ne brûle ni l'eau, la roche ou la terre)
	*/

	/* Eruption du volcan
		 - Utiliser un scanf pour demander à l'utilisateur de faire exploser le volcan ( avec restriction de le faire exploser plus d'un certain nombre de fois)
		 OU
		 - Faire exploser le volcan au hasard (doit cependant avoir une probabilité plus basse que celle de la pluie)

	*/

	/* Faire Pleuvoir
		- Utiliser un scanf pour demander à l'utilisateur de faire tomber de la pluie
		OU
		- Faire tomber de la pluie au hasard après un certain temps (avec une itération ??)
		OU
		_ Faire une règle disant que de la pluie tombe après ou avant chaque éruption volcanique.
	*/



    public static void main(String[] args) {
        new ForestWorld();
    }
}