package Assets;

import Misc.Output;

import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class TextureManager {
    // peut etre rendre public plus tard
    private HashMap<Integer, Texture> textures;
    //private ArrayList<Integer> t;

    //private ArrayList<Texture> t;
    //private BufferedImage defaultTexture;

    // constructeur vide au cas où on veux initialiser avant qu'on sache où chercher
    public TextureManager() {textures = new HashMap<>();
    }

    // parcourir répertoire et ajouter toutes les textures
    public TextureManager(String dir) {
        this();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        URL url = loader.getResource("images");
        String path = url.getPath();

        // juste pour confirmer, afficher les textures
        File [] files = new File(path).listFiles();
        for (File f: files)
            System.out.println(f.getAbsolutePath());

        // chercher dans le répertoire
        try {
            File textureDirectory = new File(dir);
            Output.infoln("Loading textures from \"" + textureDirectory.getAbsolutePath() + "\"\n");
            var textureFiles = textureDirectory.listFiles();

            // pour chaque fichier, essayer de créer une nouvelle texture pour
            assert textureFiles != null;
            for (File textureFile : textureFiles) {
                String filename = textureFile.getName();
                Output.infoln("Loading " + filename + " ...");
                // que ajouter des images
                if (filename.contains(".png") || filename.contains(".jpg")) {
                    BufferedImage img = ImageIO.read(textureFile);
                    String basename = filename.replace(".png", "").replace(".jpg", "");
                    Texture newTexture = new Texture(basename, img);
                    textures.put(basename.hashCode(), newTexture);
                }
            }
        } catch (Exception e) {
            // au cas où on ne trouve pas le répertoire
            Output.errorln("Could not load texture directory \"" + dir + "\"");
            Output.errorln(e.getMessage());
            System.exit(1);
        }
    }

    // récupérer texture selon nom
    public Texture getTexture(String name) {
        if (!textures.containsKey(name.hashCode())) {
            Output.warnln("Could not find texture \"" + name + "\", defaulting.");
            System.exit(1);
        }
        return textures.get(name.hashCode());

        //chercher parmi les textures
        /*for (Texture t: textures)
            if (t.name.equals(name))
                return t;

        // si on ne la trouve pas
        Output.warnln("Could not find texture \"" + name + "\"");

        // on en donne une par défaut
        if (textures.size() > 0) {
            Output.warnln("defaulting to texture[0]");
            return textures.get(0);
        }

        // si on en a aucune, erreur
        Output.errorln("Could not default to textures[0] since TextureManager is empty.");
        System.exit(1);
        return null;*/
    }

    public void updateTextures(double scale) {
        // si la taille de la fenêtre change, alors on remet à jour les textures scaled
        AffineTransform at = new AffineTransform();
        at.scale(scale, scale);
        AffineTransformOp scaleOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);

        // parcourir chaque texture pour la mettre à jour
        for (Texture t: this.textures.values())
            t.updateScaledImage(scaleOp, scale);
    }

    public Texture get(int hashcode) {
        return this.textures.get(hashcode);
    }

    // récupérer l'image selon nom
    public BufferedImage getImage(String name) {
        return getTexture(name).image;
    }
}
