
import Assets.TextureManager;
import World.World;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ForestWorld extends JPanel {

    private JFrame frame;
    private TextureManager tm;

    private int spriteLength = 32;
    private World w;

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

        frame = new JFrame("World of Sprite");
        frame.add(this);
        frame.setSize(800,800); //agrandi la fenêtre d'affichage
        frame.setVisible(true);

        w = new World(50, 50);
    }

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;

        //Création d'un volcan (aucune erruption)
        for ( int i = 10 ; i < 11 ; i++ ) {
            for ( int j = 10; j < w.myWorld[i][11]; j++ ){
                g2.drawImage(tm.getImage("Volcan"),spriteLength*i,spriteLength*j,spriteLength,spriteLength, frame);
            }
        }

        // Création de la terre et de la roche

        //Création de verdure (arbres,herbes)
        /* Disperser les arbres au hasard grâce à un maths.random */
        for ( int i = 0 ; i < w.myWorld.length ; i++ ) {
            for ( int j = 0 ; j < w.myWorld[0].length ; j++ ) {
                if ( w.myWorld[i][j] == 2 ) {
                    g2.drawImage(tm.getImage("Tree"),spriteLength*i,spriteLength*j,spriteLength,spriteLength, frame);
                } else {
                    g2.drawImage(tm.getImage("Grass"),spriteLength*i,spriteLength*j,spriteLength,spriteLength, frame);
                }
            }
        }

        // Création d'un ciel bleu avec nuage (sans pluie)
        /* La position du ciel et des nuages est fixe, l'image doit cependant changer durant la pluie */
        for ( int i = 0 ; i < w.myWorld.length ; i++ ) {
            for ( int j = 1 ; j < 3 ; j++ ) {
                g2.drawImage(tm.getImage("Sky"),spriteLength*i,spriteLength*j,spriteLength,spriteLength, frame);
            }
            for ( int j = 0 ; j < 1 ; j++ ) {
                g2.drawImage(tm.getImage("Cloud"),spriteLength*i,spriteLength*j,spriteLength,spriteLength, frame);
            }
        }


        //Création de l'étang
        for ( int i = 9; i < (w.myWorld.length/2) ; i++ ) {
            for ( int j = 10 ; j < 14 ; j++ ) {
                g2.drawImage(tm.getImage("Water"),spriteLength*i,spriteLength*j,spriteLength,spriteLength, frame);
            }
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