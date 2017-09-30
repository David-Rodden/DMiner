import node_structure.NFAHandler;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.script.ScriptManifest;

import java.awt.*;

/**
 * Starting point for program
 */
@ScriptManifest(name = "DMiner", version = 1.1, author = "Dungeonqueer", info = "Powermines selected rocks", logo = "")
public class Miner extends Script {
    private NFAHandler handler;

    @Override
    public void onStart() throws InterruptedException {
        handler = new NFAHandler(this);
        handler.init();
        super.onStart();
    }

    @Override
    public int onLoop() throws InterruptedException {
        handler.run();
        return 100;
    }

    @Override
    public void onExit() throws InterruptedException {
        super.onExit();
    }

    @Override
    public void onPaint(final Graphics2D g) {
        handler.drawWireFrame(g);
        g.setPaint(Color.CYAN);
        g.drawString(handler.getCurrentDescription(), 50, 50);  //default for description
    }
}
