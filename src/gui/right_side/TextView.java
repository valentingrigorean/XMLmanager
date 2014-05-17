package gui.right_side;

import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class TextView extends AbstractView {

    public TextView() {
        super();
        init();
    }

    @Override
    public void parse() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void init() {
        view = new JTextArea();
        label.setText("TextView");
        ((JTextArea) view).setEditable(false);
        scrollPane = new JScrollPane(view);
        super.add(view, BorderLayout.CENTER);
    }

    @Override
    public void setInput(AbstractView absView) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
