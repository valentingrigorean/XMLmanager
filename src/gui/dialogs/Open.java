package gui.dialogs;

import java.awt.Component;

import javax.swing.JFileChooser;

public class Open extends AbstractDialog {

    public Open(Component comp, String ext, String desc) {
        path = null;
        this.comp = comp;
        setFileFilter(new FileExtensionFilter(ext, desc));
    }

    @Override
    public void doModal() {
        if (showOpenDialog(comp) == JFileChooser.APPROVE_OPTION) {
            path = getSelectedFile().toString();
        }
    }
}
