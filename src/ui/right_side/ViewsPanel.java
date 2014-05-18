package ui.right_side;

import java.awt.GridLayout;
import ui.MainWindow;
import ui.utils.Menu;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import org.fife.rsta.ac.xml.tree.XmlOutlineTree;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

public class ViewsPanel extends JPanel implements Observer {

    private TextView textView;
    private TreeView treeView;
    private XmlView xmlView;
    private int currItems = 0x7;
    private MainWindow mw;
    private JSplitPane splitPane1;    
    private final Menu menu = new Menu(this);

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
        ((JTextArea) xmlView.getView()).setText(str);
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
            this.revalidate();
        } else {
            currItems ^= 1 << n;
            menu.setSelected(n, true);
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
        textView = new TextView();
        treeView = new TreeView();
        xmlView = new XmlView();

        splitPane1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, xmlView,
                new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                        textView, treeView));        

        this.setLayout(new GridLayout(1, 0));

        this.setComponentPopupMenu(menu);

        xmlView.addPopMenu(menu);
        textView.addPopMenu(menu);
        treeView.addPopMenu(menu);

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
            if (currItems != 4) {
                menu.setSelected(TEXT_VIEW, false);
            }
        } else if (upd.getView() instanceof TreeView) {
            showPanel(TREE_VIEW);
            if (currItems != 1) {
                menu.setSelected(TREE_VIEW, false);
            }
        } else if (upd.getView() instanceof XmlView) {
            showPanel(XML_VIEW);
            if (currItems != 2) {
                menu.setSelected(XML_VIEW, false);
            }
        }
    }

    private void notifyViews(Update upd) {
        mw.setFileStatus(true);
    }
}
