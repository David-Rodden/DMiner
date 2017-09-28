package node_structure;

import gui.MinerGUI;
import gui.listener.GUIClickListener;
import org.osbot.rs07.api.model.Entity;
import org.osbot.rs07.api.model.RS2Object;
import org.osbot.rs07.api.util.GraphicUtilities;
import org.osbot.rs07.script.Script;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Setup extends NFANode {
    private static final Color present = new Color(234, 41, 244, 180);
    private static final Color empty = new Color(255, 197, 0, 180);
    private static final Color selection = new Color(27, 255, 0, 180);
    private MinerGUI gui;
    private List<RS2Object> rocks;
    private Script ref;

    Setup(final NFAHandler handler) {
        super(handler, "Setting up bot");
        gui = new MinerGUI(handler);
        ref = handler.getRef();
        handler.getRef().getBot().addMouseListener(new GUIClickListener(this));
        rocks = new ArrayList<>();
    }

    public Script getRef() {
        return ref;
    }

    public void manageRocks(final RS2Object rock) {
        if (rocks.contains(rock)) rocks.remove(rock);
        else rocks.add(rock);
    }


    @Override
    protected void action() {

    }

    @Override
    protected NFANode determine() {
        return gui.formSent() ? getSuccess() : null;
    }

    void drawRockWireFrame(final Graphics2D g) {
        final List<Entity> rocksFocused = Stream.concat(rocks.stream(), ref.getMouse().getEntitiesOnCursor().stream()).collect(Collectors.toList());
        for (final Entity rock : rocksFocused) {
            g.setPaint(!rocks.contains(rock) ? selection : rock.exists() ? present : empty);
            GraphicUtilities.drawModel(ref.getBot(), g, rock.getGridX(), rock.getGridY(), rock.getZ(), rock.getModel());
        }
    }

    @Override
    protected void transition() {
        getHandler().setRocks(rocks);
    }
}
