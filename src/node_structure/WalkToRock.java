package node_structure;

import org.osbot.rs07.api.model.RS2Object;
import org.osbot.rs07.script.Script;

public class WalkToRock extends NFANode {
    protected WalkToRock(final NFAHandler handler) {
        super(handler, "Walking to rock");
    }

    @Override
    protected void action() {
        final Script ref = getHandler().getRef();
        final RS2Object rock = getHandler().getNearestRock();
        if (rock.getPosition().distance(ref.myPosition()) > NFAHandler.MAX_ROCK_DISTANCE) {
            sleepUntil(() -> isInProximity(rock));
            return;
        }
        ref.getWalking().walk(rock);
        sleepUntil(() -> isInProximity(rock));
    }

    private boolean isInProximity(final RS2Object rock) {
        return rock.getPosition().distance(getHandler().getRef().myPosition()) == NFAHandler.MAX_ROCK_DISTANCE;
    }

    @Override
    protected NFANode determine() {
        final Script ref = getHandler().getRef();
        return getHandler().getNearestRock().getPosition().distance(ref.myPosition()) == NFAHandler.MAX_ROCK_DISTANCE ? getSuccess() : null;
    }

    @Override
    protected void transition() {

    }
}
