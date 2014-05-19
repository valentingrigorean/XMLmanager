package ui.right_side;

import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import org.fife.rsta.ac.xml.tree.XmlOutlineTree;

public class TreeView extends AbstractView {

    public TreeView() {
        super();
        init();
    }  

    private void init() {
        view = new XmlOutlineTree();
        label.setText("TreeView");
        scrollPane = new JScrollPane(view);
        ((JScrollPane) scrollPane).setViewportView(view);
        super.add(scrollPane, BorderLayout.CENTER);
    }

}
