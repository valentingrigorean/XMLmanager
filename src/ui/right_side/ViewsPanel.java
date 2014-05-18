package ui.right_side;

import ui.MainWindow;
import ui.utils.Menu;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import org.fife.rsta.ac.xml.tree.XmlOutlineTree;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

public class ViewsPanel extends JPanel implements Observer {

    private TextView textView;
    private TreeView treeView;
    private XmlView xmlView;
    private int currItems = 0x7;
    private MainWindow mw;
    private final Menu menu = new Menu(this);

    public final static int TEXT_VIEW = 2;
    public final static int XML_VIEW = 1;
    public final static int TREE_VIEW = 0;

    public ViewsPanel() {
        init();
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println(currItems);
        if (arg instanceof TextView) {
            showPanel(TEXT_VIEW);
            if (currItems != 4) {
                menu.setSelected(TEXT_VIEW, false);
            }
        } else if (arg instanceof TreeView) {
            showPanel(TREE_VIEW);
            if (currItems != 1) {
                menu.setSelected(TREE_VIEW, false);
            }
        } else if (arg instanceof XmlView) {
            showPanel(XML_VIEW);
            if (currItems != 2) {
                menu.setSelected(XML_VIEW, false);
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
        this.removeAll();
        switch (currItems) {
            case 7:
                this.add(xmlView);
                this.add(textView);

                this.add(treeView);
                return;
            case 6:
                this.add(xmlView);
                this.add(textView);

                return;
            case 5:
                this.add(textView);
                this.add(treeView);
                return;
            case 4:
                this.add(textView);
                return;
            case 3:
                this.add(xmlView);
                this.add(treeView);
                return;
            case 2:
                this.add(xmlView);
                return;
            case 1:
                this.add(treeView);
        }
    }

    private void init() {
        textView = new TextView();
        treeView = new TreeView();
        xmlView = new XmlView();

        this.setLayout(new GridLayout(1, 3));

        this.setComponentPopupMenu(menu);

        xmlView.addPopMenu(menu);
        textView.addPopMenu(menu);
        treeView.addPopMenu(menu);

        xmlView.addObserver(this);
        textView.addObserver(this);

        treeView.addObserver(this);

        ((XmlOutlineTree) treeView.getView()).listenTo((RSyntaxTextArea) xmlView.getView());
        treeView.revalidate();

        this.add(xmlView);
        this.add(textView);

        this.add(treeView);
    }
}
