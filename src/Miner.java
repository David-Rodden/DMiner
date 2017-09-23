import org.osbot.rs07.api.model.Entity;
import org.osbot.rs07.api.util.GraphicUtilities;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.script.ScriptManifest;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

@ScriptManifest(name = "DMiner", version = 1.0, author = "Dungeonqueer", info = "Powermines selected rocks", logo = "")
public class Miner extends Script {
    final Color present = new Color(234, 41, 244, 180), empty = new Color(255, 197, 0, 180), selection = new Color(27, 255, 0, 180);
    List<Entity> rocks;
    private MinerGUI gui;

    @Override
    public void onStart() throws InterruptedException {
        rocks = new ArrayList<>();
        gui = new MinerGUI();
        getBot().addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                getMouse().getEntitiesOnCursor().stream().filter(obj -> obj.getName().equals("Rocks")).forEach(entity -> {
                    log(rocks);
                    if (rocks.contains(entity)) rocks.remove(entity);
                    else rocks.add(entity);
                });
            }

            @Override
            public void mousePressed(final MouseEvent e) {

            }

            @Override
            public void mouseReleased(final MouseEvent e) {

            }

            @Override
            public void mouseEntered(final MouseEvent e) {

            }

            @Override
            public void mouseExited(final MouseEvent e) {

            }
        });
        super.onStart();
    }

    @Override
    public int onLoop() throws InterruptedException {
        return 100;
    }

    @Override
    public void onExit() throws InterruptedException {

        super.onExit();
    }

    @Override
    public void onPaint(final Graphics2D g) {
        for (final Entity rock : rocks) {
            g.setPaint(rock.exists() ? present : empty);
            GraphicUtilities.drawModel(getBot(), g, rock.getGridX(), rock.getGridY(), rock.getZ(), rock.getModel());
        }
        g.setPaint(selection);
        getMouse().getEntitiesOnCursor().stream().filter(obj -> obj.getName().equals("Rocks")).forEach(entity -> {
            GraphicUtilities.drawModel(getBot(), g, entity.getGridX(), entity.getGridY(), entity.getZ(), entity.getModel());
        });
    }
}
