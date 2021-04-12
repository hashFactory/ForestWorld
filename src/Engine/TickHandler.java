package Engine;

import Misc.Output;

public class TickHandler implements Runnable {
    public long startTime;
    public long timeSinceLastFrame = 0;
    public long timeSinceLastTick = 0;
    public long timeAtLastTick = 0;

    double reqFPS = 60;
    double realFPS = 0.0; // TODO: implémenter
    int mspfps = 0;

    public TickHandler(int reqFPS) {
        this.reqFPS = reqFPS;
        this.mspfps = (int)(1000.0 / this.reqFPS);
    }

    @Override
    public void run() {
        long timeAtLastTick = System.currentTimeMillis();
        while (true)
        {
            //execute();
            timeSinceLastTick = System.currentTimeMillis()-timeAtLastTick;
            try {
                Thread.sleep(this.mspfps - timeSinceLastTick);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Output.infoln("Tick");
            timeAtLastTick = System.currentTimeMillis();
        }
    }

    /*
    public void execute()
    {
        String[] output = MapMethods.getCloseChunks(MainApplet.engine.protagonist);
        for (String string: output)
            Output.debug(string + "\t");
        Output.debug("\n");
        try {
            Thread.sleep(this.mspfps);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }*/
}
