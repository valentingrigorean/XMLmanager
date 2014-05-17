package gui.right_side;

import java.awt.BorderLayout;
import javax.swing.JTextArea;
import javax.swing.ToolTipManager;
import org.fife.rsta.ac.LanguageSupportFactory;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rtextarea.RTextScrollPane;

public class XmlView extends AbstractView {   

    public XmlView() {
        super();
        init();
    }

    @Override
    public void parse() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void init() {
        view = createTextArea();
        label.setText("XMLEditor");
        scrollPane = new RTextScrollPane(view,true);
        super.add(scrollPane,BorderLayout.CENTER);
    }  

    public void setDefaultText() {
        ((JTextArea) (this.view)).insert("<?xml version=\"1.0\" encoding=\"UTF-8\"?>", 0);
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
}
