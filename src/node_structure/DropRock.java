package node_structure;

public class DropRock extends NFANode {
    DropRock(final NFAHandler handler) {
        super(handler, "Dropping ores");
    }

    @Override
    protected void action() {
        getHandler().getRef().getInventory().dropForNameThatContains(" ore", "Coal");
    }

    @Override
    protected NFANode determine() {
        return !getHandler().getRef().getInventory().contains(" ore", "Coal") ? getSuccess() : null;
    }

    @Override
    protected void transition() {

    }
}
