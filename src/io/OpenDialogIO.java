package io;

import javax.swing.JOptionPane;
import ui.MainWindow;
import ui.dialogs.ConfirmPane;
import ui.dialogs.OpenDialog;

public class OpenDialogIO implements IDialogIO {

    private final OpenDialog open;
    private final MainWindow mw;

    public OpenDialogIO(MainWindow mw) {
        this.mw = mw;
        open = new OpenDialog(mw, ".xml", "Extensible Markup Language (.XML)");
    }

    @Override
    public void show() {
        if (mw.getFileStatus() == false) {
            open.doModal();
            if (open.isSet()) {
                mw.setCurrentFilePath(open.getPath());
                mw.getViewsPanel().setContent(new DocumentReader(
                        open.getPath()).readAllLines());                
            }           
            return;
        }
        switch (ConfirmPane.doModal(mw, "Save", "Do you want to save the existing data? ")) {
            case JOptionPane.YES_OPTION:
                new DocumentWriter(mw.getCurrentFilePath(), mw.getContent());
            case JOptionPane.NO_OPTION:
                mw.setFileStatus(false);
                show();
                break;
        }
    }
}
