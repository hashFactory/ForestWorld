package World;

import java.io.FileOutputStream;

public class WorldSaver {

    private String filename;
    private FileOutputStream fos;

    public WorldSaver(String filename) {
        this.filename = filename;
        /*try {
            //fos = new FileOutputStream();
        } catch () {

        }*/
    }

    public static boolean save(World w, String filename) {
        return true;
    }
}
