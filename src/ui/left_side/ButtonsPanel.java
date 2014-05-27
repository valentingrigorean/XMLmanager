package ui.left_side;

import io.NewDialogIO;
import io.OpenDialogIO;
import io.SaveDialogIO;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import ui.MainWindow;
import ui.dialogs.FindReplaceDialog;
import ui.dialogs.NewDialog;
import ui.utils.Help;

public class ButtonsPanel extends JPanel {

    private JButton btn1;
    private JButton btn2;
    private JButton btn3;
    private JButton btn4;
    private JButton btn5;
    private FindReplaceDialog find;
    private MainWindow mw;

    public ButtonsPanel() {
        super();
        init();
    }

    public void setMainWindow(MainWindow mw) {
        this.mw = mw;
        find = new FindReplaceDialog(mw);
    }

    private void init() {
        this.setPreferredSize(new Dimension(250, 200));
        this.setLayout(new GridLayout(5, 1));

        int focusPeFereastra = JComponent.WHEN_IN_FOCUSED_WINDOW; //tine cont daca e fereastra activata

        // --- NEW BUTTON ------------------------------------------------------------------------------
        btn1 = new JButton("New");
        btn1.setAction(new AbstractAction(btn1.getActionCommand()) {

            @Override
            public void actionPerformed(ActionEvent e) {
                NewDialogIO io = new NewDialogIO(mw);
                io.show();
            }
        });
        KeyStroke tastaCtrlN = KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK);

        getInputMap(focusPeFereastra).put(tastaCtrlN, "NEW");
        getActionMap().put("NEW", btn1.getAction());

        // --- OPEN BUTTON -----------------------------------------------------------------------------
        btn2 = new JButton("Open");
        btn2.setAction(new AbstractAction(btn2.getActionCommand()) {
            @Override
            public void actionPerformed(ActionEvent e) {
                OpenDialogIO io = new OpenDialogIO(mw);
                io.show();
            }
        });
        KeyStroke tastaCtrlO = KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK);
        getInputMap(focusPeFereastra).put(tastaCtrlO, "OPEN");
        getActionMap().put("OPEN", btn2.getAction());

        // --- SAVE BUTTON -----------------------------------------------------------------------------
        btn3 = new JButton("Save as");
        btn3.setAction(new AbstractAction(btn3.getActionCommand()) {
            @Override
            public void actionPerformed(ActionEvent e) {
                SaveDialogIO io = new SaveDialogIO(mw);
                io.show();
            }
        });
        KeyStroke tastaCtrlS = KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK);
        getInputMap(focusPeFereastra).put(tastaCtrlS, "SAVE");
        getActionMap().put("SAVE", btn3.getAction());

        btn4 = new JButton("");
        btn4.setAction(new AbstractAction(btn4.getActionCommand()) {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (find != null) {
                    find.setVisible(enabled);
                }
            }
        });
        KeyStroke tastaCtrlF = KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_DOWN_MASK);
        getInputMap(focusPeFereastra).put(tastaCtrlF, "FIND");
        getActionMap().put("FIND", btn4.getAction());

        // --- HELP BUTTON -----------------------------------------------------------------------------
        btn5 = new JButton("Help");
        btn5.setAction(new AbstractAction(btn5.getActionCommand()) {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    helpButtonPressed();
                } catch (MalformedURLException ex) {
                    Logger.getLogger(ButtonsPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        KeyStroke tastaF1 = KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0);
        getInputMap(focusPeFereastra).put(tastaF1, "PRESS");
        getActionMap().put("PRESS", btn5.getAction());

        this.add(btn1);
        this.add(btn2);
        this.add(btn3);
        //this.add(btn4);
        this.add(btn5);
    }

    private void linkRecentFilesPanel(NewDialog dialog) {
        dialog.getPath();
        //split the link and add to the files panel
    }

    public void helpButtonPressed() throws MalformedURLException {
        URL urlIndex = getClass().getResource("/res/help/index.html");
        new Help("Help", urlIndex);
    }
}
