package gui.dialogs;

import java.awt.Component;

import javax.swing.JFileChooser;

public abstract class AbstractDialog extends JFileChooser {

    public abstract void doModal();

    public String getPath() {
        return path;
    }

    public boolean isSet() {
        return path != null;
    }

    protected String path;
    protected Component comp;
}
