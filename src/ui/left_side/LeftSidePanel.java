package ui.left_side;

import java.awt.BorderLayout;
import ui.MainWindow;
import java.awt.Dimension;
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
        this.setPreferredSize(new Dimension(250,300));
        this.setLayout(new BorderLayout());           

        buttonsPanel = new ButtonsPanel();
        recentFilesPanel = new RecentFilesPanel();

        this.add(buttonsPanel,BorderLayout.NORTH);
        this.add(recentFilesPanel,BorderLayout.CENTER);
    }

}
