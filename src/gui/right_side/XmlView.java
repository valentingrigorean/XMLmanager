package gui.right_side;

import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.text.Highlighter.HighlightPainter;

public class XmlView extends AbstractView {

    private Highlighter highlighter;
    private HighlightPainter painter;

    public XmlView() {
        super(new JTextArea(), "XMLView");
        init();
    }

    @Override
    public void parse() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void init() {
        highlighter = ((JTextArea) view).getHighlighter();
        painter = new DefaultHighlighter.DefaultHighlightPainter(Color.pink); 
        this.setDefaultText();
    }

    @Override
    public void setInput(AbstractView absView) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void setDefaultText()
    {
       // ((JTextArea)(this.view)).append("<?xml version=\"1.0\" encoding=\"UTF-8\"?> \n \n <root> \n </root>");
        ((JTextArea)(this.view)).insert("<?xml version=\"1.0\" encoding=\"UTF-8\"?> \n \n <root> \n </root>",0);
    }
}
