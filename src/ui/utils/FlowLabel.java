package ui.utils;

import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FlowLabel extends JPanel {

    private final JLabel lbTitle;

    public FlowLabel() {
        this.setLayout(new FlowLayout());
        lbTitle = new JLabel();
        this.add(lbTitle);
    }

    public FlowLabel(String text) {
        this.setLayout(new FlowLayout());
        lbTitle = new JLabel(text);
        this.add(lbTitle);
    }

    public void setText(String text) {
        lbTitle.setText(text);
    }

}
