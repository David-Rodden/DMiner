package gui;

import node_structure.NFAHandler;

import javax.swing.*;

public class MinerGUI extends JFrame {
    private NFAHandler handler;
    private JPanel mainPanel;
    private JButton runButton;
    private JButton commenceButton;
    private boolean hasDisposed;

    public MinerGUI(final NFAHandler handler) {
        this.handler = handler;
        commenceButton.addActionListener(e -> {
            dispose();
            hasDisposed = true;
        });
        init();
    }

    private void init() {
        setTitle("MinerGUI");
        setContentPane(mainPanel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public boolean formSent() {
        return hasDisposed;
    }
}
