package gui;

import model.AutoUpdate;
import model.RecentFiles;

public class MainWindow extends javax.swing.JFrame {

    @SuppressWarnings("LeakingThisInConstructor")
    public MainWindow() {
        initComponents();
        init();
        System.out.println("Qweqwe");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        leftSidePanel = new gui.left_side.LeftSidePanel();
        rightSidePanel = new gui.right_side.ViewsPanel();

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
                new MainWindow().setVisible(true);
            }
        });
    }

    private void init() {
        leftSidePanel.setMainWindow(this);
        rightSidePanel.setMainWindow(this);
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
            autoUpdate = new AutoUpdate(currentFilePath);
        } else {
            autoUpdate.stop();
            autoUpdate.setFilePath(currentFilePath);
        }
        autoUpdate.start();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private gui.left_side.LeftSidePanel leftSidePanel;
    private gui.right_side.ViewsPanel rightSidePanel;
    // End of variables declaration//GEN-END:variables
    private AutoUpdate autoUpdate;
    private String currentFilePath;
    private RecentFiles recentFiles;

}
