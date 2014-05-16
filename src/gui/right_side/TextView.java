package gui.right_side;

import javax.swing.JTextArea;

public class TextView extends AbstractView {

    public TextView() {
        super(new JTextArea(), "TextView");
        init();
    }

    @Override
    public void parse() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void init() {
        ((JTextArea)view).setEditable(false);
    }

    @Override
    public void setInput(AbstractView absView) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
