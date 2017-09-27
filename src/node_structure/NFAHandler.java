package node_structure;

import org.osbot.rs07.api.model.RS2Object;
import org.osbot.rs07.script.Script;

import java.util.List;

public class NFAHandler {
    static final int MAX_ROCK_DISTANCE = 1;
    private final Script ref;
    private NFANode pointer;
    private List<RS2Object> rocks;

    public NFAHandler(final Script ref) {
        this.ref = ref;
    }

    public Script getRef() {
        return ref;
    }

    public RS2Object getNearestRock() {
        return ref.getObjects().closest(rocks);
    }

    /**
     * Initializes the flow of the NFS nodes
     */
    public void init() {
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
