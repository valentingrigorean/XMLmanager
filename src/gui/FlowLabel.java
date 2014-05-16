package gui;

import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FlowLabel extends JPanel {

    public FlowLabel(String text) {
        this.setLayout(new FlowLayout());
        this.add(new JLabel(text));
    }

}
