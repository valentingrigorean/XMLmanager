package xml;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXParseException;
import ui.MainWindow;

public class XmlErrorHandler implements ErrorHandler {
    private final MainWindow mw;

    public XmlErrorHandler(MainWindow mw) {
        this.mw = mw;
    }

    @Override
    public void warning(SAXParseException e) {
        mw.error("Warning", e.getMessage(), e.getLineNumber(),
                e.getColumnNumber());
    }

    @Override
    public void error(SAXParseException e) {
        mw.error("Error", e.getMessage(), e.getLineNumber(),
                e.getColumnNumber());
    }

    @Override
    public void fatalError(SAXParseException e) {
        mw.error("FatalError", e.getMessage(), e.getLineNumber(),
                e.getColumnNumber());
    }
}
