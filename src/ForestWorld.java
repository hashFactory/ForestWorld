import Engine.Engine;

import javax.swing.*;

public class ForestWorld {
    private JFrame frame;
    private Engine en;

    public ForestWorld() {
        frame = new JFrame("World of Sprite") {
            //@Override

        };

        this.en = new Engine(60);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,800); //agrandi la fenêtre d'affichage
        frame.getContentPane().add(this.en.jp);
        frame.pack();

        frame.setVisible(true);

        this.en.start();
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