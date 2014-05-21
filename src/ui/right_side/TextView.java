package ui.right_side;

import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ToolTipManager;
import org.fife.rsta.ac.LanguageSupportFactory;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

public class TextView extends AbstractView {

    public TextView() {
        super();
        init();
    }
    
       private JTextArea createJTextArea() {
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
        view = createJTextArea();
        label.setText("TextView");
        ((JTextArea) view).setEditable(false);
        scrollPane = new JScrollPane(view);
        ((JScrollPane) scrollPane).setViewportView(view);
        super.add(view, BorderLayout.CENTER);
        
       
    }
}
