package gui;

import gui.right_side.ViewsPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JPopupMenu;


public class Menu extends JPopupMenu implements ActionListener {

    private JCheckBoxMenuItem text;
    private JCheckBoxMenuItem xml;
    private JCheckBoxMenuItem tree;
    private final ViewsPanel controller;

    public Menu(ViewsPanel controller) {
        super();
        this.controller = controller;
        init();
    }

    private void init() {
        text = new JCheckBoxMenuItem("TextView");
        xml = new JCheckBoxMenuItem("XmlView");
        tree = new JCheckBoxMenuItem("TreeView");

        text.setState(true);
        xml.setState(true);
        tree.setState(true);

        text.addActionListener(this);
        xml.addActionListener(this);
        tree.addActionListener(this);

        this.add(tree);
        this.add(xml);
        this.add(text);
    }
    
    public void setSelected(int n,boolean b){
        System.out.println(n + "," +b);
        ((JCheckBoxMenuItem)this.getComponent(n)).setSelected(b);
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
