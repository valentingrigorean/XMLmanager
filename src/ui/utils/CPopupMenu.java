package ui.utils;

import ui.right_side.ViewsPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JPopupMenu;

public class CPopupMenu extends JPopupMenu implements ActionListener {

    private JCheckBoxMenuItem text;
    private JCheckBoxMenuItem xml;
    private JCheckBoxMenuItem tree;
    private JMenu menuView;
    private final ViewsPanel controller;

    public CPopupMenu(ViewsPanel controller) {
        super();
        this.controller = controller;
        init();
    }   

    private void init() {

        menuView = new JMenu("Views");

        text = new JCheckBoxMenuItem("TextView");
        xml = new JCheckBoxMenuItem("XmlView");
        tree = new JCheckBoxMenuItem("TreeView");

        text.setState(true);
        xml.setState(true);
        tree.setState(true);

        text.addActionListener(this);
        xml.addActionListener(this);
        tree.addActionListener(this);

        menuView.add(text);
        menuView.add(xml);
        menuView.add(tree);

        this.add(menuView);
    }

    public JMenu getViews() {      
        return menuView;
    }

    public void setSelected(int n, boolean b) {
        switch(n){
            case ViewsPanel.XML_VIEW:                
                xml.setSelected(b);
                return;
            case ViewsPanel.TEXT_VIEW:                
                text.setSelected(b);
                return;
            case ViewsPanel.TREE_VIEW:                
                tree.setSelected(b);
        }      
    }    

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "TextView":
                controller.showPanel(ViewsPanel.TEXT_VIEW);
                return;
            case "XmlView":
                controller.showPanel(ViewsPanel.XML_VIEW);
                return;
            case "TreeView":
                controller.showPanel(ViewsPanel.TREE_VIEW);
        }
    }
}
