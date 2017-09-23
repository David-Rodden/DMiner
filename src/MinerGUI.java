
import javax.swing.*;

public class MinerGUI extends JFrame {
    private JPanel mainPanel;
    private JButton commenceButton;

    MinerGUI() {
        commenceButton.addActionListener(e -> {
            dispose();
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
}
