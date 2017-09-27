package node_structure;

import gui.MinerGUI;

public class Setup extends NFANode {
    private MinerGUI gui;

    Setup(final NFAHandler handler) {
        super(handler, "Setting up bot");
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
