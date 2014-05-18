package ui.dialogs;

import java.awt.Component;
import javax.swing.JFileChooser;

public class NewDialog extends AbstractDialog {

    public NewDialog(Component comp, String ext, String desc) {
        super(comp, ext, desc);
    }

    @Override
    public void doModal() {
        path = null;
        option = showDialog(comp, "New");
        if (option == JFileChooser.APPROVE_OPTION) {
            path = getSelectedFile().toString();
        }
    }
}
