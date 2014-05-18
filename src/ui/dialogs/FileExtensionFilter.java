package ui.dialogs;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class FileExtensionFilter extends FileFilter {

    public FileExtensionFilter(String ext, String description) {
        m_ext = ext;
        m_description = description;
    }

    @Override
    public boolean accept(File file) {
        if (file.isDirectory()) {
            return true;
        }
        return (file.getName().endsWith(m_ext));
    }

    @Override
    public String getDescription() {
        return m_description;
    }

    private final String m_ext;
    private final String m_description;
}
