package Engine;

import Assets.TextureManager;
import Misc.Output;
import World.World;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Engine extends JPanel implements Runnable {
    double tps;
    double realFPS = 0.0; // TODO: implémenter
    int mspt;

    int frameNumber = 0;
    int tick = 0;

    long timeAtLastTick;
    Thread mainThread;
    Thread renderThread;

    private TextureManager tm;
    private RenderEngine re;
    private World w;
    public JPanel jp;

    private BufferedImage bi;

    public Engine(int tps) {
        // précharger les textures pour le jeu
        try {
            tm = new TextureManager("res/images/");
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }

        this.re = new RenderEngine(800, 800, 60, this);
        this.bi = new BufferedImage(this.re.width, this.re.height, BufferedImage.TYPE_3BYTE_BGR);

        this.tps = tps;
        this.mspt = (int)(1000.0 / this.tps);

        this.jp = this;
        this.w = new World(50, 50);
    }

    public void start() {
        //this.run();
        mainThread = new Thread(this);
        mainThread.start();

        this.renderThread = new Thread(this.re);
    }

    @Override
    public void paintComponent(Graphics g) {
        //super.paintComponent(g);

        g.drawImage(this.re.newImage(this.w, this.tm, this.bi, this), 0, 0, 800, 800, this);
        g.setColor(Color.BLACK);
        g.fillRect(10, 10, 300, 30);
        g.setColor(Color.WHITE);
        g.drawString("Frame number: " + this.frameNumber + "\nTick number: " + this.tick, 10, 30);
        this.frameNumber++;
        this.updateUI();
        //Output.infoln("Frame number: " + this.frameNumber);
        //this.r.paint(g, this.w, this.tm, this.frame);
    }

    @Override
    public void run() {
        this.timeAtLastTick = System.currentTimeMillis();
        while (true)
        {
            //execute();
            this.w.update();
            this.tick++;

            long timeSinceLastTick = System.currentTimeMillis() - this.timeAtLastTick;
            //System.out.println("Updated world after " + timeSinceLastTick);

            try {
                Thread.sleep(1 + this.mspt - timeSinceLastTick);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            timeAtLastTick = System.currentTimeMillis();
        }
    }
}
