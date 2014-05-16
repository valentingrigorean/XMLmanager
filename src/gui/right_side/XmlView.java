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
        super(new JTextArea(), "XmlView");
        init();
    }

    @Override
    public void parse() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void init() {
        highlighter = ((JTextArea) view).getHighlighter();
        painter = new DefaultHighlighter.DefaultHighlightPainter(Color.pink);       
    }

    @Override
    public void setInput(AbstractView absView) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
