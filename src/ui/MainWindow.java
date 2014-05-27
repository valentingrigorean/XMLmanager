package ui;

import javax.swing.ImageIcon;
import threads.AutoSaveThread;
import threads.IdleThread;
import ui.left_side.LeftSidePanel;
import ui.right_side.ViewsPanel;

public class MainWindow extends javax.swing.JFrame {

    public MainWindow() {
        initComponents();
        init();       
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rightSidePanel = new ui.right_side.ViewsPanel();
        leftSidePanel = new ui.left_side.LeftSidePanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        rightSidePanel.setMinimumSize(new java.awt.Dimension(250, 250));
        getContentPane().add(rightSidePanel, java.awt.BorderLayout.CENTER);
        getContentPane().add(leftSidePanel, java.awt.BorderLayout.LINE_START);

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
                MainWindow mw = new MainWindow();
                mw.setVisible(true);
            }
        });
    }

    private void init() {
        leftSidePanel.setMainWindow(this);
        rightSidePanel.setMainWindow(this);

        this.setTitle("XML Editor 2.1");
        this.setIconImage(new ImageIcon(getClass().
                getResource("/res/xml_editor_logo_icon_v4.png")).getImage());
        this.setLocationRelativeTo(null);
    }

    public void setCurrentFilePath(String path) {
        if (path == null && autoUpdate != null) {
            autoUpdate.stop();
        } else {
            currentFilePath = path;
            autoUpdateStart();
        }
    }

    public String getCurrentFilePath() {
        return currentFilePath;
    }

    private void autoUpdateStart() {
        if (autoUpdate == null) {
            autoUpdate = new AutoSaveThread(currentFilePath, this);
        } else {
            autoUpdate.stop();
            autoUpdate.setFilePath(currentFilePath);
        }
        autoUpdate.start();
    }

    public LeftSidePanel getLeftSidePanel() {
        return leftSidePanel;
    }

    public ViewsPanel getViewsPanel() {
        return rightSidePanel;
    }

    /**
     *
     * @return true if file was change else false
     */
    public boolean getFileStatus() {
        return fileStatus;
    }

    /**
     *
     * @param b true if file was change false for no change
     */
    public void setFileStatus(boolean b) {
        fileStatus = b;
    }

    public String getContent() {
        return rightSidePanel.getContent();
    }

    public void setLastSearchString(String str) {
        lastSearch = str;
    }

    public String getLastSearchString() {
        return lastSearch;
    }

    public void setLastReplaceString(String str) {
        lastReplace = str;
    }

    public void error(String type, String msg, int line, int column) {
        rightSidePanel.getErrorView().clearAll();
        rightSidePanel.getErrorView().error(type, msg, line, column);
    }

    public void clearAllErrors() {
        rightSidePanel.getErrorView().clearAll();
    }

    public void setGotInput(boolean b) {
        gotInput = b;
    }

    public boolean getGotInput() {
        return gotInput;
    }

    public String getLastReplaceString() {
        return lastReplace;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private ui.left_side.LeftSidePanel leftSidePanel;
    private ui.right_side.ViewsPanel rightSidePanel;
    // End of variables declaration//GEN-END:variables
    private AutoSaveThread autoUpdate;
    private String currentFilePath;
    private boolean fileStatus = false;
    private boolean gotInput = false;
    private String lastSearch;
    private String lastReplace;
}
