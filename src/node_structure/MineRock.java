package node_structure;


import org.osbot.rs07.api.model.RS2Object;
import org.osbot.rs07.api.util.GraphicUtilities;
import org.osbot.rs07.script.Script;

import java.awt.*;

public class MineRock extends NFANode {
    private final NFAHandler handler;

    MineRock(final NFAHandler handler) {
        super(handler, "Mining rock");
        this.handler = handler;
    }

    @Override
    protected void action() {
        final Script ref = getHandler().getRef();
        if (ref.myPlayer().isAnimating()) {
            sleepUntil(() -> !ref.myPlayer().isAnimating());
            return;
        }
        getHandler().getFocusedRock().interact("Mine");
        ref.log("Mining the rock");
        sleepUntil(() -> ref.myPlayer().isAnimating());
    }

    @Override
    protected NFANode determine() {
        final NFAHandler handler = getHandler();
        final Script ref = handler.getRef();
        if (ref.getInventory().isFull()) return getSuccess();
        final RS2Object rock = handler.getFocusedRock();
        return (!rock.exists() || !handler.isInProximity(rock)) ? getFailure() : null;
    }

    void drawRockWireFrame(final Graphics2D g) {
        final RS2Object focusedRock = handler.getFocusedRock();
        g.setPaint(present);
        GraphicUtilities.drawModel(handler.getRef().getBot(), g, focusedRock.getGridX(), focusedRock.getGridY(), focusedRock.getZ(), focusedRock.getModel());
    }

    @Override
    protected void transition() {

    }
}
