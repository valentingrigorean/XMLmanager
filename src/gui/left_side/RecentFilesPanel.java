package gui.left_side;

import gui.FlowLabel;
import gui.MainWindow;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTree;

public class RecentFilesPanel extends JPanel {

    private JTree tree;

    public JTree getTree() {
        return tree;
    }

    public void setTree(JTree tree) {
        this.tree = tree;
    }
    private JPanel panel;
    private MainWindow mw;

    public RecentFilesPanel() {
        super();        
        init();
    }

    public void setMainWindow(MainWindow mw) {
        this.mw = mw;
    }

    private void init() {
        this.setPreferredSize(new Dimension(250, 250));
        this.setLayout(new BorderLayout());

        tree = new JTree();
        panel = new JPanel();
        panel.setLayout(new GridLayout(1, 1));
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
        panel.add(tree);

        this.add(new FlowLabel("Recent Files"), BorderLayout.NORTH);
        this.add(panel, BorderLayout.CENTER);
    }
}
