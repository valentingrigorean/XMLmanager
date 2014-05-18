package ui.left_side;

import ui.utils.FlowLabel;
import ui.MainWindow;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTree;

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
