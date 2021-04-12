import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.HashMap;

public class TextureManager {
    // peut etre rendre public plus tard
    private HashMap<String, Texture> textures;
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
                    textures.put(basename, newTexture);
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
        if (!textures.containsKey(name)) {
            Output.warnln("Could not find texture \"" + name + "\", defaulting.");
            System.exit(1);
        }
        return textures.get(name);
    }

    // récupérer l'image selon nom
    public BufferedImage getImage(String name) {
        return getTexture(name).image;
    }

    /*
    // récupérer l'image selon index
    public BufferedImage getImage(int id) {

    }*/

}
