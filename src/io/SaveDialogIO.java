package io;

import java.io.File;
import javax.swing.JOptionPane;
import ui.MainWindow;
import ui.dialogs.ConfirmPane;
import ui.dialogs.SaveDialog;

public class SaveDialogIO {

    private final SaveDialog save;
    private final MainWindow mw;

    public SaveDialogIO(MainWindow mw) {
        this.mw = mw;      
        save = new SaveDialog(mw, ".xml", "Extensible Markup Language (.XML)");
    }

    public void show() {        
        save.doModal();
        if (save.isSet() && !exists()) {
            new DocumentWriter(save.getPath(), mw.getContent());
            mw.setFileStatus(false);
            return;
        }
        switch(save.getOption()){
            case JOptionPane.NO_OPTION:
            case JOptionPane.CANCEL_OPTION:
            case JOptionPane.CLOSED_OPTION:
                return;
        }
        switch (ConfirmPane.doModal(mw, "Existing file",
                "The file already exist. \nDo you want to overwrite? ")) {
            case JOptionPane.YES_OPTION:
                new DocumentWriter(mw.getCurrentFilePath(), mw.getContent());
                mw.setFileStatus(false);
                return;
            case JOptionPane.NO_OPTION:
                show();
        }
    }

    private boolean exists() {
        if (save != null && save.isSet()) {
            File f = new File(save.getPath());
            return f.exists();
        }
        return false;
    }
}
