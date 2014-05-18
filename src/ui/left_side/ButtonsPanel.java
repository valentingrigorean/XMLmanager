package ui.left_side;

import io.NewDialogIO;
import io.OpenDialogIO;
import io.SaveDialogIO;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import model.XMLValidator;
import ui.MainWindow;
import ui.utils.Help;
import ui.dialogs.NewDialog;
import ui.dialogs.OpenDialog;

public class ButtonsPanel extends JPanel {

    private JButton btn1;
    private JButton btn2;
    private JButton btn3;
    private JButton btn4;
    private JButton btn5;
    private MainWindow mw;

    public ButtonsPanel() {
        super();
        init();
    }

    public void setMainWindow(MainWindow mw) {
        this.mw = mw;
    }

    private void init() {
        this.setPreferredSize(new Dimension(300, 300));
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

        // --- SAVE BUTTON -----------------------------------------------------------------------------
        btn4 = new JButton("Validate");
        btn4.setAction(new AbstractAction(btn4.getActionCommand()) {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    validateButtonPressed();
                } catch (IOException ex) {
                    Logger.getLogger(ButtonsPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        KeyStroke tastaCtrlF5 = KeyStroke.getKeyStroke(KeyEvent.VK_F5, InputEvent.CTRL_DOWN_MASK);
        getInputMap(focusPeFereastra).put(tastaCtrlF5, "VALIDATE");
        getActionMap().put("VALIDATE", btn4.getAction());

        // --- HELP BUTTON -----------------------------------------------------------------------------
        btn5 = new JButton("Help");
        btn5.setAction(new AbstractAction(btn5.getActionCommand()) {
            @Override
            public void actionPerformed(ActionEvent e) {
                helpButtonPressed();
            }
        });
        KeyStroke tastaF1 = KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0);
        getInputMap(focusPeFereastra).put(tastaF1, "PRESS");
        getActionMap().put("PRESS", btn5.getAction());

        this.add(btn1);
        this.add(btn2);
        this.add(btn3);
        this.add(btn4);
        this.add(btn5);
    }

    private void linkRecentFilesPanel(NewDialog dialog) {
        dialog.getPath();
        //split the link and add to the files panel
    }

    public void validateButtonPressed() throws IOException {

        String fromXMLView = ((JTextArea) (mw.getViewsPanel().getXmlView().getView())).getText();
        //System.out.println(fromXMLView);

        PrintWriter out = new PrintWriter("tempFileValidate.xml");

        if (((JTextArea) (mw.getViewsPanel().getXmlView().getView())).getText().isEmpty()) {

            OpenDialog open = new OpenDialog(this, ".xml", "Extensible Markup Language (.XML)");

            int xmlViewGolDialog = JOptionPane.showConfirmDialog(null, "The xml View panel is empty ! \nDo you want to open an existing xml file ?", "Attention !", JOptionPane.YES_NO_OPTION);

            if (xmlViewGolDialog == JOptionPane.YES_OPTION) {

                open.doModal();
                if (open.getOption() != 1) {
                    ((JTextArea) (mw.getViewsPanel().getXmlView().getView())).setText(null);
                }
                if (open.isSet()) {

                    File fisierXML = new File(open.getPath());
                    Scanner input = new Scanner(fisierXML);

                    while (input.hasNext()) {
                        ((JTextArea) (mw.getViewsPanel().getXmlView().getView())).append(input.nextLine() + "\n");

                    }
                    input.close();
                }
            }
        } else if (!((JTextArea) (mw.getViewsPanel().getXmlView().getView())).getText().isEmpty()) {

            out.println(fromXMLView);
            out.close();

            XMLValidator.Validate("tempFileValidate.xml");
        }
    }

    public void helpButtonPressed() {
        URL index;
        Help fereastra = new Help();
        index = fereastra.getClass().getClassLoader().getResource("./ui/help_files/index.html");
        fereastra = new Help("Help", index);
    }
}
