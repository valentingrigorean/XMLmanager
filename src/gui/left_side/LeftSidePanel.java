package gui.left_side;

import gui.MainWindow;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JPanel;

public class LeftSidePanel extends JPanel {

    private ButtonsPanel buttonsPanel;
    private RecentFilesPanel recentFilesPanel;
    private MainWindow mw;

    public LeftSidePanel() {
        super();        
        init();
    }
    
    public ButtonsPanel getButtonsPanel(){
        return buttonsPanel;
    }
    
    public void setMainWindow(MainWindow mw){
        this.mw = mw;
        buttonsPanel.setMainWindow(mw);
        recentFilesPanel.setMainWindow(mw);
    }
    
    public RecentFilesPanel getRecentFilesPanel(){
        return recentFilesPanel;
    }

    private void init() {
        this.setPreferredSize(new Dimension(250, 250));
        this.setLayout(new GridLayout(1, 2));           

        buttonsPanel = new ButtonsPanel();
        recentFilesPanel = new RecentFilesPanel();

        this.add(buttonsPanel);
        this.add(recentFilesPanel);
    }

}
