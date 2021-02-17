

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Sprites extends JPanel {


    private JFrame frame;

    private Image grassSprite;
    private Image treeSprite;
    private Image skySprite;
    private Image cloudSprite;
    private Image volcanSprite;
    private Image waterSprite;


    private int spriteLength = 32;

    private int[][] myWorld;

    public Sprites()
    {
        try
        {
            grassSprite = ImageIO.read(new File("Grass.png"));
            treeSprite = ImageIO.read(new File ("tree.png"));
            skySprite = ImageIO.read (new File ("Sky.png"));
            cloudSprite = ImageIO.read (new File ("Cloud.png"));
            volcanSprite = ImageIO.read (new File ("Volcan.png"));
            waterSprite = ImageIO.read (new File ("Water.png"));
            //BurningTree1Sprite = ImageIO.read(new File("BurningTree1.png"));
            //BurningTree2Sprite = ImageIO.read(new File("BurningTree2.png"));
            //BurningTree3Sprite = ImageIO.read(new File("BurningTree3.png"));
            // DeadTree1Sprite = ImageIO.read(new File("DeadTree1Sprite.png"));
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.exit(-1);
        }

        frame = new JFrame("World of Sprite");
        frame.add(this);
        frame.setSize(1000,1000); //agrandi la fenêtre d'affichage
        frame.setVisible(true);

        myWorld = new int[50][50];

        for ( int i = 0 ; i != 50 ; i++ )
            for ( int j = 0 ; j != 50 ; j++ )
                myWorld[i][j] = (int)(Math.random()*3.0);
    }

    public void paint(Graphics g)
    {
        Graphics2D g2 = (Graphics2D)g;

        //Création d'un volcan (aucune erruption)
        for ( int i = 10 ; i < 11 ; i++ ){
            for ( int j = 10; j < myWorld[i][11]; j++ ){
                g2.drawImage(volcanSprite,spriteLength*i,spriteLength*j,spriteLength,spriteLength, frame);
            }
        }

        // Création de la terre et de la roche

        //Création de verdure (arbres,herbes)
        /* Disperser les arbres au hasard grâce à un maths.random */
        for ( int i = 0 ; i < myWorld.length ; i++ )
        {
            for ( int j = 0 ; j < myWorld[0].length ; j++ )
            {
                if ( myWorld[i][j] == 2 ){
                    g2.drawImage(treeSprite,spriteLength*i,spriteLength*j,spriteLength,spriteLength, frame);
                }
                else {
                    g2.drawImage(grassSprite,spriteLength*i,spriteLength*j,spriteLength,spriteLength, frame);
                }

            }
        }

        // Création d'un ciel bleu avec nuage (sans pluie)
        /* La position du ciel et des nuages est fixe, l'image doit cependant changer durant la pluie */
        for ( int i = 0 ; i < myWorld.length ; i++ ){
            for ( int j = 1 ; j < 3 ; j++ ){
                g2.drawImage(skySprite,spriteLength*i,spriteLength*j,spriteLength,spriteLength, frame);
            }
            for ( int j = 0 ; j < 1 ; j++ ) {
                g2.drawImage(cloudSprite,spriteLength*i,spriteLength*j,spriteLength,spriteLength, frame);
            }
        }


        //Création de l'étang
        for ( int i = 9; i < (myWorld.length/2) ; i++ ){
            for ( int j = 10 ; j < 14 ; j++ ){
                g2.drawImage(waterSprite,spriteLength*i,spriteLength*j,spriteLength,spriteLength, frame);
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
        new Sprites();
    }
}