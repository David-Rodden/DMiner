package node_structure;

import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.model.RS2Object;
import org.osbot.rs07.api.util.GraphicUtilities;
import org.osbot.rs07.script.Script;

import java.awt.*;

public class WalkToRock extends NFANode {
    private final NFAHandler handler;

    WalkToRock(final NFAHandler handler) {
        super(handler, "Walking to rock");
        this.handler = handler;
    }

    @Override
    protected void action() {
        final Script ref = getHandler().getRef();
        final RS2Object rock = getHandler().getFocusedRock();
        if (rock == null || !rock.exists()) ref.log("Doesn't exist");
        if (rock != null) ref.getWalking().walk(rock);
        sleepUntil(() -> getHandler().isInProximity(rock));
    }


    @Override
    protected NFANode determine() {
        final NFAHandler handler = getHandler();
        final Script ref = handler.getRef();
        final RS2Object rock = handler.getFocusedRock(), nearest = handler.getNearestRock();
        final Position myPosition = ref.myPosition();
        if (rock == null || !rock.exists() || rock.getPosition().distance(myPosition) > nearest.getPosition().distance(myPosition))
            handler.setFocusedRock(nearest);
        return handler.isInProximity(handler.getFocusedRock()) ? getSuccess() : null;
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
