package node_structure;


import org.osbot.rs07.api.model.RS2Object;
import org.osbot.rs07.script.Script;

public class MineRock extends NFANode {
    private RS2Object rock;

    MineRock(final NFAHandler handler) {
        super(handler, "Mining rock");
    }

    @Override
    protected void action() {
        final Script ref = getHandler().getRef();
        if (ref.myPlayer().isAnimating()) {
            sleepUntil(() -> !ref.myPlayer().isAnimating());
            return;
        }
        getHandler().getNearestRock().interact("Mine");
        sleepUntil(() -> !ref.myPlayer().isAnimating());
    }

    @Override
    protected NFANode determine() {
        final Script ref = getHandler().getRef();
        return ref.getInventory().isFull() ? getSuccess() : getHandler().getNearestRock().getPosition().distance(ref.myPosition()) > NFAHandler.MAX_ROCK_DISTANCE ? getFailure() : null;
    }

    @Override
    protected void transition() {

    }
}
