package gui.left_side;

import gui.FlowLabel;
import gui.MainWindow;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.File;
import java.util.Collections;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

public class RecentFilesPanel extends JPanel {

    private JTree tree;
    private JPanel panel;
    private MainWindow mw;

    public JTree getTree() {
        return tree;
    }

    public void setTree(JTree tree) {
        this.tree = tree;
    }
    

    public RecentFilesPanel() {
        super();        
        init();
    }
    
    public RecentFilesPanel(File director) {
        setLayout(new BorderLayout());

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
