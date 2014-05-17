package gui.dialogs;

import java.awt.Component;

import javax.swing.JFileChooser;

public class Open extends AbstractDialog {

    private int option = 1;

    public int getOption() {
        return option;
    }
    public Open(Component comp, String ext, String desc) {
        path = null;
        this.comp = comp;
        setFileFilter(new FileExtensionFilter(ext, desc));
    }

    @Override
    public void doModal() {
        if (showOpenDialog(comp) == JFileChooser.APPROVE_OPTION) {
            path = getSelectedFile().toString();
            option = JFileChooser.APPROVE_OPTION;
        }
    }
}
