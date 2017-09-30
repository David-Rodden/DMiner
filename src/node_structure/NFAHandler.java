package node_structure;

import data_type.Rock;
import org.osbot.rs07.api.model.Entity;
import org.osbot.rs07.api.model.RS2Object;
import org.osbot.rs07.script.Script;

import java.awt.*;
import java.util.List;

public class NFAHandler {
    private static final int MAX_ROCK_DISTANCE = 1;
    private final Script ref;
    private NFANode pointer;
    private List<Rock> rocks;
    private RS2Object focusedRock;

    public NFAHandler(final Script ref) {
        this.ref = ref;
    }

    Script getRef() {
        return ref;
    }

    void setRocks(final List<Rock> rocks) {
        this.rocks = rocks;
    }

    RS2Object getNearestRock() {
        //noinspection unchecked
        return ref.getObjects().closest(Entity::exists, rocks::contains);
    }

    /**
     * Initializes the flow of the NFS nodes
     */
    public void init() {
        NFANode current = pointer = new Setup(this);
        final NFANode loopPoint = new WalkToRock(this);
        current.setSuccess(current = loopPoint);
        final NFANode mineRock = new MineRock(this);
        current.setSuccess(current = mineRock);
        current.setFailure(loopPoint);
        current.setSuccess(current = new DropRock(this));
        current.setSuccess(loopPoint);
    }

    RS2Object getFocusedRock() {
        return focusedRock;
    }

    void setFocusedRock(final RS2Object focusedRock) {
        this.focusedRock = focusedRock;
    }

    boolean isInProximity(final RS2Object rock) {
        return rock != null && rock.getPosition().distance(ref.myPosition()) == MAX_ROCK_DISTANCE;
    }

    public String getCurrentDescription() {
        return pointer.getDescription();
    }

    public void drawWireFrame(final Graphics2D g) {
        if (pointer instanceof Setup) ((Setup) pointer).drawRockWireFrame(g);
        else if (pointer instanceof WalkToRock) ((WalkToRock) pointer).drawRockWireFrame(g);
        else if (pointer instanceof MineRock) ((MineRock) pointer).drawRockWireFrame(g);
    }

    public void run() {
        final NFANode determined = pointer.determine();
        if (determined != null) {
            pointer.transition();
            pointer = determined;
            return;
        }
        pointer.action();
    }
}
