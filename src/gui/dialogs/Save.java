package gui.dialogs;

import java.awt.Component;

import javax.swing.JFileChooser;

public class Save extends AbstractDialog {

    private int option = 1;

    public int getOption() {
        return option;
    }
    public Save(Component comp, String ext, String desc) {

        path = null;
        this.comp = comp;
        setFileFilter(new FileExtensionFilter(ext, desc));
    }

    @Override
    public void doModal() {
        if (showSaveDialog(comp) == JFileChooser.APPROVE_OPTION) {
            path = getSelectedFile().toString();
            option =  JFileChooser.APPROVE_OPTION;
        }
    }
}
