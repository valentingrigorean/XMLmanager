package gui;

import gui.left_side.LeftSidePanel;
import gui.right_side.ViewsPanel;
import javax.swing.ImageIcon;
import model.AutoSave;
import model.RecentFiles;

public class MainWindow extends javax.swing.JFrame {

    @SuppressWarnings("LeakingThisInConstructor")
    public MainWindow() {
        initComponents();
        init();       
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        leftSidePanel = new gui.left_side.LeftSidePanel(this);
        rightSidePanel = new gui.right_side.ViewsPanel(this);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        leftSidePanel.setMaximumSize(new java.awt.Dimension(250, 250));
        leftSidePanel.setMinimumSize(new java.awt.Dimension(250, 250));
        leftSidePanel.setLayout(new java.awt.GridLayout(2, 1));
        getContentPane().add(leftSidePanel, java.awt.BorderLayout.LINE_START);

        rightSidePanel.setMinimumSize(new java.awt.Dimension(250, 250));
        getContentPane().add(rightSidePanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainWindow fereastra = new MainWindow();
                
                fereastra.setVisible(true);
                fereastra.setTitle("XML Editor 2.1");
                
                ImageIcon img = new ImageIcon("./xml_editor_logo_icon_v2.png");
                fereastra.setIconImage(img.getImage());
                
                fereastra.setLocationRelativeTo(null);
            }
        });
    }

    private void init() {
           leftSidePanel.setMainWindow(this);
           rightSidePanel.setMainWindow(this);
           //leftSidePanel.getButtonsPanel().linkRecentFilesPanel(".");
    }

    public void setCurrentFilePath(String path) {
        if (path == null && autoUpdate != null) {
            autoUpdate.stop();
        } else {
            currentFilePath = path;
            recentFiles.addFile(path);
            autoUpdateStart();
        }
    }

    private void autoUpdateStart() {
        if (autoUpdate == null) {
            autoUpdate = new AutoSave(currentFilePath);
        } else {
            autoUpdate.stop();
            autoUpdate.setFilePath(currentFilePath);
        }
        autoUpdate.start();
    }
    
    public LeftSidePanel getLeftSidePanel(){
        return leftSidePanel;
    }
    
    public ViewsPanel getViewsPanel(){
        return rightSidePanel;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private gui.left_side.LeftSidePanel leftSidePanel;
    private gui.right_side.ViewsPanel rightSidePanel;
    // End of variables declaration//GEN-END:variables
    private AutoSave autoUpdate;
    private String currentFilePath;
    private RecentFiles recentFiles;

}
