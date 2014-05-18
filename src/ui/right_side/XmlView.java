package ui.right_side;

import java.awt.BorderLayout;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;
import javax.swing.ToolTipManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.fife.rsta.ac.LanguageSupportFactory;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rtextarea.RTextScrollPane;

public class XmlView extends AbstractView {    

    public XmlView() {
        super();
        init();
    }  
    
    public JPopupMenu getPopupMenu(){
        return ((RSyntaxTextArea)view).getPopupMenu();
    }

    private RSyntaxTextArea createTextArea() {
        RSyntaxTextArea textArea = new RSyntaxTextArea(25, 80);
        LanguageSupportFactory.get().register(textArea);
        textArea.setCaretPosition(0);
        textArea.requestFocusInWindow();
        textArea.setMarkOccurrences(true);
        textArea.setCodeFoldingEnabled(true);
        textArea.setTabsEmulated(true);
        textArea.setTabSize(3);
        textArea.setSyntaxEditingStyle("text/xml");
        ToolTipManager.sharedInstance().registerComponent(textArea);
        return textArea;
    }
    
    

    private void init() {
        view = createTextArea();
        label.setText("XMLView");
        scrollPane = new RTextScrollPane(view, true); 
        
        super.add(scrollPane, BorderLayout.CENTER);

        ((JTextArea) view).getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent de) {
                if (docListener) {
                    update.setType(Update.INSERT_UPDATE);
                    notifyObservers();
                }
            }

            @Override
            public void removeUpdate(DocumentEvent de) {
                if (docListener) {
                    update.setType(Update.REMOVE_UPDATE);
                    notifyObservers();
                }
            }

            @Override
            public void changedUpdate(DocumentEvent de) {
                if (docListener) {
                    update.setType(Update.CHANGE_UPDATE);
                    notifyObservers();
                }
            }
        }
        );
    }
}
