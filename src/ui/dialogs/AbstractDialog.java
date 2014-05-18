package ui.dialogs;

import java.awt.Component;

import javax.swing.JFileChooser;

public abstract class AbstractDialog extends JFileChooser {

    protected String path;
    protected Component comp;
    protected int option;

    public AbstractDialog(Component comp, String ext, String desc) {
        this.option = -1;
        this.comp = comp;
        this.setFileFilter(new FileExtensionFilter(ext, desc));
    }

    public abstract void doModal();

    public String getPath() {
        if (path.endsWith(".xml")) {
            return path;
        }
        return path + ".xml";
    }

    public boolean isSet() {
        return path != null;
    }

    public int getOption() {
        return option;
    }
}
