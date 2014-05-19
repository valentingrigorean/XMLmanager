package ui.right_side;

import java.awt.GridLayout;
import ui.MainWindow;
import ui.utils.CPopupMenu;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import org.fife.rsta.ac.xml.tree.XmlOutlineTree;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import ui.utils.ManageMenu;

public class ViewsPanel extends JPanel implements Observer {

    private TextView textView;
    private TreeView treeView;
    private XmlView xmlView;
    private int currItems = 0x7;
    private MainWindow mw;
    private JSplitPane splitPane1;
    private ManageMenu manageMenu;

    public final static int TEXT_VIEW = 2;
    public final static int XML_VIEW = 1;
    public final static int TREE_VIEW = 0;

    public ViewsPanel() {
        init();
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof Update) {
            switch (((Update) arg).getType()) {
                case Update.CHANGE_UPDATE:
                case Update.INSERT_UPDATE:
                case Update.REMOVE_UPDATE:
                    notifyViews((Update) arg);
                    return;
                case Update.VIEW_CHANGE:
                    hidePanels((Update) arg);
            }
        }
    }

    public void setContent(String str) {
        xmlView.setDocumentListener(false);
        ((JTextArea) xmlView.getView()).setText(str);
        xmlView.setDocumentListener(true);
        reinvalidateViews();
    }

    public String getContent() {
        return ((JTextArea) xmlView.getView()).getText();
    }

    public void reinvalidateViews() {
        treeView.revalidate();
    }

    public XmlView getXmlView() {
        return xmlView;
    }

    public TextView getXmlText() {
        return textView;
    }

    public TreeView getXmlTree() {
        return treeView;
    }

    public void setMainWindow(MainWindow mw) {
        this.mw = mw;
        textView.setMainWindow(mw);
        xmlView.setMainWindow(mw);
        treeView.setMainWindow(mw);
    }

    public void showPanel(int n) {
        currItems ^= 1 << n;
        if (currItems > 0) {
            rearrange();
            manageMenu.setSelected(n);
            this.revalidate();
        } else {
            currItems ^= 1 << n;
            manageMenu.setSelected(n, true);
        }
    }

    private void rearrange() {
        if (this.getComponent(0) != splitPane1) {
            this.removeAll();
            this.add(splitPane1);
        }
        switch (currItems) {
            case 7:
                splitPane1.setLeftComponent(xmlView);
                splitPane1.setRightComponent(new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                        textView, treeView));
                return;
            case 6:
                splitPane1.setLeftComponent(xmlView);
                splitPane1.setRightComponent(textView);
                return;
            case 5:
                splitPane1.setLeftComponent(textView);
                splitPane1.setRightComponent(treeView);
                return;
            case 4:
                this.removeAll();
                this.add(textView);
                return;
            case 3:
                splitPane1.setLeftComponent(xmlView);
                splitPane1.setRightComponent(treeView);
                return;
            case 2:
                this.removeAll();
                this.add(xmlView);
                return;
            case 1:
                this.removeAll();
                this.add(treeView);
        }
    }

    private void init() {
        this.manageMenu = new ManageMenu();

        textView = new TextView();
        treeView = new TreeView();
        xmlView = new XmlView();

        splitPane1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, xmlView,
                new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                        textView, treeView));

        this.setLayout(new GridLayout(1, 0));

        menuForViews();

        xmlView.addObserver(this);
        textView.addObserver(this);

        treeView.addObserver(this);

        ((XmlOutlineTree) treeView.getView()).listenTo((RSyntaxTextArea) xmlView.getView());
        treeView.revalidate();

        this.add(splitPane1); 
        
    }

    private void hidePanels(Update upd) {
        if (upd.getView() instanceof TextView) {
            showPanel(TEXT_VIEW);
        } else if (upd.getView() instanceof TreeView) {
            showPanel(TREE_VIEW);
        } else if (upd.getView() instanceof XmlView) {
            showPanel(XML_VIEW);
        }
    }

    private void notifyViews(Update upd) {
        mw.setFileStatus(true);
    }

    private void menuForViews() {
        CPopupMenu m1 = new CPopupMenu(this);
        CPopupMenu m2 = new CPopupMenu(this);
        JPopupMenu m3 = xmlView.getPopupMenu();
        m3.addSeparator();
        m3.add(m1.getViews());

        this.setComponentPopupMenu(m2);
        xmlView.addPopMenu(m3);
        textView.addPopMenu(m2);
        treeView.addPopMenu(m2);

        manageMenu.setView((JMenu) m3.getComponent(12));
        manageMenu.setMenu(m2);
    }
}
