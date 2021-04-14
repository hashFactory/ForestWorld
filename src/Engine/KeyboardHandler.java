package Engine;

import Misc.Output;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class KeyboardHandler {
    //JRootPane jrp;
    Engine engine;
    public static boolean[] keyStroke = new boolean[512];

    public KeyboardHandler(Engine engine, InputMap im, ActionMap am) {
        //this.jrp = jrp;
        this.engine = engine;
        InputMap inmap = im;
        ActionMap amap = am;

        inmap.put(KeyStroke.getKeyStroke("UP"), "UP");
        inmap.put(KeyStroke.getKeyStroke("DOWN"), "DOWN");
        inmap.put(KeyStroke.getKeyStroke("LEFT"), "LEFT");
        inmap.put(KeyStroke.getKeyStroke("RIGHT"), "RIGHT");

        // SPACE to `
        for (int i = 32; i < 96; i++)
            inmap.put(KeyStroke.getKeyStroke(Character.toString((char)i)), "pressedAction");
        for (int i = 32; i < 96; i++)
            inmap.put(KeyStroke.getKeyStroke("released " + Character.toString((char)i)), "releasedAction");

        amap.put("pressedAction", pressedAction);
        amap.put("releasedAction", releasedAction);

        /*// ADD ELEMENTS TO MAP
        amap.put("UP", up);
        amap.put("DOWN", down);
        amap.put("RIGHT", right);*/
    }

    private AbstractAction pressedAction = new AbstractAction()
    {
        public void actionPerformed(ActionEvent e)
        {
            int character = e.getActionCommand().toCharArray()[0];
            Output.debugln("+++++++ " + e.getActionCommand());
            KeyboardHandler.keyStroke[character] = true;
        }
    };

    private AbstractAction releasedAction = new AbstractAction()
    {
        public void actionPerformed(ActionEvent e)
        {
            int character = e.getActionCommand().toCharArray()[0];
            Output.debugln("------ " + e.getActionCommand());
            KeyboardHandler.keyStroke[character] = false;
        }
    };
}
