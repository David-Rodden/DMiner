package gui.listener;

import node_structure.Setup;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GUIClickListener implements MouseListener {
    private final Setup setup;

    public GUIClickListener(final Setup setup) {
        this.setup = setup;
    }g

    @Override
    public void mouseClicked(final MouseEvent e) {
        setup.getRef().getObjects().getAll().stream().filter(obj -> obj.exists() && obj.hasAction("Mine") && setup.getRef().getMouse().isOnCursor(obj)).forEach(setup::manageRocks);
    }

    @Override
    public void mousePressed(final MouseEvent e) {

    }

    @Override
    public void mouseReleased(final MouseEvent e) {

    }

    @Override
    public void mouseEntered(final MouseEvent e) {

    }

    @Override
    public void mouseExited(final MouseEvent e) {

    }
}
