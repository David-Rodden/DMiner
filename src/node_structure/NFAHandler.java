package node_structure;

import org.osbot.rs07.api.model.RS2Object;
import org.osbot.rs07.script.Script;

import java.awt.*;
import java.util.List;

public class NFAHandler {
    static final int MAX_ROCK_DISTANCE = 1;
    private final Script ref;
    private NFANode pointer;
    private List<RS2Object> rocks;

    public NFAHandler(final Script ref) {
        this.ref = ref;
    }

    Script getRef() {
        return ref;
    }

    void setRocks(final List<RS2Object> rocks) {
        this.rocks = rocks;
    }

    RS2Object getNearestRock() {
        return ref.getObjects().closest(rocks);
    }

    /**
     * Initializes the flow of the NFS nodes
     */
    public void init() {
        NFANode current = pointer = new Setup(this);
        final NFANode loopPoint = new WalkToRock(this);
        current.setSuccess(current = loopPoint);
        current.setSuccess(current = new MineRock(this));
        current.setSuccess(current = new DropRock(this));
        current.setSuccess(loopPoint);
    }

    public String getCurrentDescription() {
        return pointer.getDescription();
    }

    public void drawWireFrame(final Graphics2D g) {
        if (pointer instanceof Setup) ((Setup) pointer).drawRockWireFrame(g);
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
