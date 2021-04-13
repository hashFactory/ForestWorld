package Engine;

import Assets.TextureManager;
import Misc.Output;
import World.World;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Engine extends JPanel implements Runnable {
    double reqFPS = 20;
    double realFPS = 0.0; // TODO: implémenter
    int mspfps;

    int frameNumber = 0;

    long timeAtLastTick;
    Thread mainThread;

    private TextureManager tm;
    private RenderEngine re;
    private World w;
    public JPanel jp;

    private BufferedImage bi;

    public Engine(int reqFPS) {
        // précharger les textures pour le jeu
        try {
            tm = new TextureManager("res/images/");
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }

        this.re = new RenderEngine(800, 800);
        this.bi = new BufferedImage(this.re.width, this.re.height, BufferedImage.TYPE_3BYTE_BGR);

        this.reqFPS = reqFPS;
        this.mspfps = (int)(1000.0 / this.reqFPS);

        this.jp = this;
        this.w = new World(50, 50);
    }

    public void start() {
        //this.run();
        mainThread = new Thread(this);
        mainThread.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        //super.paintComponent(g);
        g.drawImage(this.re.newImage(this.w, this.tm, this.bi, this), this.frameNumber % 800, 0, 800, 800, this);
        //g.drawString("Frame number: " + this.frameNumber, 200, 200);
        this.frameNumber++;
        this.updateUI();
        Output.infoln("Frame number: " + this.frameNumber);
        //this.r.paint(g, this.w, this.tm, this.frame);
    }

    @Override
    public void run() {
        this.timeAtLastTick = System.currentTimeMillis();
        while (true)
        {
            //execute();
            this.w.update();
            System.out.println("Updated");

            long timeSinceLastTick = System.currentTimeMillis() - this.timeAtLastTick;

            try {
                Thread.sleep(1 + this.mspfps - timeSinceLastTick);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            timeAtLastTick = System.currentTimeMillis();
        }
    }
}
