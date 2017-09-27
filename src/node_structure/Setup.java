package node_structure;

import gui.MinerGUI;

public class Setup extends NFANode {
    private MinerGUI gui;

    protected Setup(final NFAHandler handler, final String description) {
        super(handler, description);
        gui = new MinerGUI(handler);
    }

    @Override
    protected void action() {

    }

    @Override
    protected NFANode determine() {
        return gui.formSent();
    }

    @Override
    protected void transition() {

    }
}
