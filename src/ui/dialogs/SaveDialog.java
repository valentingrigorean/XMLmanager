package ui.dialogs;

import java.awt.Component;

import javax.swing.JFileChooser;

public class SaveDialog extends AbstractDialog {

    public SaveDialog(Component comp, String ext, String desc) {
        super(comp, ext, desc);
    }

    @Override
    public void doModal() {
        path = null;
        option = showSaveDialog(comp);
        if (option == JFileChooser.APPROVE_OPTION) {
            path = getSelectedFile().toString();
        }
    }
}
