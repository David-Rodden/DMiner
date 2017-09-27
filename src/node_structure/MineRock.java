package node_structure;


import org.osbot.rs07.api.model.RS2Object;
import org.osbot.rs07.script.Script;

public class MineRock extends NFANode {
    private RS2Object rock;

    protected MineRock(final NFAHandler handler) {
        super(handler, "Mining rock");
    }

    @Override
    protected void action() {
        final Script ref = getHandler().getRef();
        if (ref.myPlayer().isAnimating()) {
            sleepUntil(() -> !ref.myPlayer().isAnimating());
            return;
        }
        ref.getObjects().closest(2, 3, 4).interact("Mine");
    }

    @Override
    protected NFANode determine() {
        return getHandler().getRef().getInventory().isFull() ? getSuccess() : null;
    }

    @Override
    protected void transition() {

    }
}
