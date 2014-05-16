package gui.right_side;

import gui.MainWindow;
import gui.Menu;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;

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
        if (arg instanceof TextView) {
            System.out.println("TEXVIEW OBSERVER");
            return;
        }
        if (arg instanceof TreeView) {
            System.out.println("TREE OBSERVER");
            return;
        }
        if (arg instanceof XmlView) {
            System.out.println("XMLVIEW OBSERVER");
        }
    }

    public void setMainWindow(MainWindow mw) {
        this.mw = mw;
        textView.setMainWindow(mw);  
        xmlView.setMainWindow(mw);
        treeView.setMainWindow(mw);
    }

    public void showPanel(int n, boolean b) {
        currItems ^= 1 << n;
        if (currItems > 0) {
            rearrange();
            this.revalidate();
        } else {
            currItems ^= 1 << n;
            menu.setSelected(n);
        }
    }

    private void rearrange() {
        this.removeAll();
        System.out.println(currItems);
        switch (currItems) {
            case 7:
                this.add(textView);
                this.add(xmlView);
                this.add(treeView);
                return;
            case 6:
                this.add(textView);
                this.add(xmlView);
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

        textView.addPopMenu(menu);
        treeView.addPopMenu(menu);
        xmlView.addPopMenu(menu);

        textView.addObserver(this);
        xmlView.addObserver(this);
        treeView.addObserver(this);

        this.add(textView);
        this.add(xmlView);
        this.add(treeView);
    }

}
