package node_structure;

import gui.GUIClickListener;
import gui.MinerGUI;
import org.osbot.rs07.api.model.RS2Object;

import java.util.List;

public class Setup extends NFANode {
    private MinerGUI gui;
    private List<RS2Object> rocks;

    Setup(final NFAHandler handler) {
        super(handler, "Setting up bot");
        gui = new MinerGUI(handler);
        handler.getRef().getBot().addMouseListener(new GUIClickListener(this));
    }

    @Override
    protected void action() {

    }

    @Override
    protected NFANode determine() {
        return gui.formSent() ? getSuccess() : null;
    }

    @Override
    protected void transition() {

    }
}
