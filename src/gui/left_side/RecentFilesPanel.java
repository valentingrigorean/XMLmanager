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

    // Make a tree list with all the nodes, and make it a JTree
    tree = new JTree(addNodes(null, director));

    // Add a listener
    tree.addTreeSelectionListener(new TreeSelectionListener() {
      public void valueChanged(TreeSelectionEvent e) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) e
            .getPath().getLastPathComponent();
        System.out.println("You selected " + node);
      }

          
    });

    // Lastly, put the JTree into a JScrollPane.
    JScrollPane scrollpane = new JScrollPane();
    scrollpane.getViewport().add(tree);
    add(BorderLayout.CENTER, scrollpane);
        //super();        
       // init();
    }
    
    DefaultMutableTreeNode addNodes(DefaultMutableTreeNode curTop, File dir) {
    String curPath = dir.getPath();
    DefaultMutableTreeNode curDir = new DefaultMutableTreeNode(curPath);
    if (curTop != null) { // should only be null at root
      curTop.add(curDir);
    }
    Vector ol = new Vector();
    String[] tmp = dir.list();
    for (int i = 0; i < tmp.length; i++)
      ol.addElement(tmp[i]);
    Collections.sort(ol, String.CASE_INSENSITIVE_ORDER);
    File f;
    Vector files = new Vector();
    // Make two passes, one for Dirs and one for Files. This is #1.
    for (int i = 0; i < ol.size(); i++) {
      String thisObject = (String) ol.elementAt(i);
      String newPath;
      if (curPath.equals("."))
        newPath = thisObject;
      else
        newPath = curPath + File.separator + thisObject;
      if ((f = new File(newPath)).isDirectory())
        addNodes(curDir, f);
      else
        files.addElement(thisObject);
    }
    // Pass two: for files.
    for (int fnum = 0; fnum < files.size(); fnum++)
      curDir.add(new DefaultMutableTreeNode(files.elementAt(fnum)));
    return curDir;
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
