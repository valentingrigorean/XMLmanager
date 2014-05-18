package ui.dialogs;

import java.awt.Component;

import javax.swing.JFileChooser;

public class OpenDialog extends AbstractDialog {

    public OpenDialog(Component comp, String ext, String desc) {
        super(comp, ext, desc);
    }

    @Override
    public void doModal() {
        path = null;
        option = showOpenDialog(comp);
        if (option == JFileChooser.APPROVE_OPTION) {
            path = getSelectedFile().toString();
        }
    }
}
