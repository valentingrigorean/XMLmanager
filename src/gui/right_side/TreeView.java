package gui.right_side;

import javax.swing.JTree;

public class TreeView extends AbstractView {

    public TreeView() {
        super(new JTree(), "TreeView");
        init();
    }   
    
    @Override
    public void parse() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void init() {
    }

    @Override
    public void setInput(AbstractView absView) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
