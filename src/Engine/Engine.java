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
    public boolean isPaused = false;
    public boolean showStats = true;

    long timeAtLastTick;
    Thread mainThread;
    Thread renderThread;

    private TextureManager tm;
    private RenderEngine re;
    protected World w;
    public KeyboardHandler kh;

    public JPanel jp;

    private BufferedImage bi;

    public Engine(int tps) {
        // précharger les textures pour le jeu
        try {
            tm = new TextureManager("res/images/");
            tm.updateTextures(1.0);
        } catch (Exception e) {
            Output.errorln("");
            e.printStackTrace();
            System.exit(-1);
        }

        // init des structures
        this.w = new World(40, 40);
        this.re = new RenderEngine(600, 600, 70, this);
        this.bi = new BufferedImage(this.re.width, this.re.height, BufferedImage.TYPE_3BYTE_BGR);

        // temps par tick
        this.tps = tps;
        this.mspt = (int)(1000.0 / this.tps);

        this.jp = this;
        this.kh = new KeyboardHandler(this, jp.getInputMap(), jp.getActionMap());
    }

    public void start() {
        //this.run();
        mainThread = new Thread(this);
        mainThread.start();

        this.renderThread = new Thread(this.re);
        this.renderThread.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        //super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        this.interpret();
        this.re.draw(g2, this.w, this.tm, this);

        // écrire des stats par dessus
        if (this.showStats) {
            g.setColor(new Color(0, 0, 0, 50));
            g.fillRect(10, 10, 300, 30);
            g.setColor(Color.WHITE);
            g.drawString("Frame number: " + this.frameNumber + "\nTick number: " + this.tick, 10, 30);
        }
        this.frameNumber++;
        //this.updateUI();
        //Output.infoln("Frame number: " + this.frameNumber);
        //this.r.paint(g, this.w, this.tm, this.frame);
    }

    public void interpret() {
        if (KeyboardHandler.keyStroke['w'])
            this.re.ycenter -= 4 * (1.0 / this.re.zoom);
        if (KeyboardHandler.keyStroke['s'])
            this.re.ycenter += 4 * (1.0 / this.re.zoom);
        if (KeyboardHandler.keyStroke['a'])
            this.re.xcenter -= 4 * (1.0 / this.re.zoom);
        if (KeyboardHandler.keyStroke['d'])
            this.re.xcenter += 4 * (1.0 / this.re.zoom);
        if (KeyboardHandler.keyStroke['q']) {
            this.re.zoom *= 0.98;
            this.tm.updateTextures(this.re.zoom);
        }
        if (KeyboardHandler.keyStroke['e']) {
            this.re.zoom *= 1.02;
            this.tm.updateTextures(this.re.zoom);
        }
        if (KeyboardHandler.keyStroke[32]) {
            Output.infoln("Toggled pause");
            this.isPaused = !this.isPaused;
            KeyboardHandler.keyStroke[32] = false;
        }
        if (KeyboardHandler.keyStroke['1']) {
            Output.infoln("Toggled stats");
            this.showStats = !this.showStats;
            KeyboardHandler.keyStroke['1'] = false;
        }
        if (KeyboardHandler.keyStroke['r']) {
            this.w.generateWorld();
        }
    }

    @Override
    public void run() {
        this.timeAtLastTick = System.currentTimeMillis();
        while (true)
        {
            //execute();
            if (!this.isPaused) {
                this.w.update();
                this.tick++;
            }

            long timeSinceLastTick = System.currentTimeMillis() - this.timeAtLastTick;
            Output.infoln("Updated world after " + timeSinceLastTick);

            try {
                Thread.sleep(1 + this.mspt - timeSinceLastTick);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            timeAtLastTick = System.currentTimeMillis();
        }
    }
}
