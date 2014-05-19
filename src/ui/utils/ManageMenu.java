package ui.utils;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import ui.right_side.ViewsPanel;

public class ManageMenu {

    private CPopupMenu menu;
    private JMenu view;

    public ManageMenu() {

    }

    public ManageMenu(CPopupMenu m1, JMenu m2) {
        menu = m1;
        view = m2;
    }

    public void setMenu(CPopupMenu m) {
        menu = m;
    }

    public void setView(JMenu view) {
        this.view = view;
    }

    public void setSelected(int n, boolean b) {
        menu.setSelected(n, b);
        switch (n) {
            case ViewsPanel.TEXT_VIEW:               
                ((JCheckBoxMenuItem) view.getItem(0)).setSelected(b);
                return;
            case ViewsPanel.XML_VIEW:
                ((JCheckBoxMenuItem) view.getItem(1)).setSelected(b);                
                return;
            case ViewsPanel.TREE_VIEW:
                ((JCheckBoxMenuItem) view.getItem(2)).setSelected(b);                
        }
    }

    public void setSelected(int n) {
        boolean b = true;
        switch (n) {
            case ViewsPanel.TEXT_VIEW:
                b = ((JCheckBoxMenuItem) view.getItem(0)).isSelected();
                ((JCheckBoxMenuItem) view.getItem(0)).setSelected(!b);
                break;
            case ViewsPanel.XML_VIEW:
                b = ((JCheckBoxMenuItem) view.getItem(1)).isSelected();
                ((JCheckBoxMenuItem) view.getItem(1)).setSelected(!b);
                break;
            case ViewsPanel.TREE_VIEW:
                b = ((JCheckBoxMenuItem) view.getItem(2)).isSelected();
                ((JCheckBoxMenuItem) view.getItem(2)).setSelected(!b);
        }
        menu.setSelected(n, !b);
    }

}
