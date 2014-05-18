package ui.right_side;

import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class TextView extends AbstractView {

    public TextView() {
        super();
        init();
    }   
    
    private void init() {
        view = new JTextArea();
        label.setText("TextView");
        ((JTextArea) view).setEditable(false);
        scrollPane = new JScrollPane(view);
        super.add(view, BorderLayout.CENTER);
    }   
}
