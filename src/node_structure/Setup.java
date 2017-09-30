package node_structure;

import data_type.Rock;
import gui.MinerGUI;
import gui.listener.GUIClickListener;
import org.osbot.rs07.api.model.Entity;
import org.osbot.rs07.api.util.GraphicUtilities;
import org.osbot.rs07.script.Script;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Setup extends NFANode {
    private MinerGUI gui;
    private List<Rock> rocks;
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

    public void manageRocks(final Rock rock) {
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
        final List<Entity> hovered = ref.getMouse().getEntitiesOnCursor().stream().filter(obj -> obj.exists() && obj.hasAction("Mine")).collect(Collectors.toList());
        final List<Entity> rocksFocused = Stream.concat(rocks.stream().map(Rock::getEntity).collect(Collectors.toList()).stream(), hovered.stream()).collect(Collectors.toList());
        for (final Entity rock : rocksFocused) {
            g.setPaint(hovered.contains(rock) ? selection : rock.exists() ? present : empty);
            GraphicUtilities.drawModel(ref.getBot(), g, rock.getGridX(), rock.getGridY(), rock.getZ(), rock.getModel());
        }
    }

    @Override
    protected void transition() {
        getHandler().setRocks(rocks);
    }
}
