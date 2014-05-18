package ui.dialogs;

import java.awt.Component;
import javax.swing.JOptionPane;

public class ConfirmPane {

    /**
     *
     * @param comp
     * @param title
     * @param body
     * @return
     */
    public static int doModal(Component comp, String title, String body) {
        return JOptionPane.showConfirmDialog(comp, body, title,
                JOptionPane.YES_NO_CANCEL_OPTION);
    }
}
