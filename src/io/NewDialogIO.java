package io;

import java.io.File;
import javax.swing.JOptionPane;
import ui.MainWindow;
import ui.dialogs.ConfirmPane;
import ui.dialogs.NewDialog;

public class NewDialogIO implements IDialogIO {

    private final NewDialog newDialog;
    private final MainWindow mw;

    public NewDialogIO(MainWindow mw) {
        this.mw = mw;
        newDialog = new NewDialog(mw, ".xml", "Extensible Markup Language (.XML)");
    }

    @Override
    public void show() {
        if (mw.getFileStatus() == false) {
            newDialog.doModal();
            if (newDialog.isSet()) {
                if (!exists()) {
                    mw.setCurrentFilePath(newDialog.getPath());
                    new DocumentWriter(newDialog.getPath(),"<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
                    mw.getViewsPanel().setContent("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
                    mw.setFileStatus(false);
                    return;
                }
                switch (ConfirmPane.doModal(mw, "Existing file",
                        "The file already exist. \nDo you want to overwrite? ")) {
                    case JOptionPane.YES_OPTION:
                        new DocumentWriter(newDialog.getPath(), "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
                        mw.setFileStatus(false);
                        mw.setCurrentFilePath(newDialog.getPath());
                        return;
                    case JOptionPane.NO_OPTION:
                        show();
                }
            }
        }
        switch (ConfirmPane.doModal(mw, "Save", "Do you want to save the existing data? ")) {
            case JOptionPane.YES_OPTION:
                if (mw.getCurrentFilePath() == null) {
                    SaveDialogIO io = new SaveDialogIO(mw);
                    io.show();
                    return;
                } else {
                    new DocumentWriter(mw.getCurrentFilePath(), mw.getContent());
                }
            case JOptionPane.NO_OPTION:
                mw.setFileStatus(false);
                show();
                break;
        }
    }

    private boolean exists() {
        if (newDialog != null && newDialog.isSet()) {
            File f = new File(newDialog.getPath());
            return f.exists();
        }
        return false;
    }
}
